/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Produits;
import tn.gamingroom.services.ProduitCrud;

/**
 * FXML Controller class
 *
 * @author yasmine
 */
public class StatistiqueController implements Initializable {

    @FXML
    private BarChart<String, Double> barchart;
    @FXML
    private NumberAxis Yaxis;
    @FXML
    private CategoryAxis XAxis;
    private PieChart piechart;

    @FXML
    private PieChart PieChartData;
    private ProduitCrud produitCrud = new ProduitCrud();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Yaxis.setLabel("Prix");
            loading();
            load();
            PieChartData.setData(Pie);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    ObservableList<PieChart.Data> Pie;
    ArrayList<Double> cell = new ArrayList<>();
    ArrayList<String> name = new ArrayList<>();
    Connection conn;
    PreparedStatement pre;
    ResultSet rs;

    public void load() throws ClassNotFoundException, SQLException {
        Pie = FXCollections.observableArrayList();
        List<Produits> produitses = produitCrud.displayProduit();
        produitses.forEach(produit -> {

            Pie.add(new PieChart.Data(produit.getLibelle(), produit.getPrix()));
            name.add(produit.getLibelle());
            cell.add(produit.getPrix());
        });

    }

    public void loading() {

        XYChart.Series<String, Double> series = new XYChart.Series<>();
        series.setName("Prix par Produits");
        List<Produits> produitses = produitCrud.displayProduit();
        try {
            produitses.forEach(produit->{
                series.getData().add(new XYChart.Data<>(produit.getLibelle(), produit.getPrix()));
            });
            barchart.getData().add(series);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void Back(ActionEvent event) {
        try {
            Parent dashboard;
            dashboard = FXMLLoader.load(getClass().getResource("AjoutProduit.fxml"));

            Scene dashboardScene = new Scene(dashboard);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(dashboardScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutCleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
