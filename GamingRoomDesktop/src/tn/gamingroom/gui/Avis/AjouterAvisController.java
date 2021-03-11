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
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Avis;
import tn.gamingroom.outils.Outils;
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
        
        txtAvis.setStyle("-fx-text-fill: white; ");
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
        
        if(Outils.containsBadWords(avisTXT)){
           JOptionPane.showMessageDialog(null,"Cet Avis ne respecte pas nos standards de la communauté en matière de contenus indésirables");
           return ;
        }
        Avis avis=new Avis(avisTXT,member_id);
        AvisService avisService=new AvisService();
        int nb=avisService.ajouterAvis(avis);
        if(nb == 0)
            JOptionPane.showMessageDialog(null,"Une erreur s'est produite, veuillez réessayer plus tard");
        else    
            JOptionPane.showMessageDialog(null,"Votre avis a été reçu");
        
        
    }

    @FXML
    private void onChangeAvis(KeyEvent event) {
        
            errAvis.setVisible(false);
    }
    
}
