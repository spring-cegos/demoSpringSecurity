package fr.cegos.demospringsecurity.service;

import fr.cegos.demospringsecurity.bo.Membre;
import fr.cegos.demospringsecurity.security.Utilisateur;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Profile("dev")
@Service
class MembreServiceDevImpl implements MembreService {

    /**
     * Pour cette implémentation on gère dans une liste Java
     * TODO : faire uns service pour gérer cela en base de donnée
     * @return
     */
    List<Membre> membres = new ArrayList<Membre>();

    public MembreServiceDevImpl(PasswordEncoder passwordEncoder) {
        Membre m1 = new Membre("Cyril", "Mace", "membre1", passwordEncoder.encode("membre1"), false);
        Membre m2 = new Membre("Eric", "Legrand", "membre2", passwordEncoder.encode("membre2"), false);
        Membre m3 = new Membre("Patricia", "Martin","admin", passwordEncoder.encode("admin"), true);
        membres.add(m1);
        membres.add(m2);
        membres.add(m3);
    }

    @Override
    public List<Membre> getMembres() {
        return membres;
    }

    @Override
    public Membre getMembreByUsername(String username) {
        for (Membre membre : membres) {
            if (membre.getUsername().equals(username)) {
                return membre;
            }
        }
        return null;
    }

    @Override
    public void addMembre(Membre membre) {
        membres.add(membre);
    }
}
