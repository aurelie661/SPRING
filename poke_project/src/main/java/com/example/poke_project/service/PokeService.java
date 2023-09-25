package com.example.poke_project.service;

import com.example.poke_project.entity.Pokemon;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Primary
@RequiredArgsConstructor
public class PokeService {  private final RestTemplateBuilder builder;

    public Pokemon getById(int id) {
        RestTemplate restTemplate = builder.build();

        ResponseEntity<JsonNode> responseJson = restTemplate
                .getForEntity("pokemon/" + id, JsonNode.class);

        List<String> abilityNames = new ArrayList<>();

        int pokeFound = Objects.requireNonNull(responseJson.getBody()).get("id").asInt();

        responseJson.getBody().findPath("abilities").elements().forEachRemaining(a -> {
            abilityNames.add(a.findPath("ability").get("name").asText());
        });

        return Pokemon
                .builder()
                .abilities(null)
                .build();
    }
}
