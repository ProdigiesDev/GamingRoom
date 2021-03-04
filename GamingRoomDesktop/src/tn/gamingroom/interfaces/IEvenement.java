/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.interfaces;

import java.util.List;
import tn.gamingroom.entities.Evenement;
import tn.gamingroom.entities.ReactionEv;

/**
 *
 * @author Asus
 */
public interface IEvenement {
    public void ajoutEvenement(Evenement t);
    public void modifierEvenement(Evenement t);
    public void suppressionEvenement(Evenement t);
    public List<Evenement> listerEvenement();
    public void sinscrirEvenement(int idE,int idM);
    public void reagirEvenement(ReactionEv rE);
    public void supprimerReacC(ReactionEv rE);
    public List chercherEvenement(String s);
    public Evenement findById(int id);
}
