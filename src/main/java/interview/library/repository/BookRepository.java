package interview.library.repository;

import interview.library.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findByIsLoaned(boolean isLoaned);
    List<Book> findByTitleAndIsLoaned(String title, boolean isLoaned);
    @Query("SELECT DISTINCT b.title FROM Book b WHERE b.isLoaned = false")
    List<String> findAvailableTitles();
}
