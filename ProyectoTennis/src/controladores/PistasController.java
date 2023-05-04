/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.*;
import static model.Club.getInstance;
/**
 * FXML Controller class
 *
 * @author aronx
 */
public class PistasController implements Initializable {

    @FXML
    private Button button16;
    @FXML
    private ImageView img11;
    @FXML
    private Button button1;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img5;
    @FXML
    private ImageView img4;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img6;
    @FXML
    private Label reserve1;
    @FXML
    private Label reserve2;
    @FXML
    private Label reserve5;
    @FXML
    private Label reserve4;
    @FXML
    private Label reserve3;
    @FXML
    private Label reserve6;
    @FXML
    private Button button2;
    @FXML
    private Button button5;
    @FXML
    private Button button4;
    @FXML
    private Button button3;
    @FXML
    private Button button6;
    @FXML
    private DatePicker dpBookingDay;
    
    /*
    *   Variables de objetos
    */
       private Club club;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            club = getInstance();
        } catch (ClubDAOException ex) {
            Logger.getLogger(PistasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PistasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dpBookingDay.setDayCellFactory((DatePicker picker) -> {
        return new DateCell() {
        @Override
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();
            setDisable(empty || date.compareTo(today) < 0 );
             }
            };
        });
        
        

    }    
    
}
