/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Member;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
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
       
    }  
    

    @FXML
    private void LoginMember(ActionEvent event) throws IOException {
        String resEmail= loginEmail.getText();
        String resPswd= loginPassword.getText();
        
        Membre user;
        MembreServices ms = new MembreServices();
        user = ms.Login(resEmail, resPswd);
        if(user != null){
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
            else if(user.getRole().toString().equals("Member")){
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
        
    }

    @FXML
    private void signupMember(ActionEvent event) throws IOException {
        
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SignupUser.fxml"));
                Parent root = loader.load();
                SignupUserController suc = loader.getController();
                btnLogin.getScene().setRoot(root);
    }
    
}
