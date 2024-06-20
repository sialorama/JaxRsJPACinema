package com.jaxrs.model;

public class ActeurDTO {
    private Long id;
    private String nom;
    private String prenom;

    public ActeurDTO(Acteur acteur) {
        this.id = acteur.getId();
        this.nom = acteur.getNom();
        this.prenom = acteur.getPrenom();
    }

    // Constructeurs, getters et setters

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

}
