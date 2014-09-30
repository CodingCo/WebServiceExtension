package chatserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import webserver.WebServer;

/**
 *
 * @author simon
 */
public class ServerExecutor {

    static ChatServer server;
    static BufferedReader input;
    static WebServer webServer;

    public static void main(String[] args) {
        try {
            ArrayBlockingQueue messageQue = new ArrayBlockingQueue(100);
            MessageHandler msgHandler = new MessageHandler(messageQue, new HashMap());
            webServer = new WebServer(msgHandler);
            webServer.startServer();
            server = new ChatServer(msgHandler);
            server.startServer();
            serverCommands();
        } catch (IOException ex) {
            Logger.getLogger(ServerExecutor.class.getName()).log(Level.SEVERE, ex.toString());
        }
    }

    public static void serverCommands() {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        try {
            String command = r.readLine();
            do {
                switchCommands(command);
            } while (!(command = r.readLine()).contains("killall"));
            r.close();
        } catch (IOException e) {

        }
        System.exit(0);
        System.out.println("server shutdown");
    }

    private static void switchCommands(String command) throws IOException {

        switch (command) {
            case "stop server":
                server.stopServer();
                break;
            case "start server":
                server.startServer();
                break;
            case "status":
                System.out.println("running");
                break;
            case "stop web":
                webServer.closeHttpServer();
                break;
            case "start web":
                webServer.startServer();
                break;
            default:
                if (!command.equals("killall")) {
                    System.out.println("unknow command");
                }
        }
    }
}
