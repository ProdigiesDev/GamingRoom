/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.entities;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Commande {
    int idcommande;
    Date datecommande;
    int quantite;
    String statu;

    public Commande(int idcommande, Date datecommande, int quantite, String statu) {
        this.idcommande = idcommande;
        this.datecommande = datecommande;
        this.quantite = quantite;
        this.statu = statu;
    }

    public Commande(Date datecommande, int quantite, String statu) {
        this.datecommande = datecommande;
        this.quantite = quantite;
        this.statu = statu;
    }

    public int getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(int idcommande) {
        this.idcommande = idcommande;
    }

    public Date getDatecommande() {
        return datecommande;
    }

    public void setDatecommande(Date datecommande) {
        this.datecommande = datecommande;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    @Override
    public String toString() {
        return "Commande{" + "idcommande=" + idcommande + ", datecommande=" + datecommande + ", quantite=" + quantite + ", statu=" + statu + '}';
    }
    
    
}


