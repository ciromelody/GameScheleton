package edu.ciromelody.gamescheleton.numerodue;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

import java.util.Random;

import edu.ciromelody.gamescheleton.utility.BitmapUtility;
import edu.ciromelody.gamescheleton.utility.Costanti;
import edu.ciromelody.gamescheleton.utility.MirrorBitmap;

public class GameEngine {
    static public int gameState;
    int pFrame;
    int contatore;
    Player player;
    Path path;
    private Paint paint;
    Bitmap[][] pupazzi25;
    Pupazzo[][] pupazzo;
    BackgroundImage backgroundImage;
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
         pupazzi25=new Bitmap[5][5];

        pupazzo=new Pupazzo[5][5];
        pupazzo[0][0]=new Pupazzo(  pupazzi25[0][0],45,45,"pupazzo00");
        pupazzo=caricaPupazzi();
        backgroundImage=new BackgroundImage();

    }
    public void updateAndDrawBackgroundImage(Canvas canvas) {
        boolean collision=false;
        if (collision == false) {
            backgroundImage.setX(backgroundImage.getX() - backgroundImage.getVelocity());

            if (backgroundImage.getX() < -AppConstants.getBitmapBank().getBackgroundWidth()) {
                backgroundImage.setX(0);
            }
        }
        canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getX(), backgroundImage.getY(), null);
        if (backgroundImage.getX() < -(AppConstants.getBitmapBank().getBackgroundWidth() - AppConstants.SCREEN_WIDTH)) {
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getX() +
                    AppConstants.getBitmapBank().getBackgroundWidth(), backgroundImage.getY(), null);
        }
    }
    public void updateAndDrawPlayer(Canvas canvas) {
       // canvas.drawColor(Color.argb(255, 200, 120, 0));
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
    public void updateAndDrawPupazzi(Canvas canvas){
       // BitmapUtility.splitBitmap(AppConstants.getBitmapBank().getPupazzi(),5,5);
        //visualizzaPupazzo(canvas);
        canvas.drawBitmap(AppConstants.getBitmapBank().getPupazzi(),100,100,null);
    }
    public Pupazzo[][] caricaPupazzi(){

        pupazzi25=BitmapUtility.splitBitmap(AppConstants.getBitmapBank().getPupazzi(),5,5);
        Random r = new Random();
        pupazzo=new Pupazzo[5][5];
        for(int x1 = 0; x1 < 5; ++x1) {
            for(int y1 = 0; y1 < 5; ++y1) {
                // Create the sliced bitmap

                int asseX = r.nextInt(800 - 65) + 65;
                int asseY = r.nextInt(800-0 ) + 0;
                //Log.d("PUPAZZO","X1:"+x1+" Y1:"+y1+pupazzi25[x1][y1].getWidth());
                pupazzo[x1][y1]=new Pupazzo( pupazzi25[x1][y1],asseX,asseY,"pupazzo"+x1+y1);


            }

        }
        return pupazzo;
    }
    public void visualizzaPupazzo(Canvas canvas){
        for(int x1 = 0; x1 < 5; ++x1) {
            for(int y1 = 0; y1 < 5; ++y1) {
                // Create the sliced bitmap

                  /*  if(pupazzo[x1][y1].nomePupazzo.equals("pupazzo23")){
                        pupazzo[x1][y1].visibile=true;
                    }else {pupazzo[x1][y1].visibile=false;}*/
                     pupazzo[x1][y1].update();
                    pupazzo[x1][y1].draw(canvas);
                    // pupazzo[x1][y1].visibile=false;

                  // canvas.drawBitmap(pupazzi25[x1][y1],x1*40,y1*23,null);
            }

        }
    }
}
