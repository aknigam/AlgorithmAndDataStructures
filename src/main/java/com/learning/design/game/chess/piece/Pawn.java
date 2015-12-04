package com.learning.design.game.chess.piece;

import com.learning.design.game.BoardPosition;
import com.learning.design.game.Piece;

/**
 * Created by a.nigam on 04/12/15.
 */
public class Pawn extends NumberedPiece {
    public Pawn(PieceType pieceType, int number) {
        super(pieceType, number);
    }

    public boolean isValidMove(BoardPosition startPosition, BoardPosition endPosition) {
        return false;
    }

    @Override
    protected String getShortName() {
        return "p";
    }
}
