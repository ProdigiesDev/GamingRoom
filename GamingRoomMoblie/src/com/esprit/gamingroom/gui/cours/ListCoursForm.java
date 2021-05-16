/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui.cours;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.gamingroom.services.CoursServices;

/**
 *
 * @author eyatr
 */
public class ListCoursForm extends Form {
    
    public ListCoursForm(){
    this.setTitle("Liste des cours");
        this.setLayout(BoxLayout.y());
        
        SpanLabel coursListSP = new SpanLabel();
        coursListSP.setText(CoursServices.getInstance().getCours().toString());
        
        this.add(coursListSP);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeForm().showBack());
}
}
