package com.learning.design.game.chess;

import com.learning.design.game.BlankPiece;
import com.learning.design.game.Board;
import com.learning.design.game.BoardPosition;
import com.learning.design.game.Piece;
import com.learning.design.game.chess.piece.PieceType;

/**
 * Created by a.nigam on 04/12/15.
 */
public class ChessBoardPosition implements BoardPosition {

    public PieceType getPositionColor() {
        return positionColor;
    }

    private PieceType positionColor;

    public Piece piece = new BlankPiece();

    public ChessBoardPosition(PieceType pieceType){
        this.positionColor = pieceType;
    }

    public void setPiece(Piece p) {
        this.piece = p;
    }

    public Piece getPiece() {
        return piece;
    }

}
