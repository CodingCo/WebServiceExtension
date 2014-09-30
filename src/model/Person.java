package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author simon
 */
@Entity
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personIdGen")
    @SequenceGenerator(name = "personIdGen", sequenceName = "PERSON_SEQ", initialValue = 100000, allocationSize = 1)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @OneToMany
    private Collection<RoleSchool> roles;

    private String mail;

    private String phone;

    public Person(String firstName, String lastName, String phone, String mail, RoleSchool role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.mail = mail;
        addRole(role);
    }

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public final void addRole(RoleSchool role) {
        if (roles == null) {
            roles = new ArrayList();
        }
        roles.add(role);
    }

    public void removeRole(RoleSchool role) {
        if (roles.contains(role)) {
            roles.remove(role);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "model.Person[ id=" + id + " ]";
    }

}
