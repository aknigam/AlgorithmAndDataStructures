package com.learning.design.game.chess.piece;

import com.learning.design.game.BoardPosition;
import com.learning.design.game.Piece;

/**
 * Created by a.nigam on 04/12/15.
 */
public class Horse extends NumberedPiece {

    public Horse(PieceType pieceType, int number) {
        super(pieceType, number);
    }

    public boolean isValidMove(BoardPosition startPosition, BoardPosition endPosition) {
        return false;
    }

    @Override
    protected String getShortName() {
        return "h";
    }
}
