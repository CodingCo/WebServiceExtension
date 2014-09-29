package model;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author simon
 */
@Entity
public class AssistentTeacher extends RoleSchool implements Serializable {

    private static final long serialVersionUID = 1L;

    public AssistentTeacher() {

    }

    @Override
    public String toString() {
        return "AssistentTeacher{" + '}' + super.toString();
    }

}
