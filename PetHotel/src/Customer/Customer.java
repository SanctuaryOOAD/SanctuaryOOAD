/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

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
       
        private int PrimaryKey;        //Primary Key
	private String Name;
        private String Tel;
        private String Email;
        private String IdCardNumber;
        private String Plan;
        
        protected float cost = 0;   //cost

    public Customer(int PrimaryKey, String Name, String Tel, String Email, String IdCardNumber, String Plan) {
        this.PrimaryKey = PrimaryKey;
        this.Name = Name;
        this.Tel = Tel;
        this.Email = Email;
        this.IdCardNumber = IdCardNumber;
        this.Plan = Plan;
    } //Constructor
    
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
            ////////DATA BASE//////
        }
        
        public void addCustomer(String Name, String Tel, String Email, String IdCardNumber, String Plan){
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
            s = new Customer(maxPrimaryKey+1, Name, Tel, Email, IdCardNumber, Plan);
            em.persist(s);


            em.getTransaction().commit();
        }
    
    
        
        
        
    
    
}
