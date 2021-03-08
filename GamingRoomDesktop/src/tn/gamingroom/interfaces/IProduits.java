/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.interfaces;

import java.util.List;
import tn.gamingroom.entities.Produits;

/**
 *
 * @author yasmine
 * @param <T>
 */
public interface IProduits <T> { 
    public int ajouterProduit(T p); //  T type generique
    public int supprimerProduit(T p);
    public int updateProduit(T p);
    public List<T> displayProduit();
    
    public List<Produits> TrierParId();
    public List<Produits> RechercherProduit(String x);
   
    public List<Integer> bestProductsSelled();
}
