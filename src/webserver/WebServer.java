package webserver;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import serverinterfaces.IHandler;
import utility.Utility;

/**
 *
 * @author simon
 */
public class WebServer {

    private HttpServer server;
    private int port;
    private String ip;
    private final Properties property = Utility.initProperties("serverproperties.txt");
    private IHandler handler;

    public WebServer(IHandler handler) {
        this.handler = handler;
    }

    public void startServer() {
        ip = property.getProperty("ipaddress", "100.85.90.7");
        port = Integer.parseInt(property.getProperty("webport", "8028"));
        try {
            server = HttpServer.create(new InetSocketAddress(ip, port), 0);
            server.createContext("/", new PageHandler());
            server.createContext("/log", new LogHandler());
            server.setExecutor(null);
            server.start();
        } catch (IOException ex) {
            Logger.getLogger(WebServer.class.getName()).log(Level.SEVERE, ex.toString());
        }
        System.out.println("Server started, listening on port: " + port);
    }

    public void closeHttpServer() {
        server.stop(5);
        System.out.println("webserver closed");
    }

    class PageHandler implements HttpHandler {

        String contentType = "";
        String contentFolder = "public/";

        @Override

        public void handle(HttpExchange he) throws IOException {
            String fileName = he.getRequestURI().getPath().substring(1);
            File file;

            if (fileName.isEmpty() || fileName.equals("/")) {
                file = new File(contentFolder + "index.html");
            } else {
                contentType = getContentType(fileName);
                file = new File(contentFolder + fileName);
            }

            byte[] bytesToSend = new byte[(int) file.length()];
            try {
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                bis.read(bytesToSend, 0, bytesToSend.length);
            } catch (IOException ie) {
                Logger.getLogger(WebServer.class.getName()).log(Level.SEVERE, null, ie);
            }
            Headers h = he.getResponseHeaders();
            h.add("Content-Type", contentType);
            he.sendResponseHeaders(200, bytesToSend.length);

            try (OutputStream os = he.getResponseBody()) {
                os.write(bytesToSend, 0, bytesToSend.length);
            }
        }
    }

    class LogHandler implements HttpHandler {

        String contentType = "";
        String contentFolder = "logfiles/";
        StringBuilder sb;
        FileReader in;
        BufferedReader br;

        @Override

        public void handle(HttpExchange he) throws IOException {

            sb = new StringBuilder();
            in = new FileReader(contentFolder + "serverlog0.txt");
            br = new BufferedReader(in);

            File first = new File("public/" + "admin1.html");
            File second = new File("public/" + "admin2.html");
            File third = new File("public/" + "admin3.html");
            BufferedReader reader = new BufferedReader(new FileReader(first));
            StringBuilder hbr = new StringBuilder();

            String input;
            while ((input = reader.readLine()) != null) {
                hbr.append(input);
            }
            String line;
            while ((line = br.readLine()) != null) {
                hbr.append("<li class=\"list-group-item\">");
                hbr.append(line);
                hbr.append("</li>");
            }
            reader = new BufferedReader(new FileReader(second));
            while ((input = reader.readLine()) != null) {
                hbr.append(input);
            }

            String onlineUsers = handler.getUsers();
            
            if (onlineUsers.equals("")) {
                hbr.append("<li class=\"list-group-item list-group-item-danger \">");
                hbr.append("no users online");
                hbr.append("</li>");
            } else {
                for (String name : onlineUsers.split(",")) {
                    hbr.append("<li class=\"list-group-item list-group-item-success \">");
                    hbr.append(name);
                    hbr.append("</li>");
                }
            }
            reader = new BufferedReader(new FileReader(third));
            while ((input = reader.readLine()) != null) {
                hbr.append(input);
            }

            String response = hbr.toString();
            Headers h = he.getResponseHeaders();
            h.add("Content-Type", "text/html");
            he.sendResponseHeaders(200, response.length());
            try (PrintWriter pw = new PrintWriter(he.getResponseBody())) {
                pw.print(response);
            }
            in.close();
            br.close();

        }
    }

    private static String getContentType(String s) {

        String contentType = s.substring(s.lastIndexOf(".") + 1);
        switch (contentType) {
            case "html":
                return "text/html";
            case "css":
                return "text/css";
            case "pdf":
                return "application/pdf";
            case "jar":
                return "applikation/zip";
            case "png":
            case "jpeg":
            case "jpg":
            case "gif":
                return "image/" + contentType;
        }
        return contentType;
    }

}
