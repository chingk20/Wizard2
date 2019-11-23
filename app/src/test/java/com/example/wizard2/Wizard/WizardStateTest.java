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
    public void calculateWinner(){
        //Keisha wrote this test
        WizardState testState = new WizardState();
        for(int i=0; i<4; i++) {
            testState.setPlayerBidsWon(0, i);
        }
        WizardCards card1 = new WizardCards("heart", 2);
        testState.cardsPlayed.set(0, card1);
        WizardCards card2 = new WizardCards("diamond", 7);
        testState.cardsPlayed.set(1, card2);
        WizardCards card3 = new WizardCards("spade", 3);
        testState.cardsPlayed.set(2, card3);
        WizardCards card4 = new WizardCards("club", 14);
        testState.cardsPlayed.set(3, card4);
        testState.setTrumpSuit("spade");
        testState.calculateWinner();
        assertEquals(0, (int)testState.getPlayerBidsWon().get(0));
        assertEquals(0, (int)testState.getPlayerBidsWon().get(1));
        assertEquals(1, (int)testState.getPlayerBidsWon().get(2));
        assertEquals(0, (int)testState.getPlayerBidsWon().get(3));
    }

    @Test
    public void resetImage(){
        //Keisha wrote this test
        WizardState testState = new WizardState();
        WizardCards card = new WizardCards("heart", 2);
        for(int i=0; i<4; i++){
        testState.cardsPlayed.set(i, card);
        }
        assertEquals(card, testState.getCardsPlayed().get(0));
        assertEquals(card, testState.getCardsPlayed().get(1));
        assertEquals(card, testState.getCardsPlayed().get(2));
        assertEquals(card, testState.getCardsPlayed().get(3));
        testState.resetImage();
        assertEquals(null, testState.getCardsPlayed().get(0));
        assertEquals(null, testState.getCardsPlayed().get(1));
        assertEquals(null, testState.getCardsPlayed().get(2));
        assertEquals(null, testState.getCardsPlayed().get(3));
    }

    @Test
    public void calculateScores(){
        //Keisha wrote this test
        WizardState testState = new WizardState();
        testState.player0.setBidNum(2);
        testState.player1.setBidNum(4);
        testState.player2.setBidNum(1);
        testState.player3.setBidNum(2);
        testState.setPlayerBidsWon(7, 0);
        testState.setPlayerBidsWon(6, 1);
        testState.setPlayerBidsWon(1, 2);
        testState.setPlayerBidsWon(2, 3);
        testState.player0.setPlayerScore(0);
        testState.player1.setPlayerScore(0);
        testState.player2.setPlayerScore(0);
        testState.player3.setPlayerScore(0);
        testState.calculateScores();
        assertEquals(-50,testState.player0.getPlayerScore());
        assertEquals(-20,testState.player1.getPlayerScore());
        assertEquals(30,testState.player2.getPlayerScore());
        assertEquals(40,testState.player3.getPlayerScore());
    }

    @Test
    public void getPlayerTurn() {
        //Kori wrote this test
        WizardState testState = new WizardState();
        assertTrue(testState.getPlayerTurn() <= 4);
        assertFalse(testState.getPlayerTurn() > 4);
        assertFalse(testState.getPlayerTurn() == 5);
    }

    @Test
    public void getGameStage() {
        //Kori wrote this test
        WizardState testState = new WizardState();
        testState.setGameStage(13);
        assertFalse(testState.getGameStage() == 4);
        assertTrue(testState.getGameStage() == 13);
        testState.setGameStage(testState.getGameStage()+1) ;
        assertTrue(testState.getGameStage() == 14);
    }

    @Test
    public void getTrumpCard() {

    }

    @Test
    public void getRoundNum() {
        //Kori wrote this test
        WizardState testState = new WizardState();
        assertFalse(testState.getRoundNum() > 15);
        assertTrue(testState.getRoundNum() <= 15);
    }

    @Test
    public void setPlayerTurn() {
        //Kori wrote this test
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
        //Kori wrote this test
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
        //Kori wrote this test
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