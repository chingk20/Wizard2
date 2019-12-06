package com.example.wizard2.Wizard;

import com.example.wizard2.GameFramework.GamePlayer;
import com.example.wizard2.GameFramework.actionMessage.GameAction;

import java.io.Serializable;

/**
 * WizardBidAction: This class represents a bid action. It doesn't do the bidding action,
 * it just represents a bid action object
 *
 * player: keeps track of the player that is making the action
 * myBid: the (integer) bid the player is making
 */

public class WizardBidAction extends GameAction implements Serializable {

    private static final long serialVersionUID = -2242980258970485343L;

    protected int bidNum;

    /**
     * Constructor for the WizardBidAction.
     */
    public WizardBidAction(GamePlayer player, int myBid){
        super(player);
        this.bidNum = myBid;
    }

    public int getBidNum(){return bidNum;}
}
