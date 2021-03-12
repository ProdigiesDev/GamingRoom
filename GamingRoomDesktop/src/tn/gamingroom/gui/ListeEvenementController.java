/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Evenement;
import tn.gamingroom.entities.EvenementImage;
import tn.gamingroom.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ListeEvenementController implements Initializable {

    @FXML
    private TableColumn<EvenementImage, ImageView> imL;
    @FXML
    private TableColumn<EvenementImage, Date> dateDL;
    @FXML
    private TableColumn<EvenementImage, Date> dateFL;
    @FXML
    private TableColumn<EvenementImage, String> tL;
    @FXML
    private TableColumn<EvenementImage, Integer> catL;
    @FXML
    private TableColumn<EvenementImage, Integer> nbML;
    @FXML
    private TableColumn<EvenementImage, String> desL;
    @FXML
    private TableColumn<EvenementImage, String> lieuL;
    @FXML
    private TableColumn<EvenementImage, String> LienYL;
    @FXML
    private TableView<EvenementImage> listeEvents;
    @FXML
    private Label n;
    @FXML
    private TableColumn<EvenementImage, Integer> idE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        idE.setCellValueFactory(new PropertyValueFactory<EvenementImage, Integer>("idevent"));
        imL.setCellValueFactory(new PropertyValueFactory<EvenementImage, ImageView>("image"));
        dateDL.setCellValueFactory(new PropertyValueFactory<EvenementImage, Date>("dateDeb"));
        dateFL.setCellValueFactory(new PropertyValueFactory<EvenementImage, Date>("dateFin"));
        tL.setCellValueFactory(new PropertyValueFactory<EvenementImage, String>("nomEvent"));
        catL.setCellValueFactory(new PropertyValueFactory<EvenementImage, Integer>("categorie_id"));
        nbML.setCellValueFactory(new PropertyValueFactory<EvenementImage, Integer>("nbreMax_participant"));
        desL.setCellValueFactory(new PropertyValueFactory<EvenementImage, String>("description"));
        lieuL.setCellValueFactory(new PropertyValueFactory<EvenementImage, String>("lieu"));
        LienYL.setCellValueFactory(new PropertyValueFactory<EvenementImage, String>("lienYoutube"));

        initTable();
        addButtonToTable();

    }

    private void addButtonToTable() {
        TableColumn<EvenementImage, Void> colBtn = new TableColumn("");

        Callback<TableColumn<EvenementImage, Void>, TableCell<EvenementImage, Void>> cellFactory = new Callback<TableColumn<EvenementImage, Void>, TableCell<EvenementImage, Void>>() {
            @Override
            public TableCell<EvenementImage, Void> call(final TableColumn<EvenementImage, Void> param) {
                final TableCell<EvenementImage, Void> cell = new TableCell<EvenementImage, Void>() {

                    private final Button btn = new Button("Supprimer");
                    private final Button btnU = new Button("Modifier");

                    {
                        btn.setTextFill(Paint.valueOf("#f8f7f7"));
                        btn.setStyle("-fx-background-color: #6f1075");
                        btn.setOnAction(event -> {
                            EvenementImage data = getTableView().getItems().get(getIndex());
                            JFrame f = new JFrame();

                            int a = JOptionPane.showConfirmDialog(f, "Êtes-vous sûr?");
                            if (a == JOptionPane.YES_OPTION) {

                            }
                            EvenementService es = new EvenementService();
                            es.suppressionEvenement(data.getIdevent());
                            initTable();
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

        listeEvents.getColumns().add(colBtn);

    }

    private void initTable() {
        EvenementService es = new EvenementService();
        List<EvenementImage> lIm = new ArrayList<EvenementImage>();
        es.listerEvenement().forEach(e -> {
            ImageView i = new ImageView(new Image(e.getImage()));
            i.setFitHeight(100);
            i.setFitWidth(100);
            lIm.add(new EvenementImage(e.getIdevent(), e.getNomEvent(), e.getDateDeb(), e.getDateFin(), i, e.getCategorie_id(), e.getNbreMax_participant(), e.getDescription(), e.getLieu(), e.getLienYoutube()));
        });

        ObservableList<EvenementImage> listEventsIm = FXCollections.observableArrayList(lIm);

        listeEvents.setItems(listEventsIm);
    }

    @FXML
    private void getSelected(MouseEvent event) {

        try {
            int index = listeEvents.getSelectionModel().getSelectedIndex();

            if (index <= -1) {

                return;

            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierEvenement.fxml"));
            Parent root;
            root = loader.load();
            System.out.println("root" + root);
            ModifierEvenementController pct = loader.getController();
            // System.out.println("idE"+idE+" "+idE.getCellData(index));
            pct.setId(idE.getCellData(index).toString());
            pct.setNomevent(tL.getCellData(index));
            pct.setDatedeb(dateDL.getCellData(index));
            pct.setDatefin(dateFL.getCellData(index));
            pct.setCategorie(catL.getCellData(index));
            pct.setImage(imL.getCellData(index).toString());
            pct.setNbremax_participant(nbML.getCellData(index).toString());
            pct.setDescription(desL.getCellData(index));
            pct.setLienyoutube(LienYL.getCellData(index));
            n.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ListeEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToAjouterEvenement(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoutEvenement.fxml"));
            Parent root = loader.load();
            
            n.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ListeEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
