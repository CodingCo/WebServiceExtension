package chatserver;

import serverinterfaces.IHandler;
import serverinterfaces.IClient;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robert
 */
public class MessageHandler implements Runnable, IHandler {

    private final ArrayBlockingQueue<Message> messages;
    private final HashMap<String, IClient> users;
    private boolean running = true;

    public MessageHandler(ArrayBlockingQueue<Message> messages, HashMap<String, IClient> users) {
        this.messages = messages;
        this.users = users;
    }

    public boolean registrerClients(String name, IClient client) {
        users.put(name, client);
        return users.containsKey(name);
    }

    public boolean unregistrerClients(String name) {
        users.remove(name);
        return users.containsKey(name);
    }

    public void notifyReciever(String message, IClient client) {
        client.sendMessage(message);
    }

    @Override
    public void notifyUsers(String message) {
        users.entrySet().stream().forEach((entry) -> {
            entry.getValue().sendMessage(message);
        });
    }

    @Override
    public synchronized void addToMessagePool(Message message) throws InterruptedException {
        messages.put(message);
    }

    @Override
    public void stopMessagePool() {
        running = false;
        messages.clear();
    }

    @Override
    public synchronized String getUsers() {
        if (!users.isEmpty()) {
            StringBuilder b = new StringBuilder();
            users.entrySet().stream().forEach((entry) -> {
                b.append(entry.getKey());
                b.append(",");
            });
            return b.substring(0, b.length() - 1);
        }
        return "";
    }

    @Override
    public void run() {
        try {
            while (running) {
                Message m = messages.take();
                String message = m.getMessage();
                int command = Protocol.CheckMessage.registrerProtocolType(message);
                switch (command) {
                    case 1:
                        connect(m);
                        break;
                    case 2:
                        send(m);
                        break;
                    case 3:
                        close(m);
                        break;
                    default:
                        Logger.getLogger(ClientHandler.class.getName()).log(Level.INFO, ("invalid message: " + message));
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.INFO, ex.toString());
        }
    }

    public void connect(Message m) {
        String message = m.getMessage();
        if (message.startsWith(Protocol.CONNECT)) {
            message = message.replace(Protocol.CONNECT, "");
            if (!message.equals("")) {
                if (!users.containsValue(m.getIClient())) {
                    m.getIClient().setName(message);
                    registrerClients(message, m.getIClient());
                    notifyUsers(Protocol.ONLINE + getUsers());
                } else {
                    notifyReciever(Protocol.SERVER_ONLINE_RESPONSE, m.getIClient());
                    System.out.println("shiiit");
                }
            }
        }
    }

    public void send(Message m) {
        String message = m.getMessage();
        if (message.startsWith(Protocol.SEND)) {
            message = message.replace(Protocol.SEND, "");
            String[] userAndMessage = message.split("#", 2);
            if (userAndMessage[0].equals("*")) {
                notifyUsers(Protocol.MESSAGE + m.getIClient().getName() + "#" + userAndMessage[1]);
            } else {
                for (String user : userAndMessage[0].split(",")) {
                    if (users.containsKey(user)) {
                        notifyReciever(Protocol.MESSAGE + m.getIClient().getName() + "#" + userAndMessage[1], users.get(user));
                    } else {
                        m.getIClient().sendMessage(Protocol.SERVER_NO_SUCH_USER + user);
                    }
                }
            }
        }
    }

    public void close(Message m) {
        notifyReciever(Protocol.CLOSE, m.getIClient());
        unregistrerClients(m.getIClient().getName());
        notifyUsers(Protocol.ONLINE + getUsers());
    }

}
