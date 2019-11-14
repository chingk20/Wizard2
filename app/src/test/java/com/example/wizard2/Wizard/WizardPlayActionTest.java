package com.example.wizard2.Wizard;

import com.example.wizard2.GameFramework.GameMainActivity;
import com.example.wizard2.GameFramework.GamePlayer;
import com.example.wizard2.GameFramework.infoMessage.GameInfo;

import org.junit.Test;

import static org.junit.Assert.*;

public class WizardPlayActionTest {

    @Test
    public void getCardToPlay() {
        //Kori wrote this test
        WizardCards testCard = new WizardCards("diamonds", 12);
        WizardPlayer testPlayer = new WizardPlayer(0, "Bart");
        WizardState testState = new WizardState();
        WizardLocalGame localTest = new WizardLocalGame();
        GamePlayer testGamer = new GamePlayer() {
            @Override
            public void gameSetAsGui(GameMainActivity activity) {

            }

            @Override
            public void setAsGui(GameMainActivity activity) {

            }

            @Override
            public void sendInfo(GameInfo info) {

            }

            @Override
            public void start() {

            }

            @Override
            public boolean requiresGui() {
                return false;
            }

            @Override
            public boolean supportsGui() {
                return false;
            }
        };
        WizardPlayAction testPlay = new WizardPlayAction(testGamer, testCard);
        testPlayer.addCardtoHand(testCard);
        assertTrue(testPlayer.currentHand.contains(testCard));
        assertFalse(testState.cardsPlayed.contains(testCard));
        assertTrue(localTest.canMove(0));
        testPlay.cardToPlay = testCard;
        testPlay.getCardToPlay();
        localTest.makeMove(testPlay);
//        assertFalse(testPlayer.currentHand.contains(testCard));
//        assertTrue(testState.cardsPlayed.contains(testCard));
    }
}