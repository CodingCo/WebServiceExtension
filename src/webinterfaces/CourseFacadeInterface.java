package webinterfaces;

import model.Course;
import model.Person;

/**
 *
 * @author kasper
 */
public interface CourseFacadeInterface {
    
    public String getAllCoursesAsJson();
    
    public String getOneCourseAsJson(long id);
    
    public Course addCourseFromGson(String json);
    
    public Course deleteCourse(long courseId);
    
    public Course assignRoleSchoolToCourse(long personId, long roleId, long courseId);
    
    public Course unassignRoleSchoolFromCourse(long personId, long roleId, long courseId);
}
