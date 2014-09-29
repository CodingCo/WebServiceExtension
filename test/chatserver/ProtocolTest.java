package chatserver;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Grønborg
 */
public class ProtocolTest {

    public ProtocolTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }


    
    @Test
    public void testCommand() {
        System.out.println("command test");
        String invalid = "";
        String connect = Protocol.CONNECT;
        String send = Protocol.SEND;
        String close = Protocol.CLOSE;
        int one = Protocol.CheckMessage.registrerProtocolType(invalid);
        int two = Protocol.CheckMessage.registrerProtocolType(connect);
        int three = Protocol.CheckMessage.registrerProtocolType(send);
        int four = Protocol.CheckMessage.registrerProtocolType(close);
        assertEquals(6, (one+two+three+four));
    }
    
    
    
    @Test
    public void send(){
        System.out.println("test messages");
        Message m = Message.generateMessage(null, "SEND#lars#hej");
        MessageHandler h = new MessageHandler(null, null);
        h.send(m);
    
    }
    
    
    
    
}
