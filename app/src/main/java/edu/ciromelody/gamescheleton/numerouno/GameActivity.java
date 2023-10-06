package edu.ciromelody.gamescheleton.numerouno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import edu.ciromelody.gamescheleton.R;

public class GameActivity extends AppCompatActivity {
//stabiliamo che il nostro schermo sia di 34 metri in lunghezza e 13 metri in altezza
// pertanto ogni dimensione in pixel dei vari dispositivi dovraà essere rapportata a queste misure
//quindi avremo un tot pixelXmetro
//purtroppo questo non ci da la certezza che un giocatore si sposti sui due assi con la stessa velocita su dispositivi diversi
//perchè un dispositivo potrebbe avere una minore densità ma una maggiore velosità di eleborazione
//per avere la stessa velocità su diversi dispositivi di dovrebbe dividere il metro per il numero di giri fatti in un secondo
public int lunghezza_in_pixel_dello_schermo;
public int altezza_in_pixel_dello_schermo;
public int lunghezza_in_metri_dello_schermo;
public int altezza_in_metri_dello_schermo;
public int pixelXmetro_lunghezza;
public int pixelXmetro_altezza;
public int numero_di_giri_fatti_in_un_secondo=1;//metto 1 per evitare l'overflow
public static int velocita_metro_al_secondo_asseX;
public static int velocita_metro_al_secondo_asseY;
GameVew gameVew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inizializzazioneVariabiliCostanti(this);
        gameVew=new GameVew(this,lunghezza_in_pixel_dello_schermo,altezza_in_pixel_dello_schermo);
        setContentView(gameVew);
    }

    private void inizializzazioneVariabiliCostanti(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        Log.d("GAME","larghezza:"+width+" Altezza:"+height);
        lunghezza_in_pixel_dello_schermo=width;
        altezza_in_pixel_dello_schermo=height;
        lunghezza_in_metri_dello_schermo=34;
        altezza_in_metri_dello_schermo=13;
        pixelXmetro_lunghezza=lunghezza_in_pixel_dello_schermo/lunghezza_in_metri_dello_schermo;
        pixelXmetro_altezza=altezza_in_pixel_dello_schermo/altezza_in_metri_dello_schermo;
        Log.d("GAME","pixel x metro sull asse X:"+ pixelXmetro_lunghezza+" pixel x metro sull asse Y:"+pixelXmetro_altezza);

    }

    //questo bloccherà il thread della gameview
    @Override
    protected void onPause() {
        super.onPause();
        gameVew.pause();
    }


    @Override
    protected void onResume() {
        super.onResume();
        gameVew.resume();
    }
}