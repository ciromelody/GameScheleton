package edu.ciromelody.gamescheleton.numerouno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import edu.ciromelody.gamescheleton.R;
import edu.ciromelody.gamescheleton.utility.ConvertiLunghezzaStringInPixel;
import edu.ciromelody.gamescheleton.utility.Costanti;

public class GameActivity extends AppCompatActivity {
//stabiliamo che il nostro schermo sia di 34 metri in lunghezza e 13 metri in altezza
// pertanto ogni dimensione in pixel dei vari dispositivi dovraà essere rapportata a queste misure
//quindi avremo un tot pixelXmetro
//purtroppo questo non ci da la certezza che un giocatore si sposti sui due assi con la stessa velocita su dispositivi diversi
//perchè un dispositivo potrebbe avere una minore densità ma una maggiore velosità di eleborazione
//per avere la stessa velocità su diversi dispositivi di dovrebbe dividere il metro per il numero di giri fatti in un secondo

public int numero_di_giri_fatti_in_un_secondo=1;//metto 1 per evitare l'overflow
public static int velocita_metro_al_secondo_asseX;
public static int velocita_metro_al_secondo_asseY;
GameVew gameVew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inizializzazioneVariabiliCostanti(this);
        gameVew=new GameVew(this, Costanti.lunghezza_in_pixel_dello_schermo,Costanti.altezza_in_pixel_dello_schermo);
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
        Costanti.lunghezza_in_pixel_dello_schermo=width;
        Costanti.altezza_in_pixel_dello_schermo=height;
        Costanti.lunghezza_in_metri_dello_schermo=34;
        Costanti.altezza_in_metri_dello_schermo=13;
        Costanti.pixelXmetro_lunghezza=Costanti.lunghezza_in_pixel_dello_schermo/Costanti.lunghezza_in_metri_dello_schermo;
        Costanti.pixelXmetro_altezza=Costanti.altezza_in_pixel_dello_schermo/Costanti.altezza_in_metri_dello_schermo;
        Log.d("GAME","pixel x metro sull asse X:"+ Costanti.pixelXmetro_lunghezza+" pixel x metro sull asse Y:"+Costanti.pixelXmetro_altezza);
        Costanti.capacita_lettere_per_riga= ConvertiLunghezzaStringInPixel.calcola_quante_lettere_vanno_in_una_riga_in_pixel(width,50);
        Log.d("GAME","capacita_lettere_per_riga="+Costanti.capacita_lettere_per_riga);
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