package Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Created by error on 3/26/18.
 */
@NamedQueries({
        @NamedQuery(name = "Librarian.findAll", query = "SELECT l FROM Librarian l"),
        @NamedQuery(name = "Librarian.loggedIn", query = "SELECT l from Librarian AS l where l.isLoggedIn = true"),
        @NamedQuery(name = "Librarian.logIn", query = "SELECT a FROM Admin a where a.name=:name AND a.password=:password")
})

@Entity
public class Librarian {

    @Id
    private long staffId;
    private String name;
    private String email;
    private String password;
    private boolean isLoggedIn;

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
