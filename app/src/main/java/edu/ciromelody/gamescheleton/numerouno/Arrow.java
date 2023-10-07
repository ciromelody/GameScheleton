package edu.ciromelody.gamescheleton.numerouno;

import static edu.ciromelody.gamescheleton.utility.Costanti.altezza_in_metri_arrow;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import edu.ciromelody.gamescheleton.R;
import edu.ciromelody.gamescheleton.utility.BitmapUtility;
import edu.ciromelody.gamescheleton.utility.Costanti;

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
    int angolodirotazione;

    // A hit box for collision detection
    private Rect hitBox;

    public void setRuota(boolean ruota) {
        this.ruota = ruota;
    }

    boolean ruota;
    Paint paintsbagliato;

    public void setInvertiDirezione(boolean invertiDirezione) {
        this.invertiDirezione = invertiDirezione;
    }

    public boolean isInvertiDirezione() {
        return invertiDirezione;
    }

    boolean invertiDirezione;
    public Arrow(Context context,int schermoX,int schermoY) {
        this.context=context;
        larghezzaSchermo=schermoX;
        altezzaSchermo=schermoY;
        inizializza();

    }

  public void inizializza() {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.arrowleft);
        bitmap= BitmapUtility.cambiaDimensioneBitmap(bitmap,3* Costanti.pixelXmetro_lunghezza,3*Costanti.pixelXmetro_altezza);
        positionX=0;
        positionY=altezzaSchermo/2;
        Log.d("GAME"," positionX:"+ positionX+"  positionY:"+ positionY);
        hitBox = new Rect(positionX, positionY, bitmap.getWidth(), bitmap.getHeight());
        minX=0;
        maxX=larghezzaSchermo-getBitmap().getWidth();
        minY=0;
        maxY=altezzaSchermo-getBitmap().getHeight();
        velocita=1;
        invertiDirezione=false;
        ruota=false;
        paintsbagliato=new Paint();

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
            if(positionX==maxX){invertiDirezione=true;}else { positionX+=velocita*Costanti.pixelXmetro_lunghezza/(Costanti.frequenza+1);}
        }else {
            if(positionX==minX){invertiDirezione=false;}else {  positionX-=velocita*Costanti.pixelXmetro_lunghezza/(Costanti.frequenza+1);}
           }
        angolodirotazione++;
        if(angolodirotazione>359){angolodirotazione=0;}

    }
    public void drawArrow(Canvas canvas){


        canvas.drawBitmap(bitmap,positionX,positionY,null);
       /* cambiaDimensioneBitmap(altezza_in_metri_arrow*3,altezza_in_metri_arrow*3);

        RotateBitmap(canvas,angolodirotazione);
        canvas.drawPath(BitmapUtility.disegnaCroce(positionX, positionY,this,paintsbagliato),paintsbagliato);
        BitmapUtility.disegnaEsplosioneBitmap(canvas,BitmapUtility.splitBitmap(getBitmap(),5,5),positionX,positionY);
        canvas.drawPath(BitmapUtility.disegnaCroce( positionX, positionY,this,paintsbagliato),paintsbagliato);
        RotateBitmap(canvas,angolodirotazione);*/

    }
    public  Bitmap RotateBitmap(Canvas canvas, float angle)
    {
        // ruota tutto il canvas questo funziona
        canvas.save();
        canvas.rotate(angle, positionX +bitmap.getWidth()/2, positionY + bitmap.getHeight() / 2);
        canvas.drawBitmap(bitmap, positionX, positionY, null);
        canvas.restore();



        return bitmap;
    }
    private void cambiaDimensioneBitmap(int larghezza,int altezza){

        int rapporto= getBitmap().getWidth()/ getBitmap().getHeight();
        Bitmap nuovoBitmap =Bitmap.createScaledBitmap(  getBitmap(), larghezza*rapporto, altezza, false);
        setBitmap(nuovoBitmap);
    }
}
