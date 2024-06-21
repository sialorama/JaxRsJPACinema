package com.jaxrs.service;

import com.jaxrs.JPAUtil;
import com.jaxrs.dto.ActeurDTO;
import com.jaxrs.model.Acteur;
import com.jaxrs.model.Film;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;


public class ActeurService {

    public List<Acteur> getAllActeurs() {
        EntityManager em = JPAUtil.getEntityManager();
        return em.createQuery("FROM Acteur", Acteur.class).getResultList();
    }

    public Acteur getActeurById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        return em.find(Acteur.class, id);
    }

    public Acteur addActeur(ActeurDTO acteurDTO) throws Exception {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Acteur acteur = new Acteur();
            acteur.setNom(acteurDTO.getNom());
            acteur.setPrenom(acteurDTO.getPrenom());
            acteur.setPhoto(acteurDTO.getPhoto());

            em.persist(acteur);
            tx.commit();

            return acteur;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    // Add film to acteur
    public void addFilmToActeur(Long acteurId, Long filmId) throws Exception {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            // Récupération de l'acteur
            Acteur acteur = em.find(Acteur.class, acteurId);
            if (acteur == null) {
                throw new Exception("Acteur not found");
            }
            // Récupération du film
            Film film = em.find(Film.class, filmId);
            if (film == null) {
                throw new Exception("Film not found");
            }
            // Ajout du film à l'acteur
            acteur.getFilms().add(film);
            film.getActeurs().add(acteur);
            // Synchroniser les deux entités
            em.merge(acteur);
            em.merge(film);

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }
}
