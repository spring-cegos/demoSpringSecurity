package fr.cegos.demospringsecurity.controller;

import fr.cegos.demospringsecurity.bo.Membre;
import fr.cegos.demospringsecurity.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginRestController {
    @Autowired
    private JwtUtils jwtUtils; // ref vers notre class utilitaire de création de JWT
    @Autowired
    AuthenticationConfiguration authenticationConfiguration; // ref vers la classe d'authentification de Spring

    @PostMapping
    public String login(@RequestBody Membre membre) throws Exception {
        // 1 - On vérifie que l'authentification est bonne en utilisant l'authentication Manager de Spring
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(membre.getUsername(),
                membre.getPassword());
        Authentication authentication =
                authenticationConfiguration.getAuthenticationManager().authenticate(authenticationToken);
        // 2 - Si c'est bon, on met à jour le SecurityContext et on crée un token JWT pour les prochains appels
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return jwt;
    }
}