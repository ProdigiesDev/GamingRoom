/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import tn.gamingroom.entities.CommentaireReact;
import tn.gamingroom.entities.Evenement;
import tn.gamingroom.entities.EvenementImage;
import tn.gamingroom.entities.ReactionEv;
import static tn.gamingroom.gui.Mapping.browser;
import tn.gamingroom.outils.Env;
import tn.gamingroom.services.CategorieServices;
import tn.gamingroom.services.EvenementService;
import tn.gamingroom.services.MembreServices;

/**
 * FXML Controller class
 *
 * @author Farah
 */
public class ConsulterEventFrontOfficeController implements Initializable {

    @FXML
    private ImageView imageC;
    @FXML
    private Label titre;
    @FXML
    private Label dd;
    @FXML
    private Label df;
    @FXML
    private Label nbM;
    @FXML
    private Label description;
    @FXML
    private TableColumn<CommentaireReact, String> membreCom;
    @FXML
    private TableColumn<CommentaireReact, String> commentaire;
    @FXML
    private WebView vidYoutube;
    int id;
    private Scene s;
    @FXML
    private TableView<CommentaireReact> tVCom;
    @FXML
    private Label likes;
    @FXML
    private Label dislikes;
    @FXML
    private ImageView qrIV;
    private String lat;
    private String lang;

    static Browser browser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void initTable() {
        EvenementService es = new EvenementService();
        MembreServices ms = new MembreServices();
        List<CommentaireReact> lIm = new ArrayList<CommentaireReact>();
        es.listeCommentaires(id).forEach(e -> {
            String nom = ms.findById(e.getMembre_id());
            lIm.add(new CommentaireReact(e.getId(), e.getEvenement_id(), e.getMembre_id(), e.getCommentaire(), nom));

        });

        ObservableList<CommentaireReact> listEventsIm = FXCollections.observableArrayList(lIm);

        tVCom.setItems(listEventsIm);
    }

    public void intData(int idE, Scene n) {
        try {
            EvenementService es = new EvenementService();
            this.id = idE;
            Evenement e = es.findById(idE);
            File f = new File(e.getImage());
            Image img = new Image(f.toURI().toURL().toExternalForm());
            imageC.setSmooth(true);
            imageC.setImage(img);
            dd.setText(e.getDateDeb().toString());
            df.setText(e.getDateFin().toString());
            titre.setText(titre.getText() + " " + e.getNomEvent());
            description.setText(e.getDescription());
            nbM.setText(nbM.getText() + " " + e.getNbreMax_participant());
            vidYoutube.getEngine().load(e.getLienYoutube());
            s = n;
            if (!e.getLieu().equals("ONLINE")) {
                String[] lieuT = e.getLieu().split(",", 3);
                this.lat = lieuT[0];
                this.lang = lieuT[1];

            }
            likes.setText(es.getLikes(idE) + "");
            dislikes.setText(es.getDisikes(idE) + "");
            membreCom.setCellValueFactory(new PropertyValueFactory<CommentaireReact, String>("nomMembre"));
            commentaire.setCellValueFactory(new PropertyValueFactory<CommentaireReact, String>("commentaire"));
            createQR(e.getDescription());
            initTable();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ConsulterEvenementBackOfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createQR(String Event) {
        // GENERATE QR CODE
        ByteArrayOutputStream out = QRCode.from(Event).to(ImageType.PNG).withSize(100, 100).stream();
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        qrIV.setImage(new Image(in));
    }

    @FXML
    private void participer(ActionEvent event) {

        JFrame f = new JFrame();

        int a = JOptionPane.showConfirmDialog(f, "Êtes-vous sûr?");
        if (a == JOptionPane.YES_OPTION) {
            EvenementService es = new EvenementService();
            if (es.eventSature(id)) {
                JOptionPane.showMessageDialog(null, "Evenement vient d'être saturé!");
            } else {
                //todo: changer le deuxieme parametre pour y mettre l'id du membre courant
                if (es.sinscrirEvenement(id, 2) != 0) {
                    JOptionPane.showMessageDialog(null, "Inscription réussite! Vous serez informés de votre adversaire ultérieurement.");
                } else {
                    int b = JOptionPane.showConfirmDialog(f, "Vous êtes déjà inscrit! voulez vous annuler votre inscription?");
                    if (b == JOptionPane.YES_OPTION) {
                        es.annulerInscription(id, 1);

                    }

                }
            }
        }
    }

    @FXML
    private void addComment(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterCommentaire.fxml"));
            Parent root;
            root = loader.load();
            AjouterCommentaireController pctC = loader.getController();
            pctC.intData(id);
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            File f = new File(Env.getDossierImageUtilEventPath() + "logo.png");
            Image img = new Image(f.toURI().toURL().toExternalForm());
            primaryStage.getIcons().add(img);
            primaryStage.setTitle("Veuillez entrer votre commentaire");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventFrontOfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void openLocation(ActionEvent event) {
        if (lat != null) {
            Scene scene;
            Stage primaryStage = new Stage();
            primaryStage.setTitle(titre.getText());
            browser = new Browser(Double.parseDouble(lat), Double.parseDouble(lang),true);
            scene = new Scene(browser, 500, 500, Color.web("#666970"));
            primaryStage.setScene(scene);
            primaryStage.show();
        } else {
            JOptionPane.showMessageDialog(null, "ONLINE");
        }

    }

}
