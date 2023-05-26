/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Booking;
import model.Club;
import model.ClubDAOException;
import model.Court;
import model.Member;

/**
 * FXML Controller class
 *
 * @author aronx
 */
public class HacerReservaController implements Initializable {

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
    private LocalDate day;
    private int hour;
    private String court;
    private String login;
    private Member logged;
    private Court courto;
    
    public void initializePopUp(String court,int hour,LocalDate day,Club club,String login,String pwd){
        this.hour = hour;
        this.day = day;
        c=club;
        this.court = court;
        int courtshow= Integer.parseInt(court)+1;
        this.login = login;
        logged = c.getMemberByCredentials(login,pwd);
        bookedCourt.setText(day+" | "+hour+":00 - "+(hour+1)+":00 | Court: "+courtshow );
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        labelMessage.setText("You can not cancel the booking if there are \n only 24 hours remaining for the reserve");

    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        labelMessage.getScene().getWindow().hide();
    }

    @FXML
    private void removeAction(ActionEvent event) throws ClubDAOException {
        List<Court> courts = c.getCourts();
        int sizec =courts.size();
        int hour1 = -1,hour2= -1,hour3= -1,hour4= -1;
        for(int i = 0; i<sizec;i++){
            
            if(courts.get(i).getName().equals(court)){
                courto = courts.get(i);
        }
        }
        List<Booking> books = c.getCourtBookings(court, day);
        sizec =books.size();
        int num = 0;
        while(num<sizec){
            
            if(books.get(num).getFromTime().equals(LocalTime.of(hour-2,0))){
                hour1 =num;
           }
            if(books.get(num).getFromTime().equals(LocalTime.of(hour-1,0))){
                hour2 =num;
           }
            if(books.get(num).getFromTime().equals(LocalTime.of(hour+1,0))){
                hour3 =num;
           }
            if(books.get(num).getFromTime().equals(LocalTime.of(hour+2,0))){
                hour4 =num;
           }
            num++;
        }
        
        if(num>=2 && hour1!=-1 && hour2!=-1 && books.get(hour2).getMember().equals(logged) && books.get(hour1).getMember().equals(logged)&&books.get(hour2).getFromTime().equals(LocalTime.of(hour-1,0)) && books.get(hour1).getFromTime().equals(LocalTime.of(hour-2,0))){
              labelMessage.setText("You can not book this court \n You have 2 consecutive hours in this court");  
            }else if(hour3!=-1 && hour4!=-1 && books.get(hour3).getMember().equals(logged) && books.get(hour4).getMember().equals(logged)&&books.get(hour3).getFromTime().equals(LocalTime.of(hour+1,0)) && books.get(hour4).getFromTime().equals(LocalTime.of(hour+2,0))){
              labelMessage.setText("You can not book this court \n You have 2 consecutive hours in this court");  
            }else if(hour2!=-1 && hour3!=-1 && books.get(hour3).getMember().equals(logged) && books.get(hour2).getMember().equals(logged)&&books.get(hour3).getFromTime().equals(LocalTime.of(hour+1,0)) && books.get(hour2).getFromTime().equals(LocalTime.of(hour-1,0))){
              labelMessage.setText("You can not book this court \n You have 2 consecutive hours in this court");   
            }else{
            System.out.println(LocalDateTime.now().toString()+day+LocalTime.of(hour,0)+c.hasCreditCard(login)+courto.getName()+logged.getNickName());
        Booking b = c.registerBooking(LocalDateTime.now(), day, LocalTime.of(hour, 0),c.hasCreditCard(login), courto, logged);
        
        System.out.println(c.hasCreditCard(login)+"Objeto:"+b.getPaid()+b.getMember().getNickName());
        b.setPaid(c.hasCreditCard(login));
        System.out.println(b.getPaid());
        labelMessage.getScene().getWindow().hide();
            
        }
    }
    
}