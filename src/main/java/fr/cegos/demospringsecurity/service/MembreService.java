package fr.cegos.demospringsecurity.service;

import fr.cegos.demospringsecurity.bo.Membre;

import java.util.List;

public interface MembreService {
    List<Membre> getMembres();
    Membre getMembreByUsername(String username);
    void  addMembre(Membre membre);
}
