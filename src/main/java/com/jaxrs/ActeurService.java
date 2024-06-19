package com.jaxrs;


import com.jaxrs.model.Acteur;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/acteurs")
public class ActeurService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActeurs() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Acteur> acteurs;
        try {
            acteurs = em.createQuery("SELECT a FROM Acteur a", Acteur.class).getResultList();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving actors: " + e.getMessage())
                    .build();
        } finally {
            em.close();
        }
        return Response.ok(acteurs).build();
    }
}
