package com.example.wizard2.Wizard;

import com.example.wizard2.GameFramework.GameMainActivity;
import com.example.wizard2.GameFramework.GamePlayer;
import com.example.wizard2.GameFramework.infoMessage.GameInfo;

import org.junit.Test;

import static org.junit.Assert.*;

public class WizardBidActionTest {

    @Test
    public void getBidNum() {
        //Kori wrote this test
        WizardState testState = new WizardState();
        WizardPlayer testPlayer = new WizardPlayer(1, "Kirk");
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
        WizardBidAction testBid = new WizardBidAction(testGamer, 14);
        testState.setRoundNum(3);
        assertFalse(testBid.getBidNum() <= testState.getRoundNum());
        assertTrue(testPlayer.getBidNum() <= testState.getRoundNum());
        assertFalse(testPlayer.getBidNum() > testState.getRoundNum());
        testState.setRoundNum(13);
        assertTrue(testPlayer.getBidNum() <= 13);
        assertFalse(testPlayer.getBidNum() > 13);
    }
}