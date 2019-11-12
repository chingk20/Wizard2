package com.example.wizard2.Wizard;

public class WizardCards{
    private String cardSuit;
    private int cardNumber;
    private int cardValue;
    private String trumpCard;

    public WizardCards(String mySuit, int myNumber){
        this.cardSuit = mySuit;
        this.cardNumber = myNumber;

        if(mySuit == trumpCard){
            this.cardValue = this.cardNumber * 10;
        }
        else{
            this.cardValue = this.cardNumber;
        }
    }

    public int getCardValue(){
        return this.cardValue;
    }

    public String getCardSuit(){
        return this.cardSuit;
    }

    public int getCardNumber(){
        return this.cardNumber;
    }

    public void setTrumpCard( String newTrump ) {trumpCard = newTrump;}
}
