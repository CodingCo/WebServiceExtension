package facades;

import com.google.gson.Gson;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.AssistentTeacher;
import model.Course;
import model.RoleSchool;
import model.Student;
import model.Teacher;
import webinterfaces.CourseFacadeInterface;

/**
 *
 * @author kasper
 */
public class CourseFacade implements CourseFacadeInterface {

    Gson transaction;
    EntityManager em;
    Factory fac;

    public CourseFacade(Gson transaction) {
	this.transaction = transaction;
	this.fac = Factory.getInstance();
	this.em = fac.getManager();
    }

    @Override
    public String getCourse(long id) {
	Query query = em.createQuery("SELECT c FROM Course c WHERE c.id = ?1").setParameter(1, id);
        Course co = (Course) query.getSingleResult();
        return transaction.toJson(co);
    }

    @Override
    public String getAllCourses() {
	List<Course> result = em.createQuery("SELECT c FROM Course c").getResultList();
        return transaction.toJson(result);
    }

    @Override
    public Course addCourse(String json) {
	Course co = transaction.fromJson(json, Course.class);
        em.getTransaction().begin();
        em.persist(co);
        em.getTransaction().commit();
        return co;
    }

    @Override
    public Course deleteCourse(long id) {
	em.getTransaction().begin();
        Course co = em.find(Course.class, id);
        em.remove(co);
        em.getTransaction().commit();
        return co;
    }

    @Override
    public RoleSchool addRoleSchoolToCourse(String json, long id) {
	RoleSchool role = null;
        if (json.contains("Teacher")) {
            role = transaction.fromJson(json, Teacher.class);
        }
        if (json.contains("Student")) {
            role = transaction.fromJson(json, Student.class);
        }
        if (json.contains("AssistentTeacher")) {
            role = transaction.fromJson(json, AssistentTeacher.class);
        }
	
	em.getTransaction().begin();
        Course co = em.find(Course.class, id);
        if (co != null && role != null) {
            co.addRole(role);
        }
        em.getTransaction().commit();

        return role;
    }
}
