package com.example.wizard2.Wizard;

import com.example.wizard2.GameFramework.GameComputerPlayer;
import com.example.wizard2.GameFramework.infoMessage.GameInfo;
import com.example.wizard2.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.wizard2.GameFramework.utilities.Logger;

public class WizardSmartAI extends GameComputerPlayer {

    private int bidNum;
    private int indexInHand;
    private WizardCards cardToPlay;
    private WizardCards aCard;
    private WizardCards tempCard;
    WizardPlayAction myPlay;
    WizardBidAction myBid;
    private int wizardValue = 15;
    private int tempValue = -1;

    public WizardSmartAI(String name){
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
            Logger.log("WizardSmartComputer", "Not your turn!");
            return;
        }

        if (info instanceof WizardState){
            int playerID = ((WizardState) info).getPlayerTurn();
            WizardPlayer player = ((WizardState) info).getPlayerInfo(playerID);
            String trumpSuit = (((WizardState) info).getTrumpSuit());
            int handSize = player.getCurrentHand().size();

            //checks that it is players turn
            if (this.playerNum != playerID) return;

            //BID STAGE: gets and sends random bid num
            if(((WizardState) info).getGameStage()==0) {
                //looks through hand, if wizard or trump suit then increment bid.
                bidNum=0;
                for(int i=0; i< handSize; i++) {
                    aCard = player.getCurrentHand().get(i);
                    if(aCard !=null) {
                        if (aCard.getCardNumber() == wizardValue || aCard.getCardSuit() == trumpSuit) {
                            bidNum++;
                        }
                    }
                }
                myBid = new WizardBidAction(this, bidNum);
                Logger.log("WizardSmartComputer", "Sending bidding move");
                sleep(1);
                game.sendAction(myBid);
            }

            //PLAYING STAGE: gets a random index in players hand and sends that card to be played
            else if(((WizardState) info).getGameStage()==1 && player.getCurrentHand() != null) {
                tempValue=-1;
                //goes through each card in players hand size
                for(int j=0; j< player.getCurrentHand().size(); j++) {
                        tempCard = player.getCurrentHand().get(j);
                        //checks that card is not null
                        if(tempCard != null) {
                            //plays the best card it has
                            if (tempCard.getCardNumber() > tempValue) {
                            tempValue = tempCard.getCardNumber();
                            cardToPlay = tempCard;
                            indexInHand = j;
                            }
                        }
                    }
                cardToPlay = player.getCurrentHand().get(indexInHand);
                myPlay = new WizardPlayAction(this, cardToPlay, indexInHand);
                Logger.log("WizardComputer", "Sending playing move");
                sleep(1);
                game.sendAction(myPlay);
            }
        }
    }
}
