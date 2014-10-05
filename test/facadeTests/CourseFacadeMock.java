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
    public Course deleteCourse(long courseId) {
        for(int i = 0; i < courses.size(); i++){
            if(courses.get(i).getId() == courseId){
                return courses.remove(i);
            }
        }
        return null;
    }

    @Override
    public Course assignRoleSchoolToCourse(long roleId, long courseId) {
        RoleSchool specificRole = null;
        for(Person person : persons){
                List<RoleSchool> roles = (ArrayList<RoleSchool>)person.getRoles();
                for(RoleSchool role : roles){
                    if(role.getId() == roleId){
                        specificRole = role;
                    }
                }
            
        }
        for(Course course : courses){
            if(course.getId() == courseId){
                course.addRole(specificRole);
                return course;
            }
        }
        return null;
    }

    @Override
    public Course unassignRoleSchoolFromCourse(long roleId, long courseId) {
//        for(Person person : persons){
//            List<RoleSchool> roles = (ArrayList<RoleSchool>)person.getRoles();
//            for(RoleSchool role : roles){
//                if(role.getId() == roleId){
//                    for(Course course : courses){
//                        for(RoleSchool coursesRole : course.getRoles()){
//                            course.removeRole(roleId);
//                            return person;
//                        }
//                    }
//                }
//            }
//        }
//        return null;
        for(Course course : courses){
            if(course.getId() == courseId){
                course.removeRole(roleId);
                return course;
            }
        }
        return null;
    }

    
    
    

}
