/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Reclamation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Reclamation;
import tn.gamingroom.outils.Outils;
import tn.gamingroom.services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author Dah
 */
public class ReclamationController implements Initializable {

    @FXML
    private AnchorPane test;
    @FXML
    private JFXTextField txtSujet;
    @FXML
    private JFXTextArea txtBody;
    @FXML
    private JFXButton btnenv;
    @FXML
    private Text errMsg;
    @FXML
    private Text errSujet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        txtSujet.setStyle("-fx-text-fill: white; ");
        txtBody.setStyle("-fx-text-fill: white; ");
        
    }    

    @FXML
    private void ajouterReclamation(ActionEvent event) {
        String body=txtBody.getText();
        String sujet=txtSujet.getText();
        int member_id=2;
        
        
        if( body.length() == 0 )
        {
            errMsg.setText("Message est obligatoire");
            errMsg.setVisible(true);
        }
        
        
        if( sujet.length() == 0 )
        {
            errSujet.setText("Sujet est obligatoire");
            errSujet.setVisible(true);
        }
        
        
        
        if( sujet.length() == 0 ||  body.length() == 0 )
            return;
        
        if(Outils.containsBadWords(body)){
           JOptionPane.showMessageDialog(null,"Ce Message ne respecte pas nos standards de la communauté en matière de contenus indésirables");
           return ;
        }
        
        Reclamation reclamation=new Reclamation();
        
        
        
        reclamation.setContenue(txtBody.getText());
        reclamation.setSujet(txtSujet.getText());
        //TODO change this
        reclamation.setMembre_id(2);
        
        ReclamationService reclamationService=new  ReclamationService();
        int nb=reclamationService.ajouterReclamation(reclamation) ;
        
        
        if(nb == 0)
            JOptionPane.showMessageDialog(null,"Une erreur s'est produite, veuillez réessayer plus tard");
        else    
            JOptionPane.showMessageDialog(null,"Votre réclamation a été ajoutée avec succès");
        
    }

    @FXML
    private void onChangeSujet(KeyEvent event) {
            errSujet.setVisible(false);
    }

    @FXML
    private void onChangeMessage(KeyEvent event) {
            errMsg.setVisible(false);
    }
    
}
