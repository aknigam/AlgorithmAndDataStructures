package com.learning.design.game;

/**
 * Created by a.nigam on 04/12/15.
 */
public class BlankPiece implements  Piece {

    public boolean isValidMove(BoardPosition startPosition, BoardPosition endPosition) {
        return false;
    }

    @Override
    public String toString() {
        return "     ";
    }
}
