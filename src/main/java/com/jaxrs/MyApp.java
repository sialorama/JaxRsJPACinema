package com.jaxrs;


import com.jaxrs.model.Acteur;
import com.jaxrs.model.Film;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/myapp")
public class MyApp {
    @GET
    @Produces("text/plain")

    public String hello() {
        return "Bienvenue à mon API Cinema";
    }

    @GET
    @Path("/acteurs")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Acteur> getActeurs() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Acteur> acteurs = em.createQuery("from Acteur", Acteur.class).getResultList();
        return acteurs;
    }

    @GET
    @Path("/films")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Film> getFilms() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Film> films = em.createQuery("from Film", Film.class).getResultList();
        return films;
    }

    // method getActeurById
    @GET
    @Path("/getActeurById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActeurById(@PathParam("id") int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Acteur acteur = em.find(Acteur.class, id);
            if (acteur == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Acteur with ID " + id + " not found")
                        .build();
            }
            return Response.ok(acteur).build();
        } catch (PersistenceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving product")
                    .build();
        }
    }

    // method getActeurById
    @GET
    @Path("/getFilmById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilmById(@PathParam("id") int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Film film = em.find(Film.class, id);
            if (film == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Film with ID " + id + " not found")
                        .build();
            }
            return Response.ok(film).build();
        } catch (PersistenceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving product")
                    .build();
        }
    }
    // method addActeur
    @POST
    @Path("/addActeur")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addActeur(Acteur acteur) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(acteur);
            em.getTransaction().commit();
            return Response.status(Response.Status.CREATED)
                    .entity(acteur)
                    .build();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de l'ajout de l'acteur")
                    .build();
        }
    }
}