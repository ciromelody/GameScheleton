package edu.ciromelody.gamescheleton.numerodue;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import edu.ciromelody.gamescheleton.utility.BitmapUtility;

public class GameEngine {
    static public int gameState;
    int pFrame;
    int contatore;
    Player player;
    Path path;
    private Paint paint;
    public GameEngine() {
       player=new Player();
        gameState = 0;
        pFrame = 0;
        contatore=0;
        path=new Path();
        paint=new Paint();

    }
    public void updateAndDrawPlayer(Canvas canvas) {
        canvas.drawColor(Color.argb(255, 200, 120, 0));
        paint.setColor(Color.argb(255, 255, 255, 255));

        canvas.drawBitmap(AppConstants.getBitmapBank().getPlayer(pFrame), player.getX(), player.getY(), null);
        canvas.drawBitmap(BitmapUtility.RotateBitmap(canvas,AppConstants.getBitmapBank().getPlayer(pFrame),200,300,90),200, -200, null);
        pFrame++;
        if (pFrame > 10) {
            pFrame = 0;
        }
        contatore+=1;
        if(contatore>300){
            contatore=0;
        }
    }
}
