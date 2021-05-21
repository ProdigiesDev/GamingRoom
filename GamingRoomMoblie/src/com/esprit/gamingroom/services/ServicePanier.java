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
import com.esprit.gamingroom.entities.Panier;
import com.esprit.gamingroom.entities.Produit;
import com.esprit.gamingroom.entities.Commande;
import com.esprit.gamingroom.entities.Commande.Statu;
import com.esprit.gamingroom.outils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static jdk.nashorn.internal.objects.NativeArray.map;

/**
 *
 * @author Alaa Smeti
 */
public class ServicePanier {
    public static ServicePanier instance = null ;
    
    private ConnectionRequest req;
    
    public static ServicePanier getInstance(){
        if(instance == null)
            instance = new ServicePanier();
        return instance;
    
    };
    
    
    public ServicePanier(){
        req = new ConnectionRequest(); 
    };
    
    public void affichePanMob(Panier panier) {
        
        String url = Statics.BASE_URL+"/paniermob";
        
        req.setUrl(url);
        req.addResponseListener((e)-> {
            String str = new String(req.getResponseData());
            System.out.println("data == " +str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req); 
    
    };
//    ArrayList<DetailCommande> listC = new ArrayList<>();
//        ConnectionRequest con = new ConnectionRequest();
//        con.setUrl("http://127.0.0.1:8000/mobile/"+idcmd+"/showDetails");
//        con.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//    
    public ArrayList<Panier>afficherPanier(){
        ArrayList<Panier> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/paniermob";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();           
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Panier pa = new Panier();
                      float quantityDemande = Float.parseFloat(obj.get("quantite").toString());        
                        pa.setQuantityDemande((int)quantityDemande);
                        Map<String, Object> produit = (Map<String, Object>) obj.get("produit");
                        Produit p = new Produit();
                        float idProduit = Float.parseFloat(produit.get("idProduit").toString());
                        float prix = Float.parseFloat(produit.get("prix").toString());
                        p.setIdprod((int)idProduit);
                        p.setPrix(prix);
                        p.setLibelle(produit.get("nom").toString());
                        p.setDescription(produit.get("description").toString());
                        p.setImage(produit.get("image").toString());
                        
                        //Commande 
                        Commande cmd = new Commande();
                        Map<String, Object> commande = (Map<String, Object>) obj.get("commande");
                        float idCmd = Float.parseFloat(commande.get("idCommande").toString());
                        cmd.setIdcommande((int)idCmd);
                       Map<String, Object> dateC = (Map<String, Object>) commande.get("date");
                        double td = (double) dateC.get("timestamp");
                        long xd = (long) (td * 1000L);
                        String formatd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(xd));
                        try {
                            Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(formatd);
                            cmd.setDatecommande((java.util.Date) date);
                        } catch (ParseException ex) {
                        }
                        cmd.setEtat(Statu.valueOf(commande.get("type").toString()));
                        pa.setCommande_id(cmd.getIdcommande());
                        pa.setProduit_id(p.getIdprod());
                        result.add(pa);
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result ;
    }
    
         
}
