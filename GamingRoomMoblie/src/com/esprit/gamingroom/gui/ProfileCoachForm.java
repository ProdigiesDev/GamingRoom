/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sonia
 */
public class ProfileCoachForm extends SideMenuBaseForm{
    
    public ProfileCoachForm(Resources res ){
         super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
        try {
            setupSideMenu(res);
        } catch (IOException ex) {
            Logger.getLogger(ProfileMembreForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected void showOtherForm(Resources res) {
     //   new StatsForm(res).show();
    }
}
