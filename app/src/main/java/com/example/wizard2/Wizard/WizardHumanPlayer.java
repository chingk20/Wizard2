package com.example.wizard2.Wizard;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wizard2.GameFramework.GameHumanPlayer;
import com.example.wizard2.GameFramework.GameMainActivity;
import com.example.wizard2.GameFramework.infoMessage.GameInfo;
import com.example.wizard2.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * WizardHumanPlayer: This class deals with all human interactions with the gui and
 * updates gui components
 *
 * sets card image views and sends play and bid actions
 */

public class WizardHumanPlayer extends GameHumanPlayer implements View.OnTouchListener, View.OnClickListener, Serializable {

    private WizardCards cardToPlay; //card the user touches to play
    private int bidNum = 0; //initializes bid to 0 before changed
    private int roundNum;

    //ACTIONS
    WizardPlayAction myPlay;
    WizardBidAction myBid;

    private WizardState state = new WizardState();

    private ArrayList<ImageView> guiCards = new ArrayList<ImageView>(); //array of cards (image views)

    // the current activity
    private GameMainActivity myActivity;

    //GUI
    private TextView player1Score = null;   //players info (bid, bids won, scores)
    private TextView player2Score = null;
    private TextView player3Score = null;
    private TextView player4Score = null;
    private TextView roundText = null;  //current round number
    private ImageView card1 = null;         //card1-card15 are user's cards in hand
    private ImageView card2 = null;
    private ImageView card3 = null;
    private ImageView card4 = null;
    private ImageView card5 = null;
    private ImageView card6 = null;
    private ImageView card7 = null;
    private ImageView card8 = null;
    private ImageView card9 = null;
    private ImageView card10 = null;
    private ImageView card11 = null;
    private ImageView card12 = null;
    private ImageView card13 = null;
    private ImageView card14 = null;
    private ImageView card15 = null;
    private ImageView card1Played = null;   //card1Played-card4Played are cards played in the middle
    private ImageView card2Played = null;
    private ImageView card3Played = null;
    private ImageView card4Played = null;
    private ImageView cardTrump = null;     //trump card
    private Button bidSubmitButton = null;
    private TextView bidText = null; //displays current bid number
    private Button bidPlus = null; //bidPlus and bidMinus allow bidNum to be incremented and decremented
    private Button bidMinus = null;
    private ImageButton gButton; //buttons to change house
    private ImageButton sButton;
    private ImageButton hButton;
    private ImageButton rButton;
    private SurfaceView mySurface;
    private Button quitButton = null;
    private Button helpButton = null;
    private Button backToGameButton = null;
    private TextView gameStage = null;
    private TextView helpText = null;

    /**
     * constructor
     * @param name the player's name
     */
    public WizardHumanPlayer(String name) {
        super(name);
    }

    /**
     * returns the GUI's top view
     * @return the GUI's top view
     */
    @Override
    public View getTopView() {
        return null;
    }

    /**
     * Callback method, called when player gets a message
     * @param info the message
     */
    @Override
    public void receiveInfo(GameInfo info) {

        if (info instanceof WizardState) {
            state = (WizardState) info;

            /**
             External Citation
             Date: 7 November 2019
             Problem: Cards were not drawing properly
             Resource: Dr. Tibelhorn
             Solution: Correct for loops to draw cards, set visibility accordingly
             */

            //sets trump card image
            if(state.getRoundNum() < 15) {
                WizardCards trumpCard = ((WizardState) info).getTrumpCard();
                switch (trumpCard.getCardSuit()) {
                    case "diamond":
                        switch (trumpCard.getCardValue()) {
                            case 0:
                                cardTrump.setImageResource(R.drawable.jester_all);
                                break;
                            case 2:
                                cardTrump.setImageResource(R.drawable.two_diamond);
                                break;
                            case 3:
                                cardTrump.setImageResource(R.drawable.three_diamond);
                                break;
                            case 4:
                                cardTrump.setImageResource(R.drawable.four_diamond);
                                break;
                            case 5:
                                cardTrump.setImageResource(R.drawable.five_diamond);
                                break;
                            case 6:
                                cardTrump.setImageResource(R.drawable.six_diamond);
                                break;
                            case 7:
                                cardTrump.setImageResource(R.drawable.seven_diamond);
                                break;
                            case 8:
                                cardTrump.setImageResource(R.drawable.eight_diamond);
                                break;
                            case 9:
                                cardTrump.setImageResource(R.drawable.nine_diamond);
                                break;
                            case 10:
                                cardTrump.setImageResource(R.drawable.ten_diamond);
                                break;
                            case 11:
                                cardTrump.setImageResource(R.drawable.jack_diamond);
                                break;
                            case 12:
                                cardTrump.setImageResource(R.drawable.queen_diamond);
                                break;
                            case 13:
                                cardTrump.setImageResource(R.drawable.king_diamond);
                                break;
                            case 14:
                                cardTrump.setImageResource(R.drawable.ace_diamond);
                                break;
                            case 15:
                                cardTrump.setImageResource(R.drawable.wizard_huff);
                                break;
                        }
                        break;
                    case "heart":
                        switch (trumpCard.getCardValue()) {
                            case 0:
                                cardTrump.setImageResource(R.drawable.jester_all);
                                break;
                            case 2:
                                cardTrump.setImageResource(R.drawable.two_heart);
                                break;
                            case 3:
                                cardTrump.setImageResource(R.drawable.three_heart);
                                break;
                            case 4:
                                cardTrump.setImageResource(R.drawable.four_heart);
                                break;
                            case 5:
                                cardTrump.setImageResource(R.drawable.five_heart);
                                break;
                            case 6:
                                cardTrump.setImageResource(R.drawable.six_heart);
                                break;
                            case 7:
                                cardTrump.setImageResource(R.drawable.seven_heart);
                                break;
                            case 8:
                                cardTrump.setImageResource(R.drawable.eight_heart);
                                break;
                            case 9:
                                cardTrump.setImageResource(R.drawable.nine_heart);
                                break;
                            case 10:
                                cardTrump.setImageResource(R.drawable.ten_heart);
                                break;
                            case 11:
                                cardTrump.setImageResource(R.drawable.jack_heart);
                                break;
                            case 12:
                                cardTrump.setImageResource(R.drawable.queen_heart);
                                break;
                            case 13:
                                cardTrump.setImageResource(R.drawable.king_heart);
                                break;
                            case 14:
                                cardTrump.setImageResource(R.drawable.ace_heart);
                                break;
                            case 15:
                                cardTrump.setImageResource(R.drawable.wizard_sly);
                                break;
                        }
                        break;
                    case "spade":
                        switch (trumpCard.getCardValue()) {
                            case 0:
                                cardTrump.setImageResource(R.drawable.jester_all);
                                break;
                            case 2:
                                cardTrump.setImageResource(R.drawable.two_spade);
                                break;
                            case 3:
                                cardTrump.setImageResource(R.drawable.three_spade);
                                break;
                            case 4:
                                cardTrump.setImageResource(R.drawable.four_spade);
                                break;
                            case 5:
                                cardTrump.setImageResource(R.drawable.five_spade);
                                break;
                            case 6:
                                cardTrump.setImageResource(R.drawable.six_spade);
                                break;
                            case 7:
                                cardTrump.setImageResource(R.drawable.seven_spade);
                                break;
                            case 8:
                                cardTrump.setImageResource(R.drawable.eight_spade);
                                break;
                            case 9:
                                cardTrump.setImageResource(R.drawable.nine_spade);
                                break;
                            case 10:
                                cardTrump.setImageResource(R.drawable.ten_spade);
                                break;
                            case 11:
                                cardTrump.setImageResource(R.drawable.jack_spade);
                                break;
                            case 12:
                                cardTrump.setImageResource(R.drawable.queen_spade);
                                break;
                            case 13:
                                cardTrump.setImageResource(R.drawable.king_spade);
                                break;
                            case 14:
                                cardTrump.setImageResource(R.drawable.ace_spade);
                                break;
                            case 15:
                                cardTrump.setImageResource(R.drawable.wizard_griff);
                                break;
                        }
                        break;
                    case "club":
                        switch (trumpCard.getCardValue()) {
                            case 0:
                                cardTrump.setImageResource(R.drawable.jester_all);
                                break;
                            case 2:
                                cardTrump.setImageResource(R.drawable.two_club);
                                break;
                            case 3:
                                cardTrump.setImageResource(R.drawable.three_club);
                                break;
                            case 4:
                                cardTrump.setImageResource(R.drawable.four_club);
                                break;
                            case 5:
                                cardTrump.setImageResource(R.drawable.five_club);
                                break;
                            case 6:
                                cardTrump.setImageResource(R.drawable.six_club);
                                break;
                            case 7:
                                cardTrump.setImageResource(R.drawable.seven_club);
                                break;
                            case 8:
                                cardTrump.setImageResource(R.drawable.eight_club);
                                break;
                            case 9:
                                cardTrump.setImageResource(R.drawable.nine_club);
                                break;
                            case 10:
                                cardTrump.setImageResource(R.drawable.ten_club);
                                break;
                            case 11:
                                cardTrump.setImageResource(R.drawable.jack_club);
                                break;
                            case 12:
                                cardTrump.setImageResource(R.drawable.queen_club);
                                break;
                            case 13:
                                cardTrump.setImageResource(R.drawable.king_club);
                                break;
                            case 14:
                                cardTrump.setImageResource(R.drawable.ace_club);
                                break;
                            case 15:
                                cardTrump.setImageResource(R.drawable.wizard_raven);
                                break;
                        }
                }
            }
            else if(state.getRoundNum() == 15){
                cardTrump.setVisibility(View.INVISIBLE);
            }

            //sets image to cards in human hand
                int i = 0;

                for (; i < state.roundNum; i++) {
                    WizardCards card = ((WizardState) info).getPlayerInfo(playerNum).getCurrentHand().get(i);
                    guiCards.get(i).setVisibility(View.VISIBLE);

                    //checks if the card is null or not
                    if (card == null) {
                    guiCards.get(i).setVisibility(View.INVISIBLE);
                    }

                    //if not null then set image
                    if (card != null) {
                    switch (card.getCardSuit()) {
                        case "diamond":
                            switch (card.getCardValue()) {
                                case 0:
                                    guiCards.get(i).setImageResource(R.drawable.jester_all);
                                    break;
                                case 2:
                                    guiCards.get(i).setImageResource(R.drawable.two_diamond);
                                    break;
                                case 3:
                                    guiCards.get(i).setImageResource(R.drawable.three_diamond);
                                    break;
                                case 4:
                                    guiCards.get(i).setImageResource(R.drawable.four_diamond);
                                    break;
                                case 5:
                                    guiCards.get(i).setImageResource(R.drawable.five_diamond);
                                    break;
                                case 6:
                                    guiCards.get(i).setImageResource(R.drawable.six_diamond);
                                    break;
                                case 7:
                                    guiCards.get(i).setImageResource(R.drawable.seven_diamond);
                                    break;
                                case 8:
                                    guiCards.get(i).setImageResource(R.drawable.eight_diamond);
                                    break;
                                case 9:
                                    guiCards.get(i).setImageResource(R.drawable.nine_diamond);
                                    break;
                                case 10:
                                    guiCards.get(i).setImageResource(R.drawable.ten_diamond);
                                    break;
                                case 11:
                                    guiCards.get(i).setImageResource(R.drawable.jack_diamond);
                                    break;
                                case 12:
                                    guiCards.get(i).setImageResource(R.drawable.queen_diamond);
                                    break;
                                case 13:
                                    guiCards.get(i).setImageResource(R.drawable.king_diamond);
                                    break;
                                case 14:
                                    guiCards.get(i).setImageResource(R.drawable.ace_diamond);
                                    break;
                                case 15:
                                    guiCards.get(i).setImageResource(R.drawable.wizard_huff);
                                    break;
                            }
                            break;
                        case "heart":
                            switch (card.getCardValue()) {
                                case 0:
                                    guiCards.get(i).setImageResource(R.drawable.jester_all);
                                    break;
                                case 2:
                                    guiCards.get(i).setImageResource(R.drawable.two_heart);
                                    break;
                                case 3:
                                    guiCards.get(i).setImageResource(R.drawable.three_heart);
                                    break;
                                case 4:
                                    guiCards.get(i).setImageResource(R.drawable.four_heart);
                                    break;
                                case 5:
                                    guiCards.get(i).setImageResource(R.drawable.five_heart);
                                    break;
                                case 6:
                                    guiCards.get(i).setImageResource(R.drawable.six_heart);
                                    break;
                                case 7:
                                    guiCards.get(i).setImageResource(R.drawable.seven_heart);
                                    break;
                                case 8:
                                    guiCards.get(i).setImageResource(R.drawable.eight_heart);
                                    break;
                                case 9:
                                    guiCards.get(i).setImageResource(R.drawable.nine_heart);
                                    break;
                                case 10:
                                    guiCards.get(i).setImageResource(R.drawable.ten_heart);
                                    break;
                                case 11:
                                    guiCards.get(i).setImageResource(R.drawable.jack_heart);
                                    break;
                                case 12:
                                    guiCards.get(i).setImageResource(R.drawable.queen_heart);
                                    break;
                                case 13:
                                    guiCards.get(i).setImageResource(R.drawable.king_heart);
                                    break;
                                case 14:
                                    guiCards.get(i).setImageResource(R.drawable.ace_heart);
                                    break;
                                case 15:
                                    guiCards.get(i).setImageResource(R.drawable.wizard_sly);
                                    break;
                            }
                            break;
                        case "spade":
                            switch (card.getCardValue()) {
                                case 0:
                                    guiCards.get(i).setImageResource(R.drawable.jester_all);
                                    break;
                                case 2:
                                    guiCards.get(i).setImageResource(R.drawable.two_spade);
                                    break;
                                case 3:
                                    guiCards.get(i).setImageResource(R.drawable.three_spade);
                                    break;
                                case 4:
                                    guiCards.get(i).setImageResource(R.drawable.four_spade);
                                    break;
                                case 5:
                                    guiCards.get(i).setImageResource(R.drawable.five_spade);
                                    break;
                                case 6:
                                    guiCards.get(i).setImageResource(R.drawable.six_spade);
                                    break;
                                case 7:
                                    guiCards.get(i).setImageResource(R.drawable.seven_spade);
                                    break;
                                case 8:
                                    guiCards.get(i).setImageResource(R.drawable.eight_spade);
                                    break;
                                case 9:
                                    guiCards.get(i).setImageResource(R.drawable.nine_spade);
                                    break;
                                case 10:
                                    guiCards.get(i).setImageResource(R.drawable.ten_spade);
                                    break;
                                case 11:
                                    guiCards.get(i).setImageResource(R.drawable.jack_spade);
                                    break;
                                case 12:
                                    guiCards.get(i).setImageResource(R.drawable.queen_spade);
                                    break;
                                case 13:
                                    guiCards.get(i).setImageResource(R.drawable.king_spade);
                                    break;
                                case 14:
                                    guiCards.get(i).setImageResource(R.drawable.ace_spade);
                                    break;
                                case 15:
                                    guiCards.get(i).setImageResource(R.drawable.wizard_griff);
                                    break;
                            }
                            break;
                        case "club":
                            switch (card.getCardValue()) {
                                case 0:
                                    guiCards.get(i).setImageResource(R.drawable.jester_all);
                                    break;
                                case 2:
                                    guiCards.get(i).setImageResource(R.drawable.two_club);
                                    break;
                                case 3:
                                    guiCards.get(i).setImageResource(R.drawable.three_club);
                                    break;
                                case 4:
                                    guiCards.get(i).setImageResource(R.drawable.four_club);
                                    break;
                                case 5:
                                    guiCards.get(i).setImageResource(R.drawable.five_club);
                                    break;
                                case 6:
                                    guiCards.get(i).setImageResource(R.drawable.six_club);
                                    break;
                                case 7:
                                    guiCards.get(i).setImageResource(R.drawable.seven_club);
                                    break;
                                case 8:
                                    guiCards.get(i).setImageResource(R.drawable.eight_club);
                                    break;
                                case 9:
                                    guiCards.get(i).setImageResource(R.drawable.nine_club);
                                    break;
                                case 10:
                                    guiCards.get(i).setImageResource(R.drawable.ten_club);
                                    break;
                                case 11:
                                    guiCards.get(i).setImageResource(R.drawable.jack_club);
                                    break;
                                case 12:
                                    guiCards.get(i).setImageResource(R.drawable.queen_club);
                                    break;
                                case 13:
                                    guiCards.get(i).setImageResource(R.drawable.king_club);
                                    break;
                                case 14:
                                    guiCards.get(i).setImageResource(R.drawable.ace_club);
                                    break;
                                case 15:
                                    guiCards.get(i).setImageResource(R.drawable.wizard_raven);
                                    break;
                            }
                    }
                    }
                }

                //set the rest of the image views to invisible if hand is smaller than 15
                for (; i < 15; i++) {
                    guiCards.get(i).setVisibility(View.INVISIBLE);
                }

            //sets image of played card by each player in middle
            WizardCards cp0 = state.cardsPlayed.get(0);
            if (cp0 != null) {
                card1Played.setVisibility(View.VISIBLE);
                switch (cp0.getCardSuit()) {
                    case "diamond":
                        switch (cp0.getCardValue()) {
                            case 0:
                                card1Played.setImageResource(R.drawable.jester_all);
                                break;
                            case 2:
                                card1Played.setImageResource(R.drawable.two_diamond);
                                break;
                            case 3:
                                card1Played.setImageResource(R.drawable.three_diamond);
                                break;
                            case 4:
                                card1Played.setImageResource(R.drawable.four_diamond);
                                break;
                            case 5:
                                card1Played.setImageResource(R.drawable.five_diamond);
                                break;
                            case 6:
                                card1Played.setImageResource(R.drawable.six_diamond);
                                break;
                            case 7:
                                card1Played.setImageResource(R.drawable.seven_diamond);
                                break;
                            case 8:
                                card1Played.setImageResource(R.drawable.eight_diamond);
                                break;
                            case 9:
                                card1Played.setImageResource(R.drawable.nine_diamond);
                                break;
                            case 10:
                                card1Played.setImageResource(R.drawable.ten_diamond);
                                break;
                            case 11:
                                card1Played.setImageResource(R.drawable.jack_diamond);
                                break;
                            case 12:
                                card1Played.setImageResource(R.drawable.queen_diamond);
                                break;
                            case 13:
                                card1Played.setImageResource(R.drawable.king_diamond);
                                break;
                            case 14:
                                card1Played.setImageResource(R.drawable.ace_diamond);
                                break;
                            case 15:
                                card1Played.setImageResource(R.drawable.wizard_huff);
                                break;
                        }
                        break;
                    case "heart":
                        switch (cp0.getCardValue()) {
                            case 0:
                                card1Played.setImageResource(R.drawable.jester_all);
                                break;
                            case 2:
                                card1Played.setImageResource(R.drawable.two_heart);
                                break;
                            case 3:
                                card1Played.setImageResource(R.drawable.three_heart);
                                break;
                            case 4:
                                card1Played.setImageResource(R.drawable.four_heart);
                                break;
                            case 5:
                                card1Played.setImageResource(R.drawable.five_heart);
                                break;
                            case 6:
                                card1Played.setImageResource(R.drawable.six_heart);
                                break;
                            case 7:
                                card1Played.setImageResource(R.drawable.seven_heart);
                                break;
                            case 8:
                                card1Played.setImageResource(R.drawable.eight_heart);
                                break;
                            case 9:
                                card1Played.setImageResource(R.drawable.nine_heart);
                                break;
                            case 10:
                                card1Played.setImageResource(R.drawable.ten_heart);
                                break;
                            case 11:
                                card1Played.setImageResource(R.drawable.jack_heart);
                                break;
                            case 12:
                                card1Played.setImageResource(R.drawable.queen_heart);
                                break;
                            case 13:
                                card1Played.setImageResource(R.drawable.king_heart);
                                break;
                            case 14:
                                card1Played.setImageResource(R.drawable.ace_heart);
                                break;
                            case 15:
                                card1Played.setImageResource(R.drawable.wizard_sly);
                                break;
                        }
                        break;
                    case "spade":
                        switch (cp0.getCardValue()) {
                            case 0:
                                card1Played.setImageResource(R.drawable.jester_all);
                                break;
                            case 2:
                                card1Played.setImageResource(R.drawable.two_spade);
                                break;
                            case 3:
                                card1Played.setImageResource(R.drawable.three_spade);
                                break;
                            case 4:
                                card1Played.setImageResource(R.drawable.four_spade);
                                break;
                            case 5:
                                card1Played.setImageResource(R.drawable.five_spade);
                                break;
                            case 6:
                                card1Played.setImageResource(R.drawable.six_spade);
                                break;
                            case 7:
                                card1Played.setImageResource(R.drawable.seven_spade);
                                break;
                            case 8:
                                card1Played.setImageResource(R.drawable.eight_spade);
                                break;
                            case 9:
                                card1Played.setImageResource(R.drawable.nine_spade);
                                break;
                            case 10:
                                card1Played.setImageResource(R.drawable.ten_spade);
                                break;
                            case 11:
                                card1Played.setImageResource(R.drawable.jack_spade);
                                break;
                            case 12:
                                card1Played.setImageResource(R.drawable.queen_spade);
                                break;
                            case 13:
                                card1Played.setImageResource(R.drawable.king_spade);
                                break;
                            case 14:
                                card1Played.setImageResource(R.drawable.ace_spade);
                                break;
                            case 15:
                                card1Played.setImageResource(R.drawable.wizard_griff);
                                break;
                        }
                        break;
                    case "club":
                        switch (cp0.getCardValue()) {
                            case 0:
                                card1Played.setImageResource(R.drawable.jester_all);
                                break;
                            case 2:
                                card1Played.setImageResource(R.drawable.two_club);
                                break;
                            case 3:
                                card1Played.setImageResource(R.drawable.three_club);
                                break;
                            case 4:
                                card1Played.setImageResource(R.drawable.four_club);
                                break;
                            case 5:
                                card1Played.setImageResource(R.drawable.five_club);
                                break;
                            case 6:
                                card1Played.setImageResource(R.drawable.six_club);
                                break;
                            case 7:
                                card1Played.setImageResource(R.drawable.seven_club);
                                break;
                            case 8:
                                card1Played.setImageResource(R.drawable.eight_club);
                                break;
                            case 9:
                                card1Played.setImageResource(R.drawable.nine_club);
                                break;
                            case 10:
                                card1Played.setImageResource(R.drawable.ten_club);
                                break;
                            case 11:
                                card1Played.setImageResource(R.drawable.jack_club);
                                break;
                            case 12:
                                card1Played.setImageResource(R.drawable.queen_club);
                                break;
                            case 13:
                                card1Played.setImageResource(R.drawable.king_club);
                                break;
                            case 14:
                                card1Played.setImageResource(R.drawable.ace_club);
                                break;
                            case 15:
                                card1Played.setImageResource(R.drawable.wizard_raven);
                                break;
                        }
                        break;
                }
            }
            WizardCards cp1 = state.cardsPlayed.get(1);
            if (cp1 != null) {
                card2Played.setVisibility(View.VISIBLE);
                switch (cp1.getCardSuit()) {
                    case "diamond":
                        switch (cp1.getCardValue()) {
                            case 0:
                                card2Played.setImageResource(R.drawable.jester_all);
                                break;
                            case 2:
                                card2Played.setImageResource(R.drawable.two_diamond);
                                break;
                            case 3:
                                card2Played.setImageResource(R.drawable.three_diamond);
                                break;
                            case 4:
                                card2Played.setImageResource(R.drawable.four_diamond);
                                break;
                            case 5:
                                card2Played.setImageResource(R.drawable.five_diamond);
                                break;
                            case 6:
                                card2Played.setImageResource(R.drawable.six_diamond);
                                break;
                            case 7:
                                card2Played.setImageResource(R.drawable.seven_diamond);
                                break;
                            case 8:
                                card2Played.setImageResource(R.drawable.eight_diamond);
                                break;
                            case 9:
                                card2Played.setImageResource(R.drawable.nine_diamond);
                                break;
                            case 10:
                                card2Played.setImageResource(R.drawable.ten_diamond);
                                break;
                            case 11:
                                card2Played.setImageResource(R.drawable.jack_diamond);
                                break;
                            case 12:
                                card2Played.setImageResource(R.drawable.queen_diamond);
                                break;
                            case 13:
                                card2Played.setImageResource(R.drawable.king_diamond);
                                break;
                            case 14:
                                card2Played.setImageResource(R.drawable.ace_diamond);
                                break;
                            case 15:
                                card2Played.setImageResource(R.drawable.wizard_huff);
                                break;
                        }
                        break;
                    case "heart":
                        switch (cp1.getCardValue()) {
                            case 0:
                                card2Played.setImageResource(R.drawable.jester_all);
                                break;
                            case 2:
                                card2Played.setImageResource(R.drawable.two_heart);
                                break;
                            case 3:
                                card2Played.setImageResource(R.drawable.three_heart);
                                break;
                            case 4:
                                card2Played.setImageResource(R.drawable.four_heart);
                                break;
                            case 5:
                                card2Played.setImageResource(R.drawable.five_heart);
                                break;
                            case 6:
                                card2Played.setImageResource(R.drawable.six_heart);
                                break;
                            case 7:
                                card2Played.setImageResource(R.drawable.seven_heart);
                                break;
                            case 8:
                                card2Played.setImageResource(R.drawable.eight_heart);
                                break;
                            case 9:
                                card2Played.setImageResource(R.drawable.nine_heart);
                                break;
                            case 10:
                                card2Played.setImageResource(R.drawable.ten_heart);
                                break;
                            case 11:
                                card2Played.setImageResource(R.drawable.jack_heart);
                                break;
                            case 12:
                                card2Played.setImageResource(R.drawable.queen_heart);
                                break;
                            case 13:
                                card2Played.setImageResource(R.drawable.king_heart);
                                break;
                            case 14:
                                card2Played.setImageResource(R.drawable.ace_heart);
                                break;
                            case 15:
                                card2Played.setImageResource(R.drawable.wizard_sly);
                                break;
                        }
                        break;
                    case "spade":
                        switch (cp1.getCardValue()) {
                            case 0:
                                card2Played.setImageResource(R.drawable.jester_all);
                                break;
                            case 2:
                                card2Played.setImageResource(R.drawable.two_spade);
                                break;
                            case 3:
                                card2Played.setImageResource(R.drawable.three_spade);
                                break;
                            case 4:
                                card2Played.setImageResource(R.drawable.four_spade);
                                break;
                            case 5:
                                card2Played.setImageResource(R.drawable.five_spade);
                                break;
                            case 6:
                                card2Played.setImageResource(R.drawable.six_spade);
                                break;
                            case 7:
                                card2Played.setImageResource(R.drawable.seven_spade);
                                break;
                            case 8:
                                card2Played.setImageResource(R.drawable.eight_spade);
                                break;
                            case 9:
                                card2Played.setImageResource(R.drawable.nine_spade);
                                break;
                            case 10:
                                card2Played.setImageResource(R.drawable.ten_spade);
                                break;
                            case 11:
                                card2Played.setImageResource(R.drawable.jack_spade);
                                break;
                            case 12:
                                card2Played.setImageResource(R.drawable.queen_spade);
                                break;
                            case 13:
                                card2Played.setImageResource(R.drawable.king_spade);
                                break;
                            case 14:
                                card2Played.setImageResource(R.drawable.ace_spade);
                                break;
                            case 15:
                                card2Played.setImageResource(R.drawable.wizard_griff);
                                break;
                        }
                        break;
                    case "club":
                        switch (cp1.getCardValue()) {
                            case 0:
                                card2Played.setImageResource(R.drawable.jester_all);
                                break;
                            case 2:
                                card2Played.setImageResource(R.drawable.two_club);
                                break;
                            case 3:
                                card2Played.setImageResource(R.drawable.three_club);
                                break;
                            case 4:
                                card2Played.setImageResource(R.drawable.four_club);
                                break;
                            case 5:
                                card2Played.setImageResource(R.drawable.five_club);
                                break;
                            case 6:
                                card2Played.setImageResource(R.drawable.six_club);
                                break;
                            case 7:
                                card2Played.setImageResource(R.drawable.seven_club);
                                break;
                            case 8:
                                card2Played.setImageResource(R.drawable.eight_club);
                                break;
                            case 9:
                                card2Played.setImageResource(R.drawable.nine_club);
                                break;
                            case 10:
                                card2Played.setImageResource(R.drawable.ten_club);
                                break;
                            case 11:
                                card2Played.setImageResource(R.drawable.jack_club);
                                break;
                            case 12:
                                card2Played.setImageResource(R.drawable.queen_club);
                                break;
                            case 13:
                                card2Played.setImageResource(R.drawable.king_club);
                                break;
                            case 14:
                                card2Played.setImageResource(R.drawable.ace_club);
                                break;
                            case 15:
                                card2Played.setImageResource(R.drawable.wizard_raven);
                                break;
                        }
                        break;
                }
            }
            WizardCards cp2 = state.cardsPlayed.get(2);
            if (cp2 != null) {
                card3Played.setVisibility(View.VISIBLE);
                switch (cp2.getCardSuit()) {
                    case "diamond":
                        switch (cp2.getCardValue()) {
                            case 0:
                                card3Played.setImageResource(R.drawable.jester_all);
                                break;
                            case 2:
                                card3Played.setImageResource(R.drawable.two_diamond);
                                break;
                            case 3:
                                card3Played.setImageResource(R.drawable.three_diamond);
                                break;
                            case 4:
                                card3Played.setImageResource(R.drawable.four_diamond);
                                break;
                            case 5:
                                card3Played.setImageResource(R.drawable.five_diamond);
                                break;
                            case 6:
                                card3Played.setImageResource(R.drawable.six_diamond);
                                break;
                            case 7:
                                card3Played.setImageResource(R.drawable.seven_diamond);
                                break;
                            case 8:
                                card3Played.setImageResource(R.drawable.eight_diamond);
                                break;
                            case 9:
                                card3Played.setImageResource(R.drawable.nine_diamond);
                                break;
                            case 10:
                                card3Played.setImageResource(R.drawable.ten_diamond);
                                break;
                            case 11:
                                card3Played.setImageResource(R.drawable.jack_diamond);
                                break;
                            case 12:
                                card3Played.setImageResource(R.drawable.queen_diamond);
                                break;
                            case 13:
                                card3Played.setImageResource(R.drawable.king_diamond);
                                break;
                            case 14:
                                card3Played.setImageResource(R.drawable.ace_diamond);
                                break;
                            case 15:
                                card3Played.setImageResource(R.drawable.wizard_huff);
                                break;
                        }
                        break;
                    case "heart":
                        switch (cp2.getCardValue()) {
                            case 0:
                                card3Played.setImageResource(R.drawable.jester_all);
                                break;
                            case 2:
                                card3Played.setImageResource(R.drawable.two_heart);
                                break;
                            case 3:
                                card3Played.setImageResource(R.drawable.three_heart);
                                break;
                            case 4:
                                card3Played.setImageResource(R.drawable.four_heart);
                                break;
                            case 5:
                                card3Played.setImageResource(R.drawable.five_heart);
                                break;
                            case 6:
                                card3Played.setImageResource(R.drawable.six_heart);
                                break;
                            case 7:
                                card3Played.setImageResource(R.drawable.seven_heart);
                                break;
                            case 8:
                                card3Played.setImageResource(R.drawable.eight_heart);
                                break;
                            case 9:
                                card3Played.setImageResource(R.drawable.nine_heart);
                                break;
                            case 10:
                                card3Played.setImageResource(R.drawable.ten_heart);
                                break;
                            case 11:
                                card3Played.setImageResource(R.drawable.jack_heart);
                                break;
                            case 12:
                                card3Played.setImageResource(R.drawable.queen_heart);
                                break;
                            case 13:
                                card3Played.setImageResource(R.drawable.king_heart);
                                break;
                            case 14:
                                card3Played.setImageResource(R.drawable.ace_heart);
                                break;
                            case 15:
                                card3Played.setImageResource(R.drawable.wizard_sly);
                                break;
                        }
                        break;
                    case "spade":
                        switch (cp2.getCardValue()) {
                            case 0:
                                card3Played.setImageResource(R.drawable.jester_all);
                                break;
                            case 2:
                                card3Played.setImageResource(R.drawable.two_spade);
                                break;
                            case 3:
                                card3Played.setImageResource(R.drawable.three_spade);
                                break;
                            case 4:
                                card3Played.setImageResource(R.drawable.four_spade);
                                break;
                            case 5:
                                card3Played.setImageResource(R.drawable.five_spade);
                                break;
                            case 6:
                                card3Played.setImageResource(R.drawable.six_spade);
                                break;
                            case 7:
                                card3Played.setImageResource(R.drawable.seven_spade);
                                break;
                            case 8:
                                card3Played.setImageResource(R.drawable.eight_spade);
                                break;
                            case 9:
                                card3Played.setImageResource(R.drawable.nine_spade);
                                break;
                            case 10:
                                card3Played.setImageResource(R.drawable.ten_spade);
                                break;
                            case 11:
                                card3Played.setImageResource(R.drawable.jack_spade);
                                break;
                            case 12:
                                card3Played.setImageResource(R.drawable.queen_spade);
                                break;
                            case 13:
                                card3Played.setImageResource(R.drawable.king_spade);
                                break;
                            case 14:
                                card3Played.setImageResource(R.drawable.ace_spade);
                                break;
                            case 15:
                                card3Played.setImageResource(R.drawable.wizard_griff);
                                break;
                        }
                        break;
                    case "club":
                        switch (cp2.getCardValue()) {
                            case 0:
                                card3Played.setImageResource(R.drawable.jester_all);
                                break;
                            case 2:
                                card3Played.setImageResource(R.drawable.two_club);
                                break;
                            case 3:
                                card3Played.setImageResource(R.drawable.three_club);
                                break;
                            case 4:
                                card3Played.setImageResource(R.drawable.four_club);
                                break;
                            case 5:
                                card3Played.setImageResource(R.drawable.five_club);
                                break;
                            case 6:
                                card3Played.setImageResource(R.drawable.six_club);
                                break;
                            case 7:
                                card3Played.setImageResource(R.drawable.seven_club);
                                break;
                            case 8:
                                card3Played.setImageResource(R.drawable.eight_club);
                                break;
                            case 9:
                                card3Played.setImageResource(R.drawable.nine_club);
                                break;
                            case 10:
                                card3Played.setImageResource(R.drawable.ten_club);
                                break;
                            case 11:
                                card3Played.setImageResource(R.drawable.jack_club);
                                break;
                            case 12:
                                card3Played.setImageResource(R.drawable.queen_club);
                                break;
                            case 13:
                                card3Played.setImageResource(R.drawable.king_club);
                                break;
                            case 14:
                                card3Played.setImageResource(R.drawable.ace_club);
                                break;
                            case 15:
                                card3Played.setImageResource(R.drawable.wizard_raven);
                                break;
                        }
                        break;
                }
            }
            WizardCards cp3 = state.cardsPlayed.get(3);
            if (cp3 != null) {
                card4Played.setVisibility(View.VISIBLE);
                switch (cp3.getCardSuit()) {
                    case "diamond":
                        switch (cp3.getCardValue()) {
                            case 0:
                                card4Played.setImageResource(R.drawable.jester_all);
                                break;
                            case 2:
                                card4Played.setImageResource(R.drawable.two_diamond);
                                break;
                            case 3:
                                card4Played.setImageResource(R.drawable.three_diamond);
                                break;
                            case 4:
                                card4Played.setImageResource(R.drawable.four_diamond);
                                break;
                            case 5:
                                card4Played.setImageResource(R.drawable.five_diamond);
                                break;
                            case 6:
                                card4Played.setImageResource(R.drawable.six_diamond);
                                break;
                            case 7:
                                card4Played.setImageResource(R.drawable.seven_diamond);
                                break;
                            case 8:
                                card4Played.setImageResource(R.drawable.eight_diamond);
                                break;
                            case 9:
                                card4Played.setImageResource(R.drawable.nine_diamond);
                                break;
                            case 10:
                                card4Played.setImageResource(R.drawable.ten_diamond);
                                break;
                            case 11:
                                card4Played.setImageResource(R.drawable.jack_diamond);
                                break;
                            case 12:
                                card4Played.setImageResource(R.drawable.queen_diamond);
                                break;
                            case 13:
                                card4Played.setImageResource(R.drawable.king_diamond);
                                break;
                            case 14:
                                card4Played.setImageResource(R.drawable.ace_diamond);
                                break;
                            case 15:
                                card4Played.setImageResource(R.drawable.wizard_huff);
                                break;
                        }
                        break;
                    case "heart":
                        switch (cp3.getCardValue()) {
                            case 0:
                                card4Played.setImageResource(R.drawable.jester_all);
                                break;
                            case 2:
                                card4Played.setImageResource(R.drawable.two_heart);
                                break;
                            case 3:
                                card4Played.setImageResource(R.drawable.three_heart);
                                break;
                            case 4:
                                card4Played.setImageResource(R.drawable.four_heart);
                                break;
                            case 5:
                                card4Played.setImageResource(R.drawable.five_heart);
                                break;
                            case 6:
                                card4Played.setImageResource(R.drawable.six_heart);
                                break;
                            case 7:
                                card4Played.setImageResource(R.drawable.seven_heart);
                                break;
                            case 8:
                                card4Played.setImageResource(R.drawable.eight_heart);
                                break;
                            case 9:
                                card4Played.setImageResource(R.drawable.nine_heart);
                                break;
                            case 10:
                                card4Played.setImageResource(R.drawable.ten_heart);
                                break;
                            case 11:
                                card4Played.setImageResource(R.drawable.jack_heart);
                                break;
                            case 12:
                                card4Played.setImageResource(R.drawable.queen_heart);
                                break;
                            case 13:
                                card4Played.setImageResource(R.drawable.king_heart);
                                break;
                            case 14:
                                card4Played.setImageResource(R.drawable.ace_heart);
                                break;
                            case 15:
                                card4Played.setImageResource(R.drawable.wizard_sly);
                                break;
                        }
                        break;
                    case "spade":
                        switch (cp3.getCardValue()) {
                            case 0:
                                card4Played.setImageResource(R.drawable.jester_all);
                                break;
                            case 2:
                                card4Played.setImageResource(R.drawable.two_spade);
                                break;
                            case 3:
                                card4Played.setImageResource(R.drawable.three_spade);
                                break;
                            case 4:
                                card4Played.setImageResource(R.drawable.four_spade);
                                break;
                            case 5:
                                card4Played.setImageResource(R.drawable.five_spade);
                                break;
                            case 6:
                                card4Played.setImageResource(R.drawable.six_spade);
                                break;
                            case 7:
                                card4Played.setImageResource(R.drawable.seven_spade);
                                break;
                            case 8:
                                card4Played.setImageResource(R.drawable.eight_spade);
                                break;
                            case 9:
                                card4Played.setImageResource(R.drawable.nine_spade);
                                break;
                            case 10:
                                card4Played.setImageResource(R.drawable.ten_spade);
                                break;
                            case 11:
                                card4Played.setImageResource(R.drawable.jack_spade);
                                break;
                            case 12:
                                card4Played.setImageResource(R.drawable.queen_spade);
                                break;
                            case 13:
                                card4Played.setImageResource(R.drawable.king_spade);
                                break;
                            case 14:
                                card4Played.setImageResource(R.drawable.ace_spade);
                                break;
                            case 15:
                                card4Played.setImageResource(R.drawable.wizard_griff);
                                break;
                        }
                        break;
                    case "club":
                        switch (cp3.getCardValue()) {
                            case 0:
                                card4Played.setImageResource(R.drawable.jester_all);
                                break;
                            case 2:
                                card4Played.setImageResource(R.drawable.two_club);
                                break;
                            case 3:
                                card4Played.setImageResource(R.drawable.three_club);
                                break;
                            case 4:
                                card4Played.setImageResource(R.drawable.four_club);
                                break;
                            case 5:
                                card4Played.setImageResource(R.drawable.five_club);
                                break;
                            case 6:
                                card4Played.setImageResource(R.drawable.six_club);
                                break;
                            case 7:
                                card4Played.setImageResource(R.drawable.seven_club);
                                break;
                            case 8:
                                card4Played.setImageResource(R.drawable.eight_club);
                                break;
                            case 9:
                                card4Played.setImageResource(R.drawable.nine_club);
                                break;
                            case 10:
                                card4Played.setImageResource(R.drawable.ten_club);
                                break;
                            case 11:
                                card4Played.setImageResource(R.drawable.jack_club);
                                break;
                            case 12:
                                card4Played.setImageResource(R.drawable.queen_club);
                                break;
                            case 13:
                                card4Played.setImageResource(R.drawable.king_club);
                                break;
                            case 14:
                                card4Played.setImageResource(R.drawable.ace_club);
                                break;
                            case 15:
                                card4Played.setImageResource(R.drawable.wizard_raven);
                                break;
                        }
                        break;
                }
            }

            //clears all the cards played
            if (cp0 == null && cp1 == null && cp2 == null && cp3 == null) {
                card1Played.setVisibility(View.INVISIBLE);
                card2Played.setVisibility(View.INVISIBLE);
                card3Played.setVisibility(View.INVISIBLE);
                card4Played.setVisibility(View.INVISIBLE);
            }

            //shows round num, game stage, and player turn on GUI
            roundText.setText("Round " + state.getRoundNum());
            if(state.getGameStage()==0) {
                gameStage.setText("Stage: Bidding");
            }
            else if (state.getGameStage()==1) {
                gameStage.setText("Stage: Playing");
            }

            //updates gui for players scores and bids
            player1Score.setText("WIZARD 1\n Bid: " + state.getPlayerBids().get(0) + "\nBids Won: "
                    + state.getPlayerBidsWon().get(0) + "\nTotal Score: " + state.getPlayerInfo(0).getPlayerScore());
            player2Score.setText("WIZARD 2\n Bid: " + state.getPlayerBids().get(1) + "\nBids Won: "
                    + state.getPlayerBidsWon().get(1) + "\nTotal Score: " + state.getPlayerInfo(1).getPlayerScore());
            player3Score.setText("WIZARD 3\n Bid: " + state.getPlayerBids().get(2) + "\nBids Won: "
                    + state.getPlayerBidsWon().get(2) + "\nTotal Score: " + state.getPlayerInfo(2).getPlayerScore());
            player4Score.setText("WIZARD 4\n Bid: " + state.getPlayerBids().get(3) + "\nBids Won: "
                    + state.getPlayerBidsWon().get(3) + "\nTotal Score: " + state.getPlayerInfo(3).getPlayerScore());

            //sets text color of player turn on GUI
            if(state.playerTurn==0){
                player1Score.setTextColor(Color.CYAN);
                player2Score.setTextColor(Color.WHITE);
                player3Score.setTextColor(Color.WHITE);
                player4Score.setTextColor(Color.WHITE);
            }
            else if(state.playerTurn==1){
                player2Score.setTextColor(Color.CYAN);
                player1Score.setTextColor(Color.WHITE);
                player3Score.setTextColor(Color.WHITE);
                player4Score.setTextColor(Color.WHITE);
            }
            else if(state.playerTurn==2){
                player3Score.setTextColor(Color.CYAN);
                player2Score.setTextColor(Color.WHITE);
                player1Score.setTextColor(Color.WHITE);
                player4Score.setTextColor(Color.WHITE);
            }
            else if(state.playerTurn==3){
                player4Score.setTextColor(Color.CYAN);
                player2Score.setTextColor(Color.WHITE);
                player3Score.setTextColor(Color.WHITE);
                player1Score.setTextColor(Color.WHITE);
            }

            roundNum = state.getRoundNum();
        }
    }

    /**
     * sets the current player as the activity's GUI
     * @param activity the game activity
     */
    @SuppressLint("WrongViewCast")
    public void setAsGui(GameMainActivity activity) {

        // remember our activity
        myActivity = activity;

        // Load the layout resource for the new configuration
        activity.setContentView(R.layout.activity_main);

        //finds and sets listener on image views
        card1 = (ImageView) myActivity.findViewById(R.id.imageView1);
        card1.setOnTouchListener(this);
        card2 = (ImageView) myActivity.findViewById(R.id.imageView2);
        card2.setOnTouchListener(this);
        card3 = (ImageView) myActivity.findViewById(R.id.imageView3);
        card3.setOnTouchListener(this);
        card4 = (ImageView) myActivity.findViewById(R.id.imageView4);
        card4.setOnTouchListener(this);
        card5 = (ImageView) myActivity.findViewById(R.id.imageView5);
        card5.setOnTouchListener(this);
        card6 = (ImageView) myActivity.findViewById(R.id.imageView6);
        card6.setOnTouchListener(this);
        card7 = (ImageView) myActivity.findViewById(R.id.imageView7);
        card7.setOnTouchListener(this);
        card8 = (ImageView) myActivity.findViewById(R.id.imageView8);
        card8.setOnTouchListener(this);
        card9 = (ImageView) myActivity.findViewById(R.id.imageView9);
        card9.setOnTouchListener(this);
        card10 = (ImageView) myActivity.findViewById(R.id.imageView10);
        card10.setOnTouchListener(this);
        card11 = (ImageView) myActivity.findViewById(R.id.imageView11);
        card11.setOnTouchListener(this);
        card12 = (ImageView) myActivity.findViewById(R.id.imageView12);
        card12.setOnTouchListener(this);
        card13 = (ImageView) myActivity.findViewById(R.id.imageView13);
        card13.setOnTouchListener(this);
        card14 = (ImageView) myActivity.findViewById(R.id.imageView14);
        card14.setOnTouchListener(this);
        card15 = (ImageView) myActivity.findViewById(R.id.imageView15);
        card15.setOnTouchListener(this);

        card1Played = (ImageView) myActivity.findViewById(R.id.player1imageView);
        card2Played = (ImageView) myActivity.findViewById(R.id.player2ImageView);
        card3Played = (ImageView) myActivity.findViewById(R.id.player3ImageView);
        card4Played = (ImageView) myActivity.findViewById(R.id.player4ImageView);

        cardTrump = (ImageView) myActivity.findViewById(R.id.trumpImageView);

        player1Score = (TextView) myActivity.findViewById(R.id.player1TextView);
        player2Score = (TextView) myActivity.findViewById(R.id.player2TextView);
        player3Score = (TextView) myActivity.findViewById(R.id.player3TextView);
        player4Score = (TextView) myActivity.findViewById(R.id.player4TextView);
        roundText = (TextView) myActivity.findViewById(R.id.roundTextView);
        gameStage = (TextView) myActivity.findViewById(R.id.gameStage);


        bidText = (TextView) myActivity.findViewById(R.id.bidText);
        bidText.setText(""+ this.bidNum);

        bidPlus = (Button) myActivity.findViewById(R.id.bidPlus);
        bidPlus.setOnClickListener(this);
        bidMinus = (Button) myActivity.findViewById(R.id.bidMinus);
        bidMinus.setOnClickListener(this);

        bidSubmitButton = (Button) myActivity.findViewById(R.id.bidSubmit);
        bidSubmitButton.setOnClickListener(this);
        quitButton = (Button) myActivity.findViewById(R.id.quitButton);
        quitButton.setOnClickListener(this);
        helpButton = (Button) myActivity.findViewById(R.id.helpButton);
        helpButton.setOnClickListener(this);

        mySurface = (SurfaceView) myActivity.findViewById(R.id.surfaceView);
        gButton = (ImageButton) myActivity.findViewById(R.id.gryffinButton);
        gButton.setOnClickListener(this);
        hButton = (ImageButton) myActivity.findViewById(R.id.huffleButton);
        hButton.setOnClickListener(this);
        rButton = (ImageButton) myActivity.findViewById(R.id.ravenButton);
        rButton.setOnClickListener(this);
        sButton = (ImageButton) myActivity.findViewById(R.id.slythButton);
        sButton.setOnClickListener(this);

        //adds all card image views to guiCards array
        guiCards.clear();
        Collections.addAll(guiCards, card1, card2, card3, card4, card5, card6, card7, card8,
                card9, card10, card11, card12, card13, card14, card15);
    }

    /**
     * perform any initialization that needs to be done after the player
     * knows what their game-position and opponents' names are.
     */
    protected void initAfterReady() {
    }

    /**
     * Updates GUI after user picks which card they want to play.
     * When player touches card it will send the card to WizardPlayAction if it is a valid move
     * @param v the view
     *        motionEvent the event
     */
    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
        int i = 0;
        //iterates through each imageview in guiCards to find which card was tapped

        /**
         External Citation
         Date: 7 November 2019
         Problem: More than one card disappeared when one card was touched
         Resource: Dr. Tribelhorn
         Solution: Changed condition of if statement and switch statement.
         */

        for (ImageView guiCard : guiCards){
            //checks if it is players turn and playing stage
            if (v == guiCard && i<state.getPlayerInfo(playerNum).getCurrentHand().size()
                    && state.getGameStage()==1 && state.getPlayerTurn()==playerNum){
                cardToPlay = state.getPlayerInfo(playerNum).getCurrentHand().get(i);
                if (cardToPlay == null)
                {
                    //Nux said to add temp to fix bug
                    return true;
                }

                //sets image to card played
                if(playerNum==0) {
                    card1Played.setVisibility(View.VISIBLE);
                    switch (cardToPlay.getCardSuit()) {
                        case "diamond":
                            switch (cardToPlay.getCardValue()) {
                                case 0:
                                    card1Played.setImageResource(R.drawable.jester_all);
                                    break;
                                case 2:
                                    card1Played.setImageResource(R.drawable.two_diamond);
                                    break;
                                case 3:
                                    card1Played.setImageResource(R.drawable.three_diamond);
                                    break;
                                case 4:
                                    card1Played.setImageResource(R.drawable.four_diamond);
                                    break;
                                case 5:
                                    card1Played.setImageResource(R.drawable.five_diamond);
                                    break;
                                case 6:
                                    card1Played.setImageResource(R.drawable.six_diamond);
                                    break;
                                case 7:
                                    card1Played.setImageResource(R.drawable.seven_diamond);
                                    break;
                                case 8:
                                    card1Played.setImageResource(R.drawable.eight_diamond);
                                    break;
                                case 9:
                                    card1Played.setImageResource(R.drawable.nine_diamond);
                                    break;
                                case 10:
                                    card1Played.setImageResource(R.drawable.ten_diamond);
                                    break;
                                case 11:
                                    card1Played.setImageResource(R.drawable.jack_diamond);
                                    break;
                                case 12:
                                    card1Played.setImageResource(R.drawable.queen_diamond);
                                    break;
                                case 13:
                                    card1Played.setImageResource(R.drawable.king_diamond);
                                    break;
                                case 14:
                                    card1Played.setImageResource(R.drawable.ace_diamond);
                                    break;
                                case 15:
                                    card1Played.setImageResource(R.drawable.wizard_huff);
                                    break;
                            }
                            break;
                        case "heart":
                            switch (cardToPlay.getCardValue()) {
                                case 0:
                                    card1Played.setImageResource(R.drawable.jester_all);
                                    break;
                                case 2:
                                    card1Played.setImageResource(R.drawable.two_heart);
                                    break;
                                case 3:
                                    card1Played.setImageResource(R.drawable.three_heart);
                                    break;
                                case 4:
                                    card1Played.setImageResource(R.drawable.four_heart);
                                    break;
                                case 5:
                                    card1Played.setImageResource(R.drawable.five_heart);
                                    break;
                                case 6:
                                    card1Played.setImageResource(R.drawable.six_heart);
                                    break;
                                case 7:
                                    card1Played.setImageResource(R.drawable.seven_heart);
                                    break;
                                case 8:
                                    card1Played.setImageResource(R.drawable.eight_heart);
                                    break;
                                case 9:
                                    card1Played.setImageResource(R.drawable.nine_heart);
                                    break;
                                case 10:
                                    card1Played.setImageResource(R.drawable.ten_heart);
                                    break;
                                case 11:
                                    card1Played.setImageResource(R.drawable.jack_heart);
                                    break;
                                case 12:
                                    card1Played.setImageResource(R.drawable.queen_heart);
                                    break;
                                case 13:
                                    card1Played.setImageResource(R.drawable.king_heart);
                                    break;
                                case 14:
                                    card1Played.setImageResource(R.drawable.ace_heart);
                                    break;
                                case 15:
                                    card1Played.setImageResource(R.drawable.wizard_sly);
                                    break;
                            }
                            break;
                        case "spade":
                            switch (cardToPlay.getCardValue()) {
                                case 0:
                                    card1Played.setImageResource(R.drawable.jester_all);
                                    break;
                                case 2:
                                    card1Played.setImageResource(R.drawable.two_spade);
                                    break;
                                case 3:
                                    card1Played.setImageResource(R.drawable.three_spade);
                                    break;
                                case 4:
                                    card1Played.setImageResource(R.drawable.four_spade);
                                    break;
                                case 5:
                                    card1Played.setImageResource(R.drawable.five_spade);
                                    break;
                                case 6:
                                    card1Played.setImageResource(R.drawable.six_spade);
                                    break;
                                case 7:
                                    card1Played.setImageResource(R.drawable.seven_spade);
                                    break;
                                case 8:
                                    card1Played.setImageResource(R.drawable.eight_spade);
                                    break;
                                case 9:
                                    card1Played.setImageResource(R.drawable.nine_spade);
                                    break;
                                case 10:
                                    card1Played.setImageResource(R.drawable.ten_spade);
                                    break;
                                case 11:
                                    card1Played.setImageResource(R.drawable.jack_spade);
                                    break;
                                case 12:
                                    card1Played.setImageResource(R.drawable.queen_spade);
                                    break;
                                case 13:
                                    card1Played.setImageResource(R.drawable.king_spade);
                                    break;
                                case 14:
                                    card1Played.setImageResource(R.drawable.ace_spade);
                                    break;
                                case 15:
                                    card1Played.setImageResource(R.drawable.wizard_griff);
                                    break;
                            }
                            break;
                        case "club":
                            switch (cardToPlay.getCardValue()) {
                                case 0:
                                    card1Played.setImageResource(R.drawable.jester_all);
                                    break;
                                case 2:
                                    card1Played.setImageResource(R.drawable.two_club);
                                    break;
                                case 3:
                                    card1Played.setImageResource(R.drawable.three_club);
                                    break;
                                case 4:
                                    card1Played.setImageResource(R.drawable.four_club);
                                    break;
                                case 5:
                                    card1Played.setImageResource(R.drawable.five_club);
                                    break;
                                case 6:
                                    card1Played.setImageResource(R.drawable.six_club);
                                    break;
                                case 7:
                                    card1Played.setImageResource(R.drawable.seven_club);
                                    break;
                                case 8:
                                    card1Played.setImageResource(R.drawable.eight_club);
                                    break;
                                case 9:
                                    card1Played.setImageResource(R.drawable.nine_club);
                                    break;
                                case 10:
                                    card1Played.setImageResource(R.drawable.ten_club);
                                    break;
                                case 11:
                                    card1Played.setImageResource(R.drawable.jack_club);
                                    break;
                                case 12:
                                    card1Played.setImageResource(R.drawable.queen_club);
                                    break;
                                case 13:
                                    card1Played.setImageResource(R.drawable.king_club);
                                    break;
                                case 14:
                                    card1Played.setImageResource(R.drawable.ace_club);
                                    break;
                                case 15:
                                    card1Played.setImageResource(R.drawable.wizard_raven);
                                    break;
                            }
                            break;
                    }
                    guiCards.get(i).setVisibility(View.INVISIBLE);
                }
                else if(playerNum==1) {
                    card2Played.setVisibility(View.VISIBLE);
                    switch (cardToPlay.getCardSuit()) {
                        case "diamond":
                            switch (cardToPlay.getCardValue()) {
                                case 0:
                                    card2Played.setImageResource(R.drawable.jester_all);
                                    break;
                                case 2:
                                    card2Played.setImageResource(R.drawable.two_diamond);
                                    break;
                                case 3:
                                    card2Played.setImageResource(R.drawable.three_diamond);
                                    break;
                                case 4:
                                    card2Played.setImageResource(R.drawable.four_diamond);
                                    break;
                                case 5:
                                    card2Played.setImageResource(R.drawable.five_diamond);
                                    break;
                                case 6:
                                    card2Played.setImageResource(R.drawable.six_diamond);
                                    break;
                                case 7:
                                    card2Played.setImageResource(R.drawable.seven_diamond);
                                    break;
                                case 8:
                                    card2Played.setImageResource(R.drawable.eight_diamond);
                                    break;
                                case 9:
                                    card2Played.setImageResource(R.drawable.nine_diamond);
                                    break;
                                case 10:
                                    card2Played.setImageResource(R.drawable.ten_diamond);
                                    break;
                                case 11:
                                    card2Played.setImageResource(R.drawable.jack_diamond);
                                    break;
                                case 12:
                                    card2Played.setImageResource(R.drawable.queen_diamond);
                                    break;
                                case 13:
                                    card2Played.setImageResource(R.drawable.king_diamond);
                                    break;
                                case 14:
                                    card2Played.setImageResource(R.drawable.ace_diamond);
                                    break;
                                case 15:
                                    card2Played.setImageResource(R.drawable.wizard_huff);
                                    break;
                            }
                            break;
                        case "heart":
                            switch (cardToPlay.getCardValue()) {
                                case 0:
                                    card2Played.setImageResource(R.drawable.jester_all);
                                    break;
                                case 2:
                                    card2Played.setImageResource(R.drawable.two_heart);
                                    break;
                                case 3:
                                    card2Played.setImageResource(R.drawable.three_heart);
                                    break;
                                case 4:
                                    card2Played.setImageResource(R.drawable.four_heart);
                                    break;
                                case 5:
                                    card2Played.setImageResource(R.drawable.five_heart);
                                    break;
                                case 6:
                                    card2Played.setImageResource(R.drawable.six_heart);
                                    break;
                                case 7:
                                    card2Played.setImageResource(R.drawable.seven_heart);
                                    break;
                                case 8:
                                    card2Played.setImageResource(R.drawable.eight_heart);
                                    break;
                                case 9:
                                    card2Played.setImageResource(R.drawable.nine_heart);
                                    break;
                                case 10:
                                    card2Played.setImageResource(R.drawable.ten_heart);
                                    break;
                                case 11:
                                    card2Played.setImageResource(R.drawable.jack_heart);
                                    break;
                                case 12:
                                    card2Played.setImageResource(R.drawable.queen_heart);
                                    break;
                                case 13:
                                    card2Played.setImageResource(R.drawable.king_heart);
                                    break;
                                case 14:
                                    card2Played.setImageResource(R.drawable.ace_heart);
                                    break;
                                case 15:
                                    card2Played.setImageResource(R.drawable.wizard_sly);
                                    break;
                            }
                            break;
                        case "spade":
                            switch (cardToPlay.getCardValue()) {
                                case 0:
                                    card2Played.setImageResource(R.drawable.jester_all);
                                    break;
                                case 2:
                                    card2Played.setImageResource(R.drawable.two_spade);
                                    break;
                                case 3:
                                    card2Played.setImageResource(R.drawable.three_spade);
                                    break;
                                case 4:
                                    card2Played.setImageResource(R.drawable.four_spade);
                                    break;
                                case 5:
                                    card2Played.setImageResource(R.drawable.five_spade);
                                    break;
                                case 6:
                                    card2Played.setImageResource(R.drawable.six_spade);
                                    break;
                                case 7:
                                    card2Played.setImageResource(R.drawable.seven_spade);
                                    break;
                                case 8:
                                    card2Played.setImageResource(R.drawable.eight_spade);
                                    break;
                                case 9:
                                    card2Played.setImageResource(R.drawable.nine_spade);
                                    break;
                                case 10:
                                    card2Played.setImageResource(R.drawable.ten_spade);
                                    break;
                                case 11:
                                    card2Played.setImageResource(R.drawable.jack_spade);
                                    break;
                                case 12:
                                    card2Played.setImageResource(R.drawable.queen_spade);
                                    break;
                                case 13:
                                    card2Played.setImageResource(R.drawable.king_spade);
                                    break;
                                case 14:
                                    card2Played.setImageResource(R.drawable.ace_spade);
                                    break;
                                case 15:
                                    card2Played.setImageResource(R.drawable.wizard_griff);
                                    break;
                            }
                            break;
                        case "club":
                            switch (cardToPlay.getCardValue()) {
                                case 0:
                                    card2Played.setImageResource(R.drawable.jester_all);
                                    break;
                                case 2:
                                    card2Played.setImageResource(R.drawable.two_club);
                                    break;
                                case 3:
                                    card2Played.setImageResource(R.drawable.three_club);
                                    break;
                                case 4:
                                    card2Played.setImageResource(R.drawable.four_club);
                                    break;
                                case 5:
                                    card2Played.setImageResource(R.drawable.five_club);
                                    break;
                                case 6:
                                    card2Played.setImageResource(R.drawable.six_club);
                                    break;
                                case 7:
                                    card2Played.setImageResource(R.drawable.seven_club);
                                    break;
                                case 8:
                                    card2Played.setImageResource(R.drawable.eight_club);
                                    break;
                                case 9:
                                    card2Played.setImageResource(R.drawable.nine_club);
                                    break;
                                case 10:
                                    card2Played.setImageResource(R.drawable.ten_club);
                                    break;
                                case 11:
                                    card2Played.setImageResource(R.drawable.jack_club);
                                    break;
                                case 12:
                                    card2Played.setImageResource(R.drawable.queen_club);
                                    break;
                                case 13:
                                    card2Played.setImageResource(R.drawable.king_club);
                                    break;
                                case 14:
                                    card2Played.setImageResource(R.drawable.ace_club);
                                    break;
                                case 15:
                                    card2Played.setImageResource(R.drawable.wizard_raven);
                                    break;
                            }
                            break;
                    }
                    guiCards.get(i).setVisibility(View.INVISIBLE);
                }
                else if(playerNum==2) {
                    card3Played.setVisibility(View.VISIBLE);
                    switch (cardToPlay.getCardSuit()) {
                        case "diamond":
                            switch (cardToPlay.getCardValue()) {
                                case 0:
                                    card3Played.setImageResource(R.drawable.jester_all);
                                    break;
                                case 2:
                                    card3Played.setImageResource(R.drawable.two_diamond);
                                    break;
                                case 3:
                                    card3Played.setImageResource(R.drawable.three_diamond);
                                    break;
                                case 4:
                                    card3Played.setImageResource(R.drawable.four_diamond);
                                    break;
                                case 5:
                                    card3Played.setImageResource(R.drawable.five_diamond);
                                    break;
                                case 6:
                                    card3Played.setImageResource(R.drawable.six_diamond);
                                    break;
                                case 7:
                                    card3Played.setImageResource(R.drawable.seven_diamond);
                                    break;
                                case 8:
                                    card3Played.setImageResource(R.drawable.eight_diamond);
                                    break;
                                case 9:
                                    card3Played.setImageResource(R.drawable.nine_diamond);
                                    break;
                                case 10:
                                    card3Played.setImageResource(R.drawable.ten_diamond);
                                    break;
                                case 11:
                                    card3Played.setImageResource(R.drawable.jack_diamond);
                                    break;
                                case 12:
                                    card3Played.setImageResource(R.drawable.queen_diamond);
                                    break;
                                case 13:
                                    card3Played.setImageResource(R.drawable.king_diamond);
                                    break;
                                case 14:
                                    card3Played.setImageResource(R.drawable.ace_diamond);
                                    break;
                                case 15:
                                    card3Played.setImageResource(R.drawable.wizard_huff);
                                    break;
                            }
                            break;
                        case "heart":
                            switch (cardToPlay.getCardValue()) {
                                case 0:
                                    card3Played.setImageResource(R.drawable.jester_all);
                                    break;
                                case 2:
                                    card3Played.setImageResource(R.drawable.two_heart);
                                    break;
                                case 3:
                                    card3Played.setImageResource(R.drawable.three_heart);
                                    break;
                                case 4:
                                    card3Played.setImageResource(R.drawable.four_heart);
                                    break;
                                case 5:
                                    card3Played.setImageResource(R.drawable.five_heart);
                                    break;
                                case 6:
                                    card3Played.setImageResource(R.drawable.six_heart);
                                    break;
                                case 7:
                                    card3Played.setImageResource(R.drawable.seven_heart);
                                    break;
                                case 8:
                                    card3Played.setImageResource(R.drawable.eight_heart);
                                    break;
                                case 9:
                                    card3Played.setImageResource(R.drawable.nine_heart);
                                    break;
                                case 10:
                                    card3Played.setImageResource(R.drawable.ten_heart);
                                    break;
                                case 11:
                                    card3Played.setImageResource(R.drawable.jack_heart);
                                    break;
                                case 12:
                                    card3Played.setImageResource(R.drawable.queen_heart);
                                    break;
                                case 13:
                                    card3Played.setImageResource(R.drawable.king_heart);
                                    break;
                                case 14:
                                    card3Played.setImageResource(R.drawable.ace_heart);
                                    break;
                                case 15:
                                    card3Played.setImageResource(R.drawable.wizard_sly);
                                    break;
                            }
                            break;
                        case "spade":
                            switch (cardToPlay.getCardValue()) {
                                case 0:
                                    card3Played.setImageResource(R.drawable.jester_all);
                                    break;
                                case 2:
                                    card3Played.setImageResource(R.drawable.two_spade);
                                    break;
                                case 3:
                                    card3Played.setImageResource(R.drawable.three_spade);
                                    break;
                                case 4:
                                    card3Played.setImageResource(R.drawable.four_spade);
                                    break;
                                case 5:
                                    card3Played.setImageResource(R.drawable.five_spade);
                                    break;
                                case 6:
                                    card3Played.setImageResource(R.drawable.six_spade);
                                    break;
                                case 7:
                                    card3Played.setImageResource(R.drawable.seven_spade);
                                    break;
                                case 8:
                                    card3Played.setImageResource(R.drawable.eight_spade);
                                    break;
                                case 9:
                                    card3Played.setImageResource(R.drawable.nine_spade);
                                    break;
                                case 10:
                                    card3Played.setImageResource(R.drawable.ten_spade);
                                    break;
                                case 11:
                                    card3Played.setImageResource(R.drawable.jack_spade);
                                    break;
                                case 12:
                                    card3Played.setImageResource(R.drawable.queen_spade);
                                    break;
                                case 13:
                                    card3Played.setImageResource(R.drawable.king_spade);
                                    break;
                                case 14:
                                    card3Played.setImageResource(R.drawable.ace_spade);
                                    break;
                                case 15:
                                    card3Played.setImageResource(R.drawable.wizard_griff);
                                    break;
                            }
                            break;
                        case "club":
                            switch (cardToPlay.getCardValue()) {
                                case 0:
                                    card3Played.setImageResource(R.drawable.jester_all);
                                    break;
                                case 2:
                                    card3Played.setImageResource(R.drawable.two_club);
                                    break;
                                case 3:
                                    card3Played.setImageResource(R.drawable.three_club);
                                    break;
                                case 4:
                                    card3Played.setImageResource(R.drawable.four_club);
                                    break;
                                case 5:
                                    card3Played.setImageResource(R.drawable.five_club);
                                    break;
                                case 6:
                                    card3Played.setImageResource(R.drawable.six_club);
                                    break;
                                case 7:
                                    card3Played.setImageResource(R.drawable.seven_club);
                                    break;
                                case 8:
                                    card3Played.setImageResource(R.drawable.eight_club);
                                    break;
                                case 9:
                                    card3Played.setImageResource(R.drawable.nine_club);
                                    break;
                                case 10:
                                    card3Played.setImageResource(R.drawable.ten_club);
                                    break;
                                case 11:
                                    card3Played.setImageResource(R.drawable.jack_club);
                                    break;
                                case 12:
                                    card3Played.setImageResource(R.drawable.queen_club);
                                    break;
                                case 13:
                                    card3Played.setImageResource(R.drawable.king_club);
                                    break;
                                case 14:
                                    card3Played.setImageResource(R.drawable.ace_club);
                                    break;
                                case 15:
                                    card3Played.setImageResource(R.drawable.wizard_raven);
                                    break;
                            }
                            break;
                    }
                    guiCards.get(i).setVisibility(View.INVISIBLE);
                }
                if(playerNum==3) {
                    card4Played.setVisibility(View.VISIBLE);
                    switch (cardToPlay.getCardSuit()) {
                        case "diamond":
                            switch (cardToPlay.getCardValue()) {
                                case 0:
                                    card4Played.setImageResource(R.drawable.jester_all);
                                    break;
                                case 2:
                                    card4Played.setImageResource(R.drawable.two_diamond);
                                    break;
                                case 3:
                                    card4Played.setImageResource(R.drawable.three_diamond);
                                    break;
                                case 4:
                                    card4Played.setImageResource(R.drawable.four_diamond);
                                    break;
                                case 5:
                                    card4Played.setImageResource(R.drawable.five_diamond);
                                    break;
                                case 6:
                                    card4Played.setImageResource(R.drawable.six_diamond);
                                    break;
                                case 7:
                                    card4Played.setImageResource(R.drawable.seven_diamond);
                                    break;
                                case 8:
                                    card4Played.setImageResource(R.drawable.eight_diamond);
                                    break;
                                case 9:
                                    card4Played.setImageResource(R.drawable.nine_diamond);
                                    break;
                                case 10:
                                    card4Played.setImageResource(R.drawable.ten_diamond);
                                    break;
                                case 11:
                                    card4Played.setImageResource(R.drawable.jack_diamond);
                                    break;
                                case 12:
                                    card4Played.setImageResource(R.drawable.queen_diamond);
                                    break;
                                case 13:
                                    card4Played.setImageResource(R.drawable.king_diamond);
                                    break;
                                case 14:
                                    card4Played.setImageResource(R.drawable.ace_diamond);
                                    break;
                                case 15:
                                    card4Played.setImageResource(R.drawable.wizard_huff);
                                    break;
                            }
                            break;
                        case "heart":
                            switch (cardToPlay.getCardValue()) {
                                case 0:
                                    card4Played.setImageResource(R.drawable.jester_all);
                                    break;
                                case 2:
                                    card4Played.setImageResource(R.drawable.two_heart);
                                    break;
                                case 3:
                                    card4Played.setImageResource(R.drawable.three_heart);
                                    break;
                                case 4:
                                    card4Played.setImageResource(R.drawable.four_heart);
                                    break;
                                case 5:
                                    card4Played.setImageResource(R.drawable.five_heart);
                                    break;
                                case 6:
                                    card4Played.setImageResource(R.drawable.six_heart);
                                    break;
                                case 7:
                                    card4Played.setImageResource(R.drawable.seven_heart);
                                    break;
                                case 8:
                                    card4Played.setImageResource(R.drawable.eight_heart);
                                    break;
                                case 9:
                                    card4Played.setImageResource(R.drawable.nine_heart);
                                    break;
                                case 10:
                                    card4Played.setImageResource(R.drawable.ten_heart);
                                    break;
                                case 11:
                                    card4Played.setImageResource(R.drawable.jack_heart);
                                    break;
                                case 12:
                                    card4Played.setImageResource(R.drawable.queen_heart);
                                    break;
                                case 13:
                                    card4Played.setImageResource(R.drawable.king_heart);
                                    break;
                                case 14:
                                    card4Played.setImageResource(R.drawable.ace_heart);
                                    break;
                                case 15:
                                    card4Played.setImageResource(R.drawable.wizard_sly);
                                    break;
                            }
                            break;
                        case "spade":
                            switch (cardToPlay.getCardValue()) {
                                case 0:
                                    card4Played.setImageResource(R.drawable.jester_all);
                                    break;
                                case 2:
                                    card4Played.setImageResource(R.drawable.two_spade);
                                    break;
                                case 3:
                                    card4Played.setImageResource(R.drawable.three_spade);
                                    break;
                                case 4:
                                    card4Played.setImageResource(R.drawable.four_spade);
                                    break;
                                case 5:
                                    card4Played.setImageResource(R.drawable.five_spade);
                                    break;
                                case 6:
                                    card4Played.setImageResource(R.drawable.six_spade);
                                    break;
                                case 7:
                                    card4Played.setImageResource(R.drawable.seven_spade);
                                    break;
                                case 8:
                                    card4Played.setImageResource(R.drawable.eight_spade);
                                    break;
                                case 9:
                                    card4Played.setImageResource(R.drawable.nine_spade);
                                    break;
                                case 10:
                                    card4Played.setImageResource(R.drawable.ten_spade);
                                    break;
                                case 11:
                                    card4Played.setImageResource(R.drawable.jack_spade);
                                    break;
                                case 12:
                                    card4Played.setImageResource(R.drawable.queen_spade);
                                    break;
                                case 13:
                                    card4Played.setImageResource(R.drawable.king_spade);
                                    break;
                                case 14:
                                    card4Played.setImageResource(R.drawable.ace_spade);
                                    break;
                                case 15:
                                    card4Played.setImageResource(R.drawable.wizard_griff);
                                    break;
                            }
                            break;
                        case "club":
                            switch (cardToPlay.getCardValue()) {
                                case 0:
                                    card4Played.setImageResource(R.drawable.jester_all);
                                    break;
                                case 2:
                                    card4Played.setImageResource(R.drawable.two_club);
                                    break;
                                case 3:
                                    card4Played.setImageResource(R.drawable.three_club);
                                    break;
                                case 4:
                                    card4Played.setImageResource(R.drawable.four_club);
                                    break;
                                case 5:
                                    card4Played.setImageResource(R.drawable.five_club);
                                    break;
                                case 6:
                                    card4Played.setImageResource(R.drawable.six_club);
                                    break;
                                case 7:
                                    card4Played.setImageResource(R.drawable.seven_club);
                                    break;
                                case 8:
                                    card4Played.setImageResource(R.drawable.eight_club);
                                    break;
                                case 9:
                                    card4Played.setImageResource(R.drawable.nine_club);
                                    break;
                                case 10:
                                    card4Played.setImageResource(R.drawable.ten_club);
                                    break;
                                case 11:
                                    card4Played.setImageResource(R.drawable.jack_club);
                                    break;
                                case 12:
                                    card4Played.setImageResource(R.drawable.queen_club);
                                    break;
                                case 13:
                                    card4Played.setImageResource(R.drawable.king_club);
                                    break;
                                case 14:
                                    card4Played.setImageResource(R.drawable.ace_club);
                                    break;
                                case 15:
                                    card4Played.setImageResource(R.drawable.wizard_raven);
                                    break;
                            }
                            break;
                    }
                    guiCards.get(i).setVisibility(View.INVISIBLE);
                }

                //sends card picked to play action
                myPlay = new WizardPlayAction(this, cardToPlay, i);
                super.game.sendAction(myPlay);
                return true;
            }
            else {
                i++;
            }
        }
        return false;
    }

    /**
     * Updates GUI after buttons are pressed.
     * Change color of background
     * Go to help screen or quit
     * Increase or decrease bid and submit
     * @param view the screen view
     */
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            //sets background color
            case R.id.gryffinButton:
                mySurface.setBackgroundColor(Color.argb(225, 128, 0, 0));
                mySurface.invalidate();
                break;
            case R.id.huffleButton:
                mySurface.setBackgroundColor(Color.argb(255,	184, 182, 8));
                mySurface.invalidate();
                break;
            case R.id.slythButton:
                mySurface.setBackgroundColor(Color.argb(255, 0, 100, 0));
                mySurface.invalidate();
                break;
            case R.id.ravenButton:
                mySurface.setBackgroundColor(Color.argb(255, 51, 51, 139));
                mySurface.invalidate();
                break;
            case R.id.helpButton:
                myActivity.setContentView(R.layout.game_help_screen);
                backToGameButton = (Button) myActivity.findViewById(R.id.backToGame);
                backToGameButton.setOnClickListener(this);
                helpText = (TextView) myActivity.findViewById(R.id.helpText);
                this.setHelpText();
                break;
            case R.id.quitButton:
                myActivity.recreate();
                myActivity.setContentView(R.layout.game_config_main);
                break;
                //backToGame is on help screen
            case R.id.backToGame:
                this.setAsGui(this.myActivity);
                this.sendInfo((GameInfo) this.state);
                break;
        }

        //used to set bid num and submit by human user
        if(state.getGameStage() == 0) {
            switch (view.getId()) {
                case R.id.bidPlus:
                    if (bidNum < state.getRoundNum()) {
                        bidNum++;
                        bidText.setText("" + bidNum);
                    }
                    break;
                case R.id.bidMinus:
                    if (bidNum > 0) {
                        bidNum--;
                        bidText.setText("" + bidNum);
                    }
                    break;
                case R.id.bidSubmit:
                    myBid = new WizardBidAction(this, bidNum);
                    super.game.sendAction(myBid);
                    break;
            }
        }
    }

    public void setHelpText(){
        helpText.setTextColor(Color.BLACK);
        helpText.setText("Cards:\n" +
                "\n" +
                "There is a normal deck of cards with four Wizard cards and four Jester cards (60 cards total). The Jesters are lowest in value followed by two, three, four, and so on with ace being high. The Wizards have the highest value.\n" +
                "\n" +
                "Object of the Game:\n" +
                "\n" +
                "The object of the game is to correctly predict the number of tricks (sub rounds) you will win within each round. You will earn points for guessing the correct value and lose points when you don’t. The player with the most points at the end of the game wins.\n" +
                "\n" +
                "Dealing and Trump:\n" +
                "\n" +
                "Each player will be dealt the same number of cards at the start of each round. The number of cards is determined by the round number. For example, if it’s round two, each player gets two cards, round three, three cards and so on. There are fifteen rounds in total. Once the cards are dealt one card will be in the middle and that determines the trump suit for that round. The last round, all cards will be dealt (each player will have fifteen) and therefore there is no trump for that round. The trump becomes the leading suit of each trick.\n" +
                "\n" +
                "Bidding:\n" +
                "\n" +
                "Before cards can be played, each player must make a bid starting with Player 1. You are bidding the number of tricks you are expecting to win that round. You can make a bid zero to the round number (including both), but not any more than that. For example, you can’t bid ten for round four. To place your bid, tap the drop-down menu under the submit bid button and select an appropriate value, then press the submit bid button. Once you submit your bid, you cannot change the value.\n" +
                "\n" +
                "Playing:\n" +
                "\n" +
                "Player 1 will start each round after everyone has made a bid. Tap the card you would like to play and wait while everyone else plays their card. When the cards disappear, that means the winner of the trick has been calculated and their Bids Won value should increment by one.\n" +
                "\n" +
                "To win a trick you must:\n" +
                "\n" +
                "o Be the first one to play a Wizard (only the first Wizard wins)\n" +
                "\n" +
                "o Play the highest value in the trump suit if there are no Wizards\n" +
                "\n" +
                "o or play the highest value in the leading suit, if no trump cards or Wizards\n" +
                "\n" +
                "Scoring:\n" +
                "\n" +
                "After each round each player’s score will be tallied. If you won the value of tricks that you bid, you get twenty points plus ten points for every trick you won. For example, if you bid two and you won two, you get forty points added to your total score. Even if you bid zero and won zero you still get twenty points. If you didn’t guess correctly and won too many tricks or didn’t win enough, ten points are deducted for how far off you were. For example, if you bid three and you only won one trick, you lose twenty points. Another example is you bid zero and you won one trick you lose ten points.");
    }

}