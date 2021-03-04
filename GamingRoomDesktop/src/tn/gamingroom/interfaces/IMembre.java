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
 */
public interface IMembre<T> {
     public void ajouterMembre(T m);
     public void ajouterCoach(T m);
     public void sumprimerMembres(T m);
     public void modifierMembres(T m);
     public void modifierMembreParAdmin(T m);
     public List<T> DisplayMembres();
     public T Login(String a,String b);
     public List<T> RechercherMembres(String x);
     public ArrayList<T> TrierParId();

    
}
