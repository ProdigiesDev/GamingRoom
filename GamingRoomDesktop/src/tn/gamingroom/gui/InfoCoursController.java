/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Cours;
import tn.gamingroom.entities.Courslm;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.entities.ParticipantsCours;
import tn.gamingroom.entities.ReacCours;
import tn.gamingroom.entities.UserSession;
import tn.gamingroom.outils.Outils;
import tn.gamingroom.services.ServiceCours;
import tn.gamingroom.services.ServiceParticipantsCours;
import tn.gamingroom.services.ServiceReacCours;

/**
 * FXML Controller class
 *
 * @author eyatr
 */
public class InfoCoursController implements Initializable {

    @FXML
    private Label nom;

    @FXML
    private Label cat;
    @FXML
    private Label des;
    @FXML
    private TextField com;
    @FXML
    private TableColumn<ReacCours, String> allcom;
    @FXML
    private TableColumn<ReacCours, Integer> colMem;

    Courslm c;

    int memberId = 3;
    int cour_id;
    @FXML
    private TableView<ReacCours> tablCom;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private Text nbInter;
    @FXML
    private Button back;

    @FXML
    private Text nbInternegng;
    @FXML
    private ImageView imgcours;
    @FXML
    private Button inscrire;
    ParticipantsCours p;
    @FXML
    private WebView vidYoutube;
    @FXML
    private FontAwesomeIcon dislike;
    @FXML
    private FontAwesomeIcon like;

    private Membre membre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colMem.setCellValueFactory(new PropertyValueFactory<ReacCours, Integer>("membre_id"));
        allcom.setCellValueFactory(new PropertyValueFactory<ReacCours, String>("commentaire"));

    }

    public void setCours(Courslm c) {
        this.c = c;
        cat.setText(String.valueOf(c.getCategorie_id()));
        nom.setText(c.getNomCours());
        des.setText(c.getDescription());
        imgcours.setImage(c.getImage().getImage());
        cour_id = c.getId();

        //System.out.println("c "+c.getLienYoutube());
        vidYoutube.getEngine().load(c.getLienYoutube());

        initTable();
        initNbInteraction();

    }

//    public void setCours(Courslm cm) {
//        this.cm = c;
//        cat.setText(String.valueOf(c.getCategorie_id()));
//        nom.setText(c.getNomCours());
//        des.setText(c.getDescription());
//        initTable();
//        initNbInteraction();
//        
//    }
    void initTable() {
        ServiceReacCours cours = new ServiceReacCours();
        ObservableList<ReacCours> courses = FXCollections.observableArrayList(cours.getListReacCours(c.getId()));
        tablCom.setItems(courses);
    }

    @FXML
    private void ajouterCommentaire(ActionEvent event) {
        String commentaire = com.getText();
        ServiceReacCours serviceReacCours = new ServiceReacCours();
        if (Outils.containsBadWords(commentaire)) {
            JOptionPane.showMessageDialog(null, "Ce Message ne respecte pas nos standards de la communauté en matière de contenus indésirables");
            return;
        }
        List<ReacCours> reacCourses = serviceReacCours.getReactionByMemberCours(c.getId(), memberId);
        System.out.println(reacCourses.size());
        if (reacCourses.size() <= 9) {
            ReacCours cours = new ReacCours(0, commentaire, memberId, c.getId());

            serviceReacCours.ajouterReacC(cours);
            tablCom.getItems().add(cours);
        } else {
            JOptionPane.showMessageDialog(null, "depasse pas 10 commentaires");
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                @Override
                public void run() {
                    ReacCours cours = new ReacCours(0, commentaire, memberId, c.getId());

                    serviceReacCours.ajouterReacC(cours);
                    tablCom.getItems().add(cours);
                }
            }, 2000
            );
        }

    }

    @FXML
    private void react(MouseEvent event) {
        ServiceReacCours serviceReacCours = new ServiceReacCours();

        ReacCours rc = serviceReacCours.getFirstReactionById(this.c.getId(), memberId);
        int nb = 0;
        if (rc == null) {
            ReacCours cours = new ReacCours(1, "", memberId, c.getId());
            nb = serviceReacCours.ajouterReacC(cours);
        } else if (rc.getNb_interaction() == -1) {
            rc.setNb_interaction(1);
            nb = serviceReacCours.updateReacC(rc);

        }
        if (nb > 0) {
            initNbInteraction();
        }
    }

    @FXML
    private void back(ActionEvent event) {
        try {
            Parent root = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("coursMembreint.fxml"));
            root = loader.load();
            CoursMembreintController pct = loader.getController();

            nom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InfoCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reacneg(MouseEvent event) {
        ServiceReacCours serviceReacCours = new ServiceReacCours();

        ReacCours rc = serviceReacCours.getFirstReactionById(this.c.getId(), memberId);
        int nb = 0;
        if (rc == null) {
            ReacCours cours = new ReacCours(-1, "", memberId, c.getId());
            nb = serviceReacCours.ajouterReacC(cours);

        } else if (rc.getNb_interaction() == 1) {

            rc.setNb_interaction(-1);
            nb = serviceReacCours.updateReacC(rc);

        }
        if (nb > 0) {
            initNbInteraction();
        }
    }

    void initNbInteraction() {
        ServiceReacCours serviceReacCours = new ServiceReacCours();
        Integer[] nbLikes = serviceReacCours.getNbInteraction(this.c.getId());
        nbInter.setText(String.valueOf(nbLikes[0]));
        nbInternegng.setText(String.valueOf(nbLikes[1]));

    }

    @FXML
    private void inscrirecours(ActionEvent event) {
        ParticipantsCours pc = new ParticipantsCours();

        JFrame f = new JFrame();

        int a = JOptionPane.showConfirmDialog(f, "Êtes-vous sûr?");
        if (a == JOptionPane.YES_OPTION) {
            ServiceParticipantsCours spc = new ServiceParticipantsCours();
            Cours c = new Cours();
            ////todo member_id tetbadel
            if (spc.verifInscrit(2, cour_id) > 0) {
                int nb = spc.ajouterParticipant(2, cour_id);
                if (nb == 0) {
                    JOptionPane.showMessageDialog(null, "Erreur d'inscription");
                } else {
                    JOptionPane.showMessageDialog(null, "Félicitation vous etes inscrit à ce cours");
                }
            }else {
                JOptionPane.showMessageDialog(null, "Vous êtes déjà inscrit");
            }
//            

        }
    }

    private void goToListePart(ActionEvent event) {

        if (UserSession.getInstance() != null) {
            membre = UserSession.getInstance().getUser();
            
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listeParticipantsCours.fxml"));
            Parent root = loader.load();
            Scene s = new Scene(root);
            ListeParticipantsCoursController pct = loader.getController();
            pct.initDat(cour_id);
            Stage prS = new Stage();
            prS.setScene(s);
            prS.show();
        } catch (IOException ex) {
            Logger.getLogger(InfoCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}



