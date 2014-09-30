package handlers;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author simon
 */
public class ServerResponse {

    public String FILE_NOT_FOUND = "404 not found";

    public void error(HttpExchange he, int statusCode, String message) throws IOException {
        send(he, statusCode, message.getBytes());
    }

    public void send(HttpExchange he, int statusCode, byte[] bytesToSend) throws IOException {
        he.sendResponseHeaders(statusCode, 0);
        try (OutputStream responseBody = he.getResponseBody()) {
            responseBody.write(bytesToSend, 0, bytesToSend.length);
        }
    }

    public void sendMessage(HttpExchange he, int statusCode, String message) throws IOException {
        send(he, statusCode, message.getBytes());
    }
}
