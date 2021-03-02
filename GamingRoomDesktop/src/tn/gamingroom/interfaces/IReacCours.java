/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.interfaces;

import java.util.List;
import tn.gamingroom.entities.Cours;
import tn.gamingroom.entities.ReacCours;

/**
 *
 * @author eyatr
 */
public interface IReacCours {
    public void ajouterReacC(ReacCours r);

    public void supprimerReacC(ReacCours r);

    public void updateReacC(ReacCours r);

    public List<ReacCours> displayReacC();
    
}
