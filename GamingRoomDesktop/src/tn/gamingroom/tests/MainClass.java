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
        MyConnection mc = new MyConnection();

        ProduitCrud pc = new ProduitCrud();
        Produits p = new Produits("cle","fff",110,"cool",40);
        Produits p1 = new Produits("zzzz","yyyy",555,"nnn",70);
pc.ajouterProduit(p1);
        pc.ajouterProduit(p);
        System.out.println(pc.displayProduit());

    }
}
