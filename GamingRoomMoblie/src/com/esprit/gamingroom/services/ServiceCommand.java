/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.Services;

import com.esprit.gamingroom.entities.Commande;
import com.esprit.gamingroom.entities.Produit;
import com.esprit.gamingroom.outils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author elbrh
 */
public class ServiceCommand {
    
//    public Commande PassCmd(Commande c, Map<Produit, Integer> produits) {
//        Commande c1 = new Commande();
//        ConnectionRequest req = new ConnectionRequest();
//        req.setUrl("http://127.0.0.1:8000/mobile/"
//                + c.getId_user().getId() + "/cmdM");
//        req.addResponseListener((NetworkEvent evtl) -> {
//            JSONParser jsonp = new JSONParser();
//            try {
//                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
//                System.out.println(tasks);
//                float id = Float.parseFloat(tasks.get("idCommande").toString());
//                c1.setId_commande((int) id);
//                for (Produit p : produits.keySet()) {
//                    ConnectionRequest req2 = new ConnectionRequest();
//                    req2.setUrl("http://127.0.0.1:8000/mobile/" + p.getId() + "/" + produits.get(p) + "/"
//                            + (int) id + "/cmdPM");
//                    req2.addResponseListener((NetworkEvent evtl1) -> {
//                        
//                    });
//                    NetworkManager.getInstance().addToQueue(req2);
//                }
//                ConnectionRequest con = new ConnectionRequest();
//                con.setUrl("http://127.0.0.1:8000/mobile/" + (int) id + "/GeneratePDF");
//                con.addResponseListener(new ActionListener<NetworkEvent>() {
//                    @Override
//                    public void actionPerformed(NetworkEvent evt) {
//                        
//                        ShowPdf s = new ShowPdf((int) id);
//                        s.getF().show();
//                        
//                    }
//                    
//                });
//                NetworkManager.getInstance().addToQueue(con);
//                
//            } catch (IOException ex) {
//            }
//            
//        });
//        NetworkManager.getInstance().addToQueue(req);
//        
//        return c1;
//    }
//    
//    public ArrayList<Commande> getCommands() {
//        ArrayList<Commande> listC = new ArrayList<>();
//        ConnectionRequest con = new ConnectionRequest();
//        con.setUrl("http://127.0.0.1:8000/mobile/" + ConnectedUser.getUser().getId() + "/GetCmds");
//        con.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                JSONParser jsonp = new JSONParser();                
//                try {
//                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
//                    System.out.println(tasks);
//                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
//                    for (Map<String, Object> obj : list) {
//                        Commande c = new Commande();
//                        float id = Float.parseFloat(obj.get("idCommande").toString());                        
//                        c.setId_commande((int) id);
//                        Map<String, Object> d = (Map<String, Object>) obj.get("date");
//                        double td = (double) d.get("timestamp");
//                        long xd = (long) (td * 1000L);
//                        String formatd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(xd));
//                        try {
//                            Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(formatd);
//                            System.out.println(date);
//                            c.setDate(date);
//                        } catch (ParseException ex) {
//                        }
//                        c.setStatu(TypeCommande.valueOf(obj.get("type").toString()));
//                        listC.add(c);
//                    }
//                } catch (IOException ex) {
//                }
//                
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);
//        return listC;
    }