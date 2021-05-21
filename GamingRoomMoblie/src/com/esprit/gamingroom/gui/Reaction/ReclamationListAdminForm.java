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
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.util.Resources;
import com.esprit.gamingroom.entities.Reclamation;
import com.esprit.gamingroom.services.AvisService;
import com.esprit.gamingroom.services.ReclamationService;
import java.util.ArrayList;

/**
 *
 * @author Dah
 */
public class ReclamationListAdminForm extends Form{
    private ArrayList<Reclamation> reclamations;
    private ReclamationService as=new ReclamationService();
    public ReclamationListAdminForm(Resources res) {
        this.setTitle("List Reclamation");
        reclamations=as.getRec();
        this.setLayout(new TableLayout(reclamations.size(),5));
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new AdminHomeForm(res).showBack());

        initTable();
        
    }
    int i;
    private void initTable(){
        this.addAll(new Label("id"),new Label("Membre"),new Label("Sujet"),new Label("Contenue"),new Label("Action"));
        int size=reclamations.size();
        if(size==0){
            this.add(new Label());
            this.add(new Label("il n'y a pas de données"));
            this.add(new Label());
        }
        else
        for ( i= 0; i < size; i++) {
            Reclamation a=reclamations.get(i);
            this.add(new Label(String.valueOf(a.getId())));
            this.add(new Label(a.getMembre().getNom()+" "+a.getMembre().getPrenom()));
            this.add(new Label(a.getSujet()));
            this.add(new Label(a.getContenue()));
            Button b=new Button("Supprimer");
            b.addActionListener(l->{
                 if (Dialog.show("Confirm", "Voulez-vous poursuivre?", "OK", "Annuler")) {
                if(as.delete(a.getId())){
                    reclamations.remove(i-1);
                    initTable();
                    
                }
                else
                    Dialog.show("Error", "Erreur est survenue", "OK","");
                
            }
            });
            this.add(b);
        }
    }
    
    
}
