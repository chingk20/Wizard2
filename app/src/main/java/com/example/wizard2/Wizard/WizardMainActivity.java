package com.example.wizard2.Wizard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.ImageView;

import com.example.wizard2.GameFramework.GameMainActivity;
import com.example.wizard2.GameFramework.LocalGame;
import com.example.wizard2.GameFramework.gameConfiguration.GameConfig;
import com.example.wizard2.R;

public class WizardMainActivity extends GameMainActivity {

    @Override
    public GameConfig createDefaultConfig() {
        return null;
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
