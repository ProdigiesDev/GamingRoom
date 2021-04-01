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
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Cours;
import tn.gamingroom.entities.Courslm;
import tn.gamingroom.outils.Env;
import tn.gamingroom.services.CategorieServices;
import tn.gamingroom.services.ServiceCours;

/**
 * FXML Controller class
 *
 * @author eyatr
 */
public class CoursMembreintController implements Initializable {

    @FXML
    private TableColumn<Courslm, ImageView> cimg;
    @FXML
    private TableColumn<Courslm, String> cnom;
    @FXML
    private TableColumn<Courslm, String> cdes;
    @FXML
    private TableColumn<Courslm, Integer> ccat;
    @FXML
    private TableView<Courslm> tbCours;
    @FXML
    private TextField prefer;
    @FXML
    private Label titre;
    @FXML
    private TableColumn<Courslm, String> lienYoutube;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceCours s = new ServiceCours();

        //ObservableList<Cours> listCours = FXCollections.observableArrayList(s.displayCours());
        cimg.setCellValueFactory(new PropertyValueFactory<Courslm, ImageView>("image"));
        cnom.setCellValueFactory(new PropertyValueFactory<Courslm, String>("nomCours"));
        ccat.setCellValueFactory(new PropertyValueFactory<Courslm, Integer>("categorie_id"));

        cdes.setCellValueFactory(new PropertyValueFactory<Courslm, String>("description"));
        lienYoutube.setCellValueFactory(new PropertyValueFactory<Courslm, String>("lienYoutube"));

        this.initTable(null);

    }

    @FXML
    private void preferCours(KeyEvent event) {
        ServiceCours s = new ServiceCours();
        //ObservableList<Cours> list = FXCollections.observableArrayList(s.displayprefcours(Integer.parseInt(prefer.getText())));
        cimg.setCellValueFactory(new PropertyValueFactory<Courslm, ImageView>("image"));
        cnom.setCellValueFactory(new PropertyValueFactory<Courslm, String>("nomCours"));

        cdes.setCellValueFactory(new PropertyValueFactory<Courslm, String>("description"));
        lienYoutube.setCellValueFactory(new PropertyValueFactory<Courslm, String>("lienYoutube"));
        ccat.setCellValueFactory(new PropertyValueFactory<Courslm, Integer>("categorie_id"));
        //System.out.println("iddperc "+prefer.getText());
        initTable(s.displayprefcours(Integer.parseInt(prefer.getText())));
    }

    @FXML
    private void getSelect(MouseEvent event) {
        try {
            Parent root = null;
            int index = tbCours.getSelectionModel().getSelectedIndex();
            Courslm c = tbCours.getItems().get(index);
            System.out.println("c "+c);
            if (index <= -1) {
                return;
            }
            //id.setText(cid.getCellData(index).toString());
//            nom.setText(cnom.getCellData(index).toString());
//            icat.setText(ccat.getCellData(index).toString());
//            //idate.(cdate.getCellData(index).toString());
//            //icl.setText(cmoc.getCellData(index).toString());
//            ides.setText(cdes.getCellData(index).toString());
            // inb.setText(cnb.getCellData(index).toString());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("infoCours.fxml"));
            root = loader.load();
            InfoCoursController pct = loader.getController();
            System.out.println("c "+c);
            pct.setCours(c);
            System.out.println("c1 "+c.getLienYoutube());
            titre.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(CoursMembreintController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initTable(List<Cours> listcours) {
        ServiceCours es = new ServiceCours();
        CategorieServices cs = new CategorieServices();
        if (listcours == null) {
            listcours = es.displayCours();
        }
        List<Courslm> lIm = new ArrayList<Courslm>();
        listcours.forEach(e -> {

            try {
                File f = new File(Env.getImagePath() + "cours\\" + e.getImage());
                Image img = new Image(f.toURI().toURL().toExternalForm());
                ImageView i = new ImageView(img);
                i.setFitHeight(50);
                i.setFitWidth(70);
                Courslm addCours = new Courslm(e.getId(), e.getNomCours(), e.getDescription(), e.getNb_participants(), e.getMembre_id(), e.getDate_creation(), e.getTags(), i, e.getCategorie_id(), cs.getCategorieById(e.getCategorie_id()).getNomcat(),e.getLienYoutube());
                System.out.println("ima "+addCours.getImage());
                addCours.setImagename(e.getImage());
                lIm.add(addCours);
            } catch (MalformedURLException ex) {
                Logger.getLogger(CoursDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        System.out.println("ev " + lIm);

        ObservableList<Courslm> listCoursIm = FXCollections.observableArrayList(lIm);

        tbCours.setItems(listCoursIm);
    }

}
