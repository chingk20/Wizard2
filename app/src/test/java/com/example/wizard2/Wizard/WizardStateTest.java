package com.example.wizard2.Wizard;

import org.junit.Test;

import static org.junit.Assert.*;

public class WizardStateTest {

    @Test
    public void makeCards() {

    }

    @Test
    public void dealDeck() {

    }

    @Test
    public void getPlayerTurn() {
        WizardState testState = new WizardState();
        assertTrue(testState.getPlayerTurn() <= 4);
        assertFalse(testState.getPlayerTurn() > 4);
        assertFalse(testState.getPlayerTurn() == 5);
    }

    @Test
    public void getGameStage() {

    }

    @Test
    public void getTrumpCard() {

    }

    @Test
    public void getRoundNum() {
        WizardState testState = new WizardState();
        assertFalse(testState.getRoundNum() > 15);
        assertTrue(testState.getRoundNum() <= 15);
    }

    @Test
    public void setPlayerTurn() {
        WizardState testState = new WizardState();
        testState.setPlayerTurn(1);
        assertTrue(testState.getPlayerTurn() == 1);
        assertFalse(testState.getPlayerTurn() == 2);
        testState.playerTurn ++;
        assertTrue(testState.getPlayerTurn() == 2);


    }

    @Test
    public void setGameStage() {

    }

    @Test
    public void setTrumpCard() {
        WizardState testState = new WizardState();
        WizardCards testCard1 = new WizardCards("hearts", 15);
        WizardCards testCard2 = new WizardCards("spades", 15);
        WizardCards testCard3 = new WizardCards("diamonds", 0);
        testState.setTrumpCard(testCard1);
        assertFalse(testState.getTrumpCard() == testCard2);
        assertFalse(testState.getTrumpCard() == testCard3);
        assertTrue(testState.getTrumpCard() == testCard1);
    }

    @Test
    public void setRoundNum() {
        WizardState testState = new WizardState();
        testState.setRoundNum(4);
        assertTrue(testState.roundNum == 4);
        assertFalse(testState.roundNum == 7);
        testState.roundNum ++;
        assertTrue(testState.roundNum == 5);
    }

    @Test
    public void getPlayerInfo() {

    }

    @Test
    public void getPlayerBids() {

    }

    @Test
    public void getCardsPlayed() {

    }

    @Test
    public void setPlayerBids() {

    }
}