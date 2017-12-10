/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkDB;

import javax.persistence.Entity;

/**
 *
 * @author natht
 */
@Entity
public class CustomerPetDB {
    
        private int primaryKey;
        private int pet_ID;

    public CustomerPetDB(int primaryKey, int pet_ID) {
        this.primaryKey = primaryKey;
        this.pet_ID = pet_ID;
    }

    public CustomerPetDB(CustomerPetDB get) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
         
    
}
