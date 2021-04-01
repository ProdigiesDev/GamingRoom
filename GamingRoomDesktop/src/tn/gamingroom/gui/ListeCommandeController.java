/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Commande;
import tn.gamingroom.entities.CommandeGui;
import tn.gamingroom.entities.Panier;
import tn.gamingroom.entities.CommandeGui;
import tn.gamingroom.entities.Produits;
import tn.gamingroom.entities.UserSession;
import tn.gamingroom.services.CommandService;
import tn.gamingroom.services.PanierService;
import tn.gamingroom.services.ProduitCrud;

/**
 * FXML Controller class 
 *
 * @author Admin
 */
public class ListeCommandeController implements Initializable {

    @FXML
    private TableView<CommandeGui> listTable;
    @FXML
    private TableColumn<CommandeGui, Integer> colCom;
    @FXML
    private TableColumn<CommandeGui, Date> colDate;
    @FXML
    private TableColumn<CommandeGui, Double> colNet;
    @FXML
    private TableColumn<CommandeGui, Commande.Statu> colEtat;
    @FXML
    private ImageView backIm;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        colCom.setCellValueFactory(new PropertyValueFactory<CommandeGui, Integer>("idcommande"));
        colDate.setCellValueFactory(new PropertyValueFactory<CommandeGui, Date >("datecommande"));
        colNet.setCellValueFactory(new PropertyValueFactory<CommandeGui, Double >("prixTot"));
        colEtat.setCellValueFactory(new PropertyValueFactory<CommandeGui, Commande.Statu >("etat"));
        addButtonToTable();
        initTable();
    }  
    void initTable(){
         CommandService cs= new CommandService();
         int memberId=3;
          if (UserSession.getInstance() != null) {
            memberId = UserSession.getInstance().getUser().getId();

        }
       List<Commande>  commande= cs.consulterMesCommande(memberId) ;
       List<CommandeGui>  commandeGuis=new ArrayList();
      commande.forEach(c->{
          PanierService ps = new PanierService();
          List<Panier> paniers =ps.getPanierByComandeId(c.getIdcommande());
          double prixTotale=0;
          for (int i = 0; i < paniers.size(); i++) {
            Produits p = ps.getProductById(paniers.get(i).getProduit_id());
            prixTotale+=p.getPrix()*paniers.get(i).getQuantityDemande();
          }
          System.out.println(prixTotale);
          commandeGuis.add(new CommandeGui(prixTotale, c.getIdcommande(),c.getMemberid(), c.getDatecommande(), c.getEtat()));
      });
        ObservableList<CommandeGui> list=FXCollections.observableArrayList(commandeGuis);
       
      listTable.setItems(list);
    }
    
    
    private void addButtonToTable() {
        TableColumn<CommandeGui, Void> colBtn = new TableColumn("");
        Callback<TableColumn<CommandeGui, Void>, TableCell<CommandeGui, Void>> cellFactory = new Callback<TableColumn<CommandeGui, Void>, TableCell<CommandeGui, Void>>() {
            @Override
            public TableCell<CommandeGui, Void> call(final TableColumn<CommandeGui, Void> param) {
                final TableCell<CommandeGui, Void> cell = new TableCell<CommandeGui, Void>() {

                    private final Button btn = new Button("Annuler");
                  
                    {
                       btn.setTextFill(Paint.valueOf("#f8f7f7"));
                       btn.setStyle("-fx-background-color: #6f1075");
                        btn.setOnAction( event -> {
                            CommandeGui data = getTableView().getItems().get(getIndex());
                            
                            JFrame f=new JFrame();

                            int a=JOptionPane.showConfirmDialog(f,"Êtes-vous sûr?");
                            if(a==JOptionPane.YES_OPTION){
                                 
                                CommandService commandService=new CommandService();
                                int nb=commandService.supprimerCommand(data.getIdcommande());
                                if(nb==0)
                                {
                                    JOptionPane.showMessageDialog(null, "Erreur lors d'annulation de commande");
                                }
                                else
                                {
                                    getTableView().getItems().remove(getIndex());
                                }
                                    
                            }
                            
                            
                            
                            
                        });
                    }
                            
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            CommandeGui data = getTableView().getItems().get(getIndex());
                            if(data.getEtat()==Commande.Statu.EnAttente)
                                setGraphic(btn);
                            else
                                setGraphic(null);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        listTable.getColumns().add(colBtn);

    }
    
}
