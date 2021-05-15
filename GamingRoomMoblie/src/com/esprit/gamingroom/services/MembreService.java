/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.esprit.gamingroom.gui.ProfileForm;
import com.esprit.gamingroom.utils.Statics;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sonia
 */
public class MembreService {
    public static MembreService instance;
    Resources theme;

    public static boolean resultOK=true;
    
    //initialisation connection request
    
    private ConnectionRequest con;
    
    public MembreService() {
        con = new ConnectionRequest();
    }

    public static MembreService getInstance() {
        if (instance == null) {
            instance = new MembreService();
        }
        return instance;
    }
    
    public void signIn(String email,String password){
        
        String url = Statics.BASE_URL+"/membre/api/login?email="+email+"&password="+password;
 
        con = new ConnectionRequest(url,false);
        con.setUrl(url);
        con.addResponseListener((e)->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(con.getResponseData()) + "";
            
       
            
            if(json.equals("404")){
                Dialog.show("Echec d'authentification", "Email éronné ",new Command("OK"));
            }
            else if(json.equals("400")){
                Dialog.show("Echec d'authentification", "Vérifier votre mot de passe ",new Command("OK"));
            }
            else if(json.equals("451")){
                Dialog.show("Echec", "Votre compte est désactivé",new Command("OK"));
            }
            else if(json.equals("200")){
                Dialog.show("Echec", "votre compte n'est pas encore activé veuillez attendre l'activation de l'admin",new Command("OK"));
            }
            else{
                try {
                    System.out.println("data =="+json);
                    Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    if(user.size() > 0){
                        new ProfileForm(theme).show();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MembreService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
    }
}
