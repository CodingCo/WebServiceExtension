package webinterfaces;

import model.Course;
import model.RoleSchool;

/**
 *
 * @author kasper
 */
public interface CourseFacadeInterface {
    
    public String getAllCoursesAsJson();
    
    public String getOneCourseAsJson(long id);
    
    public Course addCourseFromGson(String json);
    
    public Course deleteCourse(long personId, String roleName);
    
    public RoleSchool assignCourseToRoleSchool(String json, long personId, String roleName);
    
    public RoleSchool removeCourseFromRoleSchool(long personId, String roleName);
}
