/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.gamingroom.entities.Produit;
import com.esprit.gamingroom.services.ServiceProduit;
import java.util.ArrayList;

/**
 *
 * @author yasmine
 */
public class ProduitAffichageForm extends Form  {
        Form current;
    ArrayList<Produit> data = new ArrayList<>();

    
    public ProduitAffichageForm (Form previous) {
        setTitle("Listes  Des Produits");
        data = ServiceProduit.getInstance().AffichageProduit();
        Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        for (int i = 0; i < data.size(); i++) {
            Container x = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container xx = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Produit u = new Produit();
            u.setIdprod(data.get(i).getIdprod());
            u.setIdCat(data.get(i).getIdCat());
            u.setImage(data.get(i).getImage());
            u.setLibelle(data.get(i).getLibelle());
            u.setPrix(data.get(i).getPrix());
            u.setDescription(data.get(i).getDescription());
            Button modif = new Button("Modifier");
            Button supp = new Button("Supprimer");

            SpanLabel separation = new SpanLabel("----------------------------");
            SpanLabel idprod = new SpanLabel("idprod : " + data.get(i).getIdprod());
            SpanLabel idcat = new SpanLabel("id_cat : " + data.get(i).getIdCat());
            SpanLabel image = new SpanLabel("image : " + data.get(i).getImage());
            SpanLabel libelle = new SpanLabel("libelle : " + data.get(i).getLibelle());
            SpanLabel prix = new SpanLabel("prix : " + data.get(i).getPrix());
            SpanLabel description = new SpanLabel("description : " + data.get(i).getDescription());
          
//            supp.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent evt) {
//                    ServiceProduit.getInstance().deleteOffres(u.getIdprod());
//                    Dialog.show("Success", "Stade Deleted Successfully.", "OK", "Cancel");
//                    new HomeForm().show();
//                }
//            });
//            modif.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent evt) {
//
//                    new modifierForm(u);
//                }
//            });

        //  x.addAll(idprod, supp, modif);
           // xx.addAll(supp,modif);
         // y.addAll(x, xx, separation);
         
         
          x.add(idprod);
          x.add(idcat);
          x.add(image);
          x.add(libelle);
          x.add(prix);
          x.add(description);
          
         y.add(x);
        }


        addAll(y);
   getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack()); // Revenir vers l'interface précédente
    }
    
}
