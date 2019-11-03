
package pl.polsl.lab1.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Bank entity
 * @author fabianokeke
 * @version 1.0
 */

@Entity
@Table(name = "bank")
@NamedQueries({
   @NamedQuery(name = "Bank.findAll", query = "SELECT b FROM Bank b")
})

/**
 * Bank class with all the parameters needed to create a new bank
 */
public class Bank implements Serializable {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
private Integer id;

@Column(name = "name")
private String name;

@Enumerated(EnumType.STRING)
private Location location;

/**
 * one to many relationship and cascade showing that if bank is deleted, then the customers goes also
 */
@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "bank")
    private List <Customer> customers;


   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        if(name!=null)
        return name;
        else return "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bank other = (Bank) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bank{" + "id=" + id + ", name=" + name + ", location=" + location + '}';
    }

}
