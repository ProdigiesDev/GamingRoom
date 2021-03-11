/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import tn.gamingroom.entities.Cours;
import tn.gamingroom.services.ServiceCours;

/**
 * FXML Controller class
 *
 * @author eyatr
 */
public class CoursMembreintController implements Initializable {

    @FXML
    private TableColumn<Cours, File> cimg;
    @FXML
    private TableColumn<Cours, String> cnom;
    @FXML
    private TableColumn<Cours, String> cdes;
    @FXML
    private TableColumn<Cours, Integer> ccat;
    @FXML
    private TableView<Cours> tbCours;
    @FXML
    private TextField prefer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServiceCours s = new ServiceCours();

        ObservableList<Cours> listCours = FXCollections.observableArrayList(s.displayCours());
        cnom.setCellValueFactory(new PropertyValueFactory<Cours, String>("nomCours"));
       // cid.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("Id"));
        cdes.setCellValueFactory(new PropertyValueFactory<Cours, String>("description"));
        //cdate.setCellValueFactory(new PropertyValueFactory<Cours, Date>("date_creation"));
        //cmoc.setCellValueFactory(new PropertyValueFactory<Cours, String>("tags"));
       // cmem.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("membre_id"));
        //cnb.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("nb_participants"));
        ccat.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("categorie_id"));
        tbCours.setItems(listCours);
        // TODO
    }    

    @FXML
    private void preferCours(KeyEvent event) {
         ServiceCours s = new ServiceCours();
        ObservableList<Cours> list = FXCollections.observableArrayList(s.displayprefcours(Integer.parseInt(prefer.getText())));

        cnom.setCellValueFactory(new PropertyValueFactory<Cours, String>("nomCours"));
        //cid.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("Id"));
        cdes.setCellValueFactory(new PropertyValueFactory<Cours, String>("description"));
        //cdate.setCellValueFactory(new PropertyValueFactory<Cours, Date>("date_creation"));
        //cmoc.setCellValueFactory(new PropertyValueFactory<Cours, String>("tags"));
        //cmem.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("membre_id"));
        //cnb.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("nb_participants"));
        ccat.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("categorie_id"));
        tbCours.setItems(list);
    }
    
}
