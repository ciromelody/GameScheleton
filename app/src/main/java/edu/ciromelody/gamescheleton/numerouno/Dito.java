package edu.ciromelody.gamescheleton.numerouno;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import edu.ciromelody.gamescheleton.R;
import edu.ciromelody.gamescheleton.utility.BitmapUtility;
import edu.ciromelody.gamescheleton.utility.Costanti;

public class Dito {
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

    public String getNomeSprite() {
        return nomeSprite;
    }

    public void setNomeSprite(String nomeSprite) {
        this.nomeSprite = nomeSprite;
    }

    String nomeSprite;
    // A hit box for collision detection
    private Rect hitBox;

    public void setInvertiDirezione(boolean invertiDirezione) {
        this.invertiDirezione = invertiDirezione;
    }

    public boolean isInvertiDirezione() {
        return invertiDirezione;
    }

    boolean invertiDirezione;
    public Dito(Context context, int schermoX, int schermoY, String nomeSprite) {
        this.context=context;
        larghezzaSchermo=schermoX;
        altezzaSchermo=schermoY;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.finger);
        bitmap =Bitmap.createScaledBitmap(  bitmap, Costanti.pixelXmetro_lunghezza, Costanti.pixelXmetro_altezza, false);
        positionX=larghezzaSchermo/2;
        positionY=altezzaSchermo/2;
       // bitmap= BitmapUtility.cambiaDimensioneBitmap(bitmap,3* Costanti.pixelXmetro_lunghezza,3*Costanti.pixelXmetro_altezza);
        hitBox = new Rect(positionX, positionY, bitmap.getWidth(), bitmap.getHeight());
        minX=0;
        maxX=larghezzaSchermo-bitmap.getWidth();
        minY=0;
        maxY=altezzaSchermo-bitmap.getHeight();
        velocita=1;
        invertiDirezione=false;
        this.nomeSprite=nomeSprite;
        hitBox=new Rect(positionX,positionY,positionX+bitmap.getWidth(),positionY+bitmap.getHeight());
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
        hitBox.right=positionX+bitmap.getWidth();
        hitBox.bottom=positionY+bitmap.getHeight();
        /*Log.d("TOUCH","ditoX:"+positionX);
        Log.d("TOUCH","ditoY:"+positionY);*/


    }
    public void drawArrow(Canvas canvas){

        canvas.drawBitmap(bitmap,positionX,positionY,null);
    }

}
