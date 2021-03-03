/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.interfaces;

import java.util.List;

/**
 *
 * @author Sonia
 * @param <C>
 */
public interface ICategorie <C> {
    
    public void ajouterCategorie(C c);
     public void sumprimerCategorie(C c);
     public void modifierCategorie(C c);
     public List<C> DisplayCategorie();
     public List<C> RechercherCategorie(String x);
}
