package com.example.wizard2.Wizard;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.wizard2.GameFramework.GameHumanPlayer;
import com.example.wizard2.GameFramework.GameMainActivity;
import com.example.wizard2.GameFramework.infoMessage.GameInfo;
import com.example.wizard2.GameFramework.infoMessage.IllegalMoveInfo;
import com.example.wizard2.GameFramework.utilities.Logger;
import com.example.wizard2.R;

import java.nio.InvalidMarkException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.os.SystemClock.sleep;

public class WizardHumanPlayer extends GameHumanPlayer implements View.OnTouchListener, View.OnClickListener {

    private WizardCards cardToPlay;
    private int bidNum = 0;
    private int roundNum;

    //ACTIONS
    WizardPlayAction myPlay;
    WizardBidAction myBid;

    private WizardState state = new WizardState();

    private ArrayList<ImageView> guiCards = new ArrayList<ImageView>();

    //Tag for logging
    private static final String TAG = "WizardHumanPlayer";

    // the current activity
    private GameMainActivity myActivity;

    //GUI
    private TextView player1Score = null;   //players info (bid, bids won, scores)
    private TextView player2Score = null;
    private TextView player3Score = null;
    private TextView player4Score = null;
    private TextView roundText = null;
    private ImageView card1 = null;         //humans player card 1
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
    private ImageView card1Played = null;   //the card that player 1 played
    private ImageView card2Played = null;
    private ImageView card3Played = null;
    private ImageView card4Played = null;
    private ImageView cardTrump = null;     //trump card
   // private Spinner bidSpinner = null;
    private Button bidSubmitButton = null;
    private TextView bidText = null;
    private Button bidPlus = null;
    private Button bidMinus = null;

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

            //sets trump card image
            if(state.getRoundNum() < 15) {
                WizardCards trumpCard = ((WizardState) info).getTrumpCard();
                switch (trumpCard.getCardSuit()) {
                    case "diamond":
                        switch (trumpCard.getCardValue()) {
                            case 0:
                                cardTrump.setImageResource(R.drawable.jester);
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
                                cardTrump.setImageResource(R.drawable.wizard);
                                break;
                        }
                        break;
                    case "heart":
                        switch (trumpCard.getCardValue()) {
                            case 0:
                                cardTrump.setImageResource(R.drawable.jester);
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
                                cardTrump.setImageResource(R.drawable.wizard);
                                break;
                        }
                        break;
                    case "spade":
                        switch (trumpCard.getCardValue()) {
                            case 0:
                                cardTrump.setImageResource(R.drawable.jester);
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
                                cardTrump.setImageResource(R.drawable.wizard);
                                break;
                        }
                        break;
                    case "club":
                        switch (trumpCard.getCardValue()) {
                            case 0:
                                cardTrump.setImageResource(R.drawable.jester);
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
                                cardTrump.setImageResource(R.drawable.wizard);
                                break;
                        }
                }
            }

            //sets image to cards in human hand
            int i = 0;
            if(state.playerTurn == this.playerNum){
                for (; i < state.roundNum; i++) {
                    WizardCards card = ((WizardState) info).getPlayerInfo(0).getCurrentHand().get(i);
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
                                        guiCards.get(i).setImageResource(R.drawable.jester);
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
                                        guiCards.get(i).setImageResource(R.drawable.wizard);
                                        break;
                                }
                                break;
                            case "heart":
                                switch (card.getCardValue()) {
                                    case 0:
                                        guiCards.get(i).setImageResource(R.drawable.jester);
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
                                        guiCards.get(i).setImageResource(R.drawable.wizard);
                                        break;
                                }
                                break;
                            case "spade":
                                switch (card.getCardValue()) {
                                    case 0:
                                        guiCards.get(i).setImageResource(R.drawable.jester);
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
                                        guiCards.get(i).setImageResource(R.drawable.wizard);
                                        break;
                                }
                                break;
                            case "club":
                                switch (card.getCardValue()) {
                                    case 0:
                                        guiCards.get(i).setImageResource(R.drawable.jester);
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
                                        guiCards.get(i).setImageResource(R.drawable.wizard);
                                        break;
                                }
                        }
                    }
                }
                //set the rest of the image views to invisible if hand is smaller than 15
                for (; i < 15; i++) {
                    guiCards.get(i).setVisibility(View.INVISIBLE);
                }
            }

            //sets image of played card by each player in middle
            WizardCards cp1 = state.cardsPlayed.get(1);
            if (cp1 != null) {
                card2Played.setVisibility(View.VISIBLE);
                switch (cp1.getCardSuit()) {
                    case "diamond":
                        switch (cp1.getCardValue()) {
                            case 0:
                                card2Played.setImageResource(R.drawable.jester);
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
                                card2Played.setImageResource(R.drawable.wizard);
                                break;
                        }
                        break;
                    case "heart":
                        switch (cp1.getCardValue()) {
                            case 0:
                                card2Played.setImageResource(R.drawable.jester);
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
                                card2Played.setImageResource(R.drawable.wizard);
                                break;
                        }
                        break;
                    case "spade":
                        switch (cp1.getCardValue()) {
                            case 0:
                                card2Played.setImageResource(R.drawable.jester);
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
                                card2Played.setImageResource(R.drawable.wizard);
                                break;
                        }
                        break;
                    case "club":
                        switch (cp1.getCardValue()) {
                            case 0:
                                card2Played.setImageResource(R.drawable.jester);
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
                                card2Played.setImageResource(R.drawable.wizard);
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
                                card3Played.setImageResource(R.drawable.jester);
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
                                card3Played.setImageResource(R.drawable.wizard);
                                break;
                        }
                        break;
                    case "heart":
                        switch (cp2.getCardValue()) {
                            case 0:
                                card3Played.setImageResource(R.drawable.jester);
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
                                card3Played.setImageResource(R.drawable.wizard);
                                break;
                        }
                        break;
                    case "spade":
                        switch (cp2.getCardValue()) {
                            case 0:
                                card3Played.setImageResource(R.drawable.jester);
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
                                card3Played.setImageResource(R.drawable.wizard);
                                break;
                        }
                        break;
                    case "club":
                        switch (cp2.getCardValue()) {
                            case 0:
                                card3Played.setImageResource(R.drawable.jester);
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
                                card3Played.setImageResource(R.drawable.wizard);
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
                                card4Played.setImageResource(R.drawable.jester);
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
                                card4Played.setImageResource(R.drawable.wizard);
                                break;
                        }
                        break;
                    case "heart":
                        switch (cp3.getCardValue()) {
                            case 0:
                                card4Played.setImageResource(R.drawable.jester);
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
                                card4Played.setImageResource(R.drawable.wizard);
                                break;
                        }
                        break;
                    case "spade":
                        switch (cp3.getCardValue()) {
                            case 0:
                                card4Played.setImageResource(R.drawable.jester);
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
                                card4Played.setImageResource(R.drawable.wizard);
                                break;
                        }
                        break;
                    case "club":
                        switch (cp3.getCardValue()) {
                            case 0:
                                card4Played.setImageResource(R.drawable.jester);
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
                                card4Played.setImageResource(R.drawable.wizard);
                                break;
                        }
                        break;
                }
            }
            WizardCards cp0 = state.cardsPlayed.get(0);

                //clears all the cards played
                if (cp0 == null && cp1 == null && cp2 == null && cp3 == null) {
                    card1Played.setVisibility(View.INVISIBLE);
                    card2Played.setVisibility(View.INVISIBLE);
                    card3Played.setVisibility(View.INVISIBLE);
                    card4Played.setVisibility(View.INVISIBLE);
                }

                //shows round num and player turn on GUI
                roundText.setText("Round " + state.getRoundNum() + "\n Player Turn: " + (state.getPlayerTurn() + 1));

                //updates gui for players scores and bids
                player1Score.setText("PLAYER 1\n Bid: " + state.getPlayerBids().get(0) + "\nBids Won: "
                        + state.getPlayerBidsWon().get(0) + "\nTotal Score: " + state.getPlayerInfo(0).getPlayerScore());
                player2Score.setText("PLAYER 2\n Bid: " + state.getPlayerBids().get(1) + "\nBids Won: "
                        + state.getPlayerBidsWon().get(1) + "\nTotal Score: " + state.getPlayerInfo(1).getPlayerScore());
                player3Score.setText("PLAYER 3\n Bid: " + state.getPlayerBids().get(2) + "\nBids Won: "
                        + state.getPlayerBidsWon().get(2) + "\nTotal Score: " + state.getPlayerInfo(2).getPlayerScore());
                player4Score.setText("PLAYER 4\n Bid: " + state.getPlayerBids().get(3) + "\nBids Won: "
                        + state.getPlayerBidsWon().get(3) + "\nTotal Score: " + state.getPlayerInfo(3).getPlayerScore());

                roundNum = state.getRoundNum();

        }
    }

    /**
     * sets the current player as the activity's GUI
     */
    @SuppressLint("WrongViewCast")
    public void setAsGui(GameMainActivity activity) {

        // remember our activitiy
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

//        bidSpinner = (Spinner) myActivity.findViewById(R.id.bidDropdown);
//        List<Integer> spinVal = new ArrayList<Integer>();
//
//        //need for spinner
//        for (int i = 0; i < 16; i++) {
//            if(!spinVal.contains(i)) {
//                spinVal.add(i);
//            }
//        }
//
//        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(myActivity,
//                android.R.layout.simple_spinner_item, spinVal);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        bidSpinner.setAdapter(dataAdapter);

        bidText = (TextView) myActivity.findViewById(R.id.bidText);

        bidText.setText(""+ this.bidNum);


        bidPlus = (Button) myActivity.findViewById(R.id.bidPlus);
        bidPlus.setOnClickListener(this);
        bidMinus = (Button) myActivity.findViewById(R.id.bidMinus);
        bidMinus.setOnClickListener(this);

        bidSubmitButton = (Button) myActivity.findViewById(R.id.bidSubmit);
        bidSubmitButton.setOnClickListener(this);

        Collections.addAll(guiCards, card1, card2, card3, card4, card5, card6, card7, card8,
                card9, card10, card11, card12, card13, card14, card15);
    }

    /**
     * perform any initialization that needs to be done after the player
     * knows what their game-position and opponents' names are.
     */
    protected void initAfterReady() {
        //myActivity.setTitle("Tic-Tac-Toe: " + allPlayerNames[0] + " vs. " + allPlayerNames[1]);
    }

    //When player touches card it will send the card to WizardPlayAction if it is a valid move
    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
        int i = 0;
        for (ImageView guiCard : guiCards){

            //checks if it is players turn and playing stage
            if (v == guiCard && i<state.getPlayerInfo(0).getCurrentHand().size()
                    && state.getGameStage()==1 && state.getPlayerTurn()==0){
               // alreadyChosen = true;
                cardToPlay = state.getPlayerInfo(0).getCurrentHand().get(i);
                if (cardToPlay == null)
                {
                    //Nux said to add temp to fix bug
                    return true;
                }

                //sets image to card played
                card1Played.setVisibility(View.VISIBLE);
                switch (cardToPlay.getCardSuit()) {
                    case "diamond":
                        switch (cardToPlay.getCardValue()) {
                            case 0:
                                card1Played.setImageResource(R.drawable.jester);
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
                                card1Played.setImageResource(R.drawable.wizard);
                                break;
                        }
                        break;
                    case "heart":
                        switch (cardToPlay.getCardValue()) {
                            case 0:
                                card1Played.setImageResource(R.drawable.jester);
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
                                card1Played.setImageResource(R.drawable.wizard);
                                break;
                        }
                        break;
                    case "spade":
                        switch (cardToPlay.getCardValue()) {
                            case 0:
                                card1Played.setImageResource(R.drawable.jester);
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
                                card1Played.setImageResource(R.drawable.wizard);
                                break;
                        }
                        break;
                    case "club":
                        switch (cardToPlay.getCardValue()) {
                            case 0:
                                card1Played.setImageResource(R.drawable.jester);
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
                                card1Played.setImageResource(R.drawable.wizard);
                                break;
                        }
                        break;
                }
                guiCards.get(i).setVisibility(View.INVISIBLE);

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

    @Override
    public void onClick(View view) {
//        switch(view.getId()){
//            case R.id.helpButton:
////                myActivity.setContentView(R.layout.game_help_screen);
//                break;
//            case R.id.quitButton:
//                //myActivity.setContentView(R.layout.game_config_main);
//                break;
////            case R.id.backToGame:
////                myActivity.setContentView(R.layout.activity_main);
////                break;
//        }
//
//        bidNum = (Integer)bidSpinner.getSelectedItem();
//
//        //checks if bid chosen is valid
//        if(bidNum <= state.getRoundNum()) {
//            myBid = new WizardBidAction(this, bidNum);
//            super.game.sendAction(myBid);
//        }
        if(state.getGameStage() == 0) {
            switch (view.getId()) {
                case R.id.bidPlus:
                    if (bidNum < state.getRoundNum()) {
                        bidNum++;
                        bidText.setText(""+bidNum);
                    }
                    break;

                case R.id.bidMinus:
                    if (bidNum > 0) {
                        bidNum--;
                        bidText.setText(""+bidNum);
                    }
                    break;

                case R.id.bidSubmit:
                    myBid = new WizardBidAction(this, bidNum);
                    super.game.sendAction(myBid);
                    break;
            }
        }
    }


}