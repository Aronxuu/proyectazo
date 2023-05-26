/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
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
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField nickField;
    @FXML
    private TextField telephoneField;
    private PasswordField passwordField;
    private PasswordField passwordField2;
    @FXML
    private TextField cardField;
    @FXML
    private TextField numberField;
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
    private Image im;
    private Club club;
    @FXML
    private PasswordField passwordFiled2;
    @FXML
    private TextField nameField3;
    @FXML
    private PasswordField passwordFiled3;
    @FXML
    private Label nameError1;
    @FXML
    private Label passwordError;
    @FXML
    private Label surnameError;
    @FXML
    private ImageView userImage;
    
    private boolean hasValidCard = false;
    @FXML
    private Button backButton;
    @FXML
    private Label imError;
    //=========================================================
    // event handler, fired when button is clicked or 
    //                      when the button has the focus and enter is pressed
    @FXML
    private void handleRegister(ActionEvent event) throws IOException {

        boolean nerror = true;
        nickError.setVisible(false);
        pasError.setVisible(false);
        telError.setVisible(false);
        cardError.setVisible(false);
        cardError1.setVisible(false);
        surnameError.setVisible(false);
        nameError1.setVisible(false);
        passwordError.setVisible(false);
        
                Image image = chooseImage(); 
        String name = nameField3.getText();//AKI
        String surname = surnameField.getText();
        String nick = nickField.getText();
        String tel = telephoneField.getText();
        String password = passwordFiled3.getText();
        String password2 = passwordFiled2.getText();//AKi
        String card = cardField.getText();
        String number = numberField.getText();
        String as = "";
         try {
            club = getInstance();
        } catch (ClubDAOException ex) {
            Logger.getLogger(FXMLSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(club.existsLogin(nick)){
            nickError.setText("Nickname already in use, try another");
            nickError.setVisible(true);
            nerror = false;
        }
        if(as.equals(nick)){
            nickError.setText("Unvalid nickname");
            nickError.setVisible(true);
            nerror = false;
        }
        if(!password.equals(password2)){
            pasError.setVisible(true);
            nerror = false;
        }
        if(tel.length() != 9 || !tel.matches("[0-9]+")){
            telError.setVisible(true);
            nerror = false;
        }
        if(!card.equals(as) && !number.equals(as)){
        if(((card.length() != 16 && !card.equals(as)) || !card.matches("[0-9]+")) || ((number.length() != 3 && !number.equals(as))  || !number.matches("[0-9]+"))){
            cardError.setVisible(true);
            nerror = false;
            cardError1.setVisible(true);
            nerror = false;
        }
        }else{
            if(card.equals(as) && !number.equals(as)){
                cardError.setVisible(true);
            nerror = false;
            cardError1.setVisible(true);
            nerror = false;
            }
            
            if(!card.equals(as) && number.equals(as)){
                cardError.setVisible(true);
            nerror = false;
            cardError1.setVisible(true);
            nerror = false;
            }
        }
        //System.out.println(number.length());
        
        if(card.length()==16 && number.length() == 3 && number.matches("[0-9]+") && card.matches("[0-9]+")){hasValidCard=true;}
        if(as.equals(name)){
            nameError1.setVisible(true);
            nerror = false;
        }
        if(as.equals(surname)){
            surnameError.setVisible(true);
            nerror = false;
        }
        if(as.equals(password)){
            passwordError.setVisible(true);
            nerror = false;
        }
        int num = 0;
        try{
            if(!number.equals("")) num = Integer.parseInt(number);
        }catch(NumberFormatException e){
            cardError1.setVisible(true);
            nerror = false;
        }
        if(nerror){
            try {
                //System.out.println("Registrado3");
                Member b = club.registerMember(name, surname, tel, nick, password, card, num, im);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registro Exitoso");
                alert.setHeaderText("Usuario registrado correctamente");
                alert.setContentText("El usuario "+nick+" ha sido registrado, puede iniciar sesión a continuación.");
                alert.show();
                //System.out.println("Registrado");
            } catch (ClubDAOException ex) {
                Logger.getLogger(FXMLRegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/archivosfxml/login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }}


    
    private Image chooseImage() throws IOException {
        if(im != null){ return im;}
        Image image;
        image = new Image("/images/default.png");
        return image;
    }
    FileChooser fileChooser = new FileChooser();
    @FXML
    private void getImage(ActionEvent Event) throws IOException{
        try{
        File file = fileChooser.showOpenDialog(new Stage());
        BufferedImage image = ImageIO.read(file);
        Image image1 = SwingFXUtils.toFXImage(image, null);
        im = image1;
        userImage.setImage(image1);
        imError.setVisible(false);
        }catch(Exception e){
            imError.setVisible(true);
        }
    }

    
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/archivosfxml/login.fxml"));
    Parent root = loader.load();
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }



}
