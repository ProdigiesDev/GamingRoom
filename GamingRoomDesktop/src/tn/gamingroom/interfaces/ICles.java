/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.interfaces;

import java.util.List;

/**
 *
 * @author yasmine
 */
public interface ICles<T> {
    
       public int ajouterCle(T c); //  T type generique
    public int supprimerCle(int idcle);
    public int updateCle(T c);
    public List<T> displayCle();
      public List<T> Rechercher_Produit_ID(int x );
    
    
}
