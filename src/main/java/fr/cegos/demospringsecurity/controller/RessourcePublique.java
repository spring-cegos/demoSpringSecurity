package fr.cegos.demospringsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ressourcePublique")
public class RessourcePublique {
    @GetMapping
    public String getRessource() {
        return "RessourcePublique";
    }
}