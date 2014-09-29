package chatserver;

import serverinterfaces.IConnection;
import utility.CloseSuccesException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author simon
 */
public class Connection implements IConnection {

    Socket socket;
    BufferedReader input;
    PrintWriter output;

    public Connection(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void write(String input) {
        output.println(input);
    }

    @Override
    public String read() throws IOException {
        return input.readLine();
    }

    @Override
    public void close() throws CloseSuccesException {
        try {
            socket.close();
            input.close();
            output.close();
        } catch (IOException e) {
            throw new CloseSuccesException("Connection terminated");
        }
    }

    @Override
    public void open() throws IOException {
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
    }

}
