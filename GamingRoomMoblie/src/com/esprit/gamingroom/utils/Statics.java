/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.utils;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Farah
 */
public class Statics {
    public static final String BASE_URL="http://127.0.0.1:8000";
    public static final String dossierImagePath = "http://localhost/ProdigiesDev/GamingRoomWeb/public/images/evenement/";
    public static final String UPLOAD_IMAGE ="http://127.0.0.1:8000/images/produits/";
    public static final String dossierCoursImagePath = "http://127.0.0.1:8000/images/cours/";
    public static final String uploadCoursImagePath = "C:\\xampp\\htdocs\\ProdigiesDev\\GamingRoomWeb\\public\\images\\cours\\";
    public static final String dossierMembreImagePath = "http://127.0.0.1:8000/images/membre/";
    public static String getDossierImagePath() {
        return dossierImagePath;
    }

    
    public static final String WEBSOCKET_URL ="ws://127.0.0.1:9090/cn1-websocket-demo-server/chat";
    
    public static String[] split(String str,String splitBy) {
        ArrayList<String> splitArray = new ArrayList<>();
        StringTokenizer arr = new StringTokenizer(str, splitBy);
        while (arr.hasMoreTokens()) {
            splitArray.add(arr.nextToken());
        }
        return splitArray.toArray(new String[splitArray.size()]);
    }
}
