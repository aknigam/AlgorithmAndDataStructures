package com.learning.design.game;

/**
 * Some of the properties can be:
 *
 *
 * 1. Identity information
 * 2. Score
 * 3. Position/Order
 *
 */
public interface Player {

    Move move(Board board) ;

    String name();

}
