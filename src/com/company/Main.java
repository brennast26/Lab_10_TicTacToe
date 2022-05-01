package com.company;
import com.sun.rowset.internal.Row;

import java.sql.SQLOutput;
import java.util.Scanner;
public class Main
{

    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board[][] = new String[ROW][COL];

    private static void clearBoard() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                board[row][col] = "-";
            }
        }
    }

    private static void showBoard() {
        System.out.println("-------------");
        for (int row = 0; row < 3; row++) {
            System.out.print("| ");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static boolean isValidMove(int row, int col) {
        boolean retVal = false;
        if (board[row][col].equals("-"))
            retVal = true;
        return retVal;
    }

    private static boolean isWin(String player) {
        if (isColWin(player) || isRowWin(player) || isDiagonalWin(player)) {
            return true;
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player))
                {
                    return true;
                }
                if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isTie() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (board[row][col] == "-") {
                    return false;
                }
            }
        }
        return true;

    }


    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        boolean Cont = true;

        do
        {
            String currentPlayer = "X";
            int RowMark = 0;
            int ColMark = 0;
            int row = 0;
            int col = 0;
            clearBoard();
            int moveCount = 0;
            do
            {
                do
                {
                    showBoard();
                    System.out.println("\nThe current player is " + currentPlayer);
                    RowMark = safeInput.getRangedInt(in, "What row would you like to mark.", 1, 3);
                    ColMark = safeInput.getRangedInt(in, "What column would you like to mark? ", 1, 3);
                    row = RowMark - 1;
                    col = ColMark - 1;
                    isValidMove(row, col);
                }
                while (!isValidMove(row, col));
                board[row][col] = currentPlayer;
                moveCount = moveCount + 1;
                System.out.println(moveCount);
                if(moveCount >= 5)
                {
                    isWin(currentPlayer);
                    if(isWin(currentPlayer))
                    {
                        System.out.println("Player " +currentPlayer+ " wins!");
                        showBoard();
                        break;
                    }
                }
                if(moveCount >= 7)
                {
                    isTie();
                    if(isTie())
                    {
                        System.out.println("There is a tie!");
                        showBoard();
                        break;
                    }
                }
                if (currentPlayer == "X") {
                        currentPlayer = "O";
                    } else {
                        currentPlayer = "X";
                    }
            }
            while (!isWin(currentPlayer) || isTie()) ;
            //Game Exit
            Cont = safeInput.getYNConfirm(in, "Would you like to continue? ");
        }
        while(Cont != false);
    }
}

