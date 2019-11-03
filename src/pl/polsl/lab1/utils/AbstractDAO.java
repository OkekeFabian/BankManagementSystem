
package pl.polsl.lab1.utils ;

import java.util.List;

/**
 * In charge of every transaction done to the table..
 * @author fabianokeke
 * @param <T> holding the transactions
 */
public abstract class AbstractDAO<T> {
    
    
    protected ConnectionHolder connectionHolder = ConnectionHolder.getInstance();
    
    protected void startTransaction(){
        connectionHolder.getEntityManager().getTransaction().begin();
    }
    
    protected void finishTransaction (boolean success){
        if (success) {
            connectionHolder.getEntityManager().getTransaction().commit();
        }else{
            connectionHolder.getEntityManager().getTransaction().rollback();
        }
    }
    
    /**
     * To create a transaction
     * @param entity to determine if an entity is created
     */
    public void create (T entity){
        startTransaction();
        connectionHolder.getEntityManager().persist(entity);
        finishTransaction(true);
    }
    
    /**
     * 
     * @param clazz holding the entities
     * @param id of the entity
     * @return 
     */
    public T find(Class<T> clazz, Integer id){
        return connectionHolder.getEntityManager().find(clazz, id);
    }
    
    /**
     * 
     * @param name name of customer
     * @return found name
     */
    public abstract List<T> find(String name);

}
