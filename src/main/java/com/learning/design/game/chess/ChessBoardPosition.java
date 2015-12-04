package com.learning.design.game.chess;

import com.learning.design.game.BlankPiece;
import com.learning.design.game.BoardPosition;
import com.learning.design.game.Piece;
import com.learning.design.game.chess.piece.PieceType;

public class ChessBoardPosition implements BoardPosition {

    private static final Piece BLANK_POSITION_PIECE = new BlankPiece();
    private final int yCordinate;
    private final int xCoordinate;

    public PieceType getPositionColor() {
        return positionColor;
    }

    private PieceType positionColor;

    public Piece piece = BLANK_POSITION_PIECE;

    public ChessBoardPosition(PieceType pieceType, int yCordinate, int xCoordinate){
        this.positionColor = pieceType;
        this.yCordinate = yCordinate;
        this.xCoordinate = xCoordinate;
    }

    public void setPiece(Piece p) {
        this.piece = p;
    }

    public Piece getPiece() {
        return piece;
    }

    public void clear() {
        piece = BLANK_POSITION_PIECE;
    }

}
