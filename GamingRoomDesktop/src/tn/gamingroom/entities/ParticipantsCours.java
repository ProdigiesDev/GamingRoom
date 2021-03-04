/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.entities;

/**
 *
 * @author eyatr
 */
public class ParticipantsCours {
    private int id;
    private int membre_id;
    private int cour_id;
    
    public ParticipantsCours() {
    }

    public ParticipantsCours(int id, int membre_id, int cour_id) {
        this.id = id;
        this.membre_id = membre_id;
        this.cour_id = cour_id;
    }

    public ParticipantsCours(int membre_id, int cour_id) {
        this.membre_id = membre_id;
        this.cour_id = cour_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMembre_id() {
        return membre_id;
    }

    public void setMembre_id(int membre_id) {
        this.membre_id = membre_id;
    }

    public int getCour_id() {
        return cour_id;
    }

    public void setCour_id(int cour_id) {
        this.cour_id = cour_id;
    }

    @Override
    public String toString() {
        return "ParticipantsCours{" + "id=" + id + ", membre_id=" + membre_id + ", cour_id=" + cour_id + '}';
    }
    
    

    
    
    
}
