/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Categorie;
import tn.gamingroom.entities.Produits;
import tn.gamingroom.outils.MyConnection;
import tn.gamingroom.services.CategorieServices;
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
    @FXML
    private JFXTextField tfid;
    @FXML
    private Button btnannuler;
    @FXML
    private Button btnBest;
    @FXML
    private TextField tfrech;
    @FXML
    private Button btnload;
    @FXML
    private ComboBox<Categorie> listCat;
    @FXML
    private TableColumn<Produits, String> colcat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfimage.setStyle("-fx-text-fill: white;");
        tflibelle.setStyle("-fx-text-fill: white;");
        tfprix.setStyle("-fx-text-fill: white;");
        tfdesc.setStyle("-fx-text-fill: white;");
        tfid.setStyle("-fx-text-fill: white;");
        
        
        colid.setCellValueFactory(new PropertyValueFactory<Produits, Integer>("idprod"));
        colcat.setCellValueFactory(new PropertyValueFactory<Produits, String>("nomCat"));
        colimage.setCellValueFactory(new PropertyValueFactory<Produits, String>("image"));
        collibelle.setCellValueFactory(new PropertyValueFactory<Produits, String>("libelle"));
        colprix.setCellValueFactory(new PropertyValueFactory<Produits, Integer>("prix"));
        coldesc.setCellValueFactory(new PropertyValueFactory<Produits, String>("description"));
        
        // TODO
        CategorieServices crud = new CategorieServices();
        List<Categorie> categories = crud.DisplayCategorie();
        Callback<ListView<Categorie>, ListCell<Categorie>> factory = lv -> new ListCell<Categorie>() {

            @Override
            protected void updateItem(Categorie item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNomcat());
            }

        };
        listCat.setCellFactory(factory);
        listCat.setButtonCell(factory.call(null));
        listCat.setItems(FXCollections.observableArrayList(categories));
        //  addButtonToTable();
        showProduct();

    }

    @FXML
    private void ajouterProduit(ActionEvent event) {

        //  Save produit IN DATABASE
        String resImage = tfimage.getText();
        String resLibelle = tflibelle.getText();
        Categorie rescategorie = listCat.getValue();
        int resPrix = 0;
        try {

            resPrix = Integer.parseInt(tfprix.getText());

        } catch (Exception ex) {
            tfprix.setText("");
            JOptionPane.showMessageDialog(null, "Il faut ajouter un entier");
            return;

        }
        resPrix = Integer.parseInt(tfprix.getText());
        String resDesc = tfdesc.getText();
        Produits p2 = new Produits( rescategorie.getIdcat(), resImage, resLibelle, resPrix, resLibelle);
        ProduitCrud pcd = new ProduitCrud();
        if (pcd.ajouterProduit(p2) > 0) {
            JOptionPane.showMessageDialog(null, "produit ajouté");
        }
        showProduct();

    }

    public void showProduct() {
        ProduitCrud crud = new ProduitCrud();
        List<Produits> produitses=crud.displayProduit();
        
        ObservableList<Produits> list = FXCollections.observableArrayList(produitses);
        
        tvbox.setItems(list);
    }

    @FXML
    private void clean(ActionEvent event) {

        tfimage.setText(null);
        tflibelle.setText(null);
        tfdesc.setText(null);
        tfprix.setText(null);
        tfid.setText(null);

    }

    @FXML
    private void bestProductsSelled(ActionEvent event) {
        ProduitCrud pcd = new ProduitCrud();
        List<Integer> integers = pcd.bestProductsSelled();
        System.out.println(integers);
        List<Produits> produitses = new ArrayList<>();
        integers.forEach(i -> {
            produitses.add(pcd.getByID(i));
        });
        ObservableList<Produits> list = FXCollections.observableArrayList(produitses);

        tvbox.setItems(list);

    }

    @FXML
    private void getSelected(MouseEvent event) {

        int index = tvbox.getSelectionModel().getSelectedIndex();

        if (index <= -1) {

            return;

        }
        tfimage.setText(colimage.getCellData(index).toString());
        tflibelle.setText(collibelle.getCellData(index).toString());
        tfprix.setText(colprix.getCellData(index).toString());
        tfdesc.setText(coldesc.getCellData(index).toString());
        tfid.setText(colid.getCellData(index).toString());

    }

    @FXML
    private void updateTable(ActionEvent event) {

        String Value1 = tfimage.getText();
        String Value2 = tflibelle.getText();
        int Value3 = Integer.parseInt(tfprix.getText());
        String Value4 = tfdesc.getText();
        int Value5 = Integer.parseInt(tfid.getText());
        Produits p2 = new Produits(Value5,0, Value1, Value2, Value3, Value4);

        ProduitCrud pcd = new ProduitCrud();
        int x = pcd.updateProduit(p2);
        if (x > 0) {
            JOptionPane.showMessageDialog(null, "produit modifié");
            showProduct();

        } else {
            System.out.println("produit non modifié");
        }

    }

    @FXML
    private void supprimer(ActionEvent event) {

        ProduitCrud pcd = new ProduitCrud();
        Produits p = new Produits();

        p = this.tvbox.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de Suppression !");
        alert.setContentText("Voulez-Vous Vraiment Supprimer?");

        Optional<ButtonType> btn = alert.showAndWait();
        if (btn.get() == ButtonType.OK) {
            pcd.supprimerProduit(p);
            this.showProduct();
            System.out.println("suppression avec succées");
        } else {
            alert.close();
        }

        clean(event);

    }

    @FXML
    private void TrierParId(ActionEvent event) {
        ProduitCrud crud = new ProduitCrud();
        ObservableList<Produits> list = FXCollections.observableArrayList(crud.TrierParId());

        colid.setCellValueFactory(new PropertyValueFactory<Produits, Integer>("idprod"));
        colimage.setCellValueFactory(new PropertyValueFactory<Produits, String>("image"));
        collibelle.setCellValueFactory(new PropertyValueFactory<Produits, String>("libelle"));
        colprix.setCellValueFactory(new PropertyValueFactory<Produits, Integer>("prix"));
        coldesc.setCellValueFactory(new PropertyValueFactory<Produits, String>("description"));
        tvbox.setItems(list);

    }

    @FXML
    private void RechercherProduit(KeyEvent event) {

        ProduitCrud crud = new ProduitCrud();
        ObservableList<Produits> list = FXCollections.observableArrayList(crud.RechercherProduit(tfrech.getText()));

        colid.setCellValueFactory(new PropertyValueFactory<Produits, Integer>("idprod"));
        colimage.setCellValueFactory(new PropertyValueFactory<Produits, String>("image"));
        collibelle.setCellValueFactory(new PropertyValueFactory<Produits, String>("libelle"));
        colprix.setCellValueFactory(new PropertyValueFactory<Produits, Integer>("prix"));
        coldesc.setCellValueFactory(new PropertyValueFactory<Produits, String>("description"));
        tvbox.setItems(list);

    }

    @FXML
    private void loadTable(ActionEvent event) {

        ProduitCrud crud = new ProduitCrud();
        ObservableList<Produits> list = FXCollections.observableArrayList(crud.displayProduit());

        colid.setCellValueFactory(new PropertyValueFactory<Produits, Integer>("idprod"));
        colimage.setCellValueFactory(new PropertyValueFactory<Produits, String>("image"));
        collibelle.setCellValueFactory(new PropertyValueFactory<Produits, String>("libelle"));
        colprix.setCellValueFactory(new PropertyValueFactory<Produits, Integer>("prix"));
        coldesc.setCellValueFactory(new PropertyValueFactory<Produits, String>("description"));
        tvbox.setItems(list);
    }

    @FXML
    private void backToList(ActionEvent event) {

        try {
            Parent dashboard;
            dashboard = FXMLLoader.load(getClass().getResource("ADDCle.fxml"));

            Scene dashboardScene = new Scene(dashboard);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(dashboardScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutCleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
