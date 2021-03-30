/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Shared;

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
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import tn.gamingroom.gui.Jeux.AjouterJeuxController;
import tn.gamingroom.gui.Jeux.JeuxController;

/**
 * FXML Controller class
 *
 * @author Dah
 */
public class HeaderController implements Initializable {

    @FXML
    private Pane header;
    @FXML
    private JFXButton games;
    @FXML
    private JFXButton cours;
    @FXML
    private JFXButton events;
    @FXML
    private JFXButton sotre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void gotoGoogle() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Jeux/Jeux.fxml"));
            Parent root = loader.load();
            JeuxController jeuxController = loader.getController();
            games.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(HeaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gotoGames(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Jeux/Jeux.fxml"));
            Parent root = loader.load();
            JeuxController jeuxController = loader.getController();
            games.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(HeaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
