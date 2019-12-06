package com.example.wizard2.Wizard;

import android.media.MediaPlayer;

import com.example.wizard2.GameFramework.GameMainActivity;
import com.example.wizard2.GameFramework.GamePlayer;
import com.example.wizard2.GameFramework.LocalGame;
import com.example.wizard2.GameFramework.gameConfiguration.GameConfig;
import com.example.wizard2.GameFramework.gameConfiguration.GamePlayerType;
import com.example.wizard2.R;

import java.io.Serializable;
import java.util.ArrayList;

public class WizardMainActivity extends GameMainActivity implements Serializable {
    private static final int PORT_NUMBER = 5213;

    /**
     * createDefaultConfig
     *
     * Creates player types including: Human, DumbAI and SmartAI
     *
     * @return default config, what players will start the game on start screen
     */

    @Override
    public GameConfig createDefaultConfig() {
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.harry);

        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // Wizard has three player types:  human, dumbAI, and smartAI
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new WizardHumanPlayer(name);
            }
        });
        playerTypes.add(new GamePlayerType("Easy Computer Player") {
            public GamePlayer createPlayer(String name) {
                return new WizardDumbAI(name);
            }
        });
        playerTypes.add(new GamePlayerType("Smart Computer Player") {
            public GamePlayer createPlayer(String name) {
                return new WizardSmartAI(name);
            }
        });

        //will always have 4 players
        GameConfig defaultConfig = new GameConfig(playerTypes,
                4, 4, "Wizard", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Computer 1", 2); // player 2: a computer player
        defaultConfig.addPlayer("Computer 2", 2); // player 2: a computer player
        defaultConfig.addPlayer("Computer 3", 2); // player 2: a computer player
        defaultConfig.setRemoteData("Remote Human Player", "", 0);

        return defaultConfig;
    }

    /**
     * createLocalGame
     *
     * Creates a new game that runs on the server tablet,
     *
     * @return a new, game-specific instance of a sub-class of the LocalGame
     *         class.
     */

    @Override
    public LocalGame createLocalGame() {
        return new WizardLocalGame();
    }
}
