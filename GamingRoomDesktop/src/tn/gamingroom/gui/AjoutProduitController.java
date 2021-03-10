/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Produits;
import tn.gamingroom.outils.MyConnection;
import tn.gamingroom.services.ProduitCrud;

/**
 * FXML Controller class
 *
 * @author yasmine
 */
public class AjoutProduitController implements Initializable {

    @FXML
    private JFXTextField tfimage;
    @FXML
    private JFXTextField tflibelle;
    @FXML
    private JFXTextField tfprix;
    @FXML
    private TableView<Produits> tvbox;
    @FXML
    private TableColumn<Produits, Integer> colid;
    @FXML
    private TableColumn<Produits, String> colimage;
    @FXML
    private TableColumn<Produits, String> collibelle;
    @FXML
    private TableColumn<Produits, Integer> colprix;
    @FXML
    private TableColumn<Produits, String> coldesc;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupp;
    @FXML
    private JFXTextField tfdesc;
    @FXML
    private Button btnTri;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showProduct();
    }    

    @FXML
    private void ajouterProduit(ActionEvent event) {
        
      //  Save Person IN DATABASE
            String resImage = tfimage.getText();
            String resLibelle = tflibelle.getText();
        
            int resPrix = Integer.parseInt( tfprix.getText() );
            String resDesc = tfdesc.getText();
    
            Produits pl=new Produits( resImage, resLibelle, resPrix, resDesc);
                    
            
            ProduitCrud pcd = new ProduitCrud();
            pcd.ajouterProduit(pl);
            JOptionPane.showMessageDialog(null, "produit ajouté");
            


//     Produits c = new Produits();
//             ProduitCrud pcd = new ProduitCrud();
//
//            c.setImage(tfimage.getText());
//            c.setLibelle(tflibelle.getText());
//            c.setPrix(Integer.parseInt(tfprix.getText()));
//        
//            c.setDescription(tfdesc .getText());
//      JOptionPane.showMessageDialog(null, "produit ajouté");

        
        
    }
    
public void showProduct(){
    ProduitCrud crud=new ProduitCrud();
    ObservableList <Produits> list=FXCollections.observableArrayList(crud.displayProduit());

    colid.setCellValueFactory(new PropertyValueFactory<Produits,Integer>("idprod"));
    colimage.setCellValueFactory(new PropertyValueFactory<Produits,String>("image"));
    collibelle.setCellValueFactory(new PropertyValueFactory<Produits,String>("libelle"));
    colprix.setCellValueFactory(new PropertyValueFactory<Produits,Integer>("prix"));
    coldesc.setCellValueFactory(new PropertyValueFactory<Produits,String>("description"));
    tvbox.setItems(list);
}
    
    
    
    
    
    
    
    
    
}
