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

public class Reclamation {
    
    private int id;
    private String sujet;
    private String contenue;
    private Membre membre;

    public Reclamation() {
    }

    public Reclamation(int id, String sujet, String contenue, Membre membre) {
        this.id = id;
        this.sujet = sujet;
        this.contenue = contenue;
        this.membre = membre;
    }

    public Reclamation(String sujet, String contenue, Membre membre) {
        this.sujet = sujet;
        this.contenue = contenue;
        this.membre = membre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getContenue() {
        return contenue;
    }

    public void setContenue(String contenue) {
        this.contenue = contenue;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", sujet=" + sujet + ", contenue=" + contenue + ", membre=" + membre + '}';
    }
    
    
    
    
    
}