
  
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.pdfjet.A4;
import com.pdfjet.Cell;
import com.pdfjet.CoreFont;
import com.pdfjet.Font;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import com.pdfjet.Table;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Categorie;
import tn.gamingroom.entities.ImageProduits;
import tn.gamingroom.entities.Produits;
import tn.gamingroom.outils.Env;
import tn.gamingroom.outils.MyConnection;
import tn.gamingroom.services.CategorieServices;
import tn.gamingroom.services.ProduitCrud;

/**
 * FXML Controller class
 *
 * @author yasmine
 */
public class AjoutProduitController implements Initializable {

    @FXML
    private JFXTextField tflibelle;
    @FXML
    private JFXTextField tfprix;
    @FXML
    private TableView<ImageProduits> tvbox;
    @FXML
    private TableColumn<ImageProduits, Integer> colid;
    @FXML
    private TableColumn<ImageProduits, ImageView> colimage;
    @FXML
    private TableColumn<ImageProduits, String> collibelle;
    @FXML
    private TableColumn<ImageProduits, Double> colprix;
    @FXML
    private TableColumn<ImageProduits, String> coldesc;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupp;
    private JFXTextField tfdesc;
    @FXML
    private Button btnTri;
    @FXML
    private JFXTextField tfid;
    @FXML
    private Button btnannuler;
    @FXML
    private Button btnBest;
    @FXML
    private TextField tfrech;
    @FXML
    private Button btnload;
    @FXML
    private ComboBox<Categorie> listCat;
    @FXML
    private TableColumn<ImageProduits, String> colcat;
    private FileChooser fileChooser;
    private File file;
    Stage stage;
    @FXML
    private Button imagePath;
    @FXML
    private ImageView prodImage;
    @FXML
    private JFXTextArea textareaProd;
    @FXML
    private JFXTextField MaxPrix;
    @FXML
    private JFXTextField MinPrix;
    @FXML
    private Button btnSearchPrix;
    @FXML
    private Button btnpdf;
    private ImageView exit;
    private ImageView reduir;
    @FXML
    private AnchorPane DashProduit;
    @FXML
    private ImageView btnexit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tflibelle.setStyle("-fx-text-fill: white;");
        tfprix.setStyle("-fx-text-fill: white;");
        textareaProd.setStyle("-fx-text-fill: white;");
        tfid.setStyle("-fx-text-fill: white;");

        colid.setCellValueFactory(new PropertyValueFactory<ImageProduits, Integer>("idprod"));
        colcat.setCellValueFactory(new PropertyValueFactory<ImageProduits, String>("nomCat"));
        colimage.setCellValueFactory(new PropertyValueFactory<ImageProduits, ImageView>("image"));
        collibelle.setCellValueFactory(new PropertyValueFactory<ImageProduits, String>("libelle"));
        colprix.setCellValueFactory(new PropertyValueFactory<ImageProduits, Double>("prix"));
        coldesc.setCellValueFactory(new PropertyValueFactory<ImageProduits, String>("description"));

        // TODO
        CategorieServices crud = new CategorieServices();
        List<Categorie> categories = crud.DisplayCategorie();
        Callback<ListView<Categorie>, ListCell<Categorie>> factory = lv -> new ListCell<Categorie>() {

            @Override
            protected void updateItem(Categorie item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNomcat());
            }

        };
        listCat.setCellFactory(factory);
        listCat.setButtonCell(factory.call(null));
        listCat.setItems(FXCollections.observableArrayList(categories));

        initTable(null);
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg"));
    }

    @FXML
    private void ajouterProduit(ActionEvent event) throws MessagingException, IOException {

        //  Save produit IN DATABASE
        String resImage = "";
        String resLibelle = tflibelle.getText();
        Categorie rescategorie = listCat.getValue();

        String resDesc = textareaProd.getText();
        double resPrix = 0;
        // controle de saisie
        try {

            resPrix = Double.parseDouble(tfprix.getText());

        } catch (Exception ex) {
            tfprix.setText("");
            JOptionPane.showMessageDialog(null, "Il faut ajouter un entier");
            return;

        }
        resPrix = Double.parseDouble(tfprix.getText());
        Produits p2 = new Produits(rescategorie.getIdcat(), resImage, resLibelle, resPrix, resDesc);
        ProduitCrud pcd = new ProduitCrud();
        String fileName = moveImage();
        p2.setImage(fileName);

        // controle de saisie
        if (tflibelle.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Il faut remplir le champ Libelle");
            return;
        }

        if (fileName.length() == 0) {
            JOptionPane.showMessageDialog(null, "Il faut choisir une image");
            return;
        }
        if (textareaProd.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Il faut remplir le champ Description");
            return;
        }

        if (pcd.ajouterProduit(p2) > 0) {

            JOptionPane.showMessageDialog(null, "produit ajouté");
            initTable(null);

        }
    }

    private String moveImage() {
        if (file != null) {
            try {
                String fileName = file.getName();
                int doitIndex = fileName.lastIndexOf(".");
                String imageName = fileName.substring(0, doitIndex) + new java.util.Date().getTime() + "." + fileName.substring(doitIndex + 1);
                String imageNameTodb = Env.getImagePath() + "produits\\" + imageName;
                File dest = new File(imageNameTodb);
                Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                return imageName;

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return "";
    }

    @FXML
    private void clean(ActionEvent event) {
        tflibelle.setText(null);
        textareaProd.setText(null);
        tfprix.setText(null);
        tfid.setText(null);
        prodImage.setImage(null);
           listCat.getSelectionModel().select(null);
    }

    @FXML
    private void bestProductsSelled(ActionEvent event) {
        ProduitCrud pcd = new ProduitCrud();
        List<Integer> integers = pcd.bestProductsSelled();
        System.out.println(integers);
        List<Produits> produitses = new ArrayList<>();
        integers.forEach(i -> {
            produitses.add(pcd.getByID(i));
        });
        initTable(produitses);

    }

    @FXML
    private void getSelected(MouseEvent event) {
        ImageProduits c = tvbox.getSelectionModel().getSelectedItem();
        int index = tvbox.getSelectionModel().getSelectedIndex();
        if (c == null) {
            return;
        }
        if (index <= -1) {

            return;

        }
CategorieServices categorieServices=new CategorieServices();
        listCat.getSelectionModel().select(categorieServices.getById(c.getId_cat()));
        tflibelle.setText(collibelle.getCellData(index).toString());
        tfprix.setText(colprix.getCellData(index).toString());
        textareaProd.setText(coldesc.getCellData(index).toString());
        tfid.setText(colid.getCellData(index).toString());
        file = new File(Env.getImagePath() + "produits\\" + c.getImagename());
        prodImage.setImage(c.getImage().getImage());
    }

    @FXML
    private void updateTable(ActionEvent event) {

        String Value1 = "";
        String Value2 = tflibelle.getText();
        double Value3 = Double.parseDouble(tfprix.getText());
        String Value4 = textareaProd.getText();
        int Value5 = Integer.parseInt(tfid.getText());
        Categorie cat = listCat.getValue();
        String nomImage = moveImage();

        if (nomImage.length() == 0) {
            JOptionPane.showMessageDialog(null, "Veuillez insérer une image");
            return;
        }
        //  Produits p2 = new Produits(Value5, 0, Value1, Value2, Value3, Value4);
        Produits p2 = new Produits(Value5, cat.getIdcat(), nomImage, Value2, Value3, Value4, cat.getNomcat());// nbadel fel id_cat nwali 

        ProduitCrud pcd = new ProduitCrud();
        int x = pcd.updateProduit(p2);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de Modifiation !");
        alert.setContentText("Voulez-Vous Vraiment Modifer");
        Optional<ButtonType> btn = alert.showAndWait();
        if (x > 0) {
            JOptionPane.showMessageDialog(null, "produit modifié");
            initTable(null);
        } else {
            System.out.println("produit non modifié");
            JOptionPane.showMessageDialog(null, "produit non modifié");
        }

    }

    @FXML
    private void supprimer(ActionEvent event) {

        ProduitCrud pcd = new ProduitCrud();
        ImageProduits p = new ImageProduits();

        p = this.tvbox.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de Suppression !");
        alert.setContentText("Voulez-Vous Vraiment Supprimer?");

        Optional<ButtonType> btn = alert.showAndWait();
        if (btn.get() == ButtonType.OK) {
            pcd.supprimerProduit(p.getIdprod());
            this.initTable(null);
            System.out.println("suppression avec succées");
        } else {
            alert.close();
        }

        clean(event);

    }

    @FXML
    private void TrierParId(ActionEvent event) {
        ProduitCrud crud = new ProduitCrud();
        initTable(crud.TrierParId());

    }

    @FXML
    private void RechercherProduit(KeyEvent event) {

        ProduitCrud crud = new ProduitCrud();

        initTable(crud.RechercherProduit(tfrech.getText()));

    }

    @FXML
    private void loadTable(ActionEvent event) {

        ProduitCrud crud = new ProduitCrud();
        initTable(null);

    }

    @FXML
    private void backToList(ActionEvent event) {

        try {
            Parent dashboard;
            dashboard = FXMLLoader.load(getClass().getResource("ADDCle.fxml"));

            Scene dashboardScene = new Scene(dashboard);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(dashboardScene);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void loadImage(ActionEvent event) {

        file = fileChooser.showOpenDialog(stage);//ykhalini nekhtar fichier
        try {
            prodImage.setImage(new Image(file.toURI().toURL().toExternalForm()));//path image (ligne mtaa file)
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void statistique(ActionEvent event) {

        try {
            Parent stat;
            stat = FXMLLoader.load(getClass().getResource("statistique.fxml"));

            Scene dashboardScene = new Scene(stat);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(dashboardScene);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void SearchPrice(ActionEvent event) {
        double maxprice;
        double minprice;

        try {

            maxprice = Double.parseDouble(MaxPrix.getText());
            minprice = Double.parseDouble(MinPrix.getText());
            double price = minprice;

            if (maxprice < minprice) {

                minprice = maxprice;
                maxprice = price;
                MaxPrix.setText(String.valueOf(maxprice));//tconverti double ->chaine
                MinPrix.setText(String.valueOf(minprice));

            }
            ProduitCrud crud = new ProduitCrud();
            initTable(crud.RechercherPrix(minprice, maxprice));

        } catch (Exception ex) {

            MaxPrix.setText("");
            MinPrix.setText("");
            JOptionPane.showMessageDialog(null, "Il faut ajouter un entier");
            return;

        }
    }

    void initTable(List<Produits> produitses) {
        ProduitCrud crud = new ProduitCrud();
        if (produitses == null) {
            produitses = crud.displayProduit();
        }
        List<ImageProduits> lIm = new ArrayList<ImageProduits>();
        produitses.forEach(e -> {

            try {
                File f = new File(Env.getImagePath() + "produits\\" + e.getImage());
                Image img = new Image(f.toURI().toURL().toExternalForm());
                ImageView i = new ImageView(img);
                i.setFitHeight(50);
                i.setFitWidth(70);
                ImageProduits imageProduits = new ImageProduits();
                imageProduits.setDescription(e.getDescription());
                imageProduits.setId_cat(e.getId_cat());
                imageProduits.setIdprod(e.getIdprod());
                imageProduits.setImage(i);
                imageProduits.setImagename(e.getImage());
                imageProduits.setLibelle(e.getLibelle());
                imageProduits.setNomCat(e.getNomCat());
                imageProduits.setPrix(e.getPrix());

                imageProduits.setImagename(e.getImage());
                lIm.add(imageProduits);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }

        });
        System.out.println("ev " + lIm);

        ObservableList<ImageProduits> listProduitIm = FXCollections.observableArrayList(lIm);
        tvbox.setItems(listProduitIm);
    }

    public void Send() throws MessagingException, IOException {
        String to = "prodigiesdev@gmail.com";
        String from = "Gamingroom.prodigiesDev@gmail.com";
        String host = "smtp.gmail.com";
        final String Username = "Gamingroom.prodigiesDev@gmail.com";
        final String Pass = "Gamingroom2020";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {

            protected PasswordAuthentication getpPasswordAuthentication() {

                return new PasswordAuthentication("Gamingroom.prodigiesDev@gmail.com", "Gamingroom2020");
            }

        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("liste produits");
            Multipart emailContent = new MimeMultipart();
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Bonjour , voila la liste des produits que vous avez ajouté!");
            MimeBodyPart pdfAttatchement = new MimeBodyPart();
            pdfAttatchement.attachFile("C:/Users/yasmine/Desktop/pidev/GamingRoomDesktop/produits.pdf");
            emailContent.addBodyPart(textBodyPart);
            emailContent.addBodyPart(pdfAttatchement);
            message.setContent(emailContent);
            Transport.send(message, Username, Pass);
            // System.out.println("Sent message successfully....");
            JOptionPane.showMessageDialog(null, "Sent message successfully....");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public void PDF() throws FileNotFoundException, Exception {
        File out = new File("produits.pdf");
        try (FileOutputStream fos = new FileOutputStream(out)) {
            PDF pdf = new PDF(fos);
            Page page = new Page(pdf, A4.PORTRAIT);
            Font f1 = new Font(pdf, CoreFont.HELVETICA_BOLD);
            Font f2 = new Font(pdf, CoreFont.HELVETICA);

            Table table = new Table();
            List<List<Cell>> tableData = new ArrayList<>();
            List<Cell> tableRow = new ArrayList<>();

            Cell cell = new Cell(f1, colid.getText());
            tableRow.add(cell);

//            cell = new Cell(f1,colimage.getText() );
//            tableRow.add(cell);
//            
            cell = new Cell(f1, collibelle.getText());
            tableRow.add(cell);

            cell = new Cell(f1, colprix.getText());
            tableRow.add(cell);

            cell = new Cell(f1, coldesc.getText());
            tableRow.add(cell);

            cell = new Cell(f1, colcat.getText());
            tableRow.add(cell);

            tableData.add(tableRow);

            List<ImageProduits> items = tvbox.getItems();
            for (ImageProduits prod : items) {
                Cell IDProd = new Cell(f2, String.valueOf(prod.getIdprod()));
                //  Cell ImageProd = new Cell(f2, prod.getImagename());
                Cell libProd = new Cell(f2, prod.getLibelle());
                Cell PrixProd = new Cell(f2, String.valueOf(prod.getPrix()));
                Cell DescProd = new Cell(f2, prod.getDescription());
                Cell CatProd = new Cell(f2, prod.getNomCat());

                tableRow = new ArrayList<>();
                tableRow.add(IDProd);
                // tableRow.add(ImageProd);
                tableRow.add(libProd);
                tableRow.add(PrixProd);
                tableRow.add(DescProd);
                tableRow.add(CatProd);
                tableData.add(tableRow);
            }
            table.setData(tableData);
            table.setPosition(10f, 60f);
            table.setColumnWidth(0, 40f);

            table.setColumnWidth(1, 150f);
            table.setColumnWidth(2, 50f);
            table.setColumnWidth(3, 250f);
            table.setColumnWidth(4, 80f);
            while (true) {
                table.drawOn(page);
                if (!table.hasMoreData()) {
                    table.resetRenderedPagesCount();
                    break;

                }
                page = new Page(pdf, A4.PORTRAIT);
            }
            pdf.flush();
            JOptionPane.showMessageDialog(null, "PDF enregistré sous le path " + out.getAbsolutePath());
            System.out.println("Saved to " + out.getAbsolutePath());
        }
    }

    @FXML
    private void enregistrerPDF(ActionEvent event) throws Exception {

        PDF();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Mailing");
        alert.setHeaderText("Confirmation de mailing!");
        alert.setContentText("Voulez-Vous Vraiment envoyer un email?");
        Optional<ButtonType> btn = alert.showAndWait();
        if (btn.get() == ButtonType.OK) {

            Send();

        }

    }

//    @FXML
//    private void click(MouseEvent event) {
//        if (event.getSource().equals(btnexit)) 
//        {
//            System.exit(0);
//        }
//        
////       if (event.getSource().equals(btnReduce)){
////          Stage stage = (Stage) AdminDashBoardPane.getScene().getWindow();
////
////          stage.setIconified(true);
////        } 
////        
//        
//        
//    }
}