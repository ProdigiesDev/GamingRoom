/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.services;

import java.sql.SQLException;
import java.sql.Statement;
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
            String requete ="insert into produit(image,libelle,prix,description,quantite)"
                    +"values('"+p.getImage()+"','"+p.getLibelle()+"','"+p.getPrix()+"','"+p.getDescription()+"','"+p.getQuantite()+"')";
            
            Statement st=new MyConnection().getCnx().createStatement();
       st.executeUpdate(requete);
            System.out.println("Produit ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void supprimerProduit(Produits p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateProduit(Produits p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Produits> displayProduit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
