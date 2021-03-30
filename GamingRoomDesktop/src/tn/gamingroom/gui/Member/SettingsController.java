/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Member;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Sonia
 */
public class SettingsController implements Initializable {

    @FXML
    private TextField tf_nv_nom;
    @FXML
    private TextField tf_nv_prenom;
    @FXML
    private DatePicker date_naiss;
    @FXML
    private TextField tf_nv_email;
    @FXML
    private ComboBox<?> combo_sexe;
    @FXML
    private PasswordField tf_nv_mdp;
    @FXML
    private PasswordField tf_retype_mdp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
