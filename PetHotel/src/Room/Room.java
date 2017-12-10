/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Room;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;

/**
 *
 * @author natht
*/
@Entity
public class Room {
    
    @Id
    private int roomNumber;      //Primary Key
    private String roomClass;            //Small,Medium,Big
    private String stat;            //Empty,Full
    

    public Room(int roomNumber, String roomClass, String stat) {
        this.roomNumber = roomNumber;
        this.roomClass = roomClass;
        this.stat = stat;
    }
    
    public Room(){
        
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
    
    public float Cost(int day){  //Hour
        return 0;
    }
    
    public void Addpet(String pet_ID){
        ///Data Base ////
    }
    
    public void AddRoom(){
       
        /////////////////////
        
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public String getStat() {
        return stat;
    }
    
    
    
    
    
}
