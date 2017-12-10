/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pethotel;

import Customer.Customer;
import LinkDB.CustomerRoomPetDB;
import Room.Room;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import static pethotel.AddPetController.customerNameFromAddPet;
import static pethotel.AddPetController.primaryKeyFromAddPet;

/**
 * FXML Controller class
 *
 * @author Corerid
 */
public class CheckOutController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<CustomerRoomPetDB> table;

    @FXML
    private TableColumn roomNumber;

    @FXML
    private TableColumn<CustomerRoomPetDB, String> name;

    @FXML
    private TableColumn<CustomerRoomPetDB, String> petName;

    @FXML
    private TableColumn<CustomerRoomPetDB, String> checkIn;

    @FXML
    private TableColumn<CustomerRoomPetDB, String> checkOut;

    @FXML
    private TableColumn price;
    
    @FXML
    private JFXTextField roomNoSearch;

    @FXML
    private JFXTextField nameSearch;

    @FXML
    private Button back;
    
    static public int roomNumber_checkout;
    static public String customerName_checkout;
    static public String petName_checkout;
    static public String checkIn_checkout;
    static public String checkOut_checkout;
    static public String price_checkout;
    
    @FXML
    private void checkOut(MouseEvent event) throws IOException
    {
        if (event.getClickCount() == 2) //Checking double click
        {
            
            roomNumber_checkout = table.getSelectionModel().getSelectedItem().getRoomNumber();
            customerName_checkout = table.getSelectionModel().getSelectedItem().getCustomerName();
            petName_checkout = table.getSelectionModel().getSelectedItem().getPetName();
            checkIn_checkout = table.getSelectionModel().getSelectedItem().getCheckInDate();
            checkOut_checkout = table.getSelectionModel().getSelectedItem().getCheckOutDate();
            price_checkout = String.valueOf(table.getSelectionModel().getSelectedItem().getTotalPrice());
            
            Parent staffDashBoard = FXMLLoader.load(getClass().getResource("StaffDashBoard.fxml"));
            Scene StaffDashBoard = new Scene(staffDashBoard);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            
            //popup 
            Stage stage2 = new Stage();
            Parent root2 = FXMLLoader.load(getClass().getResource("PopupCheckOutSuccess.fxml"));
            stage2.setScene(new Scene(root2));
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initOwner(table.getScene().getWindow());
            stage2.showAndWait();
            
            //wait for return and change scene
            
            window.setScene(StaffDashBoard);
            window.show();

            //getUsername when click at table
//            int roomNumber_selcted = table.getSelectionModel().getSelectedItem().getRoomNumber();
//            System.out.println(roomNumber_selcted);
//            
//            String roomClass_selcted;
//            
//            
//            
//            /// get room class ////////
//            EntityManagerFactory emf0 = Persistence.createEntityManagerFactory("$dist/db/Room.odb");		
//            EntityManager em0 = emf0.createEntityManager();
//
//            em0.getTransaction().begin();
//            
//            em0.getMetamodel().entity(Room.class);
//            Query q0 = em0.createQuery("select roomClass FROM Room s WHERE s.roomNumber = :p");
//            roomClass_selcted = q0.setParameter("p", roomNumber_selcted).getSingleResult().toString();
//            ///////////////////////////
//            
//            CustomerRoomPetDB Obj;
//            
//            EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/CustomerRoomPet.odb");		
//            EntityManager em = emf.createEntityManager();
//
//            em.getTransaction().begin();
//            
//            em.getMetamodel().entity(CustomerRoomPetDB.class);
//            Query q1 = em.createQuery("DELETE FROM CustomerRoomPetDB s WHERE s.roomNumber = :p");
//            q1.setParameter("p", roomNumber_selcted).executeUpdate();
//            
//            em.getTransaction().commit();
//            //////////////////////////////////////////////////
//            Room roomObj;
//            
//            EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("$dist/db/Room.odb");		
//            EntityManager em2 = emf2.createEntityManager();
//
//            em2.getTransaction().begin();
//            
//            em2.getMetamodel().entity(Room.class);
//            Query q2 = em2.createQuery("DELETE FROM Room s WHERE s.roomNumber = :p");
//            q2.setParameter("p", roomNumber_selcted).executeUpdate();
//            
//            roomObj = new Room(roomNumber_selcted, roomClass_selcted, "Available");
//            em2.persist(roomObj);
//
//            em2.getTransaction().commit();
            

            //popup more customer detail
//            Stage stage = new Stage();
//            Parent root = FXMLLoader.load(getClass().getResource("AddPetPopup.fxml"));
//            stage.setScene(new Scene(root));
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.initOwner(table.getScene().getWindow());
//            stage.showAndWait();
           
        

        }
        
    }
    
    public void back(ActionEvent event) throws IOException{
        
        Parent staffDashBoard = FXMLLoader.load(getClass().getResource("StaffDashBoard.fxml"));
        Scene StaffDashBoard = new Scene(staffDashBoard);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(StaffDashBoard);
        window.show();
    }

    @FXML
    private void searchRoomNo(KeyEvent event){
        CustomerRoomPetDB s;
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/CustomerRoomPet.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
        
        em.getMetamodel().entity(CustomerRoomPetDB.class); // Query empty database
                
        Query q1 = em.createQuery("SELECT s FROM CustomerRoomPetDB s");
        Query q2 = em.createQuery("select Type from CustomerRoomPetDB");
        
        List<CustomerRoomPetDB> results = q1.getResultList();
        ObservableList<CustomerRoomPetDB> results2 = FXCollections.<CustomerRoomPetDB>observableArrayList(results);
        
        FilteredList filter = new FilteredList(results2, e->true);
        
        roomNoSearch.textProperty().addListener((observable, oldValue, newValue)->{
            filter.setPredicate((Predicate<? super CustomerRoomPetDB>)(CustomerRoomPetDB std)->{

                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                else if(String.valueOf(std.getRoomNumber()).contains(newValue)){
                    return true;
                }

                return false;
            });
        });
        
        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(filter);
    }
    
    @FXML
    private void searchName(KeyEvent event){

        CustomerRoomPetDB s;
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/CustomerRoomPet.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
        em.getMetamodel().entity(CustomerRoomPetDB.class);
                
        Query q1 = em.createQuery("SELECT s FROM CustomerRoomPetDB s");
        Query q2 = em.createQuery("select Type from CustomerRoomPetDB");
        
        List<CustomerRoomPetDB> results = q1.getResultList();
        ObservableList<CustomerRoomPetDB> results2 = FXCollections.<CustomerRoomPetDB>observableArrayList(results);
        
        FilteredList filter2 = new FilteredList(results2, e->true);
        
        nameSearch.textProperty().addListener((observable, oldValue, newValue)->{
            filter2.setPredicate((Predicate<? super CustomerRoomPetDB>)(CustomerRoomPetDB std)->{

                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                else if(std.getCustomerName().contains(newValue)){
                    return true;
                }

                return false;
            });
        });
        
        SortedList sort2 = new SortedList(filter2);
        sort2.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(filter2);
    }    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        name.setCellValueFactory(new PropertyValueFactory<CustomerRoomPetDB, String>("customerName"));
        petName.setCellValueFactory(new PropertyValueFactory<CustomerRoomPetDB, String>("petName"));
        checkIn.setCellValueFactory(new PropertyValueFactory<CustomerRoomPetDB, String>("checkInDate"));
        checkOut.setCellValueFactory(new PropertyValueFactory<CustomerRoomPetDB, String>("checkOutDate"));
        price.setCellValueFactory(new PropertyValueFactory<CustomerRoomPetDB, String>("totalPrice"));
        
        CustomerRoomPetDB s;
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/CustomerRoomPet.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
        
        em.getMetamodel().entity(CustomerRoomPetDB.class);      
        Query q1 = em.createQuery("SELECT s FROM CustomerRoomPetDB s");

        List<CustomerRoomPetDB> results = q1.getResultList();

        ObservableList<CustomerRoomPetDB> obs = FXCollections.<CustomerRoomPetDB>observableArrayList(results);
        table.setItems(obs);
        
    }    
    
}
