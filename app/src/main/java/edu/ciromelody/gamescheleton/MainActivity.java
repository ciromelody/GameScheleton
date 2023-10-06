package edu.ciromelody.gamescheleton;

import static edu.ciromelody.gamescheleton.utility.TypeFaceCustom.VAGRoundedBlack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.ciromelody.gamescheleton.numerouno.GameActivity;
import edu.ciromelody.gamescheleton.utility.TypeFaceCustom;

public class MainActivity extends AppCompatActivity {
Button iniziaGiocoButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       iniziaGiocoButton = findViewById(R.id.btn_inizia_gioco);
        iniziaGiocoButton.setTypeface(TypeFaceCustom.VAGRoundedBlack(this));;
        iniziaGiocoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}