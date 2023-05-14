/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @FXML
    private Label errorMessage;
    
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
        bookedCourt.getScene().getWindow().hide();
    }

    @FXML
    private void removeAction(ActionEvent event) throws ClubDAOException {
        System.out.println(byeBooking.getMadeForDay().toString()+LocalDateTime.now().plusDays(1)+byeBooking.getBookingDate().compareTo(LocalDateTime.now().plusDays(1)));
        if(byeBooking.getMadeForDay().compareTo(LocalDate.now().plusDays(1))>0){
        c.removeBooking(byeBooking);
        bookedCourt.getScene().getWindow().hide();
        }else{
            errorMessage.setText("You can not remove the booking\n Motive: less than 24h remaining");
            errorMessage.setVisible(true);
        }
    }
    
}
