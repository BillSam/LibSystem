package Beans.impl;

import Beans.BeanInt;
import DAOs.impl.ReaderDao;
import Entities.Reader;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by error on 3/27/18.
 */
@Stateless(mappedName = "readerBean")
public class ReaderBean implements BeanInt<Reader> {
    @PersistenceContext
    private EntityManager entityManager;
    private ReaderDao readerDao;

    public ReaderBean() {
        readerDao = new ReaderDao(entityManager);
    }

    public boolean create(Reader reader) {
        if (readerDao.create(reader) != null)
            return true;
        return false;
    }

    public Reader get(long id) {
        if (readerDao.read(id) != null)
            return readerDao.read(id);
        return null;
    }

    public boolean update(Reader reader, long id) {
        if (readerDao.update(reader,id) != null)
            return true;
        return false;
    }

    public boolean delete(long id) {
        if (readerDao.delete(id) != null)
            return true;
        return false;
    }

    public List getAll() {
        return readerDao.getAll();
    }
    public boolean logInReader(String name, String password){
        if (readerDao.verifyReader(name,password))
            return true;
        return false;
    }
}
