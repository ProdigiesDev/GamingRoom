/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.controlsfx.control.textfield.TextFields;
import tn.gamingroom.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author Farah
 */
public class UserEvents implements Initializable {

    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private HBox hbox;
    @FXML
    private JFXButton bntListeEv;
    @FXML
    private JFXButton bntListUpEv;
    @FXML
    private BorderPane bpane;
    HamburgerBackArrowBasicTransition burgerTask2;
    @FXML
    private JFXTextField rech;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rech.setStyle("-fx-text-fill: white; ");
        drawer.setSidePane(hbox);
        burgerTask2 = new HamburgerBackArrowBasicTransition(hamburger);
        burgerTask2.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            burgerTask2.setRate(burgerTask2.getRate() * -1);
            burgerTask2.play();

            if (drawer.isOpened()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
        EvenementService es = new EvenementService();
        System.out.println("list " + es.getListeAutoEvent());
        TextFields.bindAutoCompletion(rech, es.getListeAutoEvent());
        myEvents();
    }

    @FXML
    private void allEvents(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ListerEventUser.fxml"));
            bpane.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(UserEvents.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void upEvents(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("upComingEvents.fxml"));
            bpane.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(UserEvents.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void recherche(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chercherListEv.fxml"));
            Parent root = loader.load();
            ChercherListEvController pctC = loader.getController();
            pctC.initData(rech.getText());
            bpane.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(UserEvents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void myEvents() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("listeEvenementsUtilisateur.fxml"));
            bpane.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(UserEvents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    @FXML
    private void getMyEvents(ActionEvent event) {
        myEvents();
    }

}
