/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author yasmine
 */
public class ImageProduits {
    
    private int idprod;
     private int id_cat;
       private ImageView image;
    private String libelle;
    private double prix;
    private String description;

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }
 private String imagename;
    private String nomCat;

    public ImageProduits(int idprod, int id_cat, ImageView image, String libelle, double prix, String description, String nomCat) {
        this.idprod = idprod;
        this.id_cat = id_cat;
        this.image = image;
        this.libelle = libelle;
        this.prix = prix;
        this.description = description;
        this.nomCat = nomCat;
    }

    public String getImagename() {
        return imagename;
    }

    public ImageProduits() {
    }

    public int getIdprod() {
        return idprod;
    }

    public int getId_cat() {
        return id_cat;
    }

    @Override
    public String toString() {
        return "ImageProduits{" + "idprod=" + idprod + ", id_cat=" + id_cat + ", image=" + image + ", libelle=" + libelle + ", prix=" + prix + ", description=" + description + ", nomCat=" + nomCat + '}';
    }

    public void setIdprod(int idprod) {
        this.idprod = idprod;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    public ImageView getImage() {
        return image;
    }

    public String getLibelle() {
        return libelle;
    }

    public double getPrix() {
        return prix;
    }

    public String getDescription() {
        return description;
    }

    public String getNomCat() {
        return nomCat;
    }

   

   
}
