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
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.gamingroom.entities.Commande;
import tn.gamingroom.interfaces.ICommande;
import tn.gamingroom.outils.MyConnection;

/**
 *
 * @author Admin
 */
public class CommandService implements ICommande{

    private Connection  cnx=MyConnection.getInstance().getCnx();
    
    @Override
    public int ajouterCommand(Commande c) {
        int nb=0;
        try {
            String reqAjouter="insert into commande(datecommande,membreid) values (?,?) ";
            PreparedStatement ps = cnx.prepareStatement(reqAjouter);
            ps.setDate(1, c.getDatecommande());
            ps.setInt(2, c.getMemberid());
            
        } catch (SQLException ex) {
           ex.printStackTrace();
        } 
        return nb;
    }

    @Override
    public int modifierCommand(Commande c) {
        System.out.println("Modifier Commande");
            int nbModifier=0;
        try {
            
            String req="Update commande set statu = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, c.getEtat());
            nbModifier = ps.executeUpdate();  
           } catch (SQLException ex) {
            ex.printStackTrace();
                            }
        return nbModifier;
        } 

    @Override
    public List<Commande> consulterMonCommande(int memberid) {
        List<Commande> commandes=new ArrayList();
        try {
            String reqLister="select * from commande where memberid =" + memberid ;
            Statement statement= cnx.createStatement();
            
            ResultSet rs= statement.executeQuery(reqLister);
            while (rs.next()){
                commandes.add(new Commande(rs.getInt("idcommande"),rs.getDate("datecommande"),rs.getString("etat")));
                        }
            System.out.println("getListCommande");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return commandes;
                
        }

    @Override
    public List<Commande> consulterCommande() {
        List<Commande> listCommandes =new ArrayList();
        try {
            String requete = "select * from commande"; // statique
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Commande c = new Commande();
                c.setIdcommande(rs.getInt(1));
                c.setMemberid(rs.getInt(2));
                c.setDatecommande(rs.getDate(3));
                c.setEtat(rs.getString(4));
                listCommandes.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
           return listCommandes;
   
        }

    @Override
    public int supprimerCommand(int idcommand) {
        int nb=0;
        try {
            String requete = "delete from commande where idcommande=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, idcommand);
            nb=pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;
    }

    
}
