/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Farah
 */
import javafx.scene.paint.Color;


public class Mapping extends Application {
    private Scene scene;
    public static Browser browser;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Web View");
        browser = new Browser(30,100,true);
        scene = new Scene(browser, 750, 500, Color.web("#666970"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
