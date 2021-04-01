/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Jeux.Tictactoe;

import javafx.scene.control.Label;

/**
 *
 * @author FER-SID_PC
 */
public class Score {
    private Label scoreValue;
    private int value=0;

    public Score(Label scoreValue) {
        this.scoreValue = scoreValue;
    }
    
    public void loadScore(){
        value=0;
        displayScore();
    }

    public int getValue() {
        return value;
    }

    public void addValue(int val) {
        this.value =this.value+val;
        displayScore();
    }
    
    public void displayScore(){
        scoreValue.setText(value+"");
    }
}
