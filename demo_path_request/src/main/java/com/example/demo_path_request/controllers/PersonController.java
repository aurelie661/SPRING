package com.example.demo_path_request.controllers;

import com.example.demo_path_request.exeptions.ResourceNotFoundException;
import com.example.demo_path_request.models.PersonDTO;
import com.example.demo_path_request.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
