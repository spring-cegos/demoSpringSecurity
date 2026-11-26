package fr.cegos.demospringsecurity.service;

import fr.cegos.demospringsecurity.bo.Membre;
import fr.cegos.demospringsecurity.repository.MembreRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Profile("jpa")
@Service
class MembreServiceJpaImpl implements MembreService {

    private MembreRepository membreRepository;

    public MembreServiceJpaImpl(MembreRepository membreRepository, PasswordEncoder passwordEncoder) {
        this.membreRepository = membreRepository;
        if (membreRepository.findAll().isEmpty()) {
            // on initialise la base si aucun membres
            Membre m1 = new Membre("Cyril", "Mace", "cmace", passwordEncoder.encode("cm"), true);
            Membre m2 = new Membre("Eric", "Legrand", "elegrand", passwordEncoder.encode("elegrand"), false);
            Membre m3 = new Membre("Patricia", "Martin","pmartin", passwordEncoder.encode("pmartin"), true);
            membreRepository.save(m1);
            membreRepository.save(m2);
            membreRepository.save(m3);
        }
    }

    @Override
    public List<Membre> getMembres() {
        return membreRepository.findAll();
    }

    @Override
    public Membre getMembreByUsername(String username) {
        // on utilise un JPA Query Method pour récupérer le membre avec le suername donné
        return membreRepository.findFirstByUsername(username);
    }

    @Override
    public void addMembre(Membre membre) {
        membreRepository.save(membre);
    }
}
