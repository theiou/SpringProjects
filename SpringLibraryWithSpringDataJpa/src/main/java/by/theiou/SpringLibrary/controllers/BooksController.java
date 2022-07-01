package by.theiou.SpringLibrary.controllers;

import by.theiou.SpringLibrary.DAO.BookDAO;
import by.theiou.SpringLibrary.DAO.PersonDAO;
import by.theiou.SpringLibrary.models.Book;
import by.theiou.SpringLibrary.models.Person;
import by.theiou.SpringLibrary.repositories.BooksRepository;
import by.theiou.SpringLibrary.services.BooksService;
import by.theiou.SpringLibrary.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    @Autowired
    BooksService booksService;
    @Autowired
    PeopleService peopleService;

    @GetMapping("/search")
    public String search(){
        return "books/search";
    }

    @PostMapping("/search")
    public String search(@RequestParam("search") String search, Model model){
        List<Book> books = booksService.findByNameStartingWithIgnoreCase(search);
        if(!books.isEmpty())
            model.addAttribute("books", books);
        else
            model.addAttribute("none", "nothing");
        return "books/search";
    }

    @GetMapping
    public String showAll(@RequestParam(name = "sort_by_year", required = false)Boolean sortByYear,
                          @RequestParam(name = "page", required = false) Integer page,
                          @RequestParam(name = "books_per_page", required = false) Integer booksPerPage,
                          Model model){

        if(page == null || booksPerPage == null)
            model.addAttribute("books", booksService.findAll(sortByYear));
        else
            model.addAttribute("books", booksService.findAllWithPagination(sortByYear, page, booksPerPage));

        return "books/showAll";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable(value = "id") int id, @ModelAttribute("person") Person person, Model model){
        model.addAttribute("book", booksService.findById(id));
        Person bookOwner = booksService.bookOwner(id);
        if(bookOwner != null)
            model.addAttribute("owner", bookOwner);
        else
            model.addAttribute("people", peopleService.findAll());
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
            booksService.create(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") int id, Model model){
        model.addAttribute("book", booksService.findById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}/edit")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors())
        return "books/edit";
        else
            booksService.update(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        booksService.delete(id);
        return "redirect:/books";
    }
    @PostMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        booksService.assign(person, id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        booksService.release(id);
        return "redirect:/books/" + id;
    }



}
