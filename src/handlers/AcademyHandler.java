package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import facades.AcademyFacade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import model.BusinessAcademy;
import webinterfaces.AcademyFacadeInterface;

/**
 *
 * @author simon
 */
public class AcademyHandler implements HttpHandler {

    ServerResponse response = new ServerResponse();
    AcademyFacadeInterface facade = new AcademyFacade();

    @Override
    public void handle(HttpExchange he) throws IOException {

        String method = he.getRequestMethod();
        Headers h = he.getResponseHeaders();

        switch (method) {
            case "GET":

                String json = facade.getAcademysAsJson();
                h.add("Content-Type", "application/json");
                h.add("charset", "UTF-8");
                response.sendMessage(he, 200, json);
                break;

            case "POST":
                InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String jsonInput = br.readLine();

                BusinessAcademy academy = facade.addAcademyFromJson(jsonInput);

                h.add("Content-Type", "application/json");
                h.add("charset", "UTF-8");
                response.sendMessage(he, 200, new Gson().toJson(academy));
                break;
        }
    }

}
