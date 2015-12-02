package com.learning.design.game;

import com.learning.design.Move;
import com.learning.design.Player;
import com.learning.design.PlayerList;
import com.learning.design.game.impl.Board;
import com.learning.design.game.piece.Piece;

public abstract class MultiplayerBoardGame {

    /*

        A game is played using following entities:

        1. Board
        2. Player
        3. Pieces

        A chess game has a winner and looser.
        A chess game has its rules.
        Game should have following features:
            1. Initialize
            2. Start game
            3. Make move - unless the game is not finished


        Game manager manages multiple games

     */

    private PlayerList playerList;

    private Board board;

    abstract public void init();

    public void start(){

        /*
            Steps
                1. Prompt/ask the first player to make the move
                2. Validate the move
                3. Change the state of the game by applying the move
                4. Continue until the game is finished
         */
        while(true) {
            Player p = playerList.getNextPlayer();

            Move m = p.move();

            if(validateMove(m)) {
                applyMove(m);
            }
        }

    }

    protected void applyMove(Move m){
        // giving the chess impl here.


    }

    // protected abstract void validateMove(Move m);

    protected boolean validateMove(Move m){
        /*

        Move should have following information
        ---FIXED----
        1. Player - who made the move
        2. Piece - which made the move
        3. Board
        ---VARIABLES----
        ---for chess ---
        3. End position
        ---VARIABLES----
        -- for ludo it will be
        3. No of cells advanced
         */

        // giving the chess implementation here


        Piece p = m.getPiece();
        BoardPosition endPosition = m.getDestination();
        boolean isValidMove  = p.isValidMove(endPosition);
        return isValidMove && board.isValidPosition();

    }


}
