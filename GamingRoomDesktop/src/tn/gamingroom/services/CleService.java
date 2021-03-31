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
import java.util.Random;
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
    public int ajouterCle(Cles c) {
int nbCle=0;
        c.setCode(createKey());
        try {
            String requete = "insert into cle(code,produit_id)"
                    + "values('" + c.getCode() + "','" + c.getProduit_id() + "' )";

            Statement st = MyConnection.getInstance().getCnx().createStatement();
          nbCle=  st.executeUpdate(requete);
            System.out.println("clé ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
return nbCle;
    }

    @Override
    public int supprimerCle(int idcle) {
int res=0;
        try {
            String requete = "delete from cle where idcle=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, idcle);
            res = pst.executeUpdate();
            if (res > 0) {
                System.out.println("clé supprimé");

            } else {
                System.out.println("clé non supprimé");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
return res;
    }

    @Override
    public int updateCle(Cles c) {
int nbCle=0;
        try {
            String requete = "UPDATE cle SET code=? WHERE idcle=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, c.getCode());
            pst.setInt(2, c.getIdcle());
           nbCle= pst.executeUpdate();
            System.out.println("clé modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
return nbCle;
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
    
    public String createKey(){
        String s="";
        for(int i=0;i<5;i++){
            s=s+this.getSaltString();
            if(i!=4)
                s=s+"-";
        }
        return s;
    }

    public String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
    
      public List<Cles> Rechercher_Produit_ID(int x ){
          
             ArrayList<Cles> listOffresTypeX = new ArrayList<>();
               try {
            String req = "Select * from cle where produit_id='"+x+"'";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(req);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cles c = new Cles();

                c.setIdcle(rs.getInt(1));
                c.setCode(rs.getString(2));
             
                c.setProduit_id(rs.getInt(3));
           

                listOffresTypeX.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (listOffresTypeX.isEmpty()) {
            System.out.println("Il y a aucun cle avec cet ID");
        }
        return listOffresTypeX;
    }
          
          
          
      }
    
    
    
    
    
    
  
    
