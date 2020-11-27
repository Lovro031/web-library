package interview.library.controller;

import interview.library.model.Book;
import interview.library.model.Person;
import interview.library.service.BookService;
import interview.library.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FormTestController {

    @Autowired
    private BookService bookService;

    @Autowired
    private PersonService personService;

    @GetMapping("/addBook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @GetMapping("/addPerson")
    public String addPerson(Model model) {
        model.addAttribute("person", new Person());
        return "addperson";
    }

    @GetMapping("/addLoan")
    public String addLoan(Model model) {
        List<Book> books = bookService.getAvailableBooks();
        List<Person> persons = personService.getAllPersons();   //TODO: create getAvailablePersons in PersonService and use it instead of getAllPersons
        model.addAttribute("books", books);
        model.addAttribute("persons", persons);
        return "addloan";
    }
}
