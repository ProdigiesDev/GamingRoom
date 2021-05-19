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
import com.codename1.messaging.Message;
import com.codename1.ui.Display;
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
   
    //add
 public boolean addCoursAction(Cours c){
        
        String url = Statics.BASE_URL + "/cour/api/add/?nomcours="+ c.getNomCours()
                + "&description=" +c.getDescription()+
                "&nbParticipant=" +c.getNb_participants()  
                + "&member_id=" +c.getMembre_id()
                +"&tags=" 
                +c.getTags()+ "&imagecours=" 
                +c.getImage()+"&idcat="+
                c.getCategorie_id()
                + "&lienyoutube=" +c.getLienYoutube();
        req.setUrl(url);
        System.out.println("url"+url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);   
           }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        Message m = new Message("Cher Admin j'ai le plaisir de vous informer que j'ai ajouté un "
                + "nouveau cours nommé  "+c.getNomCours()+"");
//m.getAttachments().put(textAttachmentUri, "text/plain");
//m.getAttachments().put(imageAttachmentUri, "image/png");
Display.getInstance().sendMessage(new String[] {"eya.trifi@esprit.tn"}, "Ajout d'un nouveau cours",m);
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
               // c.setCategorie_id((int) Float.parseFloat(obj.get("categorie").getClass().toString()));
                Map<String, Object> idcat= (Map<String, Object>)(obj.get("categorie"));
                c.setCategorie_id((int) Float.parseFloat(idcat.get("idcat").toString()));
                Map<String, Object> idmem= (Map<String, Object>)(obj.get("membre"));
                c.setMembre_id((int) Float.parseFloat(idmem.get("id").toString()));
                c.setLienYoutube((String)obj.get("lienyoutube"));
                System.out.println("hohoho"+obj);
                System.out.println("KAKA"+ c);

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
                Map<String, Object> idcat= (Map<String, Object>)(obj.get("categorie"));
                c.setCategorie_id((int) Float.parseFloat(idcat.get("idcat").toString()));
                Map<String, Object> idmem= (Map<String, Object>)(obj.get("membre"));
                c.setMembre_id((int) Float.parseFloat(idmem.get("id").toString()));
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
     
//     public boolean updateCours(Cours c,int id) {
// String url = Statics.BASE_URL + "/cour/api/update/"+id+"?"+"&nomcours="+ c.getNomCours()
//                + "&description=" +c.getDescription()+
//                "&nbParticipant=" +c.getNb_participants()  
//                + "&member_id=" +c.getMembre_id()
//                +"&tags=" 
//                +c.getTags()+ "&imagecours=" 
//                +c.getImage()+"&idcat="+
//                c.getCategorie_id()
//                + "&lienyoutube=" +c.getLienYoutube();         
// System.out.println(url);
//        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this); //Supprimer cet actionListener                
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
     public boolean modifierCours(Cours c) {
        req.setUrl(Statics.BASE_URL + "/cour/api/update");
        req.addArgument("id", String.valueOf(c.getId()));
        req.addArgument("nomcours", c.getNomCours());
        req.addArgument("description", c.getDescription());
        req.addArgument("nbParticipant", String.valueOf(c.getNb_participants()));
        req.addArgument("member_id", String.valueOf(c.getMembre_id()));
        req.addArgument("tags", c.getTags());
        req.addArgument("imagecours", c.getImage());
        req.addArgument("idcat", String.valueOf(c.getCategorie_id()));
        req.addArgument("lienyoutube", c.getLienYoutube());
        

       

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode()==200;
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
//     public  void sendemail2()
//     {
//         Message m = new Message("Body of message");
//m.getAttachments().put("bien", "text/plain");
//Display.getInstance().sendMessage(new String[] {"eya.trifi@esprit.tn"}, " Date de formation finie", m);
//     }
////               void sendFileViaEmail(String hi2, String file) {
////    Message m = new Message("Test message");
////    //m.getAttachments().put(file, "image/jpeg");
////    m.setAttachment(hi2);
////    m.setAttachmentMimeType(Message.MIME_IMAGE_JPG);
////    //m.setMimeType(Message.MIME_IMAGE_JPG);
////    //m.getAttachments().put(imageAttachmentUri, "image/png");
////    Display.getInstance().sendMessage(new String[] {"mohamedamine.turki@esprit.tn"}, "test message cn1", m);
//}
//    
}

