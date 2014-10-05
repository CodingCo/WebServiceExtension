package webserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpServer;
import facades.PersonFacade;
import adapters.RoleSchoolAdapter;
import facades.CourseFacade;
import handlers.AcademyHandler;
import handlers.AdminHandler;
import handlers.CourseHandler;
import handlers.PersonHandler;
import handlers.RoleSchoolHandler;
import handlers.ServerFileHandler;
import handlers.ServerResponse;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Properties;
import model.Course;
import model.RoleSchool;
import serverinterfaces.IHandler;
import utility.Utility;
import webinterfaces.CourseFacadeInterface;
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
    
    PersonFacadeInterface personFacade;
    CourseFacadeInterface courseFacade;
    
    ServerResponse sr;
    GsonBuilder gsonBuilder;
    Gson trans;

    public WebServer(IHandler handler) {
        this.handler = handler;
        
        sr = new ServerResponse();
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(RoleSchool.class, new RoleSchoolAdapter());
        trans = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
        personFacade = new PersonFacade(trans);
        courseFacade = new CourseFacade(trans);
    }

    public void startServer() throws IOException {
        ip = property.getProperty("ipaddress", "100.85.90.7");
        port = Integer.parseInt(property.getProperty("webport", "8028"));
        server = HttpServer.create(new InetSocketAddress(ip, port), 0);
        server.createContext("/", new ServerFileHandler());
        server.createContext("/log", new AdminHandler(handler));
        server.createContext("/course", new CourseHandler(trans, courseFacade, sr));
        server.createContext("/person", new PersonHandler(trans, personFacade, sr));
        server.createContext("/academy", new AcademyHandler());
        server.createContext("/roleschool", new RoleSchoolHandler(trans, personFacade, sr));
        server.setExecutor(null);
        server.start();
        System.out.println("Server started, listening on port: " + port);
    }

    public void closeHttpServer() {
        server.stop(2);
        System.out.println("webserver closed");
    }

}
