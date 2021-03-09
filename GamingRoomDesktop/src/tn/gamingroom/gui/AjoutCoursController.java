/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Cours;
import tn.gamingroom.services.ServiceCours;

/**
 * FXML Controller class
 *
 * @author eyatr
 */
public class AjoutCoursController implements Initializable {

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfdes;
    @FXML
    private TextField tfnb;
    @FXML
    private TextField tfcat;
    @FXML
    private TextField tfmem;
    @FXML
    private TextField tfdate;
    @FXML
    private TextField tfcl;
    @FXML
    private Button btnval;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterCours(ActionEvent event) {
        try {
            Cours c = new Cours();
            ServiceCours s = new ServiceCours();
            s.ajouterCours(c);
            //Save cours in database
            int resId = Integer.parseInt(tfid.getText());
            String resNom = tfnom.getText();
            String resDes = tfdes.getText();
            int resnb = Integer.parseInt(tfnb.getText());
            int resmem = Integer.parseInt(tfmem.getText());
            Date resDate = Date.valueOf(tfdate.getText());
            String restag = tfcl.getText();
            int resCat = Integer.parseInt(tfcat.getText());
            //REDERICTION vers une autre fenetre
            
//        c.setId(Integer.parseInt(tfid.getText()));
//        c.setNomCours(tfnom.getText());
//        c.setDescription(tfdes.getText());
//        c.setNb_participants(Integer.parseInt(tfnb.getText()));
//        c.setMembre_id(Integer.parseInt(tfmem.getText()));
//        c.setDate_creation(Date.valueOf(tfdate.getText()));
//        c.setTags(tfcl .getText());
//        c.setCategorie_id(Integer.parseInt(tfcat.getText()));
//        JOptionPane.showMessageDialog(null,"Cours ajouté");

FXMLLoader loader = new FXMLLoader(getClass().getResource("coursDetails.fxml"));
Parent root = loader.load();
CoursDetailsController cd =loader.getController();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        

                
        
        
        
        
        
        
    }
    
}
