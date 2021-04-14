/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

//import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tn.gamingroom.entities.Cles;

import tn.gamingroom.entities.PanierProduit;
import tn.gamingroom.entities.Panier;
import tn.gamingroom.entities.Produits;
import tn.gamingroom.services.CleService;
import tn.gamingroom.services.CommandService;
import tn.gamingroom.services.PanierService;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class PanierController implements Initializable {

    @FXML
    private TableColumn<PanierProduit, ImageView> colImage;
    @FXML
    private TableColumn<PanierProduit, String> colNom;
    @FXML
    private TableColumn<PanierProduit, Double> colPrixU;
    @FXML
    private TableColumn<PanierProduit, Integer> colQuantite;
    @FXML
    private TableColumn<PanierProduit, Double> colPrix;
    @FXML
    private TableView<PanierProduit> listTable;
    @FXML
    private Button btCom;
    private PanierService panierServie=new PanierService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PanierService panierService = new PanierService();
           colImage.setCellValueFactory(new PropertyValueFactory<PanierProduit, ImageView>("image"));
        colNom.setCellValueFactory(new PropertyValueFactory<PanierProduit, String>("nom"));
        colPrixU.setCellValueFactory(new PropertyValueFactory<PanierProduit, Double>("PrixUnitaire"));
        colQuantite.setCellValueFactory(new PropertyValueFactory<PanierProduit, Integer>("quantite"));
        colPrix.setCellValueFactory(new PropertyValueFactory<PanierProduit, Double>("Prix"));
        addButtonToTable();
        initTable();
    }

    private void addButtonToTable() {
        TableColumn<PanierProduit, Void> colBtnPlus = new TableColumn("");
        Callback<TableColumn<PanierProduit, Void>, TableCell<PanierProduit, Void>> cellFactory2 = new Callback<TableColumn<PanierProduit, Void>, TableCell<PanierProduit, Void>>() {
            @Override
            public TableCell<PanierProduit, Void> call(final TableColumn<PanierProduit, Void> param) {
                final TableCell<PanierProduit, Void> cell = new TableCell<PanierProduit, Void>() {

                    private final Button btn = new Button("+");

                    {
                        btn.setTextFill(Paint.valueOf("#f8f7f7"));
                        btn.setStyle("-fx-background-color: #6f1075");
                        btn.setOnAction(event -> {
                            PanierProduit data = getTableView().getItems().get(getIndex());
                            int qt = data.getQuantite() + 1;
                            CleService cleService = new CleService();
                            List<Cles> cleses = cleService.Rechercher_Produit_ID(data.getId());
                            if (qt > cleses.size()) {

                                JOptionPane.showMessageDialog(null, "Vous avez dépassé la quantité disponible!");
                                data.setQuantite(cleses.size());
                                initTable();
                                return;
                            }

                            Panier panier = new Panier();
                            panier.setId(data.getIdpanier());
                            panier.setQuantityDemande(qt);
                            panierServie.modifierQuantity(panier);
                                initTable();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtnPlus.setCellFactory(cellFactory2);

        TableColumn<PanierProduit, Void> colBtnM = new TableColumn("");
        Callback<TableColumn<PanierProduit, Void>, TableCell<PanierProduit, Void>> cellFactory3 = new Callback<TableColumn<PanierProduit, Void>, TableCell<PanierProduit, Void>>() {
            @Override
            public TableCell<PanierProduit, Void> call(final TableColumn<PanierProduit, Void> param) {
                final TableCell<PanierProduit, Void> cell = new TableCell<PanierProduit, Void>() {

                    private final Button btn = new Button("-");

                    {
                        btn.setTextFill(Paint.valueOf("#f8f7f7"));
                        btn.setStyle("-fx-background-color: #6f1075");
                        btn.setOnAction(event -> {
                            PanierProduit data = getTableView().getItems().get(getIndex());
                            int qt = data.getQuantite() - 1;
                            CleService cleService = new CleService();
                            List<Cles> cleses = cleService.Rechercher_Produit_ID(data.getId());

                            if (qt <0 &&  cleses.size()>0) {

                                data.setQuantite(1);
                                initTable();
                                return;
                            }
                                else if(qt <0 && cleses.size()==0){
                                 panierServie.supprimerProd(data.getId());
                                initTable();
                                 return;
                            }
                            Panier panier = new Panier();
                            panier.setId(data.getIdpanier());
                            panier.setQuantityDemande(qt);
                            panierServie.modifierQuantity(panier);
                                initTable();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtnM.setCellFactory(cellFactory3);
        
        
//        -------------------------------------------------------
        TableColumn<PanierProduit, Void> colBtn = new TableColumn("");
        Callback<TableColumn<PanierProduit, Void>, TableCell<PanierProduit, Void>> cellFactory = new Callback<TableColumn<PanierProduit, Void>, TableCell<PanierProduit, Void>>() {
            @Override
            public TableCell<PanierProduit, Void> call(final TableColumn<PanierProduit, Void> param) {
                final TableCell<PanierProduit, Void> cell = new TableCell<PanierProduit, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setTextFill(Paint.valueOf("#f8f7f7"));
                        btn.setStyle("-fx-background-color: #6f1075");
                        btn.setOnAction(event -> {
                            PanierProduit data = getTableView().getItems().get(getIndex());
                            PanierService panierService = new PanierService();
                            panierService.supprimerProd(data.getIdpanier());
                            initTable();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        listTable.getColumns().addAll(colBtnPlus,colBtnM, colBtn);

    }

    @FXML
    private void ajouterCommande(ActionEvent event) throws MessagingException, IOException {
        JFrame f = new JFrame();
        int a = JOptionPane.showConfirmDialog(f, "Êtes-vous sûr?");
        if (a == JOptionPane.YES_OPTION) {
            CommandService commandService = new CommandService();
            int memberId = 3;
            double totale=0; 
            for (int i = 0; i < listTable.getItems().size(); i++) {
                
                totale+=listTable.getItems().get(i).getPrix();
            }
            int nb = commandService.confirmerCommande(memberId,totale);
            if (nb == 0) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la confirmation de votre commande");
            } else {
                
                Send("alaa.smeti@esprit.tn");
                JOptionPane.showMessageDialog(null, "Votre commande est en cours de traitement");
                listTable.getItems().clear();
            }
        }
    }

    private void ajouterProduit(ActionEvent event) throws MessagingException, IOException {
        JFrame f = new JFrame();
        PanierService panierService = new PanierService();
        int memberId = 3;
//         int nb=panierService.ajouterProd(idpanier);
//          if(nb==0)
//          {
//            JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du produit au panier!");
//          }
//          else{
//              
//            JOptionPane.showMessageDialog(null, "Votre produit est ajouté");
//            listTable.getItems().clear();
//          }
//        
//         Send();
    }

    void initTable() {
        PanierService ps = new PanierService();
        List<Panier> paniers = ps.consulterPanier(3);
        List<PanierProduit> panierProduits = new ArrayList<>();
        paniers.forEach(p -> {
            Produits p1 = ps.getProductById(p.getProduit_id());
            ImageView imageView = new ImageView(new Image(p1.getImage()));
            imageView.setFitHeight(200);
            imageView.setFitWidth(250);
            panierProduits.add(new PanierProduit(p1.getIdprod(), p.getId(), p1.getLibelle(), p.getQuantityDemande(), imageView, (double) (p1.getPrix() * p.getQuantityDemande()), (double) p1.getPrix()));

        });
        ObservableList<PanierProduit> list = FXCollections.observableArrayList(panierProduits);
     
        btCom.setVisible(!panierProduits.isEmpty());
        listTable.setItems(list);
    }

    public void Send(String to) throws MessagingException, IOException, AddressException {
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
            Message message = (Message) new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Commande ");
            Multipart emailContent = new MimeMultipart();
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("commande ajoutée avec succée!");
            MimeBodyPart pdfAttatchement = new MimeBodyPart();
//            pdfAttatchement.attachFile("C:/Users/yasmine/Desktop/pidev/GamingRoomDesktop/produits.pdf");
            emailContent.addBodyPart(textBodyPart);
//            emailContent.addBodyPart(pdfAttatchement);
            message.setContent(emailContent);
            Transport.send(message, Username, Pass);
            // System.out.println("Sent message successfully....");
            JOptionPane.showMessageDialog(null, "Sent message successfully....");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
