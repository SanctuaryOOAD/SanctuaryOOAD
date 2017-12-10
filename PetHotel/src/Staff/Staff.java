/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Staff;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Corerid
 */
@Entity
public class Staff {
  
        private String Username;
	private String Password;
        private String Name;
        private String Tel;
        private String Email;

        
       
	public Staff(String Username, String Password, String Name, String Tel, String Email){

		this.Username = Username;
                this.Password = Password;
                this.Name = Name;
                this.Tel = Tel;
                this.Email = Email;
 
	}
        
        public String getUsername() {
		return Username;
	}

	public String getPassword() {
		return Password;
	}

	public String getName() {
		return Name;
	}
        
        public String getTel() {
		return Tel;
	}

	public String getEmail() {
		return Email;
	}
        


         
         @Override
	public String toString() {
		return String.format("(Username: %s, Password: %s, Name: %s, E-mail: %s, Tel: %s)", 
                        this.Username, this.Password, this.Name, this.Email, this.Tel);
	}
}
