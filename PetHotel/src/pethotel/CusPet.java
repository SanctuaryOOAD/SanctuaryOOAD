/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pethotel;

import Customer.Customer;
import LinkDB.CustomerPetDB;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import pethotel.AddPetController;

/**
 *
 * @author natht
 */

public class CusPet {
    

    private String name;
    private String animal;
    private String extra;  // extra thing
    private int age;

    public CusPet(String name, String animal, String extra, int age) {

        this.name = name;
        this.animal = animal;
        this.extra = extra;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public String getAnimal() {
        return animal;
    }

    public String getExtra() {
        return extra;
    }

    public int getAge() {
        return age;
    }

    
}
