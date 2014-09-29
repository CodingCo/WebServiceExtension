package serverinterfaces;

import java.io.IOException;
import utility.CloseSuccesException;

/**
 *
 * @author simon
 */
public interface IConnection {

    public void write(String input);

    public String read() throws IOException;

    public void close() throws CloseSuccesException;

    public void open() throws IOException;

}
