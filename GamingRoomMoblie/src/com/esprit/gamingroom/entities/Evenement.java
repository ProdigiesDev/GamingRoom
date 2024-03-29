/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.entities;

import java.util.Date;


/**
 *
 * @author Farah
 */
public class Evenement {
    private int idevent;
    private String nomEvent;
    private Date dateDeb;
    private Date dateFin;
    private String image;
    private Categorie categorie_id;
    private int nbreMax_participant;
    private String description;
    private String lieu;
    private String lienYoutube;

    public Evenement() {
    }

    public Evenement(int idevent, String nomEvent, Date dateDeb, Date dateFin, String image, Categorie categorie_id, int nbreMax_participant, String description, String lieu, String lienYoutube) {
        this.idevent = idevent;
        this.nomEvent = nomEvent;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.image = image;
        this.categorie_id = categorie_id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Categorie getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(Categorie categorie_id) {
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

    @Override
    public String toString() {
        return "Evenement{" + "idevent=" + idevent + ", nomEvent=" + nomEvent + ", dateDeb=" + dateDeb + ", dateFin=" + dateFin + ", image=" + image + ", categorie_id=" + categorie_id + ", nbreMax_participant=" + nbreMax_participant + ", description=" + description + ", lieu=" + lieu + ", lienYoutube=" + lienYoutube + '}';
    }
    
    
    
}
