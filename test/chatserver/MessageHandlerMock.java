package chatserver;

import serverinterfaces.IHandler;
import java.util.ArrayList;

/**
 *
 * @author kasper
 */
public class MessageHandlerMock implements IHandler {

    public ArrayList<Message> meh;

    public MessageHandlerMock() {
        meh = new ArrayList();
    }

    public void notifyClients(String message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void notifyReciever(String message, ClientHandler handler) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean registrerClients(String name, ClientHandler handler) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean unregistrerClients(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addToMessagePool(Message message) throws InterruptedException {
        meh.add(message);
    }

    @Override
    public void stopMessagePool() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void notifyUsers(String message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
