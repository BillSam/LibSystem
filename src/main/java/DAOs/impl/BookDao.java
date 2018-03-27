package DAOs.impl;

import DAOs.Crud;
import Entities.Book;
import Entities.BookCategory;
import Entities.BookStatus;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by error on 3/26/18.
 */
public class BookDao implements Crud<Book> {
    private EntityManager em;

    public BookDao(EntityManager em) {
        this.em = em;
    }

    public Book create(Book book) {
        try {
            em.persist(book);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return book;
    }

    public Book read(long isnb) {
        Book book = null;
        try {
            book = em.find(Book.class,isnb);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return book;

    }

    public Book update(Book pssdBook, long isnb) {
        Book dbBook = read(isnb);
        if (dbBook != null){
            dbBook.setBookCategory(pssdBook.getBookCategory());
            dbBook.setBookStatus(pssdBook.getBookStatus());
            dbBook.setISNB(pssdBook.getISNB());
            dbBook.setPageCount(pssdBook.getPageCount());
            dbBook.setTitle(pssdBook.getTitle());

            try {
                em.merge(dbBook);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }

        }
        return dbBook;
    }

    public Book delete(long isnb) {

        Book book = read(isnb);
        try {
            if (book != null) {

                em.remove(isnb);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        return book;
    }

    public List getAll() {

        return em.createNamedQuery("Book.findAll").getResultList();
    }

    public List getAllBooksByCategories(BookCategory bookCategory){

        List list = null;
        Query query = em.createNamedQuery("Book.Category");
        query.setParameter("bookCategory",bookCategory);

        try {
            list = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }



        return list;

    }

    public List getBookByStatus(BookStatus status){
        List list =null;
        Query query = em.createNamedQuery("getBooksbyStatus");
        query.setParameter("status",status);

        try {
            list = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;

    }

    public BookStatus getBookstatus(long isnb){
        Book book = read(isnb);
        BookStatus status = null;
        if (book != null){
             status = book.getBookStatus();
        }

        return status;
    }

    public boolean updateBookStatus(BookStatus bookStatus,long isnb){

        Book book = read(isnb);
        boolean status = false;
        if (book != null){
            book.setBookStatus(bookStatus);
            try {
                em.merge(book);
                status = true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return status;

   }

}
