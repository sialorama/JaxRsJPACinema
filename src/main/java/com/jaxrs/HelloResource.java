package com.jaxrs;


import com.jaxrs.model.Acteur;
import com.jaxrs.model.Film;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/hello")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
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
}