package by.theiou.SpringLibrary.repositories;

import by.theiou.SpringLibrary.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findByNameStartingWithIgnoreCase(String nameFirstLetters);
}
