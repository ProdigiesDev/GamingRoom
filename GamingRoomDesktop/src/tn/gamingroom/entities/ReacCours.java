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
public class ReacCours {
    private int id;
    private int interaction;
    private String commentaire;
    private int membre_id ;
    private int cour_id  ;

    public ReacCours() {
    }

    public ReacCours(int id, int nb_interaction, String commentaire, int membre_id, int cour_id) {
        this.id = id;
        this.interaction = interaction;
        this.commentaire = commentaire;
        this.membre_id = membre_id;
        this.cour_id = cour_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNb_interaction() {
        return interaction;
    }

    public void setNb_interaction(int nb_interaction) {
        this.interaction = nb_interaction;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
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
        return "ReacCours{" + "id=" + id + ", nb_interaction=" + interaction + ", commentaire=" + commentaire + ", membre_id=" + membre_id + ", cour_id=" + cour_id + '}';
    }
    
    
    
}
