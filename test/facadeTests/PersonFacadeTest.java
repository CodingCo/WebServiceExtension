package facadeTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import webinterfaces.FacadeInterface;

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
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(RoleSchool.class, new RoleSchoolAdapter());
        Gson trans = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();

        System.out.println("getPersonsAsJSON");
        PersonFacadeMock pf = new PersonFacadeMock(trans);

        Student role = new Student("Third");
        Person p1 = new Person("John", "McLaren", "myMail@hotmail.com", "57895879", role);
        Person p2 = new Person("Dane", "McLaren", "myMail@hotmail.com", "57895879", role);
        Person p3 = new Person("OhoMan", "McLaren", "myMail@hotmail.com", "57895879", role);

        pf.addPersonFromGson(trans.toJson(p1));
        pf.addPersonFromGson(trans.toJson(p2));
        pf.addPersonFromGson(trans.toJson(p3));

        String expPerson = pf.getOnePersonAsJson(100001);

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
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(RoleSchool.class, new RoleSchoolAdapter());
        Gson trans = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
        System.out.println("getPersonsAsJSON");
        PersonFacadeMock pf = new PersonFacadeMock(trans);
        Person p1 = new Person("John", "McLaren", "myMail@hotmail.com", "57895879", new Student("Third"));
        p1.setId(100001l);
        String p1AsJson = trans.toJson(p1, Person.class);
        Person fetchedPerson = pf.addPersonFromGson(p1AsJson);

        long p1ID = fetchedPerson.getId();
        String p1Response = pf.getOnePersonAsJson(p1ID);
        System.err.println("P1 ASJSON: " + p1AsJson);
        System.err.println("P1 RESPONSE: " + p1Response);
        assertEquals(p1AsJson, p1Response);
    }

    /**
     * Test of addPersonFromGson method, of class PersonFacadeMock.
     */
    @Test
    public void testAddPersonFromGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(RoleSchool.class, new RoleSchoolAdapter());
        Gson trans = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
        FacadeInterface pf = new PersonFacadeMock(trans);

        Person person = new Person("Inger", "Hammerik", "myMail@hotmail.com", "56789589", new Student("Third"));
        pf.addPersonFromGson(trans.toJson(person));
        long id = 100001;
        Person expResult = trans.fromJson(pf.getOnePersonAsJson(id), Person.class);
        assertEquals(id, (long) expResult.getId());
    }

    /**
     * Test of addRoleSchool method, of class PersonFacadeMock.
     */
    @Test
    public void testAddRoleSchool() {
        Gson trans = new Gson();
        PersonFacadeMock instance = new PersonFacadeMock(trans);
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
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(RoleSchool.class, new RoleSchoolAdapter());
        Gson trans = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
        PersonFacadeMock instance = new PersonFacadeMock(trans);
        System.out.println("delete");
        long id = 100001L;                                                           //== Expecting we are testing on an empty table/database
        Person personToDelete = new Person("Alu", "Albert", "aluminium@metal.com", "12345678", new Student("Third"));
        
        Person expResult = instance.addPersonFromGson(trans.toJson(personToDelete));
        Person result = instance.delete(id);
        assertEquals(expResult.toString(), result.toString());
    }

}
