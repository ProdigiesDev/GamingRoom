/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui;


import com.esprit.gamingroom.services.ServicePanier;
import com.esprit.gamingroom.entities.Produit;
import com.esprit.gamingroom.outils.Statics;
import com.codename1.components.FloatingActionButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.esprit.gamingroom.services.ProduitService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author elbrh
 */
public class Panier extends  Form{

    Container all;

    float total = 0;
    static Map<Produit, Integer> produits=new HashMap();

    
    public Panier() {
        //super.setupSideMenu(f2);
        all = new Container(BoxLayout.y());
        Toolbar tb = this.getToolbar();
        tb.setTitle("Panier");
        tb.setTitleCentered(true);

        Label tot = new Label("Prix total : " + Float.toString(total));
        ProduitService panier=new ProduitService();
        List<Produit> list=panier.AffichageProduit();
        
        for (int j = 0; j < list.size(); j++) {
            produits.put(list.get(j),j+1);
        }
        if (produits == null || produits.size() == 0) {
            produits = new HashMap<>();
            Label vide = new Label("Panier vide!");
            vide.getAllStyles().setAlignment(Label.CENTER);
            this.add(vide);
        } else {
            for (Produit p : produits.keySet()) {
                Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Label label = new Label();
                System.out.println(p.getImage());
                int deviceWidth = Display.getInstance().getDisplayWidth() / 4;
                Image placeholder = Image.createImage(deviceWidth, deviceWidth); //square image set to 10% of screen width
                EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                label.setIcon(URLImage.createToStorage(encImage,
                        "Large_" + "http://localhost/PiSymfony/web/uploads/images/products/" + p.getImage()
                        + "", "http://localhost/PiSymfony/web/uploads/images/products/" + p.getImage()
                        + "", URLImage.RESIZE_SCALE_TO_FILL));

                Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                FloatingActionButton delete = FloatingActionButton.createFAB(FontImage.MATERIAL_CLEAR);
                delete.addActionListener(e -> {
                    produits.remove(p);
                    Panier b = new Panier();
                    b.getF().show();
                });
                FloatingActionButton edit = FloatingActionButton.createFAB(FontImage.MATERIAL_EDIT);
                edit.addActionListener(e -> {
                    Dialog d = new Dialog("Quantite");
                    TextField txt = new TextField();
                    txt.setConstraint(TextField.NUMERIC);
                    txt.setText(Panier.getQuantite(p) + "");
                    d.add(txt);
                    d.dispose();
                    Button ok = new Button("          Ok           ");
                    Button close = new Button("         Close          ");
                    ok.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            Panier p = new Panier();
                            try {
                                //p.addToBasket(p, Integer.parseInt(txt.getText()));
                                d.dispose();
                                System.out.println(txt.getText());
                            } catch (NumberFormatException ex) {
                                d.showBack();
                            }
                        }
                    });
                    close.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            d.dispose();
                        }
                    });
                    d.add(ok);
                    d.add(close);
                    d.showDialog();
                });
                Label nom = new Label("Produit: " + p.getLibelle());
                Label prix = new Label("Prix : " + Float.toString(p.getPrix()));
                Label Quantite = new Label("Quantite : " + produits.get(p));
                total = total + (p.getPrix() * produits.get(p));
                tot.setText("Prix total : " + Float.toString(total));
                c2.add(nom);
                c2.add(prix);
                c2.add(Quantite);
                c1.add(label);
                c1.add(c2);
                c1.getAllStyles().setBorder(Border.createLineBorder(1));
                c1.getAllStyles().setMarginTop(2);
                Container c3 = new Container(BoxLayout.y());
                c3.addComponent(edit);
                c3.addComponent(delete);
                c1.addComponent(c3);
                all.add(c1);
                all.refreshTheme();
            }
            all.add(tot);
            Button bt = new Button("PAYER");
            bt.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
//                    Commande c = new Commande();
//                    c.setId_user(ConnectedUser.getUser());
//                    CommandeService cs = new CommandeService();
//                    cs.PassCmd(c, produits);
                }
            });
            all.add(bt);
            this.setLayout(BoxLayout.y());
            this.add(all);
        }

    }

    public void addToBasket(Produit p, int q) {
        if (produits == null) {
            produits = new HashMap<>();
            produits.put(p, q);
            System.out.println(produits.size());
        } else {
            if (produits.containsKey(p)) {
                produits.remove(p);
                produits.put(p, q);
            } else {
                produits.put(p, q);
            }

            System.out.println(produits.size());
        }

    }

    public static int getsize() {
        if (produits == null) {
            produits = new HashMap<>();
            return 0;
        }
        return produits.size();
    }

    public static int getQuantite(Produit p) {
        int q = 1;
        if (produits != null && produits.containsKey(p)) {
            q = produits.get(p);
        }
        return q;
    }

    public Form getF() {
        return this;
    }

}
