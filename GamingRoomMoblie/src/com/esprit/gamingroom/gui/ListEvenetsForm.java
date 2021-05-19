/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.esprit.gamingroom.entities.Evenement;
import com.esprit.gamingroom.services.ServiceEvenement;
import com.esprit.gamingroom.utils.Statics;
import static com.esprit.gamingroom.utils.Statics.getDossierImagePath;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Farah
 */
public class ListEvenetsForm extends Form {

    SpanLabel titre;
    SpanLabel categori;
    SpanLabel date;
    Container x;
    Container ligne;
    Container imageCont;
    String imagePath = getDossierImagePath();
    Form current;

    public ListEvenetsForm(Form previous) {
        setTitle("Liste des evenement");
        current = this;
        List<Evenement> ls = ServiceEvenement.getInstance().getAllEvenets();
        for (Evenement e : ls) {
            try {
                EncodedImage enc = EncodedImage.create("/loading.gif");
                Image imgE = URLImage.createToStorage(enc, e.getImage(), imagePath + e.getImage(), URLImage.RESIZE_SCALE);
                ligne = new Container(BoxLayout.x());
                ImageViewer ivE = new ImageViewer(imgE);
                imageCont = new Container();
                x = new Container(BoxLayout.y());

                ivE.setImage(imgE.scaled(400, 400));
                titre = new SpanLabel();
                categori = new SpanLabel();
                date = new SpanLabel();
                titre.setText(e.getNomEvent());
                categori.setText(e.getCategorie_id().getNomcat());
                String d = e.getDateDeb().toString().substring(0, 10) + " " + e.getDateDeb().toString().substring(24);
                date.setText(d);
                date.getTextAllStyles().setFgColor(0x5C246E);
                date.getTextAllStyles().setFont(Font.createTrueTypeFont(Font.NATIVE_ITALIC_LIGHT, 2f));

                Button btnConst = new Button("Consulter");
                btnConst.getAllStyles().setFont(Font.createTrueTypeFont(Font.NATIVE_ITALIC_LIGHT, 2f));
                btnConst.getAllStyles().setBorder(Border.createDoubleBorder(2, 0x5C246E));
                btnConst.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
                btnConst.addPointerPressedListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new ConsulterEvenement(current, e.getIdevent()).show();
                    }
                });

                /**
                 * *************************************************************************
                 */
                TextField searchField;
                searchField = new TextField("", "Chercher un événement");
                searchField.getHintLabel().setUIID("Title");
                searchField.setUIID("Title");
                
                getToolbar().setTitleComponent(searchField);

                searchField.addDataChangeListener((i1, i2) -> {
                    String t = searchField.getText();
                    if (t.length() < 1) {
                        for (Component cmp : getContentPane()) {
                            cmp.setHidden(false);
                            cmp.setVisible(true);
                        }
                    } else {
                        t = t.toLowerCase();
                        for (Component cmp : getContentPane()) {

                            String val = ((SpanLabel) ((Container) ((Container) cmp).getComponentAt(1)).getComponentAt(0)).getText();
                            System.out.println(val);
                            boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
                            cmp.setHidden(!show);
                            cmp.setVisible(show);
                        }
                    }
                    getContentPane().animateLayout(250);
                });
                /**
                 * *************************************************************************************
                 */

                x.add(titre);
                // x.add(categori);
                x.add(date);
                x.add(btnConst);
                imageCont.add(ivE);
                ligne.add(imageCont);
                ligne.add(x);
                add(ligne);
            } //        ls.stream().forEach(l -> {
            //                    System.out.println("l "+l);
            //                    lab.setText(l.getNomEvent() + " " + l.getDescription());
            //                    add(lab);
            //                });
            catch (IOException ex) {
                System.out.println("oooooooooooooooooooooooooooooooooooooooooooooo");
                System.out.println(ex.getMessage());
            }
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
