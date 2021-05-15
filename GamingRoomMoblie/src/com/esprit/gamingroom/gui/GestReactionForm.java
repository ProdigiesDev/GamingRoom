/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Dah
 */
public class GestReactionForm extends Form{

    public GestReactionForm() {
        this.setTitle("Gestion Reaction");
        this.setLayout(BoxLayout.y());
        Button goAvis=new Button("Avis");
        goAvis.addActionListener(l->{
            new AvisForm().show();
        });
        
        Button goReac=new Button("Reclamation");
        goReac.addActionListener(l->{
            new ReclamationForm().show();
        });
        
        Button goChat=new Button("Chat");
        goChat.addActionListener(l->{
            new ChatForm().show();
        });
        
        Button goJeux=new Button("Jeux");
        goJeux.addActionListener(l->{
            new JeuxForm().show();
        });
        
        
        this.addAll(goAvis,goReac,goChat,goJeux);
        
    }
    
    
}
