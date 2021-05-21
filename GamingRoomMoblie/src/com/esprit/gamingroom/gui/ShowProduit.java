/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.gamingroom.entities.Produit;
import com.esprit.gamingroom.services.ServicePanier;
import com.esprit.gamingroom.utils.Statics;

/**
 *
 * @author yasmine
 */
public class ShowProduit extends Form {

    Form current;

    public ShowProduit(Form previous, Produit produit) {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Planning Details");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        int deviceWidth = Display.getInstance().getDisplayWidth();
        Image placeholder = Image.createImage(deviceWidth, deviceWidth, 0xbfc9d2);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        Image image = URLImage.createToStorage(encImage, produit.getLibelle() + produit.getIdprod(), Statics.UPLOAD_IMAGE + produit.getImage(), URLImage.RESIZE_SCALE);
        ImageViewer imageViewer = new ImageViewer();
        imageViewer.setImage(image.fill(deviceWidth, deviceWidth));
        Label titreLabel = new Label("#" + produit.getIdprod() + "  " + produit.getLibelle());
        Label typeLabel = new Label("Prix: " + produit.getPrix() + "DT");
        SpanLabel salleLabel = new SpanLabel("Description: " + produit.getDescription());
        Button buyButton = new Button("Buy Product");
        buyButton.addActionListener(evt -> {
            ToastBar.getInstance().setPosition(BOTTOM);
            ToastBar.Status status = ToastBar.getInstance().createStatus();
            status.setShowProgressIndicator(true);
            status.setMessage("Article ajouté avec succès");
            status.setExpires(10000);  // only show the status for 3 seconds, then have it automatically clear

            status.show();

            refreshTheme();

            ServicePanier.getInstance().addProduitPanier(produit.getIdprod(), 24);
            new ShowPanier(previous).show();

        });

        //Email
        Button btnEmail = new Button("Envoyer Mail");
        btnEmail.setUIID("actionButton");
        btnEmail.addActionListener(email -> {
            Message m = new Message("");
            m.getAttachments().put("Bienvenue Monsieur ! Que voulez vous ajouter? ", "text/plain");
//m.getAttachments().put(imageAttachmentUri, "image/png");
            Display.getInstance().sendMessage(new String[]{"minecraft@gmail.com"}, "Bienvenue Monsieur ! Que voulez vous ajouter", m);
        });

        addAll(imageViewer, titreLabel, typeLabel, salleLabel, buyButton, btnEmail); //ADD ALL COMPONENTS TO THE VIEW
        /* *** *BACK BUTTON* *** */
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        /* *** *OVERFLOW MENU* *** */
        getToolbar().addCommandToOverflowMenu("Share", null, (evt) -> {
            Display.getInstance().sendMessage(new String[]{""}, "Join Me !", new Message("Check out this product: " + produit.getLibelle() + " it's for: " + produit.getPrix() + "DT"));
        });

    }
}
