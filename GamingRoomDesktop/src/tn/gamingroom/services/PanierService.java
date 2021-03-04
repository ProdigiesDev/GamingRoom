/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.gamingroom.entities.Panier;
import tn.gamingroom.interfaces.IPanier;
import tn.gamingroom.outils.MyConnection;

/**
 *
 * @author Admin
 */
public class PanierService implements IPanier {
    
  private Connection  cnx=MyConnection.getInstance().getCnx();
    
  @Override
    public int ajouterProd(Panier p) {
        int nb=0;
        try {
            String requete = "insert into produit( produit_id, commande_id, quantityDemande) values(?,?,?)";
                   
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,p.getProduit_id());
            pst.setInt(2,p.getCommande_id());
            pst.setInt(3,p.getQuantityDemande());
            nb=pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;
    }

    @Override
    public int supprimerProd(int panieid) {
        int nb=0;
        try {
            String requete = "delete from panier where id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, panieid);
            nb=pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return nb;
    
    }

    @Override
    public int modifierQuantity(Panier p) {
        int nbModifier=0;
        try {
            String req="Update panier set quantityDemande = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,p.getQuantityDemande());
            nbModifier = ps.executeUpdate();  
           } catch (SQLException ex) {
            ex.printStackTrace();
           }
        return nbModifier;
        
    }

    @Override
    public List<Panier> consulterPanier(int commande_id) {
            System.out.println("consulterPanier");
        List<Panier> panier=new ArrayList<>();
        try {
            String reqLister="select * from panier where commande_id =" + commande_id ;
            Statement statement= cnx.createStatement();
            ResultSet rs= statement.executeQuery(reqLister);
            while (rs.next()){
                panier.add(new Panier(rs.getInt("id"),rs.getInt("produit_id"),rs.getInt("commande_id"),rs.getInt("quantityDemande")));
                        }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return panier;
    }
    
}
