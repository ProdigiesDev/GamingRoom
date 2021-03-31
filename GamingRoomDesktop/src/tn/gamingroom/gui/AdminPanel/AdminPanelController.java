/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.AdminPanel;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        defaultView();

    }

    @FXML
    private void openGames(ActionEvent event) {
        defaultView();
    }

    
    

    private void defaultView(){
    
        listerJeux.setVisible(true);
        listerreclm.setVisible(false);
        produits.setVisible(false);
        cles.setVisible(false);
    }
    @FXML
    private void openReclamtion(ActionEvent event) {
        listerJeux.setVisible(false);
        listerreclm.setVisible(true);
        produits.setVisible(false);
        cles.setVisible(false);
    }

    @FXML
    private void openProduit(ActionEvent event) {
        listerJeux.setVisible(false);
        listerreclm.setVisible(false);
        produits.setVisible(true);
        cles.setVisible(false);
    }

    @FXML
    private void openCle(ActionEvent event) {
        listerJeux.setVisible(false);
        listerreclm.setVisible(false);
        produits.setVisible(false);
        cles.setVisible(true);
    }

}
