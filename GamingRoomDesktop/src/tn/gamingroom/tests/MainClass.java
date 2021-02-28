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
        Produits p = new Produits();

        pc.ajouterProduit(p);

    }
}
