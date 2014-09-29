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
public class AssistentTeacher extends RoleSchool implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToMany
    private Collection<Course> courses;

    public AssistentTeacher() {
        super.setRoleName("Assistent Teacher");
    }

    @Override
    public String toString() {
        return "AssistentTeacher{" + '}' + super.toString();
    }

}
