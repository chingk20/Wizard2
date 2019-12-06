package com.example.wizard2.Wizard;

import com.example.wizard2.GameFramework.GamePlayer;
import com.example.wizard2.GameFramework.actionMessage.GameAction;

/**
 * WizardPlayAction: This class represents a card playing action. It doesn't do the playing action,
 * it just represents a play action object
 *
 * @param player keeps track of the player that is making the action
 * @param myCard the WizardCard the player is playing
 * @param myPlace the index of myCard in the player's hand
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
