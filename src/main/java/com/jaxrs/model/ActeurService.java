package com.jaxrs.model;

import com.jaxrs.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Set;


public class ActeurService {

    public List<Acteur> getAllActeurs() {
        EntityManager em = JPAUtil.getEntityManager();
        return em.createQuery("FROM Acteur", Acteur.class).getResultList();
    }

    public Acteur getActeurById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        return em.find(Acteur.class, id);
    }

    public Acteur createActeur(Acteur acteur) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(acteur);
        em.getTransaction().commit();
        return acteur;
    }

    public Acteur addFilmToActeur(Long acteurId, Long filmId) {
        EntityManager em = JPAUtil.getEntityManager();
        Acteur acteur = em.find(Acteur.class, acteurId);
        Film film = em.find(Film.class, filmId);
        if (acteur != null && film != null) {
            em.getTransaction().begin();
            acteur.getFilms().add(film);
            film.getActeurs().add(acteur);
            em.getTransaction().commit();
        }
        return acteur;
    }
    // Get Acteurs by Film
    public List<Acteur> getActeursByFilm(Long filmId) {
        EntityManager em = JPAUtil.getEntityManager();
        Film film = em.find(Film.class, filmId);
        if (film != null) {
            return film.getActeurs().stream().toList();
        }
        return null; // Ou gérer l'erreur selon votre choix
    }
}
