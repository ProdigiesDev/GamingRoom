/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.utils;
/*
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;*/

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
    public static String getDossierImagePath() {
        return dossierImagePath;
    }

    
    public static final String WEBSOCKET_URL ="ws://127.0.0.1:9090/cn1-websocket-demo-server/chat";
    
    public static boolean containsBadWords(String body){
       /* File file = new File("src/com/esprit/gamingroom/utils/badWords.txt");
        List<String> bodyList=Arrays.asList(body.toLowerCase().split(" "));
        
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(bodyList.contains(line)) { 
                    return true;
                }
            }
        } catch(FileNotFoundException e) { 
            e.printStackTrace();
        }
        
        return false;*/
       return false;
    }
}
