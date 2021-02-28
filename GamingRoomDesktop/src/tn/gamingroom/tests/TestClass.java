/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.tests;

import tn.gamingroom.entities.Avis;
import tn.gamingroom.outils.MyConnection;
import tn.gamingroom.services.AvisCRUD;

/**
 *
 * @author Dah
 */
public class TestClass {
    
    public static void main(String arg[]){
        MyConnection mc = MyConnection.getInstance();
        Avis avis=new Avis("Test avis",1);
        AvisCRUD avisCRUD=new AvisCRUD();
        avisCRUD.ajouterAvis(avis);
    }
}
