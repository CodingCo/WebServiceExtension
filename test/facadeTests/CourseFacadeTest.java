package facadeTests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.CourseFacade;
import model.Course;
import model.RoleSchool;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import webinterfaces.CourseInterface;

/**
 *
 * @author kasper
 */
public class CourseFacadeTest {
    
    CourseInterface instance;
    GsonBuilder gson;
    Gson transaction;
    
    public CourseFacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
	gson = new GsonBuilder();
        transaction = gson.excludeFieldsWithoutExposeAnnotation().create();
        instance = new CourseFacade(transaction);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCourse method, of class CourseFacade.
     */
    @Test
    public void testGetCourse() {
	System.out.println("getOnePersonsAsJSON");
        Course c1 = new Course("A name", "A description");
        String c1AsJson = transaction.toJson(c1, Course.class);
        instance.addCourse(c1AsJson);
        Course fetchedCourse = transaction.fromJson(instance.getCourse(100000), Course.class);

        if (fetchedCourse.getId() == 100000 && c1.getName().equals(fetchedCourse.getName())
                && c1.getDescription().equals(fetchedCourse.getDescription())) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    /**
     * Test of getAllCourses method, of class CourseFacade.
     */
    @Test
    public void testGetAllCourses() {
	System.out.println("getAllCourses");
	
	Course c1 = new Course("C++", "Programming language");
	Course c2 = new Course("Business", "Yada yada");
	Course c3 = new Course("Janitorial Excersise", "More yada");
	

        instance.addCourse(transaction.toJson(c1));
        instance.addCourse(transaction.toJson(c2));
        instance.addCourse(transaction.toJson(c3));

        String expCourse = instance.getCourse(100001);
        String responseFromServer = instance.getAllCourses();

        assertTrue(responseFromServer.contains(expCourse));
    }

    /**
     * Test of addCourse method, of class CourseFacade.
     */
    @Test
    public void testAddCourse() {
	System.out.println("addCourse");
	String json = "";
	CourseFacade instance = null;
	Course expResult = null;
	Course result = instance.addCourse(json);
	assertEquals(expResult, result);
	fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCourse method, of class CourseFacade.
     */
    @Test
    public void testDeleteCourse() {
	System.out.println("deleteCourse");
	long id = 0L;
	CourseFacade instance = null;
	Course expResult = null;
	Course result = instance.deleteCourse(id);
	assertEquals(expResult, result);
	fail("The test case is a prototype.");
    }

    /**
     * Test of addRoleSchoolToCourse method, of class CourseFacade.
     */
    @Test
    public void testAddRoleSchoolToCourse() {
	System.out.println("addRoleSchoolToCourse");
	String json = "";
	long id = 0L;
	CourseFacade instance = null;
	RoleSchool expResult = null;
	RoleSchool result = instance.addRoleSchoolToCourse(json, id);
	assertEquals(expResult, result);
	fail("The test case is a prototype.");
    }
    
}
