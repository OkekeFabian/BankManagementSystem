/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.lab1.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.Expression;
import pl.polsl.lab1.utils.AbstractDAO;
import javax.persistence.criteria.Predicate;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pl.polsl.lab1.view.View;

/**
 * CustomerDAO class responsible for changes or transactions done in or to the customer class
 * @author fabianokeke
 * @version 1.0
 * CustomerDAO class responsible for changes or transactions done in or to the bank class
 */
public class CustomerDAO extends AbstractDAO<Customer> {
    
    View view = new View();
    /**
     * @param name: Finding a customer by using the name inputted by the user
     * @return the customer found
     */
    @Override
    public List<Customer> find(String name) {
        return connectionHolder.getEntityManager().createNamedQuery("Customer.findByName")
                .setParameter("name", name).getResultList();
    }
    
    /**
     * To find the customer by providing
     * @param id of the customer the user is trying to find
     * @return the customer found
     */
    public Customer findCustomerById(Integer id) {
        connectionHolder.getEntityManager().clear();
        
        return (Customer) (connectionHolder.getEntityManager().find(Customer.class, id));
        
    }
   
    /**
     * to get all the customers present in the database
     * @return all the customers present in the database
     */
    public List<Customer> getAllCustomers() {
        return connectionHolder.getEntityManager().createNamedQuery("Customer.getAll")
                .getResultList();
    }
    
    /**
     * method to get the list of customers
     * @param name of the customer
     * @param bank of the customer
     * @return 
     */
    public List<Customer> getCustomerList(String name, Bank bank) {
        
        return connectionHolder.getEntityManager().createQuery(getCriteriaQuery(name, bank)).getResultList();
    }

    /**
     * To delete customer
     * @param id of the customer to be deleted
     * @return true or false if the transaction was concluded or not
     */
    public boolean delete(int id) {
        EntityManager em = connectionHolder.getEntityManager();
        Customer cus = findCustomerById(id);
        if (cus != null) {
            em.getTransaction().begin();
            em.remove(cus);
            em.getTransaction().commit();
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * To update customers list
     * @param id of the customer to be updated
     * @param newFirstName of the customer to be updated
     * @param newLastName of the customer to be updated
     * @param bank of the customer to be updated
     * @return 
     */
    public boolean update(int id, String newFirstName, String newLastName, Bank bank) {
        EntityManager em = connectionHolder.getEntityManager();
        Customer customer = em.find(Customer.class, id);
        
        customer.setFirstName(newFirstName);
        customer.setLastName(newLastName);
        customer.setBirthDate(new Date());
        customer.setBank(bank);
        em.getTransaction().begin();
        em.merge(customer);
        em.getTransaction().commit();
        return true;
        
    }

    /**
     * To query the customer table
     * @param customerName name of the customer 
     * @param bank of the customer to be queried
     * @return the found customer
     */
    private CriteriaQuery<Customer> getCriteriaQuery(String customerName, Bank bank) {
        Expression expr; // refers to the attributes of entity class
        Root<Customer> queryRoot; // entity/table from which the selection is performed
        CriteriaQuery<Customer> queryDefinition; // query being built
        List<Predicate> predicates = new ArrayList<>(); // list of conditions in the where clause
        CriteriaBuilder builder = connectionHolder.getEntityManager().getCriteriaBuilder(); // creates predicates

        queryDefinition = builder.createQuery(Customer.class);
        // defines the from part of the query
        queryRoot = queryDefinition.from(Customer.class);
        // defines the select part of the query
        // at this point we have a query select s from Student s (select * from student in SQL)
        queryDefinition.select(queryRoot);
        if (customerName != null) {
            expr = queryRoot.get("firstName");
            // creates condition of the form s.name LIKE name
            predicates.add(builder.like(expr, "%" + customerName + "%"));
        }
        
        if (bank != null) {
            expr = queryRoot.get("bank");
            predicates.add(builder.equal(expr, bank));
        }
        // if there are any conditions defined
        if (!predicates.isEmpty()) {
            // build the where part in which we combine the conditions using AND operator
            queryDefinition.where(
                    builder.or(predicates.toArray(
                            new Predicate[predicates.size()])));
            
        }
        return queryDefinition;
    }
}
