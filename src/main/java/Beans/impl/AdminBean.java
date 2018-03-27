package Beans.impl;

import Beans.BeanInt;
import DAOs.impl.AdminDao;
import Entities.Admin;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by error on 3/27/18.
 */
@Stateless(mappedName = "adminBean")
public class AdminBean implements BeanInt<Admin> {
    @PersistenceContext
    private EntityManager entityManager;
    private AdminDao adminDao = null;

    public AdminBean() {
        adminDao = new AdminDao(entityManager);
    }

    public boolean create(Admin admin) {
        if (adminDao.create(admin) != null){
            return true;

        }
        return false;
    }

    public Admin get(long id) {
        if (adminDao.read(id) != null){

            return adminDao.read(id);
        }
        return null;
    }

    public boolean update(Admin admin, long id) {
        if (adminDao.update(admin,id) != null){
            return true;
        }
        return false;
    }

    public boolean delete(long id) {
        if (adminDao.delete(id) != null){
            return true;
        }
        return false;
    }

    public List getAll() {
        return adminDao.getAll();
    }

    public List getAllAdminsLoggedIn(){
        return adminDao.getAllLoggedIn();
    }

    public boolean logInAdmin(String name, String password){
        if (adminDao.verifyAdmin(name, password))
            return true;

        return false;
    }
}
