/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.entities;

/**
 *
 * @author Dah
 */
public class Avis {
    
    private int id;
    private String avis,
            sentiment;
    private Membre membre;
    public Avis() {
    }

    public Avis(Membre membre, String avis) {
        this.membre = membre;
        this.avis = avis;
    }

    public Avis(String avis, String sentiment, Membre membre) {
        this.avis = avis;
        this.sentiment = sentiment;
        this.membre = membre;
    }

    public Avis(int id, String avis, String sentiment, Membre membre) {
        this.id = id;
        this.avis = avis;
        this.sentiment = sentiment;
        this.membre = membre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", avis=" + avis + ", sentiment=" + sentiment + ", membre=" + membre + '}';
    }
    
    
    
}
