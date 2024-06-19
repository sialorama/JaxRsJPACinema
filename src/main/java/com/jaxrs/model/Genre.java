package com.jaxrs.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String genre;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Film> films = new ArrayList<Film>();

    // Constructors
    public Genre(){}

    public Genre(String genre){
        this.genre = genre;
    }

    // Getters and Setters

    public int getId() {

        return Id;
    }

    public String getGenre() {

        return genre;
    }

    public void setGenre(String genre) {

        this.genre = genre;
    }
}
