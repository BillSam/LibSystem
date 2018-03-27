package DAOs.impl;

import DAOs.Crud;
import Entities.Librarian;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by error on 3/26/18.
 */
public class LibrarianDao implements Crud<Librarian> {
    private EntityManager em;

    public LibrarianDao(EntityManager em) {
        this.em = em;
    }

    public Librarian create(Librarian librarian) {

        try {
            em.persist(librarian);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return librarian;
    }

    public Librarian read(long id) {
        Librarian librarian = null;
        try {
            librarian = em.find(Librarian.class,id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return librarian;
    }

    public Librarian update(Librarian passdLibrarian, long id) {


        Librarian dbLibrarian = read(id);
        try {
            if (dbLibrarian != null){
                dbLibrarian.setEmail(passdLibrarian.getEmail());
                dbLibrarian.setName(passdLibrarian.getName());
                dbLibrarian.setStaffId(passdLibrarian.getStaffId());
                dbLibrarian.setPassword(passdLibrarian.getPassword());
                em.merge(dbLibrarian);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


        return dbLibrarian;
    }

    public Librarian delete(long id) {

        Librarian librarian = read(id);
        try {
            if (librarian != null) {

                em.remove(id);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        return librarian;
    }

    public List getAll() {

        return em.createNamedQuery("Librarian.findAll").getResultList();
    }

    public List getAllLoggedIn(){

        return em.createNamedQuery("Librarian.loggedIn").getResultList();
    }

    public boolean verifyLibrarian(String name, String password){
        boolean status = false;

        Query query = em.createNamedQuery("Librarian.logIn");
        query.setParameter("name",name);
        query.setParameter("password",password);

        try {
            Librarian librarian = (Librarian) query.getSingleResult();
            if (librarian.getName().equals(name) && librarian.getPassword().equals(password))
                status = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return status;

    }
}
