package web.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person implements Serializable {

    private static final long serialVersionUID = -38467293585120720L;

    @Id
    @GeneratedValue
    @Column(name = "USER_ID", nullable = false)
    private long id;

    @Column(name = "email", unique = true, length = 128, nullable = false)
    private String email = "";

    @Column(name = "uname", unique = true, length = 8, nullable = false)
    private String uname = null;

    @Column(name = "passwd", length = 80, nullable = false)
    private String passwd = "";

    public Person() {
        super();
    }

    public Person(String email, String uname, String passwd) {
        super();
        this.email = email;
        this.uname = uname;
        this.passwd = passwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String pass) {
        this.passwd = pass;
    }
}
