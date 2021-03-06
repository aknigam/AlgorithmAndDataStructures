package com.learning.design.game.chess.piece;

import com.learning.design.game.BoardPosition;

/**
 * Created by a.nigam on 03/12/15.
 */
public class Rook extends NumberedPiece {


    public Rook(PieceType pieceType, int number) {
        super(pieceType, number);
    }

    public boolean isValidMove(BoardPosition startPosition, BoardPosition endPosition) {
        return false;
    }

    @Override
    protected String getShortName() {
        return "r";
    }

}
