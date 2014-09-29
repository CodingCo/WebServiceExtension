package model;

import java.io.Serializable;
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
    private Collection<ClassRoom> classRoomes;

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

    // adders removers
}
