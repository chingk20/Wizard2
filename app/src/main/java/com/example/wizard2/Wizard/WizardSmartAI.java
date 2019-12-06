package com.example.wizard2.Wizard;

import com.example.wizard2.GameFramework.GameComputerPlayer;
import com.example.wizard2.GameFramework.infoMessage.GameInfo;
import com.example.wizard2.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.wizard2.GameFramework.utilities.Logger;

import java.io.Serializable;

public class WizardSmartAI extends GameComputerPlayer implements Serializable {

    private int bidNum;
    private int indexInHand;
    private WizardCards cardToPlay;
    private WizardCards aCard;
    private WizardCards tempCard;
    WizardPlayAction myPlay;
    WizardBidAction myBid;
    private int wizardValue = 15;
    private int tempValue = -1;
    private boolean canNotWin = false;

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

            /**
             External Citation
             Date: 19 November 2019
             Problem: AI was playing when it was not its turn
             Resource: Dr. Nuxoll
             Solution: Went into the game framework and checked that playerID was equal to the player turn.
             */
            if (this.playerNum != playerID) return;

            WizardPlayer player = ((WizardState) info).getPlayerInfo(playerID);
            String trumpSuit = (((WizardState) info).getTrumpSuit());
            int handSize = player.getCurrentHand().size();

            //BID STAGE: looks through hand, if wizard or trump suit present then increment bid
            if(((WizardState) info).getGameStage()==0) {
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

            //PLAYING STAGE: Tries to win, but if can't then play lowest card.
            else if(((WizardState) info).getGameStage()==1 && player.getCurrentHand() != null) {
                tempValue=-1;
                //goes through each card in players hand size
                for(int j=0; j< player.getCurrentHand().size(); j++) {
                        tempCard = player.getCurrentHand().get(j);
                        //checks that card is not null
                        if(tempCard != null) {
                            //plays the best card it has: wizard, then trump suit, then highest number
                            if(tempCard.getCardValue() == 15){
                                tempValue = tempCard.getCardNumber();
                                cardToPlay = tempCard;
                                indexInHand = j;
                            }
                            else if(tempCard.getCardNumber() > tempValue && tempCard.getCardSuit() == trumpSuit){
                                tempValue = tempCard.getCardNumber();
                                cardToPlay = tempCard;
                                indexInHand = j;
                            }
                            else if (tempCard.getCardNumber() > tempValue) {
                                tempValue = tempCard.getCardNumber();
                                cardToPlay = tempCard;
                                indexInHand = j;
                            }
                        }
                    }
                tempValue=-1;
                //checks cards already played to see if it can win with highest card
                for(int i=0; i < playerID; i++){
                    tempCard = ((WizardState) info).getCardsPlayed().get(i);
                    if(tempCard != null) {
                        if (tempCard.getCardNumber() >= cardToPlay.getCardNumber() && tempCard.getCardSuit()==trumpSuit) {
                            canNotWin = true;
                            break;
                        }
                        else if(tempCard.getCardNumber() >= cardToPlay.getCardNumber()){
                            canNotWin = true;
                            break;
                        }
                    }
                }
                tempValue = 15;
                //if can't win then play the lowest card so it doesn't waste high cards
                if(canNotWin){
                    for(int j=0; j< player.getCurrentHand().size(); j++) {
                        tempCard = player.getCurrentHand().get(j);
                        //checks that card is not null
                        if(tempCard != null) {
                            //plays the worst card it has
                            if (tempCard.getCardNumber() < tempValue && tempCard.getCardSuit() != trumpSuit) {
                                tempValue = tempCard.getCardNumber();
                                cardToPlay = tempCard;
                                indexInHand = j;
                            }
                            else if (tempCard.getCardNumber() < tempValue){
                                tempValue = tempCard.getCardNumber();
                                cardToPlay = tempCard;
                                indexInHand = j;
                            }
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
