package facadeTests;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import model.Course;
import model.Person;
import model.RoleSchool;
import webinterfaces.CourseFacadeInterface;

/**
 *
 * @author christophermortensen
 */
public class CourseFacadeMock implements CourseFacadeInterface {

    private List<Person> courses;
    private Gson trans;

    public CourseFacadeMock(Gson trans) {
        this.courses = new ArrayList<>();
        this.trans = trans;
    }

    @Override
    public String getAllCoursesAsJson() {
        return trans.toJson(courses);
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
