/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pethotel;

import LinkDB.CustomerPetDB;
import Pet.Pet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Corerid
 */
public class dummy {
    public static void main(String[] args) {
        
        CustomerPetDB s;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/CustomerPet.odb");		
	EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();

		

	Query q4 = em.createQuery("select pet_ID from CustomerPetDB where primaryKey = 9 ", CustomerPetDB.class);


        List<String> myList = new ArrayList<String>();
        myList = q4.getResultList();

        Object[] myArray = myList.toArray();
        for (Object myObject : myArray) {
           System.out.println(myObject);
        }
        
        Pet a;
	EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("$dist/db/Pet.odb");		
	EntityManager em2 = emf2.createEntityManager();
        
        em2.getTransaction().begin();
        
	Query q5 = em2.createQuery("select name from Pet s where s.pet_ID = :p", Pet.class);	
       
        for(int i=0;i<myArray.length;i++){
            System.out.println(q5.setParameter("p", myArray[i]).getSingleResult());

        }
        

        
    }
    
}
