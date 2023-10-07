package edu.ciromelody.gamescheleton.utility;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class SplitBitmap extends Sprite{

    public SplitBitmap(Bitmap bitmap, int x, int y) {
        super(bitmap,x,y);
        this.bitmap=bitmap;
        this.x=x;
        this.y=y;

    }

    public Bitmap[][] splitBitmap(Bitmap bitmap, int xCount, int yCount) {
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
    public void disegnaEsplosioneBitmap(Canvas canvas, Bitmap[][] bitmaps, int x, int y){

        for(int x1 = 0; x1 < 5; ++x1) {
            for(int y1 = 0; y1 < 5; ++y1) {
                // Create the sliced bitmap


                canvas.drawBitmap(bitmaps[x1][y1],
                        x+x1*40,y+y1*40,null);

            }

        }}
}
