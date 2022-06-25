package by.theiou.SpringLibrary.DAO;

import by.theiou.SpringLibrary.models.Book;
import by.theiou.SpringLibrary.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> showAll(){
        return jdbcTemplate.query("select * from book", new BookMapper());
    }

    public Book show(int id){
        return (Book) jdbcTemplate.queryForObject("select * from book where book_id = ?", new BookMapper(), id);
    }

    public void create(Book book){
        jdbcTemplate.update("insert into book(name, author, year) values(?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYear());
    }

    public void delete(int id){
        jdbcTemplate.update("delete from book where book_id = ?", id);
    }

    public void update(Book book){
        jdbcTemplate.update("update book set name = ?, author = ?, year = ? where book_id = ?",
                book.getName(), book.getAuthor(), book.getYear(), book.getId());
    }

    public void assign(Person person, int bookId){
        jdbcTemplate.update("update book set person_id = ? where book_id = ?", person.getId(), bookId);
    }

    public Optional<Person> bookOwner(int id){
        return jdbcTemplate.query("select p.* from book b join person p on p.person_id = b.person_id where b.book_id = ?", new PersonMapper(), id).stream().findAny();
    }
    public void release(int id){
        jdbcTemplate.update("update book set person_id = null where book_id = ?", id);
    }

}
