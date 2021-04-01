/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.AdminPanel;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.entities.UserSession;

/**
 * FXML Controller class
 *
 * @author Dah
 */
public class AdminPanelController implements Initializable {

  
    @FXML
    private JFXButton games;
    @FXML
    private JFXButton products;
    @FXML
    private JFXButton members;
    @FXML
    private JFXButton orders;
    @FXML
    private JFXButton keys;
    @FXML
    private Pane listerJeux;
    @FXML
    private Pane listerreclm;
    @FXML
    private Pane produits;
    @FXML
    private Pane cles;
    @FXML
    private Pane memberPane;
    private Membre membre;
    @FXML
    private JFXButton btnsignout;
    @FXML
    private Label name;
    @FXML
    private Pane avisPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (UserSession.getInstance() != null) {
            membre = UserSession.getInstance().getUser();
            name.setVisible(true);
            name.setText("Wlc " + membre.getNom());
        } 
        defaultView();

    }

    @FXML
    private void openGames(ActionEvent event) {
        defaultView();
    }

    private void defaultView() {

        listerJeux.setVisible(true);
        listerreclm.setVisible(false);
        produits.setVisible(false);
        cles.setVisible(false);
        memberPane.setVisible(false);
        avisPane.setVisible(false);
    }

    @FXML
    private void openReclamtion(ActionEvent event) {
        listerJeux.setVisible(false);
        listerreclm.setVisible(true);
        produits.setVisible(false);
        cles.setVisible(false);
        memberPane.setVisible(false);
        avisPane.setVisible(false);
    }

    @FXML
    private void openProduit(ActionEvent event) {
        listerJeux.setVisible(false);
        listerreclm.setVisible(false);
        produits.setVisible(true);
        cles.setVisible(false);
        memberPane.setVisible(false);
        avisPane.setVisible(false);
    }

    @FXML
    private void openCle(ActionEvent event) {
        listerJeux.setVisible(false);
        listerreclm.setVisible(false);
        produits.setVisible(false);
        cles.setVisible(true);
        memberPane.setVisible(false);
        avisPane.setVisible(false);
    }

    @FXML
    private void openMembre(ActionEvent event) {
        listerJeux.setVisible(false);
        listerreclm.setVisible(false);
        produits.setVisible(false);
        cles.setVisible(false);
        memberPane.setVisible(true);
        avisPane.setVisible(false);
    }

    @FXML
    private void signout(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Se déconnecter");
        alert.setHeaderText("Vous êtes sur le point de vous déconnecter!");
        alert.setContentText("Est-vous sûr ?");
        ButtonType okButton = new ButtonType("Oui", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);

        if (alert.showAndWait().get() == okButton) {
            Parent dashboard;
            dashboard = FXMLLoader.load(getClass().getResource("../Member/LoginMember.fxml"));
//             Parent root = loader.load();
//            DashboardAdminController adc = loader.getController();
            name.setVisible(false);
            name.setText("");
            Scene dashboardScene = new Scene(dashboard);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(dashboardScene);
            window.show();
            UserSession us = UserSession.getInstance();
            us.cleanUserSession();
        }
    }

    @FXML
    private void openAvis(ActionEvent event) {
        
        listerJeux.setVisible(false);
        listerreclm.setVisible(false);
        produits.setVisible(false);
        cles.setVisible(false);
        memberPane.setVisible(false);
        avisPane.setVisible(true);
    }
}
