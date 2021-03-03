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
import tn.gamingroom.entities.Jeux;
import tn.gamingroom.interfaces.IJeux;
import tn.gamingroom.outils.MyConnection;

/**
 *
 * @author Dah
 */
public class JeuxService implements IJeux{

    
    private Connection  cnx=MyConnection.getInstance().getCnx();
    
    @Override
    public int ajouter(Jeux jeux) {
         System.err.println("Inside ajouter Jeux");
        int nbInsert=0;
        try {        
            String req="insert into jeux (nom) values(?)";
            PreparedStatement ps=cnx.prepareStatement(req);
            ps.setString(1,jeux.getNom());
           nbInsert = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nbInsert;
    }

    @Override
    public int supprimer(int id) {
        
        System.err.println("Inside supprimer Jeux");
        
        String req="delete from jeux where id="+id;
        int nbDelete=0;
       
        try {
             Statement s = cnx.createStatement();
             nbDelete =  s.executeUpdate(req);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return nbDelete;
        
    }

    @Override
    public int modifier(Jeux jeux) {
        System.err.println("Inside Modifier Jeux");
        int nbModifier=0;
        try {
            String req="update jeux set nom = ? where id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,jeux.getNom());
            ps.setInt(2,jeux.getId());
            nbModifier = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nbModifier;
    }

    @Override
    public List<Jeux> getAll() {
        
        List<Jeux> jeux=new ArrayList();
        
        try {
            String req="select * from jeux";
            Statement s = cnx.createStatement();
            ResultSet resultSet=s.executeQuery(req);
            while(resultSet.next()){
                jeux.add(new Jeux(resultSet.getInt("id"),resultSet.getString("nom")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return jeux;
        
        
    }
    
}
