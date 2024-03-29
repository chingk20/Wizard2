package com.example.wizard2.Wizard;

import android.media.MediaPlayer;

import com.example.wizard2.GameFramework.infoMessage.GameState;
import com.example.wizard2.GameFramework.utilities.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class WizardState extends GameState implements Serializable{
    private static final long serialVersionUID = 7552321013488624386L;
    public int playerTurn;              //which players turn it is
    private int gameStage;              //which state of the game the player is in
    private String trumpSuit;           //suit of trump card
    public int roundNum;                //round number
    public boolean trickOver = false;   //if subround is over
    public int winner;


    WizardCards trumpCard;              //Trump card

    public ArrayList<WizardCards> deck = new ArrayList<>();                 //holds all the cards
    public ArrayList<WizardCards> cardsPlayed = new ArrayList<>();          //holds cards played by players
    private ArrayList<WizardPlayer> listOfPlayers = new ArrayList<WizardPlayer>();     //holds players
    private ArrayList<Integer> playerBids = new ArrayList<>();              //holds bids made by each player
    private ArrayList<Integer> playerBidsWon = new ArrayList<>();           //holds bids won by each player

    WizardPlayer currentPlayer; //not sure if needed

    //PLAYER INFO
    WizardPlayer player0 = new WizardPlayer(0, "Player 0");
    WizardPlayer player1 = new WizardPlayer(1, "Player 1");
    WizardPlayer player2 = new WizardPlayer(2, "Player 2");
    WizardPlayer player3 = new WizardPlayer(3, "Player 3");
    WizardPlayer player4 = new WizardPlayer(4, "Player 4"); //ghost player for timer

    /**
     * Constructor for the WizardState.
     */
    public WizardState(){

        listOfPlayers.add(player0);
        listOfPlayers.add(player1);
        listOfPlayers.add(player2);
        listOfPlayers.add(player3);
        listOfPlayers.add(player4);

        currentPlayer = player0;

        playerBids.add(0);
        playerBids.add(0);
        playerBids.add(0);
        playerBids.add(0);

        playerBidsWon.add(0);
        playerBidsWon.add(0);
        playerBidsWon.add(0);
        playerBidsWon.add(0);

        cardsPlayed.add(null);
        cardsPlayed.add(null);
        cardsPlayed.add(null);
        cardsPlayed.add(null);

        this.playerTurn = 0;    //player 0 will go first
        this.gameStage = 0;     //starts at game state 0: bidding phase
        this.roundNum = 1;      //start at round 1 at beginning
        this.winner = -1;

        this.makeCards();           //creates cards
        this.dealDeck(roundNum);    //deals the deck to everyone's hands
    }

    /**
     * Creates the cards in the deck.
     */
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

        //adds all the cards to deck
        Collections.addAll(deck, heartJoker, heartTwo, heartThree, heartFour, heartFive, heartSix, heartSeven, heartEight, heartNine, heartTen, heartJack, heartQueen, heartKing, heartAce, heartWizard,
                spadeJoker, spadeTwo, spadeThree, spadeFour, spadeFive, spadeSix, spadeSeven, spadeEight, spadeNine, spadeTen, spadeJack, spadeQueen, spadeKing, spadeAce, spadeWizard,
                diamondJoker, diamondTwo, diamondThree, diamondFour, diamondFive, diamondSix, diamondSeven, diamondEight, diamondNine, diamondTen, diamondJack, diamondQueen, diamondKing, diamondAce, diamondWizard,
                clubJoker, clubTwo, clubThree, clubFour, clubFive, clubSix, clubSeven, clubEight, clubNine, clubTen, clubJack, clubQueen, clubKing, clubAce, clubWizard);
    }

    /**
     * Deals the deck out to the players
     *
     * @param numTricks
     *      Number of cards each player gets
     */
    public void dealDeck(int numTricks){
        Logger.log("deck size", "deck size: "+deck.size());
        Random random = new Random();
        //deals out a card to each players hand and removes it from the deck
        for (int i = 0; i < 4; i++){

            for (int round = 0; round < numTricks; round++) {
                if(deck.size()<1){
                    Logger.log("deck size in deal deck", ""+deck.size());
                    break;
                }
                int randomCard = random.nextInt(deck.size());
                listOfPlayers.get(i).addCardtoHand(deck.get(randomCard));
                deck.remove(randomCard);
            }
        }
        //sets trump card, omit if 15th round
        if(getRoundNum() < 15) {

            //next random card is trump card
            int randomCard = random.nextInt(deck.size());
            trumpCard = deck.get(randomCard);

            //ensures that trump card is not wizard or jester
            while (trumpCard.getCardNumber() == 15 || trumpCard.getCardNumber() == 0) {
                randomCard = random.nextInt(deck.size());
                trumpCard = deck.get(randomCard);
            }
            trumpSuit = deck.get(randomCard).getCardSuit();
            trumpCard.setTrumpCard(trumpSuit);
            deck.remove(randomCard);
        }
    }

    /**
     * Copy Constructor for the WizardCards.
     */
    public WizardState(WizardState myState){
        playerTurn = myState.playerTurn;
        gameStage = myState.gameStage;
        trumpCard = myState.trumpCard;
        trumpSuit = myState.trumpSuit;
        roundNum = myState.roundNum;
        currentPlayer = myState.currentPlayer;
        winner = myState.winner;

        for (WizardCards card : myState.cardsPlayed){
            cardsPlayed.add(card);
        }

        for (Integer bid : myState.playerBids){
            playerBids.add(bid);
        }

        for (Integer bid : myState.playerBidsWon){
            playerBidsWon.add(bid);
        }

        for (WizardCards card : myState.deck){
            deck.add(card);
        }
        for (WizardPlayer player : myState.listOfPlayers){
            listOfPlayers.add(player);
        }
    }

    /**
     * CALCULATES WHO WON ROUND BY LOOKING AT PLAYED CARDS VALUE OF EACH PLAYER
     */
    public void calculateWinner(){
        int value=0;
        int base=-1;
        for(int i=0; i<4; i++)
        {
            //HARD CODED VALUES FOR NOW
            WizardCards card = getCardsPlayed().get(i);
            if(card.getCardNumber() == 15){
                value = 1000000;
            } else if(card.getCardSuit()==trumpSuit){
                value = card.getCardValue()*10;
            }
            else{
                value = card.getCardValue();
            }
            if(value > base){
                winner=i;
                base=value;
            }
        }
        setPlayerBidsWon(getPlayerBidsWon().get(winner)+1, winner);
    }

    /**
     * CALCULATES WHO WON ROUND 15 BY LOOKING AT PLAYED CARDS VALUE OF EACH PLAYER
     */
    public void calculateWinnerRound15(){
        int value=0;
        int base=-1;
        int winner=-1;
        for(int i=0; i<4; i++)
        {
            //HARD CODED VALUES FOR NOW
            WizardCards card = getCardsPlayed().get(i);
            value = card.getCardValue();
            if(value > base){
                winner=i;
                base=value;
            }
        }
        setPlayerBidsWon(getPlayerBidsWon().get(winner)+1, winner);
        setWinner(winner);
    }

    /**
     * RESETS CARDS PLAYED BY EACH PLAYER TO NULL
     */
    public void resetImage()
    {
        Logger.log("deck size", "deck size put back: "+deck.size());
        for(int i=0; i<cardsPlayed.size(); i++){
            if(cardsPlayed.get(i)!= null) {
                Logger.log("WizardState","Card put back: " + cardsPlayed.get(i));
                deck.add(cardsPlayed.get(i));
            }
            cardsPlayed.set(i, null);
        }
        Logger.log("deck size", "deck size put back: "+deck.size());
    }

    /**
     * Adds the trump card back into the deck
     */
    public void addTrumpCard(){
        deck.add(trumpCard);
    }

    /**
     * CALCULATES WHO WON ROUND BY LOOKING AT PLAYERS BID NUM AND BIDS WON
     */
    public void calculateScores(){
        player0.setRunningTotal(getPlayerBids().get(0), getPlayerBidsWon().get(0));
        player1.setRunningTotal(getPlayerBids().get(1), getPlayerBidsWon().get(1));
        player2.setRunningTotal(getPlayerBids().get(2), getPlayerBidsWon().get(2));
        player3.setRunningTotal(getPlayerBids().get(3), getPlayerBidsWon().get(3));
        player0.setBidNumWon(playerBidsWon.get(0));
        player1.setBidNumWon(playerBidsWon.get(1));
        player2.setBidNumWon(playerBidsWon.get(2));
        player3.setBidNumWon(playerBidsWon.get(3));

        player0.setPlayerScore(player0.getRunningTotal());
        player1.setPlayerScore(player1.getRunningTotal());
        player2.setPlayerScore(player2.getRunningTotal());
        player3.setPlayerScore(player3.getRunningTotal());
    }

    /**
     * Puts the cards each player has played into an array for scoring later
     */
    public void setCardsPlayed(WizardCards cardsPlayed, int playerID) {
        if(0 <= playerID && playerID <= 3){
            this.cardsPlayed.set(playerID, cardsPlayed);}
    }

    /**
     * sets each players bid number into playerBids array
     */
    public void setPlayerBids(int newPlayerBids, int playerID) {
        if(0 <= playerID && playerID <= 3){
            this.playerBids.set(playerID, newPlayerBids);}
        if(playerID==0){
            player0.setBidNum(newPlayerBids);
        }
        else if (playerID == 1){
            player1.setBidNum(newPlayerBids);
        }
        else if (playerID == 2){
            player2.setBidNum(newPlayerBids);
        }
        else if (playerID == 3){
            player3.setBidNum(newPlayerBids);
        }
    }

    /**
     * sets each players bid number won into playerBidsWon array
     */
    public void setPlayerBidsWon(int newPlayerBids, int playerID) {
        if(0 <= playerID && playerID <= 3){
            this.playerBidsWon.set(playerID, newPlayerBids); }
        if(playerID==0){
            player0.setBidNumWon(newPlayerBids);
        }
        else if (playerID == 1){
            player1.setBidNumWon(newPlayerBids);
        }
        else if (playerID == 2){
            player2.setBidNumWon(newPlayerBids);
        }
        else if (playerID == 3){
            player3.setBidNumWon(newPlayerBids);
        }
    }
    /**
        Setters and getters used to set and retrieve aspects of the game
     */
    public void setWinner(int winner) { this.winner = winner; }

    public void setPlayerTurn(int playerTurn) { this.playerTurn = playerTurn; }

    public void setGameStage(int gameStage) { this.gameStage = gameStage; }

    public void setTrumpCard(WizardCards trumpCard) { this.trumpCard = trumpCard; }

    public void setTrumpSuit(String trumpSuit) {this.trumpSuit = trumpSuit;}

    public void setRoundNum(int roundNum) { this.roundNum = roundNum; }

    public int getPlayerTurn() {return playerTurn; }

    public int getGameStage() { return gameStage; }

    public WizardCards getTrumpCard() { return trumpCard; }

    public String getTrumpSuit() {return trumpSuit;}

    public int getWinner() {return winner;}

    public int getRoundNum() { return roundNum; }

    //Get information from a certain player, must provide the desired player number
    public WizardPlayer getPlayerInfo(int playerID){
        return listOfPlayers.get(playerID);
    }

    public ArrayList<Integer> getPlayerBids(){return playerBids;}

    public ArrayList<Integer> getPlayerBidsWon(){return playerBidsWon;}

    public ArrayList<WizardCards> getCardsPlayed() {return cardsPlayed;}
}


