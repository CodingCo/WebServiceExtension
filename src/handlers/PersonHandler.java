package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import model.Person;
import model.RoleSchool;
import webinterfaces.FacadeInterface;

/**
 *
 * @author Robert
 */
public class PersonHandler implements HttpHandler {

    FacadeInterface facade;
    ServerResponse sr;

    public PersonHandler() {

        sr = new ServerResponse();
        //facade = new Facade();   
    }

    @Override
    public void handle(HttpExchange he) throws IOException {

        String method = he.getRequestMethod().toUpperCase();
        String response = "";
        int status = 0;

        switch (method) {

            case "GET":
                try {
                    String path = he.getRequestURI().getPath();
                    int lastIndex = path.lastIndexOf("/");
                    if (lastIndex > 0) {
                        int id = Integer.parseInt(path.substring(lastIndex + 1));
                        response = facade.getOnePersonAsJson(id);
                    } else {
                        response = facade.getPersonsAsJSON();
                    }
                } catch (NumberFormatException nfe) {
                    response = "id is not a number";
                    status = 404;
                }
                break;

            case "POST":
                try {
                    InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "UTF-8");
                    BufferedReader br = new BufferedReader(isr);
                    String jsonInput = br.readLine();

                    Person p = facade.addPersonFromGson(jsonInput);
                    response = new Gson().toJson(p);

                } catch (IllegalArgumentException e) {
                    status = 400;
                    response = e.getMessage();
                }
                break;

            case "DELETE":
                try {
                    String path = he.getRequestURI().getPath();
                    int lastIndex = path.lastIndexOf("/");
                    if (lastIndex > 0) {
                        int id = Integer.parseInt(path.substring(lastIndex + 1));
                        Person p = facade.delete(id);
                        response = new Gson().toJson(p);
                    } else {
                        status = 400;
                        response = "no id";
                    }
                } catch (NumberFormatException nfe) {
                    response = "id is not a number";
                    status = 404;
                }
                break;

            case "PUT":
                break;
        }
        
        he.getResponseHeaders().add("Content-Type", "application/json");
        sr.sendMessage(he, status, response);  

    }

}
