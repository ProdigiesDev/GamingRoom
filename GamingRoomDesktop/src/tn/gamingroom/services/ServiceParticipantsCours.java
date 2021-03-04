/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.gamingroom.entities.Cours;
import tn.gamingroom.entities.ParticipantsCours;
import tn.gamingroom.interfaces.IParticipantsCours;
import tn.gamingroom.outils.MyConnection;

/**
 *
 * @author eyatr
 */
public class ServiceParticipantsCours implements IParticipantsCours {

    @Override
    public void ajouterParticipant(ParticipantsCours p) {
        try {
            String requete = "INSERT INTO participantcours(membre_id,cour_id)" + "VALUES (?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, p.getMembre_id());
            pst.setInt(2, p.getCour_id());

            pst.executeUpdate();
            System.out.println("Inscription effectuée avec succès");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<ParticipantsCours> DisplayParticipants() {
        List<ParticipantsCours> PartCours = new ArrayList<>();
        try {
            String requeteDs = "SELECT * FROM participantcours";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requeteDs);
            while (rs.next()) {
                ParticipantsCours p = new ParticipantsCours();
                p.setId(rs.getInt("id"));
                p.setMembre_id(rs.getInt("membre_id"));
                p.setCour_id(rs.getInt("cour_id"));

                PartCours.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return PartCours;
    }

    
}
