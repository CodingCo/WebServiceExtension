package handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author simon
 */
public class ServerFileHandler implements HttpHandler {

    String contentType = "";
    String contentFolder = "public/";
    ServerResponse response = new ServerResponse();

    @Override
    public void handle(HttpExchange he) throws IOException {

        String requestedFile = he.getRequestURI().getPath().substring(1);
        File file;

        if (requestedFile.equals("")) {
            file = new File(contentFolder + "index.html");
            contentType = "text/html";
        } else if (requestedFile.equals("/")) {
            file = new File(contentFolder + "index.html");
            contentType = "text/html";
        } else {
            file = new File(contentFolder + requestedFile);
            contentType = getExtension(requestedFile.substring(requestedFile.lastIndexOf(".")));
        }

        if (!file.exists()) {
            response.error(he, 404, response.FILE_NOT_FOUND);
            return;
        }

        byte[] bytesToSend = new byte[(int) file.length()];
        try (FileInputStream input = new FileInputStream(file);) {
            input.read(bytesToSend, 0, bytesToSend.length);
        }

        Headers h = he.getResponseHeaders();
        h.add("Content-Type", contentType);
        h.add("charset", "UTF-8");
        response.send(he, 200, bytesToSend);
    }

    private String getExtension(String extension) {
        String image = "image/";
        switch (extension) {
            case ".html":
                return "text/html";
            case ".js":
                return "text/javascript";
            case ".jar":
                return "application/java-archive";
            case ".css":
                return "text/css";
            case ".pdf":
                return "application/pdf";
            case ".png":
                return image + "png";
            case ".jpeg":
                return image + "jpeg";
            case ".jpg":
                return image + "jpg";
            case ".gif":
                return image + "gif";
            case ".ico":
                return image + "x-icon";
            case ".zip":
                return "application/zip";
            default:
                return "text/plain";
        }
    }
}
