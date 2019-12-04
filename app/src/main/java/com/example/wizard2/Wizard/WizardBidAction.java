package com.example.wizard2.Wizard;

import com.example.wizard2.GameFramework.GamePlayer;
import com.example.wizard2.GameFramework.actionMessage.GameAction;

 /*  WizardBidAction:
  *  Takes in the bid number the player want to bid
  */

public class WizardBidAction extends GameAction {

    private static final long serialVersionUID = -2242980258970485343L;

    protected int bidNum;

    public WizardBidAction(GamePlayer player, int myBid){
        super(player);
        this.bidNum = myBid;
    }

    public int getBidNum(){return bidNum;}
}
