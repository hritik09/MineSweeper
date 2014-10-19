/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this minesInitializerlate file, choose Tools | Templates
 * and open the minesInitializerlate in the editor.
 */
package minesweeper.through.console;

import java.util.Random;

/**
 *
 * @author ankit.khandelwal
 */
public class MinesweeperBoard {
   // constants for cell refrence
   public static final int UNREVEALED = -1;
   public static final int MINE = -2;
   public static final int REVEALED = -3;
   
   // Minesweeper dimention
   private int row;
   private int totalMinesCount;
   private int unplayed;            // no of cells available for play
   private boolean[][] mines;       // mines location indicator
   private int[][] board;
   
   
   
   public MinesweeperBoard(int row) { 
     row = Math.abs(row);
     mines = new boolean[row][row];
     board = new int[row][row];
     
     this.row = row;
     this.totalMinesCount = (row * row)/6;
     this.unplayed = row * row;

     for (int i = 0; i < row; i++) {
       for (int j = 0; j < row; j++) {
         mines[i][j] = false;
         board[i][j] = UNREVEALED;
       }
     }
 
     int totalNoOfCells = row * row;
     int minesInitializer = 0;
     Random rand = new Random();
 
     while (minesInitializer < totalMinesCount) {
       int cell = rand.nextInt(totalNoOfCells);
       if (!mines[cell%row][cell/row]) {
         mines[cell%row][cell/row] = true;
         minesInitializer++;
       }
     }
   }
   
   //getter methods
   public int getBoradLenghth() {
     return row;
   }
 
   public int getMinesCount() {
     return totalMinesCount;
   }
 
   public int getUnplayedCellCount() {
     return unplayed;
   }
   
   public int expose(int x, int y) {
     if(board[x][y] == UNREVEALED) {
       unplayed--;
       if (mines[x][y]) {
         board[x][y] = MINE;
       }
       else {
         board[x][y] = surroundingMines(x, y); 
       }
     }else{
         return REVEALED;
     }
     return board[x][y];
   }
   
   public void exposeAll(){
       for (int i = 0; i < row; i++) {
       for (int j = 0; j < row; j++) {
           expose(i, j);
       }
     }
   }
   
   public void unfold(int x, int y) {
     int startingX, startingY, endingX, endingY;
 
     // putting condition for edges
     startingX = startEdgeConditon(x);
     startingY = startEdgeConditon(y);
     endingX = endEdgeConditon(x);
     endingY = endEdgeConditon(y);
 
 
     // check surrounding cells
     for (int i = startingX; i < endingX; i++) {
       for (int j = startingY; j < endingY; j++) {
         if (!mines[i][j] && board[i][j] == UNREVEALED) {
           expose(i, j);
           if (board[i][j] == 0) {
             unfold(i, j);
           }
         }
       }
     }
   }
   
   // get mines count in surrounding cells
   private int surroundingMines(int x, int y) {
     int startingX, startingY, endingX, endingY;
     int count = 0;
 
     // putting condition for edges
     startingX = startEdgeConditon(x);
     startingY = startEdgeConditon(y);
     endingX = endEdgeConditon(x);
     endingY = endEdgeConditon(y);
 
     // check surrounding cells
     for (int i = startingX; i < endingX; i++) {
       for (int j = startingY; j < endingY; j++) {
         if (mines[i][j]) {
           count++;
         }
       }
     }
     return count;
   }
   
   public int startEdgeConditon(int x){
       return (x <= 0 ? 0 : x - 1);
   }
   
   public int endEdgeConditon(int x){
       return (x >= row - 1 ? row : x + 2);
   }
   
   public void printBoard(){
     for (int i = 0; i < row; i++) {
        String str = i + "  ";  
        for (int j = 0; j < row; j++) {
          if(board[j][i] == UNREVEALED){
              str = str + " #";
          }else if(board[j][i] == 0){
              str = str + " *";
          }else{
              str = str + " " + Integer.toString(board[j][i]);
          }
        }
        System.out.println(str);
     }
     System.out.println("");
     System.out.println("Number of mines: " + totalMinesCount);
       
   }
   
}
