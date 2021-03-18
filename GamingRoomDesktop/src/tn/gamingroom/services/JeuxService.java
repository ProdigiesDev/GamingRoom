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
         System.out.println("Inside ajouter Jeux");
        int nbInsert=0;
        try {        
            String req="insert into jeux (nom,description,type_plateforme) values(?,?,?)";
            PreparedStatement ps=cnx.prepareStatement(req);
            ps.setString(1,jeux.getNom());
            ps.setString(2,jeux.getDescriString());
            ps.setString(3,jeux.getType_plateforme().toString() );
           nbInsert = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nbInsert;
    }

    @Override
    public int supprimer(int id) {
        
        System.out.println("Inside supprimer Jeux");
        
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
        System.out.println("Inside Modifier Jeux");
        int nbModifier=0;
        try {
            String req="update jeux set nom = ? , description=?, type_plateforme=? where id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,jeux.getNom());
            ps.setString(2,jeux.getDescriString());
            ps.setString(3,jeux.getType_plateforme().toString());
            ps.setInt(4,jeux.getId());
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
            String req="select * from jeux order by id desc";
            Statement s = cnx.createStatement();
            ResultSet resultSet=s.executeQuery(req);
            while(resultSet.next()){
                jeux.add(new Jeux(resultSet.getInt("id"),resultSet.getString("nom"),resultSet.getString("description"),Jeux.Type.valueOf(resultSet.getString("type_plateforme"))));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return jeux;
        
        
    }

    @Override
    public List<Jeux> search(String name, String plat) {
        List<Jeux> jeux=new ArrayList();
        
        try {
            String req="select * from jeux where nom = ? and  type_plateforme  = ?  order by id desc";
            PreparedStatement s = cnx.prepareStatement(req);
            s.setString(1, name);
            s.setString(2, plat);
            ResultSet resultSet=s.executeQuery(req);
            while(resultSet.next()){
                jeux.add(new Jeux(resultSet.getInt("id"),resultSet.getString("nom"),resultSet.getString("description"),Jeux.Type.valueOf(resultSet.getString("type_plateforme"))));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return jeux;
        
    }
    
}
