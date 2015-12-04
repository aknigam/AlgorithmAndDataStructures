package com.learning.design.game.tictactoe;

import com.learning.design.game.Board;
import com.learning.design.game.Move;
import com.learning.design.game.Player;

/**
 * Created by a.nigam on 04/12/15.
 */
public class TicTacToePlayer implements Player {

    private final String name;

    private TicTacToePieceType pieceType;

    public TicTacToePlayer(String name, TicTacToePieceType pieceType){
        this.name =  name;
        this.pieceType = pieceType;

    }
    public Move move(Board board) {
        return null;
    }

    public String name() {
        return name+" - "+pieceType.getShortname();
    }

}
