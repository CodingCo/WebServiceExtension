package facadeTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import model.Person;
import model.RoleSchool;
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
        System.out.println("addRoleSchool");
        String json = "";
        long id = 0L;
        PersonFacadeMock instance = new PersonFacadeMock();
        RoleSchool expResult = null;
        RoleSchool result = instance.addRoleSchool(json, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class PersonFacadeMock.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        long id = 0L;
        PersonFacadeMock instance = new PersonFacadeMock();
        Person expResult = null;
        Person result = instance.delete(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
