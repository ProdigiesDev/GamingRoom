/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
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
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Categorie;
import tn.gamingroom.entities.Cours;
import tn.gamingroom.entities.Courslm;
import tn.gamingroom.services.CategorieServices;
import tn.gamingroom.services.ServiceCours;

/**
 * FXML Controller class
 *
 * @author eyatr
 */
public class CoursDetailsController implements Initializable {

    @FXML
    private TableColumn<Cours, Integer> cid;
    @FXML
    private TableColumn<Cours, String> cnom;
    @FXML
    private TableColumn<Cours, String> cdes;
    @FXML
    private TableColumn<Cours, Integer> cnb;
    @FXML
    private TableColumn<Cours, Integer> cmem;
    @FXML
    private TableColumn<Cours, Date> cdate;
    @FXML
    private TableColumn<Cours, String> cmoc;
    @FXML
    private TableColumn<Cours, Integer> ccat;
    @FXML
    private TableView<Cours> tableCours;
    @FXML
    private Button btnaj;
    @FXML
    private Label titre;
    @FXML
    private Button btnup;
    @FXML
    private Button btns;
    @FXML
    private JFXTextField ides;
    @FXML
    private JFXTextField icl;
    @FXML
    private JFXDatePicker idate;
    @FXML
    private JFXTextField inb;
    private JFXTextField icat;
    @FXML
    private JFXTextField inom;
    @FXML
    private Label iid;
    @FXML
    private JFXTextField id;
    @FXML
    private Button btnclean;
    @FXML
    private JFXTextField imem;
    @FXML
    private TextField search;
    @FXML
    private Button btntri;
    @FXML
    private ComboBox<Categorie> combocat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ServiceCours s = new ServiceCours();

        ObservableList<Cours> listCours = FXCollections.observableArrayList(s.displayCours());
        id.setStyle("-fx-text-fill: white; ");
        inom.setStyle("-fx-text-fill: white; ");
        icl.setStyle("-fx-text-fill: white; ");
        ides.setStyle("-fx-text-fill: white; ");
        inb.setStyle("-fx-text-fill: white; ");
        imem.setStyle("-fx-text-fill: white; ");
        idate.setStyle("-fx-text-fill: white; ");
        cnom.setCellValueFactory(new PropertyValueFactory<Cours, String>("nomCours"));
        cid.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("Id"));
        cdes.setCellValueFactory(new PropertyValueFactory<Cours, String>("description"));
        cdate.setCellValueFactory(new PropertyValueFactory<Cours, Date>("date_creation"));
        cmoc.setCellValueFactory(new PropertyValueFactory<Cours, String>("tags"));
        cmem.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("membre_id"));
        cnb.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("nb_participants"));
        ccat.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("categorie_id"));
       
        tableCours.setItems(listCours);
        
        //catégorie combo
         CategorieServices cs = new CategorieServices();
        ObservableList l = FXCollections.observableArrayList(cs.DisplayCategorie());
        combocat.setItems(l);

        combocat.setConverter(new StringConverter<Categorie>() {
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
    private void ajouterC(ActionEvent event) {

        //try {
        Cours c = new Cours();
       ServiceCours s = new ServiceCours();
       Categorie categorie =  combocat.getValue();
         

        c.setNomCours(inom.getText());
        c.setDescription(ides.getText());
        c.setNb_participants(Integer.parseInt(inb.getText()));
        c.setDate_creation(Date.valueOf(idate.getValue()));
        c.setTags(icl.getText());
        c.setCategorie_id(categorie.getIdcat());
        c.setMembre_id(Integer.parseInt(imem.getText()));

        int nb = s.ajouterCours(c);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation d'ajout !");
        alert.setContentText("Voulez-Vous Vraiment Ajouter");

        Optional<ButtonType> btn = alert.showAndWait();
        JOptionPane.showMessageDialog(null, "Cours ajouté");
        clean();
        
        if (nb == 0) {
            JOptionPane.showMessageDialog(null, "Erreur cours non ajouteé");
        } else {
            
            ConsulterCours();
            
            
        }

//            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutCours.fxml"));
//            //System.out.println(getClass().getResource("AjoutCours.fxml"));
//            Parent root = loader.load();
//            AjoutCoursController aj = loader.getController();
//            titre.getScene().setRoot(root);
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
    }

    @FXML
    private void modifierC(ActionEvent event) {

        String Value1 = ides.getText();
        String Value2 = inom.getText();
        int Value3 = Integer.parseInt(id.getText());
        String Value4 = icl.getText();
        int Value5 = Integer.parseInt(inb.getText());
        Categorie cat = combocat.getValue();
        Date Value7 = Date.valueOf(idate.getValue());

        Cours c2 = new Cours(Value3, Value2, Value1, Value5, Value4, cat.getIdcat());

        ServiceCours s = new ServiceCours();
        int x = s.updateCours(c2);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de Modifiation !");
        alert.setContentText("Voulez-Vous Vraiment Modifer");

        Optional<ButtonType> btn = alert.showAndWait();
        if (x > 0) {
            JOptionPane.showMessageDialog(null, "Cours modifié");
            ConsulterCours();

        } else {
            System.out.println("cours non modifié");
        }

    }

    @FXML
    private void supprimer(ActionEvent event) {
        ServiceCours s = new ServiceCours();
        Cours c = new Cours();

        c = this.tableCours.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de Suppression !");
        alert.setContentText("Voulez-Vous Vraiment Supprimer");

        Optional<ButtonType> btn = alert.showAndWait();
        if (btn.get() == ButtonType.OK) {
            s.supprimerCours(c);
            this.ConsulterCours();
            System.out.println("suppression avec succées");
        } else {
            alert.close();
        }
    }

    private void ConsulterCours() {
        ServiceCours s = new ServiceCours();
        List<Cours> lc = s.displayCours();

        ObservableList<Cours> list
                = FXCollections.observableArrayList(lc);

        ObservableList<Cours> listCours = FXCollections.observableArrayList(s.displayCours());
        cnom.setCellValueFactory(new PropertyValueFactory<Cours, String>("nomCours"));
        cid.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("Id"));
        cdes.setCellValueFactory(new PropertyValueFactory<Cours, String>("description"));
        cdate.setCellValueFactory(new PropertyValueFactory<Cours, Date>("date_creation"));
        cmoc.setCellValueFactory(new PropertyValueFactory<Cours, String>("tags"));
        cmem.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("membre_id"));
        cnb.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("nb_participants"));
        ccat.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("categorie_id"));
        tableCours.setItems(list);

    }

    @FXML
    private void getSelected(MouseEvent event) {
        Cours c=tableCours.getSelectionModel().getSelectedItem();
        CategorieServices categorieServices=new CategorieServices();
        if (c==null) {
            return;
        }
        id.setText(String.valueOf(c.getId()));
        imem.setText(String.valueOf(c.getMembre_id()));
        inom.setText(c.getNomCours());
        combocat.setValue(categorieServices.getCategorieById(c.getCategorie_id()));
        Date d=c.getDate_creation(); 
        System.out.println(d);
        idate.setValue(LocalDate.of(d.getYear(), d.getMonth(), d.getDay()));
        icl.setText(c.getTags());
        ides.setText(c.getDescription());
        inb.setText(String.valueOf(c.getNb_participants()));
        //inb.setText(img.getCellData(index).toString());
    }

    @FXML
    private void clean() {
        inom.setText(null);
        id.setText(null);
        combocat.getSelectionModel().select(null);
        idate.setValue(null);
        imem.setText(null);
        inb.setText(null);
        ides.setText(null);
        icl.setText(null);
    }

    @FXML
    private void searchCours(KeyEvent event) {
        ServiceCours s = new ServiceCours();
        ObservableList<Cours> list = FXCollections.observableArrayList(s.searchCours(search.getText()));

        cnom.setCellValueFactory(new PropertyValueFactory<Cours, String>("nomCours"));
        cid.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("Id"));
        cdes.setCellValueFactory(new PropertyValueFactory<Cours, String>("description"));
        cdate.setCellValueFactory(new PropertyValueFactory<Cours, Date>("date_creation"));
        cmoc.setCellValueFactory(new PropertyValueFactory<Cours, String>("tags"));
        cmem.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("membre_id"));
        cnb.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("nb_participants"));
        ccat.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("categorie_id"));
        tableCours.setItems(list);
    }

    @FXML
    private void trierC(ActionEvent event) {
        ServiceCours s = new ServiceCours();
        ObservableList<Cours> list = FXCollections.observableArrayList(s.trierCoursID());

        cnom.setCellValueFactory(new PropertyValueFactory<Cours, String>("nomCours"));
        cid.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("Id"));
        cdes.setCellValueFactory(new PropertyValueFactory<Cours, String>("description"));
        cdate.setCellValueFactory(new PropertyValueFactory<Cours, Date>("date_creation"));
        cmoc.setCellValueFactory(new PropertyValueFactory<Cours, String>("tags"));
        cmem.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("membre_id"));
        cnb.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("nb_participants"));
        ccat.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("categorie_id"));
        tableCours.setItems(list);

    }

    private void addButtonToTable() {
//        TableColumn<Cours, Void> btn = new TableColumn("");
//
//        Callback<TableColumn<Cours, Void>, TableCell<Cours, Void>> cellFactory = new Callback<TableColumn<Cours, Void>, TableCell<Cours, Void>>() {
//            @Override
//            public TableCell<Cours, Void> call(final TableColumn<Cours, Void> param) {
//                final TableCell<Cours, Void> cell = new TableCell<Cours, Void>() {
//
//                    private final Button btn = new Button("Supprimer");
//
//                    {
//                        btn.setOnAction(event -> {
//                            Cours c = getTableView().getItems().get(getIndex());
//                            ServiceCours coursService = new ServiceCours();
//                            coursService.supprimerCours(c);
//                            ConsulterCours();
//                        });
//                    }
//
//                    @Override
//                    public void updateItem(Void item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (empty) {
//                            setGraphic(null);
//                        } else {
//                            setGraphic(btn);
//                        }
//                    }
//                };
//                return cell;
//            }
//        };
//
//        btn.setCellFactory(cellFactory);
//
//        tableCours.getColumns().add(btn);
   }

    }
