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
import tn.gamingroom.entities.Cles;
import tn.gamingroom.interfaces.ICles;
import tn.gamingroom.outils.MyConnection;

/**
 *
 * @author yasmine
 */
public class CleService implements ICles<Cles> {

    @Override
    public void ajouterCle(Cles c) {

        try {
            String requete = "insert into cle(code,produit_id)"
                    + "values('" + c.getCode() + "','" + c.getProduit_id() + "' )";

            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("clé ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimerCle(int idcle) {

        try {
            String requete = "delete from cle where idcle=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, idcle);
            int res = pst.executeUpdate();
            if (res > 0) {
                System.out.println("clé supprimé");

            } else {
                System.out.println("clé non supprimé");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void updateCle(Cles c) {

        try {
            String requete = "UPDATE cle SET code=? WHERE idcle=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, c.getCode());
            pst.setInt(2, c.getIdcle());
            pst.executeUpdate();
            System.out.println("clé modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
  @Override
    public List<Cles> displayCle() {
        
          List<Cles> myList = new ArrayList();
        
        try {
          
            String requete = "select *from cle";  // statique
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Cles c = new Cles();
                c.setIdcle(rs.getInt(1));
                c.setCode(rs.getString(2));
                c.setProduit_id(rs.getInt(3));

                myList.add(c);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;
    }
}