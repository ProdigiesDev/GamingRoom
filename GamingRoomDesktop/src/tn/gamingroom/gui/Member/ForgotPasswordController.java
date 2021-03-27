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
import javafx.scene.control.TextField;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import tn.gamingroom.outils.SendEmail;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void evoyerEmail(ActionEvent event) {
        try {
            String email = tf_email.getText();
            Random ran = new Random();
            randomCode=ran.nextInt(999999);
            String text = "Hello,"+"\n"+"this is your reset code"+randomCode+"\n"+"GamingRoom.";
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
    }
    
}
