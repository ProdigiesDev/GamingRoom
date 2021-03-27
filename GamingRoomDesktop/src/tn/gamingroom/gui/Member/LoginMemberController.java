/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Member;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.services.MembreServices;

/**
 * FXML Controller class
 *
 * @author Sonia
 */
public class LoginMemberController implements Initializable {

    @FXML
    private JFXTextField loginEmail;
    @FXML
    private JFXPasswordField loginPassword;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private Label forgotPassword;
    @FXML
    private JFXButton btnSignup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /// SHADOW ON MOUSE ENTERED //////
        DropShadow shadow = new DropShadow();
        btnLogin.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e)->{
        btnLogin.setEffect(shadow);});
        btnLogin.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e)->{
        btnLogin.setEffect(null);});
        btnSignup.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e)->{
        btnSignup.setEffect(shadow);});
        btnSignup.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e)->{
        btnSignup.setEffect(null);});
        //////
        forgotPassword.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e)->{
        forgotPassword.setTextFill(Paint.valueOf("#9486FA"));});
        forgotPassword.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e)->{
        forgotPassword.setTextFill(Paint.valueOf("#333333"));});
       
    }  
    

 @FXML
    private void LoginMember(ActionEvent event) throws IOException {
        String resEmail= loginEmail.getText();
        String resPswd= loginPassword.getText();
        
        Membre user;
        MembreServices ms = new MembreServices();
        user = ms.Login(resEmail, resPswd);
//        if (resEmail.length()==0 || resPswd.length()==0){
//            JOptionPane.showMessageDialog(null, "Veuillez compléter vos champs");
//            
//        }
         if (resEmail.isEmpty() || resPswd.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error login");
             alert.setHeaderText(null);
             alert.setContentText("Veuillez compléter vos champs");
             alert.showAndWait();
            
        }
        else{
        if(user != null){
            if(!user.getActive() && !user.getRole().toString().equals("Admin")){
              if(user.getLast_timeban()!= null)  {
                      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

                  System.out.println(user.getLast_timeban()+" last_timeBan");
                  Timestamp d= addHoursToJavaUtilDate(user.getLast_timeban(),user.getBan_duration());
                  
                  System.out.println(dateFormat.format(d)+" last_timeBan + 2h");
                 Date date = new Date(new java.util.Date().getTime());
                  System.out.println(dateFormat.format(date)+" "+date.getTime()+" todays times");
                  System.out.println(d.getTime()+" last_timeBan++2 times");
                 System.out.println(d.getTime()-date.getTime());
                 
                    
                 if(date.getTime()-d.getTime()>0){
                  
                     user.setActive(true);
                     ms.modifierMembreParAdmin(user);
                     
                 }
                 else{
                     JOptionPane.showMessageDialog(null, "You are banned you can connect after: "+d.toString());
                     return ;
                 }
              }
            }
            System.out.println(user.getRole());
            if(user.getRole().toString().equals("Admin"))
            {   Parent dashboard ;
                dashboard = FXMLLoader.load(getClass().getResource("DashboardAdmin.fxml"));
//             Parent root = loader.load();
//            DashboardAdminController adc = loader.getController();
            
                Scene dashboardScene = new Scene(dashboard);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(dashboardScene);
                window.show();
            }
            else if(user.getRole().toString().equals("Coach")){
                
            }
            else if(user.getRole().toString().equals("Membre")){
                System.out.println("pew pew pew");
                Parent dashboard ;
                dashboard = FXMLLoader.load(getClass().getResource("ProfilMember.fxml"));
//             Parent root = loader.load();
//            DashboardAdminController adc = loader.getController();
            
                Scene dashboardScene = new Scene(dashboard);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(dashboardScene);
                window.show();
            }

            
        }
        else{
             JOptionPane.showMessageDialog(null, "Vérifiez vos informations");
        }
        }
    }
    public Timestamp addHoursToJavaUtilDate(Timestamp date, int hours) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.HOUR_OF_DAY, hours);
    java.util.Date res=calendar.getTime();
  
    return new Timestamp(res.getTime());
}

    @FXML
    private void signupMember(ActionEvent event) throws IOException {
        
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SignupUser.fxml"));
                Parent root = loader.load();
                SignupUserController suc = loader.getController();
                btnLogin.getScene().setRoot(root);
    }

//    @FXML
//    private void ChangercouleurFP(MouseEvent event) {
////        forgotPassword.setTextFill(Paint.valueOf("#9F7EF7"));
//        
//    }
//
//    @FXML
//    private void ChangercouleurFP2(MouseEvent event) {
//        
//        forgotPassword.setTextFill(Paint.valueOf("#9F7EF7"));
//        
//    }

 
    
}
