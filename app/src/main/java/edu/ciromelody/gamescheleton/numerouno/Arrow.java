package edu.ciromelody.gamescheleton.numerouno;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import edu.ciromelody.gamescheleton.R;

public class Arrow {
    Context context;
    Bitmap bitmap;
    //posizione player
    int positionX;
    int positionY;
    //dimensioni schermo
    int larghezzaSchermo;
    int altezzaSchermo;

    // l'oggetto non può uscire dallo schermo
     int maxY;
     int minY;
    //   // l'oggetto non può uscire dallo schermo
     int maxX;
     int minX;
     int velocita;

    // A hit box for collision detection
    private Rect hitBox;
    boolean invertiDirezione=false;
    public Arrow(Context context,int schermoX,int schermoY) {
        this.context=context;
        larghezzaSchermo=schermoX;
        altezzaSchermo=schermoY;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.arrowleft);
        positionX=50;
        positionY=50;
        hitBox = new Rect(positionX, positionY, bitmap.getWidth(), bitmap.getHeight());
        minX=0;
        maxX=larghezzaSchermo-bitmap.getWidth();
        minY=0;
        maxY=altezzaSchermo-bitmap.getHeight();
        velocita=4;

    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Rect getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rect hitBox) {
        this.hitBox = hitBox;
    }

    public void update(){
        // imposta il rettangolo alle stesse dimensioni del bitpmap
        hitBox.left=positionX;
        hitBox.top=positionY;
        hitBox.right=bitmap.getWidth();
        hitBox.right=bitmap.getHeight();
        if(positionX<0){positionX=0;};
        if(positionX>maxX){positionX=maxX;};
        if(positionY<0){positionY=0;};
        if(positionY>maxY){positionY=maxY;};
        if(!invertiDirezione){
            if(positionX==maxX){invertiDirezione=true;}else { positionX+=velocita;}
        }else {
            if(positionX==minX){invertiDirezione=false;}else {  positionX-=velocita;;}
           }


    }
    public void drawArrow(Canvas canvas){
        canvas.drawBitmap(bitmap,positionX,positionY,null);
    }
}
