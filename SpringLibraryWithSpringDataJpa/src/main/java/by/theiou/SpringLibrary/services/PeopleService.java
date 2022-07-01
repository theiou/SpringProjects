package by.theiou.SpringLibrary.services;

import by.theiou.SpringLibrary.models.Book;
import by.theiou.SpringLibrary.models.Person;
import by.theiou.SpringLibrary.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {
    @Autowired
    private PeopleRepository peopleRepository;

    @Transactional
    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    @Transactional
    public Person findById(int id){
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void create(Person person){
        peopleRepository.save(person);
    }

    @Transactional
    public void update(Person updatedPerson){
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }

    @Transactional
    public List<Book> getOwnedBooks(int id){
        return peopleRepository.findById(id).get().getBooks();
    }
}
