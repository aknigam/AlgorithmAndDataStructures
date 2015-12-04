package com.learning.design.game.chess;

import com.learning.design.game.Board;
import com.learning.design.game.BoardPosition;
import com.learning.design.game.Piece;

import java.util.HashMap;
import java.util.Map;


public class ChessBoard implements Board{


    private Map<Piece, BoardPosition> piecePositions = new HashMap<Piece, BoardPosition>();

    private Map<BoardPosition, Piece> positionPieces = new HashMap<BoardPosition, Piece>();


    public boolean isValidPosition(BoardPosition position) {
        return true;
    }

    public BoardPosition getBoardPosition(Piece p) {
        return null;
    }

    public Piece getPieceAtPosition(BoardPosition destination) {
        return null;
    }

    public void changePiecePosition(Piece piece, BoardPosition destination) {

    }

    public void clearPosition(BoardPosition destination) {

    }
}
