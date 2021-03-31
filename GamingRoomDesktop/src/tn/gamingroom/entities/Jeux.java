/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.entities;

/**
 *
 * @author Dah
 */
public class Jeux {
    
    private int id;
    private String nom;
    private String descriString;
    private Type type_plateforme;
    private String image;
    public Jeux() {
    }

    public Jeux(String nom, String descriString, Type type_plateforme, String image) {
        this.nom = nom;
        this.descriString = descriString;
        this.type_plateforme = type_plateforme;
        this.image = image;
    }

    public Jeux(int id, String nom, String descriString, Type type_plateforme, String image) {
        this.id = id;
        this.nom = nom;
        this.descriString = descriString;
        this.type_plateforme = type_plateforme;
        this.image = image;
    }
    
    public String getDescriString() {
        return descriString;
    }

    public void setDescriString(String descriString) {
        this.descriString = descriString;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    

    public int getId() {
        return id;
    }

    public Type getType_plateforme() {
        return type_plateforme;
    }

    public void setType_plateforme(Type type_plateforme) {
        this.type_plateforme = type_plateforme;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Jeux{" + "id=" + id + ", nom=" + nom + ", descriString=" + descriString + ", type_plateforme=" + type_plateforme + '}';
    }

    
    public enum Type{
        Desktop,
        Mobile,
        Web
    }
    
}
