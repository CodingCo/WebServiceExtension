package webserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpServer;
import facades.PersonFacade;
import adapters.RoleSchoolAdapter;
import handlers.AcademyHandler;
import handlers.AdminHandler;
import handlers.PersonHandler;
import handlers.RoleSchoolHandler;
import handlers.ServerFileHandler;
import handlers.ServerResponse;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Properties;
import model.RoleSchool;
import serverinterfaces.IHandler;
import utility.Utility;
import webinterfaces.PersonFacadeInterface;

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
    
    PersonFacadeInterface facade;
    ServerResponse sr;
    GsonBuilder gsonBuilder;
    Gson trans;

    public WebServer(IHandler handler) {
        this.handler = handler;
        
        sr = new ServerResponse();
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(RoleSchool.class, new RoleSchoolAdapter());
        trans = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
        facade = new PersonFacade(trans);
    }

    public void startServer() throws IOException {
        ip = property.getProperty("ipaddress", "100.85.90.7");
        port = Integer.parseInt(property.getProperty("webport", "8028"));
        server = HttpServer.create(new InetSocketAddress(ip, port), 0);
        server.createContext("/", new ServerFileHandler());
        server.createContext("/log", new AdminHandler(handler));
        server.createContext("/person", new PersonHandler(trans, facade, sr));
        server.createContext("/academy", new AcademyHandler());
        server.createContext("/roleschool", new RoleSchoolHandler(trans, facade, sr));
        server.setExecutor(null);
        server.start();
        System.out.println("Server started, listening on port: " + port);
    }

    public void closeHttpServer() {
        server.stop(2);
        System.out.println("webserver closed");
    }

}
