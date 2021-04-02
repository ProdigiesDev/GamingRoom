/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.entities.ReactionEv;
import tn.gamingroom.entities.UserSession;
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
    private Membre membre;

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
        if (UserSession.getInstance() != null) {
            membre = UserSession.getInstance().getUser();

            es.reagirEvenement(new ReactionEv(id, membre.getId(), com.getText()));
            Stage stage = (Stage) com.getScene().getWindow();
            stage.close();
        }else{
            verifMember() ;
        }
    }
    
    
    private void goLogin() {
        try {
            Parent root = FXMLLoader.
                    load(getClass().getResource("../Member/LoginMember.fxml"));

            com.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

  
     boolean verifMember() {
        if (membre == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("vous devez d'abord vous connecter ?");
            alert.setContentText("vous devez d'abord vous connecter ?");
            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType cancelButton = new ButtonType("cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(okButton, cancelButton);

            if (alert.showAndWait().get() == okButton) {
                goLogin();
            }

            return true;
        } else {
            return false;
        }
    }

}
