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
import tn.gamingroom.entities.Cours;
import tn.gamingroom.entities.ReacCours;
import tn.gamingroom.interfaces.IReacCours;
import tn.gamingroom.outils.MyConnection;

/**
 *
 * @author eyatr
 */
public class ServiceReacCours implements IReacCours {

    @Override
    public int ajouterReacC(ReacCours r) {
        int nb_ajout=0;
        try {
            String requetereac = "INSERT INTO reactioncours(interaction,commentaire,membre_id,cour_id)"
                    + "VALUES (?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requetereac);
            pst.setInt(1, r.getNb_interaction());
            pst.setString(2, r.getCommentaire());
            pst.setInt(3, r.getMembre_id());
            pst.setInt(4, r.getCour_id());
            nb_ajout=pst.executeUpdate();
            if (nb_ajout <= 0) {
                System.out.println("Verifiez vos données");
            } else {
                System.out.println("Réaction ajouté");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb_ajout;

    }

    @Override
    public int supprimerReacC(ReacCours r) {
        int nb_supp=0;
        try {
            String requeteSP = "DELETE FROM reactioncours where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requeteSP);
            pst.setInt(1, r.getId());
            nb_supp=pst.executeUpdate();
            
            if (nb_supp <= 0) {
                System.out.println("Verifiez vos données");
            } else {
                System.out.println("Reaction supprimé");
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb_supp;
    }
    

    public List<ReacCours> getListReacCours(int cour_id){
        
        List<ReacCours> reacCours = new ArrayList<>();
        try {
            String requeteDs = "SELECT * FROM reactioncours where commentaire !=''  and  cour_id ="+cour_id;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requeteDs);
            while (rs.next()) {
                reacCours.add(new ReacCours(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getInt(5)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reacCours;
    }
    
    public ReacCours getFirstReactionById(int courId,int memberId){
        
         try {
            String requeteDs = "SELECT * FROM reactioncours where cour_id ='"+courId+"' and membre_id="+memberId;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requeteDs);
            if (rs.next()) {
                return new ReacCours(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getInt(5));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return null;
    
    }
    
    
    public int updateReacC(ReacCours r) {
        int nb_supp=0;
        try {
            String requeteSP = "update reactioncours set interaction=?  where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requeteSP);
            
            pst.setInt(1, r.getNb_interaction());
            pst.setInt(2, r.getId());
            nb_supp=pst.executeUpdate();
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb_supp;
    }
    
    public Integer[] getNbInteraction(int id){
        Integer[]  nbReaIntegers=new Integer[2];
        
         try {
            String reqnbLike = "SELECT count(*) FROM reactioncours where  interaction=1 and cour_id ="+id;
            String reqdsnbLike = "SELECT count(*) FROM reactioncours where  interaction=-1 and cour_id ="+id;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(reqnbLike);
            if (rs.next()) {
                nbReaIntegers[0]=rs.getInt(1);
            }
            
             rs = st.executeQuery(reqdsnbLike);
            if (rs.next()) {
                nbReaIntegers[1]=rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return nbReaIntegers;
    }
    

}
