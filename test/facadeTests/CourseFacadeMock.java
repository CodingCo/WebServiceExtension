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

    private List<Course> courses;                               // two tables, because we have two independent tables
    private List<Person> persons;
    private Gson trans;

    public CourseFacadeMock(Gson trans) {
        this.courses = new ArrayList<>();
        this.persons = new ArrayList<>();
        this.trans = trans;
    }

    @Override
    public String getAllCoursesAsJson() {
        return trans.toJson(courses);
    }

    @Override
    public String getOneCourseAsJson(long id) {
        for(Course course : courses){
            if(course.getId() == id){
                return trans.toJson(course);
            }
        }
        return null;
    }

    @Override
    public Course addCourseFromGson(String json) {
        Course courseFromJson = trans.fromJson(json, Course.class); 
        courseFromJson.setId(99999l + (courses.size()+1));
        courses.add(courseFromJson);
        return courseFromJson;
    }

    @Override
    public Course deleteCourse(long personId, String roleName) {
        for(Person person : persons){
            List<RoleSchool> roles = (ArrayList<RoleSchool>)person.getRoles();
            for(RoleSchool role : roles){
                if(role.getRoleName().equalsIgnoreCase(roleName)){
                    
                }
            }
        }
        return null;
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
