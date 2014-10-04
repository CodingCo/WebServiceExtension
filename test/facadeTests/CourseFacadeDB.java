package facadeTests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.Factory;
import facades.PersonFacade;
import java.util.List;
import javax.persistence.EntityManager;
import model.AssistentTeacher;
import model.Course;
import model.Person;
import model.RoleSchool;
import model.Student;
import webinterfaces.CourseFacadeInterface;

/**
 *
 * @author christophermortensen
 */
public class CourseFacadeDB implements CourseFacadeInterface {

    private Gson trans;
    private EntityManager em;
    private Factory fac;

    public CourseFacadeDB(Gson trans) {
        this.fac = Factory.getInstance();
        this.trans = trans;
        this.em = fac.getManager();
    }

    @Override
    public String getAllCoursesAsJson() {
        List<Person> result = em.createQuery("SELECT c FROM Course c").getResultList();
        return trans.toJson(result);
    }

    @Override
    public String getOneCourseAsJson(long id) {
        Course course = em.find(Course.class, id);
        return trans.toJson(course);
    }

    @Override
    public Course addCourseFromGson(String json) {
        Course courseFromJson = trans.fromJson(json, Course.class);
        em.getTransaction().begin();
        em.persist(courseFromJson);
        em.getTransaction().commit();
        return courseFromJson;
    }

    @Override
    public Course deleteCourse(long courseId) {
        em.getTransaction().begin();
        Course courseToRemove = em.find(Course.class, courseId);
        em.remove(courseToRemove);
        em.getTransaction().commit();
        return courseToRemove;
    }

    @Override
    public Course assignRoleSchoolToCourse(long personId, long roleId, long courseId) {
        em.getTransaction().begin();
        RoleSchool roleSchool = em.find(RoleSchool.class, roleId);
        Course targetCourse = em.find(Course.class, courseId);
        targetCourse.addRole(roleSchool);
        em.getTransaction().commit();
        return targetCourse;
    }

    @Override
    public Course unassignRoleSchoolFromCourse(long personId, long roleId, long courseId) {
        em.getTransaction().begin();
        Course targetCourse = em.find(Course.class, courseId);
        em.getTransaction().commit();
        return targetCourse;
    }

    public static void main(String[] args) {
        GsonBuilder gsonBuilder;
        Gson trans;
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(RoleSchool.class, new adapters.RoleSchoolAdapter());
        trans = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
        
        //== RoleSchool Part
        
        PersonFacade personFacade = new PersonFacade(trans);
        RoleSchool roleSchoolToAdd1 = new Student("Third");
        RoleSchool roleSchoolToAdd2 = new AssistentTeacher();
        Person personToAdd1 = new Person();
        personToAdd1.setFirstName("Peter");
        personToAdd1.setLastName("Lorensen");
        personToAdd1.setMail("mail");
        personToAdd1.setPhone("phone");
        personFacade.addPersonFromGson(trans.toJson(personToAdd1));
//        Person retrievedPerson = trans.fromJson(personFacade.getOnePersonAsJson(100000), Person.class);
        personFacade.addRoleSchool(trans.toJson(roleSchoolToAdd1), 100000);
        personFacade.addRoleSchool(trans.toJson(roleSchoolToAdd2), 100000);
        System.out.println(personFacade.getPersonsAsJSON());
        
        //== Course Part
        
        CourseFacadeDB courseFacade = new CourseFacadeDB(trans);
        Course courseToAdd1 = new Course("Begginning C++", "This course is a four-week intro to the language of c++");
        Course courseToAdd2 = new Course("Ruby on rails - Intermediate", "This course is a four-week sprint of the intermediate angles of Ruby on rails");
        courseFacade.addCourseFromGson(trans.toJson(courseToAdd1));
        courseFacade.addCourseFromGson(trans.toJson(courseToAdd2));
//        courseFacade.deleteCourse(100000);
        
        courseFacade.assignRoleSchoolToCourse(0, 100000, 100002);
        courseFacade.assignRoleSchoolToCourse(0, 100000, 100003);
        courseFacade.assignRoleSchoolToCourse(0, 100001, 100002);
        courseFacade.assignRoleSchoolToCourse(0, 100001, 100003);
        System.err.println(courseFacade.unassignRoleSchoolFromCourse(0, 100000, 100002).toString());
        
        String coursesAsJson = courseFacade.getAllCoursesAsJson();
        System.out.println("getAllCoursesAsJson: " + coursesAsJson);
        System.out.println("getOneCourseAsJson(100002): " + courseFacade.getOneCourseAsJson(100002));
        System.out.println("getOneCourseAsjson(100003): " + courseFacade.getOneCourseAsJson(100003));
    }

}
