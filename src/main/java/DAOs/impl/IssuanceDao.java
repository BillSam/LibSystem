package DAOs.impl;

import DAOs.Crud;
import Entities.Book;
import Entities.BookStatus;
import Entities.Issuance;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by error on 3/26/18.
 */
public class IssuanceDao implements Crud<Issuance> {
    private EntityManager em;

    public IssuanceDao(EntityManager em) {
        this.em = em;
    }

    public Issuance create(Issuance issuance) {
        try {
            em.persist(issuance);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return issuance;
    }

    public Issuance read(long id) {
        Issuance issuance = null;
        try {
            issuance = em.find(Issuance.class,id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return issuance;
    }

    public Issuance update(Issuance issuance, long id) {
        Issuance dbIssuance = read(id);
        if (dbIssuance != null){
            dbIssuance.setBook(issuance.getBook());
            dbIssuance.setDueDate(issuance.getDueDate());
            dbIssuance.setIssueDate(issuance.getIssueDate());
            dbIssuance.setLibrarian(issuance.getLibrarian());
            dbIssuance.setReader(issuance.getReader());
            dbIssuance.setAmountDue(issuance.getAmountDue());
            dbIssuance.setIssuanceState(issuance.getIssuanceState());
            try {
                em.merge(dbIssuance);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }


        }
        return dbIssuance;
    }

    public Issuance delete(long id) {
        Issuance issuance = read(id);
        try {
            if (issuance != null) {

                em.remove(issuance);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        return issuance;
    }

    public List getAll() {
        return em.createNamedQuery("Issuance.All").getResultList();
    }

    public List getOverDueBooks(){
        List list = null;
        Query query = em.createNamedQuery("Overdue.Books");
        query.setParameter("date",new Date());

        try {
            list = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public boolean updateIssuanceBookStatus(Book book,BookStatus bookStatus){

        boolean status = false;
        Query query = em.createNamedQuery("IssuanceBook.Status");
        query.setParameter("book", book);
        book.setBookStatus(bookStatus);
        Issuance issuance = (Issuance) query.getSingleResult();

        if (issuance != null){

            issuance.setBook(book);
            try {
                em.merge(issuance);
                status = true;
            }catch (Exception e){
                e.printStackTrace();
                status = false;
            }

        }
        return status;
    }

    public List getReaderBorrowHistory(long id){

        Query query = em.createNamedQuery("Reader.History");
        query.setParameter("id",id);

        return query.getResultList();
    }




}
