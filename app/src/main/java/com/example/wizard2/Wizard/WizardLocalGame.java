package com.example.wizard2.Wizard;

import com.example.wizard2.GameFramework.GamePlayer;
import com.example.wizard2.GameFramework.LocalGame;
import com.example.wizard2.GameFramework.actionMessage.GameAction;
import com.example.wizard2.GameFramework.utilities.Logger;

import java.io.Serializable;

public class WizardLocalGame extends LocalGame  implements Serializable {
    //Tag for logging
    private static final String TAG = "WizardLocalGame";

    protected WizardState state;
    private boolean waiting = false;
    public boolean roundOver;
    public boolean gameOver = false;
    private int roundCount = 3;

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
        //Game is over after 15th round
        if(gameOver) {
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
        }
        else{
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
        if(waiting){return false;}

        //gets player info
        WizardPlayer myPlayer = state.getPlayerInfo(state.getPlayerTurn());

        //BID ACTION
        if (action instanceof WizardBidAction) {

            //gets bid num from bid action
            int myBidNum = ((WizardBidAction) action).getBidNum();

            //checks if bid is valid and it is bidding stage
            if (myBidNum <= state.getRoundNum() && state.getGameStage() == 0) {

                //gets the ArrayList of integers that contains each player's bids
                //from WizardState and sets bid to according player
                state.setPlayerBids(myBidNum, state.getPlayerTurn());
                myPlayer.setBidNum(myBidNum);

                //check if it is end of round i.e. everyone has bid
                if (roundCount == 0) {
                    //sets game stage to 1: playing stage and resets player turn to 0
                    roundCount=3;
                    if(state.getPlayerTurn()!=3) {
                        state.setPlayerTurn(state.getPlayerTurn() + 1);
                    }
                    else{
                        state.setPlayerTurn(0);
                    }
                    state.setGameStage(1);
                    return true;
                }
                roundCount--;
                //updates to next players turn
                if(state.getPlayerTurn()!=3) {
                    state.setPlayerTurn(state.getPlayerTurn() + 1);
                }
                else{
                    state.setPlayerTurn(0);
                }

                return true;
            } else {
                return false;
            }
        }

        //PLAY ACTION
        else if (action instanceof WizardPlayAction) {

            //gets card chosen to play and place in hand from play action
            WizardCards cardToPlay = ((WizardPlayAction) action).getCardToPlay();
            int placeInHand = ((WizardPlayAction) action).getPlaceInHand();

            //checks if card is in hand and it is playing card stage and its players turn
            if (myPlayer.getCurrentHand().contains(cardToPlay) && state.getGameStage() == 1) {

                //moves card to cardPlayed in WizardState
                state.setCardsPlayed(cardToPlay, state.getPlayerTurn());

                //sets place as null in players hand
                myPlayer.getCurrentHand().set(placeInHand, null);

                //checks if it is end of round
                if (roundCount == 0) {
                    roundCount=3;
                    state.setPlayerTurn(4);

                    //need for clearing cards played
                    getTimer().setInterval(2000);
                    getTimer().start();
                    waiting = true;
                    return true;
                }

                //updates to next players turn
                roundCount--;
                if(state.getPlayerTurn()!=3) {
                    state.setPlayerTurn(state.getPlayerTurn() + 1);
                }
                else{
                    state.setPlayerTurn(0);
                }
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Resets the cards played by each player in GUI and prepares for next subround
     */
    public void resetTrick() {
        WizardPlayer myPlayer = state.getPlayerInfo(0);

        state.setPlayerTurn(0);

        //calculate who won sub round
        if(state.getRoundNum()==15){
            state.calculateWinnerRound15();
        }
        else {
            state.calculateWinner();
        }

        state.setPlayerTurn(state.getWinner());

        //checks if the round is over by checking if players hand is empty
        roundOver = true;

        for (int i = 0; i < state.getRoundNum(); i++) {

            //if it is not end of round then keep going
            if (myPlayer.getCurrentHand().get(i) != null) {
                roundOver = false;
                break;
            }
        }

        //resets cards played to null
        state.resetImage();

        //checks if round is over
        if (roundOver) {
            Logger.log("Local Game", "hand empty");
            state.calculateScores();
            for (int j = 0; j < 4; j++) {
                state.setPlayerBids(0, j);
                state.setPlayerBidsWon(0, j);
            }

            if(state.getRoundNum()==15){
                gameOver = true;
                checkIfGameOver();
            }

            state.setGameStage(0);
            state.addTrumpCard();

            this.checkIfGameOver();
            Logger.log("Local Game", "Round num:" + state.getRoundNum());
            if(state.getRoundNum() < 15) {
                state.setRoundNum(state.getRoundNum() + 1);
                state.dealDeck(state.roundNum);
            }
        }
    }

    /**
     * Uses timer to remove cards after specified time
     */
    @Override
    protected void timerTicked() {
        resetTrick();
        getTimer().stop();
        waiting = false;
        sendAllUpdatedState();
    }
}
