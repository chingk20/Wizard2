package com.example.wizard2.Wizard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.ImageView;

import com.example.wizard2.GameFramework.GameMainActivity;
import com.example.wizard2.GameFramework.GamePlayer;
import com.example.wizard2.GameFramework.LocalGame;
import com.example.wizard2.GameFramework.gameConfiguration.GameConfig;
import com.example.wizard2.GameFramework.gameConfiguration.GamePlayerType;
import com.example.wizard2.GameFramework.infoMessage.GameInfo;
import com.example.wizard2.R;

import java.util.ArrayList;

public class WizardMainActivity extends GameMainActivity {
    private static final int PORT_NUMBER = 2278;

    @Override
    public GameConfig createDefaultConfig() {
        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // Pig has two player types:  human and computer
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
        GameConfig defaultConfig = new GameConfig(playerTypes,
                4, 4, "Wizard", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Computer 1", 1);
        defaultConfig.addPlayer("Computer 2", 1); // player 2: a computer player
        defaultConfig.addPlayer("Computer 3", 1); // player 2: a computer player
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
