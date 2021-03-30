/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Jeux;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Jeux;
import tn.gamingroom.outils.Env;
import tn.gamingroom.services.JeuxService;

/**
 * FXML Controller class
 *
 * @author Dah
 */
public class AjouterJeuxController implements Initializable {

    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextArea txtDesc;
    @FXML
    private Text errDesc;
    @FXML
    private JFXButton btnenv;
    @FXML
    private ComboBox<Jeux.Type> comboPlat;
    @FXML
    private Text errNom;

    private Jeux jeux;
    private FileChooser fileChooser;
    private File file;
    @FXML
    private Text errImage;
    @FXML
    private ImageView imageView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fileChooser = new FileChooser();
        List<Jeux.Type> listPlat = new ArrayList();
        listPlat.add(Jeux.Type.Desktop);
        listPlat.add(Jeux.Type.Web);
        listPlat.add(Jeux.Type.Mobile);
        comboPlat.setItems(FXCollections.observableList(listPlat));
        comboPlat.getSelectionModel().select(0);
    }

    @FXML
    private void onChangeNom(KeyEvent event) {
        errNom.setVisible(false);
    }

    @FXML
    private void onChangeDesc(KeyEvent event) {
        errDesc.setVisible(false);
    }

    @FXML
    private void ajouterJeux(ActionEvent event) {
        String nom = txtNom.getText();
        String description = txtDesc.getText();
        if (jeux == null) {
            jeux = new Jeux();
        }
        JeuxService jeuxService = new JeuxService();

        if (nom.length() == 0) {
            errNom.setText("Nom est obligatoire");
            errNom.setVisible(true);
        }

        if (description.length() == 0) {
            errDesc.setText("Description est obligatoire");
            errDesc.setVisible(true);
        }

        if (nom.length() == 0 || description.length() == 0) {
            return;
        }

        if (jeuxService.search(nom, comboPlat.getValue().toString()).size() > 0) {
            JOptionPane.showMessageDialog(null, "Jeu avec le même nom déjà existé");
            return;
        } else {

            if (file != null) {
                try {
                    String fileName = file.getName();
                    int doitIndex = fileName.lastIndexOf(".");
                    String newFileName = fileName.substring(0, doitIndex) + new Date().getTime() + "." + fileName.substring(doitIndex + 1);
                    String imageNameTodb = Env.getImagePath() + "\\jeux\\" + newFileName;
                    File dest = new File(imageNameTodb);
                    Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    jeux.setImage(newFileName);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                errImage.setText("Image est obligatoire");
                errImage.setVisible(true);
                return;
            }

            jeux.setNom(nom);
            jeux.setDescriString(description);
            jeux.setType_plateforme(comboPlat.getValue());

            if (jeux.getId() == 0) {
                int nb = jeuxService.ajouter(jeux);
                if (nb == 0) {
                    JOptionPane.showMessageDialog(null, "Une erreur s'est produite, veuillez réessayer plus tard");
                } else {
                    JOptionPane.showMessageDialog(null, "Jeux ajouteé");
                }

            } else {
                int nb = jeuxService.modifier(jeux);
                if (nb == 0) {
                    JOptionPane.showMessageDialog(null, "Une erreur s'est produite, veuillez réessayer plus tard");
                } else {
                    JOptionPane.showMessageDialog(null, "Jeux a ete modifié");
                }
            }
        }

    }

    public Jeux getJeux() {
        return jeux;
    }

    public void setJeux(Jeux jeux) {
        this.jeux = jeux;
    }

    public void editInterface(Jeux jeux) {
        this.jeux = jeux;
        btnenv.setText("Modifier");
        txtNom.setText(jeux.getNom());
        txtDesc.setText(jeux.getDescriString());
        comboPlat.getSelectionModel().select(jeux.getType_plateforme());
        try {
            imageView.setImage(new Image(new File(Env.getImagePath() + "\\jeux\\" + jeux.getImage()).toURI().toURL().toExternalForm()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(AjouterJeuxController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectImage(ActionEvent event) {
        file = fileChooser.showOpenDialog((Stage) errDesc.getScene().getWindow());
        if (file == null) {
            errImage.setText("Image est obligatoire");
            errImage.setVisible(true);
        } else {
            try {
                imageView.setImage(new Image(file.toURI().toURL().toExternalForm()));
            } catch (MalformedURLException ex) {
                Logger.getLogger(AjouterJeuxController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
