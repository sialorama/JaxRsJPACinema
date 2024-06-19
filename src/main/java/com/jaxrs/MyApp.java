package com.jaxrs;


import com.jaxrs.model.Acteur;
import com.jaxrs.model.Film;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.annotation.XmlElement;

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
        List<Acteur> acteurs = em.createQuery("from Acteur",Acteur.class).getResultList() ;
        return acteurs;
    }
    @GET
    @Path("/films")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Film> getFilms() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Film> films = em.createQuery("from Film", Film.class).getResultList() ;
        return films;
    }
    // method getActeurById
    @GET
    @Path("/getById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActeurById(@PathParam("id") int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Acteur acteur = em.find(Acteur.class, id);
            if (acteur == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Product with ID " + id + " not found")
                        .build();
            }
            return Response.ok(acteur).build();
        } catch (PersistenceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving product")
                    .build();
        }
    }
        @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addActeur(Acteur acteur) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(acteur);
            em.getTransaction().commit();
            return Response.ok("Acteur ajouté avec ID : " + acteur.getId()).build();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Échec de l'ajout de l'acteur").build();
        } finally {
            em.close();
        }
    }
}