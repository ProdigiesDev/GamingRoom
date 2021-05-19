/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui.Reaction;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.gamingroom.ChatControllerForm;
/**
 *
 * @author Dah
 */
public class AdminHomeForm  extends Form{

    public AdminHomeForm() {
        this.setTitle("Admin Dashboard");
        this.setLayout(BoxLayout.y());
         Button goAvis=new Button("Avis");

        goAvis.addActionListener(l->{
            
            new AvisListAdminForm().show();
        });
        
        Button goReac=new Button("Reclamation");
        goReac.addActionListener(l->{
            new ReclamationListAdminForm().show();
        });
         Button gochat=new Button("Chat");
        gochat.addActionListener(l->{
            new ChatControllerForm().show();
        });
        
        
        
        this.addAll(goAvis,goReac,gochat);
        
    }
    
    
}
