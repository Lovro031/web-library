package interview.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    String firstName;

    @Column
    String lastName;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate dateOfBirth;

    @ElementCollection
    List<String> contactNumbers;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private PersonRoles role;

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Loan> loans = new ArrayList<>();

    @Column(nullable = false)
    private int daysLate;

    public Person() {}

    public Person(String firstName, String lastName, LocalDate dateOfBirth, String email, String password, PersonRoles role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.role = role;
        this.contactNumbers = new ArrayList<>();
        this.daysLate = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<String> getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(List<String> contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public void addContactNumber(String contactNumber) {
        this.contactNumbers.add(contactNumber);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonRoles getRole() {
        return role;
    }

    public void setRole(PersonRoles role) {
        this.role = role;
    }

    public List<Loan> getLoans() {
        return new ArrayList<>(loans);
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public void addLoan(Loan loan) {
        this.loans.add(loan);
        loan.setPerson(this);
    }

    public void removeLoan(Loan loan) {
        this.loans.remove(loan);
        loan.setPerson(null);
    }

    public int getDaysLate() {
        return daysLate;
    }

    public void setDaysLate(int daysLate) {
        this.daysLate = daysLate;
    }

    public static Comparator<Person> byDaysLateDesc = new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            return Integer.compare(p2.getDaysLate(), p1.getDaysLate());
        }
    };
}
