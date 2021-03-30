/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;
import tn.gamingroom.entities.Categorie;
import tn.gamingroom.entities.Cours;
import tn.gamingroom.entities.Courslm;
import tn.gamingroom.outils.ApiCall;
import tn.gamingroom.outils.Env;
import tn.gamingroom.services.CategorieServices;
import tn.gamingroom.services.ServiceCours;

/**
 * FXML Controller class
 *
 * @author eyatr
 */
public class CoursDetailsController implements Initializable {

    private TableColumn<Courslm, Integer> cid;
    @FXML
    private TableColumn<Courslm, String> cnom;
    @FXML
    private TableColumn<Courslm, String> cdes;
    @FXML
    private TableColumn<Courslm, Integer> cnb;
    @FXML
    private TableColumn<Courslm, Integer> cmem;
    @FXML
    private TableColumn<Courslm, Date> cdate;
    @FXML
    private TableColumn<Courslm, String> cmoc;
    @FXML
    private TableColumn<Courslm, Integer> ccat;
    @FXML
    private TableColumn<Courslm, String> clien;
    @FXML
    private TableView<Courslm> tableCours;
    @FXML
    private Button btnaj;
    @FXML
    private Label titre;
    @FXML
    private Button btnup;
    @FXML
    private Button btns;
    @FXML
    private JFXTextField ides;
    @FXML
    private JFXTextField icl;
    @FXML
    private JFXDatePicker idate;
    @FXML
    private JFXTextField inb;
    @FXML
    private JFXTextField inom;

    @FXML
    private Button btnclean;
    @FXML
    private JFXTextField imem;
    @FXML
    private TextField search;
    @FXML
    private Button btntri;
    @FXML
    private ComboBox<Categorie> combocat;
    @FXML
    private Label alert;
    @FXML
    private Button ajoutimage;
    private FileChooser fileChooser;
    private File file;
    Stage stage;
    @FXML
    private TableColumn<Courslm, ImageView> cimage;
    @FXML
    private ImageView imagevc;
    String image;
    @FXML
    private Label verifnom;
    @FXML
    private Label verifcat;
    @FXML
    private Label verifdate;
    @FXML
    private Label veriftag;
    @FXML
    private Label verifdes;
    @FXML
    private Label verifnb;
    @FXML
    private Label verifmem;
    @FXML
    private JFXTextField lien;
    @FXML
    private Button infoYoutube;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //getVideoDetails("https://www.youtube.com/watch?v=gbt6cTZSKgo");
        inom.setStyle("-fx-text-fill: white; ");
        icl.setStyle("-fx-text-fill: white; ");
        ides.setStyle("-fx-text-fill: white; ");
        inb.setStyle("-fx-text-fill: white; ");
        imem.setStyle("-fx-text-fill: white; ");
        idate.setStyle("-fx-text-fill: white; ");
        lien.setStyle("-fx-text-fill: white; ");
        cnom.setCellValueFactory(new PropertyValueFactory<Courslm, String>("nomCours"));
        cdes.setCellValueFactory(new PropertyValueFactory<Courslm, String>("description"));
        cdate.setCellValueFactory(new PropertyValueFactory<Courslm, Date>("date_creation"));
        cmoc.setCellValueFactory(new PropertyValueFactory<Courslm, String>("tags"));
        cmem.setCellValueFactory(new PropertyValueFactory<Courslm, Integer>("membre_id"));
        cnb.setCellValueFactory(new PropertyValueFactory<Courslm, Integer>("nb_participants"));
        ccat.setCellValueFactory(new PropertyValueFactory<Courslm, Integer>("categorie_id"));
        cimage.setCellValueFactory(new PropertyValueFactory<Courslm, ImageView>("image"));
        clien.setCellValueFactory(new PropertyValueFactory<Courslm, String>("lienYoutube"));

        this.initTable(null);

        //catégorie combo
        CategorieServices csc = new CategorieServices();
        ObservableList l = FXCollections.observableArrayList(csc.DisplayCategorie());
        combocat.setItems(l);

        combocat.setConverter(new StringConverter<Categorie>() {
            @Override
            public String toString(Categorie object) {
                return object.getNomcat();
            }

            @Override
            public Categorie fromString(String string) {
                return null;
            }
        });

    }

    @FXML
    private void ajouterC(ActionEvent event) {

        //try {
        //TODO fil verifNb lazem ykon integer 
        boolean verif = verifcat() || verifdate() || verifdes() || verifmem() || verifmo() || verifmo() || verifnb() || verifnom();
        if (verif) {
            return;
        }

        Cours c = new Cours();
        ServiceCours s = new ServiceCours();
        Categorie categorie = combocat.getValue();

        c.setNomCours(inom.getText());
        c.setDescription(ides.getText());
        c.setNb_participants(Integer.parseInt(inb.getText()));
        c.setDate_creation(Date.valueOf(idate.getValue()));
        c.setTags(icl.getText());
        c.setCategorie_id(categorie.getIdcat());
        //TODO hethi lazem titbadil dynamique ki tkamil sonia
        c.setMembre_id(Integer.parseInt(imem.getText()));
        String nomImage = moveImage();
        c.setImage(nomImage);
        System.out.println(c.getImage());
        if (nomImage.length() == 0) {
            JOptionPane.showMessageDialog(null, "Veuillez insérer une image");
            return;
        }
        c.setLienYoutube(lien.getText());
        int nb = s.ajouterCours(c);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation d'ajout !");
        alert.setContentText("Voulez-Vous Vraiment Ajouter");

        if (nb == 0) {
            JOptionPane.showMessageDialog(null, "Erreur cours non ajouteé");
        } else {
            Optional<ButtonType> btn = alert.showAndWait();
            JOptionPane.showMessageDialog(null, "Cours ajouté");
            Courslm cmm = new Courslm();

            cmm.setNomCours(inom.getText());
            cmm.setDescription(ides.getText());
            cmm.setNb_participants(Integer.parseInt(inb.getText()));
            cmm.setDate_creation(Date.valueOf(idate.getValue()));
            cmm.setTags(icl.getText());
            cmm.setCategorie_id(categorie.getIdcat());
            cmm.setMembre_id(Integer.parseInt(imem.getText()));

            c.setImage(nomImage);
            cmm.setLienYoutube(lien.getText());

            clean();

        }

//            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutCours.fxml"));
//            //System.out.println(getClass().getResource("AjoutCours.fxml"));
//            Parent root = loader.load();
//            AjoutCoursController aj = loader.getController();
//            titre.getScene().setRoot(root);
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
    }

    @FXML
    private void modifierC(ActionEvent event) {

        Courslm c = this.tableCours.getSelectionModel().getSelectedItem();
        if (c == null) {
            JOptionPane.showMessageDialog(null, "a5tar cours");
            return;
        }

        //TODO fil verifNb lazem ykon integer 
        boolean verif = verifcat() || verifdate() || verifdes() || verifmem() || verifmo() || verifmo() || verifnb() || verifnom();
        if (verif) {
            return;
        }
        String Value1 = ides.getText();
        String Value2 = inom.getText();

        String Value4 = icl.getText();
        int Value5 = Integer.parseInt(inb.getText());
        Categorie cat = combocat.getValue();
        Date Value7 = Date.valueOf(idate.getValue());
        String Value8 = lien.getText();
        String nomImage = moveImage();

        if (nomImage.length() == 0) {
            JOptionPane.showMessageDialog(null, "Veuillez insérer une image");
            return;
        }

        Cours c2 = new Cours(Value2, Value1, Value5, Value7, Value4, nomImage, cat.getIdcat(), Value8);
        c2.setId(c.getId());
        ServiceCours s = new ServiceCours();
        int x = s.updateCours(c2);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de Modifiation !");
        alert.setContentText("Voulez-Vous Vraiment Modifer");

        Optional<ButtonType> btn = alert.showAndWait();
        if (x > 0) {
            JOptionPane.showMessageDialog(null, "Cours modifié");
            this.initTable(null);

        } else {
            System.out.println("cours non modifié");

            JOptionPane.showMessageDialog(null, "cours non modifié");
        }

    }

    @FXML
    private void supprimer(ActionEvent event) {
        ServiceCours s = new ServiceCours();
        Courslm c = new Courslm();

        c = this.tableCours.getSelectionModel().getSelectedItem();
        if (c == null) {
            JOptionPane.showMessageDialog(null, "a5tar cours");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de Suppression !");
        alert.setContentText("Voulez-Vous Vraiment Supprimer");

        Optional<ButtonType> btn = alert.showAndWait();
        if (btn.get() == ButtonType.OK) {
            s.supprimerCours(c.getId());
            this.tableCours.getItems().remove(c);

            System.out.println("suppression avec succées");
        } else {
            alert.close();
        }
    }

    @FXML
    private void getSelected(MouseEvent event) {
        Courslm c = tableCours.getSelectionModel().getSelectedItem();
        CategorieServices categorieServices = new CategorieServices();
        if (c == null) {
            return;
        }
        //id.setText(String.valueOf(c.getId()));
        imem.setText(String.valueOf(c.getMembre_id()));
        inom.setText(c.getNomCours());
        combocat.setValue(categorieServices.getCategorieById(c.getCategorie_id()));
        Date d = c.getDate_creation();
        System.out.println(d);
        idate.setValue(LocalDate.of(d.getYear(), d.getMonth(), d.getDay()));
        icl.setText(c.getTags());
        ides.setText(c.getDescription());
        inb.setText(String.valueOf(c.getNb_participants()));
        file = new File(Env.getImagePath() + "cours\\" + c.getImagename());
        imagevc.setImage(c.getImage().getImage());
        lien.setText(c.getLienYoutube());
        //inb.setText(img.getCellData(index).toString());
    }

    @FXML
    private void clean() {
        inom.setText(null);
        //id.setText(null);
        combocat.getSelectionModel().select(null);
        idate.setValue(null);
        imem.setText(null);
        inb.setText(null);
        ides.setText(null);
        icl.setText(null);
        imagevc.setImage(null);
        lien.setText(null);
    }

    @FXML
    private void searchCours(KeyEvent event) {
        ServiceCours s = new ServiceCours();

        initTable(s.searchCours(search.getText()));
    }

    @FXML
    private void trierC(ActionEvent event) {
        ServiceCours s = new ServiceCours();
        initTable(s.trierCoursID());

        //ObservableList<Courslm> list = FXCollections.observableArrayList(s.trierCoursID());
        //String nomImage = moveImage();
        //tableCours.setItems(list);
    }

    private boolean verifnom() {
        verifnom.setStyle("-fx-text-fill: white; ");
        if (inom.getText().isEmpty()) {
            verifnom.setText("Veuillez remplir ce champs");
            return true;
        } else if (inom.getText().length() > 20) {
            verifnom.setText("Nom trop long");
            return true;
        } else {
            verifnom.setText("");
            return false;
        }
    }

    private boolean verifmo() {
        veriftag.setStyle("-fx-text-fill: white; ");
        if (icl.getText().isEmpty()) {
            veriftag.setText("Veuillez remplir ce champs");
            return true;

        } else {
            veriftag.setText("");
            return false;
        }
    }

    private boolean verifnb() {

        verifnb.setStyle("-fx-text-fill: white; ");
        int x = Integer.parseInt(inb.getText());
        if (inb.getText().isEmpty()) {
            verifnb.setText("Veuillez remplir ce champs");
            return true;

        } else {
            verifnb.setText("");
            return false;
        }
    }

    private boolean verifdate() {
        verifdate.setStyle("-fx-text-fill: white; ");

        if (idate.getValue() == null) {

            verifdate.setText("Veuillez remplir ce champs");
            return true;
        } else {
            verifdate.setText("");
            return false;
        }
    }

    private boolean verifmem() {
        verifmem.setStyle("-fx-text-fill: white; ");
        if (imem.getText().isEmpty()) {
            verifmem.setText("Veuillez remplir ce champs");
            return true;
        } else {
            verifmem.setText("");
            return false;
        }
    }

    private boolean verifdes() {
        verifdes.setStyle("-fx-text-fill: white; ");
        if (ides.getText().isEmpty()) {
            verifdes.setText("Veuillez remplir ce champs");
            return true;
        } else {
            verifdes.setText("");
            return false;
        }
    }

    private boolean verifcat() {
        verifcat.setStyle("-fx-text-fill: white; ");
        if (combocat.getItems().isEmpty()) {
            verifcat.setText("Veuillez remplir ce champs");
            return true;
        } else {
            verifcat.setText("");
            return false;
        }
    }
    
    private boolean verifYouUrl(){
        
        verifcat.setStyle("-fx-text-fill: white; ");
        if (!lien.getText().matches("^https://www.youtube.com/watch?v=")) {
            verifcat.setText("Lien invalid");
            return true;
        } else {
            verifcat.setText("");
            return false;
        }
    }

    @FXML
    private void ajouterim(ActionEvent event) {
        //nakhtar fichier
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", ".png", ".jpg", ".jpeg"));

        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            image = file.getAbsolutePath();
            Image imageForFile;
            try {
                imageForFile = new Image(file.toURI().toURL().toExternalForm());
                imagevc.setImage(imageForFile);
            } catch (MalformedURLException ex) {
                Logger.getLogger(CoursDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            alert.setText("veuillez insere une image");
        }

    }

    private String moveImage() {
        if (file != null) {
            try {
                String fileName = file.getName();
                int doitIndex = fileName.lastIndexOf(".");
                String imageName = fileName.substring(0, doitIndex) + new java.util.Date().getTime() + "." + fileName.substring(doitIndex + 1);
                String imageNameTodb = Env.getImagePath() + "cours\\" + imageName;
                File dest = new File(imageNameTodb);
                Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                return imageName;

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return "";
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
                Courslm addCours = new Courslm(e.getId(), e.getNomCours(), e.getDescription(), e.getNb_participants(), e.getMembre_id(), e.getDate_creation(), e.getTags(), i, e.getCategorie_id(), cs.getCategorieById(e.getCategorie_id()).getNomcat());
                addCours.setImagename(e.getImage());
                addCours.setLienYoutube(e.getLienYoutube());
                lIm.add(addCours);
            } catch (MalformedURLException ex) {
                Logger.getLogger(CoursDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        //System.out.println("ev " + lIm);

        ObservableList<Courslm> listCoursIm = FXCollections.observableArrayList(lIm);

        tableCours.setItems(listCoursIm);
    }

    private HashMap<String, String> getVideoDetails(String url) {
            HashMap<String, String> hashMap = new HashMap();
            int idpos=url.indexOf("=")+1;
            String id=url.substring(idpos, idpos+11);
            System.out.println(id);
        try {
            HashMap<String, String> headers = new HashMap();
            HttpURLConnection conn = ApiCall.callApi("https://youtube.googleapis.com/youtube/v3/videos?part=snippet%2CcontentDetails%2Cstatistics&id="+id+"&key="+Env.getYoutubeApiKey(), headers);
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String objctDetails=br.lines().collect(Collectors.joining(" "));
            JSONObject obj = new JSONObject(objctDetails);
            JSONArray details = obj.getJSONArray("items");
            if(details.length()==0){
                System.out.println("Check your video ");
                return null;
            }
            
            JSONObject snippet=details.getJSONObject(0).getJSONObject("snippet");
            
            String title=snippet.getString("title");
            String descirption=snippet.getString("description");
            JSONArray tagsjson=snippet.getJSONArray("tags");
            
            String tags="";
            for (int i = 0; i < tagsjson.length(); i++) {
                tags+=tagsjson.get(i);
            }
            
            System.out.println("Title : "+title);
            System.out.println("descirption : "+descirption);
            System.out.println("tags : "+tags);
            
        } catch (IOException ex) {
            Logger.getLogger(CoursDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return hashMap;
    }

    @FXML
    private void getInformationsYoutube(ActionEvent event) {
    }
}
