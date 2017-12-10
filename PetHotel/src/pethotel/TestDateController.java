/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pethotel;

import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import static java.util.concurrent.TimeUnit.DAYS;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Corerid
 */
public class TestDateController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private DatePicker in2;

    @FXML
    private DatePicker out2;
    
    @FXML
    private JFXDatePicker in;

    @FXML
    private JFXDatePicker out;
    
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
    
    public void show(ActionEvent event){
//        LocalDate dateIn = in2.getValue();
//         LocalDate dateOut = out2.getValue();
//         
//         System.out.println(formatter.format(out2.getValue()));
//         
//        
//         
//        long kuy = Duration.between(dateIn.atStartOfDay(), dateOut.atStartOfDay()).toDays();
//         
//        if(kuy <= 0){
//            System.out.println("KUY");
//        }
//        else{
//            System.out.println(kuy);
//        } 
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            out.setConverter(new StringConverter<LocalDate>() {
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
            
            out.setOnAction((ActionEvent event) -> {
                System.out.println(formatter.format(in2.getValue()));
            });

    }    
    
}
