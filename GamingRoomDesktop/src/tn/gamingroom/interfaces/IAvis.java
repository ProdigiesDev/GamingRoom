/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.interfaces;

import java.util.List;
import tn.gamingroom.entities.Avis;

/**
 *
 * @author Dah
 */
public interface IAvis {
    public int ajouterAvis(Avis avis);
     public List<Avis> getListAvis();
}
