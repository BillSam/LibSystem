package Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Created by error on 3/26/18.
 */
@NamedQueries({
        @NamedQuery(name = "Admin.findAll",query = "SELECT a from Admin a"),
        @NamedQuery(name = "Admin.isLoggedIn", query = "select a from Admin As a where a.isLoggedIn = true"),
        @NamedQuery(name = "Admin.logIn", query = "SELECT a FROM Admin a where a.name=:name AND a.password=:password")
})
@Entity
public class Admin {
    @Id
    private long id;
    private String name;
    private String password;
    private String email;
    private boolean isLoggedIn;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
