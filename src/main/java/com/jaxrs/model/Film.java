package com.jaxrs.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Film {
    // attributs et annotations
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private String affiche;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "acteur_film",
            joinColumns = @JoinColumn(name = "acteur_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    @JsonBackReference
    private Set<Acteur> acteurs = new HashSet<>();

    // Getters and Setters
    public Long getId() { return id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getAffiche() {
        return affiche;
    }
    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Set<Acteur> getActeurs() { return acteurs; }
    public void setActeurs(Set<Acteur> acteurs) { this.acteurs = acteurs; }
}
