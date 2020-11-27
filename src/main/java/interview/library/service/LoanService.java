package interview.library.service;

import interview.library.model.Book;
import interview.library.model.Loan;
import interview.library.model.Person;
import interview.library.repository.BookRepository;
import interview.library.repository.LoanRepository;
import interview.library.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {

    private static final int LOAN_DURATION_IN_WEEKS = 2;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PersonRepository personRepository;

    public List<Loan> getAllLoans() {
        List<Loan> loans = new ArrayList<>();
        loanRepository.findAll().forEach(loans::add);
        return loans;
    }

    public Loan getLoan(int id) {
        return loanRepository.findById(id).get();
    }

    public void addLoan(int bookId, int personId, LocalDate loanDate) {
        Book book = bookRepository.findById(bookId).get();
        if(book.isLoaned())             //TODO: if book is already loaned, throw some exception
            return;

        Person person = personRepository.findById(personId).get();
        if(person.getDaysLate() > 0)    //TODO: if user is late, throw some exception
            return;

        book.setLoaned(true);

        Loan loan = new Loan(book, person, loanDate);
        loan.setReturnDate(loanDate.plusWeeks(LOAN_DURATION_IN_WEEKS));

        person.addLoan(loan);

        bookRepository.save(book);
        personRepository.save(person);
        loanRepository.save(loan);
    }

    //TODO: modify(extend) the return date
    public void updateLoan(Loan loan) {
        loanRepository.save(loan);
    }

    public void deleteLoan(int id) {
        Loan loan = getLoan(id);
        Book book = loan.getBook();
        book.setLoaned(false);

        Person person = loan.getPerson();
        person.removeLoan(loan);

        bookRepository.save(book);
        personRepository.save(person);
        loanRepository.deleteById(id);
    }

    public void completeLoan(int id) {
        Loan loan = getLoan(id);
        if(loan.isCompleted())      //TODO: if loan is already completed, throw exception
            return;

        Book book = loan.getBook();
        book.setLoaned(false);

        Person person = loan.getPerson();
        person.removeLoan(loan);

        loan.setReturnDate(LocalDate.now());
        loan.setCompleted(true);

        bookRepository.save(book);
        personRepository.save(person);
        loanRepository.save(loan);
    }

    public List<Loan> getLoansByBookId(int id) {
        return loanRepository.findByBookId(id);
    }

    public List<Loan> getLoansByPersonId(int id) {
        return loanRepository.findByPersonId(id);
    }

    public List<Loan> getLoansByLoanDate(LocalDate date) {
        return loanRepository.findByLoanDate(date);
    }

    public List<Loan> getLoansByReturnDate(LocalDate date) {
        return loanRepository.findByReturnDate(date);
    }

    public List<Loan> getLoansByCompleted(boolean isCompleted) {
        return loanRepository.findByIsCompleted(isCompleted);
    }

    public List<Loan> getLoansByCompletedAndLate(boolean isCompleted, boolean isLate) {
        return loanRepository.findByIsCompletedAndIsLate(isCompleted, isLate);
    }

}
