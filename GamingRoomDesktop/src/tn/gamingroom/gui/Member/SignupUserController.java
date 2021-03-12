/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Member;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Categorie;
import tn.gamingroom.entities.CategorieMembre;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.outils.MyConnection;
import tn.gamingroom.services.CategorieMembreServices;
import tn.gamingroom.services.CategorieServices;
import tn.gamingroom.services.MembreServices;

/**
 * FXML Controller class
 *
 * @author Sonia
 */
public class SignupUserController implements Initializable {

    @FXML
    private JFXButton btnSignin;
    @FXML
    private JFXComboBox <Categorie>  categorieCombo;
    @FXML
    private JFXComboBox <String>  GenderCombo;
    @FXML
    private JFXTextField fnameMember;
    @FXML
    private JFXTextField lnameMember;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXDatePicker bday;
    @FXML
    private JFXTextField emailMember;
    @FXML
    private JFXPasswordField passwordMember;
    @FXML
    private JFXPasswordField retypePassword;
    @FXML
    private JFXCheckBox ckCoach;
    @FXML
    private TextArea textAreaDesc;
    int i=0;
        Membre m= new Membre();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       textAreaDesc.setVisible(false);
       
       //Combo Genre
        GenderCombo.getItems().addAll(
                m.getGenre().Homme.toString(),
                m.getGenre().Femme.toString()
                );

       
            // Combo Categorie
            
            
        CategorieServices cs = new CategorieServices();
        ObservableList l = FXCollections.observableArrayList(cs.DisplayCategorie());
        categorieCombo.setItems(l);

        categorieCombo.setConverter(new StringConverter<Categorie>() {
            @Override
            public String toString(Categorie object) {
                return object.getNomcat();
            }

            @Override
            public Categorie fromString(String string) {
                return null;
            }
        });

    }    

    @FXML
    private void SignInIterface(ActionEvent event) throws IOException {
          
                FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginMember.fxml"));
                Parent root = loader.load();
                LoginMemberController lmc = loader.getController();
                btnSignin.getScene().setRoot(root);
                
        
    }

    @FXML
    private void btnCreateAccountMember(ActionEvent event) {
        String prenom=fnameMember.getText();
        String nom=lnameMember.getText();
        LocalDate naiss=bday.getValue();
        String email = emailMember.getText();
        String tel=phone.getText();
        String genre = (String) GenderCombo.getValue().toString();
        Categorie categorie =  categorieCombo.getValue();
        String mdp=passwordMember.getText();
        String rmdp = retypePassword.getText();
        String description = textAreaDesc.getText();
         if(mdp.equals(rmdp)){
        if(!ckCoach.isSelected()){
           
            Membre m = new Membre(1,nom,prenom,java.sql.Date.valueOf(naiss),genre.equals("Homme")? Membre.Genre.Homme:Membre.Genre.Femme ,tel,email,mdp,Membre.Role.Membre,true);
            MembreServices ms = new MembreServices();
            int i= ms.ajouterMembre(m);
        
              if(i>0){
                int id = ms.lastId();
                  System.out.println(id);
                CategorieMembreServices cms = new CategorieMembreServices();
                cms.AffecterCategorieMembre(new CategorieMembre(id,categorie.getIdcat()));
            }
            
            
            JOptionPane.showMessageDialog(null, "Votre compte est crée avec succés");
            
            }
        else{
             Membre m1 = new Membre(1,nom,prenom,java.sql.Date.valueOf(naiss),genre.equals("Homme")? Membre.Genre.Homme:Membre.Genre.Femme ,tel,email,mdp,Membre.Role.Coach,description,false);
            MembreServices ms = new MembreServices();
            ms.ajouterCoach(m1);
             
            if(i>0){
                int id = ms.lastId();
                  System.out.println(id);
                CategorieMembreServices cms = new CategorieMembreServices();
                cms.AffecterCategorieMembre(new CategorieMembre(id,categorie.getIdcat()));
            }
            
            
            
            JOptionPane.showMessageDialog(null, "Veuillez attendre l'acceptation de l'admin");
            
        }
           
            
        }
         else{
             JOptionPane.showMessageDialog(null, "Verifier password");

            }
         
        
          
       
    }

    @FXML
    private void checkCoach(ActionEvent event) {
          i++;
         if(i%2!=0){
        textAreaDesc.setVisible(true);
        
       
         }
         else
         {
        textAreaDesc.setVisible(false);
        
        
         }
    }
    
}
