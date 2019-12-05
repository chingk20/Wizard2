package com.example.wizard2.Wizard;

import android.util.Log;

import java.io.Serializable;

/*  WizardCards:
    Creates all the cards needed to play Wizard. Cards created equal
    52 standard cards in a deck: Ace through King, four suits, plus
    4 jesters and 4 wizards.
 */

public class WizardCards implements Serializable {
    private String cardSuit;
    private int cardNumber;
    private int cardValue;
    private String trumpCard;

    public WizardCards(String mySuit, int myNumber){
        this.cardSuit = mySuit;
        this.cardNumber = myNumber;

        //sets value for each card
        if(mySuit == trumpCard){
            this.cardValue = this.cardNumber * 10;
        }
        else{
            this.cardValue = this.cardNumber;
        }
    }

    public int getCardValue(){
        return this.cardValue; }

    public String getCardSuit(){
        return this.cardSuit;
    }

    public int getCardNumber(){
        return this.cardNumber;
    }

    public void setTrumpCard( String newTrump ) {trumpCard = newTrump;}
}
