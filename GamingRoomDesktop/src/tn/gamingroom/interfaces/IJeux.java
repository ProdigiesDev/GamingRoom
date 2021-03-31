/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.interfaces;

import java.util.List;
import tn.gamingroom.entities.Jeux;

/**
 *
 * @author Dah
 */
public interface IJeux {
    public int ajouter(Jeux jeux);
    public int supprimer(int id);
    public int modifier(Jeux jeux);
    public List<Jeux> getAll();
    public List<Jeux> search(String name,String plat);
}
