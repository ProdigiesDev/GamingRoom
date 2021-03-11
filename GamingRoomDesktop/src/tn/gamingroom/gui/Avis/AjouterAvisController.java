/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Avis;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import tn.gamingroom.entities.Avis;
import tn.gamingroom.services.AvisService;

/**
 * FXML Controller class
 *
 * @author Dah
 */
public class AjouterAvisController implements Initializable {

    @FXML
    private JFXButton btnenv;
    @FXML
    private JFXTextArea txtAvis;
    @FXML
    private Text errAvis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterAvis(ActionEvent event) {
        String avisTXT=txtAvis.getText();
        int member_id=2;
        
        
        if( avisTXT.length() == 0 )
        {
            errAvis.setText("Message est obligatoire");
            errAvis.setVisible(true);
            return;
        }
        Avis avis=new Avis(avisTXT,member_id);
        AvisService avisService=new AvisService();
        avisService.ajouterAvis(avis);
        
    }

    @FXML
    private void onChangeAvis(KeyEvent event) {
        
            errAvis.setVisible(false);
    }
    
}
