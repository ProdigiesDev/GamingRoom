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
    public int ajoutEvenement(Evenement t);
    public int modifierEvenement(Evenement t);
    public int suppressionEvenement(int t);
    public List<Evenement> listerEvenement();
    public int sinscrirEvenement(int idE,int idM);
    public int reagirEvenement(ReactionEv rE);
    public int supprimerReacC(ReactionEv rE);
    public List chercherEvenement(String s);
    public Evenement findById(int id);
    public List<Evenement> triEvenement();
    public List<Evenement> upComingEvents();
    public List<ReactionEv> listerReaction();
    public boolean eventSature(int idE);
    public boolean eventExpire(int idE);
    public boolean canReact(int idE,int idM);
    public int getReact(int idE,int idM);
    public int updateReact(ReactionEv rE);
}
