/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Cles;
import tn.gamingroom.entities.ImageProduits;
import tn.gamingroom.entities.Produits;
import tn.gamingroom.outils.Env;
import tn.gamingroom.services.CleService;
import tn.gamingroom.services.ProduitCrud;

/**
 * FXML Controller class
 *
 * @author yasmine
 */
public class ListProduitController implements Initializable {

    @FXML
    private TableColumn<ImageProduits, ImageView> colIMAGE;
    @FXML
    private TableColumn<ImageProduits, String> colLibelle;
    @FXML
    private TableColumn<ImageProduits, Double> colPRIX;
    @FXML
    private TableColumn<ImageProduits, String> COLDESC;
    @FXML
    private TableColumn<ImageProduits, String> COLCAT;
    @FXML
    private TableView<ImageProduits> tvList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        
         
        COLCAT.setCellValueFactory(new PropertyValueFactory<ImageProduits, String>("nomCat"));
        colIMAGE.setCellValueFactory(new PropertyValueFactory<ImageProduits, ImageView>("image"));
        colLibelle.setCellValueFactory(new PropertyValueFactory<ImageProduits, String>("libelle"));
        colPRIX.setCellValueFactory(new PropertyValueFactory<ImageProduits, Double>("prix"));
        COLDESC.setCellValueFactory(new PropertyValueFactory<ImageProduits, String>("description"));
        
        
        initTable(null);
    }    
   
      void initTable(List<Produits> produitses) {
        ProduitCrud crud = new ProduitCrud();
        if (produitses == null) {
            produitses = crud.displayProduit();
        }
        List<ImageProduits> lIm = new ArrayList<ImageProduits>();
        produitses.forEach(e -> {

            try {
                File f = new File(Env.getImagePath() + "produits\\" + e.getImage());
                Image img = new Image(f.toURI().toURL().toExternalForm());
                ImageView i = new ImageView(img);
                i.setFitHeight(50);
                i.setFitWidth(70);
                ImageProduits imageProduits = new ImageProduits();
                imageProduits.setDescription(e.getDescription());
                imageProduits.setId_cat(e.getId_cat());
                //imageProduits.setIdprod(e.getIdprod());
                imageProduits.setImage(i);
                imageProduits.setImagename(e.getImage());
                imageProduits.setLibelle(e.getLibelle());
                imageProduits.setNomCat(e.getNomCat());
                imageProduits.setPrix(e.getPrix());

                imageProduits.setImagename(e.getImage());
                lIm.add(imageProduits);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }

        });
        System.out.println("ev " + lIm);

        ObservableList<ImageProduits> listProduitIm = FXCollections.observableArrayList(lIm);
        tvList.setItems(listProduitIm);
    }
    
}
   