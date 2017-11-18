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
import Pet.Cat;
import Pet.Dog;
import Pet.Pet;
import Pet.Rabbit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import static pethotel.AddPetController.primaryKeyFromAddPet;

/**
 * FXML Controller class
 *
 * @author Corerid
 */
public class AddPetPopupController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label customerName;

    @FXML
    private ComboBox type;

    @FXML
    private JFXTextField petName;

    @FXML
    private JFXTextField petAge;

    @FXML
    private JFXTextField extra;

    @FXML
    private JFXButton back;

    @FXML
    private JFXButton confirmButton;

    public void addPet(ActionEvent event)throws IOException{
        
        String name_s = petName.getText();
        int age_s = Integer.parseInt(petAge.getText());
        String extra_s = extra.getText();
        String type_s = type.getValue().toString();
        
        Customer s;
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Customer.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
                
        Query q3 = em.createQuery("select max(pet_ID) from Pet");
        Query q6 = em.createQuery("SELECT s FROM Pet s"); 
        
	//List<Customer> results = q2.getResultList();
        
        em.close();
        emf.close(); //close connection before add data
        
        AddPetController obj;
        obj = new AddPetController();
        
        Pet b ;
        
        int dummyPrime = 1;
            
        switch(type_s){
                
            case "Cat" : b = new Cat(dummyPrime, obj.customerNameFromAddPet, name_s, type_s, extra_s, age_s);    break;
            case "Dog" : b = new Dog(dummyPrime, obj.customerNameFromAddPet, name_s, type_s, extra_s, age_s);    break;
            case "Rabbit" : b = new Rabbit(dummyPrime, obj.customerNameFromAddPet, name_s, type_s, extra_s, age_s);  break;
            default :   b = new Pet(dummyPrime ,obj.customerNameFromAddPet, name_s, type_s, extra_s, age_s);
                
        }
        
        
        
            b.addPet(obj.customerNameFromAddPet,name_s, type_s, extra_s, age_s);
         
            System.out.println("Yeah!");
            
        /////////////// CustomerPetDB Part ////////////////////////////////////
//        CustomerPetDB obj2;
//        
//	EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("$dist/db/CustomerPet.odb");		
//	EntityManager em2 = emf2.createEntityManager();
//        
//        em2.getTransaction().begin();
//        
//        obj2 = new CustomerPetDB(obj.primaryKeyFromAddPet,);
//	em.persist(obj2);
//
//	em.getTransaction().commit();
        ///////////////////////////////////////////////////////////////////////
        
    }
    
    public void back(ActionEvent event) throws IOException{
        
        Stage stage;
        Parent root;
        
        stage = (Stage)back.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AddPetController obj;
        obj = new AddPetController();
        
        customerName.setText(obj.customerNameFromAddPet);
        type.getItems().addAll("Dog", "Cat", "Rabbit");
    }    
    
}
