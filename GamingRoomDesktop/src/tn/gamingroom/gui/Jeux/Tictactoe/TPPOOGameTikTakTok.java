/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Jeux.Tictactoe;

import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import tn.gamingroom.entities.Jeux;
import tn.gamingroom.gui.Jeux.AjouterJeuxController;
import tn.gamingroom.gui.Jeux.Snake;
import tn.gamingroom.outils.Env;

/**
 *
 * @author FER-SID_PC
 */
public class TPPOOGameTikTakTok extends Application {
    
    private Jeux jeux;

    public TPPOOGameTikTakTok(Jeux jeux) {
        this.jeux = jeux;
    }


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TikTakTok.fxml"));
        Parent root = loader.load();
        TikTakTokController controller = loader.getController();
        controller.setJeux(jeux);
        Scene scene = new Scene(root);
        stage.setTitle("TikTakTok");
        try {
            stage.getIcons().add(new Image(new File(Env.getImagePath()+"tictactoe.jpg").toURI().toURL().toExternalForm()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
