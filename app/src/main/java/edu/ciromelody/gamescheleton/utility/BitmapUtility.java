package edu.ciromelody.gamescheleton.utility;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;


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
    public static Bitmap cambiaDimensioneBitmap(Bitmap bitmap,int larghezza,int altezza){
        int rapporto= bitmap.getWidth()/ bitmap.getHeight();
        Bitmap nuovoBitmap =Bitmap.createScaledBitmap(  bitmap, larghezza*rapporto, altezza, false);
        return nuovoBitmap;
    }

    public static Bitmap[][] splitBitmap(Bitmap bitmap, int xCount, int yCount) {
        // Allocate a two dimensional array to hold the individual images.
        Bitmap[][] bitmaps = new Bitmap[xCount][yCount]; int width, height;
        // Divide the original bitmap width by the desired vertical column count
        width = bitmap.getWidth() / xCount;
        // Divide the original bitmap height by the desired horizontal row count
        height = bitmap.getHeight() / yCount;
        // Loop the array and create bitmaps
        //for each coordinate
        for(int x = 0; x < xCount; ++x) {
            for(int y = 0; y < yCount; ++y) {
                // Create the sliced bitmap
                bitmaps[x][y] = Bitmap.createBitmap(bitmap, x * width, y * height, width, height); }
        }
        // Return the array return bitmaps; }
        return bitmaps;
    }
    public static void disegnaEsplosioneBitmap(Canvas canvas, Bitmap[][] bitmaps, int x, int y){

        for(int x1 = 0; x1 < 5; ++x1) {
            for(int y1 = 0; y1 < 5; ++y1) {
                // Create the sliced bitmap
                

                canvas.drawBitmap(bitmaps[x1][y1],
                        x+x1*40,y+y1*40,null);

            }

        }}

    public static Bitmap convertToCircularBitmap(Bitmap bitmap, int strokeSize) {

        int size = bitmap.getWidth() / 2;
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color =0xff424242;
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        canvas.drawCircle(size,size,size-strokeSize,paint);

        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        final int strokeColor = Color.rgb(0, 0, 0);
        final Paint strokePaint = new Paint();
        strokePaint.setAntiAlias(true);
        strokePaint.setColor(strokeColor);
        strokePaint.setStrokeWidth(strokeSize);
        strokePaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(size,size,size-strokeSize,strokePaint);
        return output;
    }
}


