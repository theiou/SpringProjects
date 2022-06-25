package by.theiou.SpringLibrary.controllers;

import by.theiou.SpringLibrary.DAO.BookDAO;
import by.theiou.SpringLibrary.DAO.PersonDAO;
import by.theiou.SpringLibrary.models.Book;
import by.theiou.SpringLibrary.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    @Autowired
    BookDAO bookDAO;
    @Autowired
    PersonDAO personDAO;
    @GetMapping
    public String showAll(Model model){
        model.addAttribute("books", bookDAO.showAll());
        return "books/showAll";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, @ModelAttribute("person") Person person, Model model){
        model.addAttribute("book", bookDAO.show(id));
        Optional<Person> bookOwner = bookDAO.bookOwner(id);
        if(bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else
            model.addAttribute("people", personDAO.showAll());
        return "books/show";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/newBook";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors())
        return "books/newBook";
        else
        bookDAO.create(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}/edit")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors())
        return "books/edit";
        else
        bookDAO.update(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }
    @PostMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        bookDAO.assign(person, id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        bookDAO.release(id);
        return "redirect:/books/" + id;
    }



}
