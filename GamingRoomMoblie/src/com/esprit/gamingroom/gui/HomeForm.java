/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author yasmine
 */
public class HomeForm  extends Form{
       Form current;
    public HomeForm() {
        current= this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnOffres = new Button("Add produits");
        Button btnListOffres = new Button("List produits");
        
      //  btnOffres.addActionListener(e-> new addOffresForm(current).show());
        btnListOffres.addActionListener(e-> new  ProduitAffichageForm(current).show());
        
        addAll(btnListOffres, btnOffres);
    }
}
