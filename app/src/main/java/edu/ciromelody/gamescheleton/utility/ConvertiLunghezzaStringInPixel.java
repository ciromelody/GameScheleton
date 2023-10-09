package edu.ciromelody.gamescheleton.utility;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

public class ConvertiLunghezzaStringInPixel {
 public static float   convertiStringLenToPixel(String text,int texSize){
        Paint mPaint;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setTextSize(texSize);
        mPaint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.ITALIC));
// ...
        float w = mPaint.measureText(text, 0, text.length());
        Log.d("TESTO","la stringa passata:"+text+" misura in pixel:"+w);
        return w;
    }
    public static int calcola_quante_lettere_vanno_in_una_riga_in_pixel(int lunghezza_in_pixel_riga,int textSize){
        int i=0;
        int massima_capacita_in_lettere=0;
        String stringa_di_lettere="aB";
        while ( convertiStringLenToPixel(stringa_di_lettere.substring(0,i),textSize)<lunghezza_in_pixel_riga){
               i+=1;
              stringa_di_lettere+="c";
        }
        return massima_capacita_in_lettere=i-1;
    }
    public static int adattaTextSizePaint(String stringaDaAdattare,int  lunghezza_in_pixel,int textSizePaint){

        while ((convertiStringLenToPixel(stringaDaAdattare,textSizePaint))>lunghezza_in_pixel){
            textSizePaint=textSizePaint-1;
        }
        Log.d("GAME","textSizePaint dovrebbe essere="+textSizePaint);
        return textSizePaint;
    }
}
