package facadeTests;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.BusinessAcademy;
import model.Person;
import model.Student;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import webinterfaces.AcademyFacadeInterface;

/**
 *
 * @author simon
 */
public class AcademyFacadeTest {

    static AcademyFacadeInterface amock;

    public AcademyFacadeTest() {

    }

    @BeforeClass
    public static void setUpClass() {
        // true to test againt mock -- false to test up againt test DB
        boolean mockOrNot = true;
        if (mockOrNot) {
            amock = new AcademyFacadeMock();
        } else {
            amock = new AcademyFacadeDB();
        }
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

    /**
     * Test of getAcademysAsJson method, of class AcademyFacadeInterface.
     */
    @Test
    public void testGetAcademysAsJson() {
        System.out.println("getAcademysAsJson");
        String academys = amock.getAcademysAsJson();
        System.out.println(academys);
        if (academys.contains("CPH") && academys.contains("Fort Bregan")) {
            assertTrue(true);
        }
        assertFalse(false);
    }

    /**
     * Test of addAcademyFromJson method, of class AcademyFacadeInterface.
     */
    @Test
    public void testAddAcademyFromJson() {
        System.out.println("addAcademyFromJson");
        String academy = "Lollo";
        String json = "{'name' : '" + academy + "'}";
        amock.addAcademyFromJson(json);
        String result = amock.getAcademysAsJson();
        assertTrue(result.contains(academy));
    }

    /**
     * Test of deleteAcademy method, of class AcademyFacadeInterface.
     */
    @Test
    public void testDeleteAcademy() {
        System.out.println("deleteAcademy");
        String nameId = "CPH";
        int ispresent = amock.getAcademysAsJson().contains(nameId) ? 1 : 0;
        BusinessAcademy a = amock.deleteAcademy(nameId);
        if (ispresent == 1 && a.getName().equals(nameId)) {
            assertTrue(true);
        }
        assertFalse(false);
    }

}
