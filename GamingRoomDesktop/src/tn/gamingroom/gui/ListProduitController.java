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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Cles;
import tn.gamingroom.entities.Commande;
import tn.gamingroom.entities.ImageProduits;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.entities.Panier;
import tn.gamingroom.entities.PanierProduit;
import tn.gamingroom.entities.Produits;
import tn.gamingroom.entities.UserSession;
import tn.gamingroom.outils.Env;
import tn.gamingroom.services.CommandService;
import tn.gamingroom.services.CleService;
import tn.gamingroom.services.PanierService;
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
    @FXML
    private TextField qt;
    private Membre membre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (UserSession.getInstance() != null) {
            membre = UserSession.getInstance().getUser();
        }
        COLCAT.setCellValueFactory(
                new PropertyValueFactory<ImageProduits, String>("nomCat"));
        colIMAGE.setCellValueFactory(
                new PropertyValueFactory<ImageProduits, ImageView>("image"));
        colLibelle.setCellValueFactory(
                new PropertyValueFactory<ImageProduits, String>("libelle"));
        colPRIX.setCellValueFactory(
                new PropertyValueFactory<ImageProduits, Double>("prix"));
        COLDESC.setCellValueFactory(
                new PropertyValueFactory<ImageProduits, String>("description"));

        addButtonToTable();
        initTable(
                null);
    }

    void initTable(List<Produits> produitses) {
        ProduitCrud crud = new ProduitCrud();
        if (produitses == null) {
            produitses = crud.displayProduit();
        }
        List<ImageProduits> lIm = new ArrayList<ImageProduits>();
        produitses.forEach(e -> {

            try {
                File f = new File(Env.getImagePath() + "\\produits\\" + e.getImage());
                Image img = new Image(f.toURI().toURL().toExternalForm());
                ImageView i = new ImageView(img);
                i.setFitHeight(50);
                i.setFitWidth(70);
                ImageProduits imageProduits = new ImageProduits();
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
                ex.printStackTrace();
            }

        });
        System.out.println("ev " + lIm);
        ObservableList<ImageProduits> listProduitIm = FXCollections.observableArrayList(lIm);
        tvList.setItems(listProduitIm);
    }

    private void addButtonToTable() {

        TableColumn<ImageProduits, Void> colBtn = new TableColumn("");
        Callback<TableColumn<ImageProduits, Void>, TableCell<ImageProduits, Void>> cellFactory = new Callback<TableColumn<ImageProduits, Void>, TableCell<ImageProduits, Void>>() {
            @Override
            public TableCell<ImageProduits, Void> call(final TableColumn<ImageProduits, Void> param) {
                final TableCell<ImageProduits, Void> cell = new TableCell<ImageProduits, Void>() {

                    private final Button btn = new Button("Ajouter");

                    {
                        btn.setTextFill(Paint.valueOf("#f8f7f7"));
                        btn.setStyle("-fx-background-color: #6f1075");
                        btn.getStyleClass().add("btnMore");
                        btn.setOnAction(event -> {
                            if (membre == null) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setContentText("vous dois d'abord vous connecter ?");
                                alert.show();
                                return;
                            }
                            ImageProduits data = getTableView().getItems().get(getIndex());
                            PanierService panierService = new PanierService();
                            try {
                                int qtt = Integer.valueOf(qt.getText());
                                CleService cleService = new CleService();
                                List<Cles> cleses = cleService.Rechercher_Produit_ID(data.getIdprod());

                                if (qtt < 0 || qtt > cleses.size()) {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setContentText("la quantité maximale est : "+cleses.size());
                                    alert.show();
                                    return;
                                }
                                Panier panier = new Panier(data.getIdprod(), qtt);
                                addToPanier(panier);
                            } catch (Exception e) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setContentText("la quantité doit être un nombre entier");
                                alert.show();
                            }

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        tvList.getColumns().add(colBtn);

    }

    void addToPanier(Panier panier) {
        CommandService commandeService = new CommandService();
        Commande commande = commandeService.getCurrentCommande(membre.getId());
        if (commande == null) {
            commande = new Commande(membre.getId());
            int nb = commandeService.ajouterCommand(commande);
            if (nb > 0) {
                commande = commandeService.getCurrentCommande(membre.getId());
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Erreur");
                alert.setContentText("Une erreur s'est produite, veuillez réessayer plus tard");
                alert.show();
                return;
            }
        }
        PanierService panierService = new PanierService();
        panier.setCommande_id(commande.getIdcommande());
        int nb = panierService.ajouterProd(panier);
        if (nb == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Erreur");
            alert.setContentText("Une erreur s'est produite, veuillez réessayer plus tard");
            alert.show();
            return;

        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Le produit a été ajouté au panier");
        alert.show();
    }
}
