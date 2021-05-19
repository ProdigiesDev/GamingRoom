///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.esprit.gamingroom.gui.cours;
//
//import com.codename1.ui.Button;
//import com.codename1.ui.Command;
//import com.codename1.ui.Container;
//import com.codename1.ui.Dialog;
//import com.codename1.ui.Display;
//import com.codename1.ui.FontImage;
//import com.codename1.ui.Form;
//import com.codename1.ui.Label;
//import com.codename1.ui.TextField;
//import com.codename1.ui.events.ActionEvent;
//import com.codename1.ui.events.ActionListener;
//import com.codename1.ui.layouts.BoxLayout;
//import com.codename1.ui.spinner.Picker;
//import com.esprit.gamingroom.entities.Cours;
//import com.esprit.gamingroom.services.CoursServices;
//
///**
// *
// * @author eyatr
// */
//public class UpdateCoursForm extends Form{
////     Form current;
////
////    public UpdateCoursForm(Form previous , Cours c,int id) {
////   
////
////        setTitle("Modifier un cours");
////        setLayout(BoxLayout.y());
////         TextField tfNom = new TextField(c.getNomCours(), "Nom Cours ",20,TextField.ANY);
////         TextField tfDescription = new TextField(c.getDescription(), "Description ",20,TextField.ANY);
////         TextField tfnbpart = new TextField (c.getNb_participants()+"", "Nombre de participants ",20,TextField.ANY);
////         TextField tfmem = new TextField(c.getMembre_id()+"", "Membre ",20,TextField.ANY);
////         TextField tftags = new TextField(c.getTags(), "Mots clés ",20,TextField.ANY);
////         TextField tfImage = new TextField(c.getImage(), "Image ",20,TextField.ANY);
////         TextField tfCategorie = new TextField(c.getCategorie_id()+"", "Catégorie ",20,TextField.ANY);
////         TextField tfLienyoutube = new TextField(c.getLienYoutube(), "Lien Youtube",20,TextField.ANY);
////      
////
////         Label idf=new Label();
////         idf.setText(String.valueOf(c.getId()));
////      
////        Button btnValider = new Button("Modifier Cours");
////        
////        btnValider.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent evt) {
////                if ((tfNom.getText().length()==0)||(tfDescription.getText().length()==0)||(tfImage.getText().length()==0))//||(tfpdf.getText().length()==0)||(tfvideourl.getText().length()==0))
////                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
////                else
////                {
////                    try {
////                Cours cours = new Cours( tfNom.getText(),tfDescription.getText(),Integer.parseInt(tfnbpart.getText()),Integer.parseInt(tfmem.getText()),tftags.getText(),tfImage.getText(),Integer.parseInt(tfCategorie.getText()),tfLienyoutube.getText());
////                        if( CoursServices.getInstance().updateCours(c,id))
////                            Dialog.show("Success","Connection accepted",new Command("OK"));
////                        else
////                            Dialog.show("ERROR", "Server error", new Command("OK"));
////                    } catch (NumberFormatException e) {
////                        //Dialog.show("ERROR", "Status must be a number", new Command("OK"));
////                    }
////                    
////                }
////                            
////            }
////        });
////        
////        addAll(idf,tfNom, tfDescription, tfnbpart,tfCategorie,tftags,tfImage,tfmem,tfLienyoutube,btnValider);
////        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
////                ,  e -> new ListCoursForm(current).show()); // Revenir vers l'interface précédente
////                
////    }
////    
//     Label labelNom, labelDescription,labelnbparticipant,labelmembre,labeltags,labelimage,labelcategorie,labellien;
//    TextField tfNom;
//    TextField tfDescription;
//    TextField tfnbpart;
//    TextField tfmembre;
//    TextField tftags;
//    TextField tfimage;
//    TextField tfcategorie;
//    TextField tflien;
//    Button btnModifier;
//
//    public UpdateCoursForm(Form previous) {
//        super(new BoxLayout(BoxLayout.Y_AXIS));
//        addGUIs();
//        addActions();
//        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.show());
//
//    }
//
//    private void addGUIs() {
//
//        labelNom = new Label("Nom :");
//        labelNom.setUIID("defaultLabel");
//        tfNom = new TextField();
//
//        labelDescription = new Label("Description : ");
//        labelDescription.setUIID("defaultLabel");
//        tfDescription = new TextField();
//        
//        labelnbparticipant = new Label("Description : ");
//        labelnbparticipant.setUIID("defaultLabel");
//        tfnbpart = new TextField();
//        
//        labelmembre = new Label("Description : ");
//        labelmembre.setUIID("defaultLabel");
//        tfmembre = new TextField();
//        
//        labeltags = new Label("Description : ");
//        labeltags.setUIID("defaultLabel");
//        tftags = new TextField();
//        
//        labelimage = new Label("Description : ");
//        labelimage.setUIID("defaultLabel");
//        tfimage = new TextField();
//        
//        labelcategorie = new Label("Description : ");
//        labelcategorie.setUIID("defaultLabel");
//        tfcategorie = new TextField();
//        
//        labellien = new Label("Description : ");
//        labellien.setUIID("defaultLabel");
//        tflien = new TextField();
//
//        btnModifier = new Button("Modifer");
//
//        btnModifier.setUIID("buttonSuccess");
//
//        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//        container.setUIID("offreContainer");
//        container.addAll(labelNom, tfNom, labelDescription, tfDescription,labelnbparticipant,tfnbpart,labelmembre,tfmembre,labeltags,tftags,labelimage,tfimage,labelcategorie,tfcategorie,labellien,tflien, btnModifier);
//
//        this.addAll(container);
//
//    }
//
//    private void addActions() {
//
//        btnModifier.addActionListener((action) -> {
//            CoursServices.getInstance().modifierOffre(new Cours()
//                    AfficherToutOffreDeTravail.offreActuelle.getId(),
//                    1,//categorie
//                    MainApp.getSession().getSocieteId(),
//                    tfNom.getText(),
//                    tfDescription.getText()
//            ));
//
//        });
//    }
//}
