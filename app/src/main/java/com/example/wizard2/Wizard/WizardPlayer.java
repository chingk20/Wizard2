package com.example.wizard2.Wizard;

import java.io.Serializable;
import java.util.ArrayList;

/*  WizardPlayer:
    Creates a player to play the game. Keeps track of their ID, score, name, and bid number
 */

public class WizardPlayer implements Serializable {
    private int playerID;
    private int playerScore;
    private int runningTotal;
    private String playerName;
    private int bidNum;
    private int bidNumWon;
    public ArrayList<WizardCards> currentHand = new ArrayList<WizardCards>();

    /**
     * Constructor for the WizardPlayer.
     *
     * @param playerID
     *      what player number it is (0-3)
     * @param playerName
     *      players name
     *
     */
    public WizardPlayer(int playerID, String playerName){
        this.playerID = playerID;
        this.playerName = playerName;
        this.playerScore = 0;
        this.runningTotal = 0;
        this.bidNum = 0;
        this.bidNumWon = 0;
    }

    //adds a card to a player's hand
    public void addCardtoHand(WizardCards cardToAdd){
        currentHand.add(0, cardToAdd);
    }

    //calculates score for round
    public void setRunningTotal(int bidNum, int bidWon){
        if(bidNum==bidWon){
            this.runningTotal = 20 + (bidWon * 10);
        }
        else if(bidNum!=bidWon)
        {
            this.runningTotal = java.lang.Math.abs(bidNum - bidWon) * (-10);
        }
    }

    /**
     * Setters and getters for obtaining and setting information
     */

    public void setBidNum(int bidNum) {this.bidNum=bidNum;}

    public void setBidNumWon(int bidNumWon) {this.bidNumWon=bidNumWon;}

    public void setPlayerScore(int runningTotal) { this.playerScore = playerScore + runningTotal; }

    public int getBidNum() { return bidNum; }

    public int getRunningTotal()
    {
        return runningTotal;
    }

    public int getPlayerScore() { return playerScore; }

    public ArrayList<WizardCards> getCurrentHand() {return currentHand;}
}
