/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.entities;

/**
 *
 * @author Asus
 */
public class Participant {
    
    public int id;
    public int evenement_id;
    public int member_id;
    public String duel;
    public int round;

    public Participant(int evenement_id, int member_id, String duel) {
        this.evenement_id = evenement_id;
        this.member_id = member_id;
        this.duel = duel;
    }

    public Participant(int evenement_id, int member_id, String duel, int round) {
        this.evenement_id = evenement_id;
        this.member_id = member_id;
        this.duel = duel;
        this.round = round;
    }

    public Participant(int id, int evenement_id, int member_id, String duel, int round) {
        this.id = id;
        this.evenement_id = evenement_id;
        this.member_id = member_id;
        this.duel = duel;
        this.round = round;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvenement_id() {
        return evenement_id;
    }

    public void setEvenement_id(int evenement_id) {
        this.evenement_id = evenement_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getDuel() {
        return duel;
    }

    public void setDuel(String duel) {
        this.duel = duel;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    @Override
    public String toString() {
        return "participant{" + "id=" + id + ", evenement_id=" + evenement_id + ", member_id=" + member_id + ", duel=" + duel + ", round=" + round + '}';
    }
    
    
    
}
