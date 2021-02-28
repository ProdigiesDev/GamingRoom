/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.entities;

/**
 *
 * @author Dah
 */
public class Reclamation {
    
    private int id;
    private String sujet;
    private String contenue;
    private int membre_id;

    public Reclamation(int id, String sujet, String contenue, int membre_id) {
        this.id = id;
        this.sujet = sujet;
        this.contenue = contenue;
        this.membre_id = membre_id;
    }

    public Reclamation(String sujet, String contenue, int membre_id) {
        this.sujet = sujet;
        this.contenue = contenue;
        this.membre_id = membre_id;
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

    public int getMembre_id() {
        return membre_id;
    }

    public void setMembre_id(int membre_id) {
        this.membre_id = membre_id;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", sujet=" + sujet + ", contenue=" + contenue + ", membre_id=" + membre_id + '}';
    }
    
    
    
    
    
}
