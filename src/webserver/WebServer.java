package webserver;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import handlers.ServerFileHandler;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.in;
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

    // TO BE EDITED
    public void startServer() {
        ip = property.getProperty("ipaddress", "100.85.90.7");
        port = Integer.parseInt(property.getProperty("webport", "8028"));
        try {
            server = HttpServer.create(new InetSocketAddress(ip, port), 0);
            server.createContext("/", new ServerFileHandler());
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

    class LogHandler implements HttpHandler {

        String contentType = "";
        String contentFolder = "logfiles/";

        @Override

        public void handle(HttpExchange he) throws IOException {

            File logFile = new File(contentFolder + "serverlog0.txt");
            BufferedReader input = new BufferedReader(new FileReader(logFile));
            StringBuilder br = new StringBuilder();
       
            String line;
            while ((line = input.readLine()) != null) {
                br.append("<li class=\"list-group-item\">");
                br.append(line);
                br.append("</li>");
            }
            
            
            String onlineUsers = handler.getUsers();

          

//       //     String response = hbr.toString();
//            Headers h = he.getResponseHeaders();
//            h.add("Content-Type", "text/html");
//            he.sendResponseHeaders(200, response.length());
//            try (PrintWriter pw = new PrintWriter(he.getResponseBody())) {
//                pw.print(response);
//            }
//            in.close();
//          //  br.close();

        }
    }

   
}
