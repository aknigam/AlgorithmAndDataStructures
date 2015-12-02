package com.learning.design;

import com.learning.design.game.BoardPosition;
import com.learning.design.game.piece.Piece;

/**
 * Created by a.nigam on 02/12/15.
 */
public class Move {
    private Piece piece;
    private BoardPosition destination;

    public Piece getPiece() {
        return piece;
    }

    public BoardPosition getDestination() {
        return destination;
    }
}
