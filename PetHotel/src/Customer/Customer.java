/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

/**
 *
 * @author natht
 */
public class Customer {
       
        private String Username;        //Primary Key
	private String Name;
        private String Tel;
        private String Email;
        private String IdCardNumber;
        private String Plan;
        
        protected float cost = 0;   //cost

    public Customer(String Username, String Name, String Tel, String Email, String IdCardNumber, String Plan) {
        this.Username = Username;
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
    
    
        
        
        
    
    
}
