package edu.ciromelody.gamescheleton.numerouno;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.view.SurfaceHolder;
import android.view.SurfaceView;

import edu.ciromelody.gamescheleton.utility.BitmapUtility;


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
    long tempoDiAttesa;
    private int contatoreCicli;
    int lunghezza_in_metri_dello_schermo=34;
    int altezza_in_metri_dello_schermo=13;
    int angolodirotazione;
    Arrow arrow;
    ArrowUp arrowUp;
    boolean collisionetraduevettori;
    boolean visibile;
    BitmapUtility bitmapUtility;
    public GameVew(Context context,int lunghezza_in_pixel_asse_X,int lunghezza_in_pixel_asse_Y){
        super(context);
        this.context=context;
        larghezzaschermo=lunghezza_in_pixel_asse_X;
        altezzaschermo=lunghezza_in_pixel_asse_Y;
        tempoDiAttesa=17;
        // disegnare testo e oggetti
        ourHolder = getHolder();
        paint = new Paint();

        iniziaGioco();
    }

    private void iniziaGioco() {
        arrow=new Arrow((GameActivity)getContext(),larghezzaschermo,altezzaschermo);
        arrowUp=new ArrowUp((GameActivity)getContext(),larghezzaschermo,altezzaschermo,"frecciasu");
        angolodirotazione=0;
        collisionetraduevettori=false;
        visibile=true;

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
        arrow.update();
        arrowUp.update();
        if(arrow.getHitBox().intersect(arrowUp.getHitBox())){
            // i due oggetti si sono toccati

            collisionetraduevettori = !collisionetraduevettori;

        }
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
            canvas.drawText("Velocita:" + velocita + " m/s", 10, 100, paint);
            canvas.drawText("trmpo di attesa:" + tempoDiAttesa + " millisecondi ", 10, 150, paint);



            arrow.setRuota(true);
              // arrow.drawArrow(canvas);
            angolodirotazione++;
            if(angolodirotazione>359){angolodirotazione=0;}
            arrow.RotateBitmap(canvas,angolodirotazione);

            if(collisionetraduevettori){
                ruotaspazzatura(canvas);
            }else { arrowUp.drawArrow(canvas);}
            //Sblocchiamo e disegnamo
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            if(tempoDiAttesa<2){tempoDiAttesa=2;}
            gameThread.sleep(tempoDiAttesa);
        } catch (InterruptedException e) {

        }
        tempoDiArrivo=System.currentTimeMillis();
        framerate=tempoDiArrivo-tempoDiPartenza;
        if(cicliPerSecondo>=1000){
            frequenza=contatoreCicli;
            velocita= (larghezzaschermo/lunghezza_in_metri_dello_schermo)/frequenza;
            cicliPerSecondo=0;
            contatoreCicli=0;
            if(frequenza>=31){
                tempoDiAttesa+=1;
            }
            if(frequenza<=29){
                tempoDiAttesa-=1;
            }
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
    private void ruotaspazzatura(Canvas canvas) {

        angolodirotazione++;
        if(angolodirotazione>359){angolodirotazione=0;}
        // Log.e("MAIN", "palloncino6.RotateBitmap(canvas,angolodirotazione) "+ palloncino6.getNomesprite());
        arrowUp.RotateBitmap(canvas,angolodirotazione);
    }
}
