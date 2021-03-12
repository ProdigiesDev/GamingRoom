/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import tn.gamingroom.entities.PanierProduit;
import tn.gamingroom.entities.Panier;
import tn.gamingroom.entities.Produits;
import tn.gamingroom.services.CommandService;
import tn.gamingroom.services.PanierService;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class PanierController implements Initializable {

    @FXML
    private TableColumn<PanierProduit, ImageView> colImage;
    @FXML
    private TableColumn<PanierProduit, String> colNom;
    @FXML
    private TableColumn<PanierProduit, Double> colPrixU;
    @FXML
    private TableColumn<PanierProduit, Integer> colQuantite;
    @FXML
    private TableColumn<PanierProduit, Double> colPrix;
    @FXML
    private TableView<PanierProduit> listTable;
    @FXML
    private Button btCom;
    
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      initTable();
    }    
    private void addButtonToTable() {
        TableColumn<PanierProduit, Void> colBtn = new TableColumn("");
        Callback<TableColumn<PanierProduit, Void>, TableCell<PanierProduit, Void>> cellFactory = new Callback<TableColumn<PanierProduit, Void>, TableCell<PanierProduit, Void>>() {
            @Override
            public TableCell<PanierProduit, Void> call(final TableColumn<PanierProduit, Void> param) {
                final TableCell<PanierProduit, Void> cell = new TableCell<PanierProduit, Void>() {

                    private final Button btn = new Button("Supprimer");
                  
                    {
                       btn.setTextFill(Paint.valueOf("#f8f7f7"));
                       btn.setStyle("-fx-background-color: #6f1075");
                        btn.setOnAction( event -> {
                            PanierProduit data = getTableView().getItems().get(getIndex());
                            PanierService panierService=new PanierService();
                            panierService.supprimerProd(data.getIdpanier());
                            initTable();
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

        listTable.getColumns().add(colBtn);

    }
    
     @FXML
    private void ajouterCommande(ActionEvent event) {
        JFrame f=new JFrame();
        int a=JOptionPane.showConfirmDialog(f,"Êtes-vous sûr?");
        if(a==JOptionPane.YES_OPTION){
         CommandService commandService=new CommandService();
         int memberId=3;
         int nb=commandService.confirmerCommande(memberId);
          if(nb==0)
          {
            JOptionPane.showMessageDialog(null, "Erreur lors de la confirmation de votre commande");
          }
          else{
              
            JOptionPane.showMessageDialog(null, "Votre commande est en cours de traitement");
            listTable.getItems().clear();
          }
        }
    } 
    
    void initTable(){
         PanierService ps= new PanierService();
       List<Panier>  paniers= ps.consulterPanier(3) ;
       List<PanierProduit> panierProduits=new ArrayList<>();
      paniers.forEach(p->{
          Produits p1= ps.getProductById(p.getProduit_id());
          ImageView imageView=new ImageView(new Image (p1.getImage()));
          imageView.setFitHeight(100);
          imageView.setFitWidth(100);
//          TextField  fXTextField=new TextField ();
//          fXTextField.setVisible(true);
//          fXTextField.setText(String.valueOf(p.getQuantityDemande()));
//          fXTextField.setOnMouseClicked(e->{
//                  try{
//                      Integer.parseInt(fXTextField.getText());
//                  }catch(Exception ex){
//                      fXTextField.setText("");
//                      JOptionPane.showMessageDialog(null, "Quantite doit etre entier");
//
//                  }
//                });

          panierProduits.add(new PanierProduit(p1.getIdprod(),p.getId(),p1.getLibelle(),p.getQuantityDemande(),imageView,(double) (p1.getPrix()*p.getQuantityDemande()),(double) p1.getPrix()));
          
      });
        ObservableList<PanierProduit> list=FXCollections.observableArrayList(panierProduits);
        colImage.setCellValueFactory(new PropertyValueFactory<PanierProduit, ImageView>("image"));
        colNom.setCellValueFactory(new PropertyValueFactory<PanierProduit, String>("nom"));
        colPrixU.setCellValueFactory(new PropertyValueFactory<PanierProduit, Double>("PrixUnitaire"));
        colQuantite.setCellValueFactory(new PropertyValueFactory<PanierProduit, Integer>("quantite"));
        colPrix.setCellValueFactory(new PropertyValueFactory<PanierProduit, Double>("Prix"));
        addButtonToTable();
        
        btCom.setVisible(!panierProduits.isEmpty());
      listTable.setItems(list);
    }

   

}
