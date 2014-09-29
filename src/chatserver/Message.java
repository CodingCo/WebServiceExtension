package chatserver;

import serverinterfaces.IClient;

/**
 *
 * @author simon
 */
public class Message {

    private final IClient clientHandler;
    private final String message;

    private Message(IClient ch, String message) {
        this.clientHandler = ch;
        this.message = message;
    }

    public static Message generateMessage(IClient c, String m) {
        return new Message(c, m);
    }

    public IClient getIClient() {
        return clientHandler;
    }

    public String getMessage() {
        return message;
    }

}
