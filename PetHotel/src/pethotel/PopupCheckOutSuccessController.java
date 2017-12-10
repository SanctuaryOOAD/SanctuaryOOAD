/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pethotel;

import LinkDB.CustomerRoomPetDB;
import Room.Room;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import static pethotel.CheckOutController.checkIn_checkout;
import static pethotel.CheckOutController.checkOut_checkout;
import static pethotel.CheckOutController.customerName_checkout;
import static pethotel.CheckOutController.petName_checkout;
import static pethotel.CheckOutController.price_checkout;
import static pethotel.CheckOutController.roomNumber_checkout;

/**
 * FXML Controller class
 *
 * @author Corerid
 */
public class PopupCheckOutSuccessController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button ok;

    @FXML
    private Label roomNo;

    @FXML
    private Label CustomerName;

    @FXML
    private Label petName;

    @FXML
    private Label checkIn;

    @FXML
    private Label checkOut;

    @FXML
    private Label price;
    
    @FXML
    private Label kuy;
    
    public void back(ActionEvent event) throws IOException{
        
        Stage stage;
        Parent root;
        
        stage = (Stage)ok.getScene().getWindow();
        stage.close();
        
    }
    
    public void submit(ActionEvent event){
            String roomClass_selcted;

            /// get room class ////////
            EntityManagerFactory emf0 = Persistence.createEntityManagerFactory("$dist/db/Room.odb");		
            EntityManager em0 = emf0.createEntityManager();

            em0.getTransaction().begin();
            
            em0.getMetamodel().entity(Room.class);
            Query q0 = em0.createQuery("select roomClass FROM Room s WHERE s.roomNumber = :p");
            roomClass_selcted = q0.setParameter("p", roomNumber_checkout).getSingleResult().toString();
            ///////////////////////////
            
            CustomerRoomPetDB Obj;
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/CustomerRoomPet.odb");		
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();
            
            em.getMetamodel().entity(CustomerRoomPetDB.class);
            Query q1 = em.createQuery("DELETE FROM CustomerRoomPetDB s WHERE s.roomNumber = :p");
            q1.setParameter("p", roomNumber_checkout).executeUpdate();
            
            em.getTransaction().commit();
            //////////////////////////////////////////////////
            Room roomObj;
            
            EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("$dist/db/Room.odb");		
            EntityManager em2 = emf2.createEntityManager();

            em2.getTransaction().begin();
            
            em2.getMetamodel().entity(Room.class);
            Query q2 = em2.createQuery("DELETE FROM Room s WHERE s.roomNumber = :p");
            q2.setParameter("p", roomNumber_checkout).executeUpdate();
            
            roomObj = new Room(roomNumber_checkout, roomClass_selcted, "Available");
            em2.persist(roomObj);

            em2.getTransaction().commit();
            
            Stage stage;
            Parent root;

            stage = (Stage)ok.getScene().getWindow();
            stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        roomNo.setText(String.valueOf(roomNumber_checkout));
        CustomerName.setText(customerName_checkout);
        petName.setText(petName_checkout);
        checkIn.setText(checkIn_checkout);
        checkOut.setText(checkOut_checkout);
        price.setText(price_checkout);
        
    }    
    
}
