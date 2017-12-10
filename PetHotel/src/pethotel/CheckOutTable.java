/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pethotel;

/**
 *
 * @author Corerid
 */
public class CheckOutTable {
    
    private int roomNumber;
    private String name;
    private String petName;
    private String checkIn;
    private String checkOut;
    private float price;

    public CheckOutTable(int roomNumber, String name, String petName, String checkIn, String checkOut, float price) {
        
        this.roomNumber = roomNumber;
        this.name = name;
        this.petName = petName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public String getPetName() {
        return petName;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }
    
    public float getPrice() {
        return price;
    }
    
}
