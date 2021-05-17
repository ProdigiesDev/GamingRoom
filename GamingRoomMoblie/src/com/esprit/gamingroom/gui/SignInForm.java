/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Display;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.esprit.gamingroom.services.MembreService;
import com.codename1.ui.Container;

/**
 *
 * @author Sonia
 */
public class SignInForm extends Form {
    MembreService us;
    public SignInForm(Resources theme){
          super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        this.us = MembreService.getInstance();
        setUIID("SignInForm");

        getTitleArea().setUIID("Container");
        

        
        TextField email = new TextField("", "Email", 50, TextField.EMAILADDR);
        TextField password = new TextField("", "Password", 15, TextField.PASSWORD);
        email.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 4);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 4);

        Button loginButton = new Button("LOGIN");
        loginButton.setUIID("LoginButton");
        loginButton.requestFocus();
        loginButton.addActionListener(e -> {
            if ((email.getText().length() == 0) || (password.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all fields", new Command("OK"));
            } 
             else {
                    MembreService.getInstance().signIn(email.getText().toString(), password.getText().toString());
                    }

       });

        Button createNewAccount = new Button("CREATE NEW ACCOUNT");
        createNewAccount.setUIID("CreateNewAccountButton");
        createNewAccount.addActionListener(e -> new SignUpForm(theme).show());
//        // We remove the extra space for low resolution devices so things fit better
//        Label spaceLabel;
//        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
//            spaceLabel = new Label();
//        } else {
//            spaceLabel = new Label(" ");
//        }

        Container by = BoxLayout.encloseY(
//                spaceLabel,
                BorderLayout.center(email).
                        add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(password).
                        add(BorderLayout.WEST, passwordIcon),
                loginButton,
                createNewAccount
        );
        add(BorderLayout.CENTER, by);

        // for low res and landscape devices
        by.setScrollableY(false);
        by.setScrollVisible(false);
         
    }
    
}
