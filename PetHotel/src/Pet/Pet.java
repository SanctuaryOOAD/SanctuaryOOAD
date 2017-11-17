/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pet;

/**
 *
 * @author natht
 */
public class Pet {
    
    private String pet_ID;  // Primary Key
    private String Owner;   //Customer
    private String name;
    private String animal;
    private String extra;  // extra thing
    private int age;

    public Pet(String pet_ID, String Owner, String name, String animal, String extra, int age) {
        this.pet_ID = pet_ID;
        this.Owner = Owner;
        this.name = name;
        this.animal = animal;
        this.extra = extra;
        this.age = age;
    }

   
    public void show_info(){
        System.out.println("Pet ID : " + pet_ID + "\nOwner : " + Owner + "\n Name : " + name + "\nAnimal : " + animal + "\nAge : " + age);
    } 
   
    public void how_to(){ //how to take care description deaw overwrite//
        System.out.println("DEAW KOI KID  OverrideXD ");
    }
    
    
    
}
