/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Jeux;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Jeux;
import tn.gamingroom.services.JeuxService;

/**
 * FXML Controller class
 *
 * @author Dah
 */
public class ListerJeuxController implements Initializable {

    @FXML
    private TableView<Jeux> listJeux;
    @FXML
    private TableColumn<Jeux, Integer> colId;
    @FXML
    private TableColumn<Jeux, String> colNom;
    @FXML
    private TableColumn<Jeux, String> colDesc;
    @FXML
    private TableColumn<Jeux, Jeux.Type> colPlat;

    private JeuxService jeuxService = new JeuxService();
    JFrame f;
    @FXML
    private JFXButton addGameBtn;
    @FXML
    private JFXButton btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colId.setCellValueFactory(new PropertyValueFactory<Jeux, Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<Jeux, String>("nom"));
        colDesc.setCellValueFactory(new PropertyValueFactory<Jeux, String>("descriString"));
        colPlat.setCellValueFactory(new PropertyValueFactory<Jeux, Jeux.Type>("type_plateforme"));
        initTable();
        addButtonToTable();
    }

    private void addButtonToTable() {
        TableColumn<Jeux, Void> colBtn = new TableColumn("");

        Callback<TableColumn<Jeux, Void>, TableCell<Jeux, Void>> cellFactory = new Callback<TableColumn<Jeux, Void>, TableCell<Jeux, Void>>() {
            @Override
            public TableCell<Jeux, Void> call(final TableColumn<Jeux, Void> param) {
                final TableCell<Jeux, Void> cell = new TableCell<Jeux, Void>() {

                    private final JFXButton btn = new JFXButton("Supprimer");

                    {
                        btn.setTextFill(Paint.valueOf("#f8f7f7"));
                        btn.setStyle("-fx-background-color: #6f1075");

                        btn.setOnAction(event -> {
                            f = new JFrame();

                            int a = JOptionPane.showConfirmDialog(f, "Êtes-vous sûr?");
                            if (a == JOptionPane.YES_OPTION) {

                                Jeux data = getTableView().getItems().get(getIndex());

                                jeuxService.supprimer(data.getId());
                                initTable();
                                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            }
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

        listJeux.getColumns().add(colBtn);

    }

    private void initTable() {

        ObservableList<Jeux> listRec = FXCollections.observableArrayList(jeuxService.getAll());

        listJeux.setItems(listRec);
    }

    @FXML
    private void editJeux(MouseEvent event) {
        f = new JFrame();

        Jeux jeux = listJeux.getSelectionModel().selectedItemProperty().get();
        int a = JOptionPane.showConfirmDialog(f, "voulez-vous modifier ce jeu " + jeux.getNom() + " ?");
        if (a == JOptionPane.YES_OPTION) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterJeux.fxml"));
                Parent root = loader.load();
                AjouterJeuxController ajouterJeuxController = loader.getController();
                ajouterJeuxController.editInterface(jeux);
                ajouterJeuxController.setJeux(jeux);
                
                Stage stage=new Stage();
                stage.setScene(new Scene(root,1000,500));
                System.out.println("wtf");
            stage.setTitle("Modifier Jeux");
                stage.show();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } catch (IOException ex) {
                Logger.getLogger(ListerJeuxController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void goAddGame(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterJeux.fxml"));
            Parent root = loader.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(root,1000,500));
            stage.setTitle("Ajouter Jeux");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void relode(ActionEvent event) {
        initTable();
    }

}
