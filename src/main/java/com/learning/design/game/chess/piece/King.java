package com.learning.design.game.chess.piece;

import com.learning.design.game.BoardPosition;
import com.learning.design.game.Piece;

/**
 * Created by a.nigam on 04/12/15.
 */
public class King implements Piece {
    private PieceType pieceType;

    public King(PieceType pieceType) {

        this.pieceType = pieceType;
    }

    public boolean isValidMove(BoardPosition startPosition, BoardPosition endPosition) {
        return false;
    }

    @Override
    public String toString() {
        return pieceType.getShortname()+" K  ";
    }
}
