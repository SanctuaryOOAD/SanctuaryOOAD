/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Room;

import Pet.Pet;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Corerid
 */
public class dum2 {
    public static void main(String[] args) {
        Room s;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/RoomDB.odb");		
	EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Query q2 = em.createQuery("DELETE FROM Room s WHERE s.roomNumber = 8");
        q2.executeUpdate();
        
        em.getTransaction().commit();
        
        Pet a;
        EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em2 = emf2.createEntityManager();
        
        a = em2.find(Pet.class, 1);
        System.out.println(a.getName());
        em.getTransaction().begin();
        s = new Room(8, "Kuy", "Full", a);
//        
        em.persist(s);
        em.getTransaction().commit();

    }
}
