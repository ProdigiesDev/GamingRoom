/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.esprit.gamingroom.utils.Statics;
import com.codename1.io.CharArrayReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.esprit.gamingroom.entities.Evenement;
import java.util.Date;


/**
 *
 * @author Farah
 */
public class ServiceEvenement {
    public ArrayList<Evenement> events;
    
    public static ServiceEvenement instance=null;
    public boolean resultOk;
    private ConnectionRequest req;
    
    public static ServiceEvenement getInstance(){
        if(instance==null){
            instance=new ServiceEvenement();
        }
        return instance;
    }
    
    public ArrayList<Evenement> parseEvenements(String jsonText){
        try {
            events=new ArrayList<>();
            JSONParser j =new JSONParser();
            
            Map<String,Object> eventsListJson= j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list =(List<Map<String,Object>>)eventsListJson.get("root");
            
            for(Map<String,Object> obj : list){
                Evenement e=new Evenement();
                float idevent = Float.parseFloat(obj.get("idevent").toString());
                e.setIdevent((int)idevent);
                e.setNomEvent(obj.get("nomEvent").toString());
                e.setDateDeb((new SimpleDateFormat("dd/MM/yyyy").parse((obj.get("dateDeb").toString()))));
                e.setDateFin((new SimpleDateFormat("dd/MM/yyyy").parse((obj.get("dateFin").toString()))));
                e.setImage(obj.get("image").toString());
                e.setNbreMax_participant((int)Float.parseFloat(obj.get("nbreMax_participant").toString()));
                e.setDescription(obj.get("description").toString());
                e.setLieu(obj.get("lieu").toString());
                e.setLienYoutube(obj.get("lienYoutube").toString());
                
                events.add(e);
            }
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        return events;
    }
    
    public ArrayList<Evenement> getAllEvenets(){
        String url = Statics.BASE_URL+"/evenement/api/liste";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvenements(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }
    
    
}
