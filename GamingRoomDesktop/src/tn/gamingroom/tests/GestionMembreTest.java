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
        

        
        Membre m1 = new Membre("Trifi","eya",d,Membre.Genre.Femme,"51852630","eya.tri@esprit.tn","123456","eya.jpg",Membre.Role.Membre,true);
        Membre m2 = new Membre("Ayari","Farah",d,Membre.Genre.Femme,"51852630","farah.ayari@esprit.tn","123456","farah.jpg",Membre.Role.Membre,true);
        Membre m3 = new Membre(4,"Manita","Yasmine",d,Membre.Genre.Femme,"51852630","yasmine.manita@esprit.tn","123456","sonia.jpg",Membre.Role.Membre,true);
        
        Membre c1 = new Membre("Dahwathi","amine",d,Membre.Genre.Homme,"98910835","mohamedamine.dahwathi@esprit.tn","123456","yasmine.jpg",Membre.Role.Coach,"je suis amine fan de...",false);
        Membre c2 = new Membre("Smeti","Alaa",d,Membre.Genre.Homme,"98910835","alaa.smeti@esprit.tn","123456","alaa.jpg",Membre.Role.Coach,"je suis alaa fan de...",false);
        Membre c3 = new Membre(7,"Kacem","Mokhtar",d,Membre.Genre.Homme,"98910835","mokhtar.kacem@esprit.tn","123456","mokhtar.jpg",Membre.Role.Coach,"je suis mokhtar fan de...",false);

//        //m.setId(2);
//       ps.ajouterMembre(m1);
//       ps.ajouterMembre(m2);
//       ps.ajouterMembre(m3);
//       
//       ps.ajouterCoach(c1);
//       ps.ajouterCoach(c2);
//       ps.ajouterCoach(c3);

        //ps.sumprimerMembres(c3);
        //ps.modifierMembres(m3);
        
       //// pc.sumprimerMembres(c);
        //System.out.println(ps.DisplayMembres());
        //System.out.println(ps.TrierParId());
        System.out.println(ps.RechercherMembres("Trifi"));
        
//        Membre mm= ps.Login("sonia.farhat@esprit.tn", "1234");
//        if( mm != null){
//            System.out.println("Welcome back "+mm.getPrenom());
//        }
//        else{
//            System.out.println("Verifier vos informations");
//        }
}
        
    
}
