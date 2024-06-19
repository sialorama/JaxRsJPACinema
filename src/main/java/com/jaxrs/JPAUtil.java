package com.jaxrs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("cinemaPU");

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
