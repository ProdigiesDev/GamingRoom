/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui.Reaction;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import  com.esprit.gamingroom.entities.Avis;
import com.esprit.gamingroom.services.AvisService;
import com.esprit.gamingroom.utils.Toaster;
/**
 *
 * @author Dah
 */
public class AvisDetailsForm extends Form{
    private Avis avis;

    public AvisDetailsForm(Avis avis,Resources res) {
        this.avis=avis;
        this.setTitle("Avis Details");
        this.setLayout(new GridLayout(5, 2));
        this.add(new Label("Id"));
        this.add(new Label(String.valueOf(avis.getId())));
        
        
        this.add(new Label("Avis"));
        this.add(new Label(avis.getAvis()));
        
        this.add(new Label("Sentiment"));
        this.add(new Label(avis.getSentiment()));
        
        this.add(new Label("Member"));
        this.add(new Label(avis.getMembre().getNom()+" "+avis.getMembre().getPrenom()));
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new AvisListAdminForm(res).showBack());

        this.add(new Label(""));
        Button  deleteBtn=new Button("Supprimer");
        deleteBtn.addActionListener(l->{
            if (Dialog.show("Confirm", "Voulez-vous poursuivre?", "OK", "Annuler")) {
                AvisService as=new AvisService();
                if(as.delete(avis.getId()))
                       new AvisListAdminForm(res).showBack();
                else
                    Dialog.show("Error", "Erreur est survenue", "OK","");
                
            }
        });
        
        this.add(deleteBtn);
    }
    
}
