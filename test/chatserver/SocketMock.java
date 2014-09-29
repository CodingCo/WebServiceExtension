package chatserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author simon
 */
public class SocketMock extends Socket {

    public boolean closed = false;
    public int failValue = 0;

    
    public SocketMock() {
    }

    /**
     *
     * @param failValue 0 = methods return legit values 1 = methods return null
     * values - only streams everyting else throws exceptions
     */
    
    
    public void setfailValue(int value) {
        this.failValue = value;
    }

    @Override
    public void close() throws IOException {
        switch (failValue) {
            case 0:
                closed = true;
                break;
            default:
                throw new IOException();

        }
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        switch (failValue) {
            case 0:
                
                return new OutputStream() {
                    @Override
                    public void write(int b) throws IOException {

                    }
                };
            case 1:
                return null;
            default:
                throw new IOException();

        }
    }

    @Override
    public InputStream getInputStream() throws IOException {
        switch (failValue) {
            case 0:
                return new InputStream() {
                    @Override
                    public int read() throws IOException {
                        return 1;
                    }
                };
            case 1:
                return null;
            default:
                throw new IOException();
        }
    }

}
