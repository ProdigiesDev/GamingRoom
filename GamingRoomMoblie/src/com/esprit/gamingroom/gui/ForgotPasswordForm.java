/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.gamingroom.services.MembreService;

/**
 *
 * @author Sonia
 */
public class ForgotPasswordForm extends Form {
    
    MembreService us;
    
     public ForgotPasswordForm(Resources theme){
            super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        this.us = MembreService.getInstance();
        setUIID("SignInForm");

        getTitleArea().setUIID("Container");
        TextField email = new TextField("", "Email", 50, TextField.EMAILADDR);
        TextField code = new TextField("","code",6 , TextField.NUMERIC);
        TextField password = new TextField("", "Password", 15, TextField.PASSWORD);
        TextField vpassworField = new TextField("", "Repeat Password", 15, TextField.PASSWORD);
        Button validerButton = new Button("Valider");
        Label text = new Label();
        
        email.getAllStyles().setMargin(LEFT, 0);
        code.getAllStyles().setMargin(LEFT, 0);

        password.setVisible(false);
        vpassworField.setVisible(false);
        text.setVisible(false);
        //////////
        Label backIcon = new Label("", "TextField");
        backIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(backIcon, FontImage.MATERIAL_ASSIGNMENT_RETURN, 2);

        //////////////
        
        Button sendButton = new Button("Send Email");
        sendButton.setUIID("LoginButton");
         sendButton.addActionListener(e -> {
              if ((email.getText().length() == 0)) {
                Dialog.show("Alert", "Please enter email", new Command("OK"));
            } 
             else {
                    MembreService.getInstance().sendEmail(email.getText());
                    }

         });
         
         text.setText("After code verifiation :");
         Button verifButton = new Button("Verifier");
        verifButton.setUIID("LoginButton");
        verifButton.addActionListener(e -> {
       if ((code.getText().length() == 0)){
           Dialog.show("Alert", "Veuillez inserer un code", new Command("OK"));
       }
       else{
      
            boolean response = MembreService.getInstance().verifCode(code.getText());
            if(response){
                 Dialog.show("Success", "Vous pouvez changez votre mot de passe", new Command("OK"));
                 email.setEditable(false);
                 text.setVisible(true);
                   password.setVisible(true);
                vpassworField.setVisible(true);
                validerButton.setVisible(true);
                System.out.println(response);
                System.out.println(code.getText());
               
            }
            else{
                Dialog.show("Error", "Code invalide", new Command("OK"));
                 password.setVisible(false);
                vpassworField.setVisible(false);
                validerButton.setVisible(false);
                text.setVisible(false);
                
            } }
        });
        
        
        validerButton.setVisible(false);
        validerButton.setUIID("LoginButton");
         validerButton.addActionListener(e -> {
             if ((password.getText().length() == 0) || (vpassworField.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all fields", new Command("OK"));
            } 
             else{
                 MembreService.getInstance().forgotPaswword(email, password, vpassworField);
                 System.out.println("done");
                 
             }
         });
         
         ///////
          Button retour = new Button("<< Retour SignIn");
        retour.setUIID("CreateNewAccountButton");
        retour.addActionListener(e -> new SignInForm(theme).show());
        ///////

         
        Container by = BoxLayout.encloseY(
//                spaceLabel,
               
                email,
                sendButton,
                code,
                verifButton,
                text,
                password,
                vpassworField,
                validerButton,
                retour
               
                
        );
        
        add(BorderLayout.CENTER, by);
     }
}
