package edu.ciromelody.gamescheleton.numerodue;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public class Pupazzo {
    Bitmap bitmap;
    int posX,posY,posizioneInizialeX,posizioneinizialeY;
    String nomePupazzo;
    boolean visibile;
    Random r;
    public Pupazzo(Bitmap bitmap, int posizioneInizialeX, int posizioneinizialeY, String nomePupazzo) {
        this.bitmap = bitmap;
        this.posizioneInizialeX = posizioneInizialeX;
        this.posizioneinizialeY = posizioneinizialeY;
        this.nomePupazzo = nomePupazzo;
        r = new Random();
        visibile=true;
    }

    public  void update(){
        muovi();
    }

    private void muovi() {
        if(posX<0){posX=0;}
        if(posX>AppConstants.SCREEN_WIDTH){posX=0;}
        if(posY>AppConstants.SCREEN_HEIGHT){posY=0;}

       /* posX= r.nextInt(800 - 65) + 65;
        posY= r.nextInt(800-0 ) + 0;*/
        if(nomePupazzo.equals("pupazzo00")){
            posX=posX-100;

        }
        if(nomePupazzo.equals("pupazzo01")){
            posX=posX-100;
            posY=posY-20;
        }
        if(nomePupazzo.equals("pupazzo02")){
            posX=posX+100;
            posY=posY-20;
        }
        if(nomePupazzo.equals("pupazzo03")){
            posX=posX+220;
            posY=posY-20;
        }
        if(nomePupazzo.equals("pupazzo04")){
            posX=posX+420;
            posY=posY+20;
        }
        if(nomePupazzo.equals("pupazzo10")){
            posX=posX-100;
            posY=posY+200;
        }
        if(nomePupazzo.equals("pupazzo11")){
            posX=posX+200;
            posY=posY+200;
        }
        if(nomePupazzo.equals("pupazzo12")){
            posX=posX+400;
            posY=posY+300;
        }
        if(nomePupazzo.equals("pupazzo13")){
            posX=posX+600;
            posY=posY+400;
        }
        posizioneInizialeX=posX;
        posizioneinizialeY=posY;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getNomePupazzo() {
        return nomePupazzo;
    }

    public void setNomePupazzo(String nomePupazzo) {
        this.nomePupazzo = nomePupazzo;
    }

    public boolean isVisibile() {
        return visibile;
    }

    public void setVisibile(boolean visibile) {
        this.visibile = visibile;
    }

    public void draw(Canvas canvas){
        if(visibile){
            canvas.drawBitmap(bitmap,posizioneInizialeX,posizioneinizialeY,null);
        }
    }
}
