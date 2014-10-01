package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import model.RoleSchool;
import webinterfaces.FacadeInterface;

/**
 *
 * @author Robert
 */
public class RoleHandler implements HttpHandler {

    FacadeInterface facade;
    ServerResponse sr;

    public RoleHandler() {
        sr = new ServerResponse();
        //facade = new Facade();
    }

    @Override
    public void handle(HttpExchange he) throws IOException {

        String method = he.getRequestMethod();
        int status = 0;
        String response = "";
        switch (method) {

            case "GET":
                break;
            case "POST":

                try {
                    InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "UTF-8");
                    BufferedReader br = new BufferedReader(isr);
                    String jsonInput = br.readLine();

                    String path = he.getRequestURI().getPath();
                    int lastIndex = path.lastIndexOf("/");
                    if (lastIndex > 0) {
                        int id = Integer.parseInt(path.substring(lastIndex + 1));
                        RoleSchool r = facade.addRoleSchool(jsonInput, id);
                        response = new Gson().toJson(r);
                    } else {
                        status = 400;
                        response = "no id";
                    }
                } catch (NumberFormatException nfe) {
                    response = "id is not a number";
                    status = 404;
                }

                break;
            case "DELETE":
                break;
            case "PUT":
                break;

        }

        he.getResponseHeaders().add("Content-Type", "application/json");
        sr.sendMessage(he, status, response);
    }

}
