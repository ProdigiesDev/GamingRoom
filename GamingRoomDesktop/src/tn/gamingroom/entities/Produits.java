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
    private String image;
    private String libelle;
    private int prix;
    private String description;
    private int quantite;

    public Produits() {
    }

    public Produits(int idprod, String image, String libelle, int prix, String description, int quantite) {
        this.idprod = idprod;
        this.image = image;
        this.libelle = libelle;
        this.prix = prix;
        this.description = description;
        this.quantite = quantite;
    }

 

    public Produits(String image, String libelle, int prix, String description, int quantite) {
        this.image = image;
        this.libelle = libelle;
        this.prix = prix;
        this.description = description;
        this.quantite = quantite;
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

    public int getPrix() {
        return prix;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantite() {
        return quantite;
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

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Produits{" + "idprod=" + idprod + ", image=" + image + ", libelle=" + libelle + ", prix=" + prix + ", description=" + description + ", quantite=" + quantite + '}';
    }

    
    
}
