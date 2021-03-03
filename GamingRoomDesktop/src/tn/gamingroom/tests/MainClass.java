/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.tests;

import tn.gamingroom.entities.Produits;
import tn.gamingroom.outils.MyConnection;
import tn.gamingroom.services.ProduitCrud;

/**
 *
 * @author yasmine
 */
public class MainClass {

    public static void main(String[] args) {
        // MyConnection mc = MyConnection.getInstance();
        // MyConnection mc2 = MyConnection.getInstance();
        // System.out.println(mc.hashCode()+"__"+mc2.hashCode());
        ProduitCrud pc = new ProduitCrud();
        Produits p = new Produits("cle", "fff", 11, "cool", 40);
        Produits p2 = new Produits(2, "sarra.pnj", "ines", 150, "yass", 555);
        Produits p3 = new Produits(4, "ines.pnj", "noussa", 222, "tunis", 333);

//        Produits p1 = new Produits("zzzz","yyyy",555,"nnn",70);
          //pc.ajouterProduit(p1);
        //  pc.ajouterProduit(p);
//        System.out.println(pc.displayProduit());
        //  pc.updateProduit(p2);
        //  pc.ajouterProduit(p3);
       // pc.supprimerProduit(p2);
      //  pc.TrierParId().forEach(e->{System.out.println(e);});
        
        pc.RechercherParType("aa").forEach(e->{System.out.println(e);});
        
    }
}
