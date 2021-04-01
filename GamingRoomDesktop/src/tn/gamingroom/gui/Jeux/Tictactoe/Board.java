/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Jeux.Tictactoe;

import animatefx.util.ParallelAnimationFX;
import java.util.Random;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author FER-SID_PC
 */
public class Board {
    private static int size=3;
    private Square[][] board;
    private int lineWinner;
    
    public static int size() {
        return size;
    }

    public Board(Square[][] board) {
        this.board = board;
    }
    
    public void loadBoard() {
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                board[i][j].loadSquare();
            }
        }
    }
    
    public boolean makeValue(int i,int j){
        if(board[i][j].getValue()==Square.Value.EMPTY){
            board[i][j].setValue(Square.Value.X);
            return true;
        }
        return false;
    }
    
    public void generateTree(DefaultMutableTreeNode node){
        if(node!=null){
            Square[][] mat=(Square[][]) node.getUserObject();
            
        }
    }
    
    public void putRandomValue(){
        int[][] mat=new int[size*size][2];
        int k=0;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j].getValue()==Square.Value.EMPTY){
                    mat[k][0]=i;mat[k][1]=j;
                    k++;
                }   
            }
        }
        if(k>0){
            Random r = new Random();
            int m=(int)(k*r.nextDouble())%k;
            board[mat[m][0]][mat[m][1]].setValue(Square.Value.O);
        }
    }
    
    public boolean isWinner(Square.Value val){
        boolean youWin=true;
        for(int i=0;i<size;i++){
            youWin=true;
            for(int j=0;j<size;j++){
                if(board[i][j].getValue()!=val){
                    youWin=false;
                    break;
                }
            }
            if(youWin){
                lineWinner=i;
                return true;
            }
        }
        for(int j=0;j<size;j++){
            youWin=true;
            for(int i=0;i<size;i++){
                if(board[i][j].getValue()!=val){
                    youWin=false;
                    break;
                }
            }
            if(youWin){
                lineWinner=size+j;
                return true;
            }
        }
        youWin=true;
        for(int i=0;i<size;i++){
            if(board[i][i].getValue()!=val){
                youWin=false;
                break;
            }
        }
        if(youWin){
            lineWinner=size+size;
            return true;
        }
        youWin=true;
        for(int i=0;i<size;i++){
            if(board[size-1-i][i].getValue()!=val){
                youWin=false;
                break;
            }
        }
        if(youWin){
            lineWinner=size+size+1;
            return true;
        }
        return false;
    }
    
    public boolean isPlaine(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j].getValue()==Square.Value.EMPTY)
                    return false;
            }
        }
        return true;
    }
    
    public void makeAnimationWinner(){
        if(lineWinner<size){
            for(int j=0;j<size;j++)
                board[lineWinner][j].setIsWinnerSquare(true);
        }else if(lineWinner<2*size){
            for(int i=0;i<size;i++)
                board[i][lineWinner-size].setIsWinnerSquare(true);
        }else if(lineWinner==2*size){
            for(int i=0;i<size;i++)
                board[i][i].setIsWinnerSquare(true);
        }else if(lineWinner==2*size+1){
            for(int i=0;i<size;i++)
                board[size-1-i][i].setIsWinnerSquare(true);
        }
    }
}
