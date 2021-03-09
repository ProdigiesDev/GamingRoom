/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

/**
 * FXML Controller class
 *
 * @author eyatr
 */
public class CoursDetailsController implements Initializable {

    @FXML
    private TableColumn<?, ?> cid;
    @FXML
    private TableColumn<?, ?> cnom;
    @FXML
    private TableColumn<?, ?> cdes;
    @FXML
    private TableColumn<?, ?> cnb;
    @FXML
    private TableColumn<?, ?> cmem;
    @FXML
    private TableColumn<?, ?> cdate;
    @FXML
    private TableColumn<?, ?> cmoc;
    @FXML
    private TableColumn<?, ?> ccat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
