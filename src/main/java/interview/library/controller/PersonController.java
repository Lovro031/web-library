package interview.library.controller;

import interview.library.model.Loan;
import interview.library.model.Person;
import interview.library.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public Person getUserById(@PathVariable int id) {
        return personService.getPerson(id);
    }

    @PostMapping
    public void addNewPerson(@ModelAttribute("person") Person person) {
        personService.addPerson(person);
    }

    @PutMapping("/{id}")
    public void updatePerson(@ModelAttribute("person") Person person) {
        personService.updatePerson(person);
    }

    @PutMapping("/{id}/scan")
    public void updatePersonScan(@RequestParam("imageSource") String imageSource, @PathVariable int id) {
        personService.updatePerson(imageSource, id);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Integer id) {
        personService.deletePerson(id);
    }

    //TODO: enable recurring daily updates or updates on admin request
    @GetMapping("/late/update")
    public void updateAllPersonsDaysLate() {
        personService.updateAllPersonsDaysLate();
    }

    @GetMapping("/{id}/late/update")
    public void updatePersonDaysLate(@PathVariable int id) {
        personService.updatePersonDaysLate(id);
    }

    @GetMapping("/latest/{nPerson}")
    public List<Person> getNMostLatePersons(@PathVariable int nPerson) {
        return personService.getNMostLatePersons(nPerson);
    }

    @GetMapping("/{id}/late/fee")
    public double getPersonLateFee(@PathVariable int id) {
        return personService.getPersonLateFee(id);
    }

    @GetMapping("/{id}/activeloans")
    public List<Loan> getActiveLoans(@PathVariable int id) {
        return personService.getActiveLoans(id);
    }

}
