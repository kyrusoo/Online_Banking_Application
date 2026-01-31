import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleRestServer {
    public static void main(String[] args) {
        try {
            // Create a server on port 8080
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            // Link the URL path "/api/customers" to the logic in CustomerHandler
            server.createContext("/api/customers", new CustomerHandler());

            // Set a default executor (null uses the default)
            server.setExecutor(null);

            System.out.println("🚀 Server started!");
            System.out.println("Local API available at: http://localhost:8080/api/customers");

            server.start();

        } catch (IOException e) {
            System.err.println("Failed to start server: " + e.getMessage());
        }
    }
}