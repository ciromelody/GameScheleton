package edu.ciromelody.gamescheleton.numerodue;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import edu.ciromelody.gamescheleton.utility.BitmapUtility;
import edu.ciromelody.gamescheleton.utility.MirrorBitmap;

public class GameEngine {
    static public int gameState;
    int pFrame;
    int contatore;
    Player player;
    Path path;
    private Paint paint;
    boolean vaisu,vaiavanti,vaiindietro,vaigiu;
    public GameEngine() {
       player=new Player();
        gameState = 0;
        pFrame = 0;
        contatore=0;
        path=new Path();
        paint=new Paint();
        vaisu=vaigiu=vaiavanti=vaiindietro=false;
        vaiavanti=true;

    }
    public void updateAndDrawPlayer(Canvas canvas) {
        canvas.drawColor(Color.argb(255, 200, 120, 0));
        paint.setColor(Color.argb(255, 255, 255, 255));
        player.aggiornaRectIntornoAlPlayer();
        //canvas.drawBitmap(MirrorBitmap.flip(AppConstants.getBitmapBank().getPlayer(pFrame)), player.getX()-200, player.getY(), null);

        if(vaisu){
        canvas.drawBitmap(BitmapUtility.RotateBitmap(canvas,AppConstants.getBitmapBank().getPlayer(pFrame),player.getX(), player.getY(),270),200, -200, null);}else {
            if(vaiavanti){

                canvas.drawBitmap(AppConstants.getBitmapBank().getPlayer(pFrame), player.getX(), player.getY(), null);
            }else {
                if(vaigiu){

                    canvas.drawBitmap(BitmapUtility.RotateBitmap(canvas,AppConstants.getBitmapBank().getPlayer(pFrame),player.getX(), player.getY(),90),200, -200, null);
                }else {
                    if(vaiindietro){

                        //canvas.drawBitmap(MirrorBitmap.flip(AppConstants.getBitmapBank().getPlayer(pFrame)), player.getX(), player.getY(), null);
                        canvas.drawBitmap(MirrorBitmap.flip(BitmapUtility.RotateBitmap(canvas,AppConstants.getBitmapBank().getPlayer(pFrame),player.getX(), player.getY(),180)), player.getX(),-200, null);
                    }
                }
            }
        }


       /*  paint.setColor(Color.argb(255, 00, 00, 255));
         canvas.drawRect(player.rettangoloSottoIPiedi,paint);
         paint.setColor(Color.argb(255, 00, 255, 255));
        canvas.drawRect(player.rettangoloSopraLaTesta,paint);
        paint.setColor(Color.argb(255, 200, 00, 200));
        canvas.drawRect(player.rettangoloASinistra,paint);
        paint.setColor(Color.argb(255, 250, 25, 25));
        canvas.drawRect(player.rettangoloADestra,paint);*/


        pFrame++;
        if (pFrame > 14) {
            pFrame = 0;
        }
        contatore+=1;
        if(contatore>300){
            contatore=0;
        }
        if( player.pX+AppConstants.getBitmapBank().getPlayer(pFrame).getWidth()>=AppConstants.SCREEN_WIDTH){
            player.vaiSu();
         vaisu=true;
           vaigiu=vaiavanti=vaiindietro=false;
        }
        if( player.pY<=0){
            player.vaiIndietro();
          vaiindietro=true;
            vaisu=vaigiu=vaiavanti=false;
        }
        if( player.pX<=0){player.vaiGiu();
            player.vaiGiu();
          vaigiu=true;
            vaisu=vaiavanti=vaiindietro=false;
        }
        if( player.pY+AppConstants.getBitmapBank().getPlayer(pFrame).getHeight()>=AppConstants.SCREEN_HEIGHT){
            player.vaiAvanti();
          vaiavanti=true;
            vaisu=vaigiu=vaiindietro=false;
        }
    }
}
