/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;


import com.sun.javafx.scene.control.skin.Utils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


/**
 *
 * @author svalero
 */
public class FXMLSignUpController implements Initializable {


 
    //properties to control valid fieds values. 
    private BooleanProperty validPassword;
    private BooleanProperty validEmail;
    private BooleanProperty equalPasswords;  

    //private BooleanBinding validFields;
    
    //When to strings are equal, compareTo returns zero
    private final int EQUALS = 0;  
    @FXML
    private TextField emailInput;
    @FXML
    private Label emailError;
    @FXML
    private PasswordField pwdInput;
    @FXML
    private Label pwdError;
    @FXML
    private Label repeatError;
    @FXML
    private PasswordField repeatInput;
    
   
    

    /**
     * Updates the boolProp to false.Changes to red the background of the edit. 
     * Makes the error label visible and sends the focus to the edit. 
     * @param errorLabel label added to alert the user
     * @param textField edit text added to allow user to introduce the value
     * @param boolProp property which stores if the value is correct or not
     */
    private void manageError(Label errorLabel,TextField textField, BooleanProperty boolProp ){
        boolProp.setValue(Boolean.FALSE);
        showErrorMessage(errorLabel,textField);
        textField.requestFocus();
 
    }
    /**
     * Updates the boolProp to true. Changes the background 
     * of the edit to the default value. Makes the error label invisible. 
     * @param errorLabel label added to alert the user
     * @param textField edit text added to allow user to introduce the value
     * @param boolProp property which stores if the value is correct or not
     */
    private void manageCorrect(Label errorLabel,TextField textField, BooleanProperty boolProp ){
        boolProp.setValue(Boolean.TRUE);
        hideErrorMessage(errorLabel,textField);
        
    }
    /**
     * Changes to red the background of the edit and
     * makes the error label visible
     * @param errorLabel
     * @param textField 
     */
    private void showErrorMessage(Label errorLabel,TextField textField)
    {
        errorLabel.visibleProperty().set(true);
        textField.styleProperty().setValue("-fx-background-color: #FCE5E0");    
    }
    /**
     * Changes the background of the edit to the default value
     * and makes the error label invisible.
     * @param errorLabel
     * @param textField 
     */
    private void hideErrorMessage(Label errorLabel,TextField textField)
    {
        errorLabel.visibleProperty().set(false);
        textField.styleProperty().setValue("");
    }
    /*
    private void checkEditEmail()
    {
        if(!Utils.checkEmail(emailInput.textProperty().getValueSafe()))
        //Incorrect email
        manageError(emailError, emailInput,validEmail );
        else
        manageCorrect(emailError, emailInput,validEmail ); 
    }
    private void checkEditPwd()
    {
        if(!Utils.checkPassword(pwdInput.textProperty().getValueSafe()))
        //Incorrect email
        manageError(pwdError, pwdInput,validPassword);
        else
        manageCorrect(pwdError, pwdInput,validPassword ); 
    }
*/
    private void checkEditPwd2()
    {
        if(!repeatInput.getText().equals(pwdInput.getText()))
        //Incorrect email
        manageError(repeatError, repeatInput,equalPasswords );
        else
        manageCorrect(repeatError, repeatInput,equalPasswords ); 
    }
    
    

    
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        validEmail = new SimpleBooleanProperty();
        validPassword = new SimpleBooleanProperty();   
        equalPasswords = new SimpleBooleanProperty();
        
        validPassword.setValue(Boolean.FALSE);
        validEmail.setValue(Boolean.FALSE);
        equalPasswords.setValue(Boolean.FALSE);
        /*
       emailInput.focusedProperty().addListener((observable, oldValue, newValue)->{
        if(!newValue){ //focus lost.
        checkEditEmail();
        }
        });
       
       pwdInput.focusedProperty().addListener((observable, oldValue, newValue)->{
        if(!newValue){ //focus lost.
        checkEditPwd();
        }
        }); 
       */
       repeatInput.focusedProperty().addListener((observable, oldValue, newValue)->{
        if(!newValue){ //focus lost.
        checkEditPwd2();
        }
        }); 
       
        
        BooleanBinding validFields = Bindings.and(validEmail, validPassword)
                 .and(equalPasswords);
         

        

    } 
   
    
}