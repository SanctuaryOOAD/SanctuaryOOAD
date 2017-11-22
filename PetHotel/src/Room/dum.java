/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Room;


import LinkDB.CustomerPetDB;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import pethotel.ReserveRoomController;

/**
 *
 * @author Corerid
 */
public class dum {
    public static void main(String[] args) {
        Room s;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Room.odb");		
	EntityManager em = emf.createEntityManager();
        
        
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

//        em.getTransaction().begin();
//        
//        for(int i = 1; i<21; i++){
//            s = new Room(i, "Standard", "Available");
//            em.persist(s);
//        }
//        
//        for(int i = 21; i<36; i++){
//            s = new Room(i, "Superior", "Available");
//            em.persist(s);
//        }
//        
//        for(int i = 36; i<46; i++){
//            s = new Room(i, "Suite", "Available");
//            em.persist(s);
//        }
//        
//        for(int i = 46; i<=50; i++){
//            s = new Room(i, "VIP", "Available");
//            em.persist(s);
//        }
//        
//        em.getTransaction().commit();

        
        
        //System.out.println(a);
//        Query q2 = em.createQuery("select Type from Customer");
//        
//        Query q4 = em.createQuery("select pet_ID from CustomerPetDB where primaryKey = :PrimaryKey_s",CustomerPetDB.class);
//        List petList = q4.setParameter("PrimaryKey_s",ReserveRoomController.primaryKey_tbl).getResultList();
        
    }

}
