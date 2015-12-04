package com.learning.design.game.tictactoe.piece;

import com.learning.design.game.BoardPosition;
import com.learning.design.game.Piece;

/**
 * Created by a.nigam on 04/12/15.
 */
public class Cross implements Piece {
    public boolean isValidMove(BoardPosition startPosition, BoardPosition endPosition) {
        return true;
    }

    @Override
    public String toString() {
        return "x";
    }
}
