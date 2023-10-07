package edu.ciromelody.gamescheleton.numerouno;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import edu.ciromelody.gamescheleton.utility.BitmapUtility;
import edu.ciromelody.gamescheleton.utility.Costanti;


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
    ArrowLeft arrowLeft;
    ArrowRight arrowRight;
    ArrowUp arrowUp;
    Dito dito;
    boolean collisionetraduevettori;
    boolean visibile;
    BitmapUtility bitmapUtility;
    AssetManager assetManager;

    int altezza_in_metri_arrow;
    public GameVew(Context context,int lunghezza_in_pixel_asse_X,int lunghezza_in_pixel_asse_Y){
        super(context);
        this.context=context;
        larghezzaschermo=lunghezza_in_pixel_asse_X;
        altezzaschermo=lunghezza_in_pixel_asse_Y;
        Log.d("GAME","larghezza:"+larghezzaschermo+" Altezza:"+ altezzaschermo);
        tempoDiAttesa=17;
        // disegnare testo e oggetti
        ourHolder = getHolder();
        paint = new Paint();
        assetManager = context.getAssets();

        iniziaGioco();
    }

    private void iniziaGioco() {
        arrow=new Arrow((GameActivity)getContext(),larghezzaschermo,altezzaschermo);
        arrowLeft=new ArrowLeft((GameActivity)getContext(),larghezzaschermo,altezzaschermo);
        arrowRight=new ArrowRight((GameActivity)getContext(),larghezzaschermo,altezzaschermo);
        arrowUp=new ArrowUp((GameActivity)getContext(),larghezzaschermo,altezzaschermo,"frecciasu");
        dito=new Dito((GameActivity)getContext(),larghezzaschermo,altezzaschermo,"dito");
        angolodirotazione=0;
        collisionetraduevettori=false;
        visibile=true;
        altezza_in_metri_arrow=2*(altezzaschermo/altezza_in_metri_dello_schermo); //due metri


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
        dito.update();

        arrow.setPositionY(altezzaschermo/2-arrow.getBitmap().getHeight());
        if(Rect.intersects(dito.getHitBox(),arrowLeft.getHitBox())){
            //diminuisci velocita
            if(Costanti.velocita>0){
                   Costanti.velocita-=1;} else if (Costanti.velocita==0) {
                Costanti.velocita=0;

            }
        }
        if(Rect.intersects(dito.getHitBox(),arrowRight.getHitBox())){
            //aumenta velocita
            Costanti.velocita+=1;
            Log.d("TOUCH","contatto:dito freccia destra");
        }
    }


    private void drawSchermo() {

        if (ourHolder.getSurface().isValid()) {
           //Blocchiamo lo schermo per disegnare
            canvas = ourHolder.lockCanvas();

            canvas.drawColor(Color.argb(255, 0, 0, 0));


            Typeface tf = Typeface.createFromAsset(assetManager, "vag-rounded-black.ttf");

            paint.setTypeface(tf);


            paint.setTextAlign(Paint.Align.LEFT);
            paint.setColor(Color.argb(255, 255, 255, 255));
            paint.setTextSize(50);
            canvas.drawText("Frequenza:" + frequenza + " Hz", 10, 50, paint);
            canvas.drawText("Velocita:" + velocita + " m/s"+"-> Costanti.pixelXmetro_lunghezza*velocita"+Costanti.pixelXmetro_lunghezza*velocita+" pixel al secondo", 10, 100, paint);
            canvas.drawText("tempo di attesa:" + tempoDiAttesa + " millisecondi ", 10, 150, paint);
            canvas.drawText("Lunghezza in pixel:" + larghezzaschermo, 10, 200, paint);
            canvas.drawText("Altezza  in pixel:" + altezzaschermo, 10, 250, paint);
            canvas.drawText("pixel per metro asse X:" + Costanti.pixelXmetro_lunghezza, 10, 300, paint);
            canvas.drawText("pixel per metro asse Y:" + Costanti.pixelXmetro_altezza, 10, 350, paint);
            canvas.drawText("secondi:" + Costanti.secondi, 10, 400, paint);
            canvas.drawText("Costanti.velocita:" + Costanti.velocita,arrowRight.positionX-arrowRight.positionX/2, arrowRight.positionY, paint);


               arrow.drawArrow(canvas);
               arrowUp.drawArrow(canvas);
               arrowLeft.drawArrow(canvas);
                arrowRight.drawArrow(canvas);
                dito.drawArrow(canvas);






            //canvas.drawCircle(arrow.positionX+(arrow.getBitmap().getWidth())/2, arrow.positionY+(arrow.getBitmap().getHeight())/2, arrow.getBitmap().getWidth(), paint);


            if(collisionetraduevettori){

            }else {

            }
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
            Costanti.frequenza=frequenza;
            Costanti.secondi+=1;
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
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        // Tap is detected
        if (action == MotionEvent.ACTION_DOWN) {

            Log.d("TOUCH","PRESSEDX:"+(int)event.getX());
            Log.d("TOUCH","PRESSEDY:"+(int)event.getY());
            dito.setPositionX((int) event.getX());
            dito.setPositionY((int) event.getY());
        }
        if (action == MotionEvent.ACTION_UP) {
            dito.setPositionX(larghezzaschermo/2);
            dito.setPositionY(altezzaschermo/2);
        }
        return true;
    }
}
