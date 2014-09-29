package chatserver;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author simon
 */
public class MessageHandlerTest {

    MHRunThroughMock mh;
    Message message;

    public MessageHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ArrayBlockingQueue messageQue = new ArrayBlockingQueue(100);
        mh = new MHRunThroughMock(messageQue, new HashMap());
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of registrerClients method, of class MessageHandler.
     */
    @Test
    public void testRegistrerClients() {
        System.out.println("registrerClients");
        String name = "Simon";
        ClientHandler h = new ClientHandler(null, mh);
        boolean expResult = true;
        boolean result = mh.registrerClients(name, h);
        assertEquals(expResult, result);
    }

    /**
     * Test of unregistrerClients method, of class MessageHandler.
     */
    @Test
    public void testUnregistrerClients() {
        System.out.println("unregistrerClients");
        String name = "Simon";
        ClientHandler h = new ClientHandler(null, mh);
        boolean registered = mh.registrerClients(name, h);
        boolean expResult = true;
        boolean result = registered & mh.unregistrerClients(name);
        assertEquals(expResult, result);
    }


    /**
     * Test of getOnlineClientNames method, of class MessageHandler.
     */
    @Test
    public void testGetOnlineClientNames() {
        System.out.println("getOnlineClientNames");
        MessageHandler instance = null;
        String expResult = "";
        assertEquals(expResult, "");
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class MessageHandler.
     */
}
