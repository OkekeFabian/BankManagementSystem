/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.lab1.utils;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *In charge of the connections needed for anything to be done to the database
 * @author fabianokeke
 * version 1.o
 */
public class ConnectionHolder {
     /**
     * Instance of ConnectionHolder object.
     */
    private static final ConnectionHolder instance = new ConnectionHolder();
    /**
     * Persistence unit name as a string.
     */
    private String persistenceUnitName;
    /**
     * Entity manager object.
     */
    private EntityManager entityManager;
    /**
     * Persistence unit names value.
     */
    private static final String DEFAULT_PU = "LabPU";
    
    
    private ConnectionHolder (){
    }
    
    public static ConnectionHolder getInstance(){
        return instance;
    }
    
    public EntityManager getEntityManager(){
        if (entityManager == null) {
            if (persistenceUnitName == null) {
                persistenceUnitName = DEFAULT_PU;                
            }
            entityManager = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
        }
        return entityManager;
    }
    
    public void closeEntityManager(){
        if (entityManager != null) {
            entityManager.close();
            entityManager = null;
        }
    }
    /**
     * 
     * @param persistenceUnitName the persistence name of the project
     */
    public void setPersistenceUnitName(String persistenceUnitName) {
        this.persistenceUnitName = persistenceUnitName;
    }  
    
}
