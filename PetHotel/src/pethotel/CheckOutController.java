/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pethotel;

import Customer.Customer;
import LinkDB.CustomerRoomPetDB;
import Pet.Pet;
import Room.Room;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private TableView<CheckOutTable> table;

    @FXML
    private TableColumn roomNumber;

    @FXML
    private TableColumn name;

    @FXML
    private TableColumn petName;

    @FXML
    private TableColumn checkIn;

    @FXML
    private TableColumn<CheckOutTable, String> checkOut;

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
            customerName_checkout = table.getSelectionModel().getSelectedItem().getName();
            petName_checkout = table.getSelectionModel().getSelectedItem().getPetName();
            checkIn_checkout = table.getSelectionModel().getSelectedItem().getCheckIn();
            checkOut_checkout = table.getSelectionModel().getSelectedItem().getCheckOut();
            price_checkout = String.valueOf(table.getSelectionModel().getSelectedItem().getPrice());
            
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
        

        
//        List<String> cusNameList = new ArrayList<>();
        List<Integer> pet_id = new ArrayList<>();
        
        roomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        name.setCellValueFactory(new PropertyValueFactory<CheckOutTable , String>("name"));
        petName.setCellValueFactory(new PropertyValueFactory<CheckOutTable , String>("petName"));
        checkIn.setCellValueFactory(new PropertyValueFactory<CheckOutTable, String>("checkIn"));
        checkOut.setCellValueFactory(new PropertyValueFactory<CheckOutTable, String>("checkOut"));
        price.setCellValueFactory(new PropertyValueFactory<CheckOutTable, String>("price"));
        
        Customer s;
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
                
        Query q1 = em.createQuery("SELECT s FROM Pet s WHERE s.isStay = true", Pet.class);
//        Query q2 = em.createQuery("SELECT email FROM Customer s WHERE s.primaryKey = :PrimaryKey", Customer.class);
//        Query q3 = em.createQuery("SELECT tel FROM Customer s WHERE s.primaryKey = :PrimaryKey", Customer.class);
//        Query q4 = em.createQuery("SELECT idcardNumber FROM Customer s WHERE s.primaryKey = :PrimaryKey", Customer.class);
//        Query q5 = em.createQuery("SELECT plan FROM Customer s WHERE s.primaryKey = :PrimaryKey", Customer.class);
        
        //Search pet_ID that has Primary Key = PrimaryKey -- return List and store in myList

        List<Pet> myList = new ArrayList<Pet>();
        List<CheckOutTable> CheckoutTbl = new ArrayList<CheckOutTable>();
        List<CheckOutTable> sortTbl = new ArrayList<CheckOutTable>();
        CheckOutTable x ;
        int sort[];
        myList = q1.getResultList();
        
        for( Pet i : myList){
            
        }
        
        for(Pet i : myList){
            x = new CheckOutTable(i);
            CheckoutTbl.add(x);
        }
        
        
        
        
        
        ObservableList<CheckOutTable> results2 = FXCollections.<CheckOutTable>observableArrayList(CheckoutTbl);
        table.setItems(results2);

       
        
        
        
        
//        Pet a;
//	EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
//	EntityManager em2 = emf2.createEntityManager();
//        
//        em2.getTransaction().begin();
//        
//        Query q7 = em2.createQuery("select s from Pet s where s.pet_ID = :p", Pet.class);
//	Query q8 = em2.createQuery("select name from Pet s where s.pet_ID = :p", Pet.class);	
//        Query q9 = em2.createQuery("select animal from Pet s where s.pet_ID = :p", Pet.class);
//        Query q10 = em2.createQuery("select age from Pet s where s.pet_ID = :p", Pet.class);
//        Query q11 = em2.createQuery("select extra from Pet s where s.pet_ID = :p", Pet.class);
//        
//        //create ObservableList names obs for create Tableview
//        ObservableList<CusPet> obs = FXCollections.observableArrayList();
//        
//        a.get
//      
//        //This loop for add object (obs) to CusPet(Class for create table) following amount of myArray.length
//        //วนลูปเพิ่มค่าใน class CusPet เพื่อสร้างตารางโดยเฉพาะ ตามจำนวน pet_ID ที่มี (ตามจำนวนสัตว์ที่ customer มี)
//        for(int i=0;i<myArray.length;i++){
//            
////            System.out.println(q8.setParameter("p", myArray[i]).getSingleResult());
////            System.out.println(q9.setParameter("p", myArray[i]).getSingleResult());
////            System.out.println(q10.setParameter("p", myArray[i]).getSingleResult());
////            System.out.println(q11.setParameter("p", myArray[i]).getSingleResult());
//            
//            obs.add(new CheckOutTable();
//
//        }
//        
//        table.setItems(obs);

        
        ////////////////// Table //////////////////////////////////
        
    
//        Pet b;
//	EntityManagerFactory emf5 = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
//	EntityManager em5 = emf5.createEntityManager();
//        
//        em5.getTransaction().begin();
//        int kk = pet_id.size();
//        System.out.println("kkkkkkkk");
//        System.out.println(kk);
        //Search pet_ID that has Primary Key = PrimaryKey -- return List and store in myList
//        em5.find(Pet.class, pet_id.size())
        

//        List<String> myList = new ArrayList<String>();
//        
//        myList = q6.setParameter("PrimaryKey", ShowCustomerListController.primaryKeyFromshowmore).getResultList();
//        //create myArray that converted myList (List) to myArray (Array
//        Object[] myArray = myList.toArray();
//        for (Object myObject : myArray) {
//           System.out.println(myObject);
//        }
//        
//        
//        Pet a;
//	EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("$dist/db/Pet.odb");		
//	EntityManager em2 = emf2.createEntityManager();
//        
//        em2.getTransaction().begin();
//        
//        Query q7 = em2.createQuery("select s from Pet s where s.pet_ID = :p", Pet.class);
//	Query q8 = em2.createQuery("select name from Pet s where s.pet_ID = :p", Pet.class);	
//        Query q9 = em2.createQuery("select animal from Pet s where s.pet_ID = :p", Pet.class);
//        Query q10 = em2.createQuery("select age from Pet s where s.pet_ID = :p", Pet.class);
//        Query q11 = em2.createQuery("select extra from Pet s where s.pet_ID = :p", Pet.class);
//        
//        //create ObservableList names obs for create Tableview
//        ObservableList<CusPet> obs = FXCollections.observableArrayList();
//      
//        //This loop for add object (obs) to CusPet(Class for create table) following amount of myArray.length
//        //วนลูปเพิ่มค่าใน class CusPet เพื่อสร้างตารางโดยเฉพาะ ตามจำนวน pet_ID ที่มี (ตามจำนวนสัตว์ที่ customer มี)
//        for(int i=0;i<myArray.length;i++){
//            
////            System.out.println(q8.setParameter("p", myArray[i]).getSingleResult());
////            System.out.println(q9.setParameter("p", myArray[i]).getSingleResult());
////            System.out.println(q10.setParameter("p", myArray[i]).getSingleResult());
////            System.out.println(q11.setParameter("p", myArray[i]).getSingleResult());
//            
//            obs.add(new CusPet(q8.setParameter("p", myArray[i]).getSingleResult().toString(),
//                               q9.setParameter("p", myArray[i]).getSingleResult().toString(),
//                               q11.setParameter("p", myArray[i]).getSingleResult().toString(),
//                               (int)q10.setParameter("p", myArray[i]).getSingleResult()));
//
//        }
//        
//        table.setItems(obs);   
        
//        CustomerRoomPetDB s;
//		
//	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/CustomerRoomPet.odb");		
//	EntityManager em = emf.createEntityManager();
//                
//        em.getTransaction().begin();
//        
//        em.getMetamodel().entity(CustomerRoomPetDB.class);      
//        Query q1 = em.createQuery("SELECT s FROM CustomerRoomPetDB s");
//
//        List<CustomerRoomPetDB> results = q1.getResultList();
//
//        ObservableList<CustomerRoomPetDB> obs = FXCollections.<CustomerRoomPetDB>observableArrayList(results);
//        table.setItems(obs);
//        
    } 
    
    
}

