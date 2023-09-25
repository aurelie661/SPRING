package com.example.poke_project.controller;

import com.example.poke_project.entity.Pokemon;
import com.example.poke_project.service.PokeService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/pokemon")
public class PokemonRestController {
    private final PokeService pokeService;
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/api/v2/")
    public String home() {
        return "index";
    }

    @GetMapping("pokemon/{id}")
    public String getPokemon(@PathVariable int id, Model model) {

        Pokemon pokemon= pokeService.getById(id);
        model.addAttribute("pokemon", pokemon);

        return "pokemon";
    }

}
