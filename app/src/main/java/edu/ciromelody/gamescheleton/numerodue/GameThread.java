package edu.ciromelody.gamescheleton.numerodue;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
    Paint paint;
    SurfaceHolder surfaceHolder; //Surfaceholder object reference
    boolean isRunning; // Flag to detect whether the thread is running or not
    long startTime, loopTime,frequenza,framerate,velocita,cicliPerSecondo; // Loop start time and loop duration
    long DELAY = 33; // Delay in milliseconds between screen refreshes
    //long DELAY = 3333; // Delay in milliseconds between screen refreshes
    long tempoDiAttesa;
    int contatoreCicli;
    public GameThread(SurfaceHolder surfaceHolder){
        this.surfaceHolder = surfaceHolder;
        paint=new Paint();
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setColor(Color.argb(255, 255, 255, 255));
        paint.setTextSize(15);
        isRunning = true;
        tempoDiAttesa=33;
    }

    @Override
    public void run() {
        // Looping until the boolean is false
        while(isRunning){
           // startTime = SystemClock.uptimeMillis();
            startTime = System.currentTimeMillis();
            // lock the canvas
            Canvas canvas = surfaceHolder.lockCanvas(null);
            if(canvas != null){
                synchronized (surfaceHolder){

                    AppConstants.getGameEngine().updateAndDrawPlayer(canvas);
                   /*AppConstants.getGameEngine().updateAndDrawBackgroundImage(canvas);
                   // AppConstants.getGameEngine().updateAndDrawPath(canvas);
                    AppConstants.getGameEngine().updateAndDrawPlayer(canvas);
                     AppConstants.getGameEngine().updateAndDrawObstacles(canvas);
                    AppConstants.getGameEngine().tapToStart(canvas);
                    AppConstants.getGameEngine().udateAndDrawControl(canvas, framerate,tempoDiAttesa,cicliPerSecondo,paint);*/

                    // unlock the canvas
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            // loop time
            //loopTime = SystemClock.uptimeMillis() - startTime;
            //tempoDiAttesa=DELAY - loopTime;
            // Pause here to make sure we update the right amount per second
           // if(loopTime < DELAY){

                try{
                    Thread.sleep(tempoDiAttesa);
                    control();
                }catch(InterruptedException e){
                    Log.e("Interrupted","Interrupted while sleeping");
                }
           // }


        }
    }
    private void control() {


       long tempoDiArrivo=System.currentTimeMillis();
        framerate=tempoDiArrivo- startTime;

        if(cicliPerSecondo>=1000){
            frequenza=contatoreCicli;
            //velocita= (larghezzaschermo/lunghezza_in_metri_dello_schermo)/frequenza;
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
            cicliPerSecondo=cicliPerSecondo+framerate;
        }

    }

    // Return whether the thread is running
    public boolean isRunning(){
        return isRunning;
    }

    // Sets the thread state, false = stopped, true = running
    public void setIsRunning(boolean state){
        isRunning = state;
    }
}
