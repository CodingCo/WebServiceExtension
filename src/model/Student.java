package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author simon
 */
@Entity
public class Student extends RoleSchool implements Serializable {

    private static final long serialVersionUID = 1L;

    private String semester;

    @OneToMany
    private Collection<Course> courses;

    public Student() {
        super.setRoleName("student");
    }

    public Student(String semester) {
        super.setRoleName("student");
        this.semester = semester;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    // adders removers
}
