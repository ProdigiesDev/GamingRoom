/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.outils;

/**
 *
 * @author Dah
 */
public class Env {
     public static final String keyMeaningcloudApi = "fb2edfd3397580535796144ef45588eb";
    private static String dossierImageUtilEventPath ="C:\\Users\\eyatr\\Desktop\\ProdgiesDev\\GamingRoomWeb\\public\\images\\evenement\\";
    private static final String youtubeApiKey = "AIzaSyA_8Z1_SB9rtueiZXM3kMLKHlV5kOqTGSY";
    private static String dossierImagePath = "C:\\Users\\eyatr\\Desktop\\ProdgiesDev\\GamingRoomWeb\\public\\images\\";
    private static String assetsEvenement = "C:\\Users\\eyatr\\Desktop\\ProdgiesDev\\GamingRoomWeb\\assetsEvenement\\";

    private static final int port = 8087;

    public static String getImagePath() {
        return dossierImagePath;
    }

    public static String getDossierImageUtilEventPath() {
        return dossierImageUtilEventPath;
    }
    
    public static String getYoutubeApiKey() {
        return youtubeApiKey;
    }

    public static String getKeyMeaningcloudApi() {
        return keyMeaningcloudApi;
    }

    public static int getPort() {
        return port;
    }

    public static String getAssetsEvenement() {
        return assetsEvenement;
    }

}
