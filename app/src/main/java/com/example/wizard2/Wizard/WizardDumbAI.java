package com.example.wizard2.Wizard;

import android.widget.TextView;

import com.example.wizard2.GameFramework.GameComputerPlayer;
import com.example.wizard2.GameFramework.infoMessage.GameInfo;
import com.example.wizard2.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.wizard2.GameFramework.utilities.Logger;

import java.io.Serializable;

public class WizardDumbAI extends GameComputerPlayer implements Serializable {

    private int bidNum;
    private int randomCard;
    private WizardCards cardToPlay;
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
            Logger.log("WizardDumbComputer", "Not your turn!");
            return;
        }

        if (info instanceof WizardState) {
            int playerID = ((WizardState) info).getPlayerTurn();
            WizardPlayer player = ((WizardState) info).getPlayerInfo(playerID);

            /**
             External Citation
             Date: 19 November 2019
             Problem: AI was playing when it was not its turn
             Resource: Dr. Nuxoll
             Solution: Went into the game framework and checked that playerID was equal to the player turn.
             */

            if (this.playerNum != playerID) return;

            //BIDDING STAGE: gets and sends random bid num
            if (((WizardState) info).getGameStage() == 0) {
                bidNum = (int) ((((WizardState) info).getRoundNum() + 1) * Math.random());
                myBid = new WizardBidAction(this, bidNum);
                Logger.log("WizardDumbComputer", "Sending bidding move");
                sleep(1);
                game.sendAction(myBid);
            }

            //PLAYING STAGE: gets a random index in players hand and sends that card to be played
            else if (((WizardState) info).getGameStage() == 1) {
                randomCard = (int) (player.getCurrentHand().size() * Math.random());

                //checks that index of card is not null
                while (player.getCurrentHand().get(randomCard) == null) {
                    randomCard = (int) (player.getCurrentHand().size() * Math.random());
                }
                cardToPlay = player.getCurrentHand().get(randomCard);
                myPlay = new WizardPlayAction(this, cardToPlay, randomCard);
                Logger.log("WizardDumbComputer", "Sending playing move");
                sleep(1);
                game.sendAction(myPlay);
            }
        }
    }
}
