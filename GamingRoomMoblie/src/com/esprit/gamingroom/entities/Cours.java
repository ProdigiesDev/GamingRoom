/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.entities;

import java.util.Date;




/**
 *
 * @author eyatr
 */
public class Cours {
     private int id;
    private String nomCours;
    private String description;
    private int nb_participants;
    private int membre_id;
    private String date_creation;
    private String tags;
    private String image;
    private int categorie_id;
    private String lienYoutube;

    public Cours(int id, String nomCours, String description, int nb_participants, int membre_id, String date_creation, String tags, String image, int categorie_id, String lienYoutube) {
        this.id = id;
        this.nomCours = nomCours;
        this.description = description;
        this.nb_participants = nb_participants;
        this.membre_id = membre_id;
        this.date_creation = date_creation;
        this.tags = tags;
        this.image = image;
        this.categorie_id = categorie_id;
        this.lienYoutube = lienYoutube;
    }
    public Cours( String nomCours, String description, int nb_participants,  String tags, String image,  String lienYoutube) {
        //this.id = id;
        this.nomCours = nomCours;
        this.description = description;
        this.nb_participants = nb_participants;
        //this.membre_id = membre_id;
        //this.date_creation = date_creation;
        this.tags = tags;
        this.image = image;
        //this.categorie_id = categorie_id;
        this.lienYoutube = lienYoutube;
    }
    
    
    
    public Cours(int id, String nomCours, String description, int nb_participants, int membre_id, String tags, int categorie_id) {
        this.id = id;
        this.nomCours = nomCours;
        this.description = description;
        this.nb_participants = nb_participants;
        this.membre_id = membre_id;
        this.tags = tags;
        this.categorie_id = categorie_id;
    }

    public Cours(String nomCours, String description, int nb_participants,  String date_creation, String tags, String image, int categorie_id) {
        this.nomCours = nomCours;
        this.description = description;
        this.nb_participants = nb_participants;
        //this.membre_id = membre_id;
        this.date_creation = date_creation;
        this.tags = tags;
        this.image = image;
        this.categorie_id = categorie_id;
    }

    public Cours(String nomCours, String description, int nb_participants, int membre_id, String date_creation, String tags, String image, int categorie_id, String lienYoutube) {
        this.nomCours = nomCours;
        this.description = description;
        this.nb_participants = nb_participants;
        this.membre_id = membre_id;//
        this.date_creation = date_creation;
        this.tags = tags;
        this.image = image;
        this.categorie_id = categorie_id;
        this.lienYoutube = lienYoutube;
    }
    public Cours(String nomCours, String description, int nb_participants, int membre_id,  String tags, String image, int categorie_id, String lienYoutube) {
        this.nomCours = nomCours;
        this.description = description;
        this.nb_participants = nb_participants;
        this.membre_id = membre_id;//
       
        this.tags = tags;
        this.image = image;
        this.categorie_id = categorie_id;
        this.lienYoutube = lienYoutube;
    }
    public Cours(String nomCours, String description, int nb_participants, String date_creation, String tags, String image, int categorie_id, String lienYoutube) {
        this.nomCours = nomCours;
        this.description = description;
        this.nb_participants = nb_participants;
        this.membre_id = membre_id;//
        this.date_creation = date_creation;
        this.tags = tags;
        this.image = image;
        this.categorie_id = categorie_id;
        this.lienYoutube = lienYoutube;
    }
    

    public Cours(int id, String nomCours, String description, int nb_participants, int membre_id, String date_creation, String tags, String image, int categorie_id) {
        this.id = id;
        this.nomCours = nomCours;
        this.description = description;
        this.nb_participants = nb_participants;
        //this.membre_id = membre_id;
        this.date_creation = date_creation;
        this.tags = tags;
        this.image = image;
        this.categorie_id = categorie_id;
    }

    public Cours(int id, String nomCours, String description, int nb_participants, String tags, int categorie_id) {
        this.id = id;
        this.nomCours = nomCours;
        this.description = description;
        this.nb_participants = nb_participants;
        this.tags = tags;
        this.categorie_id = categorie_id;
    }

    public Cours(int id, String nomCours, String description, int nb_participants, String date_creation, String tags, int categorie_id) {
        this.id = id;
        this.nomCours = nomCours;
        this.description = description;
        this.nb_participants = nb_participants;
        this.date_creation = date_creation;
        this.tags = tags;
        this.image=image;
        this.categorie_id = categorie_id;
    }

    public Cours(int id, String nomCours, String description, int nb_participants, int membre_id, String date_creation, String tags, int categorie_id) {
        this.id = id;
        this.nomCours = nomCours;
        this.description = description;
        this.nb_participants = nb_participants;
        this.membre_id = membre_id;
        this.date_creation = date_creation;
        this.tags = tags;
        this.categorie_id = categorie_id;
    }

    public Cours(String nomCours, String description, int nb_participants, int membre_id, String date_creation, String tags, int categorie_id) {
        this.nomCours = nomCours;
        this.description = description;
        this.nb_participants = nb_participants;
        this.membre_id = membre_id;
        this.date_creation = date_creation;
        this.tags = tags;
        this.categorie_id = categorie_id;
    }
    
    

    public int getId() {
        return id;
    }

    public Cours() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNb_participants() {
        return nb_participants;
    }

    public void setNb_participants(int nb_participants) {
        this.nb_participants = nb_participants;
    }

    public int getMembre_id() {
        return membre_id;
    }

    public void setMembre_id(int membre_id) {
        this.membre_id = membre_id;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLienYoutube() {
        return lienYoutube;
    }

    public void setLienYoutube(String lienYoutube) {
        this.lienYoutube = lienYoutube;
    }

    @Override
    public String toString() {
        return "Cours{" + "id=" + id + ", nomCours=" + nomCours + ", description=" + description + ", nb_participants=" + nb_participants + ", membre_id=" + membre_id + ", date_creation=" + date_creation + ", tags=" + tags + ", image=" + image + ", categorie_id=" + categorie_id + ", lienYoutube=" + lienYoutube + '}';
    }
    
    
    
    
}
