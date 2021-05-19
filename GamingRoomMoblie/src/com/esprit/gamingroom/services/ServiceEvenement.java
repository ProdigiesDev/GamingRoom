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
import com.esprit.gamingroom.entities.Categorie;
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
    public Evenement event;
    boolean  ifIinscrit;
    String respInsc="";
    public boolean resultOK;

    public static ServiceEvenement instance = null;
    public boolean resultOk;
    private ConnectionRequest req;

    private ServiceEvenement() {
        req = new ConnectionRequest();
    }

    public static ServiceEvenement getInstance() {
        if (instance == null) {
            instance = new ServiceEvenement();
        }
        return instance;
    }

    public ArrayList<Evenement> parseEvenements(String jsonText) {
        try {
            events = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");

            for (Map<String, Object> obj : list) {
                System.out.println("obj " + obj);
                Evenement e = new Evenement();
                float idevent = Float.parseFloat(obj.get("idevent").toString());
                e.setIdevent((int) idevent);
                e.setNomEvent((String) obj.get("nomevent"));
                try {
                    e.setDateDeb((new SimpleDateFormat("yyyy-mm-dd").parse(((String) (obj.get("datedeb"))).substring(0, 10))));
                    e.setDateFin((new SimpleDateFormat("yyyy-MM-DD").parse(((String) (obj.get("datefin"))).substring(0, 10))));
                } catch (ParseException ex) {
                    System.out.println("eeeeeeeeeeeeeeeeee" + ex.getMessage());
                }

                System.out.println("dtedeb " + obj.get("datedeb"));
//                e.setDateDeb((Date)(obj.get("datedeb")));
//                e.setDateFin((Date)(obj.get("datefin")));
                e.setImage(obj.get("image").toString());
                Map<String, Object> id= (Map<String, Object>)(obj.get("categorie"));
                int idCat=(int) Float.parseFloat(id.get("idcat").toString());
                String nomcat=id.get("nomcategorie").toString();
                e.setCategorie_id(new Categorie(idCat, nomcat));
                System.out.println("caaaaaaaaaattttttttttegggggggorie "+(int) Float.parseFloat(id.get("idcat").toString()));
                e.setNbreMax_participant((int) Float.parseFloat(obj.get("nbremaxParticipant").toString()));
                e.setDescription((String) obj.get("description"));
                e.setLieu((String) obj.get("lieu"));
                e.setLienYoutube((String) obj.get("lienyoutube"));

                events.add(e);
            }

        } catch (IOException ex) {
            System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
            System.out.println(ex.getMessage());
        }
        return events;
    }
    
    public Evenement parseEvenement(String jsonText) {
        Evenement e = new Evenement();
        try {
            
            JSONParser j = new JSONParser();

            Map<String, Object> obj = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            System.out.println("eventJson "+obj);
            
                float idevent = Float.parseFloat(obj.get("idevent").toString());
                e.setIdevent((int) idevent);
                e.setNomEvent((String) obj.get("nomevent"));
                try {
                    e.setDateDeb((new SimpleDateFormat("yyyy-mm-dd").parse(((String) (obj.get("datedeb"))).substring(0, 10))));
                    e.setDateFin((new SimpleDateFormat("yyyy-MM-DD").parse(((String) (obj.get("datefin"))).substring(0, 10))));
                } catch (ParseException ex) {
                    System.out.println("eeeeeeeeeeeeeeeeee" + ex.getMessage());
                }

                System.out.println("dtedeb " + obj.get("datedeb"));
//                e.setDateDeb((Date)(obj.get("datedeb")));
//                e.setDateFin((Date)(obj.get("datefin")));
                e.setImage(obj.get("image").toString());
                Map<String, Object> id= (Map<String, Object>)(obj.get("categorie"));
                int idCat=(int) Float.parseFloat(id.get("idcat").toString());
                String nomcat=id.get("nomcategorie").toString();
                e.setCategorie_id(new Categorie(idCat, nomcat));
                e.setNbreMax_participant((int) Float.parseFloat(obj.get("nbremaxParticipant").toString()));
                e.setDescription((String) obj.get("description"));
                e.setLieu((String) obj.get("lieu"));
                e.setLienYoutube((String) obj.get("lienyoutube"));
            
            

        } catch (IOException ex) {
            System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
            System.out.println(ex.getMessage());
        }
        return e;
    }
    
    public boolean parseIfInsrit(String jsonText) {
        boolean ifInscrit = true;
        try {
            
            JSONParser j = new JSONParser();

            Map<String, Object> obj = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            System.out.println("eventJson "+obj);
            
                ifInscrit=(obj.get("message").toString()=="true")  ;
                           
            

        } catch (IOException ex) {
            System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
            System.out.println(ex.getMessage());
        }
        return ifInscrit;
    }
    
    public String parseInscription(String jsonText) {
        String respInscription ="";
        try {
            
            JSONParser j = new JSONParser();

            Map<String, Object> obj = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            System.out.println("eventJson "+obj);
            
                respInscription=obj.get("message").toString()  ;
                           
            

        } catch (IOException ex) {
            System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
            System.out.println(ex.getMessage());
        }
        return respInscription;
    }

    public ArrayList<Evenement> getAllEvenets() {
        String url = Statics.BASE_URL + "/evenement/api/liste";
        System.out.println("url " + url);
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
    
    public boolean getIfInscrit(int id) {
        //to do membre
        int idMm=62;
        String url = Statics.BASE_URL + "/evenement/api/ifInscrit/"+ id + "/" + idMm;
        System.out.println("url " + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ifIinscrit = parseIfInsrit(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ifIinscrit;
    }
    
     public Evenement getEvent(int ide) {
        //to do membre
        int idMm=62;
        String url = Statics.BASE_URL + "/evenement/api/getEvent/"+ ide;
        System.out.println("url " + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                event = parseEvenement(new String(req.getResponseData()));
               
                req.removeResponseListener(this);
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return event;
    }
     
     public String getInscription(int ide) {
          
        //to do membre
        int idMm=62;
        String url = Statics.BASE_URL + "/evenement/api/inscription/"+ ide + "/" + idMm;
        System.out.println("url " + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                respInsc = parseInscription(new String(req.getResponseData()));
               
                req.removeResponseListener(this);
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return respInsc;
    }
     
      public boolean annulerInscription(int ide) {
        //to do membre
        int idMm=62;
        String url = Statics.BASE_URL + "/evenement/api/inscription/"+ ide + "/" + idMm;
        System.out.println("url " + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this); 
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

}
