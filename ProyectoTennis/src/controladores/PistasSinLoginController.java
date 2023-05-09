/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import static model.Club.getInstance;
/**
 * FXML Controller class
 *
 * @author aronx
 */
public class PistasSinLoginController implements Initializable {

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
    private Label centerText;
    /*
    *   Variables de objetos
    */
       private Club club;
    @FXML
    private ListView<String> bookingsListView;
    /**
     * Initializes the controller class.
     */
    private final ObservableList<String> listaprincipal = FXCollections.observableArrayList(

    );
    private final double disabledOpacity = 0.99;
    @FXML
    private Button buttonProfile;
    private Label courtname;
    @FXML
    private Label clubname;
    private Member member1,member2,member3,member4,member5,member6;
    //=========================================================
    // DEBEN conincidir los tipo del ListView y de la lista observable
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            club = getInstance();
        } catch (ClubDAOException ex) {
            Logger.getLogger(PistasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PistasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        clubname.setText(club.getName());
        initializelist();
        
            

    }    

        void initializelist() {
        // en el código de inicialización del controlador
        int max = 1320;
        int ini = 540;
        int dur = club.getBookingDuration();
         String horaL;
        String minutosL;

        while(ini+dur<=1320){
 
            horaL = String.format("%02d", ini/60);
            minutosL = String.format("%02d", ini%60);
            listaprincipal.add("Hora: "+horaL+":"+minutosL+" - "+String.format("%02d", (ini+dur)/60)+":"+String.format("%02d", (ini+dur)%60));
            ini=ini+dur;
        }
        
         bookingsListView.setItems(listaprincipal);
         bookingsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        BooleanBinding noPersonSelected = 
                Bindings.isEmpty(bookingsListView.getSelectionModel().getSelectedItems());
        
        
        }

    @FXML
    private void updateSelection(MouseEvent event) {
        setCourtsGreen();
        
        System.out.println(bookingsListView.getSelectionModel().getSelectedIndex());
        int index = bookingsListView.getSelectionModel().getSelectedIndex();
        centerText.setText("CLICK ON A COURT:\n"+(9+index)+":00 - "+(9+index+1)+":00");
        
        LocalDate today = LocalDate.now();;
        System.out.println(today);
        List<Booking> l = club.getForDayBookings(today);
        int size =l.size();
        List<Court> courts = club.getCourts();
        int sizec =courts.size();
        
        for(int i = 0; i<sizec;i++){
            
            courts.get(i).setName(Integer.toString(i));
        }

        System.out.println(size);
        for(int i = 0; i<size;i++){
            
            Booking b =l.get(i);
           if(b.getFromTime().getHour()==9+index){
               String number = b.getCourt().getName();
               switch(number){
                   case "0":
                       img1.setImage(new Image("/images/pistaroja.png"));
                       //button1.setDisable(true);
                       member1 = b.getMember();
                       button1.setOpacity(disabledOpacity);
                       reserve1.setText(member1.getNickName());
                       break;
                    case "1":
                        img2.setImage(new Image("/images/pistaroja.png"));
                        //button2.setDisable(true);
                        member2 = b.getMember();
                        button2.setOpacity(disabledOpacity);
                        reserve2.setText(member2.getNickName());
                       break;
                    case "2":
                       img3.setImage(new Image("/images/pistaroja.png"));
                       //button3.setDisable(true);
                       member3 = b.getMember();
                       button3.setOpacity(disabledOpacity);
                       reserve3.setText(member3.getNickName());
                       break;
                    case "3":
                       img4.setImage(new Image("/images/pistaroja.png"));
                       //button4.setDisable(true);
                       member4 = b.getMember();
                       System.out.println(member4.getNickName());
                       button4.setOpacity(disabledOpacity);
                       reserve4.setText(member4.getNickName());
                       break;
                    case "4":
                       img5.setImage(new Image("/images/pistaroja.png"));
                       //button5.setDisable(true);
                       member5 = b.getMember();
                       button5.setOpacity(disabledOpacity);
                       reserve5.setText(member5.getNickName());
                       break;
                    case "5":
                       img6.setImage(new Image("/images/pistaroja.png"));
                       //button6.setDisable(true);
                       member6 = b.getMember();
                       button6.setOpacity(disabledOpacity);
                       reserve6.setText(member6.getNickName());
                       break;
                    
               }
           }
           
        }
    }
    private void setCourtsGreen(){
        img1.setImage(new Image("/images/pistaverde.png"));
        img2.setImage(new Image("/images/pistaverde.png"));
        img3.setImage(new Image("/images/pistaverde.png"));
        img4.setImage(new Image("/images/pistaverde.png"));
        img5.setImage(new Image("/images/pistaverde.png"));
        img6.setImage(new Image("/images/pistaverde.png"));
        /*
        button1.setDisable(false);
        button2.setDisable(false);
        button3.setDisable(false);
        button4.setDisable(false); 
        button5.setDisable(false);
        button6.setDisable(false);
        */
        button1.setOpacity(1);
        button2.setOpacity(1);
        button3.setOpacity(1);
        button4.setOpacity(1);
        button5.setOpacity(1);
        button6.setOpacity(1);
        
        
    }

    @FXML
    private void enterButton(MouseEvent event) {//este metodo cambia el cursor dependiendo de la disponibilidad de la pista
        Cursor disabledCursor = new ImageCursor(new Image("/images/cursornodisponible.png"));
        if (event.getSource() == button1) {
            if(button1.getOpacity()==disabledOpacity){
            button1.setCursor(disabledCursor);
            }else{
            button1.setCursor(Cursor.HAND);    
        }
        } else if (event.getSource() == button2) {
            if(button2.getOpacity()==disabledOpacity){
            button2.setCursor(disabledCursor);
            }else{
            button2.setCursor(Cursor.HAND);    
        }
        } else if (event.getSource() == button3) {
            if(button3.getOpacity()==disabledOpacity){
            button3.setCursor(disabledCursor);
            }else{
            button3.setCursor(Cursor.HAND);    
        }
        } else if (event.getSource() == button4) {
            if(button4.getOpacity()==disabledOpacity){
            button4.setCursor(disabledCursor);
            }else{
            button4.setCursor(Cursor.HAND);    
        }
        } else if (event.getSource() == button5) {
            if(button5.getOpacity()==disabledOpacity){
            button5.setCursor(disabledCursor);
            }else{
            button5.setCursor(Cursor.HAND);    
        }
        } else if (event.getSource() == button6) {
            if(button6.getOpacity()==disabledOpacity){
            button6.setCursor(disabledCursor);
            }else{
            button6.setCursor(Cursor.HAND);    
        }
        }
    }

    @FXML
    private void enterProfileButton(MouseEvent event) {
        buttonProfile.setCursor(Cursor.HAND);
    }

    @FXML
    private void clickedCourt(ActionEvent event) throws IOException {
        if(bookingsListView.getSelectionModel().getSelectedIndex()!=-1){
        if(event.getSource() instanceof Button){
            Button unavailable = (Button)event.getSource();
            if(unavailable.getOpacity()==disabledOpacity){//Si el botón no está disponible...
            System.out.println("Hour chosen");
            
         FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/archivosfxml/pistanodisponible.fxml"));
         Stage stage = new Stage();
         Parent root = miCargador.load();
         PistaNoDisponibleController controlPopUp = miCargador.getController();
         Member reservador = getMemberOfClickedCourt(event);
         controlPopUp.initializePopUp(reservador);
         Scene scene = new Scene(root, 300, 200);
         stage.setScene(scene);
         stage.setTitle("Court unavailable");
         stage.initModality(Modality.APPLICATION_MODAL);
         stage.showAndWait();
            }else{//Si el botón está disponible
                
            }
        }
        }else{
            System.out.println("Choose an hour");
        }
    }

    private Member getMemberOfClickedCourt(ActionEvent event){
        if (event.getSource() == button1) {
            return member1;
        
        } else if (event.getSource() == button2) {
            return member2;  
        
        } else if (event.getSource() == button3) {
            return member3;
        } else if (event.getSource() == button4) {
            return member4;
        } else if (event.getSource() == button5) {
            return member5;
        } else if (event.getSource() == button6) {
            return member6;
        }
        else{return null;}
    }

    @FXML
    private void goHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/archivosfxml/login.fxml"));
    Parent root = loader.load();
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

}
