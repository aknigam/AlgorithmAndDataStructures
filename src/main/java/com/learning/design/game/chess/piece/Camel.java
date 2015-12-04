package com.learning.design.game.chess.piece;

import com.learning.design.game.BoardPosition;
import com.learning.design.game.Piece;
import com.learning.design.game.chess.piece.NumberedPiece;
import com.learning.design.game.chess.piece.PieceType;

/**
 * Created by a.nigam on 04/12/15.
 */
public class Camel extends NumberedPiece {
    public Camel(PieceType pieceType, int number) {
        super(pieceType, number);
    }

    public boolean isValidMove(BoardPosition startPosition, BoardPosition endPosition) {
        return false;
    }

    @Override
    protected String getShortName() {
        return "c";
    }
}
