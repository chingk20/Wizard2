package com.example.wizard2.Wizard;

import org.junit.Test;

import static org.junit.Assert.*;

public class WizardPlayActionTest {

    @Test
    public void getCardToPlay() {
        WizardCards testCard = new WizardCards("diamonds", 12);
        WizardPlayer testPlayer = new WizardPlayer(0, "Bart");
        WizardState testState = new WizardState();
        testPlayer.addCardtoHand(testCard);
        assertTrue(testPlayer.currentHand.contains(testCard));
        assertFalse(testState.cardsPlayed.contains(testCard));
        //testPlayer.addCardtoHand(testCard);
        //assertFalse(testPlayer.currentHand.contains(testCard));
        //assertTrue(testState.cardsPlayed.contains(testCard));
    }
}