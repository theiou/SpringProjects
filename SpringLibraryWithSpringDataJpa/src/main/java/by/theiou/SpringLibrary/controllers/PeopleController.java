package by.theiou.SpringLibrary.controllers;

import by.theiou.SpringLibrary.DAO.PersonDAO;
import by.theiou.SpringLibrary.models.Person;
import by.theiou.SpringLibrary.services.PeopleService;
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
    PeopleService peopleService;

    @GetMapping
    public String showAll(Model model){
        model.addAttribute("people", peopleService.findAll());
        return "people/showAll";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.findById(id));
        model.addAttribute("books", peopleService.getOwnedBooks(id));
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
            peopleService.create(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        peopleService.delete(id);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String update(Model model, @PathVariable("id") int id){
        model.addAttribute("person", peopleService.findById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "people/edit";
        else
            peopleService.update(person);
        return "redirect:/people";
    }


}
