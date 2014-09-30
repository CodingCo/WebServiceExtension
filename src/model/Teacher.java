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
public class Teacher extends RoleSchool implements Serializable {

    private static final long serialVersionUID = 1L;

    private String degree;

    @OneToMany
    private Collection<Course> courses;

    public Teacher() {
        super.setRoleName("Teacher");
    }

    public Teacher(String degree) {
        super.setRoleName("Teacher");
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Teacher{" + "degree=" + degree + '}';
    }

    // adders removers
}
