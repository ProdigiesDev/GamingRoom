/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.entities;

/**
 *
 * @author yasmine
 */
public class Produit {

    private int idprod;
    private String image;
    private String libelle;
    private float prix;
            private String description;
              private int idCat;

    public Produit() {
    }

    public Produit(int idprod, String image, String libelle, float prix, String description, int idCat) {
        this.idprod = idprod;
        this.image = image;
        this.libelle = libelle;
        this.prix = prix;
        this.description = description;
        this.idCat = idCat;
    }

    public Produit(String image, String libelle, float prix, String description, int idCat) {
        this.image = image;
        this.libelle = libelle;
        this.prix = prix;
        this.description = description;
        this.idCat = idCat;
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

    public float getPrix() {
        return prix;
    }

    public String getDescription() {
        return description;
    }

    public int getIdCat() {
        return idCat;
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

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }
            
}
