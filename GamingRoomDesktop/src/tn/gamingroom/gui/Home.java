/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Dah
 */
public class Home extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        try {
            //Parent root=FXMLLoader.load(getClass().getResource("Reclamation/AjouterReclamation.fxml"));
            // Parent root=FXMLLoader.load(getClass().getResource("Avis/AjouterAvis.fxml"));
            // Parent root=FXMLLoader.load(getClass().getResource("Chat/chat.fxml"));
             Parent root=FXMLLoader.load(getClass().getResource("Accueil/Accueil.fxml"));
       
            Scene scene = new Scene(root);
            primaryStage.setTitle("GamingRoom");
            primaryStage.setScene(scene);
            //primaryStage.setMaximized(true);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
