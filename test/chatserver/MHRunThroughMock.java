package chatserver;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import serverinterfaces.IClient;

/**
 *
 * @author simon
 */
public class MHRunThroughMock extends MessageHandler {

    public boolean client = false;
    public boolean all = false;

    public MHRunThroughMock(ArrayBlockingQueue<Message> messages, HashMap<String, IClient> users) {
        super(messages, users);
    }

    public void notifyClients(String message) {
        client = true;
    }

    public void notifyReciever(String message, ClientHandler handler) {
        all = true;
    }

}
