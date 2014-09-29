package chatserver;

import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kasper
 */
public class ClientHandlerTest {
    
    private ClientHandler ch;
    private ConnectionMock cm;
    private MessageHandlerMock mhm;
    
    public ClientHandlerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
	ch = new ClientHandler(cm = new ConnectionMock(), mhm = new MessageHandlerMock());
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of run method, of class ClientHandler.
     */
//    @Test
//    public void testRun() {
//	System.out.println("run");
//	ExecutorService ex = Executors.newSingleThreadExecutor();
//	ex.execute(ch);
//	try {
//	    Thread.sleep(10);
//	} catch (InterruptedException ex1) {
//	    Logger.getLogger(ClientHandlerTest.class.getName()).log(Level.SEVERE, null, ex1);
//	}
//	ex.shutdown();
//	
//	Message ms = mhm.meh.get(mhm.meh.size() - 1);
//	try {
//	    assertEquals(ms.getMessage(), cm.read());
//	} catch (IOException ex1) {
//	    Logger.getLogger(ClientHandlerTest.class.getName()).log(Level.SEVERE, null, ex1);
//	}
//	
//    }

    /**
     * Test of sendMessage method, of class ClientHandler.
     */
    @Test
    public void testSendMessage() {
	System.out.println("sendMessage");
	ch.sendMessage("Send Message Test");
	try {
	    assertEquals(cm.read(), "Send Message Test");
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * Test of closeConnection method, of class ClientHandler.
     */
    @Test
    public void testCloseConnection() throws Exception {
	System.out.println("closeConnection");
	ch.closeConnection();
	assertTrue(!cm.open);
    }
    
}
