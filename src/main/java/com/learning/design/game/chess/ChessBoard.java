package com.learning.design.game.chess;

import com.learning.design.game.Board;
import com.learning.design.game.Piece;
import com.learning.design.game.chess.piece.*;

import java.util.HashMap;
import java.util.Map;


public class ChessBoard implements Board<ChessBoardPosition>{


    private ChessBoardPosition[][] grid = new ChessBoardPosition[8][8];
    private Piece[] blackPieces = new Piece[16];
    private Piece[] whitePieces = new Piece[16];

    public void init(){
        initAllPositions();
        placePieces(PieceType.BLACK);
        placePieces(PieceType.WHITE);
    }

    private void initAllPositions() {
        boolean evenrow = false;
        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                if(evenrow)
                    grid[i][j] = new ChessBoardPosition(j%2 ==0 ? PieceType.BLACK : PieceType.WHITE, i, j);
                else
                    grid[i][j] = new ChessBoardPosition(j%2 ==0 ? PieceType.WHITE : PieceType.BLACK, i, j);
            }
            evenrow = evenrow ? false: true;


        }
    }

    public void display(){


        for (int i = 0; i < 8; i++) {
            System.out.println("------------------------------------------------------------------------");
            StringBuffer row = new StringBuffer("|");
            for (int j = 0; j < 8; j++) {

                row.append(grid[i][j].getPiece()).append(grid[i][j].getPositionColor() == PieceType.BLACK ? "." : "'"). append(" | ");
            }
            System.out.println(row);
            
        }
        System.out.println("------------------------------------------------------------------------");
    }

    private void placePieces(PieceType pieceType) {

        int firstRow = 0;
        int secondRow = 1;
        if(pieceType ==  PieceType.BLACK){
            firstRow =  7;
            secondRow = 6;
        }
        grid[firstRow][0].setPiece(new Rook(pieceType, 1));
        grid[firstRow][7].setPiece(new Rook(pieceType, 2));
        grid[firstRow][1].setPiece(new Horse(pieceType, 1));
        grid[firstRow][6].setPiece(new Horse(pieceType, 2));
        grid[firstRow][2].setPiece(new Camel(pieceType, 1));
        grid[firstRow][5].setPiece(new Camel(pieceType, 2));
        grid[firstRow][3].setPiece(new Queen(pieceType));
        grid[firstRow][4].setPiece(new King(pieceType));

        for (int i = 0; i < 8; i++) {
            grid[secondRow][i].setPiece(new Pawn(pieceType, i));
        }

    }

    private Map<Piece, ChessBoardPosition> piecePositions = new HashMap<Piece, ChessBoardPosition>();

    private Map<ChessBoardPosition, Piece> positionPieces = new HashMap<ChessBoardPosition, Piece>();


    public boolean isValidPosition(ChessBoardPosition position) {

        return true;
    }


    public ChessBoardPosition getBoardPosition(Piece p) {
        return piecePositions.get(p);
    }

    public Piece getPieceAtPosition(ChessBoardPosition destination) {
        return positionPieces.get(destination);
    }

    public void changePiecePosition(Piece piece, ChessBoardPosition destination) {

        piecePositions.get(piece).clear();
        destination.setPiece(piece);
        piecePositions.put(piece, destination);
        positionPieces.put(destination, piece);

    }

    public void clearPosition(ChessBoardPosition destination) {

    }




    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        board.init();
        board.display();
    }

}
