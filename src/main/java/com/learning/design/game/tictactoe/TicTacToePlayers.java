package com.learning.design.game.tictactoe;

import com.learning.design.game.Player;
import com.learning.design.game.PlayerList;

public class TicTacToePlayers implements PlayerList {

    private final TicTacToePlayer xPlayer;
    private final TicTacToePlayer oPlayer;
    int cp = 0;

    public TicTacToePlayers(){
        xPlayer = new TicTacToePlayer("Anand", TicTacToePieceType.CROSS);
        oPlayer = new TicTacToePlayer("Garima", TicTacToePieceType.ZERO);
    }

    public Player getNextPlayer() {
        if(cp++ %2 == 0){
            return oPlayer;
        }
        return xPlayer;
    }
}
