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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import tn.gamingroom.services.CategorieServices;
import tn.gamingroom.entities.Categorie;
import tn.gamingroom.entities.Evenement;
import tn.gamingroom.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ModifierEvenementController implements Initializable {

    List<String> isImage;
    String image;
    private int id;
    @FXML
    private JFXTextField nomevent;
    @FXML
    private JFXDatePicker datedeb;
    @FXML
    private JFXDatePicker datefin;
    @FXML
    private JFXTextField selectedFile;
    @FXML
    private JFXComboBox<Categorie> categorie;
    @FXML
    private JFXTextField nbremax_participant;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXTextField lienyoutube;
    @FXML
    private Button bntModif;
    @FXML
    private Label nomeventCont;
    @FXML
    private Label datedebCont;
    @FXML
    private Label datefinCont;
    @FXML
    private Label imageCont;
    @FXML
    private Label nbpartCont;
    @FXML
    private Label descCont;
    @FXML
    private Label lienCont;

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public void setNomevent(String nomevent) {
        this.nomevent.setText(nomevent);
    }

    public void setDatedeb(Date datedeb) {
        this.datedeb.setValue(LocalDate.of(datedeb.getYear(), datedeb.getMonth(), datedeb.getDay()));
    }

    public void setDatefin(Date datefin) {
        this.datefin.setValue(LocalDate.of(datefin.getYear(), datefin.getMonth(), datefin.getDay()));
    }

    public void setSelectedFile(JFXTextField selectedFile) {
        this.selectedFile = selectedFile;
    }

    public void setCategorie(int cate) {
        CategorieServices cs = new CategorieServices();

        this.categorie.setValue(cs.getById(cate));
    }

    public void setImage(String image) {
        this.image = image;
    }
    

    public void setNbremax_participant(String nbremax_participant) {
        this.nbremax_participant.setText(nbremax_participant);
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void setLienyoutube(String lienyoutube) {
        this.lienyoutube.setText(lienyoutube);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bntModif.setDisable(true);
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
    private void modifierE(ActionEvent event) {
        try {
            if (!(lienyoutube.getText().isEmpty() || description.getText().isEmpty() || nbremax_participant.getText().isEmpty() || image.isEmpty() || datefin.getValue().toString().isEmpty() || datedeb.getValue().toString().isEmpty() || nomevent.getText().isEmpty())) {
                EvenementService es = new EvenementService();
                Evenement e = new Evenement();
                e.setIdevent(this.id);
                e.setNomEvent(nomevent.getText());
                e.setDateDeb(java.sql.Date.valueOf(datedeb.getValue()));
                /**
                 * *******
                 */
                e.setDateFin(java.sql.Date.valueOf(datefin.getValue()));

                //e.setImage(image);
                e.setImage("https://images.unsplash.com/photo-1615333619365-a44d7e655661?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");
                e.setCategorie_id(categorie.getValue().getIdcat());
                e.setNbreMax_participant(Integer.parseInt(nbremax_participant.getText()));
                e.setDescription(description.getText());
                e.setLienYoutube(lienyoutube.getText());
                int ret = es.modifierEvenement(e);
                if (ret == 0) {
                    JOptionPane.showMessageDialog(null, "Erreur evenement non modifiée");
                } else {
                    try {
                        JOptionPane.showMessageDialog(null, "Evenement modifiée");
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
            bntModif.setDisable(true);
            descCont.setText("Veuillez remplir ce champs");
        } else if (description.getText().length() > 255) {
            descCont.setText("La déscription est trop longue");
            bntModif.setDisable(true);
        } else {
            descCont.setText("");
            bntModif.setDisable(false);
        }
    }

    @FXML
    private void verifTitre(KeyEvent event) {
        if (nomevent.getText().isEmpty()) {
            nomeventCont.setText("Veuillez remplir ce champs");
            bntModif.setDisable(true);
        } else if (nomevent.getText().length() > 20) {
            nomeventCont.setText("Titre trop long");
            bntModif.setDisable(true);
        } else {
            nomeventCont.setText("");
            bntModif.setDisable(false);
        }
    }

    @FXML
    private void verifNbPart(KeyEvent event) {
        try {
            int x = Integer.parseInt(nbremax_participant.getText()) % 2;

            if (nbremax_participant.getText().isEmpty()) {
                System.out.println("aaa");
                bntModif.setDisable(true);
                nbpartCont.setText("Veuillez remplir ce champs");
            } else if (x != 0) {
                System.out.println("ccc");

                nbpartCont.setText("Le nombre maximale des participants doit être paire");
                bntModif.setDisable(true);
            } else {
                System.out.println("bbbb");
                nbpartCont.setText("");
                bntModif.setDisable(false);
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    @FXML
    private void verifLien(KeyEvent event) {
        if (lienyoutube.getText().isEmpty()) {
            bntModif.setDisable(true);
            lienCont.setText("Veuillez remplir ce champs");
        } else if (lienyoutube.getText().length() > 100) {
            lienCont.setText("Verrifier le lien");
            bntModif.setDisable(true);
        } else {
            lienCont.setText("");
            bntModif.setDisable(false);
        }
    }

    /**
     * ************************
     */
    @FXML
    private void verifDateDeb(ActionEvent event) {
        if (datedeb.getValue().toString().isEmpty()) {
            datedebCont.setText("Veuillez remplir ce champs");
            bntModif.setDisable(true);
        } else if (datedeb.getValue().isBefore(LocalDate.now())) {
            datedebCont.setText("La date doit être supérieur à la date acctuelle");
            bntModif.setDisable(true);
        } else {
            bntModif.setDisable(false);
            datedebCont.setText("");
        }

    }

    @FXML
    private void verifDatefin(ActionEvent event) {
        if (datefin.getValue().toString().isEmpty()) {
            bntModif.setDisable(true);
            datefinCont.setText("Veuillez remplir ce champs");
        } else if (datefin.getValue().isBefore(datedeb.getValue())) {
            datefinCont.setText("La date doit être supérieur à la date début");
            bntModif.setDisable(true);
        } else {
            datefinCont.setText("");
            bntModif.setDisable(false);
        }
    }

    @FXML
    private void verifImage(ActionEvent event) {
        if (image.isEmpty()) {
            bntModif.setDisable(true);
            imageCont.setText("Veuillez remplir ce champs");
        } else if (image.getBytes().length > 255) {
            imageCont.setText("Veuillez choisir une autre image");
            bntModif.setDisable(true);
        } else {
            imageCont.setText("");
            bntModif.setDisable(false);
        }
    }


}
