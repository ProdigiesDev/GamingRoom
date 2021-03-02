/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.tests;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import tn.gamingroom.entities.Cours;
import tn.gamingroom.entities.ReacCours;
import tn.gamingroom.outils.MyConnection;
import tn.gamingroom.services.CoursCRUD;
import tn.gamingroom.services.ReacCouCRUD;

/**
 *
 * @author eyatr
 */
public class testClass {

    public static void main(String[] args) {
        //COURS
        //DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        DateTimeFormatter f = DateTimeFormatter.ofPattern("01-02-2021");
        Date d = new Date(System.currentTimeMillis());
        Cours c = new Cours(0, "FreeFire", "FreeFire game", 23, 1, d, "xx", 1);
        MyConnection mc = MyConnection.getInstance();
        //MyConnection mc1 = MyConnection.getInstance();

        CoursCRUD coursCrud = new CoursCRUD();
        //coursCrud.ajouterCours(c);
        //coursCrud.updateCours(c);
        //coursCrud.supprimerCours(c);
        //System.out.println(coursCrud.displayCours());
        
       //REACTION COURS
        ReacCours r1 = new ReacCours(0, 5, "j'ai aimé ce cours", 1, 1);
        ReacCouCRUD reactionc=new ReacCouCRUD();
        reactionc.ajouterReacC(r1);
        
        
       

    }

}
