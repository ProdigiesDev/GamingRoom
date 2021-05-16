/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui.membre;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.gamingroom.gui.ProfileForm;
import com.esprit.gamingroom.gui.SignInForm;
import com.esprit.gamingroom.gui.SignUpForm;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sonia
 */
public class HomeForm extends Form {
    Form current;
    Resources theme;
    
    public HomeForm(){
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("Choisir une option"));
        Button btnSignIn = new Button("Sign In");
        Button btnSignUp = new Button("Sign Up");
        Button profile = new Button("profile");
        btnSignIn.addActionListener(e -> new SignInForm(theme).show());
        btnSignUp.addActionListener(e -> new SignUpForm(current).show());
        profile.addActionListener(e -> {
            new ProfileForm(theme).show();
        });
        addAll(btnSignIn,btnSignUp,profile);
        
    }
    
}
