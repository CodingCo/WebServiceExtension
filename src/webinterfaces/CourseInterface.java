package webinterfaces;

import model.Course;

/**
 *
 * @author kasper
 */
public interface CourseInterface {
    public String getCourse();
    
    public String getAllCourses();
    
    public Course addCourse();
    
    public Course deleteCourse();
}
