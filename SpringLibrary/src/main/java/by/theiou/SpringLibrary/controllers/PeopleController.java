package by.theiou.SpringLibrary.controllers;

import by.theiou.SpringLibrary.DAO.PersonDAO;
import by.theiou.SpringLibrary.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    PersonDAO personDAO;

    @GetMapping
    public String showAll(Model model){
        model.addAttribute("people", personDAO.showAll());
        return "people/showAll";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("books", personDAO.getOwnedBooks(id));
        return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/newPerson";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "people/newPerson";
        else
        personDAO.create(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String update(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "people/edit";
        else
        personDAO.update(person);
        return "redirect:/people";
    }


}
