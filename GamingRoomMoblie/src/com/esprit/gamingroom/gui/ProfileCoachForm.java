/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.Resources;
import com.esprit.gamingroom.entities.Membre;
import com.esprit.gamingroom.entities.UserSession;
import com.esprit.gamingroom.gui.cours.AddCoursForm;
import com.esprit.gamingroom.services.MembreService;
import com.esprit.gamingroom.utils.Statics;
import java.io.IOException;

/**
 *
 * @author Sonia
 */
public class ProfileCoachForm extends SideMenuBaseForm{
    Container imageCont;
    String imagePath = Statics.dossierMembreImagePath;
    Container ligne;
     Container x;
    
    public ProfileCoachForm(Resources res ){
         super(BoxLayout.y());
        try {
            setUIID("SignInForm");
          
//        setUIID("background");
        
            Toolbar tb = getToolbar();
            tb.setTitleCentered(false);
            Button menuButton = new Button("");
            menuButton.setUIID("Title");
            FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
            menuButton.addActionListener(e -> getToolbar().openSideMenu());
            UserSession us = UserSession.getInstancee();
            Membre m = us.getUser();

      
              setTitle(m.getNom()+" "+m.getPrenom());
            System.out.println("weririr :" + m.getNom());
            System.out.println("role: "+m.getRole());
            
            
////////         
            Label email = new Label();
            email.setText("Email: ");
            email.setUIID("titre2");
            
            Label useremail = new Label();
            useremail.setText(m.getEmail());
            Label tel = new Label();
            tel.setText("Telephone: ");
            Label usertel = new Label();
            usertel.setText(m.getTel());
            Label rolesexe = new Label();
            rolesexe.setText(m.getRole().toString()+" - "+m.getGenre().toString());
             useremail.setUIID("Titre");
             usertel.setUIID("Titre");
             rolesexe.setUIID("Titre");
            
            
            
            Label emailIcon = new Label("", "TextField");
            Label TelIcon = new Label("", "TextField");
            Label roleIcon = new Label("", "TextField");
            
            TelIcon.getAllStyles().setMargin(RIGHT, 0);
            emailIcon.getAllStyles().setMargin(RIGHT, 0);
            roleIcon.getAllStyles().setMargin(RIGHT, 0);
            FontImage.setMaterialIcon(emailIcon, FontImage.MATERIAL_EMAIL, 2);
            FontImage.setMaterialIcon(TelIcon, FontImage.MATERIAL_PHONE, 2);
            FontImage.setMaterialIcon(roleIcon, FontImage.MATERIAL_PERSON, 2);
            
            
            
            
            ///
            EncodedImage enc = EncodedImage.create("/loading2.gif");
            System.out.println(m.getImage());
            Image imgE = URLImage.createToStorage(enc, m.getImage(), imagePath + m.getImage(), URLImage.RESIZE_SCALE);
            ligne = new Container(BoxLayout.xCenter());
            ImageViewer ivE = new ImageViewer(imgE);
            imageCont = new Container();
            x = new Container(BoxLayout.y());
            ivE.setImage(imgE.scaled(400 , 400));
            //                ((BorderLayout) x.getLayout()).setCenterBehavior(BorderLayout.CENTER_BEHAVIOR_CENTER);
            System.out.println("omg " + imagePath + m.getImage());
            try {
                setupSideMenu(res);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
           
            
            
             imageCont.add(ivE);
                 ligne.add(imageCont);
                 
                ligne.add(x);
                   
               
                
                 Container by = BoxLayout.encloseY(
                        
                   BorderLayout.center(rolesexe).
                        add(BorderLayout.WEST, roleIcon),
                BorderLayout.center(email).
                        add(BorderLayout.WEST, emailIcon),
                         useremail,
                BorderLayout.center(usertel).
                        add(BorderLayout.WEST, TelIcon)
                
               
        );
                 ligne.add(by);
         add(ligne);
         Button btnAddCour=new Button("Ajouter un Cours");
         btnAddCour.addActionListener(l->{
              new AddCoursForm(res).show();
         });
         add(btnAddCour);
        } catch (IOException ex) {
           // Logger.getLogger(ProfileCoachForm.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("hiiii");
        }
       
    }
    protected void showOtherForm(Resources res) {
     //   new StatsForm(res).show();
    }
}
