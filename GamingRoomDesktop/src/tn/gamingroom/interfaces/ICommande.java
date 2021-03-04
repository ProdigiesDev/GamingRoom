/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.interfaces;

import java.util.List;
import tn.gamingroom.entities.Commande;

/**
 *
 * @author Admin
 */
public interface ICommande {
    public int ajouterCommand(Commande c);
    public int modifierCommand(Commande c);
    public int supprimerCommand(int idcommand);
    public List<Commande> consulterMonCommande(int memberid);
    public List<Commande> consulterCommande();
    
}
