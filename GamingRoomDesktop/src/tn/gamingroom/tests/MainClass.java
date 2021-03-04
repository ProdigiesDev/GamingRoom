/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.tests;

import tn.gamingroom.entities.Cles;
import tn.gamingroom.entities.Produits;
import tn.gamingroom.outils.MyConnection;
import tn.gamingroom.services.CleService;
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
        Produits pr = new Produits("yasmine.pnj", "xxxx", 12, "zzzzz");
        Produits pr1 = new Produits("sonia.pnj", "xyxyxy", 10, "yyyyy");
        Produits pr2 = new Produits("ines.pnj", "ykykykyky", 100, "fde");
        Produits pr5 = new Produits("lilia.pnj", "cccc", 900, "ff");
        
       // pc.ajouterProduit(pr5);
        //  pc.ajouterProduit(pr);
        //   pc.ajouterProduit(pr1);
        //   pc.ajouterProduit(pr2);
        // System.out.println(pc.displayProduit());
        Produits pr3 = new Produits(5, "yass.pnj", "hhhhh", 14, "aaaaa");
        Produits pr4 = new Produits(7, "yass.pnj", "hhhhh", 14, "aaaaa");

        //  pc.updateProduit(pr3);
        //pc.supprimerProduit(pr3);
        //  pc.TrierParId().forEach(e->{System.out.println(e);});
        // pc.RechercherProduit("y").forEach(e->{System.out.println(e);});
        
          System.out.println(pc.bestProductsSelled());
        
        
        /*******************************************************************************************/
        
        
      
        CleService cs = new CleService();
        Cles c1 = new Cles(1, "1gf52-8fs76-1x66s-78954-0134", 1);
        Cles c2 = new Cles("mk5L5-8JsJJ-1x66s-7MP4g-0134d", 2);
        Cles c3 = new Cles(8, "MKL85-YASSK-YAskh-962kY-yaskh", 2);
        Cles c = new Cles(2, "yassmine", 2);

//        boolean verifierCode = c3.getCode().matches("^((([0-9]|[a-zA-Z]){5})-){4}(([0-9]|[a-zA-Z]){5})$");
//        if (verifierCode) {
//            cs.ajouterCle(c3);
//        } else {
//            System.out.println("Code invalide");
//        }

    
      // cs.supprimerCle(20);
        // System.out.println(cs.displayCle(2));
       // cs.updateCle(c3);
        
      
    }
}
