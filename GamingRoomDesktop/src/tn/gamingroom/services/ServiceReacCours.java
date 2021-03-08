/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
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
            System.out.println("Réaction ajouté");

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
    

    

}
