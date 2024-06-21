package com.jaxrs.model;

import com.jaxrs.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

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
    // Get Films by Acteur
    /*public List<Film> getFilmsByActeur(int acteurId) throws Exception {
        EntityManager em = JPAUtil.getEntityManager();
        List<Film> films = null;

        try {
            Acteur acteur = em.find(Acteur.class, acteurId);
            if (acteur == null) {
                throw new NoResultException("Acteur with ID " + acteurId + " not found.");
            }
            // Load films associated with the actor
            films = acteur.getFilms();
            films.size(); // Force initialization of lazy-loaded collection
        } finally {
            em.close();
        }

        return films;
    }*/
}
