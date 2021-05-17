/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui.cours;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.WebBrowser;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
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

/**
 *
 * @author eyatr
 */
public class InfoCours extends Form {

    SpanLabel titre;
    SpanLabel description;
    SpanLabel date;
    SpanLabel nb_participant;
    SpanLabel tags;
    SpanLabel categorie;
    SpanLabel lien;
    SpanLabel nom;
    Form current;
    String imagePath = Statics.dossierImagePath;
    Container x;
    Container ligne;
    Container imageCont;

    public InfoCours(Form previous, int id) {
        try {
            current = this;
            Cours c = CoursServices.getInstance().getOneCours(id);

            EncodedImage enc = EncodedImage.create("/loading.gif");
            Image imgE = URLImage.createToStorage(enc, c.getImage(), imagePath + c.getImage(), URLImage.RESIZE_SCALE);
            imageCont = new Container();
            x = new Container(BoxLayout.y());
//                ((BorderLayout) x.getLayout()).setCenterBehavior(BorderLayout.CENTER_BEHAVIOR_CENTER);
            System.out.println("omg " + imagePath + c.getImage());

            ImageViewer ivE = new ImageViewer(imgE);
            ivE.setImage(imgE.scaled(400, 400));
            //video
            WebBrowser player = new WebBrowser();
            
            String integrationCode = "<iframe src=\"" + c.getLienYoutube() + "\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen style=\"width: 100%;height:800px\" </iframe>";

            player.setPage(integrationCode, null);
            System.out.println("lien" + player.getPage());
            //add(player);
            titre = new SpanLabel();
            description = new SpanLabel();
            date = new SpanLabel();
            nb_participant = new SpanLabel();
            tags = new SpanLabel();
            categorie = new SpanLabel();
            lien = new SpanLabel();

            //Label testLabel = new Label("test");
            //testLabel.getAllStyles.setFgColor(0xff000);
            titre.setText("Titre:" + "  " + c.getNomCours());
            description.setText("Déscription:" + "  " + c.getDescription());
            tags.setText("Mots clés:" + "  " + c.getTags());
            date.setText("Date de création:" + "  " + c.getDate_creation());
            //nb_participant.setText(c.getNb_participants());
            //categorie.setText(c.getCategorie_id());
            lien.setText("Lien Youtube:" + "  " + c.getLienYoutube());
            //button supp
            Label supp = new Label("Supprimer Cours");
            supp.setUIID("NewsTopLine");
            Style suppStyle = new Style(supp.getUnselectedStyle());
            suppStyle.setFgColor(0xf21f1f);
            FontImage suppImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, suppStyle);
            supp.setIcon(suppImage);
            supp.setTextPosition(RIGHT);
            supp.addPointerPressedListener(e -> {
                Dialog dig = new Dialog("delete");
                if (dig.show("Suppression", "Voulez vous vraiment supprimer ce cours", "Annuler", "oui")) {
                    dig.dispose();
                } else {
                    dig.dispose();
                }
                System.out.println("supp");
                System.out.println(id);
                if (CoursServices.getInstance().deleteCours(id));
                supp.addDragFinishedListener(x -> new HomeForm().showBack());
            }
            );

            //btn inscription
            Button btnInscrip = new Button("Inscription");
            btnInscrip.getAllStyles().setFont(Font.createTrueTypeFont(Font.NATIVE_ITALIC_LIGHT, 2f));
            btnInscrip.getAllStyles().setBorder(Border.createDoubleBorder(2, 0x5C246E));
            btnInscrip.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
//                btnInscrip.addPointerPressedListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent evt) {
//                        new InfoCours(current, c.getId()).show();
//                    }
//                });

            Label like = new Label("0");
            like.setUIID("NewsTopLine");
            Style likeStyle = new Style(supp.getUnselectedStyle());
            suppStyle.setFgColor(0xf21f1f);
            FontImage likeImage = FontImage.createMaterial(FontImage.MATERIAL_10K, suppStyle);
            like.setIcon(likeImage);
            like.setTextPosition(RIGHT);
            // FontImage like=FontImage.createMaterial(FontImage.MATERIAL_ACCESS_TIME, suppStyle);

            addAll(ivE,player, titre, description, date, tags, lien, supp, btnInscrip, like);
//            Media video = MediaManager.createMedia(e.getLienYoutube(), true);
//            System.out.println("player "+video);
//            add(new MediaPlayer(video));
//            BrowserComponent browser = new BrowserComponent();
//            browser.setURL("https://www.codenameone.com/");
//            add( browser);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, p -> previous.showBack());

    }

}
