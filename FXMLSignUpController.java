package controladores;


import com.sun.javafx.scene.control.skin.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import model.*;
import model.Club.*;
import static model.Club.getInstance;

/**
 *
 * @author Ivan
 */
public class FXMLSignUpController implements Initializable {
    
    @FXML
    private TextField emailInput;
    @FXML
    private PasswordField pwdInput;
    @FXML
    private Label pwdError;
    @FXML
    private Button Login;
    
    private Club club;
    
    public String login;
    public String pasword;
    
    @FXML
    private Label emailError;
    
    @FXML
    private void handleRegister(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/archivosfxml/register.fxml"));
    Parent root = loader.load();
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();

    }
    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        pasword = pwdInput.getText();
        login = emailInput.getText();
        System.out.println(pasword+login);
        try {
            club = getInstance();

        } catch (ClubDAOException ex) {
            Logger.getLogger(FXMLSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
       Member b = club.getMemberByCredentials(login, pasword);
       if(b == null){
           
            pwdError.setVisible(true);
       }else{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/archivosfxml/pistas.fxml"));
        loader.setControllerFactory(controllerClass -> {

        PistasController controller = new PistasController();
        controller.setLogin(login);
        return controller;
    
        });
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
       }
        }catch(Exception e){
            pwdError.setVisible(true);
        }
       
    }
   
    private void hideErrorMessage()
    { 
        pwdError.setVisible(false);
    }
    
    @FXML
    private void handleButton(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/archivosfxml/pistassinlogin.fxml"));//DEBERÍA SER pistassinlogin.fxml pero pongo pistas.fxml para testear
    Parent root = loader.load();
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    public String getLogin(){
        return login;
    }
    public String getPasword(){
        return pasword;
    }
    
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            club = getInstance();
        } catch (ClubDAOException | IOException ex) {
            Logger.getLogger(PistasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        hideErrorMessage();

    } 
   
    
}