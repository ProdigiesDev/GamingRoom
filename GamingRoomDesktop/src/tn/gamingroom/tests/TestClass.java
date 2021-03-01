/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.tests;

import tn.gamingroom.entities.Avis;
import tn.gamingroom.entities.Jeux;
import tn.gamingroom.entities.Reclamation;
import tn.gamingroom.outils.MyConnection;
import tn.gamingroom.services.AvisCRUD;
import tn.gamingroom.services.JeuxCRUD;
import tn.gamingroom.services.ReclamationCRUD;
import tn.gamingroom.services.ScoreCRUD;

/**
 *
 * @author Dah
 */
public class TestClass {
    
    public static void main(String arg[]){
        
        Avis avis=new Avis("Test avis",1);
        AvisCRUD avisCRUD=new AvisCRUD();
        avisCRUD.ajouterAvis(avis);
        System.err.println(avisCRUD.getListAvis());
        
        
        /*
        Reclamation reclamation=new Reclamation("Test Rec","dah is a great gamer", 1);
        ReclamationCRUD reclamationCRUD=new ReclamationCRUD();
        reclamationCRUD.ajouterReclamation(reclamation);
        System.out.println(reclamationCRUD.getListReclamation());
        */
      /*  
        Jeux j=new Jeux("csgo");
        JeuxCRUD cRUD=new JeuxCRUD();
        cRUD.ajouter(j);
        //cRUD.supprimer(1);
        
        Jeux j2=new Jeux(2,"cs go 1.23");
        cRUD.modifier(j2);
        
        System.err.println(cRUD.getAll());
        */
      
      /*
        ScoreCRUD scoreCRUD=new ScoreCRUD();
        System.err.println(scoreCRUD.getScoreByJeuId(4));
        */
      
    }
}
