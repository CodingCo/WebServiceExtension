package handlers;

import facades.PersonFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import adapters.RoleSchoolAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import model.Person;
import model.RoleSchool;
import webinterfaces.PersonFacadeInterface;

/**
 *
 * @author Robert
 */
public class PersonHandler implements HttpHandler {

    Gson trans;
    PersonFacadeInterface facade;
    ServerResponse sr;
    
    private String response;
    private int status;

    public PersonHandler(Gson trans, PersonFacadeInterface facade, ServerResponse sr) {
        this.trans = trans;
        this.facade = facade;
        this.sr = sr;
    }

    @Override
    public void handle(HttpExchange he) throws IOException {

        String method = he.getRequestMethod().toUpperCase();
        response = "";
        status = 0;
        
        switch (method) {
            case "GET":
                getRequest(he);
                break;

            case "POST":
                postRequest(he);
                break;

            case "DELETE":
                deleteRequest(he);
                break;

            case "PUT":
                putRequest(he);
                break;
        }
        he.getResponseHeaders().add("Content-Type", "application/json");
        sr.sendMessage(he, status, response);
    }

    private void getRequest(HttpExchange he) throws IOException {
        try {
            String path = he.getRequestURI().getPath();
            int lastIndex = path.lastIndexOf("/");
            if (lastIndex > 0) {
                int id = Integer.parseInt(path.substring(lastIndex + 1));
                response = facade.getOnePersonAsJson(id);
                status = 200;
            } else {
                response = facade.getPersonsAsJSON();
                status = 200;
            }
        } catch (NumberFormatException nfe) {
            response = "id is not a number";
            status = 404;
        }
    }

    private void postRequest(HttpExchange he) throws IOException {

        try {
            InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String jsonInput = br.readLine();

            Person p = facade.addPersonFromGson(jsonInput);
            response = trans.toJson(p);

        } catch (IllegalArgumentException e) {
            status = 400;
            response = e.getMessage();
        }

    }

    private void deleteRequest(HttpExchange he) throws IOException {
        try {
            InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String jsonInput = br.readLine();

            String path = he.getRequestURI().getPath();
            int lastIndex = path.lastIndexOf("/");
            if (lastIndex > 0) {
                int id = Integer.parseInt(path.substring(lastIndex + 1));
                Person p = facade.delete(id);
                response = trans.toJson(p);
                status = 200;
            } else {
                status = 400;
                response = "no id";
            }
        } catch (NumberFormatException nfe) {
            response = "id is not a number";
            status = 404;
        }
    }

    private void putRequest(HttpExchange he) throws IOException {

        try {
            InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String jsonInput = br.readLine();

            String path = he.getRequestURI().getPath();
            int lastIndex = path.lastIndexOf("/");

            if (lastIndex > 0) {
                int id = Integer.parseInt(path.substring(lastIndex + 1));
                Person editedPerson = facade.editPerson(jsonInput, id);
                response = trans.toJson(editedPerson);
                status = 200;
            } else {
                status = 400;
                response = "no id";
            }
        } catch (NumberFormatException nfe) {
            response = "id is not a number";
            status = 404;
        }
    }

}
