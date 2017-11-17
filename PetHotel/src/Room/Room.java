/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Room;

/**
 *
 * @author natht
 */
public class Room {
    private String roomNumber;      //Primary Key
    private String size;            //Small,Medium,Big
    private String stat;            //Empty,Full
    

    public Room(String roomNumber, String size, String stat) {
        this.roomNumber = roomNumber;
        this.size = size;
        this.stat = stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
    
    public float Cost(int time){  //Hour
        return 0;
    }
    
    public void Addpet(String pet_ID){
        ///Data Base ////
    }
    
    
    
    
}
