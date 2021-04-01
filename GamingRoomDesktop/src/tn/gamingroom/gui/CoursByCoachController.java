/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import tn.gamingroom.entities.Categorie;
import tn.gamingroom.entities.Cours;
import tn.gamingroom.entities.Courslm;
import tn.gamingroom.outils.Env;
import tn.gamingroom.services.CategorieServices;
import tn.gamingroom.services.ServiceCours;

/**
 * FXML Controller class
 *
 * @author eyatr
 */
public class CoursByCoachController implements Initializable {

    @FXML
    private TableView<Courslm> tableCours;
    @FXML
    private TableColumn<Courslm, ImageView> cimage;
    @FXML
    private TableColumn<Courslm, String> clien;
    @FXML
    private TableColumn<Courslm, String>  cnom;
    @FXML
    private TableColumn<Courslm, String>  cdes;
    
    @FXML
    private TableColumn<Courslm, Date> cdate;
    @FXML
    private TableColumn<Courslm, String> cmoc;
    @FXML
    private TableColumn<Courslm, Integer> ccat;
    @FXML
    private TableColumn<Courslm, Integer> cnb;
    @FXML
    private Label titre;
    int membre_id = 3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ServiceCours s = new ServiceCours();

        ObservableList<Cours> listCours = FXCollections.observableArrayList(s.displayCoursByCoach());
        cimage.setCellValueFactory(new PropertyValueFactory<Courslm, ImageView>("image"));
        cnom.setCellValueFactory(new PropertyValueFactory<Courslm, String>("nomCours"));
        ccat.setCellValueFactory(new PropertyValueFactory<Courslm, Integer>("categorie_id"));
        cnb.setCellValueFactory(new PropertyValueFactory<Courslm, Integer>("categorie_id"));
        cdate.setCellValueFactory(new PropertyValueFactory<Courslm, Date>("categorie_id"));
        cmoc.setCellValueFactory(new PropertyValueFactory<Courslm, String>("categorie_id"));
        cdes.setCellValueFactory(new PropertyValueFactory<Courslm, String>("description"));
        clien.setCellValueFactory(new PropertyValueFactory<Courslm, String>("lienYoutube"));

        

        this.initTable(null);
        
    }   
    
    private void initTable(List<Cours> listcours) {
        ServiceCours es = new ServiceCours();
        CategorieServices cs = new CategorieServices();
        if (listcours == null) {
            listcours = es.displayCours();
        }
        List<Courslm> lIm = new ArrayList<Courslm>();
        listcours.forEach(e -> {

            try {
                File f = new File(Env.getImagePath() + "cours\\" + e.getImage());
                Image img = new Image(f.toURI().toURL().toExternalForm());
                ImageView i = new ImageView(img);
                i.setFitHeight(50);
                i.setFitWidth(70);
                Courslm addCours = new Courslm(e.getId(), e.getNomCours(), e.getDescription(), e.getNb_participants(), e.getMembre_id(), e.getDate_creation(), e.getTags(), i, e.getCategorie_id(), cs.getCategorieById(e.getCategorie_id()).getNomcat());
                addCours.setImagename(e.getImage());
                addCours.setLienYoutube(e.getLienYoutube());
                lIm.add(addCours);
            } catch (MalformedURLException ex) {
                Logger.getLogger(CoursDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        //System.out.println("ev " + lIm);

        ObservableList<Courslm> listCoursIm = FXCollections.observableArrayList(lIm);

        tableCours.setItems(listCoursIm);
    }

    @FXML
    private void getSelected(MouseEvent event) {
    }

   
    
}
