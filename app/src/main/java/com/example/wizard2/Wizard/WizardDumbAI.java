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
    WizardPlayAction myPlay = new WizardPlayAction(this, cardToPlay);
    WizardBidAction myBid = new WizardBidAction(this, bidNum);

    WizardState state;


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

        state = (WizardState) info;

        // if it was a "not your turn" message, just ignore it
        if (info instanceof NotYourTurnInfo) return;
        Logger.log("WizardComputer", "Not your turn!");

        if (info instanceof WizardState){
            int playerID = ((WizardState) info).getPlayerTurn();
            //Logger.log("WizardComputer", "Player ID" + playerID);
            if(((WizardState) info).getGameStage()==0) {
                //Logger.log("WizardComputer", "Sending bidding move");
                bidNum = (int) ((((WizardState) info).getRoundNum() + 1) * Math.random());
                myBid = new WizardBidAction(this, bidNum);
                Logger.log("WizardComputer", "Computer Bid:" + bidNum);
                Logger.log("WizardComputer", "Sending bidding move");
                super.game.sendAction(myBid);
                sleep(1);
            }
            //need to update I think it goes through three times
            else if(((WizardState) info).getGameStage()==1 && ((WizardState) info).getPlayerTurn() >=1 &&
                    ((WizardState) info).getPlayerTurn() <=3) {
                randomCard = (int) (((WizardState) info).getPlayerInfo(playerID).getCurrentHand().size() * Math.random());
                //Logger.log("WizardComputer", "Random Computer Card:" + randomCard);
                //need to check if card is in hand
                int size = ((WizardState) info).getPlayerInfo(playerID).getCurrentHand().size();
                Logger.log("WizardComputer", "Player Turn:"+((WizardState) info).getPlayerTurn()+ " Current Hand Size:" + size);
                cardToPlay = ((WizardState) info).getPlayerInfo(playerID).getCurrentHand().get(randomCard);
                //Logger.log("WizardComputer", "Computer Card Played:" + cardToPlay);
                myPlay = new WizardPlayAction(this, cardToPlay);
                Logger.log("WizardComputer", "Sending playing move");
                super.game.sendAction(myPlay);
            }
        }

        // delay for a second to make opponent think we're thinking
        sleep(3);

        // Submit our move to the game object. We haven't even checked it it's
        // our turn, or that that position is unoccupied. If it was not our turn,
        // we'll get a message back that we'll ignore. If it was an illegal move,
        // we'll end up here again (and possibly again, and again). At some point,
        // we'll end up randomly pick a move that is legal.
        Logger.log("WizardComputer", "Sending move");
        super.game.sendAction(myBid);
        super.game.sendAction(myPlay);


    }
}
