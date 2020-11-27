package interview.library.service;

import interview.library.model.Loan;
import interview.library.model.Person;
import interview.library.model.PersonIdInfo;
import interview.library.repository.PersonRepository;
import interview.library.util.MicroblinkAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private static final double LATE_FEE_PER_BOOK_PER_DAY = 0.4;

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        personRepository.findAll().forEach(persons::add);
        return persons;
    }

    public Person getPerson(int id) {
        return personRepository.findById(id).get();
    }

    public void addPerson(Person person) {
        personRepository.save(person);
    }

    public void updatePerson(Person person) {
        personRepository.save(person);
    }

    public void updatePerson(String imageSource, int id) {
        Person person = personRepository.findById(id).get();
        String rawMrzData = MicroblinkAPI.getRawMrzDataFromImage(imageSource);
        PersonIdInfo personIdInfo = MicroblinkAPI.parseRawMrzData(rawMrzData);

        person = updateFromPersonIdInfo(person, personIdInfo);
        personRepository.save(person);
    }

    private Person updateFromPersonIdInfo(Person person, PersonIdInfo personIdInfo) {
        person.setFirstName(personIdInfo.getSecondaryIdentifier());
        person.setLastName(personIdInfo.getPrimaryIdentifier());
//        would need to convert to proper date format and store in LocalDate
//        person.setDateOfBirth(personIdInfo.getDateOfBirth());

        return person;
    }

    public void deletePerson(int id) {
        personRepository.deleteById(id);
    }

    public void updateAllPersonsDaysLate() {
        List<Person> allPersons = getAllPersons();
        for(Person person : allPersons) {
            int totalDaysLate = 0;
            List<Loan> loans = person.getLoans();
            for(Loan loan : loans) {
                totalDaysLate += loan.getDaysLate();
            }
            person.setDaysLate(totalDaysLate);
            personRepository.save(person);
        }
    }

    public void updatePersonDaysLate(int id) {
        Person person = getPerson(id);
        List<Loan> loans = person.getLoans();

        int totalDaysLate = 0;
        for(Loan loan : loans) {
            totalDaysLate += loan.getDaysLate();
        }

        person.setDaysLate(totalDaysLate);
        personRepository.save(person);
    }

    public List<Loan> getActiveLoans(int id) {
        Person person = getPerson(id);
        return person.getLoans();
    }

    public List<Person> getNMostLatePersons(int n) {
        Page<Person> page = personRepository.findAll(PageRequest.of(0, n, Sort.by(Sort.Direction.DESC, "daysLate")));
        return page.getContent();
    }

    public double getPersonLateFee(int id) {
        Person person = getPerson(id);
        return person.getDaysLate()*LATE_FEE_PER_BOOK_PER_DAY;
    }

}
