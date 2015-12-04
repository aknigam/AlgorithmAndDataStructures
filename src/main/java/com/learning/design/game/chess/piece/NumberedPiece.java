package com.learning.design.game.chess.piece;

import com.learning.design.game.BoardPosition;
import com.learning.design.game.Piece;

/**
 * Created by a.nigam on 03/12/15.
 */
public abstract class NumberedPiece implements Piece {


    private final int number;
    private final PieceType pieceType;

    public NumberedPiece(PieceType pieceType, int number) {
        this.pieceType = pieceType;
        this.number = number;

    }

    public boolean isValidMove(BoardPosition startPosition, BoardPosition endPosition) {
        return false;
    }

    @Override
    public String toString() {
        return pieceType.getShortname()+" "+getShortName()+" "+number;
    }

    abstract protected String getShortName();
}
