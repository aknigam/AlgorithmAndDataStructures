package com.learning.design.game.tictactoe;

import com.learning.design.game.Board;
import com.learning.design.game.Piece;

import java.util.HashMap;
import java.util.Map;


public class TicTacToeBoard implements Board<TicTacToeBoardPosition>{


    private TicTacToeBoardPosition[][] grid = new TicTacToeBoardPosition[3][3];

    public void init(){
        initAllPositions();
    }

    private void initAllPositions() {

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {
                TicTacToeBoardPosition position = new TicTacToeBoardPosition(i, j);
                grid[i][j] = position;
                piecePositions.put(position.getPiece(), position);
                positionPieces.put(position, position.getPiece());
            }
        }
    }

    public void display(){


        for (int i = 0; i < 3; i++) {
            System.out.println("-------------------------");
            StringBuffer row = new StringBuffer("|");
            for (int j = 0; j < 3; j++) {

                row.append(grid[i][j].getPiece()).append(" | ");
            }
            System.out.println(row);
            
        }
        System.out.println("-------------------------");
    }


    private Map<Piece, TicTacToeBoardPosition> piecePositions = new HashMap<Piece, TicTacToeBoardPosition>();

    private Map<TicTacToeBoardPosition, Piece> positionPieces = new HashMap<TicTacToeBoardPosition, Piece>();


    public boolean isValidPosition(TicTacToeBoardPosition position) {
        return true;
    }

    public TicTacToeBoardPosition getBoardPosition(Piece p) {
        return piecePositions.get(p);
    }


    public Piece getPieceAtPosition(TicTacToeBoardPosition destination) {
        return positionPieces.get(destination);
    }


    public void changePiecePosition(Piece piece, TicTacToeBoardPosition destination) {

        getBoardPosition(piece).clear();
        destination.setPiece(piece);
        piecePositions.put(piece, destination);
        positionPieces.put(destination, piece);

    }

    public void clearPosition(TicTacToeBoardPosition destination) {
        destination.clear();
    }




    public static void main(String[] args) {
        TicTacToeBoard board = new TicTacToeBoard();
        board.init();
        board.display();
    }

    public void addPieceAtPosition(Piece piece, TicTacToeBoardPosition destination, TicTacToeBoard board) {
        destination.setPiece(piece);
        piecePositions.put(piece, destination);
        positionPieces.put(destination, piece);
    }
}
