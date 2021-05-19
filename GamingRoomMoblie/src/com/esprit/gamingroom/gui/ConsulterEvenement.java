/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MediaPlayer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.components.WebBrowser;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.location.Geofence;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.esprit.gamingroom.entities.Evenement;
import com.esprit.gamingroom.services.ServiceEvenement;
import static com.esprit.gamingroom.utils.Statics.getDossierImagePath;
import java.io.IOException;
import java.util.List;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.ImageIO;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import com.codename1.util.DateUtil;
import java.io.OutputStream;

/**
 *
 * @author Farah
 */
public class ConsulterEvenement extends Form {

    Form current;
    String imagePath = getDossierImagePath();

    public ConsulterEvenement(Form previous, int id) {
        try {
            current = this;
            Evenement e = ServiceEvenement.getInstance().getEvent(id);
            setTitle(e.getNomEvent());
            Container constContainer = new Container(BoxLayout.y());
            Container globcontDetLab = new Container(BoxLayout.x());
            Container buttonCont = new Container(BoxLayout.x());
            Container contDetLab = new Container(BoxLayout.y());
            Container contDet = new Container(BoxLayout.y());

            EncodedImage enc = EncodedImage.create("/loading.gif");
            Image imgE = URLImage.createToStorage(enc, e.getImage(), imagePath + e.getImage(), URLImage.RESIZE_SCALE);

            ImageViewer ivE = new ImageViewer(imgE);
            WebBrowser player = new WebBrowser();
            String integrationCode = "<iframe src=\"" + e.getLienYoutube() + "\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen style=\"width: 100%;height:800px\" </iframe>";
            player.setPage(integrationCode, null);

            player.setPreferredSize(new Dimension(1100, 810));
            constContainer.getAllStyles().setBackgroundType(Style.BACKGROUND_NONE);
            constContainer.getAllStyles().setBgColor(0x5C246E);
            constContainer.getStyle().setBgTransparency(100);

            globcontDetLab.getAllStyles().setBackgroundType(Style.BACKGROUND_NONE);
            globcontDetLab.getAllStyles().setBgColor(0x000000);
            globcontDetLab.getStyle().setBgTransparency(100);
            //globcontDetLab.getAllStyles().setPadding(10, 10, 50, 50);
            globcontDetLab.setPreferredSize(new Dimension(1000, 750));

            MapContainer map = new MapContainer("AIzaSyBvqEORXqwzdL3muXE-wL1XEn-bHsP1_jc");

//            map.addTapListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent evt) {
//                    //Point of the screen where the user pressed
//                    int x = evt.getX();
//                    int y = evt.getY();
//                    System.out.println("xxx " + x);
//                    System.out.println("yyy " + y);
//                    //I get the coordinates from the point of the screen
//                    Coord coord = map.getCoordAtPosition(x, y);
//                    System.out.println("cooooxxxyy " + coord);
//                    //I create the marker, with a style
//                    Style s = new Style();
//                    s.setFgColor(0xff0000);
//                    s.setBgTransparency(0);
//                    FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s);
//                    EncodedImage icon = EncodedImage.createFromImage(markerImg, false);
//                    map.addMarker(icon, coord, "My position", null, null);
//                }
//            });
//
//            map.addTapListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent evt) {
//                    //I get the coordinates of the NE point
//                    Coord neCoords = map.getBoundingBox().getNorthEast();
//                    System.out.println("neCoords " + neCoords);
//                    ToastBar.showMessage("NE coords: (" + neCoords.getLatitude() + ";" + neCoords.getLongitude() + ")", FontImage.MATERIAL_PLACE);
//                }
//            });
            Container contMap = new Container();

            Dialog dlg = new Dialog(e.getNomEvent());
            dlg.setLayout(BoxLayout.x());
// span label accepts the text and the UIID for the dialog body .(new Date())
            if ((int) ((e.getDateDeb()).getTime() - (new Date()).getTime()) > 0) {
                dlg.add(new SpanLabel("Evénement à venir", "DialogBody"));
            } else if ((int) ((e.getDateDeb()).getTime() - (new Date()).getTime()) < 0) {
                dlg.add(new SpanLabel("Evenement dépassé", "DialogBody"));
            } else {
                dlg.add(new SpanLabel("Evenement en cours", "DialogBody"));
            }
            int h = Display.getInstance().getDisplayHeight();
            dlg.setDisposeWhenPointerOutOfBounds(true);
            dlg.show(h / 8 * 7, 0, 0, 0);
            /**
             * spanlables*
             */
            SpanLabel dateDeb = new SpanLabel("Date début    :");
            SpanLabel dateFin = new SpanLabel("Date fin          :");
            SpanLabel cat = new SpanLabel("Catégorie       :");
            SpanLabel nbMax = new SpanLabel("Nb Max           :");
            SpanLabel descrption = new SpanLabel("Description   :");
            SpanLabel lieu;

            Style sI = new Style();
            sI.setFgColor(0xffffff);
            sI.setBgTransparency(0);
            FontImage dateImg = FontImage.createMaterial(FontImage.MATERIAL_DATE_RANGE, sI);
            EncodedImage dateIcon = EncodedImage.createFromImage(dateImg, false);
            dateDeb.setIcon(dateIcon);
            dateFin.setIcon(dateIcon);
            FontImage catImg = FontImage.createMaterial(FontImage.MATERIAL_ART_TRACK, sI);
            EncodedImage catIcon = EncodedImage.createFromImage(catImg, false);
            cat.setIcon(catIcon);
            FontImage nbImg = FontImage.createMaterial(FontImage.MATERIAL_INPUT, sI);
            EncodedImage nbIcon = EncodedImage.createFromImage(nbImg, false);
            nbMax.setIcon(nbIcon);
            FontImage desImg = FontImage.createMaterial(FontImage.MATERIAL_NOTE, sI);
            EncodedImage desIcon = EncodedImage.createFromImage(desImg, false);
            descrption.setIcon(desIcon);

            dateDeb.getTextAllStyles().setFgColor(0xFFFFFF);
            dateDeb.getTextAllStyles().setFont(Font.createTrueTypeFont(Font.NATIVE_MAIN_REGULAR, 3f));
            dateFin.getTextAllStyles().setFgColor(0xFFFFFF);
            dateFin.getTextAllStyles().setFont(Font.createTrueTypeFont(Font.NATIVE_MAIN_REGULAR, 3f));
            descrption.getTextAllStyles().setFgColor(0xFFFFFF);
            descrption.getTextAllStyles().setFont(Font.createTrueTypeFont(Font.NATIVE_MAIN_REGULAR, 3f));
            nbMax.getTextAllStyles().setFgColor(0xFFFFFF);
            nbMax.getTextAllStyles().setFont(Font.createTrueTypeFont(Font.NATIVE_MAIN_REGULAR, 3f));
            cat.getTextAllStyles().setFgColor(0xFFFFFF);
            cat.getTextAllStyles().setFont(Font.createTrueTypeFont(Font.NATIVE_MAIN_REGULAR, 3f));

            SpanLabel dateDebLab = new SpanLabel(e.getDateDeb().toString().substring(0, 10) + " " + e.getDateDeb().toString().substring(24));
            SpanLabel dateFinLab = new SpanLabel(e.getDateDeb().toString().substring(0, 10) + " " + e.getDateFin().toString().substring(24));
            SpanLabel catLab = new SpanLabel("\n" + e.getCategorie_id().getNomcat());
            SpanLabel nbMaxLab = new SpanLabel(e.getNbreMax_participant() + "");
            SpanLabel descrptionLab = new SpanLabel(e.getDescription());
            SpanLabel lieuLlab;
            dateDebLab.getTextAllStyles().setFgColor(0xFFFFFF);
            dateDebLab.getTextAllStyles().setFont(Font.createTrueTypeFont(Font.NATIVE_MAIN_REGULAR, 3f));
            dateFinLab.getTextAllStyles().setFgColor(0xFFFFFF);
            dateFinLab.getTextAllStyles().setFont(Font.createTrueTypeFont(Font.NATIVE_MAIN_REGULAR, 3f));
            descrptionLab.getTextAllStyles().setFgColor(0xFFFFFF);
            descrptionLab.getTextAllStyles().setFont(Font.createTrueTypeFont(Font.NATIVE_MAIN_REGULAR, 3f));
            nbMaxLab.getTextAllStyles().setFgColor(0xFFFFFF);
            nbMaxLab.getTextAllStyles().setFont(Font.createTrueTypeFont(Font.NATIVE_MAIN_REGULAR, 3f));
            catLab.getTextAllStyles().setFgColor(0xFFFFFF);
            catLab.getTextAllStyles().setFont(Font.createTrueTypeFont(Font.NATIVE_MAIN_REGULAR, 3f));

            /*screenshot*/
            Button screenshotButton = new Button("screenshot");
            screenshotButton.getAllStyles().setFont(Font.createTrueTypeFont(Font.NATIVE_ITALIC_LIGHT, 2f));
            screenshotButton.getAllStyles().setBorder(Border.createDoubleBorder(2, 0xffffff));
            screenshotButton.addActionListener((evt) -> {
                Image screenshot = Image.createImage(getWidth(), getHeight());
                revalidate();
                setVisible(true);
                paintComponent(screenshot.getGraphics(), true);

                String imageFile = FileSystemStorage.getInstance().getAppHomePath() + e.getNomEvent() + ".png";
                System.out.println("path shcreen " + imageFile);
                try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                    ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
                    ToastBar.showMessage("Vous venez de faire un capture d'écran", FontImage.MATERIAL_PLACE);
                    /*
                    switch (Display.getInstance().getSMSSupport()) {
                        case Display.SMS_NOT_SUPPORTED:
                             System.out.println("smsmszeeeeemsmsmsm1");
                            return;
                        case Display.SMS_SEAMLESS:
                            Display.getInstance().sendSMS("28510447", "okkk");
                            System.out.println("smsmsmsmsmsm1");
                            return;
                        default:
                            Display.getInstance().sendSMS("28510447", "okkk");
                            System.out.println("smsmsmsmsmsm");
                            return;
                    }
                     */
                } catch (IOException err) {
                    Log.e(err);
                }
            });

            /**
             * \spanlables*
             */
            getAllStyles().setBgImage(imgE);
            constContainer.add(player);
            contDetLab.add(dateDeb);
            contDetLab.add(dateFin);
            contDetLab.add(cat);
            contDetLab.add(nbMax);

            contDet.add(dateDebLab);
            contDet.add(dateFinLab);
            contDet.add(catLab);
            contDet.add(nbMaxLab);

            if (e.getLieu().equals("ONLINE")) {
                lieu = new SpanLabel("Lieu                 :");
                lieu.getTextAllStyles().setFgColor(0xFFFFFF);
                lieu.getTextAllStyles().setFont(Font.createTrueTypeFont(Font.NATIVE_MAIN_REGULAR, 3f));
                lieuLlab = new SpanLabel(e.getLieu());
                lieuLlab.getTextAllStyles().setFgColor(0xFFFFFF);
                lieuLlab.getTextAllStyles().setFont(Font.createTrueTypeFont(Font.NATIVE_MAIN_REGULAR, 3f));
                FontImage lieuImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, sI);
                EncodedImage lieuIcon = EncodedImage.createFromImage(lieuImg, false);
                lieu.setIcon(lieuIcon);

                contDetLab.add(lieu);
                contDet.add(lieuLlab);
            } else {
                String[] coordEvent = this.split(e.getLieu());
                int x = (int) Float.parseFloat(coordEvent[0]);
                int y = (int) Float.parseFloat(coordEvent[1]);
                Coord coord = new Coord(x, y);

                Style s = new Style();
                s.setFgColor(0x5C246E);
                s.setBgTransparency(0);
                FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s);
                EncodedImage icon = EncodedImage.createFromImage(markerImg, false);
                map.addMarker(icon, coord, e.getNomEvent(), null, null);
                map.zoom(coord, 15);
                map.setLabelForComponent(new Label("Location"));
                map.setShowMyLocation(true);
                //contMap.setPreferredSize(new Dimension(1100, 810));
                ToastBar.showMessage("NE coords: (" + coord.getLatitude() + ";" + coord.getLongitude() + ")", FontImage.MATERIAL_PLACE);
                contMap.getAllStyles().setPadding(10, 10, 50, 50);
                contMap.add(map);

                Location position = LocationManager.getLocationManager().getCurrentLocationSync();
                Coord coordP = new Coord((int) position.getAltitude(), (int) position.getLongitude());
                Style sP = new Style();
                sP.setFgColor(0xff0000);
                sP.setBgTransparency(0);

                FontImage markerImgP = FontImage.createMaterial(FontImage.MATERIAL_PLACE, sP);
                EncodedImage iconP = EncodedImage.createFromImage(markerImgP, false);
                map.addMarker(iconP, coordP, "My position", null, null);
            }

            contDetLab.add(descrption);
            contDet.add(descrptionLab);

            /**
             * *************buttons
             */
            System.out.println("bbbbbbbb " + ServiceEvenement.getInstance().getIfInscrit(e.getIdevent()));
            String buttionText;
            if (ServiceEvenement.getInstance().getIfInscrit(e.getIdevent())) {
                buttionText = "Annuler";
            } else {
                System.out.println("iiiiiiiiiiiiiiiiiiiiiiii");
                buttionText = "Inscription";
            }
            Button bntInsc = new Button(buttionText);

            bntInsc.getAllStyles().setFont(Font.createTrueTypeFont(Font.NATIVE_ITALIC_LIGHT, 2f));
            bntInsc.getAllStyles().setBorder(Border.createDoubleBorder(2, 0xffffff));
            bntInsc.addActionListener((evt) -> {
                if (ServiceEvenement.getInstance().getIfInscrit(e.getIdevent())) {

                    if (Dialog.show("Confirmation", "Êtes-vous sûr?", "OK", "Annuler")) {
                        if (ServiceEvenement.getInstance().annulerInscription(e.getIdevent())) {
                            Dialog.show("", "Inscription annulée", "Ok", null);
                            bntInsc.setText("Inscription");
                        }
                    }

                } else {
                    if (Dialog.show("Confirmation", "Êtes-vous sûr??", "OK", "Annuler")) {
                        Dialog.show("", ServiceEvenement.getInstance().getInscription(e.getIdevent()), "Ok", null);
                        bntInsc.setText("Annuler");
                    }

                }
            });

            /**
             * ***********************
             */

            /*
            LocalNotification ln = new LocalNotification();
            ln.setId("LnMessage");
            ln.setAlertTitle("Welcome");
            ln.setAlertBody("Thanks for arriving!");
            Geofence gf = new Geofence("test", position, 100, 100000);
            Display.getInstance().scheduleLocalNotification(ln, 22, LocalNotification.REPEAT_NONE);*/
            //constContainer.add(gf);
            globcontDetLab.add(contDetLab);
            globcontDetLab.add(contDet);
            buttonCont.add(bntInsc);
            buttonCont.add(screenshotButton);

            constContainer.add(globcontDetLab);
            constContainer.add(buttonCont);
            constContainer.add(contMap);
            add(constContainer);
            //add(player);
//            Button close = new Button("");
//            close.setUIID("Container");
//            close.getAllStyles().setFgColor(0xff0000);
//            player.getAllStyles().setBackgroundGradientEndColor(0xff0000);

//            add(LayeredLayout.encloseIn(ivE,
//                    FlowLayout.encloseRight(close)));
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

    public String[] split(String str) {
        ArrayList<String> splitArray = new ArrayList<>();
        StringTokenizer arr = new StringTokenizer(str, ",");//split by commas
        while (arr.hasMoreTokens()) {
            splitArray.add(arr.nextToken());
        }
        return splitArray.toArray(new String[splitArray.size()]);
    }
}
