/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Reclamation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.gamingroom.entities.Reclamation;
import tn.gamingroom.services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author Dah
 */
public class ListReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> listRec;
    @FXML
    private TableColumn<Reclamation, Integer> colId;
    @FXML
    private TableColumn<Reclamation, String> colSujet;
    @FXML
    private TableColumn<Reclamation, String> colMsg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ReclamationService reclamationService=new ReclamationService();
        ObservableList<Reclamation> listRec=FXCollections.observableArrayList(reclamationService.getListReclamation());
        
        colId.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer>("id"));
        colMsg.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("contenue"));
        colSujet.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("sujet"));

        this.listRec.setItems(listRec);
        
    }    
    
}
