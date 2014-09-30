package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author simon
 */
@Entity
public class BusinessAcademy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String name;

    @OneToMany
    private Collection<Person> persons;

    @OneToMany
    private Collection<Course> courses;

    @OneToMany
    private Collection<ClassRoom> classRooms;

    public BusinessAcademy() {
    }

    public BusinessAcademy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPerson(Person p) {
        if (persons == null) {
            persons = new ArrayList<>();
        }
        persons.add(p);
    }

    public void addCourses(Course c) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(c);
    }

    public void addClassRooms(ClassRoom c) {
        if (classRooms == null) {
            classRooms = new ArrayList<>();
        }
        classRooms.add(c);
    }
}
