package fr.cegos.demospringsecurity.security;

import fr.cegos.demospringsecurity.bo.Membre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Classe TECHNIQUE qui est utilisée par Spring Security
 * pour gérer ses utilisateurs
 * Doit implémenter toute une série de méthodes
 * On la construit comme un "wrapper" autour de notre classe métier Membre
 */
@Data
@AllArgsConstructor // (permet de faire : new Utilisateur(membre))
@NoArgsConstructor
public class Utilisateur implements UserDetails {

    private Membre membre;

    /**
     * comment Spring Boot récupère les permissions à partir d'une instance d'Utilisateur
     * => à partir du membre
     * Une permission (Authority) est un peu différente d'un role
     * On a l'équivalence : permission ROLE_XXX = role XXX
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // si notre membre possède l'attribut admin à TRUE
        if (membre.getAdmin()) {
            // alors on retourne la permission ROLE_admin = role "admin"
            return List.of(new SimpleGrantedAuthority("ROLE_admin"));
        }
        // sinon on retourne la permission ROLE_user = role "user"
        return List.of(new SimpleGrantedAuthority("ROLE_user"));
    }

    /**
     * comment Spring Boot récupère le mot de passe à partir d'une instance d'Utilisateur
     * => à partir du membre
     */
    @Override
    public @Nullable String getPassword() {
        return this.membre.getPassword();
    }

    /**
     * comment Spring Boot récupère le mot de passe à partir d'une instance d'Utilisateur
     * => à partir du membre
     */
    @Override
    public String getUsername() {
        return this.membre.getUsername();
    }
}
