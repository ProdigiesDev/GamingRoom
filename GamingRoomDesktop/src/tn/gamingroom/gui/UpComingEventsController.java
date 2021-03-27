/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.EvenementImage;
import tn.gamingroom.outils.Env;
import tn.gamingroom.services.CategorieServices;
import tn.gamingroom.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author Farah
 */
public class UpComingEventsController implements Initializable {

    @FXML
    private Label n;
    @FXML
    private TableView<EvenementImage> listeEvents;
    @FXML
    private TableColumn<EvenementImage, ImageView> imL;
    @FXML
    private TableColumn<EvenementImage, Date> dateDL;
    @FXML
    private TableColumn<EvenementImage, Date> dateFL;
    @FXML
    private TableColumn<EvenementImage, String> tL;
    @FXML
    private TableColumn<EvenementImage, String> desL;
    @FXML
    private TableColumn<EvenementImage, String> lieuL;
    @FXML
    private TableColumn<EvenementImage, String> LienYL;
    @FXML
    private TableColumn<EvenementImage, Integer> idE;
    @FXML
    private TableColumn<EvenementImage, String> catL;
    @FXML
    private TableColumn<EvenementImage, String> ouvert;
    @FXML
    private TableColumn<EvenementImage, Integer> idCat;
    @FXML
    private TableColumn<EvenementImage, String> imageURL;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        idE.setCellValueFactory(new PropertyValueFactory<EvenementImage, Integer>("idevent"));
        imL.setCellValueFactory(new PropertyValueFactory<EvenementImage, ImageView>("image"));
        imageURL.setCellValueFactory(new PropertyValueFactory<EvenementImage, String>("imageUrl"));
        dateDL.setCellValueFactory(new PropertyValueFactory<EvenementImage, Date>("dateDeb"));
        dateFL.setCellValueFactory(new PropertyValueFactory<EvenementImage, Date>("dateFin"));
        tL.setCellValueFactory(new PropertyValueFactory<EvenementImage, String>("nomEvent"));
        idCat.setCellValueFactory(new PropertyValueFactory<EvenementImage, Integer>("categorie_id"));
        catL.setCellValueFactory(new PropertyValueFactory<EvenementImage, String>("categorieNom"));
        ouvert.setCellValueFactory(new PropertyValueFactory<EvenementImage, String>("sature"));
        desL.setCellValueFactory(new PropertyValueFactory<EvenementImage, String>("description"));
        lieuL.setCellValueFactory(new PropertyValueFactory<EvenementImage, String>("lieu"));
        LienYL.setCellValueFactory(new PropertyValueFactory<EvenementImage, String>("lienYoutube"));

        initTable();
        addButtonLike();
        addButtonDislike();
        addButtonParticiper();
    }

    private void initTable() {
        EvenementService es = new EvenementService();
        CategorieServices cs = new CategorieServices();
        List<EvenementImage> lIm = new ArrayList<EvenementImage>();
        es.upComingEvents().forEach(e -> {

            try {
                File f = new File(e.getImage());
                Image img = new Image(f.toURI().toURL().toExternalForm());
                ImageView i = new ImageView(img);
                i.setFitHeight(50);
                i.setFitWidth(70);
                f = new File(Env.getDossierImageUtilEventPath() + "check.png");
                if (es.eventSature(e.getIdevent())) {
                    f = new File(Env.getDossierImageUtilEventPath() + "uncheck.png");
                }
                img = new Image(f.toURI().toURL().toExternalForm());
                ImageView iC = new ImageView(img);
                iC.setFitHeight(20);
                iC.setFitWidth(25);
                lIm.add(new EvenementImage(e.getIdevent(), e.getNomEvent(), e.getDateDeb(), e.getDateFin(), i, e.getImage(), e.getCategorie_id(), cs.getById(e.getCategorie_id()).getNomcat(), e.getNbreMax_participant(), e.getDescription(), e.getLieu(), e.getLienYoutube(), iC));

            } catch (MalformedURLException ex) {
                Logger.getLogger(ListeEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        ObservableList<EvenementImage> listEventsIm = FXCollections.observableArrayList(lIm);

        listeEvents.setItems(listEventsIm);
    }

    private void addButtonLike() {
        TableColumn<EvenementImage, Void> colBtn = new TableColumn("");

        Callback<TableColumn<EvenementImage, Void>, TableCell<EvenementImage, Void>> cellFactory = new Callback<TableColumn<EvenementImage, Void>, TableCell<EvenementImage, Void>>() {
            @Override
            public TableCell<EvenementImage, Void> call(final TableColumn<EvenementImage, Void> param) {
                final TableCell<EvenementImage, Void> cell = new TableCell<EvenementImage, Void>() {

                    private final Button btn = new Button();

                    {

                        try {

                            File f = new File(Env.getDossierImageUtilEventPath() + "like.png");

                            Image img = new Image(f.toURI().toURL().toExternalForm());
                            ImageView view = new ImageView(img);
                            view.setFitHeight(20);
                            view.setFitWidth(20);
                            btn.setTextFill(Paint.valueOf("#f8f7f7"));
                            btn.setStyle("-fx-background-color: #ffffff");
                            //view.setPreserveRatio(true);
                            btn.setGraphic(view);
                        } catch (MalformedURLException ex) {
                            Logger.getLogger(ListerEventUserController.class.getName()).log(Level.SEVERE, null, ex);
                        }

//                        btn.setOnAction(event -> {
//                            EvenementImage data = getTableView().getItems().get(getIndex());
//                            JFrame f = new JFrame();
//
//                            int a = JOptionPane.showConfirmDialog(f, "Êtes-vous sûr?");
//                            if (a == JOptionPane.YES_OPTION) {
//                                EvenementService es = new EvenementService();
//                                es.suppressionEvenement(data.getIdevent());
//                                initTable();
//                            }
//
//                        });
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
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        listeEvents.getColumns().add(colBtn);

    }

    private void addButtonDislike() {
        TableColumn<EvenementImage, Void> colBtn = new TableColumn("");

        Callback<TableColumn<EvenementImage, Void>, TableCell<EvenementImage, Void>> cellFactory = new Callback<TableColumn<EvenementImage, Void>, TableCell<EvenementImage, Void>>() {
            @Override
            public TableCell<EvenementImage, Void> call(final TableColumn<EvenementImage, Void> param) {
                final TableCell<EvenementImage, Void> cell = new TableCell<EvenementImage, Void>() {

                    private final Button btn = new Button();

                    {

                        try {

                            File f = new File(Env.getDossierImageUtilEventPath() + "dislike.png");

                            Image img = new Image(f.toURI().toURL().toExternalForm());
                            ImageView view = new ImageView(img);
                            view.setFitHeight(20);
                            view.setFitWidth(20);

                            btn.setTextFill(Paint.valueOf("#f8f7f7"));
                            btn.setStyle("-fx-background-color: #ffffff");
                            //view.setPreserveRatio(true);
                            btn.setGraphic(view);
                        } catch (MalformedURLException ex) {
                            Logger.getLogger(ListerEventUserController.class.getName()).log(Level.SEVERE, null, ex);
                        }

//                        btn.setOnAction(event -> {
//                            EvenementImage data = getTableView().getItems().get(getIndex());
//                            JFrame f = new JFrame();
//
//                            int a = JOptionPane.showConfirmDialog(f, "Êtes-vous sûr?");
//                            if (a == JOptionPane.YES_OPTION) {
//                                EvenementService es = new EvenementService();
//                                es.suppressionEvenement(data.getIdevent());
//                                initTable();
//                            }
//
//                        });
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
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        listeEvents.getColumns().add(colBtn);

    }

    private void addButtonParticiper() {
        TableColumn<EvenementImage, Void> colBtn = new TableColumn("");

        Callback<TableColumn<EvenementImage, Void>, TableCell<EvenementImage, Void>> cellFactory = new Callback<TableColumn<EvenementImage, Void>, TableCell<EvenementImage, Void>>() {
            @Override
            public TableCell<EvenementImage, Void> call(final TableColumn<EvenementImage, Void> param) {
                final TableCell<EvenementImage, Void> cell = new TableCell<EvenementImage, Void>() {

                    private final Button btn = new Button("Participer");

                    {
                        btn.setTextFill(Paint.valueOf("#f8f7f7"));
                        btn.setStyle("-fx-background-color: #6f1075");
                        btn.setOnAction(event -> {
                            EvenementImage data = getTableView().getItems().get(getIndex());
                            JFrame f = new JFrame();

                            int a = JOptionPane.showConfirmDialog(f, "Êtes-vous sûr?");
                            if (a == JOptionPane.YES_OPTION) {
                                EvenementService es = new EvenementService();
                                if (es.eventSature(data.getIdevent())) {
                                    JOptionPane.showMessageDialog(null, "Evenement vient d'être saturé!");
                                } else {
                                    es.sinscrirEvenement(data.getIdevent(), 1);
                                }
                                initTable();
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
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        listeEvents.getColumns().add(colBtn);

    }

    @FXML

    private void getSelected(MouseEvent event) {
        try {
            int index = listeEvents.getSelectionModel().getSelectedIndex();

            if (index <= -1) {

                return;

            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterEvenementBackOffice.fxml"));
            Parent root;
            root = loader.load();
            ConsulterEvenementBackOfficeController pctC = loader.getController();
            pctC.intData(idE.getCellData(index), n.getScene());
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            File f = new File(Env.getDossierImageUtilEventPath() + "logo.png");
            Image img = new Image(f.toURI().toURL().toExternalForm());
            primaryStage.getIcons().add(img);
            primaryStage.setTitle(tL.getCellData(index));
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
