/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui.Reaction;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.table.TableLayout;
import com.esprit.gamingroom.services.AvisService;
import com.esprit.gamingroom.entities.Avis;
import java.util.ArrayList;

/**
 *
 * @author Dah
 */
public class AvisListAdminForm  extends Form{

    public AvisListAdminForm() {
        
        this.setTitle("List Avis");
        AvisService as=new AvisService();
        ArrayList<Avis> avises=as.getAvis();
        this.setLayout(new TableLayout(avises.size(),5));
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new AdminHomeForm().showBack());

        this.addAll(new Label("id"),new Label("Membre"),new Label("Avis"),new Label("Sentiment"),new Label("Action"));
        for (int i = 0; i < avises.size(); i++) {
            Avis a=avises.get(i);
            this.add(new Label(String.valueOf(a.getId())));
            this.add(new Label(a.getMembre().getNom()+" "+a.getMembre().getPrenom()));
            this.add(new Label(a.getAvis()));
            this.add(new Label(a.getSentiment()));
            Button b=new Button("Details");
            b.addActionListener(l->{
                new AvisDetailsForm(a).show();
            });
            this.add(b);
        }
    }
    
}
