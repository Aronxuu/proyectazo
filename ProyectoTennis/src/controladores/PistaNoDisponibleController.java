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
import model.Member;

/**
 * FXML Controller class
 *
 * @author aronx
 */
public class PistaNoDisponibleController implements Initializable {

    @FXML
    private Label userBooker;
    @FXML
    private Button buttonClick;
    @FXML
    private Label labelMessage;

    /**
     * Initializes the controller class.
     */
        private Member reservador;
    private boolean pulsadoOK = false;
    public void initializePopUp(Member hula){
        this.reservador = hula;
                userBooker.setText("Court booked by: "+reservador.getNickName());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        labelMessage.getScene().getWindow().hide();
    }
    
}
