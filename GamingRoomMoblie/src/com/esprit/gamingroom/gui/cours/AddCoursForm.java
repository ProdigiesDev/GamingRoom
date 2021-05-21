/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui.cours;

import com.codename1.capture.Capture;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.esprit.gamingroom.entities.Cours;
import com.esprit.gamingroom.entities.Membre;
import com.esprit.gamingroom.entities.UserSession;
import com.esprit.gamingroom.services.CoursServices;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;


/**
 *
 * @author eyatr
 */
public class AddCoursForm extends Form{
    protected String saveFileToDevice(String hi, String ext) throws IOException {
        URI uri;
           
        try {
            uri = new URI(hi);
            String path = uri.getPath();
            int index = hi.lastIndexOf("/");
            hi = hi.substring(index + 1);
            return hi;
        } catch (URISyntaxException ex) {
        }
        return "hh";
    }
     

     public AddCoursForm(Resources res){
        this.setTitle("Ajouter un cours");
        this.setLayout(BoxLayout.y());
       
        TextField tfname = new TextField("", "Insérer nom du cours");
        TextField tfdescription = new TextField("","Insérer la déscription");
        TextField tfnbp = new TextField("", "Insérer le nombre des participants");
        TextField tfmem = new TextField("", "Insérer le membre");
        //add(new Label("Date Creation"));
         Picker tfdate = new Picker();
         tfdate.setType(Display.PICKER_TYPE_DATE);
         TextField tftags = new TextField("", "Insérer les mots clés");
        TextField tfimg = new TextField("", "Inserer l'image");
        TextField tfcat = new TextField("", "Choisir la catégorie");
        TextField tfyoutube = new TextField("", "Insérer le lien youtube");
        CheckBox multiSelect = new CheckBox("Multi select");
        Button img1 = new Button("Chisir une image");
        Button submitTaskBtn = new Button("Confirmer");
        img1.addActionListener((ActionEvent e) -> {
            if (FileChooser.isAvailable()) {
                FileChooser.setOpenFilesInPlace(true);
                FileChooser.showOpenDialog(multiSelect.isSelected(), ".jpg, .jpeg, .png/plain", (ActionEvent e2) -> {
                    if (e2 == null || e2.getSource() == null) {
                        add("No file was selected");
                        revalidate();
                        return;
                    }
                    if (multiSelect.isSelected()) {
                        String[] paths = (String[]) e2.getSource();
                        for (String path : paths) {
                            System.out.println(path);
                            CN.execute(path);
                        }
                        return;
                    }

                    String file = (String) e2.getSource();
                    if (file == null) {
                        add("No file was selected");
                        revalidate();
                    } else {
                        Image logo;

                        try {
                            logo = Image.createImage(file).scaledHeight(500);;
                            add(logo);
                            String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "photo.png";

                            try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                                System.out.println(imageFile);
                                ImageIO.getImageIO().save(logo, os, ImageIO.FORMAT_PNG, 1);
                            } catch (IOException err) {
                            }
                        } catch (IOException ex) {
                        }

                        String extension = null;
                        if (file.lastIndexOf(".") > 0) {
                            extension = file.substring(file.lastIndexOf(".") + 1);
                            StringBuilder hi = new StringBuilder(file);
                            if (file.startsWith("file://")) {
                                hi.delete(0, 7);
                            }
                            int lastIndexPeriod = hi.toString().lastIndexOf(".");
                            Log.p(hi.toString());
                            String ext = hi.toString().substring(lastIndexPeriod);
                            String hmore = hi.toString().substring(0, lastIndexPeriod - 1);
                            try {
                                String namePic = saveFileToDevice(file, ext);
                                System.out.println(namePic);
                                 submitTaskBtn.addActionListener((evt) -> {
            
            if (tfname.getText().length() ==0 || tfdescription.getText().length()==0) {
                Dialog.show("Alert", "Textfields cannot be empty.",null, "OK");
            }else {
                
                try {
                UserSession us = UserSession.getInstancee();
                Membre m = us.getUser();
                
                Cours cours = new Cours( tfname.getText(),tfdescription.getText(),Integer.parseInt(tfnbp.getText()),m.getId(),tftags.getText(),namePic,Integer.parseInt(tfcat.getText()),tfyoutube.getText());
                    if (CoursServices.getInstance().addCoursAction(cours)) {
                        Dialog.show("Success", "Cours added successfully.",null, "OK");
                    }
                    
                    
                } catch (NumberFormatException x) {
                    Dialog.show("Alert", "nb par's status must be a number.",null, "OK");
                }
                
                
                
            }
            
            
            
        });
                            } catch (IOException ex) {
                            }

                            revalidate();
                            

                        
                    }
                    }
                        });
            }
                });
        
            
       
       
        
//        //upload image
//         theme = UIManager.initFirstTheme("/theme"); 
//        Button addPhotoCamera = new Button(theme.getImage("addcamera.png"));
//        addPhotoCamera.setWidth(75);
//        addPhotoCamera.setHeight(75);
//        addPhotoCamera.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                Form cimg = new Form(new BoxLayout(BoxLayout.Y_AXIS));
//                Container imagec = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//                Label capturedPhoto = new Label();
//                String i = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
//                if(i != null){
//                    try {
//                        Image img = Image.createImage(i);                        
//                        img = img.scaled(Math.round(Display.getInstance().getDisplayWidth()-40), Math.round(Display.getInstance().getDisplayHeight()-40));
//                        imagec.add(img);
//                    } catch (IOException ex) {                        
//                    }                    
//                }
//                cimg.add(imagec);
//                Button validerPhoto = new Button("Ajouter");
//                validerPhoto.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent evt) {
//                        String DBPhotoName = CoursServices.uploadPhoto(i);                        
//                        albumService.ajouterPhoto(DBPhotoName);
//                        
//                        Form f = rootContainer.getComponentForm();
//                        f.show();
//                        rootContainer.removeAll();
//                        c.removeAll();
//                        initialize();
//                    }
//                });
//                Button annulerPhoto = new Button("Annuler");
//                annulerPhoto.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent evt) {
//                        Form f = rootContainer.getComponentForm();
//                        f.show();
//                    }
//                });
//                
//                Container photoButtons = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
//                Container buttsPhoto = new Container(new BoxLayout(BoxLayout.X_AXIS));
//                buttsPhoto.add(validerPhoto).add(annulerPhoto);
//                photoButtons.add(BorderLayout.CENTER, buttsPhoto);
//                cimg.add(photoButtons);
//                cimg.show();
        //upload image
        
        //
        this.addAll(tfname, tfdescription,tfnbp,tfdate,tftags,tfcat,tfyoutube, submitTaskBtn,img1);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new com.esprit.gamingroom.gui.Reaction.HomeForm(res).showBack());
        
        
    }
     
    
}


