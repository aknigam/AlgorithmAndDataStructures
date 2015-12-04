package com.learning.design.game;

/**

        A chess game is played using following entities:

        1. ChessBoard
        2. Player
        3. Pieces

        A chess game has a winner and looser.
        A chess game has its rules.
        Game should have following features:
            1. Initialize
            2. Start game
            3. Make move - unless the game is not finished


        Game manager manages multiple games

        Player can see all the pieces on the board but should not be allowed to move the piece for which he is not authorized.
        So piece should not be encapsulated in the Player class rather they should be part of ChessBoard

        Player and Piece should have something in common - Color

        ChessBoard should have
        1. The ChessBoard representation (grid etc)
        2. The position of all the pieces and

        ChessBoard should provide methods to get the piece so that a move can be made.
        1. Get piece by position
        2. get piece by piece identifier (name, number and color etc)

        UNDO functionality.
        - Keep track of the moves in the sequential order.

 **/
public abstract class MultiplayerBoardGame<B extends Board, E extends BoardPosition> {

    private static final int MAX_ATTEMPTS_PER_PLAYER = 1;

    protected PlayerList playerList;

    protected B board;


    public MultiplayerBoardGame(B board, PlayerList playerList){
        this.board = board;
        this.playerList = playerList;
    }

    abstract public void init();

    abstract public void showGameRules();

    public void start() throws Exception {

        System.out.println(board);
        /*
            Steps
                1. Prompt/ask the first player to make the move
                2. Validate the move
                3. Change the state of the game by applying the move
                4. Continue until the game is finished
         */
        int noOfAttempts = 0;
        Move<E> m = null;
        Player p =  null;
        while(gameNotFinished(board)) {
            p = playerList.getNextPlayer();

            // player is making the move on the board
            // Move m = p.move(board);

            while(true) {

                // timeout must be handled here
                m = p.move(board);


                noOfAttempts++;
                if(validateMove(m, board))
                    break;
                if(noOfAttempts == MAX_ATTEMPTS_PER_PLAYER){
                    throw new Exception("User has exceeded max no. of attempts to make a valid move.");
                }
            }

            /*
            Result of applying the move is to
            1. record the move
            2. displace a piece if required by the game
            3. Giving a score to player etc. Score is normally not valid for most board games.

             */
            applyMove(m, board);

            System.out.println(board);
            
            
        }

        Player winner  = getWinner(p, board);

    }

    protected abstract Player getWinner(Player p, B board);

    protected abstract boolean gameNotFinished(B board);

    protected abstract void applyMove(Move<E> m, B board);

    protected abstract boolean validateMove(Move<E> m, B board);


}
