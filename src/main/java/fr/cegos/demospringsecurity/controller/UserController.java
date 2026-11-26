package fr.cegos.demospringsecurity.controller;

import fr.cegos.demospringsecurity.security.Utilisateur;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @GetMapping
    public String getConnectedUser(Authentication authentication) {
        Utilisateur utilisateurConnecte = (Utilisateur) authentication.getPrincipal();
        return "Membre connecté : " + utilisateurConnecte.getMembre();
    }
}