package com.jaxrs.model;


import com.jaxrs.JPAUtil;
import jakarta.persistence.EntityManager;

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
    public Set<Acteur> getActeursByFilm(Long filmId) {
        EntityManager em = JPAUtil.getEntityManager();
        Film film = em.find(Film.class, filmId);
        if (film != null) {
            // Assurez-vous que la collection d'acteurs est initialisée
            film.getActeurs().size(); // Force l'initialisation
            return film.getActeurs();
        }
        return null;
    }
}
