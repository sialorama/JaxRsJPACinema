package com.jaxrs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Acteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String photo;

        @ManyToMany(mappedBy = "acteurs")
        @JsonIgnore
        private Set<Film> films = new HashSet<>();


}
