package com.example.poke_project.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Primary
@RequiredArgsConstructor
public class PokeService {  private final RestTemplateBuilder builder;

    public ResponseEntity<JsonNode> getById(int id) {
        RestTemplate restTemplate = builder.build();

        ResponseEntity<JsonNode> responseEntity = restTemplate.getForEntity("pokemon/" + id, JsonNode.class);

        if (responseEntity.getBody() != null) {

            return responseEntity;
        }
        return null;
    }

}
