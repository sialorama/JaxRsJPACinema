package com.jaxrs.dto;

public class FilmDTO {
    private String titre;
    private String description;
    private String affiche;

    // Constructeurs
    public FilmDTO() {}

    public void FilmDTO(String titre, String description, String affiche) {
        this.titre = titre;
        this.description = description;
        this.affiche = affiche;
    }
    // Constructeurs, getters et setters
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