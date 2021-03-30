/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tn.gamingroom.chat.server.Server;
import static tn.gamingroom.chat.server.ServerApplication.threads;
import tn.gamingroom.outils.Env;

/**
 *
 * @author Dah
 */
public class Home extends Application {


    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("GamingRoom Chat Server");

        try {

            //Parent root=FXMLLoader.load(getClass().getResource("Reclamation/AjouterReclamation.fxml"));
            Parent root=FXMLLoader.load(getClass().getResource("Avis/AjouterAvis.fxml"));
            // Parent root=FXMLLoader.load(getClass().getResource("Chat/chat.fxml"));
           // Parent root = FXMLLoader.load(getClass().getResource("Accueil/Accueil.fxml"));

            Scene scene = new Scene(root);
            primaryStage.setTitle("GamingRoom");
            primaryStage.setScene(scene);

            primaryStage.setMaximized(true);
            primaryStage.setHeight(700);
            primaryStage.getIcons().add(new Image(new File("C:\\Users\\Dah\\Desktop\\demo\\images\\logo.png").toURI().toURL().toExternalForm()));
            primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
