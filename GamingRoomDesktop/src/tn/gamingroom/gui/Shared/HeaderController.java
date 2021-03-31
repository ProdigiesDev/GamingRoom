/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Shared;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
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
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.entities.UserSession;
import tn.gamingroom.gui.Jeux.AjouterJeuxController;
import tn.gamingroom.gui.Jeux.JeuxController;
import tn.gamingroom.outils.Env;

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
    @FXML
    private JFXButton home;
    @FXML
    private JFXButton games1;
    @FXML
    private JFXButton loginbtn;
    private Membre membre;
    @FXML
    private Label nom;
    @FXML
    private JFXButton signoutbtn;
    @FXML
    private FontAwesomeIcon fontsingout;
    @FXML
    private JFXButton btnshop;
    @FXML
    private FontAwesomeIcon fontshop;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (UserSession.getInstance() != null) {
            membre = UserSession.getInstance().getUser();
        }

        if (membre != null) {
            loginbtn.setVisible(false);
            nom.setVisible(true);
            fontsingout.setVisible(true);
            btnshop.setVisible(true);
            fontshop.setVisible(true);
            nom.setText(membre.getNom() + " " + membre.getPrenom());
            signoutbtn.setVisible(true);
        }
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

    @FXML
    private void gotoHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Accueil/Accueil.fxml"));
            Parent root = loader.load();
            games.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(HeaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gotoReclamtion(ActionEvent event) {
        if(membre==null){
            
         int a = JOptionPane.showConfirmDialog(new JFrame(), "vous dois d'abord vous connecter ?");
            if (a == JOptionPane.YES_OPTION) {
                goLogin();
                return;
            }
            else if (a != JOptionPane.YES_OPTION)
                return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Reclamation/AjouterReclamation.fxml"));
            Parent root = loader.load();
            games.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(HeaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.
                    load(getClass().getResource("../Member/LoginMember.fxml"));

            games.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void signout(ActionEvent event) {
        int a = JOptionPane.showConfirmDialog(new JFrame(), "Êtes-vous sûr?");
        if (a == JOptionPane.YES_OPTION) {

            UserSession.cleanUserSession();
            loginbtn.setVisible(true);
            nom.setVisible(false);
            fontsingout.setVisible(false);
            btnshop.setVisible(false);
            fontshop.setVisible(false);
            nom.setText("");
            signoutbtn.setVisible(false);
        }
    }
    
    
    private void goLogin() {
        try {
            Parent root = FXMLLoader.
                    load(getClass().getResource("../Member/LoginMember.fxml"));

            signoutbtn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
