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

    public void ajouterCours(Cours c);

    public void supprimerCours(Cours c);

    public void updateCours(Cours c);

    public List searchCours(String nomCours);

    public List trierCoursID();
    
       public List displayprefcours(int membre_id);
    

    public List<Cours> displayCours();

}
