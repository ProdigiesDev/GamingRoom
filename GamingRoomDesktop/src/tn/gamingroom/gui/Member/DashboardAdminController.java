/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Member;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Categorie;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.services.CategorieServices;
import tn.gamingroom.services.MembreServices;

/**
 * FXML Controller class
 *
 * @author Sonia
 */
public class DashboardAdminController implements Initializable {

    @FXML
    private TableView<Membre> table_memb;
    @FXML
    private TableColumn<Membre, Integer> colonne_id;
    @FXML
    private TableColumn<Membre, String> colonne_name;
    @FXML
    private TableColumn<Membre, Date> colonne_bday;
    @FXML
    private TableColumn<Membre, String> colonne_gender;
    @FXML
    private TableColumn<Membre, String> colonne_tel;
    @FXML
    private TableColumn<Membre, String> colonne_email;
    @FXML
    private TableColumn<Membre, String> colonne_role;
    @FXML
    private TableColumn<Membre,Integer > colonne_pt;
    @FXML
    private TableColumn<Membre, Boolean> colonne_active;
    @FXML
    private TableColumn<Membre, Integer> colonne_ban;
    @FXML
    private TableColumn<Membre,Date > colonne_lastban;
    @FXML
    private JFXTextField textcatid;
    @FXML
    private JFXTextField textcatname;
    @FXML
    private JFXButton btnaddCat;
    @FXML
    private JFXButton btnupdateCat;
    @FXML
    private TableView<Categorie> tableCat;
    @FXML
    private TextField textsearchcat;
    @FXML
    private JFXButton btndeleteCat;
    @FXML
    private JFXButton btnsort;
    @FXML
    private TableColumn<Categorie, Integer> colonne_idcat;
    @FXML
    private TableColumn<Categorie, String> colonne_namecat;
    @FXML
    private JFXButton btn_clean;
    @FXML
    private TextField tfsearchmember;
    @FXML
    private JFXTextField tfidmember;
    @FXML
    private JFXButton btnsortm;
    @FXML
    private JFXButton btnsignout;
    @FXML
    private JFXComboBox<String> activateCombo;
    @FXML
    private JFXButton btnUpdateActivationAccount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //Membre
        
        addButtonToTable();
        afficherMembre();
        
        //Categorie
        afficherCategorie();
        //Combo Genre
        activateCombo.getItems().addAll(
                "Activate",
                "Deactivate"
                );

        
        
    } 
    
    public void afficherCategorie(){
        CategorieServices cs = new CategorieServices();
        
        ObservableList<Categorie> listCategorie = FXCollections.observableArrayList(cs.DisplayCategorie());
        colonne_idcat.setCellValueFactory(new PropertyValueFactory<Categorie, Integer>("idcat"));
        colonne_namecat.setCellValueFactory(new PropertyValueFactory<Categorie, String>("nomcat"));
        tableCat.setItems(listCategorie);
    }
    public void afficherMembre(){
        MembreServices s = new MembreServices();

        ObservableList<Membre> listMembre = FXCollections.observableArrayList(s.DisplayMembres());
        colonne_id.setCellValueFactory(new PropertyValueFactory<Membre, Integer>("id"));
        colonne_name.setCellValueFactory(new PropertyValueFactory<Membre, String>("prenom"));
        colonne_bday.setCellValueFactory(new PropertyValueFactory<Membre, Date>("date_naissance"));
        colonne_gender.setCellValueFactory(new PropertyValueFactory<Membre, String>("genre"));
        colonne_tel.setCellValueFactory(new PropertyValueFactory<Membre, String>("tel"));
        colonne_email.setCellValueFactory(new PropertyValueFactory<Membre, String>("email"));
        colonne_role.setCellValueFactory(new PropertyValueFactory<Membre, String>("role"));
        colonne_pt.setCellValueFactory(new PropertyValueFactory<Membre, Integer>("point"));
        colonne_active.setCellValueFactory(new PropertyValueFactory<Membre, Boolean>("active"));
        colonne_ban.setCellValueFactory(new PropertyValueFactory<Membre, Integer>("ban_duration"));
        colonne_lastban.setCellValueFactory(new PropertyValueFactory<Membre, Date>("last_timeban"));
       
        table_memb.setItems(listMembre);
    }

    

    @FXML
    private void ajouterCategorie(ActionEvent event) {
        int idcat = Integer.parseInt(textcatid.getText());
        String nomcat=textcatname.getText();
         Categorie c = new Categorie(idcat,nomcat);
         
         CategorieServices cs = new CategorieServices();
         cs.ajouterCategorie(c);
         afficherCategorie();
        
    }

    @FXML
    private void modifierCategorie(ActionEvent event) {
        int idcat = Integer.parseInt(textcatid.getText());
        String nomcat = textcatname.getText();
        
        Categorie c = new Categorie(idcat, nomcat);

        CategorieServices cs = new CategorieServices();
        int x = cs.modifierCategorie(c);
        if (x > 0) {
            JOptionPane.showMessageDialog(null, "Catégorie modifiée");
            afficherCategorie();

        } else {
            JOptionPane.showMessageDialog(null, "Catégorie non modifiée");
        }
    }

    @FXML
    private void supprimerCategorie(ActionEvent event) {
        
        CategorieServices cs = new CategorieServices();
        
        Categorie c = new Categorie();

        c = this.tableCat.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de Suppression !");
        alert.setContentText("Voulez-Vous Vraiment Supprimer");

        Optional<ButtonType> btn = alert.showAndWait();
        if (btn.get() == ButtonType.OK) {
            cs.supprimerCategorie(c);
            this.afficherCategorie();
            System.out.println("suppression avec succées");
        } else {
            alert.close();
        }

        Clean(event);
    }

    @FXML
    private void trierCategorie_parId(ActionEvent event) {
        CategorieServices cs = new CategorieServices();
        ObservableList<Categorie> list = FXCollections.observableArrayList(cs.TrierParIdCat());

        colonne_idcat.setCellValueFactory(new PropertyValueFactory<Categorie, Integer>("idcat"));
        colonne_namecat.setCellValueFactory(new PropertyValueFactory<Categorie, String>("nomcat"));
 
        tableCat.setItems(list);
    }

    @FXML
    private void getSelectedCat(MouseEvent event) {
         int index = tableCat.getSelectionModel().getSelectedIndex();

        if (index <= -1) {

            return;

        }
        textcatid.setText(colonne_idcat.getCellData(index).toString());
        textcatname.setText(colonne_namecat.getCellData(index).toString());

    }

    @FXML
    private void Clean(ActionEvent event) {
        textcatid.setText(null);
        textcatname.setText(null);
    }

    @FXML
    private void RechercherCategorie(KeyEvent event) {
        CategorieServices cs = new CategorieServices();
        ObservableList<Categorie> list = FXCollections.observableArrayList(cs.RechercherCategorie(textsearchcat.getText()));

        colonne_idcat.setCellValueFactory(new PropertyValueFactory<Categorie, Integer>("idcat"));
        colonne_namecat.setCellValueFactory(new PropertyValueFactory<Categorie, String>("nomcat"));
        tableCat.setItems(list);
    }

    @FXML
    private void rechercherMember(KeyEvent event) {
        MembreServices ms = new MembreServices();
        ObservableList<Membre> list = FXCollections.observableArrayList(ms.RechercherMembres(tfsearchmember.getText()));

        colonne_id.setCellValueFactory(new PropertyValueFactory<Membre, Integer>("id"));
        colonne_name.setCellValueFactory(new PropertyValueFactory<Membre, String>("prenom"));
        colonne_bday.setCellValueFactory(new PropertyValueFactory<Membre, Date>("date_naissance"));
        colonne_gender.setCellValueFactory(new PropertyValueFactory<Membre, String>("genre"));
        colonne_tel.setCellValueFactory(new PropertyValueFactory<Membre, String>("tel"));
        colonne_email.setCellValueFactory(new PropertyValueFactory<Membre, String>("email"));
        colonne_role.setCellValueFactory(new PropertyValueFactory<Membre, String>("role"));
        colonne_pt.setCellValueFactory(new PropertyValueFactory<Membre, Integer>("point"));
        colonne_active.setCellValueFactory(new PropertyValueFactory<Membre, Boolean>("active"));
        colonne_ban.setCellValueFactory(new PropertyValueFactory<Membre, Integer>("ban_duration"));
        colonne_lastban.setCellValueFactory(new PropertyValueFactory<Membre, Date>("last_timeban"));
        table_memb.setItems(list);
    }

    @FXML
    private void trierMembre(ActionEvent event) {
        
        MembreServices ms = new MembreServices();
        ObservableList<Membre> list = FXCollections.observableArrayList(ms.TrierParId());

        colonne_id.setCellValueFactory(new PropertyValueFactory<Membre, Integer>("id"));
        colonne_name.setCellValueFactory(new PropertyValueFactory<Membre, String>("prenom"));
        colonne_bday.setCellValueFactory(new PropertyValueFactory<Membre, Date>("date_naissance"));
        colonne_gender.setCellValueFactory(new PropertyValueFactory<Membre, String>("genre"));
        colonne_tel.setCellValueFactory(new PropertyValueFactory<Membre, String>("tel"));
        colonne_email.setCellValueFactory(new PropertyValueFactory<Membre, String>("email"));
        colonne_role.setCellValueFactory(new PropertyValueFactory<Membre, String>("role"));
        colonne_pt.setCellValueFactory(new PropertyValueFactory<Membre, Integer>("point"));
        colonne_active.setCellValueFactory(new PropertyValueFactory<Membre, Boolean>("active"));
        colonne_ban.setCellValueFactory(new PropertyValueFactory<Membre, Integer>("ban_duration"));
        colonne_lastban.setCellValueFactory(new PropertyValueFactory<Membre, Date>("last_timeban"));
        table_memb.setItems(list);
    }
    
    private void addButtonToTable() {
        TableColumn<Membre, Void> colBtn = new TableColumn("");

        Callback<TableColumn<Membre, Void>, TableCell<Membre, Void>> cellFactory = new Callback<TableColumn<Membre, Void>, TableCell<Membre, Void>>() {
            @Override
            public TableCell<Membre, Void> call(final TableColumn<Membre, Void> param) {
                final TableCell<Membre, Void> cell = new TableCell<Membre, Void>() {

                    private final JFXButton btn = new JFXButton("ban");

                    {
                        btn.setStyle("-fx-background-color: #9F7EF7");
                        btn.setTextFill(Paint.valueOf("#f8f7f7"));
                        btn.setOnAction( event -> {
                            Membre membre = getTableView().getItems().get(getIndex());
                            MembreServices membreServices=new MembreServices();
                            membre.setBan_duration(membre.getBan_duration()+1);
                            membre.setActive(false);
                            membre.setLast_timeban(Date.valueOf(LocalDate.now()));
                            membreServices.modifierMembreParAdmin(membre);
                           getTableView().getItems().set(getIndex(), membre);
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

        table_memb.getColumns().add(colBtn);

    }

    @FXML
    private void getSelectedMember(MouseEvent event) {
         int index = table_memb.getSelectionModel().getSelectedIndex();

        if (index <= -1) {

            return;

        }
        
        tfidmember.setText(colonne_id.getCellData(index).toString());
        String active = colonne_active.getCellData(index).toString();
        if (active.equals("false")){
            activateCombo.setValue("Deactivate");
        }
         if (active.equals("true")){
            activateCombo.setValue("Activate");
        }
        //activateCombo.setValue(colonne_active.getCellData(index).toString());
       // tfpoint.setText(colonne_pt.getCellData(index).toString());
    }

//    @FXML
//    private void AjouterdesPoint(MouseEvent event) {
//        try{
//        int  id = Integer.parseInt(tfidmember.getText());
//        int  point = Integer.parseInt(tfpoint.getText());
//       
//       tfpoint.setText((String.valueOf(point+1)));
//        }catch(Exception ex){
//            JOptionPane.showMessageDialog(null, " choisir un membre");
//            
//        }
//          
//        
//    }

//    @FXML
//    private void modiferPointMembre(ActionEvent event) {
//        
//         int idmembre = Integer.parseInt(tfidmember.getText());
//        int pointmembre = Integer.parseInt(tfpoint.getText());
//        
//        Membre m = new Membre(idmembre, pointmembre);
//
//        MembreServices ms = new  MembreServices();
//        int x = ms.modifierMembreParAdmin(m);
//        if (x > 0) {
//            JOptionPane.showMessageDialog(null, " modification des points avec succès");
//            afficherMembre();
//
//        } else {
//            JOptionPane.showMessageDialog(null, "Erreur de modification");
//        }
//        afficherMembre();
//    }

    @FXML
    private void signout(ActionEvent event) throws IOException {
        Parent dashboard ;
                dashboard = FXMLLoader.load(getClass().getResource("LoginMember.fxml"));
//             Parent root = loader.load();
//            DashboardAdminController adc = loader.getController();
            
                Scene dashboardScene = new Scene(dashboard);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(dashboardScene);
                window.show();
    }

    @FXML
    private void ActiverCompte(ActionEvent event) {
        int idmembre = Integer.parseInt(tfidmember.getText());
        String active =  activateCombo.getValue();
        MembreServices ms = new MembreServices();
        if(active.equals("Activate")){
            Membre m = new Membre(idmembre,true);
            ms.activerCompte(m);
            JOptionPane.showMessageDialog(null, " Account successfully activated");
            afficherMembre();
        }
        if(active.equals("Deactivate")){
            Membre m1 = new Membre();
            m1.setId(idmembre);
            m1.setBan_duration(ms.getBandurParid(idmembre)+1);
            m1.setActive(false);
            m1.setLast_timeban(Date.valueOf(LocalDate.now()));
            ms.desactiverCompte(m1);
            JOptionPane.showMessageDialog(null, " Account successfully deactivated");
            afficherMembre();
        }
    }

    
}
