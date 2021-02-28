/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.services;

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
            String requete = "insert into produit(image,libelle,prix,description,quantite)"
                    + "values('" + p.getImage() + "','" + p.getLibelle() + "','" + p.getPrix() + "','" + p.getDescription() + "','" + p.getQuantite() + "')";

            Statement st = MyConnection.getInstance().getCnx().createStatement();
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
                p.setQuantite(rs.getInt(6));
                myList.add(p);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;

    }

}
