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
    int memberid;
    Date datecommande;
    String etat;

    public Commande(int idcommande, int memberid, Date datecommande, String etat) {
        this.idcommande = idcommande;
        this.memberid = memberid;
        this.datecommande = datecommande;
        this.etat = etat;
    }

    public Commande(int memberid, Date datecommande, String etat) {
        this.memberid = memberid;
        this.datecommande = datecommande;
        this.etat = etat;
    }

    public Commande() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(int idcommande) {
        this.idcommande = idcommande;
    }

    public int getMemberid() {
        return memberid;
    }

    public void setMemberid(int memberid) {
        this.memberid = memberid;
    }

    public Date getDatecommande() {
        return datecommande;
    }

    public void setDatecommande(Date datecommande) {
        this.datecommande = datecommande;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String statu) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Commande{" + "idcommande=" + idcommande + ", memberid=" + memberid + ", datecommande=" + datecommande + ", etat=" + etat + '}';
    }

   
    
}


