/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import tn.gamingroom.entities.Notification;
import tn.gamingroom.interfaces.INotification;
import tn.gamingroom.outils.MyConnection;

/**
 *
 * @author Asus
 */
public class NotificationService implements INotification {

    @Override
    public void ajoutEvenement(Notification t) {
        try {
            String requete = "insert into notification (member_id,title,body) VALUES(?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
           
            pst.setInt(1, t.getTo());
            pst.setString(2, t.getTitle());
            pst.setString(3, t.getBody());
            pst.executeUpdate();
            System.out.println("Notification inserée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
