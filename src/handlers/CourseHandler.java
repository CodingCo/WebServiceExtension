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

/**
 *
 * @author kasper
 */
public class CourseHandler implements HttpHandler {

    private CourseFacade facade;
    private ServerResponse sr;
    private GsonBuilder gson;
    private Gson transaction;
    private String response;
    private int status;

    public CourseHandler() {
	response = "";
	status = 0;
	sr = new ServerResponse();
	gson = new GsonBuilder();
	transaction = gson.excludeFieldsWithoutExposeAnnotation().create();
	facade = new CourseFacade(transaction);
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
	String method = he.getRequestMethod().toUpperCase();

	switch (method) {
	    case "GET":
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
		    response = "Id is not a number.";
		    status = 404;
		}
		break;
	    case "POST":
		try {
		    InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "UTF-8");
		    BufferedReader br = new BufferedReader(isr);
		    String jsonInput = br.readLine();

		    Course co = facade.addCourseFromGson(jsonInput);
		    
		    status = 200;
		    response = transaction.toJson(co);
		} catch (IllegalArgumentException iae) {
		    status = 400;
		    response = iae.getMessage();
		}
		break;
	    case "DELETE":
		try {
                    String path = he.getRequestURI().getPath();
                    int lastIndex = path.lastIndexOf("/");
                    if (lastIndex > 0) {
                        int id = Integer.parseInt(path.substring(lastIndex + 1));
                        Course co = facade.deleteCourse(id);
			
                        status = 200;
			response = transaction.toJson(co);
                    } else {
                        status = 400;
                        response = "no legal id";
                    }
                } catch (NumberFormatException nfe) {
                    response = "id is not a number";
                    status = 404;
                }
		break;
	    case "PUT":

		break;
	}

	he.getResponseHeaders().add("Content-type", "application/json");
	sr.sendMessage(he, status, response);
    }

}
