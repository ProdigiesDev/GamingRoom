/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import com.jfoenix.controls.JFXButton;
import com.sun.xml.internal.bind.v2.runtime.property.PropertyFactory;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import tn.gamingroom.entities.Cours;
import tn.gamingroom.entities.ReacCours;
import tn.gamingroom.services.ServiceCours;
import tn.gamingroom.services.ServiceReacCours;

/**
 * FXML Controller class
 *
 * @author eyatr
 */
public class InfoCoursController implements Initializable {

    @FXML
    private TextField nom;
    
    @FXML
    private TextField cat;
    @FXML
    private TextField des;
    @FXML
    private TextField com;
    @FXML
    private FontAwesomeIconView like;
    @FXML
    private TableColumn<ReacCours, String> allcom;
    @FXML
    private TableColumn<ReacCours, Integer> colMem;

    Cours c;
    
    int memberId=3;
    @FXML
    private TableView<ReacCours> tablCom;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private Text nbInter;
    @FXML
    private Button back;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        colMem.setCellValueFactory(new PropertyValueFactory<ReacCours,Integer>("membre_id"));
        allcom.setCellValueFactory(new PropertyValueFactory<ReacCours,String>("commentaire"));
       
    }

    public void setCours(Cours c) {
        this.c = c;
        cat.setText(String.valueOf(c.getCategorie_id()));
        nom.setText(c.getNomCours());
        des.setText(c.getDescription());
        initTable();
        initNbInter();
        
    }
     
     
     
     void initTable(){
         ServiceReacCours cours=new ServiceReacCours();
         ObservableList<ReacCours> courses=FXCollections.observableArrayList(cours.getListReacCours(c.getId()));
         tablCom.setItems(courses);
     }

    @FXML
    private void ajouterCommentaire(ActionEvent event) {
        String commentaire=com.getText();
        ReacCours cours=new ReacCours(0,commentaire,memberId,c.getId());
        ServiceReacCours serviceReacCours=new ServiceReacCours();
        serviceReacCours.ajouterReacC(cours);
        tablCom.getItems().add(cours);
    }

    @FXML
    private void react(MouseEvent event) {
        ServiceReacCours serviceReacCours=new ServiceReacCours();
                                      
        ReacCours rc=serviceReacCours.getFirstReactionById(this.c.getId(),memberId);
        
        if(rc==null){
            ReacCours cours=new ReacCours(1,"",memberId,c.getId());
            serviceReacCours.ajouterReacC(cours);
        }
        else{
            
        rc.setNb_interaction(1);
        serviceReacCours.updateReacC(rc);
        }
        initNbInter();
    }
     
     
    void initNbInter(){
        ServiceReacCours serviceReacCours=new ServiceReacCours();
        Integer[] nbLikes=serviceReacCours.getNbInteraction(this.c.getId());
        nbInter.setText(String.valueOf(nbLikes[0]));
        
    }

    @FXML
    private void back(ActionEvent event) {
        try {
            Parent root = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("coursMembreint.fxml"));
            root = loader.load();
            CoursMembreintController pct = loader.getController();
            
            nom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InfoCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    

