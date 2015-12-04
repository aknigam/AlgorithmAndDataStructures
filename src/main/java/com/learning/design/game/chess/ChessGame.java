package com.learning.design.game.chess;

import com.learning.design.game.Player;
import com.learning.design.game.*;
import com.learning.design.game.Board;


public class ChessGame extends MultiplayerBoardGame {


    @Override
    public void init() {

    }

    @Override
    public void showGameRules(){
        System.out.println("Showing chess rules");
    }

    @Override
    protected Player getWinner(Player p, Board board) {
        return null;
    }

    @Override
    protected boolean gameNotFinished(Board board) {
        return false;
    }
}
