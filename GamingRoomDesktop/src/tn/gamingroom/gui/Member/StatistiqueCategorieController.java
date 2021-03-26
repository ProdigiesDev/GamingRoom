/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Member;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Membre;

/**
 * FXML Controller class
 *
 * @author Sonia
 */
public class StatistiqueCategorieController implements Initializable {

    @FXML
    private NumberAxis y_member;
    @FXML
    private CategoryAxis x_category;
    @FXML
    private BarChart<String, Integer> chart_statistic;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadingChart();
    }  
    public void loadingChart(){
        Connection conn = null;
        Statement stmt = null ;
        ResultSet rs=null;
        int nbMembre=0,nbCoach=0;
        String SQL= "select count(id)   from membre where role= ";
        try {
            stmt=conn.createStatement();
            rs= stmt.executeQuery(SQL+"'"+Membre.Role.Membre.toString()+"'");
            if(rs.next()){
                nbMembre= rs.getInt(1);
            }
             rs= stmt.executeQuery(SQL+"'"+Membre.Role.Coach.toString()+"'");
            if(rs.next()){
                nbCoach= rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        XYChart.Series<String,Integer> series = new XYChart.Series<>();
       
        try {
    Class.forName("com.mysql.jdbc.Driver");
    conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/gamingroom","gamingRoomUser","!&_UkTz/Cw`*2#[u");
    stmt=conn.createStatement();
            try{
                 rs = conn.createStatement().executeQuery(SQL);
                 while(rs.next()){
                     series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));
                 }
                chart_statistic.getData().add(series);
                       } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Probleme ");}
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
