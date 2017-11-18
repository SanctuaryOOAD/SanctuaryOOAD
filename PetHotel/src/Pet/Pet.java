/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pet;

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
@Entity
public class Pet {
    
    private int pet_ID;  // Primary Key
    private String Owner;   //Customer
    private String name;
    private String animal;
    private String extra;  // extra thing
    private int age;

    public Pet(int pet_ID, String Owner, String name, String animal, String extra, int age) {
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
    
    public void addPet(String owner, String name, String type, String extra, int age){
            Pet s;

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Pet.odb");		
            EntityManager em = emf.createEntityManager();


            em.getTransaction().begin();
            Query q3 = em.createQuery("select max(pet_ID) from Pet ");

            System.out.println(q3.getSingleResult());
            int maxpet_ID = (int)q3.getSingleResult();
            System.out.println(maxpet_ID);
            s = new Pet(maxpet_ID+1, owner, name, type, extra, age);
            em.persist(s);


            em.getTransaction().commit();
            
        /////////////// CustomerPetDB Part ////////////////////////////////////
        AddPetController obj;
        obj = new AddPetController();
        
        CustomerPetDB obj2;
        
	EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("$dist/db/CustomerPet.odb");		
	EntityManager em2 = emf2.createEntityManager();
        
        em2.getTransaction().begin();
        
        obj2 = new CustomerPetDB(obj.primaryKeyFromAddPet,maxpet_ID+1);
	em2.persist(obj2);

	em2.getTransaction().commit();
        ///////////////////////////////////////////////////////////////////////
    } ///
    
    
    
}
