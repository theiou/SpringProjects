package by.theiou.SpringLibrary.services;

import by.theiou.SpringLibrary.models.Book;
import by.theiou.SpringLibrary.models.Person;
import by.theiou.SpringLibrary.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BooksService {
    @Autowired
    BooksRepository booksRepository;

    public List<Book> findAll(Boolean sortByYear){
        if(sortByYear == null)
            return booksRepository.findAll();

        else
            return booksRepository.findAll(Sort.by("year"));
    }

    public List<Book> findAllWithPagination(Boolean sortByYear, int page, int booksPerPage){
        if(sortByYear == null)
            return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
        else
            return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
    }

    @Transactional
    public Book findById(int id){
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void create(Book book){
        booksRepository.save(book);
    }

    @Transactional
    public void update(Book updatedBook){
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }

    @Transactional
    public Person bookOwner(int id){
        return booksRepository.findById(id).get().getOwner();
    }

    @Transactional
    public void assign(Person person, int bookId){
        booksRepository.findById(bookId).get().setOwner(person);
    }

    @Transactional
    public List<Book> findByNameStartingWithIgnoreCase(String nameFirstLetters){
        return booksRepository.findByNameStartingWithIgnoreCase(nameFirstLetters);
    }

    @Transactional
    public void release(int id){
        booksRepository.findById(id).get().setOwner(null);

    }

}
