package pl.polsl.lab1;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pl.polsl.lab1.model.Bank;
import pl.polsl.lab1.model.BankDAO;
import pl.polsl.lab1.model.Customer;
import pl.polsl.lab1.model.CustomerDAO;
import pl.polsl.lab1.model.Location;
import pl.polsl.lab1.view.View;


/**
 * 
 * @author fabianokeke
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory("LabPU");
        EntityManager em = emf.createEntityManager();

        BankDAO bankDAO = new BankDAO();
        CustomerDAO customerDAO = new CustomerDAO();

   
        Main main = new Main();
        View view = new View();
        int command = 0;

        /**
         * Switch for various transactions to be done in the program
         */
        do {
            view.options();
            try {
                command = view.readInt();

                switch (command) {
                    case 1: {

                        view.displayMessage("Create a new Bank");
                        bankDAO.create(view.createBank());
                        view.displayMessage("Bank created");
                        view.displayMessage("******************************************************");
                    }
                    break;

                    case 2: {

                        view.displayMessage("Add a new Customer");

                        customerDAO.create(main.createCustomer(view));

                        view.displayMessage("Customer created");
                        view.displayMessage("******************************************************");
                    }
                    break;

                    case 3: {

                        view.displayMessage("Updating a Bank");

                        view.displayMessage("Please enter an id of the bank you want to update");
                        int id = view.readInt();
                        Bank bank = bankDAO.findByBankId(id);
                        if (bank != null) {

                            view.displayMessage(bank.getName());
                            view.displayMessage("\nEnter a new name: \n");

                            String newName = view.readString();

                            if (newName.length() == 0) {
                                newName = bank.getName();
                            }

                            if (bankDAO.updateBank(id, newName)) {
                                view.displayMessage("SUccess");
                            } else {
                                view.displayMessage("E");
                            }

                            view.displayMessage("Bank Updated");
                            view.displayMessage("******************************************************");
                        }
                    }
                    break;

                    case 4: {
                        view.displayMessage("Updating a Customer");
                        int id = view.readInt();
                        Customer customer = customerDAO.findCustomerById(id);
                        if (customer != null) {

                            view.displayMessage("Current first name - " + customer.getFirstName());
                            view.displayMessage("\nEnter a new first name: \n");
                            String newFirstName = view.readString();

                            view.displayMessage("Current last name - " + customer.getLastName());
                            view.displayMessage("\nEnter a new last name: \n");
                            String newLastName = view.readString();

                            view.displayMessage("Current bank id - " + customer.getBank());
                            Bank bank = null;
                            while (true) {
                                view.displayMessage("\nEnter a new bank id: \n");
                                int newBankId = view.readInt();

                                bank = bankDAO.findByBankId(newBankId);
                                if (bank != null) {
                                    break;
                                } else {
                                    view.displayMessage("THe bank doesnt exist, try again...");
                                }
                            }
                            if (customerDAO.update(id, newFirstName, newLastName, bank)) {
                                view.displayMessage("Updated successfully");
                            } else {
                                view.displayMessage("The customer with such id doesnt exist");
                            }

                            view.displayMessage("Customer Updated");
                            view.displayMessage("******************************************************");
                        }
                    }
                    break;

                    case 5: {

                        view.displayMessage("Deleting Bank");
                        view.displayMessage("Enter an id of the bank you want to delete: ");
                        int idBank = view.readInt();
                        if (bankDAO.delete(idBank)) {
                            view.displayMessage("Deleted successfully");
                        } else {
                            view.displayMessage("Id doesnt exist");
                        }
                        view.displayMessage("Bank deleted");
                        view.displayMessage("******************************************************");
                    }
                    break;
                    case 6: {
                        view.displayMessage("Deleting Customer");
                        view.displayMessage("Enter an id of the customer you want to delete: ");
                        int idCustomer = view.readInt();
                        if (customerDAO.delete(idCustomer)) {
                            view.displayMessage("Deleted successfully");
                        } else {
                            view.displayMessage("Id doesnt exist");
                        }
                        view.displayMessage("Customer deleted");
                        view.displayMessage("******************************************************");
                    }
                    break;
                    case 7: {
                        view.displayMessage("Displaying all the banks");
                        List<Bank> listB = bankDAO.getBankList();
                        for (Bank c : listB) {
                            view.displayMessage(c.toString());
                        }
                        view.displayMessage("******************************************************");
                    }
                    break;

                    case 8: {
                        view.displayMessage("Displaying all the Customers");
                        List<Customer> listC = customerDAO.getAllCustomers();
                        for (Customer c : listC) {
                            view.displayMessage(c.toString());
                        }
                        view.displayMessage("******************************************************");
                    }
                    break;
                    case 9: {
                        view.displayMessage("Find customer by id\nEnter an id");
                        int findId = view.readInt();
                        Customer cs = customerDAO.findCustomerById(findId);
                        if (cs != null) {
                            view.displayMessage(cs.toString());
                        } else {
                            view.displayMessage("Customer not found");
                        }
                        view.displayMessage("******************************************************");
                    }
                    break;
                    case 10: {
                        view.displayMessage("Find bank by id\nEnter an id");
                        int findIdC = view.readInt();
                        Bank bk = bankDAO.findByBankId(findIdC);
                        if (bk != null) {
                            view.displayMessage(bk.toString());
                        } else {
                            view.displayMessage("Customer not found");
                        }
                        view.displayMessage("******************************************************");
                    }
                    break;
                    case 11: {
                        view.displayMessage("Find bank by providing name and location");
                        try {
                            bankDAO.getBankList(view.readString(), Location.valueOf(view.readString().toUpperCase()));
                        } catch (IllegalArgumentException e) {

                            view.displayMessage("Enter right data");
                        }
                    }
                    break;
                    case 12: {
                        view.displayMessage("Find Customer by providing firstName and bank Id ");

                        customerDAO.getCustomerList(view.readString(), bankDAO.findByBankId(view.readInt()));
                    }
                    break;

                }
            } catch (InputMismatchException e) {
                view.displayMessage("wrong input ,program is terminated");
                command = 0;
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (command != 0);
    }

    /**
     * Method for creating a new customer after accepting the ID
     * @param view: object of View Class so as to be able to enter and also print out messages
     * @return new customer that was created
     */
    public Customer createCustomer(View view) {
        Customer customer = new Customer();
        view.displayMessage("Enter Customer First Name: ");
        customer.setFirstName(view.readString());
       view.displayMessage("Enter Customer Last Name: ");
        customer.setLastName(view.readString());
        customer.setBirthDate(new Date());
        BankDAO bd = new BankDAO();
        Bank bank = null;
        do {
             view.displayMessage("What is the ID of the bank you want Customer to join ");
            int bankId = view.readInt();
            bank = bd.find(Bank.class, bankId);
            if (bank != null) {
                customer.setBank(bank);

                 view.displayMessage(", bank= "
                        + bank.getName()
                        + "Customer{"
                        + "id="
                        + customer.getId()
                        + ", firstName="
                        + customer.getFirstName()
                        + ", lastName="
                        + customer.getLastName()
                        + ", birthDate="
                        + customer.getBirthDate()
                        + ", bank= "
                        + customer.getBank()
                );
            }
        } while (bank == null);
        return customer;
    }

}


