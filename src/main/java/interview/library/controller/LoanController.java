package interview.library.controller;

import interview.library.model.Loan;
import interview.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    public Loan getLoanById(@PathVariable int id) {
        return loanService.getLoan(id);
    }

    @PostMapping
    public void addNewLoan(@RequestParam int bookId, @RequestParam int personId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate loanDate) {
        loanService.addLoan(bookId, personId, loanDate);
    }

    @PutMapping("/{id}")
    public void updateLoan(@RequestBody Loan loan) {
        loanService.updateLoan(loan);
    }

    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable Integer id) {
        loanService.deleteLoan(id);
    }

    @PutMapping("/{id}/complete")
    public void completeLoan(@PathVariable Integer id) {
        loanService.completeLoan(id);
    }

    @GetMapping("/date/{date}")
    public List<Loan> getLoansByLoanDate(@PathVariable String date) {
        return loanService.getLoansByLoanDate(LocalDate.parse(date));
    }

    @GetMapping("/completed")
    public List<Loan> getCompletedLoans() {
        return loanService.getLoansByCompleted(true);
    }

    @GetMapping("/open")
    public List<Loan> getOpenLoans() {
        return loanService.getLoansByCompleted(false);
    }

    @GetMapping("/late")
    public List<Loan> getLateLoans() {
        return loanService.getLoansByCompletedAndLate(false, true);
    }

    @GetMapping("/history/books/{id}")
    public List<Loan> getLoanHistoryByBookId(@PathVariable int id) {
        return loanService.getLoansByBookId(id);
    }

    @GetMapping("/history/persons/{id}")
    public List<Loan> getLoanHistoryByPersonId(@PathVariable int id) {
        return loanService.getLoansByPersonId(id);
    }


}
