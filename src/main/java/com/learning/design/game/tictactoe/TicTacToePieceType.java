package com.learning.design.game.tictactoe;

/**
 * Created by a.nigam on 04/12/15.
 */
public enum TicTacToePieceType {
    CROSS("x"), ZERO("0");

    public String getShortname() {
        return shortname;
    }

    private final String shortname;

    TicTacToePieceType(String shortname) {
        this.shortname = shortname;
    }
}
