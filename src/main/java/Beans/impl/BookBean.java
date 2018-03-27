package Beans.impl;

import Beans.BeanInt;
import DAOs.impl.BookDao;
import Entities.Book;
import Entities.BookCategory;
import Entities.BookStatus;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by error on 3/27/18.
 */
@Stateless(mappedName = "bookBean")
public class BookBean implements BeanInt<Book> {
    @PersistenceContext
    private EntityManager entityManager;
    private BookDao bookDao = null;


    public BookBean() {
        if (bookDao == null){
            bookDao = new BookDao(entityManager);
        }
    }

    public boolean create(Book book) {
        if (bookDao.create(book) != null)
            return true;
        return false;
    }


    @Transactional
    public Book get(long id) {
        if (bookDao.read(id) != null)
            return bookDao.read(id);
        return null;
    }

    @Transactional
    public boolean update(Book book, long id) {
        if (bookDao.update(book, id) != null)
            return true;
        return false;
    }

    public boolean delete(long id) {
        if (bookDao.delete(id) != null)
            return true;
        return false;
    }

    public List getAll() {
        return bookDao.getAll();
    }

    public List getBooksByCategory(BookCategory bookCategory){
        return bookDao.getAllBooksByCategories(bookCategory);
    }

    public List getAllBooksByStatus(BookStatus status){
        return bookDao.getBookByStatus(status);
    }

    @Transactional
    public BookStatus getABookStatus(long isnb){
        if (bookDao.getBookstatus(isnb) != null)
            return bookDao.getBookstatus(isnb);
        return null;

    }

    @Transactional
    public boolean updateBookStatus(BookStatus bookStatus, long id){
        if (bookDao.updateBookStatus(bookStatus, id))
            return true;
        return false;
    }


}
