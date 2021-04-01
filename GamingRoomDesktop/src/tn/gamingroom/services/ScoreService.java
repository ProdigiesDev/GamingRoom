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
import tn.gamingroom.entities.Score;
import tn.gamingroom.gui.Jeux.GamesIds;
import tn.gamingroom.interfaces.IScore;
import tn.gamingroom.outils.MyConnection;

/**
 *
 * @author Dah
 */
public class ScoreService implements IScore{

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
            Logger.getLogger(ScoreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return scores;
    }

    public Score getScoreByJeuAndMemberId(int jeux_id,int membre_id) {
            List<Score> scores=new ArrayList();
        try {
            String req="select * from score where jeux_id ="+jeux_id+" and membre_id="+membre_id;
            Statement s=cnx.createStatement();
            ResultSet rs=s.executeQuery(req);
            if(rs.next()){
                return new Score(rs.getInt("id"), rs.getInt("score"),rs.getInt("jeux_id"),rs.getInt("membre_id"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ScoreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    @Override
    public int updateScore(Score s) {
        System.out.println("Inside Modifier Jeux");
        int nbModifier=0;
        try {
            
            String req="update score set score = ?  where jeux_id= ? and membre_id = ? ";
            if(GamesIds.TIC_TAC_TOE!=s.getJeux_id())
                req+=" and score < "+s.getScore();
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,s.getScore());
            ps.setInt(2,s.getJeux_id());
            ps.setInt(3,s.getMemebre_id());
            nbModifier = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nbModifier;
    }

    @Override
    public int ajouterScore(Score s) {
        int nb=0;
        try {
            String reqAjouter="insert into score (score,jeux_id,membre_id) values(?,?,?)";
            PreparedStatement ps=cnx.prepareStatement(reqAjouter);
            ps.setInt(1,s.getScore());
            ps.setInt(2,s.getJeux_id());
            ps.setInt(3, s.getMemebre_id());
            nb=ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();       
        }
        return nb;
    }
    
    
    public int updateOrAdd(Score s){
        
        Score score=getScoreByJeuAndMemberId(s.getJeux_id(), s.getMemebre_id());
        if(score==null){
            return ajouterScore(s);
        }
        
        return updateScore(s);
    }
}
