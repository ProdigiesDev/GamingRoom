/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.services;

import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.gamingroom.entities.Reclamation;
import com.esprit.gamingroom.entities.Membre;
import com.esprit.gamingroom.utils.Statics;
import com.esprit.gamingroom.utils.Toaster;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Dah
 */
public class ReclamationService {

    boolean resultOK;
    ArrayList<Reclamation> listRec = new ArrayList();

    ToastBar.Status popup;

    public boolean add(Reclamation rec) {

        popup = Toaster.showLoading();
        String url = Statics.BASE_URL + "/api/reclamation/addreclamation?contenue=" + rec.getContenue() + "&member_id=" + rec.getMembre().getId() + "&sujet=" + rec.getSujet();
        ConnectionRequest request = new ConnectionRequest(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = request.getResponseCode() == 200;
                popup.clear();
                request.removeResponseListener((ActionListener<NetworkEvent>) this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(request);
        return resultOK;
    }

    public boolean delete(int id) {

        popup = Toaster.showLoading();
        String url = Statics.BASE_URL + "/api/reclamation/delete/" + id;
        ConnectionRequest request = new ConnectionRequest(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = request.getResponseCode() == 200;
                popup.clear();
                request.removeResponseListener((ActionListener<NetworkEvent>) this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(request);
        return resultOK;
    }

    private ArrayList<Reclamation> parseJSONAction(String textJson) {

        JSONParser j = new JSONParser();
        ArrayList<Reclamation> avises = new ArrayList();
        try {
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String, Object>> avisList = (ArrayList<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : avisList) {
                Reclamation rec = new Reclamation();
                float id = Float.parseFloat(obj.get("id").toString());
                rec.setId((int) id);
                rec.setSujet(obj.get("sujet").toString());
                rec.setContenue(obj.get("contenue").toString());

                Map<String, Object> membre = (Map<String, Object>) obj.get("membre");
                Membre m = new Membre();
                m.setNom(membre.get("nom").toString());
                m.setPrenom(membre.get("prenom").toString());
                rec.setMembre(m);
                avises.add(rec);
                popup.clear();

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return avises;
    }

    public ArrayList<Reclamation> getRec() {

        popup = Toaster.showLoading();
        String url = Statics.BASE_URL + "/api/reclamation";
        ConnectionRequest request = new ConnectionRequest(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                listRec = parseJSONAction(new String(request.getResponseData()));
                request.removeResponseListener((ActionListener<NetworkEvent>) this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(request);
        return listRec;
    }
}
