package handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import facades.CourseFacade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import model.Course;
import model.Person;
import webinterfaces.CourseFacadeInterface;
import webinterfaces.PersonFacadeInterface;

/**
 *
 * @author kasper
 */
public class CourseHandler implements HttpHandler {

    Gson trans;
    CourseFacadeInterface facade;
    ServerResponse sr;

    private String response;
    private int status;

    public CourseHandler(Gson trans, CourseFacadeInterface facade, ServerResponse sr) {
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
                response = facade.getOneCourseAsJson(id);
                status = 200;
            } else {
                response = facade.getAllCoursesAsJson();
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
            Course c = facade.addCourseFromGson(jsonInput);
            response = trans.toJson(c);

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
                Course c = facade.deleteCourse(id);
                response = trans.toJson(c);
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

    //== jsonInput/data from client should be like this to make it work: 
    // { "type": "assign"/"unassign", "roleId": "roleIdTo(Assign/Unassign) }
    private void putRequest(HttpExchange he) throws IOException {

        try {
            InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String jsonInput = br.readLine();
            long roleId = parseRoleId(jsonInput);
            String path = he.getRequestURI().getPath();
            int lastIndex = path.lastIndexOf("/");

            if (lastIndex > 0 && jsonInput.toLowerCase().contains("unassign")) {        // unassigning happens here
                int id = Integer.parseInt(path.substring(lastIndex + 1));
                Course editedCourse = facade.unassignRoleSchoolFromCourse(roleId, id);
                response = trans.toJson(editedCourse);
                status = 200;
            } else if (lastIndex > 0 && jsonInput.toLowerCase().contains("assign")) {     // assigning happens here
                int id = Integer.parseInt(path.substring(lastIndex + 1));
                Course editedCourse = facade.assignRoleSchoolToCourse(roleId, id);
                response = trans.toJson(editedCourse);
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

    private long parseRoleId(String json){
        String jsonSub = json.substring(1, json.length()-1);
        String stringRoleId = jsonSub.split(",")[1].split(":")[1].trim();
        long roleId = Long.parseLong(stringRoleId); // stringRoleId.substring(1, stringRoleId.length()-1) if "100000"
        return roleId;
    }
    
    
}
