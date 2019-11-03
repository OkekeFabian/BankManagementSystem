/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.lab1.view;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import pl.polsl.lab1.model.Bank;
import pl.polsl.lab1.model.BankDAO;
import pl.polsl.lab1.model.Customer;
import pl.polsl.lab1.model.Location;

/**
 * In charge of the input and output in the project
 * @author fabianokeke
 * @version 1.0
 */
public class View {
    
    /**
     * Instructs the user on what to enter about the instructions and transactions available to him
     */
    public void nextTransaction() {
        System.out.println ( "***************************" );
        System.out.println ( "Please enter a number from 1 to 5 to proceed to a transaction" );
    }

    /**
     * Options available for the user to select
     */
    public void options() {
        System.out.println ( "Main Menu\n 1.Add a Bank \n 2.Add a customer \n 3.update bank\n 4.update Customer\n 5.Delete a bank.\n 6.delete a customer"
                + "\n 7.Display Banks \n 8.Display Customers \n 9. Query Bank Table \n 10.Query Customer Table \n 11.Query Bank table using Bank name or iD \n 12.Query Customer Table by providind customer first name or Bank Id" );
        nextTransaction ();
    }
/**
 * 
 * @return integer entered by the user
 */
    public int readInt() {
Scanner scanner = new Scanner ( System.in );
        while(true){
            try {
                return scanner.nextInt ();
            } catch (InputMismatchException e){
                System.out.println ("Please enter an Integer");
                scanner.next();
                }
            }

    }

    /**
     * Read string used by the user to decide account number and name
     *
     * @return int  value
     * @throws InputMismatchException thrown if the given name or account number entered is not a string
     */
    public String readString(){
        Scanner scanner = new Scanner ( System.in );
        try {
            return scanner.next ();
        } catch (InputMismatchException e){
            return "Please enter a String  next time";
        }
    }

  

   /**
    * create a bank
    * @return the bank created
    */
    public Bank createBank ()
    {
        Bank bank = new Bank();
         System.out.println ( "Enter Bank Name: " );
        bank.setName(readString ());
        
        System.out.println("Please select a location from the list:");
        Location locs[] = Location.values();
        for(Location l : locs){
            System.out.println(l.toString());
        }
        
        while(true){
            int locId = readInt();
        Location selectedLoc = Location.getLocationById(locId);
        if(selectedLoc!=null)
        {
            bank.setLocation(selectedLoc);
            break;
        }
        else System.out.println("Invalid location please select another one..");
        }
        

    return bank;
    }
    
    

     
    /**
     * Displays balance of the user on standard output
     * @param customer taking from the customer class and saving the field bank
     */
    public void displayCustomer(Customer customer){
        System.out.println(", bank=" 
                + "Customer{" 
                + "id="
                + customer.getId()
                + ", firstName="
                + customer.getFirstName()
                + ", lastName="
                + customer.getLastName()
                + ", birthDate="
                + customer.getBirthDate()
                +", bank= " 
                +customer.getBank()
        );
    }
    
    /**
     * Displays message on standard output
     * @param message to display system output of transactions
     */
    public void displayMessage(String message){
        System.out.println (message);
    }

}
