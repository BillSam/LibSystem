package Beans.impl;

import Beans.LibraryInt;
import Entities.Book;
import Entities.BookStatus;
import Entities.Issuance;
import Entities.IssuanceState;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by error on 3/27/18.
 */
@Stateless(mappedName = "LibraryBean")
public class LibraryBean implements LibraryInt {

    @EJB
    private IssuanceBean issuanceBean;
    @EJB
    private BookBean bookBean;

    /*
    * before issuing a book: check book in db(status),verify reader,
    * update status of book, return date*/
    @Transactional
    public boolean checkOutBook(Object o) {
        Issuance issuance = (Issuance) o;
        if (bookBean.get(issuance.getBook().getISNB()) != null){//check book in db
            if (bookBean.getABookStatus(issuance.getBook().getISNB()).equals(BookStatus.AVAILABLE)){//check availability
                if (issuanceBean.create(issuance)){//created successfully
                    //update status of the book
                    if (bookBean.updateBookStatus(BookStatus.ISSUED,issuance.getBook().getISNB())){//update status issued in Book
                        if (issuanceBean.updateIssuanceBookStatus(issuance.getBook(),BookStatus.ISSUED)){//update status issued in Issuance
                            return true;
                        }

                    }

                }

            }

        }

        return false;
    }

    @Transactional
    public Issuance checkInBook(Object o) {
        Issuance issuance = (Issuance) o;
        Issuance dbIssuance = issuanceBean.get(issuance.getId());
        if (dbIssuance != null) {
            if (dbIssuance.getBook().getISNB() == issuance.getBook().getISNB()) {//verify book
                if (dbIssuance.getReader().getId() == issuance.getReader().getId()) {//verify user
                    //check if Overdue..
                    if (!issuanceBean.isBookOverDue(dbIssuance.getBook())){
                        if (bookBean.updateBookStatus(BookStatus.AVAILABLE,dbIssuance.getBook().getISNB())){
                            //update issuance state to settled..
                            dbIssuance.setIssuanceState(IssuanceState.SETTLED);
                            if (issuanceBean.update(dbIssuance,dbIssuance.getId())){
                                return dbIssuance;
                            }

                        }

                    }{
                        //deal with fee
                    }




                }

            }
        }

        return dbIssuance;
    }

    @Transactional
    public boolean placeReservation(Object o) {
        Book book = (Book) o;
        if (bookBean.get(book.getISNB()) != null){
            Book book1 =bookBean.get(book.getISNB());
            book1.setBookStatus(BookStatus.RESERVED);
            if (bookBean.update(book1,book.getISNB())){
                return true;
            }

        }
        return false;
    }

    public List reportOverdueBooks() {

        return issuanceBean.getOverDueBooks();
    }

    public double calculateFee(Object o) {
        return 0;
    }

    public List getReaderBorrowHistory(long id) {

        return issuanceBean.getReaderBorrowHistory(id);

    }
}
