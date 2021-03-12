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
    Statu etat;
    public Commande() {
    }

    public Commande(int memberid) {
        this.memberid = memberid;
    }
    
    public Commande(int idcommande, int memberid, Date datecommande, Statu etat) {
        this.idcommande = idcommande;
        this.memberid = memberid;
        this.datecommande = datecommande;
        this.etat = etat;
    }

    public Commande(int memberid, Date datecommande, Statu etat) {
        this.memberid = memberid;
        this.datecommande = datecommande;
        this.etat = etat;
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

    public Statu getEtat() {
        return etat;
    }

    public void setEtat(Statu etat) {
        this.etat = etat;
    }

    

    @Override
    public String toString() {
        return "Commande{" + "idcommande=" + idcommande + ", memberid=" + memberid + ", datecommande=" + datecommande + ", statu=" + etat + '}';
    }

   
    public enum Statu{
        EnCours,
        Valider,
        EnAttente
    }
    
}


