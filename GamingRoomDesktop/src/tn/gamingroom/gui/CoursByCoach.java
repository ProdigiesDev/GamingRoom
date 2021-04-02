/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author eyatr
 */
public class CoursByCoach extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
       try {
            Parent root = FXMLLoader.load(getClass().getResource("CoursByCoach.fxml"));
            Scene scene = new Scene(root, 1500, 720);
            
            primaryStage.setTitle("GamingRoom");
            primaryStage.setScene(scene);
            
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
