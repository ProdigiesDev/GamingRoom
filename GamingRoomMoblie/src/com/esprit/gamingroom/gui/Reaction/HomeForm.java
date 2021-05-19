/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui.Reaction;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import com.esprit.gamingroom.entities.Avis;
import com.esprit.gamingroom.entities.Membre;
import com.esprit.gamingroom.entities.Reclamation;
import com.esprit.gamingroom.services.ReclamationService;
import com.esprit.gamingroom.services.AvisService;

/**
 *
 * @author Dah
 */
public class HomeForm extends Form{

    public HomeForm() {
        this.setTitle("Accueil");
        this.setLayout(BoxLayout.y());
        this.add(avisCont());
        this.add(recCont());
    }
    
    private Container avisCont(){
        
        Container container=new Container(new GridLayout(3,2));
        TextArea avi=new TextArea("");
        avi.setRows(5);
        Button addBtn=new Button("Envoyer");
        addBtn.addActionListener(l->{
            AvisService as=new AvisService();
            Membre m=new Membre();
            String avisTxt=avi.getText();
            if(avisTxt.length()< 20){
                
                Dialog.show("Validation", "Votre avis doit etre au minimum 20 character", "OK","");
                
                return;
            }
            
            m.setId(60);
            if(as.add(new Avis(m , avi.getText()))){
                
                Dialog.show("Info", "Merci pour votre Avis", "OK","");
                avi.setText("");
            }
            else 
                Dialog.show("Error", "Erreur est survenue", "OK","");
        });
        container.addAll(new Label(),new Label("Votre Avis"), new Label("Message"), avi,new Label(),addBtn);
        
        return container;
    }
    
     private Container recCont(){
        TextModeLayout tl = new TextModeLayout(3, 2);
        Container container=new Container(tl);
        TextComponent sujet = new TextComponent().label("Sujet");
        Validator val = new Validator();
        val.addConstraint(sujet, new LengthConstraint(5));
        TextComponent cont = new TextComponent().label("Contenue").multiline(true);
        val.addConstraint(cont, new LengthConstraint(20));
        Button addBtn=new Button("Envoyer");
        addBtn.addActionListener(l->{
            ReclamationService as=new ReclamationService();
            Membre m=new Membre();
            if(!val.isValid()){
                
                Dialog.show("Validation", "Contenue doit etre au minimum 20 character et Sujet au minimum 5 character ", "OK","");
                return;
            }
            
            String contTxt=cont.getText();
            String sujTxt=sujet.getText();
         
            m.setId(60);
            if(as.add(new Reclamation( sujTxt,contTxt,m))){
                Dialog.show("Info", "Réclamation recu", "OK","");
                cont.text("");
                sujet.text("");
            }
            else 
                Dialog.show("Error", "Erreur est survenue", "OK","");
        });
        container.addAll(new Label(),new Label("Réclamation"),sujet,cont,new Label(),addBtn);
        
        return container;
    }
    
}
