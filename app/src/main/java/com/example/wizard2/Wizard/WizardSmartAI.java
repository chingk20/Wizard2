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
    WizardPlayAction myPlay;
    WizardBidAction myBid;
    private int wizardValue = 15;

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
            else if(((WizardState) info).getGameStage()==1) {

                //Looks through the previous cards played and sees if it can win
                for(int i=0; i<playerID; i++){
                    //goes through hand size
                    for(int j=0; j<handSize; j++) {
                        //checks that player is not winning more bids than they bid
                        if(player.getCurrentHand().get(j)!=null ) {
                            if (player.getBidNum() >= (((WizardState) info).getPlayerBidsWon().get(playerID))) {
                                Logger.log("WizardSmartComputer", "Cards Played"+ ((WizardState) info).getCardsPlayed());
                                Logger.log("WizardSmartComputer", "player turn"+ playerID +" card: " +i+" " +((WizardState) info).getCardsPlayed().get(i));
                                //if someone has already played a trump suit card then look at values to see if computer can win
                                if (((WizardState) info).getCardsPlayed().get(i).getCardSuit() != null
                                        && ((WizardState) info).getCardsPlayed().get(i).getCardSuit() == trumpSuit
                                        && player.getCurrentHand().get(j).getCardSuit() == trumpSuit
                                        && ((WizardState) info).getCardsPlayed().get(i).getCardNumber() < player.getCurrentHand().get(j).getCardNumber()) {
                                    cardToPlay = player.getCurrentHand().get(j);
                                    indexInHand = j;
                                    //break;
                                }
                                //if not then look at value of cards to see if they can win
                                else if (((WizardState) info).getCardsPlayed().get(i).getCardNumber() < player.getCurrentHand().get(j).getCardNumber()
                                        && ((WizardState) info).getCardsPlayed().get(i).getCardSuit() != trumpSuit) {
                                    cardToPlay = player.getCurrentHand().get(j);
                                    indexInHand = j;
                                    //break;
                                }
                            } else {
                                if (player.getCurrentHand().get(j).getCardNumber() < wizardValue
                                        && player.getCurrentHand().get(j)!= null) {
                                    cardToPlay = player.getCurrentHand().get(j);
                                    wizardValue = player.getCurrentHand().get(j).getCardNumber();
                                }
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
