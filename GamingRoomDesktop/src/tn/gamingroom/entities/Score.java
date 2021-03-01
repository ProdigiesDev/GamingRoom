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
public class Score {
    
    private int id;
    private int score;
    private int jeux_id;
    private int memebre_id;

    public Score(int id, int score, int jeux_id, int memebre_id) {
        this.id = id;
        this.score = score;
        this.jeux_id = jeux_id;
        this.memebre_id = memebre_id;
    }

    public Score(int score, int jeux_id, int memebre_id) {
        this.score = score;
        this.jeux_id = jeux_id;
        this.memebre_id = memebre_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getJeux_id() {
        return jeux_id;
    }

    public void setJeux_id(int jeux_id) {
        this.jeux_id = jeux_id;
    }

    public int getMemebre_id() {
        return memebre_id;
    }

    public void setMemebre_id(int memebre_id) {
        this.memebre_id = memebre_id;
    }

    @Override
    public String toString() {
        return "Score{" + "id=" + id + ", score=" + score + ", jeux_id=" + jeux_id + ", memebre_id=" + memebre_id + '}';
    }
    
    
    
}
