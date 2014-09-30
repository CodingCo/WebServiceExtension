package facadeTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import model.Person;
import model.RoleSchool;
import model.Student;
import model.Teacher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ThomasHedegaard
 */
public class PersonFacadeTest {
    
    public PersonFacadeTest() {
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
     * Test of getPersonsAsJSON method, of class PersonFacadeMock.
     */
    @Test
    public void testGetPersonsAsJSON() {
        System.out.println("getPersonsAsJSON");
        PersonFacadeMock instance = new PersonFacadeMock();
        String expResult = "";
        String result = instance.getPersonsAsJSON();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOnePersonAsJson method, of class PersonFacadeMock.
     */
    @Test
    public void testGetOnePersonAsJson() {
        System.out.println("getOnePersonAsJson");
        long id = 0L;
        PersonFacadeMock instance = new PersonFacadeMock();
        String expResult = "";
        String result = instance.getOnePersonAsJson(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPersonFromGson method, of class PersonFacadeMock.
     */
    @Test
    public void testAddPersonFromGson() {
        System.out.println("addPersonFromGson");
        String json = "";
        PersonFacadeMock instance = new PersonFacadeMock();
        Person expResult = null;
        Person result = instance.addPersonFromGson(json);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addRoleSchool method, of class PersonFacadeMock.
     */
    @Test
    public void testAddRoleSchool() {
        Gson trans = new Gson();
        PersonFacadeMock instance = new PersonFacadeMock();
        System.out.println("addRoleSchool");
        long id = 100000L;                                                           //== Expecting we are testing on an empty table/database
        String degree = "D2";
        RoleSchool roleToTransform = new Teacher(degree);
        String json = trans.toJson(roleToTransform);
        
        RoleSchool expResult = roleToTransform;
        RoleSchool result = instance.addRoleSchool(json, id);
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of delete method, of class PersonFacadeMock.
     */
    @Test
    public void testDelete() {
        PersonFacadeMock instance = new PersonFacadeMock();
        System.out.println("delete");
        long id = 1000000L;                                                           //== Expecting we are testing on an empty table/database
        Person personToDelete = new Person("Alu", "Albert", "aluminium@metal.com", "12345678", new Student());
        Person expResult = personToDelete;
        Person result = instance.delete(id);
        assertEquals(expResult.toString(), result.toString());
    }
    
}
