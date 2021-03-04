 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.tests;

import tn.gamingroom.services.CommandService;
import tn.gamingroom.entities.Commande;
import tn.gamingroom.entities.Panier;

/**
 *
 * @author Admin
 */
public class CommandeTest {

    public CommandeTest(String test_commande, int par) {
    }
   
public static void main (String arg[]){
    CommandeTest commandetest = new CommandeTest("Test commande",1);
    CommandService cs = new CommandService();
    //Commande c1= new Commande("1","111","22","en cours" );

   }

   static void ajouterProdPanier(){
       Panier panier=new Panier(0, 0);
   }

}