/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Categorie;
import tn.gamingroom.entities.Evenement;
import tn.gamingroom.services.CategorieServices;
import tn.gamingroom.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AjoutEvenementController implements Initializable {

    List<String> isImage;
    String image;
    @FXML
    private JFXTextField nomevent = new JFXTextField();
    @FXML
    private JFXDatePicker datedeb = new JFXDatePicker();
    @FXML
    private JFXDatePicker datefin = new JFXDatePicker();
    @FXML
    private JFXTextField selectedFile = new JFXTextField();
    @FXML
    private JFXComboBox<Categorie> categorie = new JFXComboBox();
    @FXML
    private JFXTextField nbremax_participant = new JFXTextField();
    @FXML
    private JFXTextField lienyoutube = new JFXTextField();
    @FXML
    private JFXTextArea description = new JFXTextArea();
    @FXML
    private Label nomeventCont = new Label();
    @FXML
    private Label datedebCont = new Label();
    @FXML
    private Label datefinCont = new Label();
    @FXML
    private Label imageCont = new Label();
    @FXML
    private Label nbpartCont = new Label();
    @FXML
    private Label descCont = new Label();
    @FXML
    private Label lienCont = new Label();
    @FXML
    private Button bntAjout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isImage = new ArrayList<>();
        isImage.add(".jpg");
        isImage.add(".png");
        isImage.add(".gif");

        CategorieServices cs = new CategorieServices();

        ObservableList lCat = FXCollections.observableArrayList(cs.DisplayCategorie());

        categorie.setItems(lCat);

        categorie.setConverter(new StringConverter<Categorie>() {
            @Override
            public String toString(Categorie object) {
                return object.getNomcat();
            }

            @Override
            public Categorie fromString(String string) {
                return null;
            }

        });
        bntAjout.setDisable(true);

        /* if (!(nomevent.getText().isEmpty() || datedeb.getValue().toString().isEmpty() || datefin.getValue().toString().isEmpty() || image.getBytes().toString().isEmpty() || categorie.getId() == null || description.getText().isEmpty() || lienyoutube.getText().isEmpty())) {
            bntAjout.setDisable(true);
        } else {
            bntAjout.setDisable(false);
        }*/
    }

    @FXML
    private void ChooseFile(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image", isImage));
        File f = fc.showOpenDialog(null);
        if (f != null) {
            image = f.getAbsolutePath();
            selectedFile.setText("Selected File::" + image);
        }

    }

    @FXML
    private void ajouterE(ActionEvent event) {
        try {
            if (!(lienyoutube.getText().isEmpty() || description.getText().isEmpty() || nbremax_participant.getText().isEmpty() || image.isEmpty() || datefin.getValue().toString().isEmpty() || datedeb.getValue().toString().isEmpty() || nomevent.getText().isEmpty())) {
                EvenementService es = new EvenementService();
                Evenement e = new Evenement();
                //if (nomevent.getText().length() > 20 || nomevent.getText().isEmpty()) {
                //    nomeventCont.setText("Titre trop long");
                // } else {
                e.setNomEvent(nomevent.getText());
                //    nomeventCont.setText("");
                //  }

                // if (datedeb.getValue().isBefore(LocalDate.now())|| datedeb.getValue().toString().isEmpty()) {
                //    datedebCont.setText("La date doit être supérieur à la date acctuelle");
                //  } else {
                e.setDateDeb(java.sql.Date.valueOf(datedeb.getValue()));
                //    datedebCont.setText("");
                // }

                //  if (datefin.getValue().isBefore(datedeb.getValue()) || datefin.getValue().toString().isEmpty()) {
                //     datefinCont.setText("La date doit être supérieur à la date début");
                //} else {
                e.setDateFin(java.sql.Date.valueOf(datefin.getValue()));
                //    datefinCont.setText("");
                ///}

                //e.setImage(image);
                //if (image.getBytes().length > 255 || image.isEmpty()) {
                //   imageCont.setText("Veuillez choisir une autre image");
                // } else {
                e.setImage("https://images.unsplash.com/photo-1615333619365-a44d7e655661?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");
                //   imageCont.setText("");
                //}

                e.setCategorie_id(categorie.getValue().getIdcat());

                //if (Integer.parseInt(nbremax_participant.getText()) % 2 != 0 || nbremax_participant.getText().isEmpty()) {
                //     nbpartCont.setText("Le nombre maximale des participants doit être paire");
                // } else {
                e.setNbreMax_participant(Integer.parseInt(nbremax_participant.getText()));
                //    nbpartCont.setText("");
                // }

                // if (description.getText().length() > 255 || description.getText().isEmpty()) {
                //    descCont.setText("La déscription est trop longue");
                // } else {
                e.setDescription(description.getText());
                //    descCont.setText("");
                // }

                //if (lienyoutube.getText().length() > 100 || lienyoutube.getText().isEmpty()) {
                //lienCont.setText("Verrifier le lien");
                //} else {
                e.setLienYoutube(lienyoutube.getText());
                // lienCont.setText("");
                //}

                int ret = es.ajoutEvenement(e);
                if (ret == 0) {
                    JOptionPane.showMessageDialog(null, "Erreur evenement non ajouteé");
                } else {
                    try {
                        JOptionPane.showMessageDialog(null, "Evenement ajouteé");
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("listeEvenement.fxml"));
                        Parent root = loader.load();

                        nomevent.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(AjoutEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
        }
    }

    @FXML
    private void backToList(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listeEvenement.fxml"));
            Parent root = loader.load();

            lienyoutube.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ListeEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void verifDesc(KeyEvent event) {
        if (description.getText().isEmpty()) {
            bntAjout.setDisable(true);
            descCont.setText("Veuillez remplir ce champs");
        } else if (description.getText().length() > 255) {
            descCont.setText("La déscription est trop longue");
            bntAjout.setDisable(true);
        } else {
            descCont.setText("");
            bntAjout.setDisable(false);
        }
    }

    @FXML
    private void verifTitre(KeyEvent event) {
        if (nomevent.getText().isEmpty()) {
            nomeventCont.setText("Veuillez remplir ce champs");
            bntAjout.setDisable(true);
        } else if (nomevent.getText().length() > 20) {
            nomeventCont.setText("Titre trop long");
            bntAjout.setDisable(true);
        } else {
            nomeventCont.setText("");
            bntAjout.setDisable(false);
        }
    }

    @FXML
    private void verifNbPart(KeyEvent event) {
        try {
            int x = Integer.parseInt(nbremax_participant.getText()) % 2;

            if (nbremax_participant.getText().isEmpty()) {
                System.out.println("aaa");
                bntAjout.setDisable(true);
                nbpartCont.setText("Veuillez remplir ce champs");
            } else if (x != 0) {
                System.out.println("ccc");

                nbpartCont.setText("Le nombre maximale des participants doit être paire");
                bntAjout.setDisable(true);
            } else {
                System.out.println("bbbb");
                nbpartCont.setText("");
                bntAjout.setDisable(false);
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    @FXML
    private void verifLien(KeyEvent event) {
        if (lienyoutube.getText().isEmpty()) {
            bntAjout.setDisable(true);
            lienCont.setText("Veuillez remplir ce champs");
        } else if (lienyoutube.getText().length() > 100) {
            lienCont.setText("Verrifier le lien");
            bntAjout.setDisable(true);
        } else {
            lienCont.setText("");
            bntAjout.setDisable(false);
        }
    }

    /**
     * ************************
     */
    @FXML
    private void verifDateDeb(ActionEvent event) {
        if (datedeb.getValue().toString().isEmpty()) {
            datedebCont.setText("Veuillez remplir ce champs");
            bntAjout.setDisable(true);
        } else if (datedeb.getValue().isBefore(LocalDate.now())) {
            datedebCont.setText("La date doit être supérieur à la date acctuelle");
            bntAjout.setDisable(true);
        } else {
            bntAjout.setDisable(false);
            datedebCont.setText("");
        }

    }

    @FXML
    private void verifDatefin(ActionEvent event) {
        if (datefin.getValue().toString().isEmpty()) {
            bntAjout.setDisable(true);
            datefinCont.setText("Veuillez remplir ce champs");
        } else if (datefin.getValue().isBefore(datedeb.getValue())) {
            datefinCont.setText("La date doit être supérieur à la date début");
            bntAjout.setDisable(true);
        } else {
            datefinCont.setText("");
            bntAjout.setDisable(false);
        }
    }

    @FXML
    private void verifImage(ActionEvent event) {
        if (image.isEmpty()) {
            bntAjout.setDisable(true);
            imageCont.setText("Veuillez remplir ce champs");
        } else if (image.getBytes().length > 255) {
            imageCont.setText("Veuillez choisir une autre image");
            bntAjout.setDisable(true);
        } else {
            imageCont.setText("");
            bntAjout.setDisable(false);
        }
    }

}
