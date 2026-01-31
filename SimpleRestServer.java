import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;

public class SimpleRestServer {
    public static void main(String[] args) throws IOException {
        // Create a server listening on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Create an endpoint at "/api/customers"
        server.createContext("/api/customers", new CustomerHandler());

        System.out.println("Server started at http://localhost:8080/api/customers");
        server.start();
    }

    static class CustomerHandler implements HttpHandler {
        private final DatabaseManager db = new DatabaseManager();
        private final ObjectMapper mapper = new ObjectMapper(); // Jackson JSON tool

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                List<Customer> customers = db.getAllCustomers();
                String jsonResponse = mapper.writeValueAsString(customers);
                sendResponse(exchange, jsonResponse, 200);

            } else if ("POST".equals(exchange.getRequestMethod())) {
                // 1. Read the JSON string sent by the user
                java.util.Scanner s = new java.util.Scanner(exchange.getRequestBody()).useDelimiter("\\A");
                String body = s.hasNext() ? s.next() : "";

                // 2. Convert JSON string into a Java Customer object
                Customer newCustomer = mapper.readValue(body, Customer.class);

                // 3. Save it using your existing DatabaseManager
                db.saveCustomer(newCustomer);

                // 4. Send success message
                String response = "Customer created successfully!";
                sendResponse(exchange, response, 201);
            }
        }

        // Helper method to keep code clean
        private void sendResponse(HttpExchange exchange, String response, int code) throws IOException {
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(code, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }
}