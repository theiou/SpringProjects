package by.theiou.SpringLibrary.DAO;

import by.theiou.SpringLibrary.models.Book;
import by.theiou.SpringLibrary.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {

    /*JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> showAll(){
        return jdbcTemplate.query("select * from person", new PersonMapper());
    }
    public Person show(int id){

        return (Person) jdbcTemplate.queryForObject("select * from person where person_id = ?", new PersonMapper(), id);
    }

    public void create(Person person){
        jdbcTemplate.update("insert into person(name, year_of_birth) values(?, ?)", person.getName(), person.getYearOfBirth());

    }
    public void delete(int id){
        jdbcTemplate.update("delete from person where person_id = ?", id);
    }
    public void update(Person person){
        jdbcTemplate.update("update person set name = ?, year_of_birth = ? where person_id = ?",
                person.getName(), person.getYearOfBirth(), person.getId());
    }

    public List<Book> getOwnedBooks(int id){
        return jdbcTemplate.query("select * from book where person_id = ?", new BookMapper(), id);
    }
*/
}
