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
import tn.gamingroom.interfaces.ICours;
import tn.gamingroom.outils.MyConnection;

/**
 *
 * @author eyatr
 */
public class ServiceCours implements ICours {

    @Override
    public void ajouterCours(Cours c) {
        
        try {
            String requete = "INSERT INTO cours(nomCours,description,nb_participant,"
                  + "membre_id,date_creation,tags,categorie_id)"
                    + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, c.getNomCours());
            pst.setString(2, c.getDescription());
            pst.setInt(3, c.getNb_participants());
            pst.setInt(4, c.getMembre_id());
            pst.setDate(5, c.getDate_creation());
            pst.setString(6, c.getTags());
            pst.setInt(7, c.getCategorie_id());
            pst.executeUpdate();
            System.out.println("Cours ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimerCours(Cours c) {
        try {
            String requeteSP = "DELETE FROM cours where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requeteSP);
            pst.setInt(1, c.getId());
            pst.executeUpdate();
            System.out.println("Cours supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void updateCours(Cours c) {
        try {
            String requeteUp = "UPDATE cours SET nomCours=? WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requeteUp);
            pst.setString(1, c.getNomCours());
            pst.setInt(2, c.getId());
            pst.executeUpdate();
            System.out.println("Cours modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Cours> displayCours() {
        List<Cours> CoursList = new ArrayList<>();
        try {
            String requeteDs = "SELECT * FROM cours";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requeteDs);
            while (rs.next()) {
                Cours c = new Cours();
                c.setId(rs.getInt("id"));
                c.setNomCours(rs.getString("nomCours"));
                c.setDescription(rs.getString("description"));
                c.setNb_participants(rs.getInt("nb_participant"));
                c.setMembre_id(rs.getInt("membre_id"));
                c.setDate_creation(rs.getDate("date_creation"));
                c.setTags(rs.getString("tags"));
                c.setCategorie_id(rs.getInt("categorie_id"));
                CoursList.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return CoursList;
    }

}
