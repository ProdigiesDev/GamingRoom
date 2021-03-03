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
    
       public void ajouterCle(T c); //  T type generique
    public void supprimerCle(T c);
    public void updateCle(T c);
    public List<T> displayCle(int prod_id);
    
}
