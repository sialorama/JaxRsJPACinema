package com.jaxrs.model;

import jakarta.persistence.*;

@Entity
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titre;
    private String description;
    private String affiche;

    public Film(){}

    public Film(int id, String titre, String description, String affiche){
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.affiche = affiche;

    }
    public Film( String nom, String prenom, String photo) {
        this.titre = nom;
        this.description = prenom;
        this.affiche = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = description;
    }

    public String getAffiche() {
        return affiche;
    }

    public void setAffiche(String affiche) {
        affiche = affiche;
    }

}
