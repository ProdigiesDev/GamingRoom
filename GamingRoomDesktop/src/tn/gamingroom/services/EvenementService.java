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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.gamingroom.entities.Evenement;
import tn.gamingroom.entities.ReactionEv;
import tn.gamingroom.interfaces.IEvenement;
import tn.gamingroom.outils.MyConnection;

/**
 *
 * @author Asus
 */
public class EvenementService implements IEvenement {

    @Override
    public void ajoutEvenement(Evenement t) {
        try {
            String requete = "INSERT INTO evenement(nomevent,datedeb,datefin,image,categorie_id,nbremax_participant,description,lieu,lienyoutub)"
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

    @Override
    public void modifierEvenement(Evenement t) {
        try {
            String requete = "UPDATE evenement SET nomevent=? datedeb=? datefin=? image=? categorie_id=? nbremax_participant=? description=? lieu=? lienyoutub WHERE id=?";
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
            pst.setInt(2, t.getIdevent());
            pst.executeUpdate();
            System.out.println("Evenement modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void suppressionEvenement(Evenement t) {
        try {
            String requete = "DELETE FROM evenement where idE=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, t.getIdevent());
            pst.executeUpdate();
            System.out.println("Evenement supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Evenement> listerEvenement() {
        List<Evenement> evenementList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM evenement";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setIdevent(rs.getInt("idevent"));
                e.setNomEvent(rs.getString("nomevent"));
                e.setDateDeb(rs.getDate("datedeb"));
                e.setDateFin(rs.getDate("datefin"));
                e.setImage(rs.getString("image"));
                e.setCategorie_id(rs.getInt("categorie_id"));
                e.setNbreMax_participant(rs.getInt("nbremax_participant"));
                e.setDescription(rs.getString("description"));
                e.setLieu(rs.getString("lieu"));
                e.setLienYoutube(rs.getString("lienyoutube"));
                evenementList.add(e);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return evenementList;
    }

    @Override
    public void reagirEvenement(ReactionEv rE) {
         try {
            String requetereac = "INSERT INTO reactionev(interaction,commentaire,evenement_id,membre_id)"
                    + "VALUES (?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requetereac);
            pst.setInt(1, rE.getInteraction());
            pst.setString(2, rE.getCommentaire());
            pst.setInt(3, rE.getEvenement_id());
            pst.setInt(4, rE.getMembre_id());
            
            pst.executeUpdate();
            System.out.println("Réaction ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public void supprimerReacC(ReactionEv rE) {
        try {
            String requeteSP = "DELETE FROM reactionev where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requeteSP);
            pst.setInt(1, rE.getId());
            pst.executeUpdate();
            System.out.println("Reaction supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
