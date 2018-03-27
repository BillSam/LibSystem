package Entities;

import javax.persistence.*;

/**
 * Created by error on 3/26/18.
 */
@NamedQueries({
        @NamedQuery(name = "Book.findAll", query = "SELECT b from Book b"),
        @NamedQuery(name = "Book.Category", query = "select b from Book b where b.bookCategory=:bookCategory"),
        @NamedQuery(name = "getBooksbyStatus", query = "select b from Book b where b.bookStatus=:status"),
})
@Entity
public class Book {
    @Id
    private long ISNB;
    private String title;
    private int pageCount;
    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;
    @ManyToOne
    private BookCategory bookCategory;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public long getISNB() {
        return ISNB;
    }

    public void setISNB(long ISNB) {
        this.ISNB = ISNB;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }
}
