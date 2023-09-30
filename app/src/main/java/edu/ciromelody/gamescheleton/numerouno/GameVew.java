package edu.ciromelody.gamescheleton.numerouno;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;

import android.view.SurfaceControl;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameVew extends SurfaceView implements Runnable {
Context context;
int larghezzaschermo;
int altezzaschermo;
boolean playing;
    Thread gameThread = null;
    // Per disegnare
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder ourHolder;
    private long frequenza;
    private long tempoDiPartenza;
    private long tempoDiArrivo;
    private long framerate;
    private long cicliPerSecondo;
    private long velocita;
    private int contatoreCicli;

    public GameVew(Context context,int lunghezza_in_pixel_asse_X,int lunghezza_in_pixel_asse_Y){
        super(context);
        this.context=context;
        larghezzaschermo=lunghezza_in_pixel_asse_X;
        altezzaschermo=lunghezza_in_pixel_asse_Y;

        // disegnare testo e oggetti
        ourHolder = getHolder();
        paint = new Paint();
        iniziaGioco();
    }

    private void iniziaGioco() {

    }


    @Override
    public void run() {

        while (playing) {
            tempoDiPartenza=System.currentTimeMillis();
            update();
            drawSchermo();
            control();
        }
    }

    private void update() {

    }


    private void drawSchermo() {

        if (ourHolder.getSurface().isValid()) {
           //Blocchiamo lo schermo per disegnare
            canvas = ourHolder.lockCanvas();

            canvas.drawColor(Color.argb(255, 0, 0, 0));




            paint.setTextAlign(Paint.Align.LEFT);
            paint.setColor(Color.argb(255, 255, 255, 255));
            paint.setTextSize(50);
            canvas.drawText("Frequenza:" + frequenza + " Hz", 10, 50, paint);
            //Sblocchiamo e disegnamo
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {

        }
        tempoDiArrivo=System.currentTimeMillis();
        framerate=tempoDiArrivo-tempoDiPartenza;
        if(cicliPerSecondo>=1000){
            frequenza=contatoreCicli;
            cicliPerSecondo=0;
            contatoreCicli=0;
        }else {
            contatoreCicli+=1;
            cicliPerSecondo=cicliPerSecondo+framerate;}
    }
    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {

        }
    }
}
