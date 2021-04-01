/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import tn.gamingroom.entities.Evenement;
import tn.gamingroom.outils.Env;
import tn.gamingroom.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author Farah
 */
public class ListeEvenementsUtilisateurController implements Initializable {

    @FXML
    private Label n;
    @FXML
    private HBox vBoxEv;
    private List<Evenement> listeEvents;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EvenementService es = new EvenementService();
        listeEvents = es.getUserEvents(1);
        listeEvents.forEach(ev -> {

            try {
                Pane paneEv = new Pane();

                vBoxEv.setPadding(new Insets(20));
                vBoxEv.setSpacing(30);
                paneEv.setPrefWidth(200);
                paneEv.setPrefHeight(200);
                ImageView evImage = new ImageView(new Image(new File(ev.getImage()).toURI().toURL().toExternalForm()));
                Pane paneEv2 = new Pane();
                paneEv.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5);");
                System.out.println("im " + evImage.getImage());
                evImage.setFitWidth(200);
                evImage.setFitHeight(200);
                TextFlow textFlow = new TextFlow();
                textFlow.setPrefWidth(147);
                textFlow.setPrefHeight(150);
                textFlow.setLayoutX(5);
                textFlow.setLayoutY(230);
                getListForEVDesc(ev.getDescription(), textFlow);
                Label EvTitle = new Label(ev.getNomEvent());
                EvTitle.getStyleClass().add("EventTitle");
                EvTitle.setPrefWidth(668);
                EvTitle.setPrefHeight(36);
                EvTitle.setLayoutX(5);
                EvTitle.setLayoutY(200);
                EvTitle.setStyle("-fx-text-fill:#ffffff");
                JFXButton viewMore = new JFXButton();
                viewMore.setText("View More");
                viewMore.getStyleClass().add("btnMore");
                viewMore.setStyle("-fx-font-size:20");
                viewMore.setStyle("-fx-text-fill:#ffffff");
                viewMore.setLayoutX(5);
                viewMore.setLayoutY(300);
                viewMore.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {

                        try {
                            FXMLLoader loader;
                            loader = new FXMLLoader(getClass().getResource("consulterEventFrontOffice.fxml"));
                            Parent root = loader.load();
                            Scene s = new Scene(root);
                            ConsulterEventFrontOfficeController evController = loader.getController();
                            evController.intData(ev.getIdevent(), viewMore.getScene());
                            Stage primaryStage = new Stage();
                            primaryStage.setScene(s);
                            primaryStage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(ListeEvenementsUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                paneEv.getChildren().addAll(evImage, textFlow, EvTitle, viewMore);
                vBoxEv.getChildren().add(paneEv);
            } catch (MalformedURLException ex) {
                Logger.getLogger(ListeEvenementsUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private ObservableList getListForEVDesc(String desc, TextFlow textFlow) {
        ObservableList list = textFlow.getChildren();
        List<String> listreviw = Arrays.asList((desc).split("(?<=\\G.{30})"));

        listreviw.forEach(reviewSub -> {
            Text text1 = new Text(reviewSub);

            //Setting font to the text 
            text1.setFont(new Font(12));

            //Setting color to the text  
            text1.setFill(Color.DARKTURQUOISE);

            list.add(text1);
        });
        return list;
    }
}
