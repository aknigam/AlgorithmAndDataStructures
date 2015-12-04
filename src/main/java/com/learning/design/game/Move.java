package com.learning.design.game;

import com.learning.design.game.BoardPosition;
import com.learning.design.game.Piece;

/**
 * Created by a.nigam on 02/12/15.
 */
public class Move <E extends BoardPosition> {
    private Piece piece;
    private E destination;

    public Piece getPiece() {
        return piece;
    }

    public E getDestination() {
        return destination;
    }
}
