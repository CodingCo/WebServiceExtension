package model;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author simon
 */
@Entity
public class Teacher extends RoleSchool implements Serializable {

    private static final long serialVersionUID = 1L;

    private String degree;

    public Teacher() {
    }

    @Override
    public String toString() {
        return "Teacher{" + "degree=" + degree + '}';
    }

}
