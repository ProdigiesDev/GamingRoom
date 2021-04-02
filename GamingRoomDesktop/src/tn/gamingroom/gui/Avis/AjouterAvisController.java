/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Avis;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Avis;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.entities.UserSession;
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
    private Pane paneInfo;
    @FXML
    private Pane paneLoader;
    @FXML
    private ProgressIndicator loader;

    @FXML
    private JFXTextArea txtAvis;
    @FXML
    private Text errAvis;
    private Membre membre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        paneLoader.setVisible(false);
        txtAvis.setStyle("-fx-text-fill: white; ");
        if (UserSession.getInstance() != null) {
            membre = UserSession.getInstance().getUser();
        }
    }

    @FXML
    private void ajouterAvis(ActionEvent event) {
        if (this.membre == null) {
            int a = JOptionPane.showConfirmDialog(new JFrame(), "vous devez d'abord vous connecter ?");
            if (a == JOptionPane.YES_OPTION) {
                goLogin();
            }
            return;
        }
        String avisTXT = txtAvis.getText();
        int member_id = this.membre.getId();

        if (avisTXT.length() == 0) {
            errAvis.setText("Message est obligatoire");
            errAvis.setVisible(true);
            return;
        }

        if (Outils.containsBadWords(avisTXT)) {
            JOptionPane.showMessageDialog(null, "Cet Avis ne respecte pas nos standards de la communauté en matière de contenus indésirables");
            return;
        }
        Avis avis = new Avis(avisTXT, member_id, "");
        AvisService avisService = new AvisService();
        int nb = avisService.ajouterAvis(avis);
        if (nb == 0) {
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite, veuillez réessayer plus tard");
        } else {
            reset();
            JOptionPane.showMessageDialog(null, "Votre avis a été reçu");
        }

    }

    private void goLogin() {
        try {
            Parent root = FXMLLoader.
                    load(getClass().getResource("../Member/LoginMember.fxml"));

            errAvis.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void onChangeAvis(KeyEvent event) {

        errAvis.setVisible(false);
    }

    void changeVisiblite(boolean paneVal1, boolean paneVal2) {

        paneInfo.setVisible(paneVal1);
        paneLoader.setVisible(paneVal2);
    }

    void reset() {
        txtAvis.setText("");
    }
}
