package com.example.wizard2.Wizard;

import org.junit.Test;

import static org.junit.Assert.*;

public class WizardBidActionTest {

    @Test
    public void getBidNum() {
        WizardState testState = new WizardState();
        WizardPlayer testPlayer = new WizardPlayer(1, "Kirk");
        assertTrue(testPlayer.getBidNum() <= testState.getRoundNum());
        assertFalse(testPlayer.getBidNum() > testState.getRoundNum());
    }
}