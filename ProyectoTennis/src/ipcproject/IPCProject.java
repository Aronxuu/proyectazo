/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.HOla sergio soy Aron
 */
package ipcproject;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Booking;
import model.Club;
import model.ClubDAOException;
import model.Court;
import model.Member;


public class IPCProject extends Application {
    //dsdfasdfsdafasdfsd
    @Override
    public void start(Stage stage) throws Exception {
        //======================================================================
        // 1- creación del grafo de escena a partir del fichero FXML
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("/archivosfxml/login.fxml"));
        Parent root = loader.load();
        //======================================================================
        // 2- creación de la escena con el nodo raiz del grafo de escena
        Scene scene = new Scene(root);
        //======================================================================
        // 3- asiganación de la escena al Stage que recibe el metodo 
        //     - configuracion del stage
        //     - se muestra el stage de manera no modal mediante el metodo show()
        stage.setScene(scene);
        stage.setTitle("Tennis administrator master 2000 ULTRA");
        stage.getIcons().add(new Image("/images/logo.png"));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */

        public static void main(String[] args) throws ClubDAOException, IOException {
        //PARA ABAJO ES PARA LLENAR LA BASE DE DATOS SE PUEDE BORRAR HASTA EL SIGUIENTE COMENTARIO ASUMO
        Club club= Club.getInstance(); 
        //==================================
        //Clean the file club.db
        club.setInitialData();
        
        //===================================
        // club data:
        System.out.println("Club name: "+ club.getName());
        for (Court court : club.getCourts()) {
            System.out.println("court:" + court.getName());
        }
        //===================================
        // add simple data:
        club.addSimpleData();
        
        //===================================
        // users        
        for (Member member : club.getMembers()) {
            System.out.println("member:" + member.getName()+ ", "+ member.getNickName());
        }
        
        //===================================
        // bookings now + 2 days
        System.out.println("Bookings after 2 days");
        List<Booking> forDayBookings = club.getForDayBookings(LocalDate.now().plusDays(2));
        for (Booking booking : forDayBookings) {
              System.out.println("booking:" + booking.getMember().getNickName()+
                      ", " + booking.getCourt().getName()+ ", "+
                      booking.getMadeForDay().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) +
                      ", "+booking.getFromTime().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));
        }   

        //PARA ARRIBA ES PARA LLENAR LA BASE DE DATOS SE PUEDE BORRAR ASUMO
        
        //NO BORRAR LAUNCH ARGS launch(args);
        launch(args);
        //NO BORRAR LAUNCH ARGS launch(args);
    }


    
}
