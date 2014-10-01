package webserver;

import com.sun.net.httpserver.HttpServer;
import handlers.AdminHandler;
import handlers.ServerFileHandler;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Properties;
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

    public void startServer() throws IOException {
        ip = property.getProperty("ipaddress", "100.85.90.7");
        port = Integer.parseInt(property.getProperty("webport", "8028"));
        server = HttpServer.create(new InetSocketAddress(ip, port), 0);
        server.createContext("/", new ServerFileHandler());
        server.createContext("/log", new AdminHandler(handler));
        server.setExecutor(null);
        server.start();
        System.out.println("Server started, listening on port: " + port);
    }

    public void closeHttpServer() {
        server.stop(2);
        System.out.println("webserver closed");
    }

}
