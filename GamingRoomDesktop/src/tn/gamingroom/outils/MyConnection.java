/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.outils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author House
 */
public class MyConnection {

    String url = "jdbc:mysql://localhost:3306/gamingroom"; //nom de l'API:esm SJBD/l'addresse ip mtas l sjbd wel port/nom bd 
    String login = "gamingRoomUser";
    String pwd = "!&_UkTz/Cw`*2#[u";
    Connection cnx;
    public static MyConnection instance;// declarer une var instance statique de meme type que la classe

    private MyConnection() {
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // methode static 
    public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }

}
