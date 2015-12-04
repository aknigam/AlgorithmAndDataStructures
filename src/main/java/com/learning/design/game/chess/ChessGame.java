package com.learning.design.game.chess;

import com.learning.design.game.Player;
import com.learning.design.game.*;
import com.learning.design.game.Board;


public class ChessGame extends MultiplayerBoardGame<ChessBoard, ChessBoardPosition> {


    public ChessGame(ChessBoard board, PlayerList playerList) {
        super(board, playerList);
    }

    @Override
    public void init() {

    }

    @Override
    public void showGameRules(){
        System.out.println("Showing chess rules");
    }

    @Override
    protected Player getWinner(Player p, ChessBoard board) {
        return null;
    }

    @Override
    protected boolean gameNotFinished(ChessBoard board) {
        return false;
    }

    @Override
    protected void applyMove(Move<ChessBoardPosition> m, ChessBoard board) {
        // giving the chess impl here.
        // this takes care of application. Validation is already done. Even if a piece is already present in the target position.
        // validation has passed , this means the the moved piece can move to the destination.


        Piece dp = board.getPieceAtPosition(m.getDestination());
        if(dp == null)
        {
            board.clearPosition(m.getDestination());

        }
        board.changePiecePosition(m.getPiece(), m.getDestination());
    }

    /**
     Move should have following information
     ---FIXED----
     1. Player - who made the move
     2. Piece - which made the move
     3. ChessBoard
     ---VARIABLES----
     ---for chess ---
     3. End position
     ---VARIABLES----
     -- for ludo it will be
     3. No of cells advanced

     // giving the chess implementation here

     // what about the case if the other piece is present in the target.
     **/
    @Override
    protected boolean validateMove(Move<ChessBoardPosition> m, ChessBoard board) {
        Piece p = m.getPiece();

        ChessBoardPosition startPosition = board.getBoardPosition(p);
        ChessBoardPosition endPosition = m.getDestination();
        // first apply piece rules and then the board rules.
        // then any other game rules
        boolean isValidMove  = p.isValidMove(startPosition , endPosition);

        return isValidMove && board.isValidPosition(endPosition);
    }
}
