package com.example.wizard2.Wizard;

import org.junit.Test;

import static org.junit.Assert.*;

public class WizardPlayerTest {

    @Test
    public void addCardtoHand(WizardCards cardToAdd){
        //Keisha wrote this test
        WizardPlayer player = new WizardPlayer(0, "player 0");
        assertEquals(0, player.currentHand.size());
        WizardCards hearttwo = new WizardCards("heart", 2);
        player.addCardtoHand(hearttwo);
        assertEquals(1, player.currentHand.size());
        player.addCardtoHand(hearttwo);
        assertEquals(2, player.currentHand.size());
    }

    @Test
    public void setRunningTotal(int bidNum, int bidWon){
        //Keisha wrote this test
        WizardPlayer player = new WizardPlayer(0, "player 0");
        assertEquals(0, player.getRunningTotal());
        player.setRunningTotal(3,5);
        assertEquals(-20, player.getRunningTotal());
        player.setRunningTotal(7,1);
        assertEquals(-60, player.getRunningTotal());
        player.setRunningTotal(0,0);
        assertEquals(10, player.getRunningTotal());
        player.setRunningTotal(2,2);
        assertEquals(50, player.getRunningTotal());
    }

    @Test
    public void setPlayerScore(int runningTotal) {
        //Keisha wrote this test
        WizardPlayer player = new WizardPlayer(0, "player 0");
        assertEquals(0, player.getPlayerScore());
        player.setRunningTotal(7,1);
        player.setPlayerScore(runningTotal);
        assertEquals(-60, player.getPlayerScore());
        player.setRunningTotal(3,3);
        player.setPlayerScore(runningTotal);
        assertEquals(10, player.getPlayerScore());
    }
}
