/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.lab1.model;

/**
 * 
 * @author fabianokeke
 * @version 1.0
 * Holding the 4 main locations that banks created can be made in
 */

public enum Location {
    GLIWICE(1,"Gliwice"), KATOWICE(2,"Katowice"), WARSAW(3,"Warsaw"), KRAKOW(4,"Krakow");
    
    private int id;
    private String name;
    Location(int id,String name) {
        this.id = id;
        this.name = name;
    }
    public String toString(){
        return this.id + " - " + this.name;
    }
    public static Location getLocationById(int id){
        
        switch(id){
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
    public Integer getId(){
        return id;
    }
}

