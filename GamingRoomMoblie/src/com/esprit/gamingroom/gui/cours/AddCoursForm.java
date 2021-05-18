/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui.cours;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.esprit.gamingroom.entities.Cours;
import com.esprit.gamingroom.services.CoursServices;

/**
 *
 * @author eyatr
 */
public class AddCoursForm extends Form{
     public AddCoursForm(){
        this.setTitle("Ajouter un cours");
        this.setLayout(BoxLayout.y());
        
        
        TextField tfname = new TextField("", "Insérer nom du cours");
        TextField tfdescription = new TextField("","Insérer la déscription");
        TextField tfnbp = new TextField("", "Insérer le nombre des participants");
        TextField tfmem = new TextField("", "Insérer le membre");
        //add(new Label("Date Creation"));
         Picker tfdate = new Picker();
         tfdate.setType(Display.PICKER_TYPE_DATE);
         TextField tftags = new TextField("", "Insérer les mots clés");
        TextField tfimg = new TextField("", "Inserer l'image");
        TextField tfcat = new TextField("", "Choisir la catégorie");
        TextField tfyoutube = new TextField("", "Insérer le lien youtube");
        
        Button submitTaskBtn = new Button("Confirmer");
        submitTaskBtn.addActionListener((evt) -> {
            
            if (tfname.getText().length() ==0 || tfdescription.getText().length()==0) {
                Dialog.show("Alert", "Textfields cannot be empty.",null, "OK");
            }else {
                
                try {
                
                Cours cours = new Cours( tfname.getText(),tfdescription.getText(),Integer.parseInt(tfnbp.getText()),Integer.parseInt(tfmem.getText()),tftags.getText(),tfimg.getText(),Integer.parseInt(tfcat.getText()),tfyoutube.getText());
                    if (CoursServices.getInstance().addCoursAction(cours)) {
                        Dialog.show("Success", "Cours added successfully.",null, "OK");
                    }
                    
                } catch (NumberFormatException e) {
                    Dialog.show("Alert", "nb par's status must be a number.",null, "OK");
                }
                
                
                
            }
            
            
            
        });
        
        this.addAll(tfname, tfdescription,tfnbp,tfmem,tfdate,tftags,tfimg,tfcat,tfyoutube, submitTaskBtn);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeForm().showBack());
        
        
    }
    
}
