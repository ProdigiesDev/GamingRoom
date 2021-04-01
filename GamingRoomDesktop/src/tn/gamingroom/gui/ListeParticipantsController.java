/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.services.MembreServices;

/**
 * FXML Controller class
 *
 * @author Farah
 */
public class ListeParticipantsController implements Initializable {

    
    @FXML
    private TableView<Membre> table_memb;
    @FXML
    private TableColumn<Membre, Integer> colonne_id;
    @FXML
    private TableColumn<Membre, String> colonne_name;
    @FXML
    private TableColumn<Membre, String> colonne_tel;
    @FXML
    private TableColumn<Membre, String> colonne_email;
    @FXML
    private TableColumn<Membre,Integer > colonne_pt;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    public void afficherMembre(List<Membre> mL){
        MembreServices s = new MembreServices();

        ObservableList<Membre> listMembre = FXCollections.observableArrayList(mL);
        colonne_id.setCellValueFactory(new PropertyValueFactory<Membre, Integer>("id"));
        colonne_name.setCellValueFactory(new PropertyValueFactory<Membre, String>("prenom"));
        colonne_tel.setCellValueFactory(new PropertyValueFactory<Membre, String>("tel"));
        colonne_email.setCellValueFactory(new PropertyValueFactory<Membre, String>("email"));
        colonne_pt.setCellValueFactory(new PropertyValueFactory<Membre, Integer>("point"));
        
        table_memb.setItems(listMembre);
    }
    public void initDat(int idE){
        MembreServices s = new MembreServices();
        List<Membre> mL=s.getListeParticipants(idE);
        afficherMembre(mL);
    }
    
}
