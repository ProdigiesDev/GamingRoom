/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.entities;

/**
 *
 * @author Sonia
 */
public class CategorieMembre {
    
    private int id;
    private int membre_id;
    private int categorie_id;

    public CategorieMembre() {
    }

    public CategorieMembre(int id, int membre_id, int categorie_id) {
        this.id = id;
        this.membre_id = membre_id;
        this.categorie_id = categorie_id;
    }

    public CategorieMembre(int membre_id, int categorie_id) {
        this.membre_id = membre_id;
        this.categorie_id = categorie_id;
    }

    public int getId() {
        return id;
    }

    public int getMembre_id() {
        return membre_id;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMembre_id(int membre_id) {
        this.membre_id = membre_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    @Override
    public String toString() {
        return "CategorieMembre{" + "id=" + id + ", membre_id=" + membre_id + ", categorie_id=" + categorie_id + '}'+"\n";
    }
    
    
    
    
}
