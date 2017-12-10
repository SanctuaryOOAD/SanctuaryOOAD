/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pet;

import Customer.Customer;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.*;
import java.util.*;

/**
 *
 * @author Corerid
 */

public class dum {
    public static void main(String[] args) {
        
        Pet s;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em = emf.createEntityManager();
        
        Customer a;

            em.getTransaction().begin();
            a = em.find(Customer.class,1);
        
        s = new Dog(3, "Pom", "Dog", "shit lover", 6, a);
	em.persist(s);

	em.getTransaction().commit();

    }
    
}
