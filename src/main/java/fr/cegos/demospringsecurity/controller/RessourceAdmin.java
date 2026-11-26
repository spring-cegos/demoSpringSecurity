package fr.cegos.demospringsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ressourceAdmin")
public class RessourceAdmin {
    @GetMapping
    public String getRessource() {
        return "RessourceAdmin";
    }
}