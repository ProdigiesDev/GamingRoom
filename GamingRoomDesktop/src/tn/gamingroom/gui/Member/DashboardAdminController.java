/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Member;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import org.controlsfx.control.textfield.TextFields;
import tn.gamingroom.entities.Categorie;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.entities.UserSession;
import tn.gamingroom.outils.SendEmail;
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
    private TableColumn<Membre, Integer> colonne_pt;
    @FXML
    private TableColumn<Membre, Boolean> colonne_active;
    @FXML
    private TableColumn<Membre, Integer> colonne_ban;
    @FXML
    private TableColumn<Membre, Date> colonne_lastban;
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
    private JFXButton btn_statisticPage;
    @FXML
    private TextArea txtarea_coachdesc;
    @FXML
    private JFXButton btn_activate;
    @FXML
    private JFXButton btn_refuser;
    @FXML
    private Button button_imprimer;
    @FXML
    private FontAwesomeIcon iconreload;

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
//        activateCombo.getItems().addAll(
//                "Activate",
//                "Deactivate"
//                );
        /// 
        txtarea_coachdesc.setVisible(false);
        //text fill
        tfidmember.setStyle("-fx-text-FILL : white;");
        textcatid.setStyle("-fx-text-FILL : white;");
        textcatname.setStyle("-fx-text-FILL : white;");
        
         /// autocompile
        
        MembreServices ms = new MembreServices();
       
        TextFields.bindAutoCompletion(tfsearchmember,ms.GetEmail());

    }

    public void afficherCategorie() {
        CategorieServices cs = new CategorieServices();

        ObservableList<Categorie> listCategorie = FXCollections.observableArrayList(cs.DisplayCategorie());
        colonne_idcat.setCellValueFactory(new PropertyValueFactory<Categorie, Integer>("idcat"));
        colonne_namecat.setCellValueFactory(new PropertyValueFactory<Categorie, String>("nomcat"));
        tableCat.setItems(listCategorie);
    }

    public void afficherMembre() {
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
        //int idcat = Integer.parseInt(textcatid.getText());
        String nomcat = textcatname.getText();
        Categorie c = new Categorie(1, nomcat);

        CategorieServices cs = new CategorieServices();
        if(cs.CategorieExiste(nomcat)== false){
        cs.ajouterCategorie(c);
        JOptionPane.showMessageDialog(null, "Catégorie ajouté avec succés"); 
        afficherCategorie();
        }
        else{
           JOptionPane.showMessageDialog(null, "Nom catégorie existe déjà "); 
        }

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
                        btn.setOnAction(event -> {
                            Membre membre = getTableView().getItems().get(getIndex());
                            MembreServices membreServices = new MembreServices();
                            membre.setBan_duration(membre.getBan_duration() + 1);
                            membre.setActive(false);
                            membre.setLast_timeban(Timestamp.valueOf(LocalDateTime.now()));
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
//       Membre.Role role =  colonne_role.getCellData(index);
        int ban = colonne_ban.getCellData(index);
        boolean active = colonne_active.getCellData(index);

        if (index <= -1) {

            return;

        } else if (active == false && ban == 0) {
            tfidmember.setText(colonne_id.getCellData(index).toString());
            txtarea_coachdesc.setVisible(true);
            MembreServices ms = new MembreServices();
            int id = Integer.parseInt(tfidmember.getText());
            String texte = ms.getDescParId(id);
            txtarea_coachdesc.setText(texte);

        } else {
            txtarea_coachdesc.setVisible(false);
        }

//        
//        tfidmember.setText(colonne_id.getCellData(index).toString());
////        String active = colonne_active.getCellData(index).toString();
//        if (active.equals("false")){
//            activateCombo.setValue("Deactivate");
//        }
//         if (active.equals("true")){
//            activateCombo.setValue("Activate");
//        }
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
    private void signout(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Are you sure ?");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);

        if (alert.showAndWait().get() == okButton) {
            Parent dashboard;
            dashboard = FXMLLoader.load(getClass().getResource("LoginMember.fxml"));
//             Parent root = loader.load();
//            DashboardAdminController adc = loader.getController();

            Scene dashboardScene = new Scene(dashboard);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(dashboardScene);
            window.show();
            UserSession us = UserSession.getInstance();
            us.cleanUserSession();
        }
    }

//    @FXML
//    private void ActiverCompte(ActionEvent event) {
//        int idmembre = Integer.parseInt(tfidmember.getText());
//        String active =  activateCombo.getValue();
//        MembreServices ms = new MembreServices();
//        if(active.equals("Activate")){
//            Membre m = new Membre(idmembre,true);
//            ms.activerCompte(m);
//            JOptionPane.showMessageDialog(null, " Account successfully activated");
//            afficherMembre();
//        }
//        if(active.equals("Deactivate")){
//            Membre m1 = new Membre();
//            m1.setId(idmembre);
//            m1.setBan_duration(ms.getBandurParid(idmembre)+1);
//            m1.setActive(false);
//            m1.setLast_timeban(Timestamp.valueOf(LocalDateTime.now()));
//            ms.desactiverCompte(m1);
//            JOptionPane.showMessageDialog(null, " Account successfully deactivated");
//            afficherMembre();
//        }
//    }
    @FXML
    private void staticMembre(ActionEvent event) {
         try {
            FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("StatistiqueMembres.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Statistiques");
                stage.setScene(new Scene(root));
                stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void activerCompteCoach(ActionEvent event) {

        int idmembre = Integer.parseInt(tfidmember.getText());
        MembreServices ms = new MembreServices();
        String email = ms.getEmailParId(idmembre);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Are you sure ?");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);
        if (alert.showAndWait().get() == okButton) {
            System.out.println("bla bla");
            Membre m = new Membre(idmembre, true);
            ms.activerCompte(m);
            JOptionPane.showMessageDialog(null, " Account successfully activated");
            afficherMembre();
            String texte = "Hello," + "\n" + " We are glad to let you know that we accepeted your request to be a coach and your account is acctivated now  " + "\n" + "\n" + "GamingRoom";

            try {
                boolean b = SendEmail.sendMail(email, "Account activation", texte);
                if (b == true) {
                    JOptionPane.showMessageDialog(null, "Email sent successfullu");
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR! ");
                }
            } catch (MessagingException ex) {
                Logger.getLogger(DashboardAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void RefuserCompte(ActionEvent event) {
        int idmembre = Integer.parseInt(tfidmember.getText());
        MembreServices ms = new MembreServices();
        String email = ms.getEmailParId(idmembre);
        Membre m = new Membre(idmembre);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Refuse Account");
        alert.setHeaderText("You're about to refuse and delete member!");
        alert.setContentText("Are you sure ?");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);
        if (alert.showAndWait().get() == okButton) {
            ms.sumprimerMembres(m);
            JOptionPane.showMessageDialog(null, " Account successfully deleted");
            afficherMembre();
            String texte = "Hello," + "\n" + " We are sorry to let you know that we didn't accepet your request to be a coach  " + "\n" + "\n" + "GamingRoom";

            try {
                boolean b = SendEmail.sendMail(email, "Account activation", texte);
                if (b == true) {
                    JOptionPane.showMessageDialog(null, "Email sent successfullu");
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR! ");
                }
            } catch (MessagingException ex) {
                Logger.getLogger(DashboardAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void imprimerTable(ActionEvent event) throws SQLException, DocumentException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamingroom", "gamingRoomUser", "!&_UkTz/Cw`*2#[u");
            PreparedStatement pt = con.prepareStatement("select * from membre where role != 'Admin'");
            ResultSet rs = pt.executeQuery();

            /* Step-2: Initialize PDF documents - logical objects */
            Document my_pdf_report = new Document();

            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("ListeMembre.pdf"));

            my_pdf_report.open();

            my_pdf_report.add(new Paragraph("Listes des Membres:"));
            my_pdf_report.add(new Paragraph(" "));
            my_pdf_report.addCreationDate();

            //we have seven columns in our table
            PdfPTable my_report_table = new PdfPTable(7);

            //create a cell object
            PdfPCell table_cell;

            table_cell = new PdfPCell(new Phrase("ID"));
            table_cell.setBackgroundColor(BaseColor.CYAN);
            my_report_table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase("Genre"));
            table_cell.setBackgroundColor(BaseColor.CYAN);
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Telephone"));
            table_cell.setBackgroundColor(BaseColor.CYAN);
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Email"));
            table_cell.setBackgroundColor(BaseColor.CYAN);
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Role"));
            table_cell.setBackgroundColor(BaseColor.CYAN);
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Point"));
            table_cell.setBackgroundColor(BaseColor.CYAN);
            my_report_table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase("Last_timeban"));
            table_cell.setBackgroundColor(BaseColor.CYAN);
            my_report_table.addCell(table_cell);

            while (rs.next()) {

                String idRdv = rs.getString("id");
                table_cell = new PdfPCell(new Phrase(idRdv));
                my_report_table.addCell(table_cell);

                String dn = rs.getString("genre");
                table_cell = new PdfPCell(new Phrase(dn));
                my_report_table.addCell(table_cell);
                String tel = rs.getString("numero_tel");
                table_cell = new PdfPCell(new Phrase(tel));
                my_report_table.addCell(table_cell);
                String email = rs.getString("email");
                table_cell = new PdfPCell(new Phrase(email));
                my_report_table.addCell(table_cell);
                String role = rs.getString("role");
                table_cell = new PdfPCell(new Phrase(role));
                my_report_table.addCell(table_cell);
                String point = rs.getString("point");
                table_cell = new PdfPCell(new Phrase(point));
                my_report_table.addCell(table_cell);

                String lban = rs.getString("last_timeban");
                table_cell = new PdfPCell(new Phrase(lban));
                my_report_table.addCell(table_cell);

            }
            /* Attach report table to PDF */

            my_pdf_report.add(my_report_table);

            System.out.println(my_pdf_report);
            my_pdf_report.close();
            JOptionPane.showMessageDialog(null, "imprimer avec succes");

            /* Close all DB related objects */
            rs.close();
            pt.close();
            con.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    private void ReloadTable(MouseEvent event) {
         
             afficherMembre();
         
    }

}
