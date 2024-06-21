package com.jaxrs.service;


import com.jaxrs.JPAUtil;
import com.jaxrs.model.Acteur;
import com.jaxrs.model.Film;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class FilmService {

    public List<Film> getAllFilms() {
        EntityManager em = JPAUtil.getEntityManager();
        return em.createQuery("FROM Film", Film.class).getResultList();
    }

    public Film getFilmById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        return em.find(Film.class, id);
    }

    public Film createFilm(Film film) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(film);
        em.getTransaction().commit();
        return film;
    }
    // Methde getActeursByFilm
    public List<Acteur> getActeursByFilm(Long filmId) throws Exception {
        EntityManager em = JPAUtil.getEntityManager();
        List<Acteur> acteurs = null;
        try {
            // Démarrage de la transaction
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            // Récupération du film
            Film film = em.find(Film.class, filmId);
            if (film == null) {
                throw new Exception("Film not found");
            }
            // Conversion du Set en List
            acteurs.addAll(film.getActeurs());
            // Validation de la transaction
            transaction.commit();
        } catch (Exception e) {
            // Si une exception survient, annuler la transaction
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e; // Renvoyer l'exception pour traitement ultérieur
        } finally {
            em.close(); // Fermer l'EntityManager
        }
        return acteurs;
    }
}
