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
import com.esprit.gamingroom.entities.Produit;
import com.esprit.gamingroom.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yasmine
 */
public class ProduitService {
    
 public static ProduitService instance = null;

    //intialistaion connection request
    private ConnectionRequest req;

    public static ProduitService getInstance() {

        if (instance == null) {
            instance = new ProduitService();
        }
        return instance;
    }

    public ProduitService() {
        req = new ConnectionRequest();

    }

    //affichage 
    public ArrayList<Produit> AffichageProduit() {

        ArrayList<Produit> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/liste2";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapProduits = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listofMaps = (List<Map<String, Object>>) mapProduits.get("root");

                    // Map<String, Object> mapCategory = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    for (Map<String, Object> obj : listofMaps) {
                        Produit pr = new Produit();
                        float idprod = Float.parseFloat(obj.get("idprod").toString());
                        String image = obj.get("image").toString();
                        String libelle = obj.get("libelle").toString();

                        float prix = Float.parseFloat(obj.get("prix").toString());
                        String description = obj.get("description").toString();
                        pr.setIdprod((int) idprod);
                        pr.setImage(obj.get("image").toString());

                        /* MApping Category */
//                        Object a = obj.get("idcat");
//                        Map<String, Object> resultMap = new HashMap<String, Object>();
//                        BeanInfo info = Introspector.getBeanInfo(a.getClass());
//                        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
//                            Method reader = pd.getReadMethod();
//                            if (reader != null) {
//                                resultMap.put(pd.getName(), reader.invoke(a));
//                            }
//                        }
//                        // System.out.println("id category: "+obj.get("idCat"));  
//                        System.out.println("result: " + resultMap.get("idcat"));
                        /* End MApping */

                        // pr.setImage(image);
                        pr.setLibelle(libelle);
                        pr.setPrix(prix);
                        pr.setDescription(description);

                        //insert data into ArrayList result
                        result.add(pr);

                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }


}

