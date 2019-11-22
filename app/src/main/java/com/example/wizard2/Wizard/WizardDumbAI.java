package com.example.wizard2.Wizard;

import android.widget.TextView;

import com.example.wizard2.GameFramework.GameComputerPlayer;
import com.example.wizard2.GameFramework.infoMessage.GameInfo;
import com.example.wizard2.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.wizard2.GameFramework.utilities.Logger;

public class WizardDumbAI extends GameComputerPlayer {

    private int bidNum;
    private int randomCard;
    private WizardCards cardToPlay;
    //private int placeInHand;
    WizardPlayAction myPlay;
    WizardBidAction myBid;


    public WizardDumbAI(String name){
        super(name);
    }

    /**
     * Called when the player receives a game-state (or other info) from the
     * game.
     *
     * @param info
     * 		the message from the game
     */
    @Override
    protected void receiveInfo(GameInfo info) {

        // if it was a "not your turn" message, just ignore it
        if (info instanceof NotYourTurnInfo) {
            Logger.log("WizardComputer", "Not your turn!");
            return;
        }

        if (info instanceof WizardState){

            int playerID = ((WizardState) info).getPlayerTurn();
            WizardPlayer player = ((WizardState) info).getPlayerInfo(playerID);

            //checks if it is players turn
            if (this.playerNum != playerID) return;

            //BIDDING ACTION: checks if it is bidding stage
            if(((WizardState) info).getGameStage()==0) {
                //gets random bid num and send bid num to be bid action
                bidNum = (int) ((((WizardState) info).getRoundNum()+1) * Math.random());
                myBid = new WizardBidAction(this, bidNum);
                sleep(1);
                //Logger.log("WizardComputer", "Sending bidding move");
                game.sendAction(myBid);

            }

            //PLAYING ACTION: checks if it is playing stage
            else if(((WizardState) info).getGameStage()==1 && ((WizardState) info).getPlayerTurn() >=1 &&
                    ((WizardState) info).getPlayerTurn() <=3) {
                //finds random spot in players hand
                randomCard = (int) (player.getCurrentHand().size() * Math.random());

                //checks if card is in hand i.e. not null
                while(player.getCurrentHand().get(randomCard)==null) {
                    randomCard = (int) (player.getCurrentHand().size() * Math.random());
                }

                //gets the card in players hand and sends card to play action
                cardToPlay = player.getCurrentHand().get(randomCard);
                myPlay = new WizardPlayAction(this, cardToPlay, randomCard);
                //Logger.log("WizardComputer", "Sending playing move");
                sleep(1);
                game.sendAction(myPlay);
            }
        }

        // delay for a second to make opponent think we're thinking
//        sleep(1);

        // Submit our move to the game object. We haven't even checked it it's
        // our turn, or that that position is unoccupied. If it was not our turn,
        // we'll get a message back that we'll ignore. If it was an illegal move,
        // we'll end up here again (and possibly again, and again). At some point,
        // we'll end up randomly pick a move that is legal.

    }
}
