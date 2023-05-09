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
public class PistasController implements Initializable {

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
    private final double yourBookedOpacity = 1.5;
    @FXML
    private Button buttonProfile;
    private Label courtname;
    @FXML
    private Label clubname;
    private Member member1,member2,member3,member4,member5,member6;
    private Booking b1,b2,b3,b4,b5,b6;
    private String hour;
    private String loggeduser;
    private MouseEvent updateevent;
   
    //=========================================================
    // DEBEN conincidir los tipo del ListView y de la lista observable
    @FXML
    private ListView<Booking> myBookingListView;
    
    private final ObservableList<Booking> listaPrincReservados = FXCollections.observableArrayList(

    );
   
    public void getLogged(String login){
        loggeduser = login;
    }
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

         dpBookingDay.setValue(LocalDate.now());
        initializelist();
        initializeListMyBooking();
            

    }    

        void initializeListMyBooking(){
            loggeduser= "user2";
            List<Booking> reser = club.getUserBookings(loggeduser);
            int i = 10;
            int j = 0;
            int tamaño = reser.size();
            while(i!=0 && tamaño!=0){
                if(j!=0 && reser.get(j-1).getFromTime().equals(reser.get(j).getFromTime())){
                    System.out.println("Aquí hay dos+ pistas misma hora");
                }else{
                    listaPrincReservados.add(reser.get(j));
                    i--;
                }

                j++;
                tamaño--;
            }

            myBookingListView.setItems(listaPrincReservados);
            myBookingListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            class BookingListCell extends ListCell<Booking>{

                @Override
                protected void updateItem(Booking t, boolean bln) {
                    super.updateItem(t, bln); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                    if (t==null || bln) setText(null);
                    else{
                        int x = t.getFromTime().getHour() + 1;
                        setText(t.getMadeForDay().toString() + ", " + t.getFromTime().toString() + " - " + x + ":00");
                    }

                }

            }
            myBookingListView.setCellFactory(c-> new BookingListCell());
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
        updateevent=event;
        
        
        
        System.out.println(bookingsListView.getSelectionModel().getSelectedIndex());
        int index = bookingsListView.getSelectionModel().getSelectedIndex();
        centerText.setText("CLICK ON A COURT:\n"+(9+index)+":00 - "+(9+index+1)+":00");
        hour = (9+index)+":00 - "+(9+index+1)+":00";
        LocalDate forDay = dpBookingDay.valueProperty().get();
        System.out.println(forDay);
        List<Booking> l = club.getForDayBookings(forDay);
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
                       member1 = b.getMember();
                       if(b.getMember().getNickName().equals(loggeduser)){
                       img1.setImage(new Image("/images/pistaazul.png"));                           
                       button1.setOpacity(yourBookedOpacity);
                       b1=b;
                       } else{
                       img1.setImage(new Image("/images/pistaroja.png"));
                       //button1.setDisable(true);
                      reserve1.setText(member1.getNickName());
                       button1.setOpacity(disabledOpacity);
                       }
                       break;
                    case "1":
                        member2 = b.getMember();
                        if(b.getMember().getNickName().equals(loggeduser)){
                       img2.setImage(new Image("/images/pistaazul.png"));                           
                       button2.setOpacity(yourBookedOpacity);    
                       b2=b;
                       } else{
                       img2.setImage(new Image("/images/pistaroja.png"));
                       //button1.setDisable(true);
                      reserve2.setText(member2.getNickName());
                       button2.setOpacity(disabledOpacity);
                       }
                       break;
                    case "2":
                        member3 = b.getMember();
                       if(b.getMember().getNickName().equals(loggeduser)){
                       img3.setImage(new Image("/images/pistaazul.png"));                           
                       button3.setOpacity(yourBookedOpacity);    
                       b3=b;
                       } else{
                       img3.setImage(new Image("/images/pistaroja.png"));
                       //button1.setDisable(true);
                      reserve3.setText(member3.getNickName());
                       button3.setOpacity(disabledOpacity);
                       }
                       break;
                    case "3":
                        member4 = b.getMember();
                       if(b.getMember().getNickName().equals(loggeduser)){
                       img4.setImage(new Image("/images/pistaazul.png"));                           
                       button4.setOpacity(yourBookedOpacity);    
                       b4=b;
                       } else{
                       img4.setImage(new Image("/images/pistaroja.png"));
                       //button1.setDisable(true);
                      reserve4.setText(member4.getNickName());
                       button4.setOpacity(disabledOpacity);
                       }
                       break;
                    case "4":
                        member5 = b.getMember();
                       if(b.getMember().getNickName().equals(loggeduser)){
                       img5.setImage(new Image("/images/pistaazul.png"));                           
                       button5.setOpacity(yourBookedOpacity); 
                       b5=b;
                       } else{
                       img5.setImage(new Image("/images/pistaroja.png"));
                       //button1.setDisable(true);
                      reserve5.setText(member5.getNickName());
                       button5.setOpacity(disabledOpacity);
                       };
                       break;
                    case "5":
                        member6 = b.getMember();
                       if(b.getMember().getNickName().equals(loggeduser)){
                       img6.setImage(new Image("/images/pistaazul.png"));                           
                       button6.setOpacity(yourBookedOpacity);    
                       b6=b;
                       } else{
                       img6.setImage(new Image("/images/pistaroja.png"));
                       //button1.setDisable(true);
                       reserve6.setText(member6.getNickName());
                       button6.setOpacity(disabledOpacity);
                       };
                    
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
        reserve1.setText("");
        reserve2.setText("");
        reserve3.setText("");
        reserve4.setText("");
        reserve5.setText("");
        reserve6.setText("");
        
        
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
            Button button = (Button)event.getSource();
            if(button.getOpacity()==disabledOpacity){//Si el botón no está disponible...
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
            }else if(button.getOpacity()==yourBookedOpacity){//Si el botón está disponible
         FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/archivosfxml/cancelarreserva.fxml"));
         Stage stage = new Stage();
         Parent root = miCargador.load();
         CancelarReservaController controlPopUp = miCargador.getController();
         String court;
         Booking remove;
        if (event.getSource() == button1) {
            court = hour+" | Court: 1";
            remove = b1;
        } else if (event.getSource() == button2) {
            court = hour+" | Court: 2";
            remove = b2;
        } else if (event.getSource() == button3) {
            court = hour+" | Court: 3";
            remove = b3;
        } else if (event.getSource() == button4) {
            court = hour+" | Court: 4";
            remove = b4;
        } else if (event.getSource() == button5) {
            court = hour+" | Court: 5";
            remove = b5;
        } else if (event.getSource() == button6) {
            court = hour+" | Court: 6";
            remove = b6;
        }else{court="";remove=null;}
         controlPopUp.initializePopUp(court,dpBookingDay.valueProperty().get().toString(),remove,club);
         Scene scene = new Scene(root, 300, 200);
         stage.setScene(scene);
         stage.setTitle("Court unavailable");
         stage.initModality(Modality.APPLICATION_MODAL);
         stage.showAndWait();
         updateSelection(updateevent);
            }
        }else{
            
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
    private void updateSelection2(MouseEvent event) {
System.out.println("Estamos aquí");
        setCourtsGreen();
        
        List<Court> courts = club.getCourts();
        int sizec =courts.size();
        
        for(int i = 0; i<sizec;i++){
            
            courts.get(i).setName(Integer.toString(i));
        }
        
        System.out.println("El índice es " + myBookingListView.getSelectionModel().getSelectedIndex());
        Booking item = myBookingListView.getSelectionModel().getSelectedItem();
        List<Booking> resEseDia = club.getForDayBookings(item.getMadeForDay());
        List<Booking> resHora = new ArrayList<>();
        System.out.println("Estamos aquí 2");
        for(int i=0; i<resEseDia.size(); i++){
            if(item.getFromTime().equals(resEseDia.get(i).getFromTime())){
                resHora.add(resEseDia.get(i));
            }
        }
        
        
        centerText.setText("CLICK ON A COURT TO SEE\n MORE DETAILS");
        
        
        
        
        for(int i=0; i<resHora.size(); i++){
            String urCourt = resHora.get(i).getCourt().getName();
            switch(urCourt){
                case "0":
                    member1 = resHora.get(i).getMember();
                    if(resHora.get(i).getMember().getNickName().equals(loggeduser)){
                        img1.setImage(new Image("/images/pistaazul.png"));
                        if(resHora.get(i).getPaid()==true){
                            reserve1.setText("PAID");
                        }else{reserve1.setText("NOT PAID");}
                    }else{
                        img1.setImage(new Image("/images/pistaroja.png"));
                        reserve1.setText(member1.getNickName());
                    }                   
                    System.out.println("funciona");
                    break;
                case "1":
                    member2 = resHora.get(i).getMember();
                    if(resHora.get(i).getMember().getNickName().equals(loggeduser)){
                        img2.setImage(new Image("/images/pistaazul.png"));
                        if(resHora.get(i).getPaid()==true){
                            reserve2.setText("PAID");
                        }else{reserve2.setText("NOT PAID");}
                    }else{
                        img2.setImage(new Image("/images/pistaroja.png"));
                        reserve2.setText(member2.getNickName());
                    }                   
                    System.out.println("funciona");
                    break;
                case "2":
                    member3 = resHora.get(i).getMember();
                    if(resHora.get(i).getMember().getNickName().equals(loggeduser)){
                        img3.setImage(new Image("/images/pistaazul.png"));
                        if(resHora.get(i).getPaid()==true){
                            reserve3.setText("PAID");
                        }else{reserve3.setText("NOT PAID");}
                    }else{
                        img3.setImage(new Image("/images/pistaroja.png"));
                        reserve3.setText(member3.getNickName());
                    }
                    System.out.println("funciona");
                    break;
                case "3":
                    member4 = resHora.get(i).getMember();
                    if(resHora.get(i).getMember().getNickName().equals(loggeduser)){
                        img4.setImage(new Image("/images/pistaazul.png"));
                        if(resHora.get(i).getPaid()==true){
                            reserve4.setText("PAID");
                        }else{reserve4.setText("NOT PAID");}
                    }else{
                        img4.setImage(new Image("/images/pistaroja.png"));
                        reserve4.setText(member4.getNickName());
                    }
                    System.out.println("funciona");
                    break;
                case "4":
                    member5 = resHora.get(i).getMember();
                    if(resHora.get(i).getMember().getNickName().equals(loggeduser)){
                        img5.setImage(new Image("/images/pistaazul.png"));
                        if(resHora.get(i).getPaid()==true){
                            reserve5.setText("PAID");
                        }else{reserve5.setText("NOT PAID");}
                    }else{
                        img5.setImage(new Image("/images/pistaroja.png"));
                        reserve5.setText(member5.getNickName());
                    }
                    System.out.println("funciona");
                    break;
                case "5":
                    member6 = resHora.get(i).getMember();
                    if(resHora.get(i).getMember().getNickName().equals(loggeduser)){
                        img6.setImage(new Image("/images/pistaazul.png"));
                        if(resHora.get(i).getPaid()==true){
                            reserve6.setText("PAID");
                        }else{reserve6.setText("NOT PAID");}
                    }else{
                        img6.setImage(new Image("/images/pistaroja.png"));
                        reserve6.setText(member6.getNickName());
                    }
                    System.out.println("funciona");
                    break;
            
            }
        }
    }


    @FXML
    private void clickedDelete(ActionEvent event) {
    }

}
