/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui.cours;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.List;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.esprit.gamingroom.entities.Cours;
import com.esprit.gamingroom.services.CoursServices;
import com.esprit.gamingroom.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author eyatr
 */
public class ListCoursForm extends Form {
    
    SpanLabel titre;
    SpanLabel description;
    SpanLabel date;
    Container x;
    Container ligne;
    Container imageCont;
    String imagePath = Statics.dossierImagePath;
    Form current;

    public ListCoursForm(Form previous) {
        setTitle("Liste des cours");
        current = this;
        ArrayList<Cours> ls = CoursServices.getInstance().getAllCours();
        for (Cours c : ls) {
            try {
                EncodedImage enc = EncodedImage.create("/loading.gif");
                Image imgE = URLImage.createToStorage(enc, c.getImage(), imagePath + c.getImage(), URLImage.RESIZE_SCALE);
                ligne = new Container(BoxLayout.x());
                ImageViewer ivE = new ImageViewer(imgE);
                imageCont = new Container();
                x = new Container(BoxLayout.y());
//                ((BorderLayout) x.getLayout()).setCenterBehavior(BorderLayout.CENTER_BEHAVIOR_CENTER);
                System.out.println("omg " + imagePath + c.getImage());

                ivE.setImage(imgE.scaled(400 , 400));
                titre = new SpanLabel();
                description = new SpanLabel();
                date = new SpanLabel();
                titre.setText(c.getNomCours());
                description.setText(c.getDescription());
                System.out.println("e " + c);
                //String d = c.getDateDeb().toString().substring(0, 10) + " " + e.getDateDeb().toString().substring(24);
//                date.setText(d);
//                date.getTextAllStyles().setFgColor(0x5C246E);
//                date.getTextAllStyles().setFont(Font.createTrueTypeFont(Font.NATIVE_ITALIC_LIGHT, 2f));
                //btn consulter
                Button btnConst = new Button("Consulter");
                btnConst.getAllStyles().setFont(Font.createTrueTypeFont(Font.NATIVE_ITALIC_LIGHT, 2f));
                btnConst.getAllStyles().setBorder(Border.createDoubleBorder(2, 0x5C246E));
                btnConst.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
                btnConst.addPointerPressedListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new InfoCours(current, c.getId()).show();
                    }
                });

                x.add(titre);
                x.add(description);
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
