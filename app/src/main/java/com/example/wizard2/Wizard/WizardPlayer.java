package com.example.wizard2.Wizard;

import java.util.ArrayList;

public class WizardPlayer {
    private int playerID;
    private int playerScore;
    private int runningTotal;
    private String playerName;
    private int bidNum;
    public ArrayList<WizardCards> currentHand = new ArrayList<WizardCards>();

    public WizardPlayer(int playerID, String playerName){
        this.playerID = playerID;
        this.playerName = playerName;
        this.playerScore = 0;
        runningTotal = 0;
        bidNum = 0;
    }

    public void addCardtoHand(WizardCards cardToAdd){
        currentHand.add(cardToAdd);
    }

    public void setRunningTotal(int bidNum, int bidWon){
        if(bidNum==bidWon){
            this.runningTotal = 20 + (bidWon * 10);
        }
        else{
            this.runningTotal = java.lang.Math.abs(bidNum - bidWon) * (-10);
        }
    }

    public void setPlayerScore(int runningTotal) { this.playerScore = playerScore + getRunningTotal(); }


    public int getBidNum() { return bidNum; }

    public int getRunningTotal() { return runningTotal; }

    public int getPlayerScore() { return playerScore; }


    public ArrayList<WizardCards> getCurrentHand() {return currentHand;}
}
