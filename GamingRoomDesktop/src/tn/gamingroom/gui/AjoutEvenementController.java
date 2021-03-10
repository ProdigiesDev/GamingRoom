/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import tn.gamingroom.entities.Categorie;
import tn.gamingroom.services.CategorieServices;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AjoutEvenementController implements Initializable {

    List<String> isImage;
    @FXML
    private JFXTextField nomevent;
    @FXML
    private JFXDatePicker datedeb;
    @FXML
    private JFXDatePicker datefin;
    @FXML
    private JFXTextField selectedFile;
    @FXML
    private JFXComboBox<Categorie> categorie;
    @FXML
    private JFXTextField nbremax_participant;
    @FXML
    private JFXTextField lienyoutube;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isImage=new ArrayList<>();
        isImage.add(".jpg");
        isImage.add(".png");
        isImage.add(".gif");
        
        
        CategorieServices cs = new CategorieServices(); 
       
        ObservableList lCat = FXCollections.observableArrayList(cs.DisplayCategorie());
        
        categorie.setItems(lCat);
        
        categorie.setConverter(new StringConverter<Categorie>() {
            @Override
            public String toString(Categorie object) {
                return object.getNomcat();
            }

            @Override
            public Categorie fromString(String string) {
                return null;
            }
        });
        
        
    }    

    @FXML
    private void ChooseFile(ActionEvent event) {
        FileChooser fc=new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image", isImage));
        File f=fc.showOpenDialog(null);
        if(f != null){
            selectedFile.setText("Selected File::"+f.getAbsolutePath());
        }
        
    }

    @FXML
    private void ajouterE(ActionEvent event) {
    }
    
}
