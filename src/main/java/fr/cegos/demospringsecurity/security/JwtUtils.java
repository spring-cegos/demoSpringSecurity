package fr.cegos.demospringsecurity.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JwtUtils {
    //  @Value("${jwt.secret}")
    //  permet d'initialiser l'attribut jwtSecret
    //  à partir de la propriété jwt.secre de : application.properties
    @Value("${jwt.secret}")
    private String jwtSecret;

    /**
     * 1 : génération du token JWT à l'authentification
     */
    public String generateJwtToken(Authentication authentication) {
        Utilisateur userPrincipal = (Utilisateur) authentication.getPrincipal();
        return JWT.create().withClaim("username", userPrincipal.getUsername()).sign(Algorithm.HMAC256(jwtSecret));
    }

    /**
     * 2 : récupération du token JWT depuis la requête HTTP
     */
    public static String parseJwt(HttpServletRequest request) {
        // le token est récupéré à partir de l'entête de requête Authorization
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            // Par convention, il y a toujours "Bearer " qui est mis en préfixe du token
            return headerAuth.substring(7, headerAuth.length());
        }
        return null;
    }

    /**
     * 3 : vérification token OK (encodé avec bon secret)
     */
    public boolean validateJwtToken(String authToken) {
        try {
            JWT.require(Algorithm.HMAC256(jwtSecret)).build().verify(authToken);
            return true;
        } catch (Exception e) {
            System.out.println("error : " + e.getStackTrace());
        }
        return false;
    }

    /**
     * 4 : récupération du username depuis un token JWT
     */
    public String getUserNameFromJwtToken(String token) {
        return JWT.require(Algorithm.HMAC256(jwtSecret)).build().verify(token).getClaim("username").asString();
    }
}