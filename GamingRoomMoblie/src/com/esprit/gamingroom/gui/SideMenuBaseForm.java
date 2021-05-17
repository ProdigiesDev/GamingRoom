/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.esprit.gamingroom.entities.Membre;
import com.esprit.gamingroom.entities.UserSession;
import com.esprit.gamingroom.gui.membre.HomeForm;
import java.io.IOException;

/**
 *
 * @author Sonia
 */
public abstract class SideMenuBaseForm extends Form {
    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public void setupSideMenu(Resources res) throws IOException {
    UserSession us = UserSession.getInstancee();
        Membre m = us.getUser();
//        Image logo = res.getImage("sidemenu2.jpg");
//      
//        ImageViewer im =new ImageViewer(logo);
//        Container sidemenuTop = BorderLayout.center(im);
//        sidemenuTop.setUIID("SidemenuTop");
//
//        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Home", FontImage.MATERIAL_HOME, e -> new HomeForm().show());
        if (m.getRole()== Membre.Role.Membre){
            getToolbar().addMaterialCommandToSideMenu("  Profile : "+m.getPrenom()+" "+m.getNom(), FontImage.MATERIAL_PERSON,e -> new ProfileMembreForm(res).showBack());
        }
        else if(m.getRole()== Membre.Role.Coach){
            getToolbar().addMaterialCommandToSideMenu("  Profile : "+m.getPrenom()+" "+m.getNom(), FontImage.MATERIAL_PERSON,e -> new ProfileCoachForm(res).showBack());
        }
//         getToolbar().addMaterialCommandToSideMenu("  Profile", FontImage.MATERIAL_PERSON, e -> showOtherForm(res));
//        getToolbar().addMaterialCommandToSideMenu("  Shop", FontImage.MATERIAL_SHOP, e -> {
////            try {
////                new ShopForm(res).show();
////            } catch (IOException ex) {
////
////            }
//            
//        });
//        getToolbar().addMaterialCommandToSideMenu("  Events", FontImage.MATERIAL_EVENT, e -> showOtherForm(res));
//        getToolbar().addMaterialCommandToSideMenu("  Competitions", FontImage.MATERIAL_TRENDING_UP, e -> {
////            try {
////                new CompetitionsForm(res).show();
////            } catch (IOException ex) {
////               
////            }
//        });
//        getToolbar().addMaterialCommandToSideMenu("  News", FontImage.MATERIAL_LIST, e -> showOtherForm(res));
//        getToolbar().addMaterialCommandToSideMenu("  Reviews", FontImage.MATERIAL_THUMB_UP, e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> {new SignInForm(res).show();
        UserSession.instance.cleanUserSession();});
        
    }

    protected abstract void showOtherForm(Resources res);
    
}
