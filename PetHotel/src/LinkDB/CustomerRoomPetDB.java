/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkDB;

import javax.persistence.Entity;

/**
 *
 * @author Corerid
 */
@Entity
public class CustomerRoomPetDB {
    private int primaryKey;
    private int roomNumber;
    private int pet_ID;
    private String checkInDate;
    private String checkOutDate;
    private int totalDay;
    private float totalPrice;
    
    private String customerName;
    private String petName;

    public CustomerRoomPetDB(int primaryKey, int roomNumber, int pet_ID, String checkInDate, String checkOutDate, int totalDay, float totalPrice, String customerName, String petName) {
        this.primaryKey = primaryKey;
        this.roomNumber = roomNumber;
        this.pet_ID = pet_ID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalDay = totalDay;
        this.totalPrice = totalPrice;
        this.customerName = customerName;
        this.petName = petName;
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
    
    public int getPet_ID() {
        return pet_ID;
    }
    
    public String getCheckInDate() {
        return checkInDate;
    }
    
    public String getCheckOutDate() {
        return checkOutDate;
    }

    public int getTotalDay() {
        return totalDay;
    }
    
    public float getTotalPrice() {
        return totalPrice;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public String getPetName() {
        return petName;
    }
    
    
}
