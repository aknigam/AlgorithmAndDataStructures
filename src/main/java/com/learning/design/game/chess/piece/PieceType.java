package com.learning.design.game.chess.piece;

/**
 * Created by a.nigam on 04/12/15.
 */
public enum PieceType {
    WHITE("W"),  BLACK("B");


    public String getShortname() {
        return shortname;
    }

    private final String shortname;

    PieceType(String shortName) {
        this.shortname = shortName;
    }
}
