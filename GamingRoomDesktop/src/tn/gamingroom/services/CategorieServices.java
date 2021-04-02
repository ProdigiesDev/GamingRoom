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
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.gamingroom.entities.Categorie;
import tn.gamingroom.interfaces.ICategorie;
import tn.gamingroom.outils.MyConnection;

/**
 *
 * @author Sonia
 */
public class CategorieServices implements ICategorie<Categorie> {

    @Override
    public int ajouterCategorie(Categorie c) {
        int nbAjoutCat = 0;
        try {
            String requete = "INSERT INTO categorie(nomcategorie) VALUES (?)";
            PreparedStatement ps = new MyConnection().getCnx().prepareStatement(requete);
            ps.setString(1, c.getNomcat());

            nbAjoutCat = ps.executeUpdate();
            System.out.println("Categorie ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nbAjoutCat;
    }

    @Override
    public int supprimerCategorie(Categorie c) {
        int nbSuppCat = 0;
        try {
            String requete = "DELETE FROM categorie WHERE idcat=?";
            PreparedStatement ps = new MyConnection().getCnx().prepareStatement(requete);
            ps.setInt(1, c.getIdcat());
            nbSuppCat = ps.executeUpdate();
            System.out.println("Catégorie supprimée avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nbSuppCat;
    }

    @Override
    public int modifierCategorie(Categorie c) {
        int nbModifCat = 0;
        try {
            String requete = "UPDATE categorie SET nomcategorie=? where idcat=?";
            PreparedStatement ps = new MyConnection().getCnx().prepareStatement(requete);
            ps.setString(1, c.getNomcat());
            ps.setInt(2, c.getIdcat());

            nbModifCat = ps.executeUpdate();
            System.out.println("Catégorie modifiée avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nbModifCat;
    }

    @Override
    public List<Categorie> DisplayCategorie() {
        List<Categorie> CatList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM categorie";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Categorie c = new Categorie();
                c.setIdcat(rs.getInt("idcat"));
                c.setNomcat(rs.getString("nomcategorie"));

                CatList.add(c);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return CatList;
    }

    
    public Categorie getCategorieById(int id) {
        List <Categorie> CatList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM categorie where idcat="+id;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            if(rs.next()){
                Categorie c = new Categorie();
                c.setIdcat(rs.getInt("idcat"));
                c.setNomcat(rs.getString("nomcategorie"));
               
                return c;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
    
    @Override
    public List<Categorie> RechercherCategorie(String x) {
        ArrayList<Categorie> ListCategorie = new ArrayList<>();
        try {
            String requete = "Select * from categorie where nomcategorie like '%" + x + "%'";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Categorie c = new Categorie();

                c.setIdcat(rs.getInt("idcat"));
                c.setNomcat(rs.getString("nomcategorie"));

                ListCategorie.add(c);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        if (ListCategorie.isEmpty()) {
            System.out.println("NOT FOUND");
        }
        return ListCategorie;
    }

    @Override
    public ArrayList<Categorie> TrierParIdCat() {

        ArrayList<Categorie> ListCategorie = new ArrayList<>();
        try {

            String requete = "select * from categorie ORDER BY idcat DESC ";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);

            ResultSet rs = pst.executeQuery(requete);

            while (rs.next()) {
                Categorie c = new Categorie();

                c.setIdcat(rs.getInt("idcat"));
                c.setNomcat(rs.getString("nomcategorie"));

                ListCategorie.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListCategorie;
    }

    @Override
    public ArrayList<Categorie> TrierParNomCat() {
        ArrayList<Categorie> ListCategorie = new ArrayList<>();
        try {

            String requete = "select * from categorie ORDER BY nomcategorie ";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);

            ResultSet rs = pst.executeQuery(requete);

            while (rs.next()) {
                Categorie c = new Categorie();

                c.setIdcat(rs.getInt("idcat"));
                c.setNomcat(rs.getString("nomcategorie"));

                ListCategorie.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListCategorie;
    }

    public Categorie getById(int x) {
        try {
            String requete = "Select * from categorie where idcat=" + x;
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Categorie c = new Categorie();

                c.setIdcat(rs.getInt("idcat"));
                c.setNomcat(rs.getString("nomcategorie"));
                return c;
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
    public boolean CategorieExiste(String cat) {
        try {
            String requete = "select count(nomcategorie) as em from  categorie where  nomcategorie='" + cat + "'";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                return rs.getInt("em") > 0;
            }

        } catch (SQLException ex) {
            Logger.getLogger(MembreServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
