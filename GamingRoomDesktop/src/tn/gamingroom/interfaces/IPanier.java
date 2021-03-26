/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.interfaces;

import java.util.List;
import tn.gamingroom.entities.Panier;

/**
 *
 * @author Admin
 */
public interface IPanier {
    public int ajouterProd(Panier p);
    public int supprimerProd( int panieid);
    public int modifierQuantity(Panier p);
    public List<Panier> consulterPanier(int member_id);
}
