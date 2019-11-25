package com.example.wizard2.Wizard;

import org.junit.Test;

import static org.junit.Assert.*;

public class WizardStateTest {

    @Test
    public void makeCards() {
        //Keisha wrote this test
        WizardState testState = new WizardState();
        testState.deck.clear();
        testState.makeCards();
        assertEquals(60, testState.deck.size());
    }

    @Test
    public void dealDeck() {
        //Keisha wrote this test
        WizardState testState = new WizardState();
        assertEquals(1, testState.player0.currentHand.size());
        assertEquals(1, testState.player1.currentHand.size());
        assertEquals(1, testState.player2.currentHand.size());
        assertEquals(1, testState.player3.currentHand.size());
        testState.dealDeck(3);
        assertEquals(4, testState.player0.currentHand.size());
        assertEquals(4, testState.player1.currentHand.size());
        assertEquals(4, testState.player2.currentHand.size());
        assertEquals(4, testState.player3.currentHand.size());
        testState.dealDeck(7);
        assertEquals(11, testState.player0.currentHand.size());
        assertEquals(11, testState.player1.currentHand.size());
        assertEquals(11, testState.player2.currentHand.size());
        assertEquals(11, testState.player3.currentHand.size());
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
        //Keisha wrote this test
        WizardState testState = new WizardState();
        WizardCards myCard = new WizardCards("club", 4);
        testState.setTrumpCard(myCard);
        assertEquals(myCard,testState.getTrumpCard());
        WizardCards myCard1 = new WizardCards("heart", 7);
        assertFalse(myCard1==testState.getTrumpCard());
        testState.setTrumpCard(myCard1);
        assertEquals(myCard1,testState.getTrumpCard());
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
        //Keisha wrote this test
        WizardState testState = new WizardState();
        testState.setGameStage(3);
        assertEquals(3, testState.getGameStage());
        testState.setGameStage(7);
        assertEquals(7, testState.getGameStage());
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
        //Keisha wrote this test
        WizardState testState = new WizardState();
        testState.setPlayerBids(4,0);
        testState.setPlayerBids(8,1);
        testState.setPlayerBids(1,2);
        testState.setPlayerBids(2,3);
        assertEquals(4,(int)testState.getPlayerBids().get(0));
        assertEquals(8,(int)testState.getPlayerBids().get(1));
        assertEquals(1,(int)testState.getPlayerBids().get(2));
        assertEquals(2,(int)testState.getPlayerBids().get(3));
        assertFalse(7==(int)testState.getPlayerBids().get(0));
        assertFalse(3==(int)testState.getPlayerBids().get(1));
        assertFalse(4==(int)testState.getPlayerBids().get(2));
        assertFalse(9==(int)testState.getPlayerBids().get(3));
    }

    @Test
    public void getCardsPlayed() {
        //Keisha wrote this test
        WizardState testState = new WizardState();
        WizardCards testCard1 = new WizardCards("spade", 12);
        WizardCards testCard2 = new WizardCards("club", 5);
        WizardCards testCard3 = new WizardCards("diamond", 8);
        WizardCards testCard4 = new WizardCards("spade", 0);
        testState.setCardsPlayed(testCard1, 0);
        assertEquals(testCard1,testState.getCardsPlayed().get(0));
        testState.setCardsPlayed(testCard2, 1);
        assertEquals(testCard2,testState.getCardsPlayed().get(1));
        testState.setCardsPlayed(testCard3, 2);
        assertEquals(testCard3,testState.getCardsPlayed().get(2));
        testState.setCardsPlayed(testCard4, 3);
        assertEquals(testCard4,testState.getCardsPlayed().get(3));
    }

    @Test
    public void setCardsPlayed() {
        //Keisha wrote this test
        WizardState testState = new WizardState();
        WizardCards testCard1 = new WizardCards("heart", 4);
        WizardCards testCard2 = new WizardCards("club", 8);
        WizardCards testCard3 = new WizardCards("diamond", 15);
        WizardCards testCard4 = new WizardCards("spade", 9);
        testState.setCardsPlayed(testCard1, 0);
        assertEquals(testCard1,testState.getCardsPlayed().get(0));
        testState.setCardsPlayed(testCard2, 1);
        assertEquals(testCard2,testState.getCardsPlayed().get(1));
        testState.setCardsPlayed(testCard3, 2);
        assertEquals(testCard3,testState.getCardsPlayed().get(2));
        testState.setCardsPlayed(testCard4, 3);
        assertEquals(testCard4,testState.getCardsPlayed().get(3));
    }

    @Test
    public void setPlayerBids() {
        //Keisha wrote this test
        WizardState testState = new WizardState();
        testState.setPlayerBids(2,0);
        testState.setPlayerBids(4,1);
        testState.setPlayerBids(7,2);
        testState.setPlayerBids(0,3);
        assertEquals(2,(int)testState.getPlayerBids().get(0));
        assertEquals(4,(int)testState.getPlayerBids().get(1));
        assertEquals(7,(int)testState.getPlayerBids().get(2));
        assertEquals(0,(int)testState.getPlayerBids().get(3));
        assertFalse(3==(int)testState.getPlayerBids().get(0));
        assertFalse(8==(int)testState.getPlayerBids().get(1));
        assertFalse(4==(int)testState.getPlayerBids().get(2));
        assertFalse(2==(int)testState.getPlayerBids().get(3));
    }

    @Test
    public void setPlayerBidsWon(){
        //Keisha wrote this test
        WizardState testState = new WizardState();
        testState.setPlayerBidsWon(2,0);
        testState.setPlayerBidsWon(4,1);
        testState.setPlayerBidsWon(7,2);
        testState.setPlayerBidsWon(0,3);
        assertEquals(2,(int)testState.getPlayerBidsWon().get(0));
        assertEquals(4,(int)testState.getPlayerBidsWon().get(1));
        assertEquals(7,(int)testState.getPlayerBidsWon().get(2));
        assertEquals(0,(int)testState.getPlayerBidsWon().get(3));
        assertFalse(3==(int)testState.getPlayerBidsWon().get(0));
        assertFalse(8==(int)testState.getPlayerBidsWon().get(1));
        assertFalse(4==(int)testState.getPlayerBidsWon().get(2));
        assertFalse(2==(int)testState.getPlayerBidsWon().get(3));
    }

    @Test
    public void getPlayerBidsWon(){
        //Keisha wrote this test
        WizardState testState = new WizardState();
        testState.setPlayerBidsWon(1,0);
        testState.setPlayerBidsWon(6,1);
        testState.setPlayerBidsWon(9,2);
        testState.setPlayerBidsWon(14,3);
        assertEquals(1,(int)testState.getPlayerBidsWon().get(0));
        assertEquals(6,(int)testState.getPlayerBidsWon().get(1));
        assertEquals(9,(int)testState.getPlayerBidsWon().get(2));
        assertEquals(14,(int)testState.getPlayerBidsWon().get(3));
        assertFalse(3==(int)testState.getPlayerBidsWon().get(0));
        assertFalse(8==(int)testState.getPlayerBidsWon().get(1));
        assertFalse(4==(int)testState.getPlayerBidsWon().get(2));
        assertFalse(2==(int)testState.getPlayerBidsWon().get(3));
    }

    @Test
    public void setTrumpSuit(){
        //Keisha wrote this test
        WizardState testState = new WizardState();
        WizardCards testCard1 = new WizardCards("spade", 12);
        testState.setTrumpSuit("heart");
        assertEquals("heart", testState.getTrumpSuit());
        testState.setTrumpSuit(testCard1.getCardSuit());
        assertEquals("spade", testState.getTrumpSuit());
    }

    @Test
    public void getTrumpSuit(){
        //Keisha wrote this test
        WizardState testState = new WizardState();
        WizardCards testCard1 = new WizardCards("club", 12);
        testState.setTrumpSuit("diamond");
        assertEquals("diamond", testState.getTrumpSuit());
        testState.setTrumpSuit(testCard1.getCardSuit());
        assertEquals("club", testState.getTrumpSuit());
    }
}