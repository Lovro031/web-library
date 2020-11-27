package interview.library.controller;

import interview.library.model.Book;
import interview.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.getBook(id);
    }

    @PostMapping
    public void addNewBook(@ModelAttribute("book") Book book) {
        bookService.addBook(book);
    }

    @PutMapping("/{id}")
    public void updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/available")
    public List<String> getAvailableTitles() {
        return bookService.getAvailableTitles();
    }

    @GetMapping("/available/all")
    public List<Book> getAvailableBooks() {
        return bookService.getAvailableBooks();
    }

    @GetMapping("/available/{title}")
    public List<Book> get(@PathVariable String title) {
        return bookService.getAvailableBooksByTitle(title);
    }
}
