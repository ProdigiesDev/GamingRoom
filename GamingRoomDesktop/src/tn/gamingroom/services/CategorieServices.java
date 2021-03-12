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
    public void ajouterCategorie(Categorie c) {
        try {
            String requete= "INSERT INTO categorie(nomcategorie) VALUES (?)";
            PreparedStatement ps = new MyConnection().getCnx().prepareStatement(requete);
            ps.setString(1, c.getNomcat());
            
            ps.executeUpdate();
            System.out.println("Categorie ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerCategorie(Categorie c) {
        try {
            String requete ="DELETE FROM categorie WHERE idcat=?";
            PreparedStatement ps = new MyConnection().getCnx().prepareStatement(requete);
            ps.setInt(1, c.getIdcat());
            ps.executeUpdate();
            System.out.println("Catégorie supprimée avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifierCategorie(Categorie c) {
        try {
            String requete ="UPDATE membre SET nomcategorie=?";
            PreparedStatement ps = new MyConnection().getCnx().prepareStatement(requete);
            ps.setString(1, c.getNomcat());
            
            ps.executeUpdate();
            System.out.println("Catégorie modifiée avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Categorie> DisplayCategorie() {
        List <Categorie> CatList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM categorie";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
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
          String requete = "Select * from categorie where nomcategorie like '%"+x+"%'";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Categorie c = new Categorie();
                
                c.setIdcat(rs.getInt("idcat"));
                c.setNomcat(rs.getString("nomcategorie"));
                
                ListCategorie.add(c);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        if  (ListCategorie.isEmpty()) {
            System.out.println("NOT FOUND");
        }
        return ListCategorie;
    }

    @Override
    public ArrayList<Categorie> TrierParIdCat() {
        
        ArrayList<Categorie> ListCategorie = new ArrayList<>();
       try {
        
           String requete= "select * from categorie ORDER BY idcat ASC "; 
           PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
           
           
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
                 return ListCategorie ;
    }

    @Override
    public ArrayList<Categorie> TrierParNomCat() {
        ArrayList<Categorie> ListCategorie = new ArrayList<>();
       try {
        
           String requete= "select * from categorie ORDER BY nomcategorie "; 
           PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
           
           
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
                 return ListCategorie ;
    }
    

   
    
}
