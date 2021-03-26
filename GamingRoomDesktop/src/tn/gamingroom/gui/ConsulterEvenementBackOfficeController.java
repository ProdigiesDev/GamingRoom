/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tn.gamingroom.entities.Evenement;
import tn.gamingroom.services.EvenementService;

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
    @FXML
    private Label lienY;
    @FXML
    private Label titre;
    private Scene s;
    private int id;

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
        System.out.println("image" + image);
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

    public void intData(int idE, Scene n) {
        EvenementService es = new EvenementService();
        this.id = idE;
        Evenement e = es.findById(idE);
        Image im = new Image(e.getImage());
        imageC.setSmooth(true);
        imageC.setImage(im);
        dd.setText(e.getDateDeb().toString());
        df.setText(e.getDateFin().toString());
        titre.setText(titre.getText() + " " + e.getNomEvent());
        description.setText(e.getDescription());
        lienY.setText(e.getLienYoutube());
        nbM.setText(nbM.getText() + " " + e.getNbreMax_participant());
        s = n;
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
            pct.setImage(e.getImage());
            pct.setNbremax_participant(e.getNbreMax_participant() + "");
            pct.setDescription(e.getDescription());
            pct.setLienyoutube(e.getLienYoutube());
            s.setRoot(root);
            Stage stage = (Stage) description.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEvenementBackOfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
