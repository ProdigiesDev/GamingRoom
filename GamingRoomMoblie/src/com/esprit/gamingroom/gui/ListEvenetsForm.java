/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.services;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;

/**
 *
 * @author Farah
 */
public class ListEvenetsForm extends Form {
    
    public ListEvenetsForm(Form previous){
        setTitle("Liste des evenement");
        
        SpanLabel sp=new SpanLabel();
        sp.setText(ServiceEvenement.getInstance().getAllEvenets().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }
    
}
