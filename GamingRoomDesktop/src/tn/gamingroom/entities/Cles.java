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
public class Cles {
    
    private int idcle;
    private String code;
    private int produit_id;

    public Cles() {
    }

    public Cles(int idcle, String code, int produit_id) {
        this.idcle = idcle;
        this.code = code;
        this.produit_id = produit_id;
    }
 
    public int getIdcle() {
        return idcle;
    }

    public String getCode() {
        return code;
    }

    public void setIdcle(int idcle) {
        this.idcle = idcle;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    public int getProduit_id() {
        return produit_id;
    }
    
      @Override
    public String toString() {
        return "Cles{" + "idcle=" + idcle + ", code=" + code + ", produit_id=" + produit_id + '}';
    }
    
}
