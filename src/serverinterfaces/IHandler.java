package serverinterfaces;

import chatserver.Message;

/**
 *
 * @author Grønborg
 */
public interface IHandler {

    public void stopMessagePool();
    public void addToMessagePool(Message message) throws InterruptedException;
    public String getUsers();
    public void notifyUsers(String message);
}
