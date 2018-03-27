package Beans;


import javax.ejb.Local;
import java.util.List;

/**
 * Created by error on 3/27/18.
 */
@Local
public interface LibraryInt {
    boolean checkOutBook(Object o);
    Object checkInBook(Object o);
    boolean placeReservation(Object o);
    List reportOverdueBooks();
    double calculateFee(Object o);
    List getReaderBorrowHistory(long id);

}
