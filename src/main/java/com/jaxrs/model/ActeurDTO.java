package com.jaxrs.model;

import java.util.Set;

public class ActeurDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String photo;

    public ActeurDTO(Acteur acteur) {
        this.id = acteur.getId();
        this.nom = acteur.getNom();
        this.prenom = acteur.getPrenom();
        this.photo = acteur.getPhoto();
    }
    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }
}

