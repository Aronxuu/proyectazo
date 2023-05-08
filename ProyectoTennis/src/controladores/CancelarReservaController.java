/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Booking;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 * FXML Controller class
 *
 * @author aronx
 */
public class CancelarReservaController implements Initializable {

    @FXML
    private Button buttonClick;
    @FXML
    private Label labelMessage;

    /**
     * Initializes the controller class.
     */
    private boolean pulsadoOK = false;
    @FXML
    private Label bookedCourt;
    @FXML
    private Button removeButton;
    private Booking byeBooking;
    private Club c;
    
    public void initializePopUp(String hour,String day,Booking booked,Club club){
        byeBooking = booked;
        bookedCourt.setText(day+" | "+hour);
        c=club;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        labelMessage.getScene().getWindow().hide();
    }

    @FXML
    private void removeAction(ActionEvent event) throws ClubDAOException {
        c.removeBooking(byeBooking);
        labelMessage.getScene().getWindow().hide();
    }
    
}
