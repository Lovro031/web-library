package interview.library.model;

import interview.library.model.converter.BooleanStringConverter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="bookId")
    private Book book;

    @ManyToOne
    @JoinColumn(name="personId")
    private Person person;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate loanDate;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate returnDate;

    @Column(nullable = false)
    @Convert(converter = BooleanStringConverter.class)
    private boolean isCompleted;

    @Column(nullable = false)
    @Convert(converter = BooleanStringConverter.class)
    private boolean isLate;

    @Column(nullable = false)
    private int daysLate;

    protected Loan() {}

    public Loan(Book book, Person person, LocalDate loanDate) {
        this.book = book;
        this.person = person;
        this.loanDate = loanDate;
        this.isCompleted = false;
        this.isLate = false;
        this.daysLate = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isLate() {
        return isLate;
    }

    public void setLate(boolean late) {
        isLate = late;
    }

    public int getDaysLate() {
        return daysLate;
    }

    public void setDaysLate(int daysLate) {
        this.daysLate = daysLate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return id == loan.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
