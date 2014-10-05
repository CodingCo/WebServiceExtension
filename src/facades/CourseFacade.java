package facades;

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
public class CourseFacade implements CourseFacadeInterface {

    private Gson trans;
    private EntityManager em;
    private Factory fac;

    public CourseFacade(Gson trans) {
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
    public Course assignRoleSchoolToCourse(long roleId, long courseId) {
        em.getTransaction().begin();
        RoleSchool roleSchool = em.find(RoleSchool.class, roleId);
        Course targetCourse = em.find(Course.class, courseId);
        targetCourse.addRole(roleSchool);
        em.getTransaction().commit();
        return targetCourse;
    }

    @Override
    public Course unassignRoleSchoolFromCourse(long roleId, long courseId) {
        em.getTransaction().begin();
        Course targetCourse = em.find(Course.class, courseId);
        targetCourse.removeRole(roleId);
        em.getTransaction().commit();
        return targetCourse;
    }

}
