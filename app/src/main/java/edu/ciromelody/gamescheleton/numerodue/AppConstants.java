package edu.ciromelody.gamescheleton.numerodue;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class AppConstants {
    static public int gameState;
   static BitmapBank bitmapBank;
    static GameEngine gameEngine;
    static int SCREEN_WIDTH, SCREEN_HEIGHT;
    static int gravity;
    static int VELOCITY_WHEN_JUMPED;
    static int VELOCITY_OBSTACLES;
    static Context gameActivityContext;
    static boolean playerGrounded;
    public static   int lunghezza_in_metri_dello_schermo=24;
    public static  int altezza_in_metri_dello_schermo=7;

    public static int lunghezza_in_pixel_dello_schermo;
    public static  int altezza_in_pixel_dello_schermo;

    public static int pixelXmetro_lunghezza;
    public static int pixelXmetro_altezza;
    public static long frequenza;
    public static int secondi;
    public static int velocita=1;
    public static int frequenza_di_riferimento=30     ;
    public static int capacita_lettere_per_riga;

    public static void initialization(Context context){
        setScreenSize(context);
        AppConstants.gameActivityContext = context;
        bitmapBank = new BitmapBank(context.getResources());
        setGameConstants();
        gameEngine = new GameEngine();
    }
    public static void setScreenSize(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        AppConstants.SCREEN_WIDTH = width;
        AppConstants.SCREEN_HEIGHT = height;
        AppConstants.pixelXmetro_lunghezza= AppConstants.SCREEN_WIDTH/AppConstants.lunghezza_in_metri_dello_schermo;
        AppConstants.pixelXmetro_altezza=AppConstants.SCREEN_HEIGHT/AppConstants.altezza_in_metri_dello_schermo;
        AppConstants.frequenza=1;

    }
    public static void setGameConstants(){
        AppConstants.gravity = 3;
        AppConstants.VELOCITY_WHEN_JUMPED = -50;
        AppConstants.VELOCITY_OBSTACLES = 25;
        AppConstants.playerGrounded = true;
    }

    // Return BitmapBank instance
    public static BitmapBank getBitmapBank(){
        return bitmapBank;
    }

    // Return GameEngine instance
    public static GameEngine getGameEngine(){
        return gameEngine;
    }
}
