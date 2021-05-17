/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.esprit.gamingroom.entities.Membre;
import com.esprit.gamingroom.entities.UserSession;
import com.esprit.gamingroom.gui.ProfileCoachForm;
import com.esprit.gamingroom.gui.ProfileMembreForm;
import com.esprit.gamingroom.utils.SendEmail;
import com.esprit.gamingroom.utils.Statics;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;




/**
 *
 * @author Sonia
 */
public class MembreService {
    public static MembreService instance;
    Resources theme;
    int randomCode ; 


    
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
        System.out.println(url);
        System.out.println("i am here");
        con = new ConnectionRequest(url,false);
        //con.setUrl(url);

        con.addResponseListener((e)->{
            
            //int result = con.getResponseCode();
         // System.out.println(result+"*******");
           // System.out.println(con.getResponseErrorMessage());
            
            JSONParser j = new JSONParser();
            
            String json = new String(con.getResponseData()) + "";
            
            
            if(json.equals("failed") ){
                Dialog.show("Echec d'authentification", "Email éronné ",new Command("OK"));
            }
            else if(json.equals("pass")){
                Dialog.show("Echec d'authentification", "Vérifier votre mot de passe ",new Command("OK"));
            }
            else if(json.equals("Cdesactive")){
                Dialog.show("Echec", "Votre compte est désactivé",new Command("OK"));
            }
            else if(json.equals("coach")){
                Dialog.show("Echec", "votre compte n'est pas encore activé veuillez attendre l'activation de l'admin",new Command("OK"));
            }
            else{
//                JSONParser j = new JSONParser();
//                 String json = new String(con.getResponseData()) + "";
                try {
                    System.out.println("data =="+json);

                   
                    Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    
                   
                    if(user.size() > 0){
                        
                         try {
                        Membre m = parseListUserJson(json);
                        String str = new String(con.getResponseData());
                        System.out.println("Test"+str);
                        System.out.println("hakouna matata "+m.getRole());
                        if(m.getRole() == Membre.Role.Membre){
                            new ProfileMembreForm(theme).show();
                            UserSession userSession = new UserSession(m,m.getRole());
                        }
                        else if (m.getRole() == Membre.Role.Coach){
                             new ProfileCoachForm(theme).show();
                             UserSession userSession = new UserSession(m,m.getRole());
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(MembreService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MembreService.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
            
        });
             
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
     public Membre parseListUserJson(String json) throws ParseException {

        Membre u = new Membre();
        try {
            JSONParser j = new JSONParser();

            Map<String, Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray()));
            u.setId((int) (double) obj.get("id"));
            u.setNom(obj.get("nom").toString());
            u.setPrenom(obj.get("prenom").toString());
            if(obj.get("genre").toString().equals("Femme")){
                u.setGenre(Membre.Genre.Femme);
            }
            else{
                u.setGenre(Membre.Genre.Homme);
            }
            u.setTel(obj.get("numeroTel").toString());
            u.setEmail(obj.get("email").toString());
            u.setImage(obj.get("image").toString());
            if(obj.get("role").toString().equals("Coach")){
                u.setRole(Membre.Role.Coach);
            }
            else if(obj.get("role").toString().equals("Membre")){
                u.setRole(Membre.Role.Membre);
            }
 
            UserSession z = UserSession.getInstance(u);
            System.out.println(UserSession.instance);

        } catch (IOException ex) {
        }

        return u;
    }
     /////////////// forgot password /////////////////////////////
     public void sendEmail(String email){
     

        try {
            Random ran = new Random();
            randomCode=ran.nextInt(999999);
            String text = "Hello,"+"\n"+"this is your reset code: "+randomCode+"\n"+"GamingRoom.";
            boolean b =SendEmail.sendMail(email,"Reseting Code",text);
            if(b==true){
 
              Dialog.show("Success", "Un code est enoyé à : "+email,new Command("OK"));

            }
            else{

              Dialog.show("Error", "ERROR! code hasn't been sent to the email",new Command("Cancel"));
                
            }
        } catch (MessagingException ex) {
            Logger.getLogger(MembreService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
     }
     public boolean verifCode(String random){
         System.out.println(randomCode);
         return random.equals(Integer.toString(randomCode));
     }
      public void forgotPaswword(TextField email,TextField password,TextField vpass){
          
         
          String url = Statics.BASE_URL+"/membre/api/fpass?email="+email.getText()+"&password="+password.getText();
          con = new ConnectionRequest(url,false);
          
                 
            con.addResponseListener((e)->{
               JSONParser j = new JSONParser();
            
            String json = new String(con.getResponseData()) + "";
             
             if(json.equals("failed") ){
                Dialog.show("Erreur", "Email éronné ",new Command("OK"));
            }
             else{
                 if(password.getText().equals(vpass)){
               
                     byte[]data = (byte[]) e.getMetaData();
                     String responseData = new String(data);}
                 else {
                                     Dialog.show("Erreur", "vérifier vos champs ",new Command("OK"));

                 }
                 }
              
             
             
            
          }); 
//           else{
//                               Dialog.show("Error", "The code that you entered is not correct ",new Command("OK"));
//
//           }
      
          
          NetworkManager.getInstance().addToQueueAndWait(con);
      }
      //////////////////// fin forgot password //////////////////////////////////
}
