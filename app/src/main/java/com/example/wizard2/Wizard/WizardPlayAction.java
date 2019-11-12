package com.example.wizard2.Wizard;

import com.example.wizard2.GameFramework.GamePlayer;
import com.example.wizard2.GameFramework.actionMessage.GameAction;

public class WizardPlayAction extends GameAction {
    protected WizardCards cardToPlay;

    public WizardPlayAction(GamePlayer player, WizardCards myCard){
        super(player);
        this.cardToPlay = myCard;
    }

    public WizardCards getCardToPlay() {
        return cardToPlay;
    }

}
