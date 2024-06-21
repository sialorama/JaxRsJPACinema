package com.jaxrs.service;


import com.jaxrs.JPAUtil;
import com.jaxrs.model.Acteur;
import com.jaxrs.model.Film;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Set;

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
    public Set<Acteur> getActeursByFilm(Long filmId) throws Exception {
        EntityManager em = JPAUtil.getEntityManager();
        Set<Acteur> acteurs = null;
        try {
            // Démarrage de la transaction
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            // Récupération du film
            Film film = em.find(Film.class, filmId);
            if (film == null) {
                throw new Exception("Film not found");
            }

            // Chargement de la liste des acteurs
            film.getActeurs().size(); // Force l'initialisation de la collection
            acteurs = film.getActeurs();

            // Validation de la transaction
            transaction.commit();
        } catch (Exception e) {
            // Si une exception survient, annuler la transaction
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return acteurs;
    }
}