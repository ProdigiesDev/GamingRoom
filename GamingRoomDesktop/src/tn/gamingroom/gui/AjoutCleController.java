/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Cles;
import tn.gamingroom.entities.Produits;
import tn.gamingroom.services.CleService;
import tn.gamingroom.services.ProduitCrud;

/**
 * FXML Controller class
 *
 * @author yasmine
 */
public class AjoutCleController implements Initializable {

    @FXML
    private ComboBox<Produits> listProd;
    @FXML
    private TextField nbCle;
    @FXML
    private Button btnAjouter;
    @FXML
    private TableView<Cles> tableview;
    @FXML
    private TableColumn<Cles, Integer> colid;
    @FXML
    private TableColumn<Cles, String> colcode;
    @FXML
    private TableColumn<Cles, Integer> colproduitid;
    @FXML
    private TextField tfrechProd;
    @FXML
    private Button btnload;
    @FXML
    private ImageView reduce;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         nbCle.setStyle("-fx-text-fill: white;");
        ProduitCrud crud=new ProduitCrud();
        List<Produits> produitses=crud.displayProduit();
        Callback<ListView<Produits>, ListCell<Produits>> factory = lv -> new ListCell<Produits>() {
            
            @Override
            protected void updateItem(Produits item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getIdprod()+" "+item.getLibelle());
            }

        };
        
        listProd.setCellFactory(factory);
        listProd.setButtonCell(factory.call(null));
        listProd.setItems(FXCollections.observableArrayList(produitses));
        addButtonToTable();
 showCle();
    }    

    @FXML
    private void ajouterCle(ActionEvent event) {
        CleService cleService=new CleService();
        Produits p=listProd.getValue();
        int nb=0;
          try{
            nb=Integer.parseInt(nbCle.getText());
        }catch(Exception ex){
            nbCle.setText("");
            JOptionPane.showMessageDialog(null,"Il faut ajouter un entier");
            return;
        }
        int nbCles=0;
        for (int i = 0; i < nb; i++) {
           nbCles+= cleService.ajouterCle(new Cles("", p.getIdprod()));
        }
        JOptionPane.showMessageDialog(null,nbCles+" clé ajouté");
        showCle();
    }

    public void showCle() {
        CleService cle = new CleService();
        ObservableList<Cles> list = FXCollections.observableArrayList(cle.displayCle());

      colid.setCellValueFactory(new PropertyValueFactory<Cles, Integer>("idcle"));
        colcode.setCellValueFactory(new PropertyValueFactory<Cles, String>("code"));
        colproduitid.setCellValueFactory(new PropertyValueFactory<Cles, Integer>("produit_id"));
        tableview.setItems(list);
    }

  

    @FXML
    private void backToList(ActionEvent event) {
        
     
        try {
            Parent dashboard ;
            dashboard = FXMLLoader.load(getClass().getResource("ADDCle.fxml"));
            
            
            Scene dashboardScene = new Scene(dashboard);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(dashboardScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutCleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    
}


    private void supprimerCle(ActionEvent event) {
        
        CleService cle = new CleService();
        Cles c = new Cles();

        c = this.tableview.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de Suppression !");
        alert.setContentText("Voulez-Vous Vraiment Supprimer?");

        Optional<ButtonType> btn = alert.showAndWait();
        if (btn.get() == ButtonType.OK) {
            cle.supprimerCle(c.getIdcle());
            this.showCle();
            System.out.println("suppression avec succées");
        } else {
            alert.close();
        }

        clean(event);
        
    }

    private void clean(ActionEvent event) {
        listProd.getSelectionModel().select(null);
        
        nbCle.setText(null);
        
        
        
    }
    
    private void addButtonToTable() {
        TableColumn<Cles, Void> colBtn = new TableColumn("");

        Callback<TableColumn<Cles, Void>, TableCell<Cles, Void>> cellFactory = new Callback<TableColumn<Cles, Void>, TableCell<Cles, Void>>() {
            @Override
            public TableCell<Cles, Void> call(final TableColumn<Cles, Void> param) {
                final TableCell<Cles, Void> cell = new TableCell<Cles, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction( event -> {
                            Cles data = getTableView().getItems().get(getIndex());
                            CleService cleService=new CleService();
                            JFrame f=new JFrame();

                            int a=JOptionPane.showConfirmDialog(f,"Êtes-vous sûr?");
                            if(a==JOptionPane.YES_OPTION){
                                    int nb=cleService.supprimerCle(data.getIdcle());
                                    if(nb>0)
                                        getTableView().getItems().remove(getIndex());
                                    
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

        tableview.getColumns().add(colBtn);

    }

    @FXML
    private void RechercherProduit_ID(KeyEvent event) {
        
         CleService crud = new CleService();
  ObservableList<Cles> list = FXCollections.observableArrayList(crud.Rechercher_Produit_ID(Integer.parseInt(tfrechProd.getText())));

        colid.setCellValueFactory(new PropertyValueFactory<Cles, Integer>("idcle"));
        colcode.setCellValueFactory(new PropertyValueFactory<Cles, String>("code"));
        colproduitid.setCellValueFactory(new PropertyValueFactory<Cles, Integer>("produit_id"));
       
        tableview.setItems(list);
        
        
        
        
    }

    @FXML
    private void LoadTableCle(ActionEvent event) {
        
          CleService crud = new CleService();
        ObservableList<Cles> list = FXCollections.observableArrayList(crud.displayCle());

        colid.setCellValueFactory(new PropertyValueFactory<Cles, Integer>("idcle"));
           colcode.setCellValueFactory(new PropertyValueFactory<Cles, String>("code"));
        colproduitid.setCellValueFactory(new PropertyValueFactory<Cles, Integer>("produit_id"));
       
        tableview.setItems(list);
        
        
        
        
    }

    

    
}