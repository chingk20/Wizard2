package com.example.wizard2.Wizard;

import android.util.Log;


/**
 * WizardCards: This class represents a single card
 *
 * @param mySuit the (string) suit of the card
 * @param myNumber the (integer) number of the card (Joker is 0, Jack-King is 11-13,
 *                 Ace is 14, Wizard is 15)
 */

public class WizardCards{
    private String cardSuit;
    private int cardNumber;
    private int cardValue;
    private String trumpCard;

    public WizardCards(String mySuit, int myNumber){
        this.cardSuit = mySuit;
        this.cardNumber = myNumber;

        //sets value for each card
        //if the card's suit matches the trump card's suit, the card's
        //value is multiplied by 10, so that it is guaranteed to be
        //more valuable than any other card of a different suit
        if(mySuit == trumpCard){
            this.cardValue = this.cardNumber * 10;
        }
        else{
            this.cardValue = this.cardNumber;
        }
    }

    public int getCardValue(){ return this.cardValue; }

    public String getCardSuit(){
        return this.cardSuit;
    }

    public int getCardNumber(){
        return this.cardNumber;
    }

    public void setTrumpCard(String newTrump) {trumpCard = newTrump;}
}
