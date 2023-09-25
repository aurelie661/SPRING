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

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/pokemon/{id}")
    public String getPokemon(@PathVariable String id, Model model) {
        Pokemon pokemon = restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon/" + id, Pokemon.class);
        model.addAttribute("pokemon", pokemon);
        pokeService.getById(Integer.parseInt(id));
        return "pokemon";
    }

}
