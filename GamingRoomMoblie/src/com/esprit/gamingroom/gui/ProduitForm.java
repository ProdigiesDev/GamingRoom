/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.gamingroom.entities.Produit;
import com.esprit.gamingroom.gui.ShowProduit;
import com.esprit.gamingroom.services.ProduitService;
import com.esprit.gamingroom.utils.Statics;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author yasmine
 */
public class ProduitForm extends Form {

    Form current;
    ArrayList<Produit> produitArrayList = new ArrayList<>();
    ProduitService produitService = new ProduitService();

    public ProduitForm(Form previous) {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Product List");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        produitArrayList = produitService.AffichageProduit();
        showProduit();
        
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        /* *** *SEARCHBAR* *** */
        getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                            || line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);

                }
                getContentPane().animateLayout(150);
            }
        }, 4);
        /* *** *OVERFLOW MENU* *** */
        getToolbar().addCommandToOverflowMenu("Sort by Libelle", null, (evt) -> {
            removeAll();
            Collections.sort(produitArrayList, Produit.libelleComparator);
            showProduit();
        });
    }

    private void showProduit() {
        for (Produit produit : produitArrayList) {
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage(deviceWidth / 3, deviceWidth / 4, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            Image image = URLImage.createToStorage(encImage, produit.getLibelle()+ produit.getIdprod(), Statics.UPLOAD_IMAGE + produit.getImage(), URLImage.RESIZE_SCALE);
                 MultiButton multiButton = new MultiButton();
            multiButton.setTextLine1("#" + produit.getIdprod() + " " + produit.getLibelle());
            multiButton.setTextLine2("PRIX: " + produit.getPrix()+"DT");
            multiButton.setUIID(produit.getIdprod() + "");
            multiButton.setIcon(image);
            multiButton.setEmblem(FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_RIGHT, "", 10.0f));
            multiButton.addActionListener(l -> new ShowProduit(current, produit).show());
            // container.add(multiButton);
            add(multiButton);
        }
    }

}
