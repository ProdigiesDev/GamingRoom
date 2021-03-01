/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.interfaces;

import java.util.List;
import tn.gamingroom.entities.Cours;

/**
 *
 * @author eyatr
 */
public interface ICours {

    public void ajouterCours(Cours t);

    public void supprimerCours(Cours t);

    public void updateCours(Cours t);

    public List<Cours> displayCours();

}
