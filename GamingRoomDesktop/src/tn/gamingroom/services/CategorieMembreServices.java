/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.services;

import java.lang.reflect.Member;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.gamingroom.entities.Categorie;
import tn.gamingroom.entities.CategorieMembre;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.interfaces.ICategorieMembre;
import tn.gamingroom.outils.MyConnection;

/**
 *
 * @author Sonia
 */
public class CategorieMembreServices implements ICategorieMembre {

    @Override
    public void AffecterCategorieMembre(CategorieMembre cm) {
        try {
            String requete= "INSERT INTO categori_membre (membre_id,categorie_id) VALUES (?,?)";
            PreparedStatement ps = new MyConnection().getCnx().prepareStatement(requete);
            ps.setInt(1, cm.getMembre_id());
            ps.setInt(2, cm.getCategorie_id());
            
            ps.executeUpdate();
            System.out.println("Categorie ajoutée au Membre");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void SuprimerCategorieMembre(CategorieMembre cm) {
         try {
            String requete ="DELETE FROM categori_membre WHERE id=?";
            PreparedStatement ps = new MyConnection().getCnx().prepareStatement(requete);
            ps.setInt(1, cm.getId());
            ps.executeUpdate();
            System.out.println("Catégorie affectéé au membre est supprimée avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void ModifierCategorieMembre(CategorieMembre cm) {
        try {
            String requete ="UPDATE categori_membre SET categorie_id=?";
            PreparedStatement ps = new MyConnection().getCnx().prepareStatement(requete);
            ps.setInt(1, cm.getCategorie_id());
            
            ps.executeUpdate();
            System.out.println("Catégorie affectéé au membre est modifiée avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<String> DisplayCategorie(int id_membre) {
        
         List<String> CatList = new ArrayList<>();
        try {
        String requete = "select c.nomcategorie as nom from categorie c, categori_membre cm  where  cm.membre_id="+id_membre+" and c.idcat =cm.categorie_id";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
               CategorieMembre cm = new CategorieMembre();

               CatList.add(rs.getString("nom"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return CatList;
    }
    
    
    
}
