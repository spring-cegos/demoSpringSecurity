package fr.cegos.demospringsecurity.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe METIER de notre application
 * Je vais gérer la liste de mes membres dans un service
 * avec à terme une base de donnée
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Membre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String prenom;
    private String nom;
    private String username;
    private String password;
    private Boolean admin;

    public Membre(String prenom, String nom, String username, String password, Boolean admin) {
        this.prenom = prenom;
        this.nom = nom;
        this.username = username;
        this.password = password;
        this.admin = admin;
    }
}