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
    private static String dossierImagePath ="C:/Users/Farah/Desktop/Esprit/3eme/sem2/PIDEV/Sprint1/GamingRoomRepo/assetsEvenement/Images/";
    private static String dossierImageUtilEventPath ="C:/Users/Farah/Desktop/Esprit/3eme/sem2/PIDEV/Sprint1/GamingRoomRepo/assetsEvenement/";
    public static String getImagePath() {
        return dossierImagePath;
    }

    public static String getDossierImageUtilEventPath() {
        return dossierImageUtilEventPath;
    }
    
}
