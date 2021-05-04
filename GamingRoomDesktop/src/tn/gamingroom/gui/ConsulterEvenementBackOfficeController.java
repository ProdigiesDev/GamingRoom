/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.CommentaireReact;
import tn.gamingroom.entities.Evenement;
import static tn.gamingroom.gui.ConsulterEventFrontOfficeController.browser;
import tn.gamingroom.outils.Env;
import tn.gamingroom.services.EvenementService;
import tn.gamingroom.services.MembreServices;

/**
 * FXML Controller class
 *
 * @author Farah
 */
public class ConsulterEvenementBackOfficeController implements Initializable {

    @FXML
    private ImageView imageC;
    @FXML
    private Label description;
    @FXML
    private Label dd;
    @FXML
    private Label df;
    @FXML
    private Label nbM;
    private Label lienY;
    @FXML
    private Label titre;
    private Scene s;
    private int id;
    @FXML
    private TableView<CommentaireReact> tVCom;
    @FXML
    private TableColumn<CommentaireReact, String> membreCom;
    @FXML
    private TableColumn<CommentaireReact, String> commentaire;
    @FXML
    private WebView vidYoutube;
    @FXML
    private Label likes;
    @FXML
    private Label dislikes;

    private String lat;
    private String lang;

    public ImageView getImage() {
        return imageC;
    }

    public void setImage(ImageView image) {
        this.imageC = image;
    }

    public ImageView getImageC() {
        return imageC;
    }

    public void setImageC(String image) {
        Image im = new Image(image);
        imageC.setImage(im);
        imageC.setFitHeight(675);
        imageC.setFitWidth(475);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    private void initTable() {
        EvenementService es = new EvenementService();
        MembreServices ms = new MembreServices();
        List<CommentaireReact> lIm = new ArrayList<CommentaireReact>();
        es.listeCommentaires(id).forEach(e -> {
            String nom = ms.findById(e.getMembre_id());
            lIm.add(new CommentaireReact(e.getId(), e.getEvenement_id(), e.getMembre_id(), e.getCommentaire(), nom));

        });

        ObservableList<CommentaireReact> listEventsIm = FXCollections.observableArrayList(lIm);

        tVCom.setItems(listEventsIm);
    }

    public void intData(int idE, Scene n) {
        try {
            EvenementService es = new EvenementService();
            this.id = idE;
            Evenement e = es.findById(idE);
            File f = new File(Env.getDossierImageUtilEventPath()+e.getImage());
            Image img = new Image(f.toURI().toURL().toExternalForm());
            imageC.setSmooth(true);
            imageC.setImage(img);
            dd.setText(e.getDateDeb().toString());
            df.setText(e.getDateFin().toString());
            titre.setText(titre.getText() + " " + e.getNomEvent());
            description.setText(e.getDescription());
            nbM.setText(nbM.getText() + " " + e.getNbreMax_participant());
            vidYoutube.getEngine().load(e.getLienYoutube());
            s = n;
            if (!e.getLieu().equals("ONLINE")) {
                String[] lieuT = e.getLieu().split(",", 3);
                this.lat = lieuT[0];
                this.lang = lieuT[1];
            }
            likes.setText(es.getLikes(idE) + "");
            dislikes.setText(es.getDisikes(idE) + "");
            membreCom.setCellValueFactory(new PropertyValueFactory<CommentaireReact, String>("nomMembre"));
            commentaire.setCellValueFactory(new PropertyValueFactory<CommentaireReact, String>("commentaire"));

            initTable();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ConsulterEvenementBackOfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToModif(ActionEvent event) {
        EvenementService es = new EvenementService();
        Evenement e = es.findById(id);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierEvenement.fxml"));
            Parent root;
            root = loader.load();
            ModifierEvenementController pct = loader.getController();

            pct.setId(e.getIdevent());
            pct.setNomevent(e.getNomEvent());
            pct.setDatedeb(e.getDateDeb());
            pct.setDatefin(e.getDateFin());
            pct.setCategorie(e.getCategorie_id());
            pct.setNbremax_participant(e.getNbreMax_participant() + "");
            pct.setDescription(e.getDescription());
            pct.setLienyoutube(e.getLienYoutube());
            pct.iniData(e.getIdevent());
            s.setRoot(root);
            Stage stage = (Stage) description.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEvenementBackOfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void ListParticipants(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listeParticipants.fxml"));
            Parent root= loader.load();
            ListeParticipantsController pctC = loader.getController();
            pctC.initDat(id);
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            
            primaryStage.setTitle(titre.getText());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEvenementBackOfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void openLocation(ActionEvent event) {
        if (lat != null) {
            Scene scene;
            Stage primaryStage = new Stage();
            primaryStage.setTitle(titre.getText());
            browser = new Browser(Double.parseDouble(lat), Double.parseDouble(lang), true);
            scene = new Scene(browser, 500, 500, Color.web("#666970"));
            primaryStage.setScene(scene);
            primaryStage.show();
        } else {
            JOptionPane.showMessageDialog(null, "ONLINE");
        }

    }

}
