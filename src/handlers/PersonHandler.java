package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import model.AssistentTeacher;
import model.Person;
import webinterfaces.FacadeInterface;

/**
 *
 * @author Robert
 */
public class PersonHandler implements HttpHandler {

    List<Person> list = new ArrayList();
    FacadeInterface facade;
    ServerResponse sr;

    public PersonHandler() {

        sr = new ServerResponse();
        //facade = new Facade(); 

    }

    @Override
    public void handle(HttpExchange he) throws IOException {

        list.add(new Person("Kasper", "Hald", "234", "123", new AssistentTeacher()));
        list.add(new Person("Kasper", "Hald", "234", "123", new AssistentTeacher()));
        list.add(new Person("Kasper", "Hald", "234", "123", new AssistentTeacher()));

        String method = he.getRequestMethod().toUpperCase();
        String response = "";
        int status = 0;

        System.out.println(method);
        switch (method) {

            case "GET":
                try {
                    String path = he.getRequestURI().getPath();
                    int lastIndex = path.lastIndexOf("/");
                    if (lastIndex > 0) {
                        int id = Integer.parseInt(path.substring(lastIndex + 1));
                        response = facade.getOnePersonAsJson(id);
                        status = 200;
                    } else {
                        //response = facade.getPersonsAsJSON();
                        String json = new Gson().toJson(list);
                        response = json;
                        status = 200;
                    }
                } catch (NumberFormatException nfe) {
                    response = "id is not a number";
                    status = 404;
                }
                break;

            case "POST":
                System.out.println("POST");
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

            case "OPTIONS":
                System.out.println("DELETE");
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
                System.out.println("PUT");
                break;
        }

        System.out.println(response);
        he.getResponseHeaders().add("Content-Type", "application/json");
        sr.sendMessage(he, status, response);

    }

}
