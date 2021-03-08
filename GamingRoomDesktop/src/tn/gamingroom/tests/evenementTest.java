package tn.gamingroom.tests;


import java.sql.Date;
import static java.sql.JDBCType.NULL;
import java.time.format.DateTimeFormatter;
import tn.gamingroom.entities.Evenement;
import tn.gamingroom.entities.ReactionEv;
import tn.gamingroom.outils.MyConnection;
import tn.gamingroom.services.EvenementService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Asus
 */
public class evenementTest {

    public static void main(String[] args) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("1998-03-26");
        Date d = new Date(System.currentTimeMillis());
        EvenementService es = new EvenementService();
        Evenement e = new Evenement("comp1", d, d, "image", 1, 6, "description", "lieu", "yooutuube");
        MyConnection mc = MyConnection.getInstance();
        //MyConnection mc1 = MyConnection.getInstance();
/////////////////////////////////////////////////////////////////Ajour
        //es.ajoutEvenement(e);
        //System.out.println(es.listerEvenement());
///////////////////////////////////////////////////////////////Modif     
//        Evenement eAvM = es.findById(1);
//        Evenement eApM = new Evenement(1, eAvM.getNomEvent(), eAvM.getDateDeb(), eAvM.getDateFin(), "qsxqkpsjxsqic", eAvM.getCategorie_id(), 4, "k,scpskd,cks", eAvM.getLieu(), eAvM.getLienYoutube());
//        es.modifierEvenement(eApM);
//        System.out.println(es.listerEvenement());
///////////////////////////////////////////////////////////Suppression        
//        Evenement eSupp = es.findById(6);
//        es.suppressionEvenement(eSupp);
//////////////////////////////////////////////////////Liste        
       // System.out.println(es.listerEvenement());
///////////////////////////////////////////////////////Reaction Ajout
        ReactionEv rE=new ReactionEv(2, 4, "très bien");
       // es.reagirEvenement(rE);
       System.out.println(es.listerEvenement());
//////////////////////////////////////////////////////supprimer reaction
        ReactionEv rRs=new ReactionEv(6);
        //es.supprimerReacC(rRs);
        //System.out.println(es.listerEvenement());
//////////////////////////////////////////////////////Chercher Ev
        //System.out.println(es.chercherEvenement("aventure"));
/////////////////////////////////////////////////////inscription Ev        
        //es.sinscrirEvenement(2, 6);
/////////////////////////////////////////////////////tri ev
      // System.out.println(es.triEvenement());
////////////////////////////////////////////////upComingEvents
        //
        System.out.println(es.upComingEvents());

    }

}
