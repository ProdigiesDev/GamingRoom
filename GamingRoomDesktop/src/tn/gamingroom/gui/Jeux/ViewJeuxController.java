/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Jeux;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Jeux;
import tn.gamingroom.entities.Score;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.entities.UserSession;
import tn.gamingroom.gui.Jeux.Tictactoe.TPPOOGameTikTakTok;
import tn.gamingroom.outils.Env;
import tn.gamingroom.services.JeuxService;
import tn.gamingroom.services.MembreServices;
import tn.gamingroom.services.ScoreService;

/**
 * FXML Controller class
 *
 * @author Dah
 */
public class ViewJeuxController implements Initializable {

    @FXML
    private ImageView jeuxImage;
    @FXML
    private JFXListView<ScoreList> listChat;
    @FXML
    private Label title;
    @FXML
    private JFXButton startBtn;
    private Jeux jeux;
    @FXML
    private TextFlow descgame;
    private List<Score> scores;
    private Membre membre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        

    }

    public void editInterface(Jeux jeux) {
        this.jeux = jeux;
        ScoreService scoreService = new ScoreService();
        title.setText(jeux.getNom());
        ObservableList<ScoreList> items = FXCollections.observableArrayList();
        MembreServices membreServices = new MembreServices();
        scoreService.getScoreByJeuId(jeux.getId()).forEach(score -> {
            items.add(new ScoreList(membreServices.getById(score.getMemebre_id()).getNom(), score.getScore()));
        });
        listChat.setItems(items);
        getListForJeuxDesc(jeux.getDescriString(), descgame);
        try {
            jeuxImage.setImage(new Image(new File(Env.getImagePath() + "\\jeux\\" + jeux.getImage()).toURI().toURL().toExternalForm()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(ViewJeuxController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getListForJeuxDesc(String desc, TextFlow textFlow) {
        ObservableList list = textFlow.getChildren();
        List<String> listreviw = Arrays.asList((desc).split("(?<=\\G.{30})"));

        listreviw.forEach(reviewSub -> {
            Text text1 = new Text(reviewSub);

            //Setting font to the text 
            text1.setFont(new Font(15));

            //Setting color to the text  
            text1.setFill(Color.DARKSLATEBLUE);

            list.add(text1);
        });
    }

    @FXML
    private void startTheGame(ActionEvent event) {
        if (UserSession.getInstance() != null) {
            membre = UserSession.getInstance().getUser();
        } else {
            int a = JOptionPane.showConfirmDialog(new JFrame(), "vous dois d'abord vous connecter ?");
            if (a == JOptionPane.YES_OPTION) {
                goLogin();
            }
            return;
        }
            int a = JOptionPane.showConfirmDialog(new JFrame(), "Êtes-vous prêt à jouer ?");
        if (a == JOptionPane.YES_OPTION) {
            switch (jeux.getId()) {
                case GamesIds.SNAKE_ID:
                    opengame(new Snake(jeux));
                    break;
                case GamesIds.TIC_TAC_TOE:
                    opengame(new TPPOOGameTikTakTok(jeux));
                default:
                    break;
            }
        }
    }

    private void opengame(Application game) {
        Platform.runLater(new Runnable() {
            public void run() {

                try {
                    game.start(new Stage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    private void goLogin() {
        try {
            Parent root = FXMLLoader.
                    load(getClass().getResource("../Member/LoginMember.fxml"));

            startBtn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

class ScoreList {

    String name;
    int score;

    public ScoreList() {
    }

    public ScoreList(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return name + " : " + score;
    }

}
