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
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.gamingroom.entities.Produits;
import tn.gamingroom.interfaces.IProduits;
import tn.gamingroom.outils.MyConnection;

/**
 *
 * @author yasmine
 */
public class ProduitCrud implements IProduits<Produits> {

    @Override
    public int ajouterProduit(Produits p) {
        int nbProd = 0;
        try {
            String requete = "insert into produit(id_cat,image,libelle,prix,description)"
                    + "values(" + p.getId_cat() + ",+'" + p.getImage() + "','" + p.getLibelle() + "'," + p.getPrix() + ",'" + p.getDescription() + "')";

            Statement st = MyConnection.getInstance().getCnx().createStatement();
            nbProd = st.executeUpdate(requete);
            System.out.println("nbProd " + nbProd);
            System.out.println("Produit ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nbProd;
    }

    @Override
    public int supprimerProduit(int idprod) {
        int nbProd = 0;
        try {
            String requete = "delete from produit where idprod=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, idprod);

            nbProd = pst.executeUpdate();
            System.out.println("produit supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nbProd;
    }

    @Override
    public int updateProduit(Produits p) {
        int rowsUpdated = 0;
        try {
//
//            String requete = "UPDATE produit SET libelle='" + p.getLibelle()
//                    + "',image='" + p.getImage()
//                    + "',prix='" + p.getPrix()
//                    + "',description='" + p.getDescription()
//                    + "' WHERE idprod=" + p.getIdprod();
            
            
               String requete = "UPDATE produit SET libelle='" + p.getLibelle()+ "',id_cat='" + p.getId_cat()
                    + "',image='" + p.getImage()
                    + "',prix='" + p.getPrix()
                    + "',description='" + p.getDescription()
                    + "' WHERE idprod=" + p.getIdprod();
            
            
            
            
            
            
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            rowsUpdated = pst.executeUpdate(requete);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rowsUpdated;
    }

        
    public List<Produits> displayProduitORDERBYLibelle() {

        List<Produits> myList = new ArrayList();
        try {

            String requete = "select p.*,c.nomcategorie from produit p , categorie c where c.idcat=p.id_cat ORDER BY libelle"; // statique
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Produits p = new Produits();
                p.setIdprod(rs.getInt(1));
                p.setImage(rs.getString(3));
                p.setLibelle(rs.getString(4));
                p.setPrix(rs.getDouble(5));
                p.setDescription(rs.getString(6));
                p.setId_cat(rs.getInt(2));
                p.setNomCat(rs.getString(7));
                myList.add(p);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;

    }

    @Override
    public List<Produits> displayProduit() {

        List<Produits> myList = new ArrayList();
        try {

            String requete = "select p.*,c.nomcategorie from produit p , categorie c where c.idcat=p.id_cat"; // statique
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Produits p = new Produits();
                p.setIdprod(rs.getInt(1));
                p.setImage(rs.getString(3));
                p.setLibelle(rs.getString(4));
                p.setPrix(rs.getDouble(5));
                p.setDescription(rs.getString(6));
                p.setId_cat(rs.getInt(2));
                p.setNomCat(rs.getString(7));
                myList.add(p);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;

    }

    public ArrayList<Produits> TrierParId() {
        ArrayList<Produits> listProduit = new ArrayList<>();
        try {

            String requete = "select p.* ,c.nomcategorie from produit p , categorie c where c.idcat=p.id_cat ORDER BY idprod DESC";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);

            ResultSet res = pst.executeQuery(requete);
            Produits pr = null;
            while (res.next()) {

                pr = new Produits(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4), res.getDouble(5), res.getString(6), res.getString(7));
                listProduit.add(pr);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listProduit;

    }

    public List<Produits> RechercherProduit(String x) {
     ArrayList<Produits> listOffresTypeX = new ArrayList<>();
        try {
       String req = "Select p.* ,c.nomcategorie from produit p , categorie c where c.idcat=p.id_cat and ( libelle like '%" + x + "%' or description like '%" + x + "%' )";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(req);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Produits pr = new Produits();

                pr.setIdprod(rs.getInt(1));
                pr.setImage(rs.getString(3));
                pr.setLibelle(rs.getString(4));
                pr.setPrix(rs.getDouble(5));
                pr.setDescription(rs.getString(6));
                pr.setId_cat(rs.getInt(2));
                pr.setNomCat(rs.getString(7));

                listOffresTypeX.add(pr);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (listOffresTypeX.isEmpty()) {
            System.out.println("Il y a aucun produit de ce libelle");
        }
        return listOffresTypeX;
    }
    public Produits getByID(int x) {

        try {

            String requete = "select  p.* ,c.nomcategorie from produit p , categorie c where c.idcat=p.id_cat and idprod='" + x + "'"; // statique
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                Produits p = new Produits();
                p.setIdprod(rs.getInt(1));
                p.setImage(rs.getString(3));
                p.setLibelle(rs.getString(4));
                p.setPrix(rs.getInt(5));
                p.setDescription(rs.getString(6));
                return p;

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;

    }    
        

    public List<Integer> bestProductsSelled() {
        List<Integer> integers = new ArrayList<>();
        try {
            String req = "Select produit_id,sum(quantityDemande) from panier group by produit_id";//kol produit 9adeh be3na totale
            Statement st = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st.executeQuery(req);
            int max = 0;

            //n5arjou l valeur maximum mta akber quantité be3néha 
            while (rs.next()) {
                int qt = rs.getInt(2);
                if (qt > max) {
                    max = qt;
                }
            }
            //taatina l produit eli aandhom qt max 
            req = "Select p.idprod,sum(quantityDemande) from produit p, panier pa where p.idprod=pa.produit_id group by  p.idprod   having sum(quantityDemande) = " + max;

            rs = st.executeQuery(req);
            while (rs.next()) {
                integers.add(rs.getInt(1));// nekhdou biha f liste mta l prod(id)
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return integers;
    }

    public List<Produits> RechercherPrix(double minPrice, double maxPrice) {

        ArrayList<Produits> listOffresTypeX = new ArrayList<>();
        try {
            String req = "Select p.* ,c.nomcategorie from produit p , categorie c where c.idcat=p.id_cat and  prix >= " + minPrice + " and prix <= " + maxPrice;
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(req);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Produits pr = new Produits();

                pr.setIdprod(rs.getInt(1));
                pr.setImage(rs.getString(3));
                pr.setLibelle(rs.getString(4));
                pr.setPrix(rs.getDouble(5));
                pr.setDescription(rs.getString(6));
                pr.setId_cat(rs.getInt(2));
                pr.setNomCat(rs.getString(7));

                listOffresTypeX.add(pr);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (listOffresTypeX.isEmpty()) {
            System.out.println("Il y a aucun produit de ce libelle");
        }
        return listOffresTypeX;
    }

}
