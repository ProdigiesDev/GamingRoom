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
import tn.gamingroom.entities.Reclamation;
import tn.gamingroom.interfaces.IReclamation;
import tn.gamingroom.outils.MyConnection;

/**
 *
 * @author Dah
 */
public class ReclamationService implements IReclamation{
    
    private Connection  cnx=MyConnection.getInstance().getCnx();
    
    @Override
    public int  ajouterReclamation(Reclamation reclamation) {
        int nb=0;
        try {
            String reqAjouter="insert into reclamation (sujet,contenue,membre_id) values(?,?,?)";
            PreparedStatement ps=cnx.prepareStatement(reqAjouter);
            ps.setString(1, reclamation.getSujet());
            ps.setString(2,reclamation.getContenue());
            ps.setInt(3,reclamation.getMembre_id());
            nb=ps.executeUpdate();
            System.out.println("Reclamation ajouter");
        } catch (SQLException ex) {
            ex.printStackTrace();       
        }
        return nb;
    }

    @Override
    public List<Reclamation> getListReclamation() {
        List<Reclamation> reclamations=new ArrayList();
        try {
            String reqLister="select * from reclamation order by id desc";
            Statement statement=cnx.createStatement();
           
            ResultSet rs =  statement.executeQuery(reqLister);
             while(rs.next()){
                reclamations.add(new Reclamation(rs.getInt("id"), rs.getString("sujet"), rs.getString("contenue"), rs.getInt("membre_id")));
            }
            System.out.println("getListReclamation");
        } catch (SQLException ex) {
            ex.printStackTrace();       
        }
        
        return reclamations;
    }
    
}
