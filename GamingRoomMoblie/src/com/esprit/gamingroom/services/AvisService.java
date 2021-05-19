package com.esprit.gamingroom.services;

import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.gamingroom.entities.Avis;
import com.esprit.gamingroom.entities.Membre;
import com.esprit.gamingroom.utils.Statics;
import com.esprit.gamingroom.utils.Toaster;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dah
 */
public class AvisService {
    
    boolean resultOK;
    ArrayList<Avis> listAvis=new ArrayList();
    ToastBar.Status popup;
    public boolean add(Avis avis){
         popup=Toaster.showLoading();
        String url = Statics.BASE_URL+"/api/avis/addavis?avis="+avis.getAvis()+"&member_id="+avis.getMembre().getId();
        ConnectionRequest request = new ConnectionRequest(url);
        request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
             resultOK=request.getResponseCode() == 200;
             popup.clear();
             request.removeResponseListener((ActionListener<NetworkEvent>) this);
             }
         });
         
        
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        return resultOK;
    }
    public boolean delete(int id){
        
         popup=Toaster.showLoading();
        String url = Statics.BASE_URL+"/api/avis/delete/"+id;
        ConnectionRequest request = new ConnectionRequest(url);
        request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
             resultOK=request.getResponseCode() == 200;
             popup.clear();
             request.removeResponseListener((ActionListener<NetworkEvent>) this);
             }
         });
         
        
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        return resultOK;
    }
    private ArrayList<Avis> parseJSONAction(String textJson){
        
        JSONParser j = new JSONParser();
        ArrayList<Avis> avises=new ArrayList();
        try {
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String,Object>> avisList = (ArrayList<Map<String,Object>>) tasksListJson.get("root");
            
            for (Map<String, Object> obj : avisList) {
                Avis av = new Avis();
                float id = Float.parseFloat(obj.get("id").toString());
                av.setId((int) id);
                av.setAvis(obj.get("avis").toString());
                av.setSentiment(obj.get("sentiment").toString());
                
                Map<String, Object> membre = (Map<String, Object>) obj.get("membre");
                Membre m=new Membre();
                m.setNom(membre.get("nom").toString());
                m.setPrenom(membre.get("prenom").toString());
                av.setMembre(m);
                avises.add(av);

                popup.clear();
            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return avises;  
    }


    public ArrayList<Avis> getAvis(){
        
         popup=Toaster.showLoading();
         String url = Statics.BASE_URL+"/api/avis";
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 
             listAvis = parseJSONAction(new String(request.getResponseData()));
             request.removeResponseListener((ActionListener<NetworkEvent>) this);
             }
         });
         
        
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        return listAvis;
    }
}
