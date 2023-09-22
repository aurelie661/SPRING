package com.example.demo_path_request.controllers;

import com.example.demo_path_request.exeptions.ResourceNotFoundException;
import com.example.demo_path_request.models.PersonDTO;
import com.example.demo_path_request.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/persons") // http://localhost:8080/api/persons
public class PersonRestController {
    private final PersonService personService;

    @GetMapping("list") // GET http://localhost:8080/api/persons/list
    public List<PersonDTO> listPersons() {
        return personService.getPersons();
    }

    @GetMapping("details/{personId}") // GET http://localhost:8080/api/persons/details/XXXXX
    public ResponseEntity<Optional<PersonDTO>> getPersonById(@PathVariable("personId") UUID id) {
        Optional<PersonDTO> found = personService.getPersonById(id);

        if (found.isPresent()) {
            return ResponseEntity.ok(found);
        } else {
            throw new ResourceNotFoundException();
        }
    }


}
