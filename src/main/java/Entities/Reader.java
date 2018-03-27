package Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Created by error on 3/26/18.
 */
@NamedQueries({
        @NamedQuery(name = "Reader.findAll",query = "SELECT r from Reader r"),
        @NamedQuery(name = "Reader.logIn", query = "SELECT a FROM Admin a where a.name=:name AND a.password=:password")
})
@Entity
public class Reader {

    @Id
    private long id;
    private String email;
    private String name;
    private String password;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
