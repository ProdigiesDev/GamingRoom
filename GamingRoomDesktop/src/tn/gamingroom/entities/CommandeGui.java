/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.entities;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class CommandeGui extends Commande{
    
    
    double prixTot;

    public CommandeGui(double prixTot, int idcommande, int memberid, java.sql.Date datecommande, Statu etat) {
        super(idcommande, memberid, datecommande, etat);
        this.prixTot = prixTot;
    }

    public double getPrixTot() {
        return prixTot;
    }

    public void setPrixTot(double prixTot) {
        this.prixTot = prixTot;
    }

   

    
    @Override
    public String toString() {
        return super.toString()+ " prixUn=" + prixTot + '}';
    }
    
    
    
}
