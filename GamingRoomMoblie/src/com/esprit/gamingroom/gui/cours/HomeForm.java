/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui.cours;

import com.codename1.components.FloatingActionButton;
import com.codename1.ui.Button;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Stroke;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;

/**
 *
 * @author eyatr
 */
public class HomeForm extends Form {
    public HomeForm(){
        
        this.setTitle("Cours de Gaming");
        this.setLayout(BoxLayout.y());
        
       // FloatingActionButton addTaskBtn = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
      
                
        Button addTaskBtn = new Button("Ajouter un Cours");
//        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
//        addTaskBtn.getAllStyles().setFont(Font.createTrueTypeFont(Font.NATIVE_MAIN_LIGHT, 2f));
//                addTaskBtn.getAllStyles().setBorder(RoundBorder.create(). rectangle(true).
//        color(0xffffff).
//        strokeColor(0).
//        strokeOpacity(120).
//        stroke(borderStroke));
//                addTaskBtn.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_3D);
        Button listTasksBtn = new Button("Liste des Cours");
        //Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
//        addTaskBtn.getAllStyles().setFont(Font.createTrueTypeFont(Font.NATIVE_MAIN_LIGHT, 2f));
//                listTasksBtn.getAllStyles().setBorder(RoundBorder.create(). rectangle(true).
//        color(0xffffff).
//        strokeColor(0).
//        strokeOpacity(120).
//        stroke(borderStroke));
//                addTaskBtn.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_3D);
        
        
         addTaskBtn.addActionListener(e -> new AddCoursForm(null).show());
        listTasksBtn.addActionListener(x->new ListCoursForm(this).show());
        
        
       this.addAll( listTasksBtn,addTaskBtn);
        
    }
    
}
