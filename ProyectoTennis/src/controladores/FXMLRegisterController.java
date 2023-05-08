/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Club;
import static model.Club.getInstance;
import model.ClubDAOException;
import model.Member;

/**
 *
 * @author jsoler
 */
public class FXMLRegisterController implements Initializable {
    //========================================================
    // objects defined into FXML file with fx:id 
    @FXML
    private Button register;
    @FXML
    private Label labelMessage;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField nickField;
    @FXML
    private TextField telephoneField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordField2;
    @FXML
    private TextField cardField;
    @FXML
    private TextField numberField;
    @FXML
    private Label nullError;
    @FXML
    private Label nickError;
    @FXML
    private Label pasError;
    @FXML
    private Label telError;
    @FXML
    private Label cardError;
    @FXML
    private Label cardError1;
    @FXML
    private ComboBox<String> imageComboBox;
    private Image im;
    private Club club;
    //=========================================================
    // event handler, fired when button is clicked or 
    //                      when the button has the focus and enter is pressed
    @FXML
    private void handleRegister(ActionEvent event) throws IOException {
        
        boolean nerror = true;
        nullError.setVisible(false);
        nickError.setVisible(false);
        pasError.setVisible(false);
        telError.setVisible(false);
        cardError.setVisible(false);
        cardError1.setVisible(false);
        String name = nameField.getText();
        String surname = surnameField.getText();
        String nick = nickField.getText();
        String tel = telephoneField.getText();
        String password = passwordField.getText();
        String password2 = passwordField2.getText();
        String card = cardField.getText();
        String number = numberField.getText();
        String as = "";
         try {
            club = getInstance();
            club.setInitialData();
        } catch (ClubDAOException ex) {
            Logger.getLogger(FXMLSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(as.equals(name) | as.equals(surname) | as.equals(nick) | as.equals(tel) | as.equals(password) | as.equals(password2) | as.equals(card) | as.equals(number)){
            nullError.setVisible(true);
            nerror = false;
        }
        if(club.existsLogin(nick)){
            nickError.setVisible(true);
            nerror = false;
        }
        if(!password.equals(password2)){
            pasError.setVisible(true);
            nerror = false;
        }
        if(tel.length() != 9){
            telError.setVisible(true);
            nerror = false;
        }
        if(card.length() != 16){
            cardError.setVisible(true);
            nerror = false;
        }
        if(number.length() != 3){
            cardError1.setVisible(true);
            nerror = false;
        }
        int num = Integer.parseInt(number);
        if(nerror){
            try {
                Member b = club.registerMember(name, surname, tel, nick, password, card, num, im);
            } catch (ClubDAOException ex) {
                Logger.getLogger(FXMLRegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pistas.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }}
    @FXML
    private void handleImageSelection(ActionEvent event) {
    
    }


    
    
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
