/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.interfaces;

import java.util.List;
import tn.gamingroom.entities.Reclamation;

/**
 *
 * @author Dah
 */
public interface IReclamation {
    public void ajouterReclamation(Reclamation reclamation);
    public List<Reclamation>  getListReclamation();
}
