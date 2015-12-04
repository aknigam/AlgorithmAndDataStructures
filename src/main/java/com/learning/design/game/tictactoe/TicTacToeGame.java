package com.learning.design.game.tictactoe;


import com.learning.design.game.*;


public class TicTacToeGame extends MultiplayerBoardGame<TicTacToeBoard, TicTacToeBoardPosition>{


    public static void main(String[] args) throws Exception {

        TicTacToeBoard b = new TicTacToeBoard();
        PlayerList ps = new TicTacToePlayers();
        TicTacToeGame game = new TicTacToeGame(b, ps);
        game.init();
        game.start();
    }

    public TicTacToeGame(TicTacToeBoard board, PlayerList playerList) {
        super(board, playerList);
    }

    @Override
    public void init() {
        board.init();
    }

    @Override
    public void showGameRules() {

    }

    @Override
    protected Player getWinner(Player p, TicTacToeBoard board) {
        return null;
    }

    @Override
    protected boolean gameNotFinished(TicTacToeBoard board) {
        return false;
    }

    @Override
    protected void applyMove(Move<TicTacToeBoardPosition> m, TicTacToeBoard board) {
        board.addPieceAtPosition(m.getPiece(), m.getDestination(), board);
    }

    @Override
    protected boolean validateMove(Move<TicTacToeBoardPosition> m, TicTacToeBoard board) {
        return true;
    }
}
