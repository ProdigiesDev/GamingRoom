/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Member;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Sonia
 */
public class ProfilCoachController implements Initializable {

    @FXML
    private Label label_role;
    @FXML
    private Label label_sexe;
    @FXML
    private Label label_tel;
    @FXML
    private Label label_email;
    @FXML
    private ImageView image_user;
    @FXML
    private Label label_nomprenom;
    @FXML
    private Button btn_settings;
    @FXML
    private JFXButton btn_signout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficherSettings(ActionEvent event) {
    }

    @FXML
    private void SignOut(ActionEvent event) {
    }
    
}
