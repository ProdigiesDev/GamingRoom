/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.interfaces;

import java.util.List;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.entities.ParticipantsCours;

/**
 *
 * @author eyatr
 */
public interface IParticipantsCours {
    public int ajouterParticipant(int membre_id, int cour_id);
    public List<ParticipantsCours> DisplayParticipants ();
    public List <Membre>getListeParticipants(int idE);
 
}
