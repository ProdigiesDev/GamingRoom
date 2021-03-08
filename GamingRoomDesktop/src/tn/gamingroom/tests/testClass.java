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
        DateTimeFormatter f = DateTimeFormatter.ofPattern("00-00-0000");
        Date d = new Date(System.currentTimeMillis());
        MyConnection mc = MyConnection.getInstance();
        //DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        //COURS
        Cours c1 = new Cours("FreeFire", "FreeFire game", 23, 1, d, "Action", 1);
        Cours c2= new Cours("MMORPG ", "MMORPG  game", 26, 2, d, "Action", 2);
        Cours c3= new Cours("FIFA", "FIFA game", 15, 3, d, "Action", 3);
        Cours c4= new Cours("LOL", "LOL game", 23, 4, d, "Action", 4);
        ServiceCours coursCrud = new ServiceCours();
        /////////////////////////////Ajouter Cours////////////////////
        //coursCrud.ajouterCours(c1);
        //coursCrud.ajouterCours(c2);
        //coursCrud.ajouterCours(c3);
        //coursCrud.ajouterCours(c4);
        
        /////////////////////////////Modifier Cours////////////////////
//        Cours c5= coursCrud.findById(1);
//        Cours c6= new Cours (c5.getId(),"GTA", c5.getDescription(),c5.getNb_participants(),c5.getMembre_id(),c5.getDate_creation(),c5.getTags(),c5.getCategorie_id());
//        coursCrud.updateCours(c6);
        
        /////////////////////////////Supprimer Cours////////////////////
//        c1.setId(3);
//        coursCrud.supprimerCours(c1);
//        
        //////////////////Afficher liste des cours///////////////
        //System.out.println(coursCrud.displayCours());
          
        //////////////////Chercher cours selon nom et description ///////////////
       // System.out.println(coursCrud.searchCours("MMORPG"));
        
        ////////////////////Trier cours avec ordre decroissant////////////////
        //System.out.println(coursCrud.trierCoursID());
        
        
        ///////////////////////////Find cours By Id///////////////////
        //System.out.println(coursCrud.findById(15));
        
        //cours selon préference
        //System.out.println(coursCrud.displayprefcours(2));
        
        
        
        
        
        
        
       //REACTION COURS
        ReacCours r1 = new ReacCours(1, "j'ai aimé ce cours", 1, 1);
        ReacCours r2 = new ReacCours(-1, "J'ai pas aimé ce cours", 2, 3);
        ReacCours r3 = new ReacCours(1, "Merci coach pour ce cours", 4, 4);
        ServiceReacCours reactionc=new ServiceReacCours();
        /////////////////Ajouter Reaction Cours////////////////
        //reactionc.ajouterReacC(r1);
        
       /////////////////Supprimer Reaction Cours////////////////
        //r2.setId(15);
        //reactionc.supprimerReacC(r1);
        
        

        //Participants cours
        ParticipantsCours p1= new ParticipantsCours(1,2);
        ParticipantsCours p2= new ParticipantsCours(2,3);
        ParticipantsCours p3= new ParticipantsCours(3,4);
        
        
        ServiceParticipantsCours pc = new ServiceParticipantsCours();
        
        /////////Ajouter Participant//////////
        //pc.ajouterParticipant(p1);
        //////////////Afficher les participants///////////
        //System.out.println(pc.DisplayParticipants());
        
        
        
        
       
       

    }

}
