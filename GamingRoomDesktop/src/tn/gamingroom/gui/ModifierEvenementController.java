/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;
import tn.gamingroom.services.CategorieServices;
import tn.gamingroom.entities.Categorie;
import tn.gamingroom.entities.Evenement;
import static tn.gamingroom.gui.AjoutEvenementController.browser;
import tn.gamingroom.outils.Env;
import tn.gamingroom.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ModifierEvenementController implements Initializable {

    List<String> isImage;
    String image;
    String imageNameTodb;
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
    @FXML
    private ImageView imV;
    @FXML
    private JFXTextField langLat;
    @FXML
    private Button btnGoToMap;
    @FXML
    private JFXRadioButton online;
    @FXML
    private Button btnHelpL;

    public void iniData(int id) {
        try {
            EvenementService es = new EvenementService();
            imageNameTodb = es.findById(id).getImage();
            File f = new File(imageNameTodb);
            selectedFile.setText("Selected File::" + imageNameTodb);
            Image img = new Image(f.toURI().toURL().toExternalForm());
            System.out.println("image " + img);
            imV.setImage(img);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ModifierEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomevent(String nomevent) {
        this.nomevent.setText(nomevent);
    }

    public void setDatedeb(Date datedeb) {
//        
        this.datedeb.setValue(datedeb.toLocalDate());
        System.out.println("datedeb " + datedeb.toLocalDate());
    }

    public void setDatefin(Date datefin) {
        this.datefin.setValue(datefin.toLocalDate());
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

    public String getImageNameTodb() {
        return imageNameTodb;
    }

    public void setImageNameTodb(String imageNameTodb) {
        this.imageNameTodb = imageNameTodb;
    }

    public void setLangLat(String langLat) {
        this.langLat.setText(langLat);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nomevent.setStyle("-fx-text-fill: white; ");
        datedeb.setStyle("-fx-text-fill: white; ");
        datefin.setStyle("-fx-text-fill: white; ");
        selectedFile.setStyle("-fx-text-fill: white; ");
        categorie.setStyle("-fx-text-fill: white; ");
        nbremax_participant.setStyle("-fx-text-fill: white; ");
        lienyoutube.setStyle("-fx-text-fill: white; ");
        description.setStyle("-fx-text-fill: white; ");
        bntModif.setDisable(true);

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

        isImage = new ArrayList<>();
        isImage.add(".jpg");
        isImage.add(".png");
        isImage.add(".gif");
    }

    @FXML
    private void ChooseFile(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image", isImage));
        File f = fc.showOpenDialog(null);
        if (f != null) {
            try {
                image = f.getAbsolutePath();
                selectedFile.setText("Selected File::" + image);
                Image imageForFile = new Image(f.toURI().toURL().toExternalForm());
                imV.setImage(imageForFile);
                imageNameTodb = Env.getDossierImageUtilEventPath() + imageForFile;
                File dest = new File(imageNameTodb);
                Files.copy(f.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);

            } catch (IOException ex) {
                Logger.getLogger(AjoutEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void modifierE(ActionEvent event) {
        try {
            if (!(lienyoutube.getText().isEmpty() || description.getText().isEmpty() || nbremax_participant.getText().isEmpty() || imageNameTodb.isEmpty() || datefin.getValue().toString().isEmpty() || datedeb.getValue().toString().isEmpty() || nomevent.getText().isEmpty())) {
                EvenementService es = new EvenementService();
                Evenement e = new Evenement();
                e.setIdevent(this.id);
                e.setNomEvent(nomevent.getText());
                e.setDateDeb(java.sql.Date.valueOf(datedeb.getValue()));
                /**
                 * *******
                 */
                e.setDateFin(java.sql.Date.valueOf(datefin.getValue()));
                if (!online.isSelected()) {
                    e.setLieu(langLat.getText());
                } else {
                    e.setLieu("ONLINE");
                }
                e.setImage(imageNameTodb);
                e.setCategorie_id(categorie.getValue().getIdcat());
                System.out.println("cat " + e.getCategorie_id());
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
    private void backToList(ActionEvent event
    ) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listeEvenement.fxml"));
            Parent root = loader.load();

            lienyoutube.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ListeEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void verifDesc(KeyEvent event
    ) {
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
    private void verifTitre(KeyEvent event
    ) {
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
    private void verifNbPart(KeyEvent event
    ) {
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
    private void verifLien(KeyEvent event
    ) {
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
    private void verifDateDeb(ActionEvent event
    ) {
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
    private void verifDatefin(ActionEvent event
    ) {
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
    private void verifImage(ActionEvent event
    ) {
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

    @FXML
    private void goToMap(ActionEvent event
    ) {

        Scene scene;
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Map");
        browser = new Browser(0, 0, false);
        scene = new Scene(browser, 500, 500, Color.web("#666970"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void ifSelectetd(ActionEvent event
    ) {
        if (online.isSelected()) {
            langLat.setDisable(true);
            btnGoToMap.setDisable(true);
            btnHelpL.setDisable(true);
        } else {
            langLat.setDisable(false);
            btnGoToMap.setDisable(false);
            btnHelpL.setDisable(false);
        }
    }

    @FXML
    private void helpL(ActionEvent event
    ) {
        JOptionPane.showMessageDialog(null, "Comment récuperer la longitude et latitude:\n"
                + "ouvrir la fenêtre de Map\n"
                + "Cliquez sur Agranidr le plan\n"
                + "Choisissez la localisation\n"
                + "La copier et la coller dans le champs Lieu.");
    }

}
