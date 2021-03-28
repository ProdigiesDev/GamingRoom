/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tn.gamingroom.entities.ReactionEv;
import tn.gamingroom.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author Farah
 */
public class AjouterCommentaireController implements Initializable {

    @FXML
    private JFXTextArea com;
    int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        com.setStyle("-fx-text-fill: white; ");
    }

    public void intData(int id) {
        this.id = id;
    }

    @FXML
    private void ajouter(ActionEvent event) {
        EvenementService es = new EvenementService();
        es.reagirEvenement(new ReactionEv(id, 1, com.getText()));
    }

}
