package edu.ciromelody.gamescheleton.utility;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.util.Log;

import edu.ciromelody.gamescheleton.R;


public class MacchinaDaScrivereVecchia {
    MediaPlayer  tastoMacchinaDaScrivere;
    MediaPlayer  ritornocarrello;
    Paint mPaint;
    int i;
    int Larghezza_tasto;
    int rigaY;
    int numerorighe;
    String[] righe;
    String tasto1;
    String tasto;
    boolean superatariga;
    int nuovapartenza;
    int lunghezzatesto;
    int textSizePaint;
    public MacchinaDaScrivereVecchia(Context context)  {

        this.tastoMacchinaDaScrivere=MediaPlayer.create(context, R.raw.suonotastimacchinadascrivere);
        ritornocarrello=MediaPlayer.create(context, R.raw.ritornocarrello);


        mPaint = new Paint();
        i=1;
        rigaY=360;
        numerorighe=0;
        righe=new String[100];
        tasto1="";
        tasto="";
        superatariga=false;
        nuovapartenza=0;
        lunghezzatesto=120;
        textSizePaint=64;

    }

    public void update(String testodascrivere,int massimospazio,Canvas canvas,int textSizePaint){

        testodascrivere=testodascrivere.substring(nuovapartenza,testodascrivere.length());
        textSizePaint=ConvertiLunghezzaStringInPixel.adattaTextSizePaint(testodascrivere,massimospazio,textSizePaint);
        Log.d("TASTO","testodascrivere "+testodascrivere);
       int lunghezzatesto=testodascrivere.length();

      if (i<=lunghezzatesto){






                              tasto=testodascrivere.substring(0,i+1);


                    Log.d("TASTO","tasto:"+tasto);
                    this.tastoMacchinaDaScrivere.start();
                    if(i==1){
                            Log.d("TASTO","i=1:"+tasto);
                           disegnatasto(tasto,canvas,true,textSizePaint);}
                               else {
                            disegnatasto(tasto,canvas,false,textSizePaint);
                               }
                      float lunghezzainpixel= ConvertiLunghezzaStringInPixel.convertiStringLenToPixel(testodascrivere,64);

                           if( testodascrivere.substring(i,i+1).equals("-")){
                               //disegnatasto("?",canvas,true);
                               disegnatasto(" ",canvas,true,textSizePaint);
                               Log.d("TASTO",testodascrivere.substring(i,i+1));
                               nuovapartenza=i+2;
                               i=0;
                               ritornocarrello.start();
                           }


                    i+=1;}








          if(i==lunghezzatesto){
              ritornocarrello.start();
              i++;



          }
          if(i>lunghezzatesto){
              disegnatasto(testodascrivere,canvas,false,textSizePaint);
              // i=1;


          }


        }


    private void disegnatasto(String tasto,Canvas canvas,boolean aumentariga,int textSizePaint) {

        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setTextSize(textSizePaint);
        mPaint.setColor(Color.RED);
        mPaint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.ITALIC));
       float misuraLarghezzaTasto=ConvertiLunghezzaStringInPixel.convertiStringLenToPixel(tasto,64);
       int larghezzatasto=(int)misuraLarghezzaTasto;

           if(aumentariga){rigaY+=textSizePaint;
                            numerorighe=numerorighe+1;
                           }
              righe[numerorighe]=tasto;
              // Log.d("TASTO","numerorighe:"+numerorighe);
               int ciclo=numerorighe;
               int rigaciclo=rigaY;
               while (ciclo>0){



                       canvas.drawText(righe[ciclo], 20, rigaciclo, mPaint);
                       rigaciclo-=textSizePaint;
                       if(rigaciclo<360){rigaciclo=360;}
                        ciclo-=1;

               }


          //canvas.drawText(tasto, 20, rigaY, mPaint);



       }
}
