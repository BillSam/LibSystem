package DAOs.impl;

import DAOs.Crud;
import Entities.Admin;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by error on 3/26/18.
 */
public class AdminDao implements Crud<Admin> {
    private EntityManager em;

    public AdminDao(EntityManager em) {
        this.em = em;
    }

    public Admin create(Admin admin) {

        try {
            em.persist(admin);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return admin;

    }

    public Admin read(long id) {
        Admin admin = null;
        try {
           admin= em.find(Admin.class,id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return admin;
    }

    public Admin update(Admin passdAdmin,long id) {

        Admin dbAdmin = null;
        try {
            dbAdmin = read(id);
            if (dbAdmin != null){
                dbAdmin.setEmail(passdAdmin.getEmail());
                dbAdmin.setId(passdAdmin.getId());
                dbAdmin.setName(passdAdmin.getName());
                dbAdmin.setPassword(passdAdmin.getPassword());
                em.merge(dbAdmin);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


        return dbAdmin;
    }

    public Admin delete(long id) {
        Admin admin = read(id);
        try {
            if (admin != null){
                em.remove(admin);
            }

        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }

        return admin;
    }

    public List getAll() {

        return em.createNamedQuery("Admin.findAll").getResultList();
    }


    public List getAllLoggedIn(){

        return em.createNamedQuery("Admin.isLoggedIn").getResultList();


    }

    public boolean verifyAdmin(String name, String password){

        boolean status = false;
        Query query = em.createNamedQuery("Admin.logIn");
        query.setParameter("name",name);
        query.setParameter("password",password);

        try {
            Admin admin = (Admin) query.getSingleResult();
            if (admin.getName().equals(name) && admin.getPassword().equals(password)){
                status = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return status;

    }


}
