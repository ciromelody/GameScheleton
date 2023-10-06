package edu.ciromelody.gamescheleton.utility;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;


import edu.ciromelody.gamescheleton.numerouno.Arrow;

public class BitmapUtility {
    Canvas canvas;
    Bitmap bitmap;

    public static   Bitmap RotateBitmap( Canvas canvas,Bitmap bitmap,int positionX,int positionY,float angle)
    {
        // ruota tutto il canvas questo funziona
        canvas.save();
        canvas.rotate(angle, positionX +bitmap.getWidth()/2, positionY + bitmap.getHeight() / 2);
        canvas.drawBitmap(bitmap, positionX, positionY, null);
        canvas.restore();



        return bitmap;
    }
    public static Path disegnaCroce(int oggetto_posizioneX, int oggetto_posizioneY, Arrow arrow,Paint paintsbagliato){


       Path mPath=new Path();
        mPath.reset();
        mPath.moveTo(oggetto_posizioneX, oggetto_posizioneY);

        mPath.lineTo(oggetto_posizioneX+(arrow.getBitmap().getWidth()), oggetto_posizioneY+(arrow.getBitmap().getHeight()));
        mPath.moveTo(oggetto_posizioneX, oggetto_posizioneY+(arrow.getBitmap().getHeight()));
        mPath.lineTo(oggetto_posizioneX+(arrow.getBitmap().getWidth()), oggetto_posizioneY);
      /*  for (int i = 50; i < 100; i++) {
            mPath.moveTo(i, i-1);
            mPath.lineTo(i, i);
        }*/
        mPath.close();
        paintsbagliato.setStrokeWidth(30);
        paintsbagliato.setPathEffect(null);
        paintsbagliato.setColor(Color.RED);
        paintsbagliato.setStyle(Paint.Style.STROKE);
        return mPath;
    }
}
