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

    FacadeInterface instance;
    GsonBuilder gsonBuilder;
    Gson trans;

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
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(RoleSchool.class, new RoleSchoolAdapter());
        trans = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();

        // Only one should be visible when running test
        instance = new PersonFacadeDB(trans);
//        instance = new PersonFacadeMock(trans);
    }

    @After
    public void tearDown() {

    }

    public void before() {
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(RoleSchool.class, new RoleSchoolAdapter());
        trans = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();

        // Only one should be visible when running test
        instance = new PersonFacadeDB(trans);
//        instance = new PersonFacadeMock(trans);
    }

    /**
     * Test of getPersonsAsJSON method, of class PersonFacadeMock.
     */
    @Test
    public void testGetPersonsAsJSON() {
        System.out.println("getPersonsAsJSON");

        Student role = new Student("Third");
        Person p1 = new Person("John", "McLaren", "myMail@hotmail.com", "57895879", role);
        Person p2 = new Person("Dane", "McLaren", "myMail@hotmail.com", "57895879", role);
        Person p3 = new Person("OhoMan", "McLaren", "myMail@hotmail.com", "57895879", role);

        instance.addPersonFromGson(trans.toJson(p1));
        instance.addPersonFromGson(trans.toJson(p2));
        instance.addPersonFromGson(trans.toJson(p3));

        String expPerson = instance.getOnePersonAsJson(100001);
        String responseFromServer = instance.getPersonsAsJSON();

        assertTrue(responseFromServer.contains(expPerson));
    }

    /**
     * Test of getOnePersonAsJson method, of class PersonFacadeMock.
     */
    @Test
    public void testGetOnePersonAsJson() {
        System.out.println("getOnePersonsAsJSON");
        Person p1 = new Person("John", "McLaren", "myMail@hotmail.com", "57895879", new Student("Third"));
        String p1AsJson = trans.toJson(p1, Person.class);
        instance.addPersonFromGson(p1AsJson);
        Person fetchedPerson = trans.fromJson(instance.getOnePersonAsJson(100000), Person.class);

        if (fetchedPerson.getId() == 100000 && p1.getFirstName().equals(fetchedPerson.getFirstName())
                && p1.getPhone().equals(fetchedPerson.getPhone())) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }

    }

    /**
     * Test of addPersonFromGson method, of class PersonFacadeMock.
     */
    @Test
    public void testAddPersonFromGson() {
        System.out.println("AddPersonFromGson");
        Person person = new Person("Inger", "Hammerik", "myMail@hotmail.com", "56789589", new Student("Third"));
        instance.addPersonFromGson(trans.toJson(person));
        long id = 100000;
        Person expResult = trans.fromJson(instance.getOnePersonAsJson(id), Person.class);
        assertEquals(id, (long) expResult.getId());
    }

    /**
     * Test of addRoleSchool method, of class PersonFacadeMock.
     */
    @Test
    public void testAddRoleSchool() {
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
        System.out.println("delete");
        long id = 100000L;                                                           //== Expecting we are testing on an empty table/database
        Person personToAdd = new Person("Alu", "Albert", "aluminium@metal.com", "12345678", new Student("Third"));

        Person deletedPerson = instance.addPersonFromGson(trans.toJson(personToAdd));
        Person resultPerson = instance.delete(id);
        String expResultJson = trans.toJson(deletedPerson);
        String resultJson = trans.toJson(resultPerson);
//        System.out.println("EXPRESULTJSON " + expResultJson);
//        System.out.println("RESULTJSON " + resultJson);
        assertEquals(expResultJson, resultJson);
    }

}
