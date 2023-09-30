package edu.ciromelody.gamescheleton.numerouno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.ciromelody.gamescheleton.MainActivity;
import edu.ciromelody.gamescheleton.R;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
    }

    public void restart(View view) {

        Intent intent = new Intent(GameOver.this, MainActivity.class);
        startActivity(intent);
        finish();
        return;
    }

    public void exit(View view) {
        finish();
        return;
    }
}