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
public class Avis {
    
    private int id;
    private String avis;
    private int member_id;
    private String sentiment;
    
    public Avis() {
    }

    public Avis(int id, String avis, int member_id, String sentiment) {
        this.id = id;
        this.avis = avis;
        this.member_id = member_id;
        this.sentiment = sentiment;
    }

    public Avis(String avis, int member_id, String sentiment) {
        this.avis = avis;
        this.member_id = member_id;
        this.sentiment = sentiment;
    }

    public Avis(String avis, int member_id) {
        this.avis = avis;
        this.member_id = member_id;
    }

    
    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }
    
    
    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", avis=" + avis + ", member_id=" + member_id + '}';
    }
    
}
