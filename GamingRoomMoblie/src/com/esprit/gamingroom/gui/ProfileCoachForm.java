/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.gamingroom.entities.Membre;
import com.esprit.gamingroom.entities.UserSession;
import com.esprit.gamingroom.utils.Statics;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sonia
 */
public class ProfileCoachForm extends SideMenuBaseForm{
    Container imageCont;
    String imagePath = Statics.dossierImagePath;
    Container ligne;
     Container x;
    
    public ProfileCoachForm(Resources res ){
         super(BoxLayout.y());
        try {
            Toolbar tb = getToolbar();
            tb.setTitleCentered(false);
            Button menuButton = new Button("");
            menuButton.setUIID("Title");
            FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
            menuButton.addActionListener(e -> getToolbar().openSideMenu());
            UserSession us = UserSession.getInstancee();
            Membre m = us.getUser();
            System.out.println("weririr :" + m.getNom());
            System.out.println("role: "+m.getRole());
            
            ///
            EncodedImage enc = EncodedImage.create("/loading2.gif");
            System.out.println(m.getImage());
            Image imgE = URLImage.createToStorage(enc, m.getImage(), imagePath + m.getImage(), URLImage.RESIZE_SCALE);
            ligne = new Container(BoxLayout.xCenter());
            ImageViewer ivE = new ImageViewer(imgE);
            imageCont = new Container();
            x = new Container(BoxLayout.y());
            ivE.setImage(imgE.scaled(300 , 300));
            //                ((BorderLayout) x.getLayout()).setCenterBehavior(BorderLayout.CENTER_BEHAVIOR_CENTER);
            System.out.println("omg " + imagePath + m.getImage());
            try {
                setupSideMenu(res);
            } catch (IOException ex) {
                Logger.getLogger(ProfileMembreForm.class.getName()).log(Level.SEVERE, null, ex);
            }
             imageCont.add(ivE);
                 ligne.add(imageCont);
                ligne.add(x);
                add(ligne);
        } catch (IOException ex) {
            Logger.getLogger(ProfileCoachForm.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    protected void showOtherForm(Resources res) {
     //   new StatsForm(res).show();
    }
}
