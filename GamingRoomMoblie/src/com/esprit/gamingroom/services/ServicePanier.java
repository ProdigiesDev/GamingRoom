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
    
    public ArrayList<Panier>afficherPanier(){
        ArrayList<Panier> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/paniermob";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();           
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Panier pa = new Panier();
                      float quantityDemande = Float.parseFloat(obj.get("quantite").toString());        
                        dc.setQuantite((int)quantite);
                        Map<String, Object> produit = (Map<String, Object>) obj.get("produit");
                        Produit p = new Produit();
                        float idProduit = Float.parseFloat(produit.get("idProduit").toString());
                        float prix = Float.parseFloat(produit.get("prix").toString());
                        p.setId((int)idProduit);
                        p.setPrix(prix);
                        p.setNom(produit.get("nom").toString());
                        p.setDescription(produit.get("description").toString());
                        p.setImage(produit.get("image").toString());
                        //Etablissment
                        Map<String, Object> etab = (Map<String, Object>) produit.get("idEtab");
                        Etablissement e = new Etablissement() ;
                        int idEtab = (int)  Float.parseFloat(etab.get("id").toString());
                        e.setId(idEtab);
                        e.setImage(etab.get("image").toString());
                        e.setNom(etab.get("nom").toString());
                        e.setAdresse(etab.get("adresse").toString());
                        p.setEtab(e);
                        //Commande 
                        Commande cmd = new Commande();
                        Map<String, Object> commande = (Map<String, Object>) obj.get("commande");
                        float idCmd = Float.parseFloat(commande.get("idCommande").toString());
                        cmd.setId_commande((int)idCmd);
                       Map<String, Object> dateC = (Map<String, Object>) commande.get("date");
                        double td = (double) dateC.get("timestamp");
                        long xd = (long) (td * 1000L);
                        String formatd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(xd));
                        try {
                            Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(formatd);
                            cmd.setDate(date);
                        } catch (ParseException ex) {
                        }
                        cmd.setStatu(TypeCommande.valueOf(commande.get("type").toString()));
                        //CommandeUser
                                Utilisateur u = new Utilisateur();
                                Map<String, Object> userCmd = (Map<String, Object>) commande.get("idUser");
                                u.setNom(userCmd.get("nom").toString());
                                u.setPrenom(userCmd.get("prenom").toString());
                                u.setPhoto_profil(userCmd.get("photoProfil").toString());
                                int numTel = (int)Double.parseDouble(userCmd.get("numTel").toString());
                                System.err.println(numTel);
                                float idUser = Float.parseFloat(userCmd.get("id").toString());
                                u.setId((int)idUser);
                                u.setNum_tel((int)numTel);
                        cmd.setId_user(u);
                        dc.setC(cmd);
                        dc.setP(p);
                        listC.add(dc);
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listC ;
    }
    
         
}
