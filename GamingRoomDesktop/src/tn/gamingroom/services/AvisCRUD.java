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
import tn.gamingroom.entities.Avis;
import tn.gamingroom.interfaces.IAvis;
import tn.gamingroom.outils.MyConnection;

/**
 *
 * @author Dah
 */
public class AvisCRUD implements IAvis{

    
    private Connection  cnx=MyConnection.getInstance().getCnx();
    
    @Override
    public void ajouterAvis(Avis avis) {
        try {
            String reqAjouter="insert into avis (avis,member_id) values(?,?)";
            PreparedStatement ps=cnx.prepareStatement(reqAjouter);
            ps.setString(1,avis.getAvis());
            ps.setInt(2, avis.getMember_id());
            ps.executeUpdate();
            System.out.println("Avis ajouter");
        } catch (SQLException ex) {
            ex.printStackTrace();       
        }
;
    }

    @Override
    public List<Avis> getListAvis() {

        
        List<Avis> avis=new ArrayList();
        
        try {
            String req="select * from avis";
            Statement s = cnx.createStatement();
            ResultSet resultSet=s.executeQuery(req);
            while(resultSet.next()){
                avis.add(new Avis(resultSet.getInt("id"),resultSet.getString("avis"), resultSet.getInt("member_id")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return avis;    
    }
    
}
