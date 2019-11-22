package com.example.wizard2.Wizard;

import com.example.wizard2.GameFramework.GamePlayer;
import com.example.wizard2.GameFramework.actionMessage.GameAction;

/*  WizardPlayAction:
    Takes in player, the card they want to play, and the place in their hand the card is
 */

public class WizardPlayAction extends GameAction {
    public WizardCards cardToPlay;
    public int placeInHand;


    public WizardPlayAction(GamePlayer player, WizardCards myCard, int myPlace){
        super(player);
        this.cardToPlay = myCard;
        this.placeInHand = myPlace;
    }

    public int getPlaceInHand() {
        return placeInHand;
    }

    public WizardCards getCardToPlay() {
        return cardToPlay;
    }

}
