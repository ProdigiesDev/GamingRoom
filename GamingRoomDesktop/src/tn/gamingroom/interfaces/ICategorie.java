/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.interfaces;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sonia
 * @param <C>
 */
public interface ICategorie <C> {
    
     public int ajouterCategorie(C c);
     public int supprimerCategorie(C c);
     public int modifierCategorie(C c);
     public List<C> DisplayCategorie();
     public List<C> RechercherCategorie(String x);
     public ArrayList<C> TrierParIdCat();
     public ArrayList<C> TrierParNomCat();
 
}
