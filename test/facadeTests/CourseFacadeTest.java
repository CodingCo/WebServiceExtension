/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facadeTests;

import model.Course;
import model.RoleSchool;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import webinterfaces.CourseFacadeInterface;

/**
 *
 * @author christophermortensen
 */
public class CourseFacadeTest {
    
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllCoursesAsJson method, of class CourseFacadeInterface.
     */
    @Test
    public void testGetAllCoursesAsJson() {
        System.out.println("getAllCoursesAsJson");
        CourseFacadeInterface instance = new CourseFacadeInterfaceImpl();
        String expResult = "";
        String result = instance.getAllCoursesAsJson();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOneCourseAsJson method, of class CourseFacadeInterface.
     */
    @Test
    public void testGetOneCourseAsJson() {
        System.out.println("getOneCourseAsJson");
        long id = 0L;
        CourseFacadeInterface instance = new CourseFacadeInterfaceImpl();
        String expResult = "";
        String result = instance.getOneCourseAsJson(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCourseFromGson method, of class CourseFacadeInterface.
     */
    @Test
    public void testAddCourseFromGson() {
        System.out.println("addCourseFromGson");
        String json = "";
        CourseFacadeInterface instance = new CourseFacadeInterfaceImpl();
        Course expResult = null;
        Course result = instance.addCourseFromGson(json);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCourse method, of class CourseFacadeInterface.
     */
    @Test
    public void testDeleteCourse() {
        System.out.println("deleteCourse");
        long personId = 0L;
        String roleName = "";
        CourseFacadeInterface instance = new CourseFacadeInterfaceImpl();
        Course expResult = null;
        Course result = instance.deleteCourse(personId, roleName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of assignCourseToRoleSchool method, of class CourseFacadeInterface.
     */
    @Test
    public void testAssignCourseToRoleSchool() {
        System.out.println("assignCourseToRoleSchool");
        String json = "";
        long personId = 0L;
        String roleName = "";
        CourseFacadeInterface instance = new CourseFacadeInterfaceImpl();
        RoleSchool expResult = null;
        RoleSchool result = instance.assignCourseToRoleSchool(json, personId, roleName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCourseFromRoleSchool method, of class CourseFacadeInterface.
     */
    @Test
    public void testRemoveCourseFromRoleSchool() {
        System.out.println("removeCourseFromRoleSchool");
        long personId = 0L;
        String roleName = "";
        CourseFacadeInterface instance = new CourseFacadeInterfaceImpl();
        RoleSchool expResult = null;
        RoleSchool result = instance.removeCourseFromRoleSchool(personId, roleName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class CourseFacadeInterfaceImpl implements CourseFacadeInterface {

        public String getAllCoursesAsJson() {
            return "";
        }

        public String getOneCourseAsJson(long id) {
            return "";
        }

        public Course addCourseFromGson(String json) {
            return null;
        }

        public Course deleteCourse(long personId, String roleName) {
            return null;
        }

        public RoleSchool assignCourseToRoleSchool(String json, long personId, String roleName) {
            return null;
        }

        public RoleSchool removeCourseFromRoleSchool(long personId, String roleName) {
            return null;
        }
    }
    
}
