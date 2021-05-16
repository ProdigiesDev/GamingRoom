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
import com.codename1.ui.events.ActionListener;
import com.esprit.gamingroom.entities.Cours;
import com.esprit.gamingroom.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author eyatr
 */
public class CoursServices {
     //var
    boolean resultOK;
    ConnectionRequest req;
    static CoursServices instance;
    ArrayList<Cours> cours = new ArrayList<>();
    
    
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
    
//    //ADD TASK 
//    public boolean addTaskAction(Cours c){
//        
//        String url = Statics.BASE_URL + "/cour/api/"+ c.getId()+ "/" + c.getNomCours()+ "/" +c.getDescription()+ "/" +c.getNb_participants()
//                + "/" +c.getMembre_id()+ "/" +c.getDate_creation()+ "/" +c.getTags()+ "/" +c.getImage()+"/"+c.getCategorie_id()+ "/" +c.getLienYoutube();
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener((evt) -> {
//            resultOK = req.getResponseCode()==200;
//        });
//        
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
     //PARSE Cours JSON : convert JSON to java objects
    public ArrayList<Cours> parseJSONAction(String textJson){
        
        JSONParser j = new JSONParser();
        
        try {
            
            Map<String, Object> coursListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String,Object>> CoursList = (ArrayList<Map<String,Object>>) coursListJson.get("root");
            
            for (Map<String, Object> obj : CoursList) {
                
                Cours c = new Cours();
                
           
                   
                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int) id);
                c.setNomCours(obj.get("nomcours").toString());
                c.setDescription(obj.get("description").toString());
                c.setNb_participants((int) Float.parseFloat(obj.get("nbParticipant").toString()));
                //c.setMembre_id((int) Float.parseFloat(obj.get("membre").toString()));
                //c.setDate_creation((Date)(obj.get("dateCreation")));
                c.setTags(obj.get("tags").toString());
                c.setImage(obj.get("imagecours").toString());//lezem nrodha image file
                //c.setCategorie_id((int) Float.parseFloat(obj.get("categorie").toString()));
                c.setLienYoutube(obj.get("lienyoutube").toString());

                
                cours.add(c);

            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return cours;  
    }



    //GET COURS
    public ArrayList<Cours> getCours(){
        
         String url = Statics.BASE_URL+"/cour/api/";
         System.out.println(url);
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 
             cours = parseJSONAction(new String(request.getResponseData()));
             request.removeResponseListener((ActionListener<NetworkEvent>) this);
             }
         });
       
        NetworkManager.getInstance().addToQueueAndWait(request);
        return cours;
    }
    

    
}
