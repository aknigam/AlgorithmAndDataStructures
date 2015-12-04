package com.learning.design.game;


public interface Board<E extends  BoardPosition> {

    boolean isValidPosition(E position);

    E getBoardPosition(Piece p) ;

    Piece getPieceAtPosition(E destination);

    void changePiecePosition(Piece piece, E destination);

    void clearPosition(E destination) ;
}
