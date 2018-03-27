package Beans.impl;

import Beans.BeanInt;
import DAOs.impl.LibrarianDao;
import Entities.Librarian;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by error on 3/27/18.
 */
@Stateless(mappedName = "librarianBean")
public class LibrarianBean implements BeanInt<Librarian> {

    @PersistenceContext
    private EntityManager entityManager;
    private LibrarianDao librarianDao = null;

    public LibrarianBean() {
        librarianDao = new LibrarianDao(entityManager);
    }

    public boolean create(Librarian librarian) {
        if (librarianDao.create(librarian) != null){
            return true;

        }
        return false;
    }

    public Librarian get(long id) {
        if (librarianDao.read(id) != null)
            return librarianDao.read(id);
        return null;
    }

    public boolean update(Librarian librarian, long id) {
        if (librarianDao.update(librarian,id) != null)
            return true;
        return false;
    }

    public boolean delete(long id) {
        if (librarianDao.delete(id) != null)
            return true;
        return false;
    }

    public List getAll() {
        return librarianDao.getAll();
    }

    public List getAllLibrariansLoggedIn(){return librarianDao.getAllLoggedIn();}

    public boolean logInLibrarian(String name,String password){
        if (librarianDao.verifyLibrarian(name, password)){
            return true;
        }
        return false;
    }

}
