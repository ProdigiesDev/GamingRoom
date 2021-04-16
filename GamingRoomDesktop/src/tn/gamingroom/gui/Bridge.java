/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

/**
 *
 * @author Farah
 */
public class Bridge {
     @SuppressWarnings("unused")
    public void println(String msg) {
        System.out.println("msg from js: " + msg);
    }

    @SuppressWarnings("unused")
    public void clickMap(double lat, double lng) {
        System.out.println("from js lat:" + lat + " lng:" + lng + ", adding marker");
        Mapping.browser.webEngine.executeScript("document.addMarker(" + lat + ", " + lng + ")");
    }
    
}
