/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Jeux;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import tn.gamingroom.entities.Jeux;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.outils.Env;
import tn.gamingroom.services.JeuxService;
import tn.gamingroom.services.MembreServices;

/**
 * FXML Controller class
 *
 * @author Dah
 */
public class JeuxController implements Initializable {

    @FXML
    private VBox vbox;
    private List<Jeux> listJeuxs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        JeuxService jeuxService = new JeuxService();
        listJeuxs = jeuxService.getJeuxByType(Jeux.Type.Desktop.toString());
        listJeuxs.forEach(jeux -> {
            try {
                Pane paneJeux = new Pane();
                paneJeux.setPrefWidth(1334);
                paneJeux.setPrefHeight(265);
                ImageView jeuxImage = new ImageView(new Image(new File(Env.getImagePath() + "\\jeux\\" + jeux.getImage()).toURI().toURL().toExternalForm()));
                jeuxImage.setFitWidth(462);
                jeuxImage.setFitHeight(273);
                TextFlow textFlow = new TextFlow();
                textFlow.setPrefWidth(592);
                textFlow.setPrefHeight(192);
                textFlow.setLayoutX(513);
                textFlow.setLayoutY(59);
                getListForJeuxDesc(jeux.getDescriString(), textFlow);
                Label jeuxTitle = new Label(jeux.getNom());
                jeuxTitle.getStyleClass().add("jeuxTitle");
                jeuxTitle.setPrefWidth(668);
                jeuxTitle.setPrefHeight(36);
                jeuxTitle.setLayoutX(513);
                jeuxTitle.setLayoutY(14);
                JFXButton viewMore = new JFXButton();
                viewMore.setText("View More");
                viewMore.getStyleClass().add("btnMore");
                viewMore.setStyle("-fx-font-size:20");
                viewMore.setLayoutX(1193);
                viewMore.setLayoutY(203);
                viewMore.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {

                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewJeux.fxml"));
                            Parent root = loader.load();
                            ViewJeuxController jeuxController = loader.getController();
                            jeuxController.editInterface(jeux);
                            viewMore.getScene().setRoot(root);
                        } catch (IOException ex) {
                            Logger.getLogger(JeuxController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                paneJeux.getChildren().addAll(jeuxImage, textFlow, jeuxTitle, viewMore);
                vbox.getChildren().add(paneJeux);
            } catch (MalformedURLException ex) {
                Logger.getLogger(JeuxController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private ObservableList getListForJeuxDesc(String desc, TextFlow textFlow) {
        ObservableList list = textFlow.getChildren();
        List<String> listreviw = Arrays.asList((desc).split("(?<=\\G.{30})"));

        listreviw.forEach(reviewSub -> {
            Text text1 = new Text(reviewSub);

            //Setting font to the text 
            text1.setFont(new Font(15));

            //Setting color to the text  
            text1.setFill(Color.DARKSLATEBLUE);

            list.add(text1);
        });
        return list;
    }
}
