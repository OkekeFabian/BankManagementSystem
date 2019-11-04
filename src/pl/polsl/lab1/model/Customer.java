package pl.polsl.lab1.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Customer entity holding all fields needed to create a customer in the database
 *
 * @author fabianokeke
 * @version 1.0
 */
@Entity
@Table(name = "customer")
/**
 * Query for getting all the customers
 */
@NamedQueries({
    @NamedQuery(name = "Customer.getAll", query = "SELECT s FROM Customer s")

})

/**
 * Customer Class holding all parameters needed in creating a new customer
 */
public class Customer implements Serializable {

    /**
     * the id of the customer created (Primary Key)
     */
    @Column(name = "customer_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The first name of the customer created
     */
    @Column(name = "firstname", nullable = false, length = 50)
    private String firstName;

    /**
     * The Last name of the customer created
     */
    @Column(name = "lastname", nullable = false, length = 50)
    private String lastName;

    /**
     * The birth date of the customer
     */
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    /**
     * Customer bank column
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;

    /**
     * customer's Id getter
     *
     * @return id of the customer
     */
    public Integer getId() {
        return id;
    }

    /**
     * Customer's id setter
     *
     * @param id new id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Customer's Name getter
     *
     * @return customer's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * customer's Name setter
     *
     * @param firstName new First name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Customer's first Name getter
     *
     * @return customer's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * customer's last Name setter
     *
     * @param lastName new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Customer's birth Date getter
     *
     * @return Birth date of the customer
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * To set the customers birth date
     *
     * @param birthDate of the customer
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * override of base hashCode() method
     *
     * @return int hash code
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * override of base equals() method
     *
     * @param obj other object to compare this object with
     * @return true if objects are equal or false if not
     */
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
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * customer's Bank getter
     *
     * @return Employee's department
     */
    public Bank getBank() {
        return bank;
    }

    /**
     * Employee's Department setter
     *
     * @param bank new bank
     */
    public void setBank(Bank bank) {
        this.bank = bank;
    }

    /**
     * Override of toString() method
     *
     * @return a composite String containing all the fields
     */
    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate + ", bank=" + bank + '}';
    }

}
