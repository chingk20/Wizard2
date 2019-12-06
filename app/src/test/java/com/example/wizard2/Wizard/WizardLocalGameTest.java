package com.example.wizard2.Wizard;

import org.junit.Test;

import static org.junit.Assert.*;

public class WizardLocalGameTest {

    //Taylor wrote this test
    @Test
    public void checkIfGameOver() {
        WizardLocalGame testLocal = new WizardLocalGame();

        assertEquals(null, testLocal.checkIfGameOver());

        testLocal.gameOver = true;
        testLocal.state.getPlayerInfo(0).setPlayerScore(10000);
        assertEquals("Player 1 is the winner", testLocal.checkIfGameOver());
    }

    //Taylor wrote this test
    @Test
    public void canMove() {
        WizardLocalGame testLocal = new WizardLocalGame();

        testLocal.state.setPlayerTurn(2);
        assertFalse(testLocal.canMove(0));
        assertTrue(testLocal.canMove(2));

    }

    @Test
    public void makeMove() {
    }

    //Taylor wrote this test
    @Test
    public void resetTrick() {
        WizardLocalGame testLocal = new WizardLocalGame();

        assertEquals(testLocal.state.getPlayerTurn(), 0);

        WizardCards card1 = new WizardCards("heart", 2);
        testLocal.state.cardsPlayed.set(0, card1);
        WizardCards card2 = new WizardCards("diamond", 7);
        testLocal.state.cardsPlayed.set(1, card2);
        WizardCards card3 = new WizardCards("spade", 3);
        testLocal.state.cardsPlayed.set(2, card3);
        WizardCards card4 = new WizardCards("club", 14);
        testLocal.state.cardsPlayed.set(3, card4);
        testLocal.state.setTrumpSuit("spade");

        testLocal.resetTrick();
        assertEquals(testLocal.state.getPlayerTurn(), 2);
    }
}