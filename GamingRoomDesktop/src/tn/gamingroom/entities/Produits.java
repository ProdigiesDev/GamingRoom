/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.entities;

/**
 *
 * @author yasmine
 */
public class Produits {

 

    private int idprod;
     private int id_cat;
       private String image;
    private String libelle;
    private double prix;
    private String description;
 
    private String nomCat;

    public Produits() {
    }


    public Produits(String image, String libelle, double prix, String description) {
        this.image = image;
        this.libelle = libelle;
        this.prix = prix;
        this.description = description;
    }

    public Produits(int id_cat, String image, String libelle, double prix, String description) {
        this.id_cat = id_cat;
        this.image = image;
        this.libelle = libelle;
        this.prix = prix;
        this.description = description;
        this.nomCat = nomCat;
    }
    public Produits(int idprod, String image, String libelle, double prix, String description,String nomCat) {
        this.id_cat = id_cat;
        this.image = image;
        this.libelle = libelle;
        this.prix = prix;
        this.description = description;
        this.nomCat = nomCat;
    }

    public Produits(int idprod, int id_cat, String image, String libelle, double prix, String description, String nomCat) {
        this.idprod = idprod;
        this.id_cat = id_cat;
        this.image = image;
        this.libelle = libelle;
        this.prix = prix;
        this.description = description;
        this.nomCat = nomCat;
    }
 

    
   
    

    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }
 

   

    public int getIdprod() {
        return idprod;
    }

    public String getImage() {
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

   

    public void setIdprod(int idprod) {
        this.idprod = idprod;
    }

    public void setImage(String image) {
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

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    @Override
    public String toString() {
        return "Produits{" + "idprod=" + idprod + ", image=" + image + ", libelle=" + libelle + ", prix=" + prix + ", description=" + description + '}';
    }

   

 

    
    
}
