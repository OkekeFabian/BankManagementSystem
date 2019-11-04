/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.lab1.model;

/**
 * Holding the 4 main locations that banks created can be made in
 *
 * @author fabianokeke
 * @version 1.0
 *
 */
public enum Location {
    /**
     * First Location to be chosen by the user
     */
    GLIWICE(1, "Gliwice"),
    
    /**
     * Second Location to be chosen by the user
     */
    KATOWICE(2, "Katowice"),
    /**
     * Third Location to be chosen by the user
     */
    WARSAW(3, "Warsaw"),
    
    /**
     * Fourth Location to be chosen by the user
     */
    KRAKOW(4, "Krakow");

    /**
     * Id's of the locations of the banks
     */
    private final int id;

    /**
     * name of the locations of the banks
     */
    private final String name;

    /**
     * Constructor of the enumerated Location 
     *
     * @param id of the locations of the banks
     * @param name of the locations of the banks
     */
    Location(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Override of toString() method
     *
     * @return a composite String containing all the fields
     */
    @Override
    public String toString() {
        return this.id + " - " + this.name;
    }

    /**
     *To get the location by providing the id
     * @param id of the locations of banks
     * @return the locations based on id provided
     */
    public static Location getLocationById(int id) {

        switch (id) {
            case 1:
                return Location.GLIWICE;
            case 2:
                return Location.KATOWICE;
            case 3:
                return Location.WARSAW;
            case 4:
                return Location.KRAKOW;
            default:
                return null;
        }
    }

    /**
     * Getting the Id of location of the banks
     *
     * @return Id of the locations of the banks
     */
    public Integer getId() {
        return id;
    }
}
