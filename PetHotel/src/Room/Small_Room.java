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
public class Small_Room extends Room {

    public Small_Room(String roomNumber, String size, String stat) {
        super(roomNumber, size, stat);
    }

    @Override
    public float Cost(int time) {       //Calculate Cost
        return  time * 30;                
    }

 }
