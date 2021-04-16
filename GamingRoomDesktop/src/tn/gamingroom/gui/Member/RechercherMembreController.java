/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Member;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.services.MembreServices;

/**
 * FXML Controller class
 *
 * @author Sonia
 */
public class RechercherMembreController implements Initializable {

    @FXML
    private BorderPane pane_table;
    @FXML
    private TableView<Membre> table_membre;
    @FXML
    private TableColumn<Membre, String> colonne_nom;
    @FXML
    private TableColumn<Membre, String> colonne_prenom;
    @FXML
    private TableColumn<Membre, Date> colonne_naissance;
    @FXML
    private TableColumn<Membre, String> colonne_sexe;
    @FXML
    private TableColumn<Membre, String> colonne_tel;
    @FXML
    private TableColumn<Membre, String> colonne_email;
    @FXML
    private TableColumn<Membre, String> colonne_role;
    @FXML
    private TextField textsearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /// autocompile
        MembreServices ms = new MembreServices();

        TextFields.bindAutoCompletion(textsearch, ms.RechercherMembresProfil());

    }

    @FXML
    private void RechercherMembre(KeyEvent event) {
        MembreServices ms = new MembreServices();
        ObservableList<Membre> list = FXCollections.observableArrayList(ms.RechercherMembres(textsearch.getText()));

        colonne_prenom.setCellValueFactory(new PropertyValueFactory<Membre, String>("prenom"));
        colonne_nom.setCellValueFactory(new PropertyValueFactory<Membre, String>("nom"));
        colonne_naissance.setCellValueFactory(new PropertyValueFactory<Membre, Date>("date_naissance"));
        colonne_sexe.setCellValueFactory(new PropertyValueFactory<Membre, String>("genre"));
        colonne_tel.setCellValueFactory(new PropertyValueFactory<Membre, String>("tel"));
        colonne_email.setCellValueFactory(new PropertyValueFactory<Membre, String>("email"));
        colonne_role.setCellValueFactory(new PropertyValueFactory<Membre, String>("role"));

        table_membre.setItems(list);
        pane_table.setVisible(true);

    }

    @FXML
    private void getSelectedMember(MouseEvent event) throws IOException {
        int index = table_membre.getSelectionModel().getSelectedIndex();
        String email = colonne_email.getCellData(index);

        MembreServices ms = new MembreServices();
        Membre m = ms.getMembreByEmail(email);
        System.out.println(m);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProfilC.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        ProfilCController pc = fxmlLoader.getController();
        stage.setTitle("Profil");
        ProfilCController pcc = fxmlLoader.getController();
        pcc.setEmail(m.getEmail());
        pcc.setNomEtPrenom(m.getPrenom(), m.getNom());
        pcc.setRole(m.getRole());
        pcc.setSexe(m.getGenre());
        pcc.setTel(m.getTel());
        pcc.setEmail(m.getEmail());
        pcc.set_image(m.getImage());

        stage.setScene(new Scene(root));
        stage.show();
    }

}
