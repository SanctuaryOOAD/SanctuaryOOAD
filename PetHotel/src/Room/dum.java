/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Room;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 *
 * @author Corerid
 */
public class dum {
    public static void main(String[] args) {
//        Room s;
//	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
//	EntityManager em = emf.createEntityManager();
        
        
//        em.getTransaction().begin();
//        s = new Room(51, "Vip", "Avaible");
//	em.persist(s);
//        
//        em.getTransaction().commit();
        
//        em.getTransaction().begin();
//        
//        Query q10 = em.createQuery("DELETE FROM Room s WHERE s.roomNumber = :p");
//        q10.setParameter("p", 54).executeUpdate();
//        
//        s = new Room(54, "Vip", "Full");
//	em.persist(s);
//        
//        em.getTransaction().commit();

        Room s;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em = emf.createEntityManager();
        
//        Pet a;
//
            em.getTransaction().begin();
//            a = em.find(Pet.class,1);
//            s = new Suite_Room(36, "Suite", "Available");
//        em.persist(s);
        /// Delete ////
//        em.getTransaction().begin();
//        for(int i=1;i<=50;i++){
//            Query q2 = em.createQuery("DELETE FROM Room s WHERE s.roomNumber = :p");
//            q2.setParameter("p", i).executeUpdate();
//        }
        ///////////////
//
        for(int i = 1; i<51; i++){
            s = new Room(i, "Available");
            em.persist(s);
            //em.getTransaction().commit();
        }
//////        em.getTransaction().commit();
//        for(int i = 21; i<36; i++){
//            s = new Room(i, "Superior", "Available");
//            em.persist(s);
//        }
//        
//        for(int i = 36; i<46; i++){
//            s = new Suite_Room(i, "Suite", "Available");
//            em.persist(s);
//        }
////        
//        for(int i = 46; i<=50; i++){
//            s = new Vip_Room(i, "VIP", "Available");
//            em.persist(s);
//        }
//        
        em.getTransaction().commit();

        
        
        //System.out.println(a);
//        Query q2 = em.createQuery("select Type from Customer");
//        
//        Query q4 = em.createQuery("select pet_ID from CustomerPetDB where primaryKey = :PrimaryKey_s",CustomerPetDB.class);
//        List petList = q4.setParameter("PrimaryKey_s",ReserveRoomController.primaryKey_tbl).getResultList();
        
    }

}
