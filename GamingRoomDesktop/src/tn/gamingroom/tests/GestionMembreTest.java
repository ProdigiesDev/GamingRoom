/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.tests;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import tn.gamingroom.entities.Categorie;
import tn.gamingroom.entities.CategorieMembre;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.outils.MyConnection;
import tn.gamingroom.services.CategorieMembreServices;
import tn.gamingroom.services.CategorieServices;
import tn.gamingroom.services.MembreServices;

/**
 *
 * @author Sonia
 */
public class GestionMembreTest {
    public static void main(String [] args){
        MyConnection mc = new MyConnection();
        MembreServices ps = new MembreServices();
        
        DateTimeFormatter f = DateTimeFormatter.ofPattern("08-03-1999");
        Date d = new Date(System.currentTimeMillis());
        

       
        Membre a1 = new Membre("Farhat","Sonia",d,Membre.Genre.Femme,"96431792","sonia.farhat@esprit.tn","123456","eya.jpg",Membre.Role.Admin,true);
        Membre m1 = new Membre("Trifi","Eya",d,Membre.Genre.Femme,"51852630","eya.trifi@esprit.tn","123456","eya.jpg",Membre.Role.Membre,true);
        Membre m2 = new Membre("Ayari","Farah",d,Membre.Genre.Femme,"51852630","farah.ayari@esprit.tn","123456","farah.jpg",Membre.Role.Membre,true);
        Membre m3 = new Membre("Manita","fedi",d,Membre.Genre.Femme,"51852630","yasmine.manita@esprit.tn","123456","sonia.jpg",Membre.Role.Membre,true);
        
        
        Membre c1 = new Membre("Dahwathi","Amine",d,Membre.Genre.Homme,"98910835","mohamedamine.dahwathi@esprit.tn","123456","yasmine.jpg",Membre.Role.Coach,"je suis amine fan de...",false);
        Membre c2 = new Membre("Smeti","Alaa",d,Membre.Genre.Homme,"98910835","alaa.smeti@esprit.tn","123456","alaa.jpg",Membre.Role.Coach,"je suis alaa fan de...",false);
        Membre c3 = new Membre(12,"Kacem","Mokhtar",d,Membre.Genre.Homme,"98910835","mokhtar.kacem@esprit.tn","123456","mokhtar.jpg",Membre.Role.Coach,"je suis mokhtar fan de...",false);
///// Ajouter Membre//////////
////        //m.setId(2);
       ps.ajouterMembre(a1);
       ps.ajouterMembre(m1);
       ps.ajouterMembre(m2);
       ps.ajouterMembre(m3);
//
//// ///// Ajouter Coach////////// 
//
//       ps.ajouterCoach(c1);
//       ps.ajouterCoach(c2);
//       ps.ajouterCoach(c3);

///// Supprimer Membre//////////
//        ps.sumprimerMembres(c3);
//       System.out.println(ps.DisplayMembres());
       
     ///// Modifier Membre//////////  
       
//        ps.modifierMembres(m3);
//        System.out.println(ps.DisplayMembres());
        
     ///// Trier membre par id ////////// 
//        System.out.println(ps.TrierParId());
        
        //////// recherche par nom ou prenom ou email ////////// 
//        System.out.println(ps.RechercherMembres("Trifi"));
//        System.out.println(ps.RechercherMembres("Eya"));


//        ///// Login////////// 
//        Membre mm = ps.Login("farah.ayari@esprit.tn","123456");
//        if( mm != null){
//            System.out.println("Welcome back "+mm.getPrenom());
//        }
//        else{
//            System.out.println("Verifier vos informations");
//        }

///// Forget password////////// 

//       MembreServices ms=new MembreServices();
//        ms.fPwd(7);
////  


          
        CategorieServices cs = new CategorieServices();
        
        Categorie cat1 = new Categorie("Action");
        Categorie cat2 = new Categorie("Adventure");
        Categorie cat3 = new Categorie("Sport");
        Categorie cat4 = new Categorie("Puzzle");

////          ///// Ajouter categorie////////// 
//        cs.ajouterCategorie(cat1);
//        cs.ajouterCategorie(cat2);
//        cs.ajouterCategorie(cat3);
//        cs.ajouterCategorie(cat4);
//        
            ///// afficher categorie////////// 
//         System.out.println(cs.DisplayCategorie());

  ///// SUPPRIMER categorie////////// 
//////        cs.supprimerCategorie(cat4);
////         System.out.println(cs.DisplayCategorie());
////         System.out.println(cs.TrierParIdCat());

  ///// Trier categorie par NOM////////// 
////         System.out.println(cs.TrierParNomCat());

  ///// RECHERCHER categorie ////////// 
////         System.out.println(cs.RechercherCategorie("Action"));


        CategorieMembreServices cms=new CategorieMembreServices();
        CategorieMembre cm1 = new CategorieMembre(7,2);
        CategorieMembre cm2 = new CategorieMembre(7,3);
//        
//       cms.AffecterCategorieMembre(cm1);
//          cms.AffecterCategorieMembre(cm2);
//        System.out.println(cms.DisplayCategorie(7));
//        
//   
//            cms.SuprimerCategorieMembre(cm1);

        


}
        
    
}
