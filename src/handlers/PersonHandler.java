package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;

/**
 *
 * @author Robert
 */
public class PersonHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {
        
        String method = he.getRequestMethod().toUpperCase();
        
        switch(method){
        
            case "GET":
                
                
                break;
           
                
            case "POST":
        
                
                
                break;
       
                
            case "DELETE":
                
                break;
                
                
            case "PUT":
                
                break;
        }
        
        
    }

}
