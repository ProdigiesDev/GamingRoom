/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui.cours;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author eyatr
 */
public class HomeForm extends Form {
    public HomeForm(){
        
        this.setTitle("Cours de Gaming");
        this.setLayout(BoxLayout.y());
        
        Button addTaskBtn = new Button("Add Cours");
        Button listTasksBtn = new Button("Liste des Cours");
        
        
         addTaskBtn.addActionListener(e -> new AddCoursForm().show());
        listTasksBtn.addActionListener(x->new ListCoursForm(this).show());
        
        
       this.addAll( listTasksBtn,addTaskBtn);
        
    }
    
}
