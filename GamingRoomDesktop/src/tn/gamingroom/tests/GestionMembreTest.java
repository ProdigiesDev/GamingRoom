/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.tests;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.outils.MyConnection;
import tn.gamingroom.services.MembreServices;

/**
 *
 * @author Sonia
 */
public class GestionMembreTest {
    public static void main(String [] args){
        MyConnection mc = new MyConnection();
        MembreServices ps = new MembreServices();
        
        DateTimeFormatter f = DateTimeFormatter.ofPattern("01-02-2021");
        Date d = new Date(System.currentTimeMillis());
        

        
        Membre m = new Membre("Farhat","sonia",d,Membre.Genre.Femme,"51852630","sonia.farhat@esprit.tn","123456","sonia.jpg",Membre.Role.Membre,true);
        
        Membre m1 = new Membre("Manita","Yasmine",d,Membre.Genre.Femme,"51852630","sonia.farhat@esprit.tn","123456","sonia.jpg",Membre.Role.Membre,true);
        //m.setId(2);
       ps.ajouterMembre(m);
        //pc.sumprimerMembres(m);
        //pc.modifierMembres(m);
        //Membre c = new Membre(5,"Manita","Yasmine",d,"Femme","98910835","yasmine@esprit.tn","123456","yasmine.jpg",Membre.Role.Coach,"je suis yasmine fans de...",false);
        //pc.ajouterCoach(c);
       //// pc.sumprimerMembres(c);
        //System.out.println(ps.DisplayMembres());
        
        Membre m2= ps.Login("sonia.farhat@esprit.tn", "1234");
        if( m2 != null){
            System.out.println("Welcome back "+m2.getPrenom());
        }
        else{
            System.out.println("Verifier vos informations");
        }
}
        
    
}
