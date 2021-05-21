/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.gamingroom.Home;
import com.esprit.gamingroom.entities.Produit;
import com.esprit.gamingroom.services.ServicePanier;
import java.util.ArrayList;

/**
 *
 * @author yasmine
 */
public class ShowPanier extends Form {

    Form current;

    public ShowPanier(Form previous) {
        super(new BoxLayout(BoxLayout.Y_AXIS));
        /* *** *CONFIG SCREEN* *** */
        this.add(new Label("votre panier :"));
        ArrayList<Produit> listProduits = ServicePanier.getInstance().findpanierbyproduit(61);

        int quantiteM = 0;
        int quantiteF = 0;
        boolean isAffichéF = true;
        boolean isAffichéM = true;
        
        int somme = 0;

        for (Produit p : ServicePanier.getInstance().findpanierbyproduit(61)) {
            if (p.getLibelle().equals("Fortnite")) {
                quantiteF++;
            }
            if (p.getLibelle().equals("Minecraft")) {
                quantiteM++;
            }
        }

        for (Produit p : ServicePanier.getInstance().findpanierbyproduit(61)) {

            if (p.getLibelle().equals("Fortnite") && isAffichéF) {
                this.addAll(new Label(p.getLibelle() + " / Prix Unité :" + p.getPrix()+" DT" + " / Quantité : " + quantiteF));

                somme += p.getPrix() * quantiteF;
                
                this.add(new Label("Prix total pour Fortnite : " + (p.getPrix()*quantiteF)+" DT"));
            }
            if (p.getLibelle().equals("Minecraft") && isAffichéM) {
                this.addAll(new Label(p.getLibelle() + " / Prix Unité :" + p.getPrix()+" DT" + " / Quantité : " + quantiteM));

                    somme += p.getPrix() * quantiteM;
                    
                this.add(new Label("Prix total pour Minecraft : " + (p.getPrix()*quantiteM) +" DT"));
            }

            if (p.getLibelle().equals("Fortnite")) {
                isAffichéF = false;
            }
            if (p.getLibelle().equals("Minecraft")) {
                isAffichéM = false;
            }
            
            

        }
        this.add(new Label("-------------------------------"));
            this.add(new Label("Prix total : "+somme+" DT"));

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ProduitForm().show());
    }
}
