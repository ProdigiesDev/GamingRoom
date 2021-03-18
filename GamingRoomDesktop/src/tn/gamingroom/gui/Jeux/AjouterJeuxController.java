/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Jeux;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Jeux;
import tn.gamingroom.services.JeuxService;

/**
 * FXML Controller class
 *
 * @author Dah
 */
public class AjouterJeuxController implements Initializable {

    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextArea txtDesc;
    @FXML
    private Text errDesc;
    @FXML
    private JFXButton btnenv;
    @FXML
    private ComboBox<Jeux.Type> comboPlat;
    @FXML
    private Text errNom;

    private Jeux jeux;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        txtNom.setStyle("-fx-text-fill: white; ");
        txtDesc.setStyle("-fx-text-fill: white; ");
        List<Jeux.Type> listPlat = new ArrayList();
        listPlat.add(Jeux.Type.Desktop);
        listPlat.add(Jeux.Type.Web);
        listPlat.add(Jeux.Type.Mobile);
        comboPlat.setItems(FXCollections.observableList(listPlat));
        comboPlat.getSelectionModel().select(0);
    }    

    @FXML
    private void onChangeNom(KeyEvent event) {
        errNom.setVisible(false);
    }

    @FXML
    private void onChangeDesc(KeyEvent event) {
        errDesc.setVisible(false);
    }

    @FXML
    private void ajouterJeux(ActionEvent event) {
        String nom=txtNom.getText();
        String description=txtDesc.getText();
        
        JeuxService jeuxService=new JeuxService();
        
        if(nom.length() == 0)
        {
            errNom.setText("Nom est obligatoire");
            errNom.setVisible(true);
        }
        
        if(description.length() == 0)
        {
            errDesc.setText("Description est obligatoire");
            errDesc.setVisible(true);
        }
        
        if(nom.length() == 0 || description.length() == 0)
            return;
        
        if(jeuxService.search(nom, comboPlat.getValue().toString()).size() > 0){
            JOptionPane.showMessageDialog(null,"Jeu avec le même nom déjà existé");
            return ;
        }
        
        jeux.setNom(nom);
        jeux.setDescriString(description);
        jeux.setType_plateforme(comboPlat.getValue());
        
        
        
        if(jeux.getId()==0){
            int nb=jeuxService.ajouter(jeux);
            if(nb == 0)
                JOptionPane.showMessageDialog(null,"Une erreur s'est produite, veuillez réessayer plus tard");
            else    
                JOptionPane.showMessageDialog(null,"Jeux ajouteé");

        }
        else{
            int nb=jeuxService.modifier(jeux);
            if(nb == 0)
                JOptionPane.showMessageDialog(null,"Une erreur s'est produite, veuillez réessayer plus tard");
            else    
                JOptionPane.showMessageDialog(null,"Jeux a ete modifié");
        }
       
        
        
    }

    public Jeux getJeux() {
        return jeux;
    }

    public void setJeux(Jeux jeux) {
        this.jeux = jeux;
    }
    
    public void editInterface(Jeux jeux){
        this.jeux=jeux;
        btnenv.setText("Modifier");
        txtNom.setText(jeux.getNom());
        txtDesc.setText(jeux.getDescriString());
        comboPlat.getSelectionModel().select(jeux.getType_plateforme());
    }
    
   
    
}
