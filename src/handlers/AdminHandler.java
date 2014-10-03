package handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import serverinterfaces.IHandler;

/**
 *
 * @author simon
 */
public class AdminHandler implements HttpHandler {

    private final String contentFolder = "logfiles/";
    private IHandler handler;
    private final ServerResponse response = new ServerResponse();

    public AdminHandler(IHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        String resource = he.getRequestURI().getPath().substring(5);
        StringBuilder br = new StringBuilder();

        String contentType = "";

        if (resource.equals("online")) {
            String onlineUsers = handler.getUsers();

            if (!onlineUsers.isEmpty()) {
                String json = "[";
                contentType = "application/json";
                for (String user : onlineUsers.split(",")) {
                    json += "{ \"name\": \"";
                    json += user;
                    json += "\"},";
                    System.out.println("hej");
                }
                json = json.substring(0, json.length() - 1);
                json += "]";
                br.append(json);
            }else{
                br.append("nousers");
            }

        }

        if (resource.equals("log")) {
            File logFile = new File(contentFolder + "serverlog0.txt");
            BufferedReader input = new BufferedReader(new FileReader(logFile));
            contentType = "text/plain";

            String line;
            while ((line = input.readLine()) != null) {
                br.append("<li class=\"list-group-item\">");
                br.append(line);
                br.append("</li>");
            }
        }

        String res = br.toString();
        Headers h = he.getResponseHeaders();
        h.add("Content-Type", "text/html");
        h.add("charset", "UTF-8");
        response.send(he, 200, res.getBytes());
    }

}
