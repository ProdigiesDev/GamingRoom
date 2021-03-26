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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author yasmine
 */
public class StatistiqueController implements Initializable {

    @FXML
    private Button load;
    @FXML
    private BarChart<String, Integer> barchart;
    @FXML
    private NumberAxis Yaxis;
    @FXML
    private CategoryAxis XAxis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         Yaxis.setLabel("Prix");
    }    

    @FXML
    private void LoadStat(ActionEvent event) {
        
        Connection conn = null;
        Statement stmt = null ;
        ResultSet rs=null;
        String SQL= "Select libelle, prix FROM  produit ORDER BY libelle";  
        XYChart.Series<String,Integer> series = new XYChart.Series<>();
        series.setName("Prix par Produits");   
        try {
    Class.forName("com.mysql.jdbc.Driver");
    conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/gamingroom","gamingRoomUser","!&_UkTz/Cw`*2#[u");
    stmt=conn.createStatement();                   
            try{
                 rs = conn.createStatement().executeQuery(SQL);
                 while(rs.next()){
                     series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));
                 }
                barchart.getData().add(series);
                       } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Probleme ");}                     
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void Back(ActionEvent event) {
              try {
            Parent dashboard ;
            dashboard = FXMLLoader.load(getClass().getResource("AjoutProduit.fxml"));
            
            
            Scene dashboardScene = new Scene(dashboard);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(dashboardScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutCleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
        
        
    }
    

