/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.tests;

import java.lang.reflect.Array;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import tn.gamingroom.entities.Cours;
import tn.gamingroom.entities.ParticipantsCours;
import tn.gamingroom.entities.ReacCours;
import tn.gamingroom.outils.MyConnection;
import tn.gamingroom.services.ServiceCours;
import tn.gamingroom.services.ServiceParticipantsCours;
import tn.gamingroom.services.ServiceReacCours;

/**
 *
 * @author eyatr
 */
public class testClass {

    public static void main(String[] args) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("01-02-2021");
        Date d = new Date(System.currentTimeMillis());
        MyConnection mc = MyConnection.getInstance();
        //DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        //COURS
        
        Cours c = new Cours("FreeFire", "FreeFire game", 23, 1, d, "xx", 1);
        Cours c1= new Cours("lol", "lol game", 23, 2, d, "xx", 1);
        Cours c2= new Cours("lol", "lol game", 23, 2, d, "xx", 1);
        Cours c3= new Cours("lol", "lol game", 23, 2, d, "xx", 1);
        ServiceCours coursCrud = new ServiceCours();
        //coursCrud.ajouterCours(c1);
        //coursCrud.updateCours(c);
        //coursCrud.supprimerCours(c1);
        //c1.setId(27);
        //System.out.println(coursCrud.displayCourswithId(27));
        //System.out.println(coursCrud.displayCourswithName("l"));
        //System.out.println(coursCrud.trierCoursID());
        //System.out.println(coursCrud.displayCours());
        
        
        
       //REACTION COURS
        ReacCours r1 = new ReacCours(0, 5, "j'ai aimé ce cours", 1, 1);
        ServiceReacCours reactionc=new ServiceReacCours();
        //reactionc.ajouterReacC(r1);
        //reactionc.supprimerReacC(r1);
        
        //Participants cours
        ParticipantsCours p1= new ParticipantsCours(2,4);
        ServiceParticipantsCours pc = new ServiceParticipantsCours();
        //pc.ajouterParticipant(p1);
        //System.out.println(pc.DisplayParticipants());
        
        //cours selon préference
        System.out.println(coursCrud.displayprefcours(2));
        
        
       
       

    }

}
