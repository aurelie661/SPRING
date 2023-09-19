package com.example.exercice_01.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/base")
public class BaseController {
    @GetMapping("1-param")
    public String getOneParam(Model model){
        model.addAttribute("paramA", "John DUPONT");

        return "1-param";
    }
}
