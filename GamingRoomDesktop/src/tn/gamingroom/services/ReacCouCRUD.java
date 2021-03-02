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
public class ReacCouCRUD implements IReacCours {

    @Override
    public void ajouterReacC(ReacCours r) {
        try {
            String requetereac = "INSERT INTO reactioncours(nb_interaction,commentaire,membre_id,cour_id)"
                    + "VALUES (?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requetereac);
            pst.setInt(1, r.getNb_interaction());
            pst.setString(2, r.getCommentaire());
            pst.setInt(3, r.getMembre_id());
            pst.setInt(4, r.getCour_id());
            pst.executeUpdate();
            System.out.println("Réaction ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimerReacC(ReacCours r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateReacC(ReacCours r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReacCours> displayReacC() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
