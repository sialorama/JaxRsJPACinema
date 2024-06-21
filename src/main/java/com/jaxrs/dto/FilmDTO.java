package com.jaxrs.model;

public class FilmDTO {
    private Long id;
    private String titre;
    private String description;
    private String affiche;

    public void FilmDTO(Film film) {
        this.id = film.getId();
        this.titre = film.getTitre();
        this.description = film.getDescription();
        this.affiche = film.getAffiche();
    }

    // Constructeurs, getters et setters
    public Long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAffiche() {
        return affiche;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }
}