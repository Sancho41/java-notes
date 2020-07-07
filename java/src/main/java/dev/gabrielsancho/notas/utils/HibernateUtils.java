package dev.gabrielsancho.notas.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtils {

    private static final String persistenceUnitName = "notas";
    private static EntityManagerFactory NOTAS_ENTITY_MANEGER_FACTORY;

    public static EntityManager createEntityManager(){
        if(NOTAS_ENTITY_MANEGER_FACTORY == null){
            generateEntityManegerFactory();
        }
        return NOTAS_ENTITY_MANEGER_FACTORY.createEntityManager();
    }

    private static void generateEntityManegerFactory(){
        NOTAS_ENTITY_MANEGER_FACTORY = Persistence.createEntityManagerFactory(persistenceUnitName);
    }

}
