/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui;

import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Farah
 */
public class Browser extends Region {

    private WebView browser = new WebView();
    WebEngine webEngine = browser.getEngine();

    public Browser(double lat, double lng, boolean b) {
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(() -> {

                        if (b) {
                            String js = (String) webEngine.executeScript(
                                    "document.goToLocation( " + lat + ", " + lng + ");"
                            );
                        }
//                        else{
//                            String js = (String) webEngine.executeScript(
//                                    "document.goToLocation( " + 33.892166 + ", " + 9.561555499999997 + ");"
//                            );
//                        }
                    });
                }).start();
            }
        });

        //noinspection ConstantConditions
        if (b) {
            webEngine.load(getClass().getResource("google.html").toExternalForm());
        }else{
            webEngine.load(getClass().getResource("googleAjout.html").toExternalForm());
        }
        getChildren().add(browser);

        JSObject jsobj = (JSObject) webEngine.executeScript("window");
        jsobj.setMember("java", new Bridge());
    }

    @Override
    protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
    }

    @Override
    protected double computePrefWidth(double height) {
        return 750;
    }

    @Override
    protected double computePrefHeight(double width) {
        return 500;
    }
}
