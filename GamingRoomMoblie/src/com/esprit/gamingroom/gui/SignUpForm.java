/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui;

import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.DateSpinner;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.esprit.gamingroom.entities.Membre;
import com.esprit.gamingroom.services.MembreService;


/**
 *
 * @author Sonia
 */
public class SignUpForm extends Form {
    MembreService us;
    Membre m;
    public SignUpForm(Resources res){
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        this.us = MembreService.getInstance();
        setUIID("SignUpForm");

        getTitleArea().setUIID("Container");
        TextField nom = new TextField("", "Nom", 50, TextField.ANY);
        TextField prenom = new TextField("", "Prenom", 15, TextField.ANY);
        Picker p=new Picker();
        p.setType(Display.PICKER_TYPE_DATE);
        //DatePicker dateN = new DatePicker();
        ComboBox genre = new ComboBox();
        genre.addItem("Homme");
        genre.addItem("Femme");
        
         nom.getAllStyles().setMargin(LEFT, 0);
         prenom.getAllStyles().setMargin(LEFT, 0);
         p.getAllStyles().setMargin(LEFT, 0);
         genre.getAllStyles().setMargin(LEFT, 0);
        
         Container by,byy;
        by = BoxLayout.encloseY(
//                spaceLabel,
                nom,
                prenom,
                genre,
                p
               
        );
       
        add(BorderLayout.NORTH, by);
        
    }
}
