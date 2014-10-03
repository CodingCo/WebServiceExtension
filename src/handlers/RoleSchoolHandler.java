package handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import facades.PersonFacade;
import facades.RoleSchoolAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import model.Person;
import model.RoleSchool;
import webinterfaces.FacadeInterface;

/**
 *
 * @author christophermortensen
 */
public class RoleSchoolHandler implements HttpHandler{

    FacadeInterface facade;
    ServerResponse sr;
    GsonBuilder gsonBuilder;
    Gson trans;
    
    public RoleSchoolHandler(){
        sr = new ServerResponse();
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(RoleSchool.class, new RoleSchoolAdapter());
        trans = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
        facade = new PersonFacade(trans);
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
                    response = null;
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
                    response = null;
                } catch (IllegalArgumentException e) {
                    status = 400;
                    response = e.getMessage();
                }
                break;

            case "DELETE":
                try {
                    InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "UTF-8");
                    BufferedReader br = new BufferedReader(isr);
                    String jsonInput = br.readLine();
                    
                    String path = he.getRequestURI().getPath();
                    int lastIndex = path.lastIndexOf("/");
                    
                    if(lastIndex > 0){
                        System.err.println("INSIDE DELETE ROLENAME");
                        int id = Integer.parseInt(path.substring(lastIndex + 1));
                        RoleSchool r = facade.deleteRoleSchool(id, jsonInput);
                        response = trans.toJson(r);
                        status = 200;
                        
                    } else{
                        status = 400;
                        response = "no id";
                    }
                } catch (NumberFormatException nfe) {
                    response = "id is not a number";
                    status = 404;
                }
                break;

            case "PUT":
                try {
                    InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "UTF-8");
                    BufferedReader br = new BufferedReader(isr);
                    String jsonInput = br.readLine();

                    String path = he.getRequestURI().getPath();
                    int lastIndex = path.lastIndexOf("/");
                    
                    if (lastIndex > 0) {
                        int id = Integer.parseInt(path.substring(lastIndex + 1));
                        RoleSchool r = facade.addRoleSchool(jsonInput, id);
                        response = trans.toJson(r);
                        status = 200;
                    }else {
                        status = 400;
                        response = "no id";
                    }
                } catch (NumberFormatException nfe) {
                    response = "id is not a number";
                    status = 404;
                }

                break;
        }

        he.getResponseHeaders().add("Content-Type", "application/json");
        sr.sendMessage(he, status, response);

    }
    
    
}
