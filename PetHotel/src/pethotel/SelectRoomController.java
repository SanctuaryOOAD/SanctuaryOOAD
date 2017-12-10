/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pethotel;

import Customer.Customer;
import Customer.Customer_Gold;
import Customer.Customer_Platinum;
import Customer.Customer_Silver;
import LinkDB.CustomerPetDB;
import LinkDB.CustomerRoomPetDB;
import Pet.Pet;
import Room.Room;
import Room.Standard_Room;
import Room.Suite_Room;
import Room.Superior_Room;
import Room.Vip_Room;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static java.util.Collections.list;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import static pethotel.AddPetController.customerNameFromAddPet;
import static pethotel.AddPetController.primaryKeyFromAddPet;
import static pethotel.ReserveRoomController.customerName_tbl;
import static pethotel.ReserveRoomController.primaryKey_tbl;
import static pethotel.SelectPetAndDateController.petIDFromSelectPet;
import static pethotel.SelectPetAndDateController.totalDay;

/**
 * FXML Controller class
 *
 * @author natht
 */
public class SelectRoomController implements Initializable {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Room> tbl;
    
    @FXML
    private JFXComboBox  ChoosePet;
    
    @FXML
    private TableColumn<Room,String> roomNumber_tbl;

    @FXML
    private TableColumn<Room,String> roomClass_tbl;

    @FXML
    private TableColumn<Room,String> roomStat_tbl;

    @FXML
    private JFXComboBox roomClass;

    @FXML
    private JFXTextField name;
    
    @FXML
    private TextField class_ts;

    @FXML
    private JFXTextField id;
    
    @FXML
    private JFXDatePicker checkIn;

    @FXML
    private JFXDatePicker checkOut;
    
    @FXML
    private Label totalDay_S;
    
    @FXML
    private Label totalPrice;
    
    @FXML
    private JFXButton submitButton;
    
    @FXML
    private Label petName;

    @FXML
    private Label customerName;

    @FXML
    private Label roomNo;


    


    /**
     * Initializes the controller class.
     */
     @FXML
    private void searchRoomClass(MouseEvent event){
        Room s;
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Room.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
                
        Query q1 = em.createQuery("SELECT s FROM Room s");
        Query q2 = em.createQuery("select Type from Room");
        
        List<Room> results = q1.getResultList();
        ObservableList<Room> results2 = FXCollections.<Room>observableArrayList(results);
        
        FilteredList filter = new FilteredList(results2, e->true);
        System.out.println(""+roomClass.idProperty());
        roomClass.promptTextProperty().addListener((observable, oldValue, newValue)->{
            filter.setPredicate((Predicate<? super Room>)(Room std)->{

                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                else if(std.getRoomClass().contains(newValue)){
                    return true;
                }

                return false;
            });
        });
        
        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(tbl.comparatorProperty());
        tbl.setItems(filter);

    }
        
    
//    @FXML
//    private void dddd(KeyEvent event){
//           Room s;
//		
//	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Room.odb");		
//	EntityManager em = emf.createEntityManager();
//                
//        em.getTransaction().begin();
//                
//        Query q1 = em.createQuery("SELECT s FROM Room s");
//        Query q2 = em.createQuery("select Type from Room");
//        
//        List<Room> results = q1.getResultList();
//        ObservableList<Room> results2 = FXCollections.<Room>observableArrayList(results);
//        
//        FilteredList filter = new FilteredList(results2, e->true);
//       System.out.println(""+class_ts.textProperty());
//        class_ts.textProperty().addListener((observable, oldValue, newValue)->{
//            filter.setPredicate((Predicate<? super Room>)(Room std)->{
//
//                if(newValue.isEmpty() || newValue == null){
//                    return true;
//                }
//                else if(std.getRoomClass().contains(newValue)){
//                    return true;
//                }
//
//                return false;
//            });
//        });
//        
//        SortedList sort = new SortedList(filter);
//        sort.comparatorProperty().bind(tbl.comparatorProperty());
//        tbl.setItems(filter);
//    }
    
    //static public int totalDay;
    //static public String checkIn_String;
    //static public String checkOut_String;
    static public String totalPrice_label;
    static public float totalPrice_DB;
    
    @FXML
    private void getDate(ActionEvent event){
//        LocalDate dateIn = checkIn.getValue();
//        LocalDate dateOut = checkOut.getValue();
//
//        long totalDay_long = Duration.between(dateIn.atStartOfDay(), dateOut.atStartOfDay()).toDays();
//        totalDay = (int)totalDay_long;
//        
//        if(totalDay <= 0){
//            System.out.println("Wrong!!");
//        }
//        else{
//            System.out.println(totalDay);
//        } 
    }
    
    
    ReserveRoomController obj = new ReserveRoomController();
    SelectPetAndDateController selectPetObj = new SelectPetAndDateController();
    
    static public int selected_roomNum;
    static public String selected_pet;
    static public String selected_roomType;
    
    int statCP = 0;
       
    @FXML
    private void chooseRoom(MouseEvent event){
        
      if (event.getClickCount() == 2) //Checking double click
        {
            selected_roomNum = tbl.getSelectionModel().getSelectedItem().getRoomNumber();   //Choose  RoomNumber 
            System.out.println(selected_roomNum);
            
            String roomNumber_S = String.valueOf(tbl.getSelectionModel().getSelectedItem().getRoomNumber());
            
            selected_roomType = tbl.getSelectionModel().getSelectedItem().getRoomClass();
            
//            Room obj;
//
//            switch(selected_roomType){
//
//                case "Standard" : obj = new Standard_Room();    break;
//                case "Superior" : obj = new Superior_Room();    break;
//                case "Suite" : obj = new Suite_Room();  break;
//                case "Vip" : obj = new Vip_Room();  break;
//                default :   obj = new Room(); break;  
//            }
//
//            totalPrice_label = Float.toString(obj.Cost(selectPetObj.totalDay));
//
//            totalPrice.setText(totalPrice_label);
            
            
            Room obj;

            switch(selected_roomType){

                case "Standard" : obj = new Standard_Room();    break;
                case "Superior" : obj = new Superior_Room();    break;
                case "Suite" : obj = new Suite_Room();  break;
                case "VIP" : obj = new Vip_Room();  break;
                default :   obj = new Room(); break;  
            }
            
            float priceBefore = obj.Cost(selectPetObj.totalDay);
            
            ////////////////////
            EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("$dist/db/Customer.odb");		
            EntityManager em2 = emf2.createEntityManager();
                
            em2.getTransaction().begin();
            em2.getMetamodel().entity(Customer.class);
        
            Query q4 = em2.createQuery("select plan from Customer where primaryKey = :PrimaryKey_s", Customer.class);
            String customer_plan = q4.setParameter("PrimaryKey_s",ReserveRoomController.primaryKey_tbl).getSingleResult().toString();
            
            Customer obj2;
            switch(customer_plan){
                case "Silver" : obj2 = new Customer_Silver();    break;
                case "Gold" : obj2 = new Customer_Gold();    break;
                case "Platinum" : obj2 = new Customer_Platinum();  break;
                default :   obj2 = new Customer(); break;  
            }
            
            obj2.setCost(priceBefore); //set cost
            totalPrice_DB = obj2.realCost();

            totalPrice_label = Float.toString(obj2.realCost());
            

            totalPrice.setText(totalPrice_label+" Bath");
            
            roomNo.setText(roomNumber_S);
         
        }
    
        
    }
    
//  List petList2 = new ArrayList(); //name of customer's pet
//  List petList3 = new ArrayList();
//  int len;
//    
//    @FXML
//    private void choosePet(MouseEvent event){
//        if(statCP == 0){                    //limited set Combbox 
//        statCP = 1;                         //Set Combobox State
//        
//        //////////////////////Data BASE PET/////////////////////////////////////////////////////////////////        CustomerPet.odb
//        //CustomerPetDB s; 
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/CustomerPet.odb");		
//	EntityManager em = emf.createEntityManager();
//                
//        em.getTransaction().begin();
//        
//        Query q4 = em.createQuery("select pet_ID from CustomerPetDB where primaryKey = :PrimaryKey_s",CustomerPetDB.class);
//        List petList = q4.setParameter("PrimaryKey_s",ReserveRoomController.primaryKey_tbl).getResultList();
//        //petList3.add(q4.setParameter("PrimaryKey_s",ReserveRoomController.primaryKey_tbl).getResultList());
//
//        System.out.println(petList);
//        
//        em.getTransaction().commit();
//        /////////////////////////////////////////////////////////////////////////////////////////////////////
//         
//        ///////////////////////////////Pet DB////////////////////////////////////////////////////////////////
//         
//        //create myArray that converted myList (List) to myArray (Array
//        Object[] myArray = petList.toArray(); //array store pet_ID
//
//        for (Object myObject : myArray) {
//           System.out.println(myObject);
//        }
//
//        Pet a;
//	EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("$dist/db/Pet.odb");		
//	EntityManager em2 = emf2.createEntityManager();
//        
//        em2.getTransaction().begin();
//        
//        Query q7 = em2.createQuery("select name from Pet s where s.pet_ID = :p", Pet.class);
//        
//        len = myArray.length;
//        for(int i=0;i<myArray.length;i++){
//            
//             petList2.add(q7.setParameter("p", myArray[i]).getSingleResult());
//             System.out.println(q7.setParameter("p", myArray[i]).getSingleResult());
//             System.out.println("////////////////////////////////////////////////////");
//
//        }
//        
//        ChoosePet.getItems().addAll(petList2);  //Set value Pet
//        }
//    }
    
    @FXML
    private void selectType(ActionEvent event){

        roomClass.getValue().toString();
        
        if(roomClass.getValue().toString().contains("All")){
            Room s;

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Room.odb");		
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();

            em.getMetamodel().entity(Room.class); // Query empty database

            Query q1 = em.createQuery("SELECT s FROM Room s");

            List<Room> results = q1.getResultList();
            ObservableList<Room> results2 = FXCollections.<Room>observableArrayList(results);

            tbl.setItems(results2);
        }
        else{
            Room s;

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Room.odb");		
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();

            em.getMetamodel().entity(Room.class); // Query empty database

            Query q1 = em.createQuery("SELECT s FROM Room s where roomClass = :p");
            //Query q2 = em.createQuery("select Type from Customer");

            List<Room> results = q1.setParameter("p", roomClass.getValue().toString()).getResultList();
            ObservableList<Room> results2 = FXCollections.<Room>observableArrayList(results);

            tbl.setItems(results2);
        }
    }
    
    
    @FXML
    private void submit(ActionEvent event) throws IOException{


        /////get Type room/////
        EntityManagerFactory emf5 = Persistence.createEntityManagerFactory("$dist/db/Room.odb");		
	EntityManager em5 = emf5.createEntityManager();
        
        em5.getTransaction().begin();
        
        Query q11 = em5.createQuery("select roomClass from Room where roomNumber = :RoomNumber",Room.class);
        String roomClass_s = q11.setParameter("RoomNumber",selected_roomNum).getSingleResult().toString();
        
        em5.close();
        emf5.close();
        
        ///////////////////////////////////////////////////
        
        /////// get status room ///////////////////////////
        EntityManagerFactory emf6 = Persistence.createEntityManagerFactory("$dist/db/Room.odb");		
	EntityManager em6 = emf6.createEntityManager();
        
        em6.getTransaction().begin();
        
        Query q12 = em6.createQuery("select stat from Room where roomNumber = :RoomNumber",Room.class);
        String status_s = q12.setParameter("RoomNumber",selected_roomNum).getSingleResult().toString();
        
        em6.close();
        emf6.close();
        //////////////////////////////////////////////////
        
        if(status_s.contains("Full")){
            System.out.println("FULL!!");
            //popup
            Stage stage2 = new Stage();
            Parent root2 = FXMLLoader.load(getClass().getResource("PopupFullRoom.fxml"));
            stage2.setScene(new Scene(root2));
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initOwner(submitButton.getScene().getWindow());                
            stage2.showAndWait();  
        }
        else{
     
//            Object pet_s = ChoosePet.getValue();
//            int pet_ID = 0;
//
//            EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/CustomerPet.odb");		
//            EntityManager em = emf.createEntityManager();
//
//            em.getTransaction().begin();
//
//            Query q4 = em.createQuery("select pet_ID from CustomerPetDB where primaryKey = :PrimaryKey_s",CustomerPetDB.class);
//            List petList = q4.setParameter("PrimaryKey_s",ReserveRoomController.primaryKey_tbl).getResultList();
//
//            em.getTransaction().commit();
//            //////////////////////////////////////////////////////////////////////////////////////////////////////
//            
//            ///////////////////////////////Pet DB/////////////////////////////////////////////////////////////////
//
//            //create myArray that converted myList (List) to myArray (Array)
//            Object[] myArray = petList.toArray();
//
//            Pet a;
//            EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("$dist/db/Pet.odb");		
//            EntityManager em2 = emf2.createEntityManager();
//
//            em2.getTransaction().begin();
//
//            Query q7 = em2.createQuery("select name from Pet s where s.pet_ID = :p", Pet.class);
//
//            len = myArray.length;
//            for(int i=0;i<myArray.length;i++){
//                
//                 petList2.add(q7.setParameter("p", myArray[i]).getSingleResult());
//
//            }
//
//            for(int i=0;i<len; i++){
//                if (pet_s == petList2.get(i)){
//                    pet_ID = (int)petList.get(i);
//                }
//            }
//
//            System.out.println(pet_ID);
//            
//            ////////get pet_id from roomNumber ///////////
//            
//            
//
//            EntityManagerFactory emf7 = Persistence.createEntityManagerFactory("$dist/db/CustomerRoomPet.odb");		
//            EntityManager em7 = emf7.createEntityManager();
//
//            em7.getTransaction().begin();
//            
//            em7.getMetamodel().entity(CustomerRoomPetDB.class);
//
//            Query q13 = em7.createQuery("select pet_ID from CustomerRoomPetDB ");
//            //String roomClass_s = q11.setParameter("RoomNumber",selected_roomNum).getSingleResult().toString();
//            List<CustomerRoomPetDB> pet_IDList = q13.getResultList();
//
//            em7.close();
//            emf7.close();

            /////////////////////////////////////////////


//            if(pet_IDList.contains(pet_ID)){
//                System.out.println("ADD PAI LAEWWWWWWW");
//            }
//            else{
                ////// get Customer and Pet name ////
                //Customer
                EntityManagerFactory emf8 = Persistence.createEntityManagerFactory("$dist/db/Customer.odb");		
                EntityManager em8 = emf8.createEntityManager();
                
                em8.getMetamodel().entity(Customer.class);
                Query q20 = em8.createQuery("select name from Customer where primaryKey = :p");
                String Customer_name = q20.setParameter("p", obj.primaryKey_tbl).getSingleResult().toString();
                
                em8.close();
                emf8.close();
                
                //Pet
                EntityManagerFactory emf9 = Persistence.createEntityManagerFactory("$dist/db/Pet.odb");		
                EntityManager em9 = emf9.createEntityManager();
                
                em9.getMetamodel().entity(Pet.class);
                Query q21 = em9.createQuery("select name from Pet where pet_ID = :p");
                String Pet_name = q21.setParameter("p", selectPetObj.petIDFromSelectPet).getSingleResult().toString();
                
                em9.close();
                emf9.close();
                /////////////////////////////////////
                

                CustomerRoomPetDB s;
                EntityManagerFactory emf3 = Persistence.createEntityManagerFactory("$dist/db/CustomerRoomPet.odb");		
                EntityManager em3 = emf3.createEntityManager();

                em3.getTransaction().begin();

                s = new CustomerRoomPetDB(obj.primaryKey_tbl, selected_roomNum, selectPetObj.petIDFromSelectPet, selectPetObj.checkIn_String, selectPetObj.checkOut_String, selectPetObj.totalDay, totalPrice_DB, Customer_name, Pet_name);
                em3.persist(s);

                em3.getTransaction().commit();

                Room roomObj;
                EntityManagerFactory emf4 = Persistence.createEntityManagerFactory("$dist/db/Room.odb");		
                EntityManager em4 = emf4.createEntityManager();

                em4.getTransaction().begin();



                Query q10 = em4.createQuery("DELETE FROM Room s WHERE s.roomNumber = :p");
                q10.setParameter("p", selected_roomNum).executeUpdate();

                System.out.println(roomClass_s);

                roomObj = new Room(selected_roomNum, roomClass_s, "Full");
                em4.persist(roomObj);

                em4.getTransaction().commit();
                
                //// back to dash board ////
                
                Parent reserveLockerParent = FXMLLoader.load(getClass().getResource("StaffDashBoard.fxml"));
                Scene StaffDashBoard = new Scene(reserveLockerParent);

                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                
                //popup 
                Stage stage2 = new Stage();
                Parent root2 = FXMLLoader.load(getClass().getResource("PopupSuccessReserveRoom.fxml"));
                stage2.setScene(new Scene(root2));
                stage2.initModality(Modality.APPLICATION_MODAL);
                stage2.initOwner(submitButton.getScene().getWindow());
                stage2.showAndWait();

                //wait for return and change scene

                window.setScene(StaffDashBoard);
                window.show();
            //} // close if inner if
        } // close if outer if
    }
    
    @FXML
    private void back(ActionEvent event) throws IOException{
        
        Parent selectPet = FXMLLoader.load(getClass().getResource("SelectPetAndDate.fxml"));
        Scene SelectPet = new Scene(selectPet);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(SelectPet);
        window.show();
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        //name.setText(obj.customerName_tbl);
        //String dummy = ""+obj.primaryKey_tbl;
        //id.setText(dummy);
        
        roomClass.getItems().addAll("All", "Standard", "Superior", "Suite", "VIP");
              
        roomNumber_tbl.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        roomClass_tbl.setCellValueFactory(new PropertyValueFactory<Room,String>("roomClass"));
        roomStat_tbl.setCellValueFactory(new PropertyValueFactory<Room,String>("stat"));
        
        Room s;
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Room.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
                
        Query q1 = em.createQuery("SELECT s FROM Room s");
        Query q2 = em.createQuery("select Type from Room");
       
        List<Room> results = q1.getResultList();
        ObservableList<Room> results2 = FXCollections.<Room>observableArrayList(results);
        tbl.setItems(results2);
        
        TableView<Room> table = new TableView<>();
        
        /////// set label //////////////////////////////////////////////////////////////////
        
        customerName.setText(obj.customerName_tbl);
        
        	
	EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("$dist/db/Pet.odb");		
	EntityManager em2 = emf2.createEntityManager();
                
        em2.getTransaction().begin();
                
        Query q3 = em2.createQuery("SELECT name FROM Pet s where pet_ID = :p");
        petName.setText(q3.setParameter("p", petIDFromSelectPet).getSingleResult().toString());
        //System.out.println(petIDFromSelectPet);
        
        totalDay_S.setText(String.valueOf(totalDay));
        
        
        ////////////// Format date ////////////////////
//        checkIn.setConverter(new StringConverter<LocalDate>() {
//            @Override
//            public String toString(LocalDate t) {
//               if(t != null){
//                   return formatter.format(t);
//               }
//               return null;
//            }
//
//            @Override
//            public LocalDate fromString(String string) {
//                if(string != null && !string.trim().isEmpty()){
//                    return LocalDate.parse(string, formatter);
//                }
//                return null;
//            }
//        });
//
//        checkOut.setConverter(new StringConverter<LocalDate>() {
//            @Override
//            public String toString(LocalDate t) {
//               if(t != null){
//                   return formatter.format(t);
//               }
//               return null;
//            }
//
//            @Override
//            public LocalDate fromString(String string) {
//                if(string != null && !string.trim().isEmpty()){
//                    return LocalDate.parse(string, formatter);
//                }
//                return null;
//            }
//        }); 
//        ///////////////////////////////////////////////
//        
//        checkIn.setOnAction((ActionEvent event) -> {
//            System.out.println(formatter.format(checkIn.getValue()));
//        });
//        
//        checkOut.setOnAction((ActionEvent event) -> {
//            System.out.println(formatter.format(checkOut.getValue()));
//            
//            LocalDate dateIn = checkIn.getValue();
//            LocalDate dateOut = checkOut.getValue();
//            
//            checkIn_String = checkIn.getValue().toString();
//            checkOut_String = checkOut.getValue().toString();
//
//            long totalDay0 = (Duration.between(dateIn.atStartOfDay(), dateOut.atStartOfDay()).toDays())+1;
//            
//            totalDay = (int)totalDay0;
//           
//            String totalDay_String = String.valueOf(totalDay); 
//            System.out.println(totalDay_String);
//                        
//            if(totalDay <= 0){
//                System.out.println("Wrong!!");
//            }
//            else{
//                totalDay_S.setText(totalDay_String.toString());
//            }
             ///// ShowPrice /////
//            Room obj;
//
//            switch(selected_roomType){
//
//                case "Standard" : obj = new Standard_Room();    break;
//                case "Superior" : obj = new Superior_Room();    break;
//                case "Suite" : obj = new Suite_Room();  break;
//                case "Vip" : obj = new Vip_Room();  break;
//                default :   obj = new Room(); break;  
//            }
//            
//            float priceBefore = obj.Cost(selectPetObj.totalDay);
//            
//            ////////////////////
//            EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("$dist/db/Customer.odb");		
//            EntityManager em2 = emf2.createEntityManager();
//                
//            em2.getTransaction().begin();
//            em2.getMetamodel().entity(Customer.class);
//        
//            Query q4 = em2.createQuery("select plan from Customer where primaryKey = :PrimaryKey_s", Customer.class);
//            String customer_plan = q4.setParameter("PrimaryKey_s",ReserveRoomController.primaryKey_tbl).getSingleResult().toString();
//            
//            Customer obj2;
//            switch(customer_plan){
//                case "Silver" : obj2 = new Customer_Silver();    break;
//                case "Gold" : obj2 = new Customer_Gold();    break;
//                case "Platinum" : obj2 = new Customer_Platinum();  break;
//                default :   obj2 = new Customer(); break;  
//            }
//            
//            obj2.setCost(priceBefore); //set cost
//            totalPrice_DB = obj2.realCost();
//
//            totalPrice_label = Float.toString(obj2.realCost());
//            
//
//            totalPrice.setText(totalPrice_label);
            /////////////////////
      
        //});
        

        //totalPrice.setText(totolPrice_label);      
    }    
}
