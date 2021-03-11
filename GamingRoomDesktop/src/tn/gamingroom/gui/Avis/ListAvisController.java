/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Avis;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.gamingroom.entities.Avis;
import tn.gamingroom.entities.Reclamation;
import tn.gamingroom.services.AvisService;

/**
 * FXML Controller class
 *
 * @author Dah
 */
public class ListAvisController implements Initializable {

    @FXML
    private TableView<Avis> listRec;
    @FXML
    private TableColumn<Avis, Integer> colId;
    @FXML
    private TableColumn<Avis, String> colAvis;
    @FXML
    private TableColumn<Avis, String> colM;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         colId.setCellValueFactory(new PropertyValueFactory<Avis,Integer>("id"));
        colAvis.setCellValueFactory(new PropertyValueFactory<Avis,String>("avis"));
        colM.setCellValueFactory(new PropertyValueFactory<Avis,String>("member_id"));
        AvisService as=new  AvisService();
        this.listRec.setItems(FXCollections.observableArrayList(as.getListAvis()));
    }    
    
}
