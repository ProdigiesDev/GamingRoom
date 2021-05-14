/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui.evenement;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.gamingroom.services.ListEvenetsForm;

/**
 *
 * @author Farah
 */
public class HomeForm extends Form {

    Form current;
    
    public HomeForm() {
        current = this; 
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choisir une option"));
        Button btnListTasks = new Button("List Tasks");
        btnListTasks.addActionListener(e -> new ListEvenetsForm(current).show());
        
        addAll(btnListTasks);

    }

}
