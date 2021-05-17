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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.esprit.gamingroom.entities.Cours;
import com.esprit.gamingroom.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author eyatr
 */
public class CoursServices {
     //var
    boolean resultOK;
    ConnectionRequest req;
    public ArrayList<Cours> courses;
    public Cours cours;
    public static CoursServices instance = null;

    
    
    //constructor
    private CoursServices() {
        req = new ConnectionRequest();
    }
    
    //SINGLETON
    public static CoursServices getInstance(){
        
        if (instance == null) {
            instance = new CoursServices();
        }
        
        return instance;
    }
   
    //ADD Cours
    public boolean addCoursAction(Cours c){
        
        String url = Statics.BASE_URL + "/cour/api/add?" + c.getNomCours()+ "&" +c.getDescription()+
                "&" +c.getNb_participants()  + "&" +c.getMembre_id()+"&"+c.getDate_creation()+ "&" 
                +c.getTags()+ "&" +c.getImage()+"&"+c.getCategorie_id()+ "&" +c.getLienYoutube();
        req.setUrl(url);
        System.out.println(url);
        //req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);   
           }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    } 
     //PARSE Cours JSON : convert JSON to java objects
     public ArrayList<Cours> parseCourses(String jsonText) {
        try {
            courses = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");

            for (Map<String, Object> obj : list) {
                System.out.println("obj " + obj);
                Cours c = new Cours();
                float id = Float.parseFloat(obj.get("id").toString());
               c.setId((int) id);
                c.setNomCours((String)obj.get("nomcours"));
                c.setDescription((String)obj.get("description"));
                c.setNb_participants((int) Float.parseFloat(obj.get("nbParticipant").toString()));
//                try {
                //c.setMembre_id((int) Float.parseFloat(obj.get("membre").toString()));
                //c.setDate_creation((Date)(obj.get("dateCreation")));
                //c.setDescription(obj.get("dateCreation").toString());
//                    c.setDate_creation((new SimpleDateFormat("yyyy-mm-dd").parse(((String) (obj.get("dateCreation"))).substring(0, 10))));
//                } catch (ParseException ex) {
//                    Logger.getLogger(CoursServices.class.getName()).log(Level.SEVERE, null, ex);
//                }
                String DataConverter = obj.get("dateCreation").toString();
                c.setDate_creation(DataConverter);
                c.setTags((String)obj.get("tags"));
                c.setImage((String)obj.get("imagecours"));//lezem nrodha image file
                //c.setCategorie_id((int) Float.parseFloat(obj.get("categorie").toString()));
                c.setLienYoutube((String)obj.get("lienyoutube"));

                courses.add(c);
            }

        } catch (IOException ex) {
            System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
            System.out.println(ex.getMessage());
        }
        return courses;
    }
    //parse from farah
     public Cours parsecours(String jsonText) {
        Cours c = new Cours();
        try {
            
            JSONParser j = new JSONParser();

            Map<String, Object> obj = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            System.out.println("eventJson "+obj);
            
                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int) id);
                c.setNomCours((String)obj.get("nomcours"));
                c.setDescription((String)obj.get("description"));
                c.setNb_participants((int) Float.parseFloat(obj.get("nbParticipant").toString()));
//                try {
                //c.setMembre_id((int) Float.parseFloat(obj.get("membre").toString()));
                //c.setDate_creation((Date)(obj.get("dateCreation")));
                //c.setDescription(obj.get("dateCreation").toString());
//                    c.setDate_creation((new SimpleDateFormat("yyyy-mm-dd").parse(((String) (obj.get("dateCreation"))).substring(0, 10))));
//                } catch (ParseException ex) {
//                    Logger.getLogger(CoursServices.class.getName()).log(Level.SEVERE, null, ex);
//                }
                String DataConverter = obj.get("dateCreation").toString();
                c.setDate_creation(DataConverter);
                c.setTags((String)obj.get("tags"));
                c.setImage((String)obj.get("imagecours"));//lezem nrodha image file
                //c.setCategorie_id((int) Float.parseFloat(obj.get("categorie").toString()));
                c.setLienYoutube((String)obj.get("lienyoutube"));

            

        } catch (IOException ex) {
            System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
            System.out.println(ex.getMessage());
        }
        return c;
    }

 

    //GET ALL COURS
   public ArrayList<Cours> getAllCours() {
        String url = Statics.BASE_URL + "/cour/api/";
        System.out.println("url " + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                courses = parseCourses(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return courses;
    }
     //GET ONE COURS
    public Cours getOneCours(int id) {
        String url = Statics.BASE_URL + "/cour/api/"+id;
        System.out.println("url " + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cours= parsecours(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cours;
    }
    
    
     public boolean deleteCours(int id){
        String url = Statics.BASE_URL+"/cour/api/delete/"+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
            return resultOK;                      
    }
    

    
}
