/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Accueil;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import tn.gamingroom.entities.Produits;
import tn.gamingroom.outils.Env;
import tn.gamingroom.services.ProduitCrud;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.services.MembreServices;
import java.util.Arrays;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tn.gamingroom.entities.Avis;
import tn.gamingroom.entities.UserSession;
import tn.gamingroom.gui.Jeux.AjouterJeuxController;
import tn.gamingroom.gui.Jeux.Snake;
import tn.gamingroom.services.AvisService;

/**
 * FXML Controller class
 *
 * @author Dah
 */
public class AccueilController implements Initializable {

    @FXML
    private AnchorPane parentControl;
    @FXML
    private Pane bestProduct1;
    @FXML
    private Pane bestProduct2;

    @FXML
    private Pane bestProduct3;

    
    @FXML
    private Label reviewMbr;
    @FXML
    private TextFlow review;
    @FXML
    private Button nextProductbtn1;
    
    private int bestAvisPage=0;
    private List<Produits> bestProducts;
    private List<Avis> listAvis;
    private final int NB_PRODUCT_PAGE_ITEMS = 3;
    private Membre membre;
    @FXML
    private Button presProductbtn1;
    @FXML
    private Pane bestProduct11;
    @FXML
    private Pane avisPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(UserSession.getInstance()!=null){
            membre=UserSession.getInstance().getUser();
            avisPane.setVisible(true);
        }
        
        AvisService avisService=new AvisService();
        listAvis=avisService.getPostivesAvis();
        review.setTextAlignment(TextAlignment.CENTER); 
        review.setLineSpacing(2.0); 
        
        bestAvisPage=0;
        initReview();
    }

    /*
    void initListProducts(){
        if(bestProductPage>1){
            
        int startFrom=bestProductPage*NB_PRODUCT_PAGE_ITEMS-1;
        int endIn=bestProductPage*NB_PRODUCT_PAGE_ITEMS-1;
        }
        
        List<Produits> produitses=bestProducts.subList(nbProjectStart, nbProjectEnd);
    }
     */
    
    
    private void initReview(){
        
        System.out.println(bestAvisPage);
        ObservableList list = review.getChildren();
        MembreServices membreServices=new MembreServices();
        Membre membre=membreServices.getById(listAvis.get(bestAvisPage).getMember_id());
        reviewMbr.setText(membre.getNom()+" "+membre.getPrenom());
        List<String> listreviw=Arrays.asList(("\" "+listAvis.get(bestAvisPage).getAvis().concat(" \"")).split("(?<=\\G.{20})"));
        list.clear();
        listreviw.forEach(reviewSub->{
             Text text1 = new Text(reviewSub); 

            //Setting font to the text 
            text1.setFont(new Font(15)); 

            //Setting color to the text  
            text1.setFill(Color.DARKSLATEBLUE); 
            
            list.add(text1);  
        });   
    }
    
    @FXML
    private void presAvisValue(ActionEvent event) {
        bestAvisPage-=1;
        if(bestAvisPage<0)
            bestAvisPage=0;
        System.out.println(bestAvisPage);
        initReview();
    }

    @FXML
    private void nextAvisValue(ActionEvent event) {
          bestAvisPage+=1;
        if(bestAvisPage>=listAvis.size())
            bestAvisPage=listAvis.size()-1;
        initReview();
    }

}
