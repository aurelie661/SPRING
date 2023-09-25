package com.example.poke_project.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.awt.*;

@Getter
@Data
@Builder
public class Pokemon {
    private String name;
    private Long id;
    private int nationalPokedexNumber;
    private List types;
    private List abilities;
    private String imageUrl;
    private double height;
    private double weight;
}
