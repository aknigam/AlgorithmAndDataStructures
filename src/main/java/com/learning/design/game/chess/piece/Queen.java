package com.learning.design.game.chess.piece;

import com.learning.design.game.BoardPosition;
import com.learning.design.game.Piece;
import com.learning.design.game.chess.piece.PieceType;

/**
 * Created by a.nigam on 04/12/15.
 */
public class Queen implements Piece {

    private PieceType pieceType;
    public Queen(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public boolean isValidMove(BoardPosition startPosition, BoardPosition endPosition) {
        return false;
    }

    @Override
    public String toString() {
        return pieceType.getShortname()+" Q  ";
    }
}
