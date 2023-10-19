package edu.ciromelody.gamescheleton.numerodue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.ciromelody.gamescheleton.R;

public class GameActivity extends AppCompatActivity {
    GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppConstants.initialization(this);
        gameView = new GameView(this);
        setContentView(gameView );
    }
}