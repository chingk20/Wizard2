package com.example.wizard2.Wizard;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class WizardHumanPlayer extends GameHumanPlayer implements AdapterView.OnItemSelectedListener, View.OnClickListener , View.OnTouchListener {

    private WizardCards cardToPlay;
    private int bidNum = 0;

    WizardPlayAction myPlay = new WizardPlayAction(this, cardToPlay);
    WizardBidAction myBid = new WizardBidAction(this, bidNum);

    WizardPlayer myPlayer;
    private WizardState state;

    private ArrayList<ImageView> guiCards = new ArrayList<ImageView>();

    //Tag for logging
    private static final String TAG = "WizardHumanPlayer";
    // the current activity
    private GameMainActivity myActivity;

    private List<String> spinVal = new ArrayList<String>();

    // the card picture
    private TextView player1Score = null;
    private TextView player2Score = null;
    private TextView player3Score = null;
    private TextView player4Score = null;
    private TextView roundText = null;
    private ImageView card1 = null;     //humans player card 1
    private ImageView card2 = null;
    private ImageView card3 = null;
    private ImageView card4 = null;
    private ImageView card5 = null;
    private ImageView card6 = null;
    private ImageView card7 = null;
    private ImageView card8 = null;
    private ImageView card9 = null;     //humans player card 1
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
    private ImageView cardTrump = null;
    private Spinner bidSpinner = null;

    /**
     * constructor
     *
     * @param name the player's name
     */
    public WizardHumanPlayer(String name) {
        super(name);
        myPlayer = new WizardPlayer(0, name);
    }

    /**
     * returns the GUI's top view
     *
     * @return the GUI's top view
     */
    @Override
    public View getTopView() {
        return null;
    }

    /**
     * Callback method, called when player gets a message
     *
     * @param info the message
     */
    @Override
    public void receiveInfo(GameInfo info) {

//        if (surfaceView == null) return;
//
//        if (info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo) {
//            // if the move was out of turn or otherwise illegal, flash the screen
//            surfaceView.flash(Color.RED, 50);
//        } else if (!(info instanceof WizardGameState))
//            // if we do not have a WizardState, ignore
//            return;
//        else {
//            surfaceView.setState((WizardGameState) info);
//            surfaceView.invalidate();
//            Logger.log(TAG, "receiving");
//       }

        if (info instanceof WizardState) {
            state = (WizardState) info;
//            this.player1Score.setText("Player 1 Total Score: " + (((WizardPlayer) info).getPlayerScore()));
//            this.player2Score.setText("Player 2 Total Score: " + (((WizardPlayer) info).getPlayerScore()));
//            this.player3Score.setText("Player 3 Total Score: " + (((WizardPlayer) info).getPlayerScore()));
//            this.player4Score.setText("Player 4 Total Score: " + (((WizardPlayer) info).getPlayerScore()));

            //sets image to cards in hand
            int i = 0;
            for (; i < state.getRoundNum(); i++) {
                WizardCards card = ((WizardState) info).getPlayerInfo(0).getCurrentHand().get(i);
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
            for (; i < 15; i++) {
                guiCards.get(i).setVisibility(View.INVISIBLE);
            }

            //sets text on gui
            roundText.setText("Round " + state.getRoundNum());
            player1Score.setText("PLAYER 1\n Bid: " + myPlayer.getBidNum() + "\nBids Won: "
                    + myPlayer.getBidNum() + "\nTotal Score: " + myPlayer.getPlayerScore());

            //need for spinner
            for (i = 0; i < state.getRoundNum()+1; i++) {
                spinVal.add(""+i);
            }
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

        //SurfaceView mySurfaceView = (SurfaceView) myActivity.findViewById((R.id.surfaceView));

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

        bidSpinner = (Spinner) myActivity.findViewById(R.id.bidDropdown);
        bidSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(myActivity,
                android.R.layout.simple_spinner_item, spinVal);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bidSpinner.setAdapter(dataAdapter);

        Collections.addAll(guiCards, card1, card2, card3, card4, card5, card6, card7, card8,
                card9, card10, card11, card12, card13, card14, card15);
    }

    /**
     * perform any initialization that needs to be done after the player
     * knows what their game-position and opponents' names are.
     */
    protected void initAfterReady() {
        myActivity.setTitle("Tic-Tac-Toe: " + allPlayerNames[0] + " vs. " + allPlayerNames[1]);
    }

    /*
    bid number dropdown and imageview on click listeners go here
     */


    //spinner listener for the bid dropdown menu
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Logger.log("BidNum","bid placed "+ myPlayer.getBidNum());
        if(state.getPlayerTurn()==0) {
            bidNum = pos;
            super.game.sendAction(myBid);
        }

//        if (view.getId() == R.id.bidDropdown) {
//            bidNum = (Integer) parent.getItemAtPosition(pos);
//            super.game.sendAction(myBid);
//        }

        //WizardHumanPlayer is like the controller, so when user interacts with dropdown and chooses a bid
        //we handle that bid here, set it equal to bidNum, and send bidNum through sendAction to the game class
        //WizardLocalGame extends LocalGame which extends Game, so it receives the action from Game

        //myBid is a WizardBidAction object which sends the player and the bid number to WizardBidAction

    }

    //unused method required with spinner
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void onClick(View button) {
//        if (button.getId() == R.id.imageView) {
//
//        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
        int i = 0;
        Logger.log("onTouch","size of hand "+state.getPlayerInfo(0).getCurrentHand().size());
        for (ImageView guiCard : guiCards){
            Logger.log("onTouch","card picked "+ myPlayer.getCurrentHand().get(i));
            if (v == guiCards.get(i)){
                Logger.log("onTouch","card picked "+ myPlayer.getCurrentHand().get(i));
                cardToPlay = myPlayer.getCurrentHand().get(i);
                super.game.sendAction(myPlay);
                return true;
            }
            i++;
        }
        return false;
    }
}