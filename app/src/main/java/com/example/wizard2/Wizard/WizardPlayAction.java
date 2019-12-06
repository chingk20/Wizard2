package com.example.wizard2.Wizard;

import com.example.wizard2.GameFramework.GamePlayer;
import com.example.wizard2.GameFramework.actionMessage.GameAction;
import java.io.Serializable;
/**
 * WizardPlayAction: This class represents a card playing action. It doesn't do the playing action,
 * it just represents a play action object
 *
 * player : keeps track of the player that is making the action
 * myCard : the WizardCard the player is playing
 * myPlace : the index of myCard in the player's hand
 */

public class WizardPlayAction extends GameAction implements Serializable {

    private static final long serialVersionUID = -2242980258970485343L;
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
