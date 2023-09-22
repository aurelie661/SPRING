package com.example.demo_path_request.controllers;

import com.example.demo_path_request.exeptions.ResourceNotFoundException;
import com.example.demo_path_request.models.PersonDTO;
import com.example.demo_path_request.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.UUID;

@RequestMapping("/persons")
@Controller
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping("list")
    public String listPersons(Model model) {
        model.addAttribute("personsList",personService.getPersons());
        return "views/list";
    }

    @GetMapping("{personId}")
    public String getPersonById(@PathVariable("personId")UUID id,Model model){
        Optional<PersonDTO> personFound = personService.getPersonById(id);

        if(personFound.isPresent()){
           model.addAttribute("person", personFound.get());
           model.addAttribute("mode", "details");
           return "views/form";
        }
        throw new ResourceNotFoundException();
    }

    @GetMapping("add")
    public String addPerson(Model model){
        model.addAttribute("person", PersonDTO.builder().build());
        model.addAttribute("mode", "add");

        return "views/form";
    }

    @PostMapping("add")
    public String addPersonHandler(PersonDTO newPerson){
        personService.addPerson(newPerson);

        return "redirect:/persons/list";
    }

    @GetMapping("delete/{personId}")
    public String deletePersonById(@PathVariable("personId") UUID id,Model model){
        Optional<PersonDTO> personFound =personService.getPersonById(id);

        if(personFound.isPresent()){
            model.addAttribute("person", personFound.get());
            model.addAttribute("mode", "delete");
            return "views/form";
        }
        throw new ResourceNotFoundException();
    }

    @PostMapping("delete/{personId}")
    public String deletePersonHandler(@PathVariable("personId")  UUID id){
        personService.deletePerson(id);
        return "redirect:/persons/list";
    }

    @GetMapping("edit/{personId}")
    public String editPerson(@PathVariable("personId") UUID id, Model model){

        Optional<PersonDTO> personDTOOptional = personService.getPersonById(id);

        if(personDTOOptional.isPresent()){
            model.addAttribute("person", personDTOOptional.get());
            model.addAttribute("mode", "edit");
            return "views/form";
        }
        throw new ResourceNotFoundException();
    }

    @PostMapping("edit/{personId}")
    public String editPersonHandler(@PathVariable("personId") UUID id, PersonDTO personDTO){

        personService.editPerson(id,personDTO);

        return "redirect:/persons/list";
    }
}
