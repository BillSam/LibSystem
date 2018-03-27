package Entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by error on 3/26/18.
 */
@NamedQueries({
        @NamedQuery(name = "Issuance.All", query = "SELECT i from Issuance i"),
        @NamedQuery(name = "Overdue.Books", query = "select i from Issuance i where i.dueDate < :date"),
        @NamedQuery(name = "IssuanceBook.Status", query = "select i from Issuance i where i.book=:book"),
        @NamedQuery(name = "Reader.History", query = "select i from Issuance i where i.reader.id=:id")
})
@Entity
public class Issuance {
    @Id
    private long id;
    @OneToOne
    private Reader reader;
    @OneToOne
    private Librarian librarian;
    @OneToOne
    private Book book;
    private Date issueDate;
    private Date dueDate;
    private Date dateReturned;
    private double AmountDue;
    @Enumerated(EnumType.STRING)
    private IssuanceState issuanceState;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
    }

    public double getAmountDue() {
        return AmountDue;
    }

    public void setAmountDue(double amountDue) {
        AmountDue = amountDue;
    }

    public IssuanceState getIssuanceState() {
        return issuanceState;
    }

    public void setIssuanceState(IssuanceState issuanceState) {
        this.issuanceState = issuanceState;
    }
}
