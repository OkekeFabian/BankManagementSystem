
package pl.polsl.lab1.utils ;

import java.util.List;

/**
 * In charge of every transaction done to the table and it is extended by BankDAO and CustomerDAO..
 * @author fabianokeke
 * @version 1.0
 * @param <T> holding the transactions
 */
public abstract class AbstractDAO<T> {
    
    /**
     * creating an instance of connection Holder
     */
    protected ConnectionHolder connectionHolder = ConnectionHolder.getInstance();
    
    /**
     * To start a transaction
     */
    protected void startTransaction(){
        connectionHolder.getEntityManager().getTransaction().begin();
    }
    
    /**
     * To finish and commit every transaction after changes
     * @param success if the transaction was successful
     */
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
     * To find in the class of Customer or bank by providing id
     * @param clazz holding the entities
     * @param id of the entity
     * @return  the found class members
     */
    public T find(Class<T> clazz, Integer id){
        return connectionHolder.getEntityManager().find(clazz, id);
    }
    
    /**
     * To find entity
     * @param name name of members of entity
     * @return found members
     */
    public abstract List<T> find(String name);

}
