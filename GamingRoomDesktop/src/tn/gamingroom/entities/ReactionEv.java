/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.entities;

/**
 *
 * @author Asus
 */
public class ReactionEv {
    public int id;
    public int evenement_id;
    public int membre_id;
    public int interaction;
    public String commentaire;

    public ReactionEv(int evenement_id, int membre_id) {
        this.evenement_id = evenement_id;
        this.membre_id = membre_id;
    }

    public ReactionEv(int evenement_id, int membre_id, int interaction) {
        this.evenement_id = evenement_id;
        this.membre_id = membre_id;
        this.interaction = interaction;
    }

    public ReactionEv(int evenement_id, int membre_id, String commentaire) {
        this.evenement_id = evenement_id;
        this.membre_id = membre_id;
        this.commentaire = commentaire;
    }

    public ReactionEv(int evenement_id, int membre_id, int interaction, String commentaire) {
        this.evenement_id = evenement_id;
        this.membre_id = membre_id;
        this.interaction = interaction;
        this.commentaire = commentaire;
    }

    public ReactionEv(int id, int evenement_id, int membre_id, int interaction, String commentaire) {
        this.id = id;
        this.evenement_id = evenement_id;
        this.membre_id = membre_id;
        this.interaction = interaction;
        this.commentaire = commentaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvenement_id() {
        return evenement_id;
    }

    public void setEvenement_id(int evenement_id) {
        this.evenement_id = evenement_id;
    }

    public int getMembre_id() {
        return membre_id;
    }

    public void setMembre_id(int membre_id) {
        this.membre_id = membre_id;
    }

    public int getInteraction() {
        return interaction;
    }

    public void setInteraction(int interaction) {
        this.interaction = interaction;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "reactionEv{" + "id=" + id + ", evenement_id=" + evenement_id + ", membre_id=" + membre_id + ", interaction=" + interaction + ", commentaire=" + commentaire + '}';
    }
    
    
    
}
