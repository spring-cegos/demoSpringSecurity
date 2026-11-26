package fr.cegos.demospringsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security va chercher dans le contexte Spring
 * l'encodeur de mot de passe de type PasswordEncoder
 * qu'on aura défini (ici BCryptPasswordEncoder)
 */
@Configuration
public class PasswordEncoderConfiguration {
    @Bean // on définit un bean pour l'utilitaire d'encryption de mot de passe
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}