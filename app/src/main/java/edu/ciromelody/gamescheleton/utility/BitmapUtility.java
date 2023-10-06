package edu.ciromelody.gamescheleton.utility;

import android.graphics.Bitmap;
import android.graphics.Canvas;

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
}
