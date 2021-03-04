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
    public void ajouterProduit(Produits p) {

        try {
            String requete = "insert into produit(image,libelle,prix,description)"
                    + "values('" + p.getImage() + "','" + p.getLibelle() + "','" + p.getPrix() + "','" + p.getDescription() + "')";

            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Produit ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimerProduit(Produits p) {

        try {
            String requete = "delete from produit where idprod=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, p.getIdprod());
            pst.executeUpdate();
            System.out.println("produit supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void updateProduit(Produits p) {

        try {
 
            String requete = "UPDATE produit SET libelle='" + p.getLibelle()
                    +"',image='"+p.getImage()
                    + "',prix='" + p.getPrix()
                    + "',description='" + p.getDescription()
                 
                    + "' WHERE idprod=" + p.getIdprod();
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            int rowsUpdated = pst.executeUpdate(requete);
            if (rowsUpdated > 0) {
                System.out.println("La modification de produit" + p.getIdprod() + " a été éffectué avec succée ");
                System.out.println("Produit Modifié");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }



@Override
        public List<Produits> displayProduit() {
        List<Produits> myList = new ArrayList();
        try {

            String requete = "select *from produit"; // statique
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Produits p = new Produits();
                p.setIdprod(rs.getInt(1));
                p.setImage(rs.getString(2));
                p.setLibelle(rs.getString(3));
                p.setPrix(rs.getInt(4));
                p.setDescription(rs.getString(5));
            
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
        
           String requete= "select * from produit ORDER BY idprod DESC"; 
           PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
           
           
           ResultSet res = pst.executeQuery(requete);
        Produits pr = null;
        while (res.next()) {
       
       
        pr=new Produits(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getString(5));
            listProduit.add(pr);
            
        } 
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                 return listProduit ;
    
}
        
        
        
   public List<Produits> RechercherProduit(String x) {
        ArrayList<Produits> listOffresTypeX = new ArrayList<>();
        try {
          String req = "Select * from produit where libelle like '%"+x+"%' or description like '%"+x+"%'";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(req);
            //st.setString(1, x);
          //  st.setString(2, x);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Produits pr = new Produits();
               pr.setIdprod(rs.getInt(1));
                pr.setImage(rs.getString(2));
                pr.setLibelle(rs.getString(3));
                pr.setPrix(rs.getInt(4));
                pr.setDescription(rs.getString(5));
             
                
                
                
                listOffresTypeX.add(pr);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if  (listOffresTypeX.isEmpty()) {
            System.out.println("Il y a aucun produit de ce libelle");
        }
        return listOffresTypeX;
    }     
        
        
    public List<Integer> bestProductsSelled(){
        List<Integer> integers=new ArrayList<>();
        try {
          String req = "Select produit_id,sum(quantityDemande) from panier group by produit_id";//kol produit 9adeh be3na totale
            Statement st = MyConnection.getInstance().getCnx().createStatement();
        
            ResultSet rs = st.executeQuery(req);
            int max=0;
            
            //n5arjou l valeur maximum mta akber quantité be3néha 
            while(rs.next()){
                int qt=rs.getInt(2);
                if(qt>max)
                    max=qt;
            }
            //taatina l produit eli aandhom qt max 
            req = "Select p.idprod,sum(quantityDemande) from produit p, panier pa where p.idprod=pa.produit_id group by  p.idprod   having sum(quantityDemande) = "+max;
            
           
             rs = st.executeQuery(req);
            while(rs.next()){
                integers.add(rs.getInt(1));// nekhdou biha f liste mta l prod(id)
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return integers;
    } 
        // kol produit kadeh aandou qt totale weli aandou  qt total= kaber qt totla houma eli yokherjou
        
        
        
        
        
        
        
}
