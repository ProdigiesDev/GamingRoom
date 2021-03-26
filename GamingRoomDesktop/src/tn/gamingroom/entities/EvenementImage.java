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
 * @author Asus
 */
public class EvenementImage {

    private int idevent;
    private String nomEvent;
    private Date dateDeb;
    private Date dateFin;
    private ImageView image;
    private String imageUrl;
    private int categorie_id;
    private String categorieNom;
    private int nbreMax_participant;
    private String description;
    private String lieu;
    private String lienYoutube;

    public EvenementImage(int idevent, String nomEvent, Date dateDeb, Date dateFin, ImageView image, String imageUrl,int categorie_id, String categorieNom,int nbreMax_participant, String description, String lieu, String lienYoutube) {
        this.idevent = idevent;
        this.nomEvent = nomEvent;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.image = image;
        this.imageUrl = imageUrl;
        this.categorie_id = categorie_id;
        this.categorieNom = categorieNom;
        this.nbreMax_participant = nbreMax_participant;
        this.description = description;
        this.lieu = lieu;
        this.lienYoutube = lienYoutube;
    }

    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    public String getNomEvent() {
        return nomEvent;
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }

    public Date getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(Date dateDeb) {
        this.dateDeb = dateDeb;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public int getNbreMax_participant() {
        return nbreMax_participant;
    }

    public void setNbreMax_participant(int nbreMax_participant) {
        this.nbreMax_participant = nbreMax_participant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getLienYoutube() {
        return lienYoutube;
    }

    public void setLienYoutube(String lienYoutube) {
        this.lienYoutube = lienYoutube;
    }

    public String getCategorieNom() {
        return categorieNom;
    }

    public void setCategorieNom(String categorieNom) {
        this.categorieNom = categorieNom;
    }

    @Override
    public String toString() {
        return "EvenementImage{" + "idevent=" + idevent + ", nomEvent=" + nomEvent + ", dateDeb=" + dateDeb + ", dateFin=" + dateFin + ", image=" + image + ", imageUrl=" + imageUrl + ", categorie_id=" + categorie_id + ", categorieNom=" + categorieNom + ", nbreMax_participant=" + nbreMax_participant + ", description=" + description + ", lieu=" + lieu + ", lienYoutube=" + lienYoutube + '}';
    }
    
   
    

}
