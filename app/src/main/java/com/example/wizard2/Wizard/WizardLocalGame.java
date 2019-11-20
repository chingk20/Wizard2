package com.example.wizard2.Wizard;

import android.util.Log;

import com.example.wizard2.GameFramework.GamePlayer;
import com.example.wizard2.GameFramework.LocalGame;
import com.example.wizard2.GameFramework.actionMessage.GameAction;
import com.example.wizard2.GameFramework.utilities.Logger;

import java.util.ArrayList;

import static android.os.SystemClock.sleep;

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
        super();

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
            //checks if bid is valid and it is bidding stage
                if (((WizardBidAction) action).getBidNum() <= state.getRoundNum() && state.getGameStage() == 0) {
                    // gets the ArrayList of integers that contains each player's bids from WizardState
                    state.setPlayerBids(((WizardBidAction) action).getBidNum(), state.getPlayerTurn());
                    //Logger.log("LocalGame", "Sending bidding move bid:" + ((WizardBidAction) action).getBidNum());
                    Logger.log("Local Game", "Players Bids: " + state.getPlayerBids());
                    //Logger.log("Local Game", "Player Turn: " + state.getPlayerTurn());
                    //check if it is end of round i.e. everyone has bid
                    if (state.getPlayerTurn() == 3) {
                        state.setGameStage(1);
                        state.setPlayerTurn(0);
                        //Logger.log("Local Game", "Player Turn: " + state.getPlayerTurn() + " Game Stage: " + state.getGameStage());
                        return true;
                    }
                        state.setPlayerTurn(state.playerTurn+1);
                        //Logger.log("Local Game", "Player Turn: " + state.getPlayerTurn() + " Game Stage: " + state.getGameStage());
                        return true;
                } else {
                    return false;
                }
        } else if (action instanceof WizardPlayAction) {
            WizardPlayer myPlayer = state.getPlayerInfo(state.getPlayerTurn());
            WizardCards cardToPlay = ((WizardPlayAction) action).getCardToPlay();
            //checks if card is in hand and it is playing card stage and its players turn
            if (myPlayer.getCurrentHand().contains(cardToPlay) && state.getGameStage()==1) {
                Logger.log("Local Game", "Player Turn:" + state.getPlayerTurn());
                state.setCardsPlayed(cardToPlay, state.getPlayerTurn());
                state.setCardsPlayedValue(cardToPlay.getCardValue(), state.getPlayerTurn());
                myPlayer.getCurrentHand().remove(cardToPlay);
                Logger.log("Local Game", "Cards Played: " + state.getCardsPlayedValue());
                //checks is it is end of round
                //if everyone's hand is empty then increment round num and redeal
                if(state.getPlayerTurn()==3)
                {
                    if(myPlayer.getCurrentHand().size()==0)
                    {
                        //resets all bids to zero after round
                        for(int i=0; i<4; i++) {
                            state.setPlayerBids(i, 0);
                        }
                        state.setGameStage(0);
                        state.setPlayerTurn(0);
                        state.calculateWinner();
                        WizardPlayer player0 = state.getPlayerInfo(0);
                        //state.resetImage();
                        state.setRoundNum(state.roundNum++);
                        state.dealDeck(state.roundNum);
                        Logger.log("Local Game", "Deal Deck");
                    }
                    state.setGameStage(0);
                    state.setPlayerTurn(0);
                    //calculate who won sub round
                    state.calculateWinner();
                    Logger.log("Local Game", "Bids Won:" + state.getPlayerBidsWon());
                    //Logger.log("Local Game", "Player Turn:" + state.getPlayerTurn());
                    //state.resetImage();
                    return true;
                }
                state.setPlayerTurn(state.playerTurn+1);
                Logger.log("Local Game", "Player Turn:" + state.getPlayerTurn());
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
