package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;

/**
 *
 * @author kasper
 */
public class CourseHandler implements HttpHandler {

    public CourseHandler(){
	
    }
    
    @Override
    public void handle(HttpExchange he) throws IOException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
