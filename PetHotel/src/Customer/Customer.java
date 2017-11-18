/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

import LinkDB.CustomerPetDB;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author natht
 */
@Entity
public class Customer {
       
        private int primaryKey;        //Primary Key
	private String name;
        private String tel;
        private String email;
        private String idcardNumber;
        private String plan;
        
        protected float cost = 0;   //cost

    public Customer(int primaryKey, String name, String tel, String email, String idcardNumber, String plan) {
        this.primaryKey = primaryKey;
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.idcardNumber = idcardNumber;
        this.plan = plan;
    }

    
    
        public void setCost(float cost) {
            this.cost = cost;
        }  //cost
        
        public float discount(){            //calculate discount
            return this.cost * 0;
        }
        
        public float realCost(){
            return this.cost - discount(); 
        }  // Calculate realcost(cost - discount)
        
        public void AddPet(String pet_ID){
           
        CustomerPetDB obj; //Customer object
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/CustomerPet.odb");		
	EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        obj = new CustomerPetDB(this.primaryKey,pet_ID);   //Add username + Pet ID
	em.persist(obj);


	em.getTransaction().commit();
        }
        
        public void addCustomer(String name, String tel, String email, String idcardNumber, String plan){
            Customer s;

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Customer.odb");		
            EntityManager em = emf.createEntityManager();


            em.getTransaction().begin();
            Query q3 = em.createQuery("select max(PrimaryKey) from Customer ");
            if (q3.getSingleResult() == null){
                System.exit(0);
            }
            System.out.println(q3.getSingleResult());
            int maxPrimaryKey = (int)q3.getSingleResult();
               System.out.println(maxPrimaryKey);
            s = new Customer(maxPrimaryKey+1, name, tel, email, idcardNumber, plan);
            em.persist(s);


            em.getTransaction().commit();
        } ///
    
    
        
        
        
    
    
}
