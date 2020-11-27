package interview.library.repository;

import interview.library.model.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Integer> {

    List<Loan> findByBookId(int id);
    List<Loan> findByPersonId(int id);
    List<Loan> findByLoanDate(LocalDate date);
    List<Loan> findByReturnDate(LocalDate date);
    List<Loan> findByIsCompleted(boolean isCompleted);
    List<Loan> findByIsCompletedAndIsLate(boolean isCompleted, boolean isLate);
}
