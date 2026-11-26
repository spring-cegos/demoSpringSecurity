package fr.cegos.demospringsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ressourcePrivee")
public class RessourcePrivee {
    @GetMapping
    public String getRessource() {
        return "RessourcePrivee";
    }
}