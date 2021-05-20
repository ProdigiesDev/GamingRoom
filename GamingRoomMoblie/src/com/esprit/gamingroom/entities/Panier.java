/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.entities;

/**
 *
 * @author Alaa Smeti
 */
public class Panier {
    int id;
    int produit_id;
    int commande_id;
    int quantityDemande;

    public Panier() {
    }

    public Panier(int produit_id, int quantityDemande) {
        this.produit_id = produit_id;
        this.quantityDemande = quantityDemande;
    }

    public Panier(int produit_id, int commande_id, int quantityDemande) {
        this.produit_id = produit_id;
        this.commande_id = commande_id;
        this.quantityDemande = quantityDemande;
    }

    public Panier(int id, int produit_id, int commande_id, int quantityDemande) {
        this.id = id;
        this.produit_id = produit_id;
        this.commande_id = commande_id;
        this.quantityDemande = quantityDemande;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    public int getCommande_id() {
        return commande_id;
    }

    public void setCommande_id(int commande_id) {
        this.commande_id = commande_id;
    }
    

    public int getQuantityDemande() {
        return quantityDemande;
    }

    public void setQuantityDemande(int quantityDemande) {
        this.quantityDemande = quantityDemande;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", produit_id=" + produit_id + ", commande_id=" + commande_id + ", quantityDemande=" + quantityDemande + '}';
    }

}
