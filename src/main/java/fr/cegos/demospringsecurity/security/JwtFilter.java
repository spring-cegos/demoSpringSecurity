package fr.cegos.demospringsecurity.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private JwtUtils jwtUtils;
    private ServicePersonaliseAuthentification gestionPersoUtilisateurs;

    /**
     * Méthode appelée avant la verification de Spring Security
     * pour vérifier le JWT et metre à jour le Contexte de sécurité
     **/
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws
            ServletException, IOException {
        try {
            // 1 - je récupère le token JWT depuis l'entête de ma requête HTTP
            String jwt = jwtUtils.parseJwt(request);

            // 2 - je vérifie que le token JWT a bien été créé par moi (avec le bon secret)
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                // Si le token a été validé, PAS BESOIN DE FAIRE DES VERIFICATIONS SUPPLEMENTAIRES
                // je pars du principe qu'un token JWT correct = utilisateur authentifié
                // ici : on pourrait rajouter vérification sur l'expiration du token

                // 3 - on récupère le nom d'utilisateur à partir du tojen JWT
                String username = jwtUtils.getUserNameFromJwtToken(jwt);

                // 4 - On met à jour le contexte Spring Security avec les infos de connexion de l'utilisateur
                // note : pas de verification supplémentaire du mot de passe, on fait confiance si le JWT est valide
                UserDetails userDetails = gestionPersoUtilisateurs.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // MAJ du contexte Spring Security
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilterAsyncDispatch() {
        return false;
    }

    @Override
    protected boolean shouldNotFilterErrorDispatch() {
        return false;
    }
}