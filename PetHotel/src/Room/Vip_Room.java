/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Room;

import javax.persistence.Entity;

/**
 *
 * @author natht
 */

public class Vip_Room extends Room {
    
    public Vip_Room(int roomNumber, String roomClass, String stat) {
        super(roomNumber, roomClass, stat);
    }
    
    public Vip_Room() {

    }

    @Override
    public float Cost(int day) {
        return day * 1000 ;
    }
    
    
}
