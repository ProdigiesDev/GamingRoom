/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
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
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Categorie;
import tn.gamingroom.entities.ImageProduits;
import tn.gamingroom.entities.Produits;
import tn.gamingroom.outils.Env;
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
    private JFXTextField tflibelle;
    @FXML
    private JFXTextField tfprix;
    @FXML
    private TableView<ImageProduits> tvbox;
    @FXML
    private TableColumn<ImageProduits, Integer> colid;
    @FXML
    private TableColumn<ImageProduits, ImageView> colimage;
    @FXML
    private TableColumn<ImageProduits, String> collibelle;
    @FXML
    private TableColumn<ImageProduits, Double> colprix;
    @FXML
    private TableColumn<ImageProduits, String> coldesc;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupp;
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
    private TableColumn<ImageProduits, String> colcat;
    private FileChooser fileChooser;
    private File file;
    Stage stage;
    @FXML
    private Button imagePath;
    @FXML
    private ImageView prodImage;
    @FXML
    private JFXTextArea textareaProd;
    @FXML
    private JFXTextField MaxPrix;
    @FXML
    private JFXTextField MinPrix;
    @FXML
    private Button btnSearchPrix;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tflibelle.setStyle("-fx-text-fill: white;");
        tfprix.setStyle("-fx-text-fill: white;");
        textareaProd.setStyle("-fx-text-fill: white;");
        tfid.setStyle("-fx-text-fill: white;");

        colid.setCellValueFactory(new PropertyValueFactory<ImageProduits, Integer>("idprod"));
        colcat.setCellValueFactory(new PropertyValueFactory<ImageProduits, String>("nomCat"));
        colimage.setCellValueFactory(new PropertyValueFactory<ImageProduits, ImageView>("image"));
        collibelle.setCellValueFactory(new PropertyValueFactory<ImageProduits, String>("libelle"));
        colprix.setCellValueFactory(new PropertyValueFactory<ImageProduits, Double>("prix"));
        coldesc.setCellValueFactory(new PropertyValueFactory<ImageProduits, String>("description"));

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

        initTable(null);
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg"));
    }

    @FXML
    private void ajouterProduit(ActionEvent event) {

        //  Save produit IN DATABASE
        String resImage = "";
        String resLibelle = tflibelle.getText();
        Categorie rescategorie = listCat.getValue();
        double resPrix = 0;
        try {

            resPrix = Double.parseDouble(tfprix.getText());

        } catch (Exception ex) {
            tfprix.setText("");
            JOptionPane.showMessageDialog(null, "Il faut ajouter un entier");
            return;

        }
        resPrix =Double.parseDouble(tfprix.getText());
        String resDesc = textareaProd.getText();
        Produits p2 = new Produits(rescategorie.getIdcat(), resImage, resLibelle, resPrix, resDesc);
        ProduitCrud pcd = new ProduitCrud();
        String fileName=moveImage();
    if(fileName.length()==0) 
        {
           JOptionPane.showMessageDialog(null, "Il faut choisir une image");   
        return ;
        }
        if (pcd.ajouterProduit(p2) > 0) {
            JOptionPane.showMessageDialog(null, "produit ajouté");
            initTable(null);
        }
 

    }

    private String moveImage() {
        if (file != null) {
            try {
                String fileName = file.getName();
                int doitIndex = fileName.lastIndexOf(".");
                String imageName = fileName.substring(0, doitIndex) + new java.util.Date().getTime() + "." + fileName.substring(doitIndex + 1);
                String imageNameTodb = Env.getImagePath() + "produits\\" + imageName;
                File dest = new File(imageNameTodb);
                Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                return imageName;

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return "";
    }
    


    @FXML
    private void clean(ActionEvent event) {

        tflibelle.setText(null);
        textareaProd.setText(null);
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
    initTable(produitses);
     

    }

    @FXML
    private void getSelected(MouseEvent event) {

        int index = tvbox.getSelectionModel().getSelectedIndex();

        if (index <= -1) {

            return;

        }
        tflibelle.setText(collibelle.getCellData(index).toString());
        tfprix.setText(colprix.getCellData(index).toString());
        textareaProd.setText(coldesc.getCellData(index).toString());
        tfid.setText(colid.getCellData(index).toString());

    }

    @FXML
    private void updateTable(ActionEvent event) {

        String Value1 = "";
        String Value2 = tflibelle.getText();
        double Value3 = Double.parseDouble(tfprix.getText());
        String Value4 = textareaProd.getText();
        int Value5 = Integer.parseInt(tfid.getText());
        Produits p2 = new Produits(Value5, 0, Value1, Value2, Value3, Value4);

        ProduitCrud pcd = new ProduitCrud();
        int x = pcd.updateProduit(p2);
        if (x > 0) {
            JOptionPane.showMessageDialog(null, "produit modifié");
            initTable(null);
        } else {
            System.out.println("produit non modifié");
        }

    }

    @FXML
    private void supprimer(ActionEvent event) {

        ProduitCrud pcd = new ProduitCrud();
        ImageProduits p = new ImageProduits();

        p = this.tvbox.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de Suppression !");
        alert.setContentText("Voulez-Vous Vraiment Supprimer?");

        Optional<ButtonType> btn = alert.showAndWait();
        if (btn.get() == ButtonType.OK) {
            pcd.supprimerProduit(p.getIdprod());
            this.initTable(null);
            System.out.println("suppression avec succées");
        } else {
            alert.close();
        }

        clean(event);

    }

    @FXML
    private void TrierParId(ActionEvent event) {
        ProduitCrud crud = new ProduitCrud();
  initTable(crud.TrierParId());
     
      

    }

    @FXML
    private void RechercherProduit(KeyEvent event) {

        ProduitCrud crud = new ProduitCrud();

    initTable(crud.RechercherProduit(tfrech.getText()));


    }

    @FXML
    private void loadTable(ActionEvent event) {

        ProduitCrud crud = new ProduitCrud();
        initTable(null);
    
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
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void loadImage(ActionEvent event) {

        file = fileChooser.showOpenDialog(stage);//ykhalini nekhtar fichier
        try {
            prodImage.setImage(new Image(file.toURI().toURL().toExternalForm()));//path image (ligne mtaa file)
        } catch (Exception ex) {
           ex.printStackTrace();
        }

    }

    @FXML
    private void statistique(ActionEvent event) {

        try {
            Parent stat;
            stat = FXMLLoader.load(getClass().getResource("statistique.fxml"));

            Scene dashboardScene = new Scene(stat);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(dashboardScene);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void SearchPrice(ActionEvent event) {
        double maxprice;
        double minprice;

        try {

            maxprice = Double.parseDouble(MaxPrix.getText());
            minprice = Double.parseDouble(MinPrix.getText());
            double price = minprice;
            
            if (maxprice < minprice) {

                minprice = maxprice;
                maxprice = price;
                MaxPrix.setText(String.valueOf(maxprice));//tconverti double ->chaine
                MinPrix.setText(String.valueOf(minprice));
                ProduitCrud crud = new ProduitCrud();
                initTable(crud.RechercherPrix(minprice, maxprice));
                
            }

        } catch (Exception ex) {

            MaxPrix.setText("");
            MinPrix.setText("");
            JOptionPane.showMessageDialog(null, "Il faut ajouter un entier");
            return;

        }
    }
    
    void initTable(List<Produits> produitses){
        ProduitCrud crud = new ProduitCrud();
if(produitses==null){
            produitses=crud.displayProduit();
        }
        List<ImageProduits> lIm = new ArrayList<ImageProduits>();
        produitses.forEach(e -> {

            try {
                File f = new File(Env.getImagePath() + "produits\\" + e.getImage());
                Image img = new Image(f.toURI().toURL().toExternalForm());
                ImageView i = new ImageView(img);
                i.setFitHeight(50);
                i.setFitWidth(70);
                ImageProduits imageProduits=new ImageProduits();
                imageProduits.setDescription(e.getDescription());
                imageProduits.setId_cat(e.getId_cat());
                imageProduits.setIdprod(e.getIdprod());
                imageProduits.setImage(i);
                imageProduits.setImagename(e.getImage());
                imageProduits.setLibelle(e.getLibelle());
                imageProduits.setNomCat(e.getNomCat());
                imageProduits.setPrix(e.getPrix());
                
                
                
                imageProduits.setImagename(e.getImage());
                lIm.add(imageProduits);
            } catch (MalformedURLException ex) {
ex.printStackTrace();            }

        });
        System.out.println("ev " + lIm);

        ObservableList<ImageProduits> listProduitIm = FXCollections.observableArrayList(lIm);
        tvbox.setItems(listProduitIm);
    }
}
