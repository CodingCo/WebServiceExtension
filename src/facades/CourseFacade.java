package facades;

import com.google.gson.Gson;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.AssistentTeacher;
import model.Course;
import model.Person;
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
    public String getAllCoursesAsJson() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getOneCourseAsJson(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Course addCourseFromGson(String json) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Course deleteCourse(long personId, String roleName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person assignCourseToRoleSchool(String json, long personId, String roleName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person removeCourseFromRoleSchool(long personId, String roleName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    


}
