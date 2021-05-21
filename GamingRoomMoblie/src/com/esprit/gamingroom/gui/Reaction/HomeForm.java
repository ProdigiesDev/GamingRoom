/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui.Reaction;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import com.esprit.gamingroom.entities.Avis;
import com.esprit.gamingroom.entities.Membre;
import com.esprit.gamingroom.entities.Reclamation;
import com.esprit.gamingroom.entities.UserSession;
import com.esprit.gamingroom.gui.SideMenuBaseForm;
import com.esprit.gamingroom.gui.solitaire.Solitaire;
import com.esprit.gamingroom.services.ReclamationService;
import com.esprit.gamingroom.services.AvisService;
import java.io.IOException;

/**
 *
 * @author Dah
 */
public class HomeForm extends SideMenuBaseForm{

    Container x;
    Container ligne;
    Container imageCont;
    String imagePath = "http://localhost:8000/assets/images/dark/";
    Membre m;
    public HomeForm(Resources res) {
          UserSession us = UserSession.getInstancee();
          m = us.getUser();
        try {
            this.setTitle("Accueil");
            this.setLayout(BoxLayout.y());
            Toolbar tb = getToolbar();
            tb.setTitleCentered(false);
            Button menuButton = new Button("");
            menuButton.setUIID("Title");
            FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
            menuButton.addActionListener(e -> getToolbar().openSideMenu());
            EncodedImage enc = EncodedImage.create("/loading.gif");
            Image imgE = URLImage.createToStorage(enc, "login2.png", imagePath+"login2.png", URLImage.RESIZE_SCALE);
            ligne = new Container(BoxLayout.y());
            ImageViewer ivE = new ImageViewer(imgE);
            imageCont = new Container();
            x = new Container(BoxLayout.y());
            ivE.setImage(imgE.scaled(900, 500));
            SpanLabel titre = new SpanLabel();
            titre.setText("GAMINGROOM - GAMING PLATFORME");
            x.add(titre);
            imageCont.add(ivE);
            ligne.add(imageCont);
            ligne.add(x);
            add(ligne);
            try {
                    setupSideMenu(res);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            this.add(avisCont());
            this.add(recCont());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private Container avisCont() {

        Container container = new Container(new GridLayout(3, 2));
        TextArea avi = new TextArea("");
        avi.setRows(5);
        Button addBtn = new Button("Envoyer");
        addBtn.addActionListener(l -> {
            AvisService as = new AvisService();
          
        
            String avisTxt = avi.getText();
            if (avisTxt.length() < 20) {

                Dialog.show("Validation", "Votre avis doit etre au minimum 20 character", "OK", "");

                return;
            }

            if (as.add(new Avis(m, avi.getText()))) {

                Dialog.show("Info", "Merci pour votre Avis", "OK", "");
                avi.setText("");
            } else {
                Dialog.show("Error", "Erreur est survenue", "OK", "");
            }
        });
        container.addAll(new Label(), new Label("Votre Avis"), new Label("Message"), avi, new Label(), addBtn);

        return container;
    }

    private Container sectionContainer() {

        Container container = new Container(BoxLayout.y());
        
       //container.add(subConatiner("login2.png","Nos Produit",new HomeForm()));
      //  container.add(subConatiner("login2.png","Nos Cours",new HomeForm()));
      //  container.add(subConatiner("login2.png","Nos Evenement",new HomeForm()));
      //  container.add(subConatiner("login2.png","Nos Jeux",new Solitaire()));
        return container;

    }

    private Container subConatiner(String image, String title, Form form) {  
        Container container = new Container(BoxLayout.x());
        
        try {
            EncodedImage enc = EncodedImage.create("/loading.gif");
            Image imgE = URLImage.createToStorage(enc, image, imagePath+image, URLImage.RESIZE_SCALE);
            ImageViewer ivE = new ImageViewer(imgE);
            ivE.setImage(imgE.scaled(400, 400));
            Container imagec=new Container();
            imagec.add(ivE);
            Button btnConst = new Button("Voir plus");
            btnConst.getAllStyles().setFont(Font.createTrueTypeFont(Font.NATIVE_ITALIC_LIGHT, 2f));
            btnConst.getAllStyles().setBorder(Border.createDoubleBorder(2, 0x5C246E));
            btnConst.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
            btnConst.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    form.show();
                }
            });
            container.add(imagec);
            container.add(new Label(title));
            container.add(btnConst);
            
        } catch (IOException ex) {
            ex.printStackTrace();        }
        return container;
    }

    private Container recCont() {
        TextModeLayout tl = new TextModeLayout(3, 2);
        Container container = new Container(tl);
        TextComponent sujet = new TextComponent().label("Sujet");
        Validator val = new Validator();
        val.addConstraint(sujet, new LengthConstraint(5));
        TextComponent cont = new TextComponent().label("Contenue").multiline(true);
        val.addConstraint(cont, new LengthConstraint(20));
        Button addBtn = new Button("Envoyer");
        addBtn.addActionListener(l -> {
            ReclamationService as = new ReclamationService();
            if (!val.isValid()) {

                Dialog.show("Validation", "Contenue doit etre au minimum 20 character et Sujet au minimum 5 character ", "OK", "");
                return;
            }

            String contTxt = cont.getText();
            String sujTxt = sujet.getText();

            if (as.add(new Reclamation(sujTxt, contTxt, m))) {
                Dialog.show("Info", "Réclamation recu", "OK", "");
                cont.text("");
                sujet.text("");
            } else {
                Dialog.show("Error", "Erreur est survenue", "OK", "");
            }
        });
        container.addAll(new Label(), new Label("Réclamation"), sujet, cont, new Label(), addBtn);

        return container;
    }

    protected void showOtherForm(Resources res) {
     //   new StatsForm(res).show();
    }
}
