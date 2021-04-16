/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Member;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.outils.Env;

/**
 * FXML Controller class
 *
 * @author Sonia
 */
public class ProfilCController implements Initializable {

   
    @FXML
    private Label label_role;
    @FXML
    private Label label_sexe;
    @FXML
    private Label label_tel;
    @FXML
    private Label label_email;
    @FXML
    private ImageView image_user;
    @FXML
    private Label label_nomprenom;

    public void setNomEtPrenom(String nom, String prenom) {
        this.label_nomprenom.setText(nom + " " + prenom);
    }

    public void setSexe(Membre.Genre sexe) {
        this.label_sexe.setText(sexe.toString());
    }

    public void setRole(Membre.Role role) {
        this.label_role.setText(role.toString());
    }

    public void setEmail(String email) {
        this.label_email.setText(email);
    }

    public void set_image(String mm) {
        try {
            Image img= new Image(new File(Env.getImagePath() + "\\membre\\" + mm).toURI().toURL().toExternalForm());
            System.out.println("img "+new File(Env.getImagePath() + "\\membre\\" + mm).getPath());
            image_user.setImage(img);
        } catch (MalformedURLException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setTel(String tel) {
        this.label_tel.setText(tel);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
