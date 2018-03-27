package Beans.impl;

import Beans.BeanInt;
import DAOs.impl.IssuanceDao;
import Entities.Book;
import Entities.BookStatus;
import Entities.Issuance;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by error on 3/27/18.
 */
@Stateless(mappedName = "issuanceBean")
public class IssuanceBean implements BeanInt<Issuance> {

    @PersistenceContext
    private EntityManager entityManager;
    private IssuanceDao issuanceDao = null;

    public IssuanceBean() {
        issuanceDao = new IssuanceDao(entityManager);

    }

    @Transactional
    public boolean create(Issuance issuance) {
        if (issuanceDao.create(issuance) != null){
            return true;
        }
        return false;
    }

    public Issuance get(long id) {
        if (issuanceDao.read(id) != null)
            return issuanceDao.read(id);
        return null;
    }

    @Transactional
    public boolean update(Issuance issuance, long id) {
        if (issuanceDao.update(issuance,id) != null)
            return true;
        return false;
    }

    public boolean delete(long id) {
        if (issuanceDao.delete(id) != null)
            return true;
        return false;
    }

    public List getAll() {
        return issuanceDao.getAll();
    }

    @Transactional
    public boolean isBookOverDue(Book book){
        List<Issuance> list = getOverDueBooks();

        for (Issuance i : list) {
            if (i.getBook().getISNB() == book.getISNB()){
                return true;
            }

        }
        return false;


    }

    public List getOverDueBooks(){

        return issuanceDao.getOverDueBooks();
    }
    @Transactional
    public boolean updateIssuanceBookStatus(Book book, BookStatus bookStatus){
        if (issuanceDao.updateIssuanceBookStatus(book,bookStatus))
            return true;
        return false;
    }

    public List getReaderBorrowHistory(long id){
        return issuanceDao.getReaderBorrowHistory(id);
    }
}
