/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pethotel;

import Customer.Customer;
import LinkDB.CustomerPetDB;
import Pet.Pet;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author Corerid
 */
public class PopupShowCustomerListController implements Initializable {
    
    @FXML
    private TableView<CusPet> table;
    
    @FXML
    private TableColumn<CusPet, String> type;

    @FXML
    private TableColumn age;

    @FXML
    private TableColumn<CusPet, String> extra;

    @FXML
    private TableColumn<CusPet, String> nameT;
    
    @FXML
    private Button back;

    @FXML
    private Label name;

    @FXML
    private Label plan;

    @FXML
    private Label tel;

    @FXML
    private Label email;

    @FXML
    private Label idCardNumber;

    public void back(ActionEvent event) throws IOException{
        
        Stage stage;
        Parent root;
        
        stage = (Stage)back.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Customer s;
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Customer.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
                
        Query q1 = em.createQuery("SELECT name FROM Customer s WHERE s.primaryKey = :PrimaryKey", Customer.class);
        Query q2 = em.createQuery("SELECT email FROM Customer s WHERE s.primaryKey = :PrimaryKey", Customer.class);
        Query q3 = em.createQuery("SELECT tel FROM Customer s WHERE s.primaryKey = :PrimaryKey", Customer.class);
        Query q4 = em.createQuery("SELECT idcardNumber FROM Customer s WHERE s.primaryKey = :PrimaryKey", Customer.class);
        Query q5 = em.createQuery("SELECT plan FROM Customer s WHERE s.primaryKey = :PrimaryKey", Customer.class);
        

        name.setText(q1.setParameter("PrimaryKey", ShowCustomerListController.primaryKeyFromshowmore).getSingleResult().toString());
        email.setText(q2.setParameter("PrimaryKey", ShowCustomerListController.primaryKeyFromshowmore).getSingleResult().toString());
        tel.setText(q3.setParameter("PrimaryKey", ShowCustomerListController.primaryKeyFromshowmore).getSingleResult().toString());
        idCardNumber.setText(q4.setParameter("PrimaryKey", ShowCustomerListController.primaryKeyFromshowmore).getSingleResult().toString());
        plan.setText(q5.setParameter("PrimaryKey", ShowCustomerListController.primaryKeyFromshowmore).getSingleResult().toString());
        
        ////////////////// Table //////////////////////////////////
        
        type.setCellValueFactory(new PropertyValueFactory<CusPet, String>("animal"));
        nameT.setCellValueFactory(new PropertyValueFactory<CusPet, String>("name"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        extra.setCellValueFactory(new PropertyValueFactory<CusPet, String>("extra"));

    
        CustomerPetDB b;
	EntityManagerFactory emf4 = Persistence.createEntityManagerFactory("$dist/db/CustomerPet.odb");		
	EntityManager em4 = emf4.createEntityManager();
        
        em4.getTransaction().begin();
        //Search pet_ID that has Primary Key = PrimaryKey -- return List and store in myList
        Query q6 = em4.createQuery("SELECT pet_ID FROM CustomerPetDB s WHERE s.primaryKey = :PrimaryKey", CustomerPetDB.class);

        List<String> myList = new ArrayList<String>();
        
        myList = q6.setParameter("PrimaryKey", ShowCustomerListController.primaryKeyFromshowmore).getResultList();
        //create myArray that converted myList (List) to myArray (Array
        Object[] myArray = myList.toArray();
        for (Object myObject : myArray) {
           System.out.println(myObject);
        }
        
        
        Pet a;
	EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("$dist/db/Pet.odb");		
	EntityManager em2 = emf2.createEntityManager();
        
        em2.getTransaction().begin();
        
        Query q7 = em2.createQuery("select s from Pet s where s.pet_ID = :p", Pet.class);
	Query q8 = em2.createQuery("select name from Pet s where s.pet_ID = :p", Pet.class);	
        Query q9 = em2.createQuery("select animal from Pet s where s.pet_ID = :p", Pet.class);
        Query q10 = em2.createQuery("select age from Pet s where s.pet_ID = :p", Pet.class);
        Query q11 = em2.createQuery("select extra from Pet s where s.pet_ID = :p", Pet.class);
        
        //create ObservableList names obs for create Tableview
        ObservableList<CusPet> obs = FXCollections.observableArrayList();
      
        //This loop for add object (obs) to CusPet(Class for create table) following amount of myArray.length
        //วนลูปเพิ่มค่าใน class CusPet เพื่อสร้างตารางโดยเฉพาะ ตามจำนวน pet_ID ที่มี (ตามจำนวนสัตว์ที่ customer มี)
        for(int i=0;i<myArray.length;i++){
            
//            System.out.println(q8.setParameter("p", myArray[i]).getSingleResult());
//            System.out.println(q9.setParameter("p", myArray[i]).getSingleResult());
//            System.out.println(q10.setParameter("p", myArray[i]).getSingleResult());
//            System.out.println(q11.setParameter("p", myArray[i]).getSingleResult());
            
            obs.add(new CusPet(q8.setParameter("p", myArray[i]).getSingleResult().toString(),
                               q9.setParameter("p", myArray[i]).getSingleResult().toString(),
                               q11.setParameter("p", myArray[i]).getSingleResult().toString(),
                               (int)q10.setParameter("p", myArray[i]).getSingleResult()));

        }
        
        table.setItems(obs);
        

    }    
}
