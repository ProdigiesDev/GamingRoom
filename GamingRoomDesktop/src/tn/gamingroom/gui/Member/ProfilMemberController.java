/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Member;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.entities.UserSession;
import tn.gamingroom.outils.Env;

/**
 * FXML Controller class
 *
 * @author Sonia
 */
public class ProfilMemberController implements Initializable {

    @FXML
    private Label label_role;
    @FXML
    private ImageView image_user;
    @FXML
    private Label label_nomprenom;
    @FXML
    private Label label_sexe;
    @FXML
    private Label label_tel;
    @FXML
    private Label label_email;
    @FXML
    private JFXButton btn_signout;
    @FXML
    private Button btn_settings;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Afficherprofil();
        
        
    }  
    public void Afficherprofil(){
         UserSession us = UserSession.getInstance();
            Membre m = us.getUser();
            String nom = m.getNom();
            String prenom = m.getPrenom();
            Membre.Role role = m.getRole();
            Membre.Genre sexe = m.getGenre();
            String email = m.getEmail();
            String tel = m.getTel();
        try {
           
            
            image_user.setImage(new Image(new File(Env.getImagePath() + "\\membre\\" + m.getImage()).toURI().toURL().toExternalForm()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProfilMemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
        label_nomprenom.setText(nom+" "+prenom);
        label_role.setText(role.toString());
        label_sexe.setText(sexe.toString());
        label_email.setText(email);
        label_tel.setText(tel);
    }

    @FXML
    private void SignOut(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Are you sure ?");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);
        
        if(alert.showAndWait().get()== okButton){
                Parent dashboard ;
                dashboard = FXMLLoader.load(getClass().getResource("LoginMember.fxml"));
//             Parent root = loader.load();
//            DashboardAdminController adc = loader.getController();
            
                Scene dashboardScene = new Scene(dashboard);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(dashboardScene);
                window.show(); 
                UserSession us = UserSession.getInstance();
                us.cleanUserSession();
        }
    }

    @FXML
    private void afficherSettings(ActionEvent event) {
        try {
                FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("Settings.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Settings");
//                ForgotPasswordController fpc = fxmlLoader.getController();
//                fpc.setRnom(loginEmail.getText());
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginMemberController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
}
