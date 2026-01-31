import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Scanner;

public class CustomerHandler implements HttpHandler {
    private final DatabaseManager db = new DatabaseManager();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();

        try {
            if ("GET".equals(method)) {
                // Get all customers from your existing DB manager
                List<Customer> customers = db.getAllCustomers();
                String json = mapper.writeValueAsString(customers);
                sendResponse(exchange, json, 200);

            } else if ("POST".equals(method)) {
                // Read the JSON body from Postman
                Scanner scanner = new Scanner(exchange.getRequestBody()).useDelimiter("\\A");
                String body = scanner.hasNext() ? scanner.next() : "";

                // Convert JSON to your existing Customer object
                Customer newCustomer = mapper.readValue(body, Customer.class);

                // Use your existing save method
                db.saveCustomer(newCustomer);
                sendResponse(exchange, "Success: Customer added.", 201);

            } else if ("DELETE".equals(method)) {
                // 1. Get the Query String (the part after the ?)
                String query = exchange.getRequestURI().getQuery();
                String customerId = null;

                // 2. Simple parsing to find "id="
                if (query != null && query.contains("id=")) {
                    customerId = query.split("id=")[1];
                }

                if (customerId != null) {
                    // 3. Call your existing DatabaseManager method
                    // Note: Ensure this method exists in your DatabaseManager
                    db.deleteCustomer(customerId);
                    sendResponse(exchange, "Customer " + customerId + " deleted.", 200);
                } else {
                    sendResponse(exchange, "Error: Please provide an ID (?id=XYZ)", 400);
                }
            } else {
                sendResponse(exchange, "Method Not Allowed", 405);
            }
        } catch (Exception e) {
            // This prevents the "infinite loading" if the DB fails
            sendResponse(exchange, "Error: " + e.getMessage(), 500);
        }
    }

    // This helper ensures the connection ALWAYS closes
    private void sendResponse(HttpExchange exchange, String response, int code) throws IOException {
        byte[] bytes = response.getBytes();
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(code, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}