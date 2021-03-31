/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.entities;

import java.sql.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author eyatr
 */
public class Courslm {

    private int id;
    private String nomCours;
    private String description;
    private int nb_participants;
    private int membre_id;
    private Date date_creation;
    private String tags;
    private ImageView image;
    private int categorie_id;
    private String categorieNom;
    private String imagename;
    private String lienYoutube;
    public Courslm() {
    }

    public Courslm(int id, String nomCours, String description, int nb_participants, int membre_id, Date date_creation, String tags, ImageView image, int categorie_id, String categorieNom, String imagename, String lienYoutube) {
        this.id = id;
        this.nomCours = nomCours;
        this.description = description;
        this.nb_participants = nb_participants;
        this.membre_id = membre_id;
        this.date_creation = date_creation;
        this.tags = tags;
        this.image = image;
        this.categorie_id = categorie_id;
        this.categorieNom = categorieNom;
        this.imagename = imagename;
        this.lienYoutube = lienYoutube;
    }
    
    

    public Courslm(int id, String nomCours, String description, int nb_participants, int membre_id, Date date_creation, String tags, ImageView image, int categorie_id, String categorieNom) {
        this.id = id;
        this.nomCours = nomCours;
        this.description = description;
        this.nb_participants = nb_participants;
        this.membre_id = membre_id;
        this.date_creation = date_creation;
        this.tags = tags;
        this.image = image;
        this.categorie_id = categorie_id;
        this.categorieNom = categorieNom;
    }

    public Courslm(int id, String nomCours, String description, int nb_participants, int membre_id, Date date_creation, String tags, ImageView image, int categorie_id) {
        this.id = id;
        this.nomCours = nomCours;
        this.description = description;
        this.nb_participants = nb_participants;
        this.membre_id = membre_id;
        this.date_creation = date_creation;
        this.tags = tags;
        this.image = image;

        this.categorie_id = categorie_id;
    }

    public Courslm(int id, String nomCours, String description, int nb_participants, int membre_id, Date date_creation, String tags, String image) {
        this.id = id;
        this.nomCours = nomCours;
        this.description = description;
        this.nb_participants = nb_participants;
        this.membre_id = membre_id;
        this.date_creation = date_creation;
        this.tags = tags;
        this.categorieNom = categorieNom;
        this.categorie_id = categorie_id;
    }

    public Courslm(int id, String nomCours, String description, int nb_participants, int membre_id, Date date_creation, String tags, ImageView i, int categorie_id, String nomcat, String lienYoutube) {
        this.id = id;
        this.nomCours = nomCours;
        this.description = description;
        this.nb_participants = nb_participants;
        this.membre_id = membre_id;
        this.date_creation = date_creation;
        this.tags = tags;
        this.lienYoutube = lienYoutube;
        this.image = i;
        
        this.categorie_id = categorie_id;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    public int getId() {
        return id;
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

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getCategorieNom() {
        return categorieNom;
    }

    public void setCategorieNom(String categorieNom) {
        this.categorieNom = categorieNom;
    }

    public String getLienYoutube() {
        return lienYoutube;
    }

    public void setLienYoutube(String lienYoutube) {
        this.lienYoutube = lienYoutube;
    }
    
    

    @Override
    public String toString() {
        return "Courslm{" + "id=" + id + ", nomCours=" + nomCours + ", description=" + description + ", nb_participants=" + nb_participants + ", membre_id=" + membre_id + ", date_creation=" + date_creation + ", tags=" + tags + ", image=" + image + ", categorie_id=" + categorie_id + ", categorieNom=" + categorieNom + ", imagename=" + imagename + ", lienYoutube=" + lienYoutube + '}';
    }
    
    

}
