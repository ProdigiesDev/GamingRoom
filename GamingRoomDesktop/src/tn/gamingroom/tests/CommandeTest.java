 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.tests;

import java.sql.Date;
import tn.gamingroom.services.CommandService;
import tn.gamingroom.entities.Commande;
import tn.gamingroom.entities.Panier;
import tn.gamingroom.services.PanierService;

/**
 *
 * @author Admin
 */
public class CommandeTest {

   
   
    public static void main (String arg[]){
        PanierService panierService=new PanierService();
        CommandService commandService=new CommandService();
       //       /*  Ajouter Commande */
       
//         Commande  commande=new Commande(3);
//         int nb=commandService.ajouterCommand(commande);
//         if(nb==0)
//                System.out.println("Erreur Ajout commande");
//         else
//             System.out.println("Commande a ete ajouter");
         
       
        
        // Ajouter produit au panier 
        
            Panier panier=new Panier(1,5,10);
           int res=panierService.ajouterProd(panier);
           if(res>0)
               System.out.println("Produit a été ajouter au panier");
           else
               System.out.println("Erreur lors d'ajout d'un produit");
        
          
        /* Modifier qt produit*/ 
        
//        Panier p=new Panier(13,1,13,20 );
//        int res2=panierService.modifierQuantity(p);
//        if(res2>0)
//            System.out.println("Quantité a ete modifier");
//        else
//            System.out.println("Erreur lors de la modification de la quantité");
        
        
       /*Supprimer panier id *
       int res3=panierService.supprimerProd(10);
       if(res3>0)
           System.out.println("Produit est supprimer");
       else
           System.out.println("Erreur lors de la suppression");
       */
       
       
       /* Consulter Panier */
       System.out.println(panierService.consulterPanier(3));
       
       
       /* Modifier etat d'un commande *
        Commande  commande2=new Commande(11,3,new Date(10,10,10),Commande.Statu.Valider);
        int res4=commandService.modifierCommand(commande2);
       if(res4>0)
           System.out.println("Commande a  ete  modifier");
       else
           System.out.println("Erreur lors de la modification commande");
       */
       
        /* Supprimer Commande 
       int res5=commandService.supprimerCommand(1);
       if(res5>0)
           System.out.println("Commande est supprimer!");
       else
           System.out.println("Erreur lors de la suppression");*/
       
    //////////////////////////////////////////////////////////////////////   
       
       
       /* Consulter Mes Commande */
        System.out.println(commandService.consulterMesCommande(3));
        
        
       /* Consulter all Commande */
//        System.out.println(commandService.consulterCommande());
        
        
        
        
        
      
   }

   
  
}