/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Member;

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
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Membre;

/**
 * FXML Controller class
 *
 * @author Sonia
 */
public class StatistiqueMembresController implements Initializable {

    @FXML
    private BarChart<Object, Integer> chart_statistic;
    @FXML
    private NumberAxis y_numbers;
    @FXML
    private CategoryAxis x_role;
  

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
        
        String SQL= "select role,count(*) from membre where role != 'Admin' group by role";


        
        XYChart.Series<Object,Integer> series = new XYChart.Series<>();
       
        try {
    Class.forName("com.mysql.jdbc.Driver");
    conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/gamingroom","gamingRoomUser","!&_UkTz/Cw`*2#[u");
    stmt=conn.createStatement();
            try{
                 rs = conn.createStatement().executeQuery(SQL);
                 while(rs.next()){
                     System.out.println(rs.getString(2));
                     series.getData().add(new XYChart.Data<>(rs.getObject(1), rs.getInt(2)));
                 }
                chart_statistic.getData().add(series);
                       } catch (Exception e) {
                             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Probleme");
            alert.show();
                            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
