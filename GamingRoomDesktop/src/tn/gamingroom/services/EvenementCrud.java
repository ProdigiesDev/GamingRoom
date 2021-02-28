/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import tn.gamingroom.entities.Evenement;
import tn.gamingroom.interfaces.IEvenement;

/**
 *
 * @author Asus
 */
public class EvenementCrud implements IEvenement<Evenement>{

    @Override
    public void ajoutEvenement(Evenement t) {
        try {
            String requete= "INSERT INTO evenement(nomevent,datedeb,datefin,image,categorie_id,nbremax_participant,description,lieu,lienyoutub)"
                    + "VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, t.getNomEvent());
            pst.setDate(2, t.getDateDeb());
            pst.setDate(2, t.getDateFin());
            pst.setString(2, t.getImage());
            pst.setInt(2, t.getCategorie_id());
            pst.setInt(2, t.getNbreMax_participant());
            pst.setString(2, t.getDescription());
            pst.setString(2, t.getLieu());
            pst.setString(2, t.getLienYoutube());
            
            pst.executeUpdate();
            System.out.println("Evenement inserée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       
}
