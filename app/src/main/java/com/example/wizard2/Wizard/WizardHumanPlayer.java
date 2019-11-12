package com.example.wizard2.Wizard;

import android.graphics.Color;
import android.media.Image;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wizard2.GameFramework.GameHumanPlayer;
import com.example.wizard2.GameFramework.GameMainActivity;
import com.example.wizard2.GameFramework.infoMessage.GameInfo;
import com.example.wizard2.GameFramework.infoMessage.IllegalMoveInfo;
import com.example.wizard2.R;

import java.nio.InvalidMarkException;
import java.util.ArrayList;
import java.util.Collections;

public class WizardHumanPlayer extends GameHumanPlayer implements AdapterView.OnItemSelectedListener, View.OnClickListener , View.OnTouchListener{
    private WizardCards cardToPlay;

    private int bidNum = 0;

    WizardPlayAction myPlay = new WizardPlayAction(this, cardToPlay);
    WizardBidAction myBid = new WizardBidAction(this, bidNum);

    WizardPlayer myPlayer;

    //Tag for logging
    private static final String TAG = "WizardHumanPlayer1";
    // the current activity
    private GameMainActivity myActivity;

    // the card picture
    private TextView player1Score = null;
    private TextView player2Score = null;
    private TextView player3Score = null;
    private TextView player4Score = null;
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

    private ArrayList<ImageView> guiCards = new ArrayList<ImageView>();
    /**
     * constructor
     *
     * @param name the player's name
     */
    public WizardHumanPlayer(String name) {
        super(name);

        myPlayer = new WizardPlayer(0, name);
    }

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
//            this.player1Score.setText("Player 1 Total Score: " + (((WizardPlayer) info).getPlayerScore()));
//            this.player2Score.setText("Player 2 Total Score: " + (((WizardPlayer) info).getPlayerScore()));
//            this.player3Score.setText("Player 3 Total Score: " + (((WizardPlayer) info).getPlayerScore()));
//            this.player4Score.setText("Player 4 Total Score: " + (((WizardPlayer) info).getPlayerScore()));
            for (ImageView guiCard : guiCards) {
                for (WizardCards card : ((WizardState) info).getPlayerInfo(0).getCurrentHand()) {
                    switch (card.getCardSuit()) {
                        case "diamond":
                            switch (card.getCardValue()) {
                                case 0:
                                    guiCard.setImageResource(R.drawable.jester);
                                    break;
                                case 2:
                                    guiCard.setImageResource(R.drawable.two_diamond);
                                    break;
                                case 3:
                                    guiCard.setImageResource(R.drawable.three_diamond);
                                    break;
                                case 4:
                                    guiCard.setImageResource(R.drawable.four_diamond);
                                    break;
                                case 5:
                                    guiCard.setImageResource(R.drawable.five_diamond);
                                    break;
                                case 6:
                                    guiCard.setImageResource(R.drawable.six_diamond);
                                    break;
                                case 7:
                                    guiCard.setImageResource(R.drawable.seven_diamond);
                                    break;
                                case 8:
                                    guiCard.setImageResource(R.drawable.eight_diamond);
                                    break;
                                case 9:
                                    guiCard.setImageResource(R.drawable.nine_diamond);
                                    break;
                                case 10:
                                    guiCard.setImageResource(R.drawable.ten_diamond);
                                    break;
                                case 11:
                                    guiCard.setImageResource(R.drawable.jack_diamond);
                                    break;
                                case 12:
                                    guiCard.setImageResource(R.drawable.queen_diamond);
                                    break;
                                case 13:
                                    guiCard.setImageResource(R.drawable.king_diamond);
                                    break;
                                case 14:
                                    guiCard.setImageResource(R.drawable.ace_diamond);
                                    break;
                                case 15:
                                    guiCard.setImageResource(R.drawable.wizard);
                                    break;
                            }
                            break;
                        case "heart":
                            switch (card.getCardValue()) {
                                case 0:
                                    guiCard.setImageResource(R.drawable.jester);
                                    break;
                                case 2:
                                    guiCard.setImageResource(R.drawable.two_heart);
                                    break;
                                case 3:
                                    guiCard.setImageResource(R.drawable.three_heart);
                                    break;
                                case 4:
                                    guiCard.setImageResource(R.drawable.four_heart);
                                    break;
                                case 5:
                                    guiCard.setImageResource(R.drawable.five_heart);
                                    break;
                                case 6:
                                    guiCard.setImageResource(R.drawable.six_heart);
                                    break;
                                case 7:
                                    guiCard.setImageResource(R.drawable.seven_heart);
                                    break;
                                case 8:
                                    guiCard.setImageResource(R.drawable.eight_heart);
                                    break;
                                case 9:
                                    guiCard.setImageResource(R.drawable.nine_heart);
                                    break;
                                case 10:
                                    guiCard.setImageResource(R.drawable.ten_heart);
                                    break;
                                case 11:
                                    guiCard.setImageResource(R.drawable.jack_heart);
                                    break;
                                case 12:
                                    guiCard.setImageResource(R.drawable.queen_heart);
                                    break;
                                case 13:
                                    guiCard.setImageResource(R.drawable.king_heart);
                                    break;
                                case 14:
                                    guiCard.setImageResource(R.drawable.ace_heart);
                                    break;
                                case 15:
                                    guiCard.setImageResource(R.drawable.wizard);
                                    break;
                            }
                            break;
                        case "spade":
                            switch (card.getCardValue()) {
                                case 0:
                                    guiCard.setImageResource(R.drawable.jester);
                                    break;
                                case 2:
                                    guiCard.setImageResource(R.drawable.two_spade);
                                    break;
                                case 3:
                                    guiCard.setImageResource(R.drawable.three_spade);
                                    break;
                                case 4:
                                    guiCard.setImageResource(R.drawable.four_spade);
                                    break;
                                case 5:
                                    guiCard.setImageResource(R.drawable.five_spade);
                                    break;
                                case 6:
                                    guiCard.setImageResource(R.drawable.six_spade);
                                    break;
                                case 7:
                                    guiCard.setImageResource(R.drawable.seven_spade);
                                    break;
                                case 8:
                                    guiCard.setImageResource(R.drawable.eight_spade);
                                    break;
                                case 9:
                                    guiCard.setImageResource(R.drawable.nine_spade);
                                    break;
                                case 10:
                                    guiCard.setImageResource(R.drawable.ten_spade);
                                    break;
                                case 11:
                                    guiCard.setImageResource(R.drawable.jack_spade);
                                    break;
                                case 12:
                                    guiCard.setImageResource(R.drawable.queen_spade);
                                    break;
                                case 13:
                                    guiCard.setImageResource(R.drawable.king_spade);
                                    break;
                                case 14:
                                    guiCard.setImageResource(R.drawable.ace_spade);
                                    break;
                                case 15:
                                    guiCard.setImageResource(R.drawable.wizard);
                                    break;
                            }
                            break;
                        case "club":
                            switch (card.getCardValue()) {
                                case 0:
                                    guiCard.setImageResource(R.drawable.jester);
                                    break;
                                case 2:
                                    guiCard.setImageResource(R.drawable.two_club);
                                    break;
                                case 3:
                                    guiCard.setImageResource(R.drawable.three_club);
                                    break;
                                case 4:
                                    guiCard.setImageResource(R.drawable.four_club);
                                    break;
                                case 5:
                                    guiCard.setImageResource(R.drawable.five_club);
                                    break;
                                case 6:
                                    guiCard.setImageResource(R.drawable.six_club);
                                    break;
                                case 7:
                                    guiCard.setImageResource(R.drawable.seven_club);
                                    break;
                                case 8:
                                    guiCard.setImageResource(R.drawable.eight_club);
                                    break;
                                case 9:
                                    guiCard.setImageResource(R.drawable.nine_club);
                                    break;
                                case 10:
                                    guiCard.setImageResource(R.drawable.ten_club);
                                    break;
                                case 11:
                                    guiCard.setImageResource(R.drawable.jack_club);
                                    break;
                                case 12:
                                    guiCard.setImageResource(R.drawable.queen_club);
                                    break;
                                case 13:
                                    guiCard.setImageResource(R.drawable.king_club);
                                    break;
                                case 14:
                                    guiCard.setImageResource(R.drawable.ace_club);
                                    break;
                                case 15:
                                    guiCard.setImageResource(R.drawable.wizard);
                                    break;
                            }
                            break;
                    }
                }
            }
        }
    }

    /**
     * sets the current player as the activity's GUI
     */
    public void setAsGui(GameMainActivity activity) {

        // remember our activitiy
        myActivity = activity;

        // Load the layout resource for the new configuration
        activity.setContentView(R.layout.activity_main);

        // set the surfaceView instance variable
        //surfaceView = (WizardSurfaceView)myActivity.findViewById(R.id.surfaceView);
        //Logger.log("set listener","OnTouch");
        //surfaceView.setOnTouchListener(this);

        SurfaceView mySurfaceView = (SurfaceView) myActivity.findViewById((R.id.surfaceView));

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
        card9 = (ImageView) myActivity.findViewById(R.id.imageView1);
        card9.setOnTouchListener(this);
        card10 = (ImageView) myActivity.findViewById(R.id.imageView2);
        card10.setOnTouchListener(this);
        card11 = (ImageView) myActivity.findViewById(R.id.imageView3);
        card11.setOnTouchListener(this);
        card12 = (ImageView) myActivity.findViewById(R.id.imageView4);
        card12.setOnTouchListener(this);
        card13 = (ImageView) myActivity.findViewById(R.id.imageView5);
        card13.setOnTouchListener(this);
        card14 = (ImageView) myActivity.findViewById(R.id.imageView6);
        card14.setOnTouchListener(this);
        card15 = (ImageView) myActivity.findViewById(R.id.imageView7);
        card15.setOnTouchListener(this);

        card1Played = (ImageView) myActivity.findViewById(R.id.player1imageView);
        card2Played = (ImageView) myActivity.findViewById(R.id.player2ImageView);
        card3Played = (ImageView) myActivity.findViewById(R.id.player3ImageView);
        card4Played = (ImageView) myActivity.findViewById(R.id.player4ImageView);

        cardTrump = (ImageView) myActivity.findViewById(R.id.trumpImageView);

        Collections.addAll(guiCards, card1, card2, card3, card4, card5, card6, card7, card8,
                card9, card10, card11, card12, card13, card14, card15);
    }

    /**
     * returns the GUI's top view
     *
     * @return the GUI's top view
     */
//    @Override
//    public View getTopView() {
//        return myActivity.findViewById(R.id.top_gui_layout);
//    }

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
        if (view.getId() == R.id.bidDropdown) {
            bidNum = (Integer) parent.getItemAtPosition(pos);
            super.game.sendAction(myBid);
        }

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
        for (ImageView guiCard : guiCards){
            if (v == guiCard){
                cardToPlay = myPlayer.getCurrentHand().get(i);
                super.game.sendAction(myPlay);
                return true;
            }
        }
        return false;
    }
}