/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.through.console;

/**
 *
 * @author Hritik.Bhardwaj
 */
import java.util.Scanner;

public class MinesweeperThroughConsole
{
    public static final int MINE = -2;
    public static final int REVEALED = -3;

    public static int rowSelected;               // row picked to play          
    public static int columnSelected;               // column picked to play

      public static void main(String[] args) 
      {
          
         Scanner sc = new Scanner(System.in);    // keyboard input
         System.out.print("Input N for N x N size minesweeper you wish to play :");
         int row = sc.nextInt();
         MinesweeperBoard playBoard = new MinesweeperBoard(row);
         int totalCellCount = row*row;
         while( true ) // play loop starts
         {
            System.out.println("Which row and column to play? ( enter positive row and column less than minesweeper dimention N or enter negetive number to quit) ");
            System.out.println("enter positive row and column seperate by space");
            rowSelected = sc.nextInt();
            columnSelected = sc.nextInt();
            if ((columnSelected < 0) || (rowSelected < 0) || (columnSelected > row - 1) || (rowSelected > row - 1) )
            {
               System.out.println("Thank you for playing!!.....exiting now");
               System.exit(0);
            }
            else 
            {
               int cellValue = playBoard.expose(columnSelected, rowSelected);
               if (cellValue == MINE){
                  System.out.println("Boom..!! game over");
                  playBoard.exposeAll();
                  playBoard.printBoard();
                  System.out.println("Exiting game");
                  System.exit(0);
               }else if(cellValue == REVEALED){
                  System.out.println("This move has been made");
                  System.out.println("");
               }else if(cellValue == 0){
                  playBoard.unfold(columnSelected, rowSelected);
                  playBoard.printBoard();
               }else{
                  playBoard.printBoard();
               }
               
               int uplayedCount = playBoard.getUnplayedCellCount();
               if(uplayedCount == playBoard.getMinesCount()){
                     System.out.println("Congratulation you won!!");
                     System.out.println("Good game");
               }
            }   
            
      }     
    }  
} // end class

