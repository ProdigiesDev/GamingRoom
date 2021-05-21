/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;

import com.codename1.ui.Container;

import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.esprit.gamingroom.entities.Membre;
import com.esprit.gamingroom.services.MembreService;

import com.codename1.ext.filechooser.FileChooser;
import com.codename1.ui.Dialog;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author Sonia
 */
public class SignUpForm extends Form {

    MembreService us;
    Membre m;
    Validator val = new Validator();

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

    public SignUpForm(Resources res) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        this.us = MembreService.getInstance();
        setUIID("SignUpForm");

        getTitleArea().setUIID("Container");
        Label titre = new Label();
        titre.setText("Sign Up");
        titre.setUIID("Title");

        TextField nom = new TextField("", "Nom", 50, TextField.ANY);
        TextField prenom = new TextField("", "Prenom", 15, TextField.ANY);
        Picker date = new Picker();
        date.setType(Display.PICKER_TYPE_DATE);

        ComboBox genre = new ComboBox();
        genre.addItem(Membre.Genre.Homme.toString());
        genre.addItem(Membre.Genre.Femme.toString());

        TextField Tel = new TextField("", "Telephone", 15, TextField.NUMERIC);
        TextField email = new TextField("", "Email", 50, TextField.EMAILADDR);
        TextField password = new TextField("", "Password", 15, TextField.PASSWORD);
        TextField vpassword = new TextField("", "Password", 15, TextField.PASSWORD);
        TextField image = new TextField("", "Image", 50, TextField.ANY);

        val.addConstraint(email, RegexConstraint.validEmail("Email doit etre valide"));
        val.addConstraint(Tel, new RegexConstraint("^[0-9]{8}$","Telephone doit etre chaine des nombres"));
        val.addConstraint(nom, new RegexConstraint("^[a-zA-Z]+$", "Nom doit etre chaine de caractère"));
        val.addConstraint(prenom, new RegexConstraint("^[a-zA-Z]+$", "Prenom doit etre chaine de caractère"));

        ComboBox role = new ComboBox();
        role.addItem(Membre.Role.Membre.toString());
        role.addItem(Membre.Role.Coach.toString());

        TextArea desc = new TextArea();
        desc.setRows(3);
//        desc.setColumns(10);
//        desc.setMaxSize(255);
        desc.setHint("Decrivez vous dans le domaine de Gaming ");
        Button valider = new Button("Valider Inscription");
        valider.setUIID("LoginButton");
        ///////////////image/////////////////////
        Button img1 = new Button("Choisir une image");
        CheckBox multiSelect = new CheckBox();
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
                                valider.addActionListener((evt) -> {
                                    if (!val.isValid()) {
                                      Dialog.show("Validation", "Verfier vous donner : 1-Telephone doit etre chaine des nombres 2-Nom et Prenom  doit etre chaine de caractère 3-Email doit etre valide", "OK", "");
                                        return;
                                    }

                                    MembreService.getInstance().signUp(nom, prenom, date.getDate(), genre.getSelectedItem().toString(), Tel, email, password, vpassword, namePic, role.getSelectedItem().toString(), desc);

                                    try {

                                    } catch (NumberFormatException x) {
                                        Dialog.show("Alert", "nb par's status must be a number.", null, "OK");
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

//        Button valider = new Button("Valider Inscription");
//        valider.setUIID("LoginButton");
//        valider.addActionListener(e -> {
//           MembreService.getInstance().signUp(nom,prenom,date.getDate(),genre.getSelectedItem().toString(),Tel,email,password,vpassword,image,role.getSelectedItem().toString(),desc);
//
//
//       });
        Button retour = new Button("<< Retour SignIn");
        retour.setUIID("CreateNewAccountButton");
        retour.addActionListener(e -> new SignInForm(res).show());

//         nom.getAllStyles().setMargin(LEFT, 0);
//         prenom.getAllStyles().setMargin(LEFT, 0);
//         date.getAllStyles().setMargin(LEFT, 0);
//         genre.getAllStyles().setMargin(LEFT, 0);
        Container by;
        by = BoxLayout.encloseY(
                //                spaceLabel,
                titre,
                img1,
                nom,
                prenom,
                date,
                genre,
                Tel,
                email,
                password,
                vpassword,
                role,
                desc,
                valider,
                retour
        );

        add(BorderLayout.NORTH, by);

    }
}
