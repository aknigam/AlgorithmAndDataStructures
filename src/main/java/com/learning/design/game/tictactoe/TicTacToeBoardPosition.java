package com.learning.design.game.tictactoe;

import com.learning.design.game.BlankPiece;
import com.learning.design.game.BoardPosition;
import com.learning.design.game.Piece;

/**
 * Created by a.nigam on 04/12/15.
 */
public class TicTacToeBoardPosition implements BoardPosition {

    private static Piece BLANK_POSITION_PIECE = new BlankPiece();
    private Piece piece = BLANK_POSITION_PIECE;

    private final int yCordinate;
    private final int xCoordinate;

    public TicTacToeBoardPosition(int yCordinate, int xCoordinate) {
        this.yCordinate = yCordinate;
        this.xCoordinate = xCoordinate;
    }


    public void setPiece(Piece p) {

    }

    public Piece getPiece() {
        return piece;
    }

    public void clear() {
        piece = BLANK_POSITION_PIECE;
    }
}
