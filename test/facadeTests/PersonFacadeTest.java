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
        Gson trans = new Gson();
        System.out.println("getPersonsAsJSON");
        PersonFacadeMock pf = new PersonFacadeMock();

        Person p1 = new Person("John", "McLaren", "myMail@hotmail.com", "57895879", new Student());
        Person p2 = new Person("John", "McLaren", "myMail@hotmail.com", "57895879", new Student());
        Person p3 = new Person("John", "McLaren", "myMail@hotmail.com", "57895879", new Student());
        String expPerson = trans.toJson(p3);

        pf.addPersonFromGson(trans.toJson(p1));
        pf.addPersonFromGson(trans.toJson(p2));
        pf.addPersonFromGson(trans.toJson(p3));

        String responseFromServer = pf.getPersonsAsJSON();

        if (responseFromServer.contains(expPerson)) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }

    }

    /**
     * Test of getOnePersonAsJson method, of class PersonFacadeMock.
     */
    @Test
    public void testGetOnePersonAsJson() {
        Gson trans = new Gson();
        System.out.println("getPersonsAsJSON");
        PersonFacadeMock pf = new PersonFacadeMock();
        Person p1 = new Person("John", "McLaren", "myMail@hotmail.com", "57895879", new Student());
        String p1AsJson = trans.toJson(p1, Person.class);
        pf.addPersonFromGson(p1AsJson);
        long p1ID = p1.getId();
        String p1Response = pf.getOnePersonAsJson(p1ID);

        assertEquals(p1AsJson, p1Response);
    }

    /**
     * Test of addPersonFromGson method, of class PersonFacadeMock.
     */
    @Test
    public void testAddPersonFromGson() {
        System.out.println("addPersonFromGson");
        Gson trans = new Gson();
        PersonFacadeMock pf = new PersonFacadeMock();
        
        Person person = new Person("Inger", "Hammerik", "myMail@hotmail.com", "56789589", new Student());
        pf.addPersonFromGson(trans.toJson(person));
        long id = person.getId();
        Person expResult = trans.fromJson(pf.getOnePersonAsJson(id), Person.class);
        assertEquals(id, (long)expResult.getId());
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
