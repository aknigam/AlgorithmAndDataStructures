package com.learning.design.game;


import com.learning.design.game.BoardPosition;

public interface Piece {

    // this property may not be required here
    //private BoardPosition boardPosition;

    public boolean isValidMove(BoardPosition startPosition, BoardPosition endPosition) ;
}
