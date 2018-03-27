package DAOs.impl;

import DAOs.Crud;
import Entities.Reader;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by error on 3/26/18.
 */
public class ReaderDao implements Crud<Reader> {
    private EntityManager em;

    public ReaderDao(EntityManager em) {
        this.em = em;
    }

    public Reader create(Reader reader) {
        try {
            em.persist(reader);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return reader;
    }

    public Reader read(long id) {
        Reader reader = null;
        try {
            reader = em.find(Reader.class,id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return reader;
    }

    public Reader update(Reader passdreader, long id) {
        Reader dbReader = read(id);
        if (dbReader != null){
            dbReader.setEmail(passdreader.getEmail());
            dbReader.setId(passdreader.getId());
            dbReader.setName(passdreader.getName());
            dbReader.setPassword(passdreader.getPassword());
            try{
                em.merge(dbReader);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return dbReader;
    }

    public Reader delete(long id) {
        Reader reader = read(id);
        try {
            if (reader != null) {

                em.remove(id);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        return reader;
    }

    public List getAll() {
        return em.createNamedQuery("Reader.findAll").getResultList();
    }

    public boolean verifyReader(String name, String password){

        boolean status = false;
        Query query = em.createNamedQuery("Reader.logIn");
        query.setParameter("name",name);
        query.setParameter("password",password);

        try {
            Reader reader = (Reader) query.getSingleResult();
            if (reader.getName().equals(name) && reader.getPassword().equals(password)){
                status = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return status;

    }
}
