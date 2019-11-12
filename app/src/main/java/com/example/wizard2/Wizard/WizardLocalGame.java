package com.example.wizard2.Wizard;

import com.example.wizard2.GameFramework.GamePlayer;
import com.example.wizard2.GameFramework.LocalGame;
import com.example.wizard2.GameFramework.actionMessage.GameAction;

import java.util.ArrayList;

public class WizardLocalGame extends LocalGame {
    //Tag for logging
    private static final String TAG = "WizardLocalGame";
    // the game's state
    protected WizardState state;

    /**
     * Constructor for the WizardLocalGame.
     */
    public WizardLocalGame() {
        //perform superclass initialization
        //super();

        // create a new, unfilled-in WizardState object
        state = new WizardState();
    }

    /**
     * Check if the game is over. It is over, return a string that tells
     * who the winner(s), if any, are. If the game is not over, return null;
     *
     * @return a message that tells who has won the game, or null if the
     * game is not over
     */
    @Override
    protected String checkIfGameOver() {
        //Game is over after 15 rounds
        if (state.roundNum == 16) {
            int player1Score = state.getPlayerInfo(0).getPlayerScore();
            int player2Score = state.getPlayerInfo(1).getPlayerScore();
            int player3Score = state.getPlayerInfo(2).getPlayerScore();
            int player4Score = state.getPlayerInfo(3).getPlayerScore();
            if (player1Score > player2Score && player1Score > player3Score && player1Score > player4Score) {
                return ("Player 1 is the winner");
            } else if (player2Score > player1Score && player2Score > player3Score && player2Score > player4Score) {
                return ("Player 2 is the winner");
            } else if (player3Score > player1Score && player3Score > player2Score && player3Score > player4Score) {
                return ("Player 3 is the winner");
            } else if (player4Score > player1Score && player4Score > player2Score && player4Score > player3Score) {
                return ("Player 4 is the winner");
            } else {
                return ("There is a tie");
            }
        } else {
            return null;
        }
    }

    /**
     * Notify the given player that its state has changed. This should involve sending
     * a GameInfo object to the player. If the game is not a perfect-information game
     * this method should remove any information from the game that the player is not
     * allowed to know.
     *
     * @param p the player to notify
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        // make a copy of the state, and send it to the player
        WizardState copy = new WizardState(state);
        p.sendInfo(copy);
    }

    /**
     * Tell whether the given player is allowed to make a move at the
     * present point in the game.
     *
     * @param playerIdx the player's player-number (ID)
     * @return true iff the player is allowed to move
     */
    protected boolean canMove(int playerIdx) {
        if (state.getPlayerTurn() == playerIdx) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Makes a move on behalf of a player.
     *
     * @param action The move that the player has sent to the game
     * @return Tells whether the move was a legal one.
     */
    @Override
    protected boolean makeMove(GameAction action) {

        if (action instanceof WizardBidAction) {
            // gets the ArrayList of integers that contains each player's bids from WizardGameState
            state.getPlayerBids().add(state.getPlayerTurn(), ((WizardBidAction) action).getBidNum());
            return true;
        } else if (action instanceof WizardPlayAction) {
            // get the suit and value of the player's hand
            WizardPlayAction tm = (WizardPlayAction) action;
            //int cardValue = tm.getCardValue();
            //String cardSuit = tm.getCardSuit();
            ArrayList<WizardCards> playershand = new ArrayList<WizardCards>();
            //playershand = state.getCurrentHand();

            // get the id of our player
            int playerId = getPlayerIdx(tm.getPlayer());

            //if the player tries to play a card when it is not their turn, indicate an illegal move
            if (playerId != state.getPlayerTurn()) {
                return false;
            }
                // get the id of the player whose move it is
                int whoseMove = state.getPlayerTurn();

                //place the player's selected card in the middle
                //state.setCardPlayed(cardValue, cardSuit, playershand);

                // make it the other player's turn
                state.setPlayerTurn(whoseMove++);

                // return true, indicating the it was a legal move
                return true;
            }
            return false;
        }
}
