/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.gamingroom.entities;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Sonia
 */
public class Membre {

   
    public enum Role{
        Admin,
        Membre,
        Coach 
    }
    public enum Genre{
        Femme,
        Homme
    }
    private int id;
    private String nom;
    private String prenom;
    private Date date_naissance;
    private Genre genre;
    private String tel;
    private String email;
    private String password;
    private String image;
    private Role role;
    private int point ;
    private String description;
    private boolean active;
    private int ban_duration;
    private Timestamp last_timeban;

    public Membre() {
    }

    public Membre(int id) {
        this.id = id;
    }

    public Membre(int id, String nom, String prenom, Date date_naissance, Genre genre, String tel, String email, String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.genre = genre;
        this.tel = tel;
        this.email = email;
        this.image = image;
    }
    
    

    public Membre(int id, int point, boolean active, int ban_duration, Timestamp last_timeban) {
        this.id = id;
        this.point = point;
        this.active = active;
        this.ban_duration = ban_duration;
        this.last_timeban = last_timeban;
    }

   
    

    public Membre(int id, boolean active) {
        this.id = id;
        this.active = active;
    }
    

    public Membre(int id, int point) {
        this.id = id;
        this.point = point;
    }
    

    public Membre(int id, String nom, String prenom, Date date_naissance, Genre genre, String tel, String email, String password, String image, Role role, int point, String description, boolean active, int ban_duration, Timestamp last_timeban) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.genre = genre;
        this.tel = tel;
        this.email = email;
        this.password = password;
        this.image = image;
        this.role = role;
        this.point = point;
        this.description = description;
        this.active = active;
        this.ban_duration = ban_duration;
        this.last_timeban = last_timeban;
    }

    public Membre(String nom, String prenom, Date date_naissance, Genre genre, String tel, String email, String password, String image, Role role, int point, String description, boolean active, int ban_duration, Timestamp last_timeban) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.genre = genre;
        this.tel = tel;
        this.email = email;
        this.password = password;
        this.image = image;
        this.role = role;
        this.point = point;
        this.description = description;
        this.active = active;
        this.ban_duration = ban_duration;
        this.last_timeban = last_timeban;
    }

    public Membre(int id, String nom, String prenom, Date date_naissance, Genre genre, String tel, String email, String password, Role role, boolean active) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.genre = genre;
        this.tel = tel;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    public Membre(int id, String nom, String prenom, Date date_naissance, Genre genre, String tel, String email, String password, String image, Role role, boolean active) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.genre = genre;
        this.tel = tel;
        this.email = email;
        this.password = password;
        this.image = image;
        this.role = role;
        this.active = active;
    }

  
    

    public Membre(String nom, String prenom, Date date_naissance, Genre genre, String tel, String email, String password, String image, Role role, boolean active) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.genre = genre;
        this.tel = tel;
        this.email = email;
        this.password = password;
        this.image = image;
        this.role = role;
        this.active = active;
    }

    public Membre(int id, String nom, String prenom, Date date_naissance, Genre genre, String tel, String email, String password, String image, Role role, String description, boolean active) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.genre = genre;
        this.tel = tel;
        this.email = email;
        this.password = password;
        this.image = image;
        this.role = role;
        this.description = description;
        this.active = active;
    }

    public Membre(int id, String nom, String prenom, Date date_naissance, Genre genre, String tel, String email, String password, Role role, String description, boolean active) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.genre = genre;
        this.tel = tel;
        this.email = email;
        this.password = password;
        this.role = role;
        this.description = description;
        this.active = active;
    }
    
    
    public Membre(String nom, String prenom, Date date_naissance, Genre genre, String tel, String email, String password, String image, Role role, String description, boolean active) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.genre = genre;
        this.tel = tel;
        this.email = email;
        this.password = password;
        this.image = image;
        this.role = role;
        this.description = description;
        this.active = active;
    }
    
    

   

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getImage() {
        return image;
    }

    public Role getRole() {
        return role;
    }

    public int getPoint() {
        return point;
    }


    public String getDescription() {
        return description;
    }

    public boolean getActive() {
        return active;
    }

    public int getBan_duration() {
        return ban_duration;
    }

    public Timestamp getLast_timeban() {
        return last_timeban;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPoint(int point) {
        this.point = point;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setBan_duration(int ban_duration) {
        this.ban_duration = ban_duration;
    }

    public void setLast_timeban(Timestamp last_timeban) {
        this.last_timeban = last_timeban;
    }

    @Override
    public String toString() {
        return "Membre{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", date_naissance=" + date_naissance + ", genre=" + genre + ", tel=" + tel + ", email=" + email + ", password=" + password + ", image=" + image + ", role=" + role + ", point=" + point +  ", description=" + description + ", active=" + active + ", ban_duration=" + ban_duration + ", last_timeban=" + last_timeban + '}'+"\n";
    }
    
    
    
    
    
}
