/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Member;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.entities.UserSession;
import tn.gamingroom.outils.Env;
import tn.gamingroom.services.MembreServices;

/**
 * FXML Controller class
 *
 * @author Sonia
 */
public class SettingsController implements Initializable {

    @FXML
    private TextField tf_nv_nom;
    @FXML
    private TextField tf_nv_prenom;
    @FXML
    private DatePicker date_naiss;
    @FXML
    private TextField tf_nv_email;
    @FXML
    private ComboBox<String> combo_sexe;
    @FXML
    private PasswordField tf_nv_mdp;
    @FXML
    private PasswordField tf_retype_mdp;
    @FXML
    private TextField tf_id;
    @FXML
    private JFXButton btn_modifiermdp;
    Membre m = new Membre();
    @FXML
    private JFXButton btn_browse;
    @FXML
    private ImageView image_set;
    //image//
    private FileChooser fileChooser;
    private File file;
    Stage stage;
    String image;
    @FXML
    private JFXButton btn_modifierInfos;
    @FXML
    private TextField tf_nv_tel;
    @FXML
    private Label supprimerCompte;

    public void setId(int Value) {
        this.tf_id.setText(String.valueOf(Value));
    }

    public void setNom(String value) {
        this.tf_nv_nom.setText(value);
    }

    public void setPrenom(String value) {
        this.tf_nv_prenom.setText(value);
    }

    public void setSexe(Membre.Genre sexe) {
        this.combo_sexe.setValue(sexe.toString());
    }

    public void set_Date(LocalDate date_naiss) {
        this.date_naiss.setValue(date_naiss);
    }

    public void set_Email(String email) {
        this.tf_nv_email.setText(email);
    }

    public void set_image(Membre mm) {

        try {
            this.image_set.setImage(new Image(new File(Env.getImagePath() + "\\membre\\" + mm.getImage()).toURI().toURL().toExternalForm()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setTel(String tel) {
        this.tf_nv_tel.setText(tel);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tf_id.setVisible(false);
        //Combo Genre
        combo_sexe.getItems().addAll(
                m.getGenre().Homme.toString(),
                m.getGenre().Femme.toString()
        );

        // File chooser
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg"));
        
        ///////////
        supprimerCompte.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e)->{
        supprimerCompte.setTextFill(Paint.valueOf(" #d004eb"));});
        supprimerCompte.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e)->{
        supprimerCompte.setTextFill(Paint.valueOf("#ffffff"));});
        
        supprimerCompte.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e)->{
            try {
                
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("vous etes sur le point de supprimer votre compte !");
        alert.setContentText("etes-vous sur? ?");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);
        
        if(alert.showAndWait().get()== okButton){
            UserSession us = UserSession.getInstance();
            Membre m =us.getUser();
            MembreServices ms = new MembreServices();
            Membre mm = new Membre(Integer.parseInt(tf_id.getText()));
            
            
            ms.sumprimerMembres(mm);
            us.cleanUserSession();
                FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("LoginMember.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Login");
                stage.setScene(new Scene(root));
                ((Stage) tf_nv_tel.getScene().getWindow()).close();
                stage.setMaximized(true);
                stage.show(); 
        }
            } catch (IOException ex) {
                Logger.getLogger(LoginMemberController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        });

    }

    @FXML
    private void modifierMdp(ActionEvent event) {
        int id = Integer.parseInt(tf_id.getText());
        String nvmpd = tf_nv_mdp.getText();
        String rnvmdp = tf_retype_mdp.getText();
        if (nvmpd.isEmpty() || rnvmdp.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez compléter vos champs");
            alert.showAndWait();

        } else if (nvmpd.equals(rnvmdp)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation de modification !");
            alert.setContentText("Voulez-Vous Vraiment modifier");
            MembreServices ms = new MembreServices();
            Optional<ButtonType> btn = alert.showAndWait();
            if (btn.get() == ButtonType.OK) {
                ms.modifierMDPParMembre(id, rnvmdp);
                JOptionPane.showMessageDialog(null, "Modification mot de passe avec succés");
                tf_nv_mdp.setText(null);
                tf_retype_mdp.setText(null);
            } else {
                alert.close();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez verifier vos champs");
            alert.showAndWait();
        }
    }

    @FXML
    private void selectImage(ActionEvent event) {
        try {

            file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                image = file.getAbsolutePath();

                Image imageForFile = new Image(file.toURI().toURL().toExternalForm());
                image_set.setImage(imageForFile);
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(SignupUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String moveImage() {
        if (file != null) {
            try {
                String fileName = file.getName();
                int doitIndex = fileName.lastIndexOf(".");
                String imageName = fileName.substring(0, doitIndex) + new java.util.Date().getTime() + "." + fileName.substring(doitIndex + 1);
                String imageNameTodb = Env.getImagePath() + "membre\\" + imageName;
                File dest = new File(imageNameTodb);
                Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                return imageName;

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return "";
    }

    @FXML
    private void modifierInfos(ActionEvent event) {

        int id = Integer.parseInt(tf_id.getText());
        String nom = tf_nv_nom.getText();
        String prenom = tf_nv_prenom.getText();
        String sexe = (String) combo_sexe.getValue().toString();
        LocalDate naiss = date_naiss.getValue();
        String email = tf_nv_email.getText();
        String tel = tf_nv_tel.getText();
        Membre m = new Membre(id, nom, prenom, java.sql.Date.valueOf(naiss), sexe.equals("Homme") ? Membre.Genre.Homme : Membre.Genre.Femme, tel, email, " ");
        String nomImage = moveImage();
        m.setImage(nomImage);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de modification !");
        alert.setContentText("Voulez-Vous Vraiment modifier");
        MembreServices ms = new MembreServices();
        Optional<ButtonType> btn = alert.showAndWait();
        if (btn.get() == ButtonType.OK) {
            ms.modifierMembres(m);

            System.out.println("modificatation avec succés ");
            JOptionPane.showMessageDialog(null, " modificatation avec succés");
        } else {
            alert.close();
        }

    }

}
