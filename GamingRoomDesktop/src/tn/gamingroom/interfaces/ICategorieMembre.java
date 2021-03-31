/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.interfaces;

import java.util.List;
import tn.gamingroom.entities.CategorieMembre;

/**
 *
 * @author Sonia
 */
public interface ICategorieMembre {
    
    public int AffecterCategorieMembre(CategorieMembre cm);
    public int SuprimerCategorieMembre(CategorieMembre cm);
    public int ModifierCategorieMembre(CategorieMembre cm);
    public List<String> DisplayCategorie(int id_membre);
    
    
}
