package fr.cegos.demospringsecurity.security;

import fr.cegos.demospringsecurity.bo.Membre;
import fr.cegos.demospringsecurity.service.MembreService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Je vais créer mon propre service d'authentification
 * afin de la réaliser à partir de la liste de mes membres
 * => il va falloir que je renvoie à Spring Security
 * une instance de classe qui implémente UserDetails (Utilisateur)
 * à partir d'un username qu'il aura récupéré (via la requête HTTP)
 * => à partir de cela Spring Sevurity se débrouille tout seul
 * (pas besoin de faire la comparaison des mots de passe etc...)
 */
@Service
@AllArgsConstructor
public class ServicePersonaliseAuthentification implements UserDetailsService {

    // je vais utiliser mon service de gestion des membres
    MembreService membreService;

    /**
     * Je dois spécifier à Spring Security
     * COMMENT il recupère un utilisateur au format UserDetails
     * à partir d'un username utilisé pour la connexion
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1 - je récupère le membre qui correspond à cet utilisateur
        Membre membre = membreService.getMembreByUsername(username);
        // si on n'a pas trouvé l'utilisateur, il faut lever une exception de type UsernameNotFoundException
        // afin de ne pas avoir d'erreur 500
        if (membre == null) {
            throw new UsernameNotFoundException(username);
        }

        // 2 - à partir de ce membre recupéré, je vais créer une instance
        // de classe Utilisateur (qui implémente UserDetails)
        return new Utilisateur(membre);
    }
}
