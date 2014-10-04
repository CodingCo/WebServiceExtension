package webinterfaces;

import model.Course;
import model.RoleSchool;

/**
 *
 * @author kasper
 */
public interface CourseFacadeInterface {
    public String getCourse(long id);
    
    public String getAllCourses();
    
    public Course addCourse(String json);
    
    public Course deleteCourse(long id);
    
    public RoleSchool addRoleSchoolToCourse(String json, long id);
}
