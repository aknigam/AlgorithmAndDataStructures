package com.learning.design.game;


public interface Board {

    boolean isValidPosition(BoardPosition position);

    BoardPosition getBoardPosition(Piece p) ;

    Piece getPieceAtPosition(BoardPosition destination);

    void changePiecePosition(Piece piece, BoardPosition destination);

    void clearPosition(BoardPosition destination) ;
}
