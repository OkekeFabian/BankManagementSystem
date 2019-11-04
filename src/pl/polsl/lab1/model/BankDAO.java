/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.lab1.model;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import pl.polsl.lab1.utils.AbstractDAO;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;

import javax.persistence.criteria.Root;

/**
 * BankDAO class responsible for changes or transactions done in or to the bank
 * class
 *
 * @author fabianokeke
 * @version 1.0
 *
 */
public class BankDAO extends AbstractDAO<Bank> {

    /**
     * Finding Bank by providing name
     *
     * @param name:Finding a bank by using the name inputted by the user
     * @return the bank(s) found after the search
     */
    @Override
    public List<Bank> find(String name) {
        return connectionHolder.getEntityManager().createNamedQuery("Bank.findByName")
                .setParameter("name", name).getResultList();
    }

    /**
     * Finding Bank by providing ID
     *
     * @param id:Finding bank by the id provided by the user
     * @return the bank(s) found after the search
     */

    public Bank findByBankId(Integer id) {
        connectionHolder.getEntityManager().clear();

        return (Bank) (connectionHolder.getEntityManager().find(Bank.class, id));

    }

    /**
     * To see the list of Banks in the database
     *
     * @return the bank list before and after changes are made
     */
    public List<Bank> getBankList() {
        return connectionHolder.getEntityManager().createNamedQuery("Bank.findAll").getResultList();

    }

    /**
     * Overloaded constructor of getting list of banks
     *
     * @param name: name of the bank that the user is trying to find
     * @param location:location of the bank that the user is trying to find
     * @return list of banks also when the user is trying to search and so has
     * to provide name and location
     *
     */
    public List<Bank> getBankList(String name, Location location) {

        return connectionHolder.getEntityManager().createQuery(getCriteriaQuery(name, location)).getResultList();
    }

    /**
     * To delete a bank by providing id
     *
     * @param id the user has to enter to delete the bank
     * @return true/false value if the transaction was concluded or not
     */
    public boolean delete(int id) {
        EntityManager em = connectionHolder.getEntityManager();
        Bank ba = findByBankId(id);
        if (ba != null) {
            em.getTransaction().begin();
            em.remove(ba);
            em.getTransaction().commit();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Updating bank by providing id and name
     *
     * @param id user providing id to be able to access and update the desired
     * bank
     * @param name providing the name of the bank to be updated
     * @return true/false value if the transaction was concluded or not
     * @throws Exception if bank doesn't exist
     */
    public boolean updateBank(int id, String name)
            throws Exception {
        EntityManager em = connectionHolder.getEntityManager();
        Bank bank;
        bank = em.find(Bank.class, id);
        if (bank != null) {
            bank.setName(name);
            em.getTransaction().begin();
            em.merge(bank);
            em.getTransaction().commit();
            return true;
        } else {
            throw new Exception("No bank entry with id = " + id + " was found in the database");
        }
    }

    /**
     * Criteria query responsible for finding Bank after providing name and location
     * 
     *
     * @param name providing the name of the bank to be queried
     * @param location: user providing location to be able to access and query
     * the desired bank
     * @return the bank found
     */
    private CriteriaQuery<Bank> getCriteriaQuery(String name, Location location) {
        Expression expr; // refers to the attributes of entity class
        Root<Bank> queryRoot; // entity/table from which the selection is performed
        CriteriaQuery<Bank> queryDefinition; // query being built
        List<Predicate> predicates = new ArrayList<>(); // list of conditions in the where clause

        CriteriaBuilder builder; // creates predicates
        builder = connectionHolder.getEntityManager().getCriteriaBuilder();

        queryDefinition = builder.createQuery(Bank.class);
        // defines the from part of the query
        queryRoot = queryDefinition.from(Bank.class);
        // defines the select part of the query
        // at this point we have a query select s from Student s (select * from student in SQL)
        queryDefinition.select(queryRoot);
        if (name != null) {
            // gets access to the field called name in the Student class
            expr = queryRoot.get("name");
            predicates.add(builder.like(expr, name));
        }

        if (location != null) {
            // gets access to the field called name in the Student class
            expr = queryRoot.get("location");
            // creates condition of the form s.average >= average
            predicates.add(builder.equal(expr, location));
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
