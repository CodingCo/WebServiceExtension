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
    private final IHandler handler;
    private final ServerResponse response = new ServerResponse();

    public AdminHandler(IHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handle(HttpExchange he) throws IOException {

        File logFile = new File(contentFolder + "serverlog0.txt");
        BufferedReader input = new BufferedReader(new FileReader(logFile));
        StringBuilder br = new StringBuilder();

        String onlineUsers = handler.getUsers();

        String line;
        while ((line = input.readLine()) != null) {
            br.append("<li class=\"list-group-item\">");
            br.append(line);
            br.append("</li>");
        }

        String log = br.toString();
        Headers h = he.getResponseHeaders();
        h.add("Content-Type", "text/html");
        h.add("charset", "UTF-8");
        response.send(he, 200, log.getBytes());
    }

}
