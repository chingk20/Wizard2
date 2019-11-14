package com.example.wizard2.Wizard;

import android.util.Log;

import com.example.wizard2.GameFramework.infoMessage.GameState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Random;

public class WizardState extends GameState {
    public int playerTurn; //which players turn it is
    private int gameStage;  //which state of the game the player is in
    private String trumpSuit;  //suit of trump card
    public int roundNum;

    WizardCards trumpCard;

    private ArrayList<WizardCards> deck = new ArrayList<>();
    public ArrayList<WizardCards> cardsPlayed = new ArrayList<>();
    private ArrayList<WizardPlayer> listOfPlayers = new ArrayList<WizardPlayer>();
    private ArrayList<Integer> playerBids = new ArrayList<>();

    WizardPlayer currentPlayer;
    WizardPlayer player0 = new WizardPlayer(0, "Player 0");
    WizardPlayer player1 = new WizardPlayer(1, "Player 1");
    WizardPlayer player2 = new WizardPlayer(2, "Player 2");
    WizardPlayer player3 = new WizardPlayer(3, "Player 3");

    public WizardState(){
        //Log.i("deck", "i am in wizard state");
        listOfPlayers.add(player3);
        listOfPlayers.add(player0);
        listOfPlayers.add(player1);
        listOfPlayers.add(player2);

        currentPlayer = player0;

        this.playerTurn = 0;    //player 0 will go first
        this.gameStage = 0;     //starts at game state 0: bidding phase
        this.roundNum = 1;

        this.makeCards();
        this.dealDeck(roundNum);
    }

    public void makeCards(){
        WizardCards heartJoker = new WizardCards("heart", 0);
        WizardCards heartTwo = new WizardCards("heart",2);
        WizardCards heartThree = new WizardCards("heart",3);
        WizardCards heartFour = new WizardCards("heart",4);
        WizardCards heartFive = new WizardCards("heart",5);
        WizardCards heartSix = new WizardCards("heart",6);
        WizardCards heartSeven = new WizardCards("heart",7);
        WizardCards heartEight = new WizardCards("heart",8);
        WizardCards heartNine = new WizardCards("heart",9);
        WizardCards heartTen = new WizardCards("heart",10);
        WizardCards heartJack = new WizardCards("heart",11);
        WizardCards heartQueen = new WizardCards("heart",12);
        WizardCards heartKing = new WizardCards("heart",13);
        WizardCards heartAce = new WizardCards("heart",14);
        WizardCards heartWizard = new WizardCards("heart",15);

        WizardCards spadeJoker = new WizardCards("spade", 0);
        WizardCards spadeTwo = new WizardCards("spade",2);
        WizardCards spadeThree = new WizardCards("spade",3);
        WizardCards spadeFour = new WizardCards("spade",4);
        WizardCards spadeFive = new WizardCards("spade",5);
        WizardCards spadeSix = new WizardCards("spade",6);
        WizardCards spadeSeven = new WizardCards("spade",7);
        WizardCards spadeEight = new WizardCards("spade",8);
        WizardCards spadeNine = new WizardCards("spade",9);
        WizardCards spadeTen = new WizardCards("spade",10);
        WizardCards spadeJack = new WizardCards("spade",11);
        WizardCards spadeQueen = new WizardCards("spade",12);
        WizardCards spadeKing = new WizardCards("spade",13);
        WizardCards spadeAce = new WizardCards("spade",14);
        WizardCards spadeWizard = new WizardCards("spade",15);

        WizardCards diamondJoker = new WizardCards("diamond", 0);
        WizardCards diamondTwo = new WizardCards("diamond",2);
        WizardCards diamondThree = new WizardCards("diamond",3);
        WizardCards diamondFour = new WizardCards("diamond",4);
        WizardCards diamondFive = new WizardCards("diamond",5);
        WizardCards diamondSix = new WizardCards("diamond",6);
        WizardCards diamondSeven = new WizardCards("diamond",7);
        WizardCards diamondEight = new WizardCards("diamond",8);
        WizardCards diamondNine = new WizardCards("diamond",9);
        WizardCards diamondTen = new WizardCards("diamond",10);
        WizardCards diamondJack = new WizardCards("diamond",11);
        WizardCards diamondQueen = new WizardCards("diamond",12);
        WizardCards diamondKing = new WizardCards("diamond",13);
        WizardCards diamondAce = new WizardCards("diamond",14);
        WizardCards diamondWizard = new WizardCards("diamond",15);

        WizardCards clubJoker = new WizardCards("club", 0);
        WizardCards clubTwo = new WizardCards("club",2);
        WizardCards clubThree = new WizardCards("club",3);
        WizardCards clubFour = new WizardCards("club",4);
        WizardCards clubFive = new WizardCards("club",5);
        WizardCards clubSix = new WizardCards("club",6);
        WizardCards clubSeven = new WizardCards("club",7);
        WizardCards clubEight = new WizardCards("club",8);
        WizardCards clubNine = new WizardCards("club",9);
        WizardCards clubTen = new WizardCards("club",10);
        WizardCards clubJack = new WizardCards("club",11);
        WizardCards clubQueen = new WizardCards("club",12);
        WizardCards clubKing = new WizardCards("club",13);
        WizardCards clubAce = new WizardCards("club",14);
        WizardCards clubWizard = new WizardCards("club",15);

        Collections.addAll(deck, heartJoker, heartTwo, heartThree, heartFour, heartFive, heartSix, heartSeven, heartEight, heartNine, heartTen, heartJack, heartQueen, heartKing, heartAce, heartWizard,
                spadeJoker, spadeTwo, spadeThree, spadeFour, spadeFive, spadeSix, spadeSeven, spadeEight, spadeNine, spadeTen, spadeJack, spadeQueen, spadeKing, spadeAce, spadeWizard,
                diamondJoker, diamondTwo, diamondThree, diamondFour, diamondFive, diamondSix, diamondSeven, diamondEight, diamondNine, diamondTen, diamondJack, diamondQueen, diamondKing, diamondAce, diamondWizard,
                clubJoker, clubTwo, clubThree, clubFour, clubFive, clubSix, clubSeven, clubEight, clubNine, clubTen, clubJack, clubQueen, clubKing, clubAce, clubWizard);

    }


    //deals a card out to a player
    public void dealDeck(int numTricks){
        //Log.i("deck", "i am in deal deck");
        Random random = new Random();
        for (int i = 0; i < listOfPlayers.size(); i++){
            for (int round = 0; round < numTricks; round++) {
                int randomCard = random.nextInt(deck.size());
                listOfPlayers.get(i).addCardtoHand(deck.get(randomCard));
                deck.remove(randomCard);
            }
        }
        //Log.i("player 1 hand", "player 1 hand: "+player1.getCurrentHand());
        int randomCard = random.nextInt(deck.size());
        trumpCard = deck.get(randomCard);
        trumpSuit = deck.get(randomCard).getCardSuit();
        deck.remove(randomCard);
        //Log.i("trumpCard", "trump card: "+ trumpCard);
    }

    //copy constructor
    public WizardState(WizardState myState){
        playerTurn = myState.playerTurn;
        gameStage = myState.gameStage;
        trumpCard = myState.trumpCard;
        roundNum = myState.roundNum;

        for (WizardCards card : myState.deck){
            deck.add(card);
        }
        for (WizardPlayer player : myState.listOfPlayers){
            listOfPlayers.add(player);
        }
    }

    public int getPlayerTurn() {return playerTurn; }

    public int getGameStage() { return gameStage; }

    public WizardCards getTrumpCard() { return trumpCard; }

    public String getTrumpSuit() {return trumpSuit;}

    public int getRoundNum() { return roundNum; }

    public void setPlayerTurn(int playerTurn) { this.playerTurn = playerTurn; }

    public void setGameStage(int gameStage) { this.gameStage = gameStage; }

    public void setTrumpCard(WizardCards trumpCard) { this.trumpCard = trumpCard; }

    public void setTrumpSuit(String trumpSuit) {this.trumpSuit = trumpSuit;}

    public void setRoundNum(int roundNum) { this.roundNum = roundNum; }

    //public void setCardNumber(int cardNumber) {this.cardNumber = cardNumber; }

    //public void setCardSuit(String cardSuit) {this.cardSuit = cardSuit; }

    //Get information from a certain player, must provide the desired player number
    public WizardPlayer getPlayerInfo(int playerID){
        return listOfPlayers.get(playerID);
    }

    public ArrayList<Integer> getPlayerBids(){return playerBids;}

    public ArrayList<WizardCards> getCardsPlayed() {return cardsPlayed;}

    public void setPlayerBids(ArrayList newPlayerBids){this.playerBids = newPlayerBids;}
}
