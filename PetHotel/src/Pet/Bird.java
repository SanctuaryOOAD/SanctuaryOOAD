/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pet;

import Customer.Customer;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Corerid
 */
@MappedSuperclass
public class Bird extends Pet{ 
    public Bird(int pet_ID, String name, String animal, String extra, int age, Customer owner) {
        super(pet_ID, name, animal, extra, age, owner);
    }

    @Override
    public void how_to() {
        super.how_to(); //To change body of generated methods, choose Tools | Templates.
    } 
}
