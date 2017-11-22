/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pethotel;

import LinkDB.CustomerPetDB;
import LinkDB.CustomerRoomPetDB;
import Pet.Pet;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import static pethotel.ReserveRoomController.customerPlan_tbl;


/**
 * FXML Controller class
 *
 * @author Corerid
 */
public class SelectPetAndDateController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
    
    @FXML
    private JFXComboBox  ChoosePet;
    
    @FXML
    private JFXDatePicker checkIn;

    @FXML
    private JFXDatePicker checkOut;
    
    @FXML
    private Label totalDay_S;
    
    @FXML
    private Label plan;

    @FXML
    private Label name;
    
    //static publi
    int statCP = 0;
    List petList2 = new ArrayList(); //name of customer's pet
    List petList3 = new ArrayList();
    int len;
    static public int petIDFromSelectPet;
    static public String checkIn_String;
    static public String checkOut_String;
    static public int totalDay;
    
    ReserveRoomController obj = new ReserveRoomController();
    
    @FXML
    private void choosePet(MouseEvent event){
        if(statCP == 0){                    //limited set Combbox 
        statCP = 1;                         //Set Combobox State
        
        //////////////////////Data BASE PET/////////////////////////////////////////////////////////////////        CustomerPet.odb
        //CustomerPetDB s; 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/CustomerPet.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
        
        Query q4 = em.createQuery("select pet_ID from CustomerPetDB where primaryKey = :PrimaryKey_s",CustomerPetDB.class);
        List petList = q4.setParameter("PrimaryKey_s",ReserveRoomController.primaryKey_tbl).getResultList();
        //petList3.add(q4.setParameter("PrimaryKey_s",ReserveRoomController.primaryKey_tbl).getResultList());

        System.out.println(petList);
        
        em.getTransaction().commit();
        /////////////////////////////////////////////////////////////////////////////////////////////////////
         
        ///////////////////////////////Pet DB////////////////////////////////////////////////////////////////
         
        //create myArray that converted myList (List) to myArray (Array
        Object[] myArray = petList.toArray(); //array store pet_ID

        for (Object myObject : myArray) {
           System.out.println(myObject);
        }

        Pet a;
	EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("$dist/db/Pet.odb");		
	EntityManager em2 = emf2.createEntityManager();
        
        em2.getTransaction().begin();
        
        Query q7 = em2.createQuery("select name from Pet s where s.pet_ID = :p", Pet.class);
        
        len = myArray.length;
        for(int i=0;i<myArray.length;i++){
            
             petList2.add(q7.setParameter("p", myArray[i]).getSingleResult());
             System.out.println(q7.setParameter("p", myArray[i]).getSingleResult());
             System.out.println("////////////////////////////////////////////////////");

        }
        
        ChoosePet.getItems().addAll(petList2);  //Set value Pet
        }
    }
    
    public void next(ActionEvent event) throws IOException{
        Object pet_s = ChoosePet.getValue();
        int pet_ID = 0;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/CustomerPet.odb");		
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Query q4 = em.createQuery("select pet_ID from CustomerPetDB where primaryKey = :PrimaryKey_s",CustomerPetDB.class);
        List petList = q4.setParameter("PrimaryKey_s",ReserveRoomController.primaryKey_tbl).getResultList();

        em.getTransaction().commit();
            ///////////////////////////////Pet DB/////////////////////////////////////////////////////////////////

            //create myArray that converted myList (List) to myArray (Array)
        Object[] myArray = petList.toArray();

        Pet a;
        EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("$dist/db/Pet.odb");		
        EntityManager em2 = emf2.createEntityManager();

        em2.getTransaction().begin();

        Query q7 = em2.createQuery("select name from Pet s where s.pet_ID = :p", Pet.class);

        len = myArray.length;
        for(int i=0;i<myArray.length;i++){

             petList2.add(q7.setParameter("p", myArray[i]).getSingleResult());

        }

        for(int i=0;i<len; i++){
            if (pet_s == petList2.get(i)){
                pet_ID = (int)petList.get(i);
            }
        }

        System.out.println(pet_ID);

        ////////get pet_id from roomNumber ///////////



        EntityManagerFactory emf7 = Persistence.createEntityManagerFactory("$dist/db/CustomerRoomPet.odb");		
        EntityManager em7 = emf7.createEntityManager();

        em7.getTransaction().begin();

        em7.getMetamodel().entity(CustomerRoomPetDB.class);

        Query q13 = em7.createQuery("select pet_ID from CustomerRoomPetDB ");
        //String roomClass_s = q11.setParameter("RoomNumber",selected_roomNum).getSingleResult().toString();
        List<CustomerRoomPetDB> pet_IDList = q13.getResultList();

        em7.close();
        emf7.close();

        /////////////////////////////////////////////

        if(pet_IDList.contains(pet_ID)){
            System.out.println("This pet's already added!!");
            //popup
            Stage stage2 = new Stage();
            Parent root2 = FXMLLoader.load(getClass().getResource("PopupSamePet.fxml"));
            stage2.setScene(new Scene(root2));
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initOwner(checkOut.getScene().getWindow());                
            stage2.showAndWait();
            
        }
        else{
            petIDFromSelectPet = pet_ID;
            
            Parent loginCustomerParent = FXMLLoader.load(getClass().getResource("SelectRoom.fxml"));
            Scene LoginCustomer = new Scene(loginCustomerParent);
        
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
            window.setScene(LoginCustomer);
            window.show();
        
        }
     
    }
    
    @FXML
    private void back(ActionEvent event) throws IOException{
        
        Parent reserveRoom = FXMLLoader.load(getClass().getResource("ReserveRoom.fxml"));
        Scene ReserveRoom = new Scene(reserveRoom);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(ReserveRoom);
        window.show();
        
    }    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        name.setText(obj.customerName_tbl);
        plan.setText(obj.customerPlan_tbl);
        
 ////////////// Format date ////////////////////
        checkIn.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate t) {
               if(t != null){
                   return formatter.format(t);
               }
               return null;
            }

            @Override
            public LocalDate fromString(String string) {
                if(string != null && !string.trim().isEmpty()){
                    return LocalDate.parse(string, formatter);
                }
                return null;
            }
        });

        checkOut.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate t) {
               if(t != null){
                   return formatter.format(t);
               }
               return null;
            }

            @Override
            public LocalDate fromString(String string) {
                if(string != null && !string.trim().isEmpty()){
                    return LocalDate.parse(string, formatter);
                }
                return null;
            }
        }); 
        ///////////////////////////////////////////////
        
        checkIn.setOnAction((ActionEvent event) -> {
            System.out.println(formatter.format(checkIn.getValue()));
        });
        
        checkOut.setOnAction((ActionEvent event) -> {
            System.out.println(formatter.format(checkOut.getValue()));
            
            LocalDate dateIn = checkIn.getValue();
            LocalDate dateOut = checkOut.getValue();
            
            checkIn_String = checkIn.getValue().toString();
            checkOut_String = checkOut.getValue().toString();

            long totalDay0 = (Duration.between(dateIn.atStartOfDay(), dateOut.atStartOfDay()).toDays())+1;
            
            totalDay = (int)totalDay0;
           
            String totalDay_String = String.valueOf(totalDay); 
            System.out.println(totalDay_String);
                        
            if(totalDay <= 0){
                try {
                    System.out.println("Wrong!!");
                    //popup
                    Stage stage2 = new Stage();
                    Parent root2 = FXMLLoader.load(getClass().getResource("PopupWrongDate.fxml"));
                    stage2.setScene(new Scene(root2));
                    stage2.initModality(Modality.APPLICATION_MODAL);
                    stage2.initOwner(checkOut.getScene().getWindow());                
                    stage2.showAndWait();
                } catch (IOException ex) {
                    Logger.getLogger(SelectPetAndDateController.class.getName()).log(Level.SEVERE, null, ex);
                }
     
            }
            else{
                totalDay_S.setText(totalDay_String.toString());
            }
        });
        
    }
}
