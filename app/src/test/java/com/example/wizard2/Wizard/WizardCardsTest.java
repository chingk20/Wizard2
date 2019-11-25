package com.example.wizard2.Wizard;

import org.junit.Test;

import static org.junit.Assert.*;

public class WizardCardsTest {

    @Test
    public void getCardValue(){
        //Keisha wrote this test
        WizardCards card1 = new WizardCards("heart", 5);
        assertEquals(5, card1.getCardValue());
        assertFalse(9==card1.getCardValue());
        WizardCards card2 = new WizardCards("heart", 8);
        assertEquals(8, card2.getCardValue());
        assertFalse(12==card2.getCardValue());
    }

    @Test
    public void getCardSuit(){
        //Keisha wrote this test
        WizardCards card1 = new WizardCards("heart", 5);
        assertEquals("heart", card1.getCardSuit());
        assertFalse("spade" == card1.getCardSuit());
        WizardCards card2 = new WizardCards("club", 8);
        assertEquals("club", card2.getCardSuit());
        assertFalse( "diamond" == card2.getCardSuit());
    }

    @Test
    public void getCardNumber(){
        //Keisha wrote this test
        WizardCards card1 = new WizardCards("heart", 5);
        assertEquals(5, card1.getCardNumber());
        assertFalse(7 == card1.getCardNumber());
        WizardCards card2 = new WizardCards("club", 0);
        assertEquals(0, card2.getCardNumber());
        assertFalse( 9 == card2.getCardNumber());
    }
}
