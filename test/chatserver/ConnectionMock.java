package chatserver;

import serverinterfaces.IConnection;
import java.io.IOException;
import utility.CloseSuccesException;

/**
 *
 * @author kasper
 */
public class ConnectionMock implements IConnection {

    public String text = "Hello";
    public boolean open = false;
    
    @Override
    public void write(String input) {
	text = input;
    }

    @Override
    public String read() throws IOException {
	return text;
    }

    @Override
    public void close() throws CloseSuccesException {
	open = false;
    }

    @Override
    public void open() throws IOException {
	open = true;
    }
    
}
