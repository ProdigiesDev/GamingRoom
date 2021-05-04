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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Categorie;
import tn.gamingroom.entities.Evenement;
import static tn.gamingroom.gui.ConsulterEventFrontOfficeController.browser;
import tn.gamingroom.outils.Env;
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
    String imageNameTodb;
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
    @FXML
    private ImageView imV;
    @FXML
    private Button bntHelp;
    @FXML
    private JFXRadioButton online;
    @FXML
    private JFXTextField langLat;
    @FXML
    private Button btnGoToMap;
    @FXML
    private Button btnHelpL;
    static Browser browser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomevent.setStyle("-fx-text-fill: white; ");
        datedeb.setStyle("-fx-text-fill: white; ");
        datefin.setStyle("-fx-text-fill: white; ");
        selectedFile.setStyle("-fx-text-fill: white; ");
        categorie.setStyle("-fx-text-fill: white; ");
        nbremax_participant.setStyle("-fx-text-fill: white; ");
        lienyoutube.setStyle("-fx-text-fill: white; ");
        description.setStyle("-fx-text-fill: white; ");

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

        final Tooltip tooltip1 = new Tooltip();
        final Tooltip tooltip2 = new Tooltip();
        final Tooltip tooltip3 = new Tooltip();
        tooltip1.setText("Help");
        bntHelp.setTooltip(tooltip1);
        tooltip2.setText("Format embed");
        lienyoutube.setTooltip(tooltip2);
        tooltip3.setText("Map");
        btnGoToMap.setTooltip(tooltip3);
        btnHelpL.setTooltip(tooltip1);

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
    private void ajouterE(ActionEvent event) {
        try {
            if (!(lienyoutube.getText().isEmpty() || description.getText().isEmpty() || nbremax_participant.getText().isEmpty() || image.isEmpty() || datefin.getValue().toString().isEmpty() || datedeb.getValue().toString().isEmpty() || nomevent.getText().isEmpty())) {
                EvenementService es = new EvenementService();
                Evenement e = new Evenement();
                e.setNomEvent(nomevent.getText());
                e.setDateDeb(java.sql.Date.valueOf(datedeb.getValue()));
                e.setDateFin(java.sql.Date.valueOf(datefin.getValue()));
                e.setImage(imageNameTodb);
                if (!online.isSelected()) {
                    e.setLieu(langLat.getText());
                } else {
                    e.setLieu("ONLINE");
                }
                e.setCategorie_id(categorie.getValue().getIdcat());
                e.setNbreMax_participant(Integer.parseInt(nbremax_participant.getText()));
                e.setDescription(description.getText());
                e.setLienYoutube(lienyoutube.getText());

                int ret = es.ajoutEvenement(e);
                if (ret == 0) {
                    JOptionPane.showMessageDialog(null, "Erreur evenement non ajouteé");
                } else {
                    JOptionPane.showMessageDialog(null, "Evenement ajouteé");
                    try {
                        JOptionPane.showMessageDialog(null, "Evenement ajouteé");
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminPanel/AdminPanel.fxml"));
                        Parent root = loader.load();

                        nomevent.getScene().setRoot(root);

                    } catch (IOException ex) {
                        Logger.getLogger(AjoutEvenementController.class
                                .getName()).log(Level.SEVERE, null, ex);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminPanel/AdminPanel.fxml"));
            Parent root = loader.load();

            lienyoutube.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(ListeEvenementController.class
                    .getName()).log(Level.SEVERE, null, ex);
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

                bntAjout.setDisable(true);
                nbpartCont.setText("Veuillez remplir ce champs");
            } else if (x != 0) {

                nbpartCont.setText("Le nombre maximale des participants doit être paire");
                bntAjout.setDisable(true);
            } else {

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
            String pattern = "^(http(s)?:\\/\\/)?((w){3}.)?youtu(be|.be)?(\\.com)?\\/.+";
            if (!lienyoutube.getText().matches(pattern)) {
                lienCont.setText("Lien Youtube Invalide");
                bntAjout.setDisable(true);
            } else if (lienyoutube.getText().matches(pattern)) {
                lienCont.setText("");
                bntAjout.setDisable(false);
            }
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
    private void verifDatefin(ActionEvent event
    ) {
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
    private void verifImage(ActionEvent event
    ) {
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

    @FXML
    private void help(ActionEvent event
    ) {
        JOptionPane.showMessageDialog(null, "Finding the embed code on YouTube:"
                + "Go to YouTube.\n"
                + "Navigate to the video you wish to embed.\n"
                + "Click the Share link below the video, then click the Embed link.\n"
                + "The embed link will be highlighted in blue. You will need to copy this link in order to add it to your page in the Employer Center.");
    }

    @FXML
    private void goToMap(ActionEvent event) {

        Scene scene;
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Map");
        browser = new Browser(0, 0, false);
        scene = new Scene(browser, 500, 500, Color.web("#666970"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void ifSelectetd(ActionEvent event) {
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
    private void helpL(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Comment récuperer la longitude et latitude:\n"
                + "ouvrir la fenêtre de Map\n"
                + "Cliquez sur Agranidr le plan\n"
                + "Choisissez la localisation\n"
                + "La copier et la coller dans le champs Lieu.");
    }

}
