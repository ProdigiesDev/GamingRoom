/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yasmine
 */
public class ADDCleController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void interface2(ActionEvent event) throws IOException {
        
        Parent dashboard ;
                dashboard = FXMLLoader.load(getClass().getResource("ListProduit.fxml"));


                Scene dashboardScene = new Scene(dashboard);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(dashboardScene);
             //   window.setTitle("Gestion Produit");
                window.show();
    
}

    @FXML
    private void interfaceCle(ActionEvent event) throws IOException {
        
        
        
         Parent dashboard ;
                dashboard = FXMLLoader.load(getClass().getResource("AjoutCle.fxml"));


                Scene dashboardScene = new Scene(dashboard);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(dashboardScene);
                window.show();
        
        
    }
}