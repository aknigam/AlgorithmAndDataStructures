package com.learning.design.game;

/*

        A game is played using following entities:

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

     */
public abstract class MultiplayerBoardGame {

    private static final int MAX_ATTEMPTS_PER_PLAYER = 1;



    private PlayerList playerList;

    private Board board;

    abstract public void init();

    abstract public void showGameRules();

    public void start() throws Exception {

        /*
            Steps
                1. Prompt/ask the first player to make the move
                2. Validate the move
                3. Change the state of the game by applying the move
                4. Continue until the game is finished
         */
        int noOfAttempts = 0;
        Move m = null;
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
            
            
        }

        Player winner  = getWinner(p, board);

    }

    protected abstract Player getWinner(Player p, Board board);

    protected abstract boolean gameNotFinished(Board board);

    protected void applyMove(Move m, Board board){
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

    // protected abstract void validateMove(Move m);

    protected boolean validateMove(Move m, Board board){

        /*
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
         */

        // giving the chess implementation here

        // what about the case if the other piece is present in the target.


        Piece p = m.getPiece();

        BoardPosition startPosition = board.getBoardPosition(p);
        BoardPosition endPosition = m.getDestination();
        // first apply piece rules and then the board rules.
        // then any other game rules
        boolean isValidMove  = p.isValidMove(startPosition , endPosition);

        return isValidMove && this.board.isValidPosition(endPosition);

    }


}
