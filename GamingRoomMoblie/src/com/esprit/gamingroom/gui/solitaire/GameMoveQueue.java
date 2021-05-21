/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui.solitaire;
import java.util.ArrayList;

/**
 *
 * @author Dah
 */
public class GameMoveQueue {
    private static ArrayList<GameMove> moves = new ArrayList<>();
    private static int operationOffset = 0;
    
    public static void perform(GameMove move) {
        while(operationOffset < moves.size()) {
            moves.remove(operationOffset);
        }
        moves.add(move);
        move.doMove();
        operationOffset = moves.size();
    }
    
    public static void undo() {
        if(operationOffset > 0) {
            operationOffset--;
            GameMove m = moves.get(operationOffset);
            m.undoMove();
            if(m.isHidden()) {
                undo();
            }
        }
    }
    
    public static void redo() {
        if(operationOffset < moves.size()) {
            GameMove m = moves.get(operationOffset);
            m.doMove();
            operationOffset++;
            if(m.isHidden()) {
                redo();
            }
        }
    }
    
    public static void reset() {
        moves.clear();
        operationOffset = 0;
    }
}