package interview.library.service;

import interview.library.model.Book;
import interview.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    public Book getBook(int id) {
        return bookRepository.findById(id).get();
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findByIsLoaned(false);
    }

    public List<String> getAvailableTitles() {
        return bookRepository.findAvailableTitles();
    }

    public List<Book> getAvailableBooksByTitle(String title) {
        return bookRepository.findByTitleAndIsLoaned(title, false);
    }

}
