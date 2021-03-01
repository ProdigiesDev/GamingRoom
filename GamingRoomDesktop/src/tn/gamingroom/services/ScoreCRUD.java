/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.gamingroom.entities.Score;
import tn.gamingroom.interfaces.IScore;
import tn.gamingroom.outils.MyConnection;

/**
 *
 * @author Dah
 */
public class ScoreCRUD implements IScore{

    private Connection cnx=MyConnection.getInstance().getCnx();

    
    @Override
    public List<Score> getScoreByJeuId(int jeux_id) {
            List<Score> scores=new ArrayList();
        try {
            String req="select * from score where jeux_id ="+jeux_id;
            Statement s=cnx.createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next()){
                scores.add(new Score(rs.getInt("id"), rs.getInt("score"),rs.getInt("jeux_id"),rs.getInt("membre_id")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ScoreCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return scores;
    }
    
}
