/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.evenement;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Asus
 */
public class EvenementHomePage extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 900, 500);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
//public class DatePickerPopupDemo extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//        try {
//            BorderPane root = new BorderPane();
//            Scene scene = new Scene(root, 400, 400);
//            //    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//            DatePicker datePicker = new DatePicker(LocalDate.now());
//
//            DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
//            Node popupContent = datePickerSkin.getPopupContent();
//
//            root.setCenter(popupContent);
//
//            primaryStage.setScene(scene);
//            primaryStage.show();
//
////[...]
//            LocalDate selectedDate = datePicker.getValue();
//            datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
//                @Override
//                public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
//                    System.out.println("New Value: " + newValue);
//                }
//            });
////Or using neat lambda
//            datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
//                System.out.println("New Value: " + newValue);
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
