package chatserver;

import serverinterfaces.IHandler;
import serverinterfaces.IClient;
import serverinterfaces.IConnection;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.Utility;

/**
 *
 * @author Robert
 */
public class ClientHandler implements Runnable, IClient {

    private final IConnection connection;
    private final IHandler messageHandler;
    private static final Logger logger = Logger.getLogger(ClientHandler.class.getName());
    private String name;
    
    
    public ClientHandler(IConnection connection, IHandler messageHandler) {
        Utility.setLogFile(logger.getName());
        this.connection = connection;
        this.messageHandler = messageHandler;
    }

    @Override
    public void run() {
        try {
            connection.open();
            String message;

            while ((message = connection.read()) != null) {
                messageHandler.addToMessagePool(Message.generateMessage(this, message));
            }

        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.INFO, ex.toString());
        } finally {
            secureExit();
        }
    }

    public void secureExit() {
        try {
            messageHandler.addToMessagePool(Message.generateMessage(this, Protocol.CLOSE));
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, ex.toString());
        }
    }

    @Override
    public void sendMessage(String message) {
        this.connection.write(message);
    }

    @Override
    public void closeConnection() {
        connection.close();
        Logger.getLogger(ChatServer.class.getName()).log(Level.INFO, ("disconnected from server"));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
