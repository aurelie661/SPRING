package com.example.demo_path_request.controllers;


import com.example.demo_path_request.models.PersonDTO;
import com.example.demo_path_request.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/persons")
@RestController
@RequiredArgsConstructor
public class PersonRestController {
    private final PersonService personService;

    @GetMapping("list")
    public List<PersonDTO> listPersons() {
        return personService.getPersons();
    }
}
