/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Member;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import tn.gamingroom.outils.SendEmail;
import tn.gamingroom.services.MembreServices;

/**
 * FXML Controller class
 *
 * @author Sonia
 */
public class ForgotPasswordController implements Initializable {

    @FXML
    private TextField tf_email;
    @FXML
    private JFXButton btn_sendEmail;
    @FXML
    private TextField tf_code;
    @FXML
    private JFXButton btn_verifyCode;
    
    int randomCode ;
    
    @FXML
    private TextField tf_verifpass;
    @FXML
    private Pane pane_reset_code;

    @FXML
    private JFXButton btn_reset;
    @FXML
    private PasswordField tf_newpass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pane_reset_code.setVisible(false);
    }
       public void setRnom(String Value) {
        this.tf_email.setText(Value);
    }    

    @FXML
    private void evoyerEmail(ActionEvent event) {
        try {
            String email = tf_email.getText();
            Random ran = new Random();
            randomCode=ran.nextInt(999999);
            String text = "Hello,"+"\n"+"this is your reset code: "+randomCode+"\n"+"GamingRoom.";
            boolean b =SendEmail.sendMail(email,"Reseting Code",text);
            if(b==true){
                JOptionPane.showMessageDialog(null, "Code has been sent to the email");
            }
            else{
                 JOptionPane.showMessageDialog(null,"ERROR! code hasn't been sent to the email");
            }
        } catch (MessagingException ex) {
            Logger.getLogger(ForgotPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void verifierCode(ActionEvent event) {
        int code = Integer.parseInt(tf_code.getText());
        String email = tf_email.getText();
        if(code == randomCode){
            pane_reset_code.setVisible(true);
            
        }
        else{
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error Code");
             alert.setHeaderText(null);
             alert.setContentText("The code that you entered is not correct");
             alert.showAndWait();
        }
    }

    @FXML
    private void modifierPassword(ActionEvent event) {
        String nvmdp = tf_newpass.getText();
        String verifmdp = tf_verifpass.getText();
        String email = tf_email.getText();
        MembreServices ms = new MembreServices();
        if(!nvmdp.isEmpty() || !nvmdp.isEmpty()){
        if(nvmdp.equals(verifmdp)){
            ms.forgotPassword(email, nvmdp);
            JOptionPane.showMessageDialog(null, "Reset successfully");
        }
        else {
            JOptionPane.showMessageDialog(null, "Password do not match");
        }
    } else {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error ");
             alert.setHeaderText(null);
             alert.setContentText("Veuillez remplir vos champs");
             alert.showAndWait();
        }
    }
    
}
