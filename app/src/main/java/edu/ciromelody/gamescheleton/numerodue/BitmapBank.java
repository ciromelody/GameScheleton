package edu.ciromelody.gamescheleton.numerodue;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import edu.ciromelody.gamescheleton.R;
import edu.ciromelody.gamescheleton.utility.BitmapUtility;

public class BitmapBank {
    Bitmap[] player = new Bitmap[15];
    public BitmapBank(Resources res) {
        player[0] = BitmapFactory.decodeResource(res, R.drawable.run1);
        player[1] = BitmapFactory.decodeResource(res, R.drawable.run2);
        player[2] = BitmapFactory.decodeResource(res, R.drawable.run3);
        player[3] = BitmapFactory.decodeResource(res, R.drawable.run4);
        player[4] = BitmapFactory.decodeResource(res, R.drawable.run5);
        player[5] = BitmapFactory.decodeResource(res, R.drawable.run6);
        player[6] = BitmapFactory.decodeResource(res, R.drawable.run7);
        player[7] = BitmapFactory.decodeResource(res, R.drawable.run8);
        player[8] = BitmapFactory.decodeResource(res, R.drawable.run9);
        player[9] = BitmapFactory.decodeResource(res, R.drawable.run10);
        player[10] = BitmapFactory.decodeResource(res, R.drawable.run11);
        player[11] = BitmapFactory.decodeResource(res, R.drawable.run12);
        player[12] = BitmapFactory.decodeResource(res, R.drawable.run13);
        player[13] = BitmapFactory.decodeResource(res, R.drawable.run14);
        player[14] = BitmapFactory.decodeResource(res, R.drawable.run15);
    }
    public Bitmap getPlayer(int pFrame){
        //diciamo che il player è alto 2 metri
        return  BitmapUtility.cambiaDimensioneBitmap(player[pFrame],AppConstants.pixelXmetro_altezza*2,AppConstants.pixelXmetro_altezza*2);
       // return player[pFrame];
    }

    // Return player width
    public int getPlayerWidth(int pFrame){
        return getPlayer(pFrame).getWidth();

        //return player[0].getWidth();
    }

    // Return player height
    public int getPlayerHeight(int pFrame){
        return getPlayer( pFrame).getHeight();

       // return player[0].getHeight();
    }
}
