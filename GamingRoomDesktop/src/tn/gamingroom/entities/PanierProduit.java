/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.entities;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class PanierProduit {
    private Integer id;
    private Integer idpanier;
    private  String nom;
    private  Integer quantite;
    private  TextField tQuantite;
    private  ImageView image;
    private  Double prix;
    private  Double prixUnitaire;
    
    public PanierProduit(Integer id,Integer idpanier,String nom, Integer quantite, TextField tQuantite, ImageView image, Double prix, Double prixUnitaire) {
        this.nom = nom;
        this.id = id;
        this.idpanier = idpanier;
        this.quantite = quantite;
        this.tQuantite = tQuantite;
        this.image = image;
        this.prix = prix;
        this.prixUnitaire = prixUnitaire;
    }

    public PanierProduit(Integer id,Integer idpanier,String nom, Integer quantite, ImageView image, Double prix, Double prixUnitaire) {
        this.nom = nom;
        this.id = id;
        this.idpanier = idpanier;
        this.quantite = quantite;
        this.image = image;
        this.prix = prix;
        this.prixUnitaire = prixUnitaire;
    }

    public Integer getIdpanier() {
        return idpanier;
    }

    public void setIdpanier(Integer idpanier) {
        this.idpanier = idpanier;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public TextField gettQuantite() {
        return tQuantite;
    }

    public void settQuantite(TextField tQuantite) {
        this.tQuantite = tQuantite;
    }


    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    @Override
    public String toString() {
        return "PanierProduit{" + "nom=" + nom + ", quantite=" + quantite + ", tQuantite=" + tQuantite + ", image=" + image + ", prix=" + prix + ", prixUnitaire=" + prixUnitaire + '}';
    }

    

   

    

    
    
    
    
    
}
