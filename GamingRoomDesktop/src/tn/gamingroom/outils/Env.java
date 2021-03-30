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
    private static final String keyMeaningcloudApi="fb2edfd3397580535796144ef45588eb";
    private static final int port=8087;
    private static String dossierImagePath="C:\\Users\\Dah\\Desktop\\demo\\images\\";

    public static String getKeyMeaningcloudApi() {
        return keyMeaningcloudApi;
    }

    public static String getImagePath() {
        return dossierImagePath;
    }
    
    public static int getPort() {
        return port;
    }
}
