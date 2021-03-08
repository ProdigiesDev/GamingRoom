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
    public int ajouterCours(Cours c) {

        int nb_ajouter=0;
        try {
            String requete = "INSERT INTO cour(nomCours,description,nb_participant ,membre_id,date_creation,tags,categorie_id)"
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
            nb_ajouter=pst.executeUpdate();
            
            System.out.println("Cours ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb_ajouter;

    }
    
   

    @Override
    public int supprimerCours(Cours c) {
        int nb_supprim=0;
        try {
            String requeteSP = "DELETE FROM cour where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requeteSP);
            pst.setInt(1, c.getId());
            nb_supprim=pst.executeUpdate();
            
            if (nb_supprim <= 0) {
                System.out.println("Verifiez vos données");
            } else {
                System.out.println("Cours supprimé");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb_supprim;
    }

    @Override
    public int updateCours(Cours c) {
        int nb_up=0;
        try {
            
            String requeteUp = "UPDATE cour SET nomCours=? WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requeteUp);
            pst.setString(1, c.getNomCours());
            pst.setInt(2, c.getId());
            pst.executeUpdate();
            if (nb_up <= 0) {
                System.out.println("Verifiez vos données");
            } else {
                System.out.println("Cours modifié");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb_up;
    }

    @Override
    public List<Cours> displayCours() {
        List<Cours> CoursList = new ArrayList<>();
        try {
            String requeteDs = "SELECT * FROM cour";
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


    @Override
    public List searchCours(String x) {
        List<Cours> CoursListx = new ArrayList<>();
        try {
            String requete = "Select * from cour where nomCours like '%" + x + "%' or description like '%" + x + "%'";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
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
                CoursListx.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return CoursListx;
    }

    @Override
    public List trierCoursID() {
        ArrayList<Cours> listCours = new ArrayList<>();
        try {

            String requete = "select * from cour ORDER BY id DESC";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);

            ResultSet res = pst.executeQuery(requete);
            Cours c = null;
            while (res.next()) {

                c = new Cours(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5), res.getDate(6), res.getString(7), res.getInt(8));
                listCours.add(c);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listCours;
    }

    @Override
    public Cours findById(int id) {
        Cours c = new Cours();
        try {
            String requete = "SELECT * FROM cour where id=" + id;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                c.setId(rs.getInt("id"));
                c.setNomCours(rs.getString("nomCours"));
                c.setDescription(rs.getString("description"));
                c.setNb_participants(rs.getInt("nb_participant"));
                c.setMembre_id(rs.getInt("membre_id"));
                c.setDate_creation(rs.getDate("date_creation"));
                c.setTags(rs.getString("tags"));
                c.setCategorie_id(rs.getInt("categorie_id"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }

    @Override
    public List<Cours> displayprefcours(int membre_id) {
        List<Cours> CoursList = new ArrayList<>();
        try {
            String requeteDs = "select * from cour where categorie_id in ( select categorie_id from categori_membre where membre_id='" + membre_id + "' )";
            //String requeteDs = "select * from cour where not EXISTS (select * from cours where idcat in (select id from categori_membre where membre_id=2)";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requeteDs);
            //ResultSet rs1 = st.executeQuery(requeteDs1);
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

            requeteDs = "select * from cour where id not in( select id from cour where categorie_id in ( select categorie_id from categori_membre where membre_id='" + membre_id + "' ) ) ";
            st = MyConnection.getInstance().getCnx()
                    .createStatement();
            rs = st.executeQuery(requeteDs);
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
            ex.printStackTrace();
        }
        return CoursList;
    }

}
