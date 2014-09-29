package chatserver;

import serverinterfaces.IHandler;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.Utility;

/**
 *
 * @author simon
 */
public class ChatServer implements Runnable {

    private ServerSocket serverSocket;
    private MessageHandler messageHandler;
    private final String ipAddress;
    private final int port;
    private Properties property = Utility.initProperties("serverproperties.txt");
    private boolean running = true;

    public ChatServer(IHandler messageHandler) {
        this.messageHandler = (MessageHandler) messageHandler;
        this.ipAddress = property.getProperty("ipaddress");
        this.port = Integer.parseInt(property.getProperty("port"));
        Utility.setLogFile("");
    }

    @Override
    public void run() {
        try {
            openConnection();
            while (running) {
                Socket socket;
                System.out.println("waiting for connection");
                socket = serverSocket.accept();
                ClientHandler h = new ClientHandler(new Connection(socket), messageHandler);
                Thread handlerThread = new Thread(h);
                handlerThread.start();
            }

        } catch (IOException e) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.INFO, "disconnected");
        }
        System.out.println("server closed");
    }

    public void openConnection() throws IOException {
        this.serverSocket = new ServerSocket();
        this.serverSocket.bind(new InetSocketAddress(ipAddress, port));
        System.out.println("Connection opened on: " + ipAddress + " port: " + port);

    }

    public void startServer() {
        Thread serverThread = new Thread(this);
        Thread handlerThread = new Thread(messageHandler);
        handlerThread.start();
        serverThread.start();
        System.out.println("message " + handlerThread.getName());
        System.out.println("server " + serverThread.getName());
    }

    public void stopServer() {
        try {
            messageHandler.notifyUsers(Protocol.CLOSE);
            serverSocket.close();
        } catch (IOException ex) {
             Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, (ex.toString() + " Could not disconnect"));
        }

    }

}
